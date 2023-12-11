package edu.scnu.train12306.common.utils;

/**
 * @author long
 * @version 1.0
 * @ClassName JwTUtil
 * @description: TODO
 * @date 2023/12/9 21:12
 */


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JwTUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JwTUtil.class);

    /**
     * 盐值很重要，不能泄漏，且每个项目都应该不一样，可以放到配置文件中
     */
    private static final String key = "ambition12306";

    /**
     * 包装了hutool的createToken，同时加入了官方推荐的签发时间
     * @param id
     * @param mobile
     * @return token
     */
    public static String createToken(Long id, String mobile) {
        DateTime now = DateTime.now();
        //设置时间
        DateTime expTime = now.offsetNew(DateField.HOUR, 1);
        Map<String, Object> payload = new HashMap<>();
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, expTime);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        // 内容，用户的基础信息
        payload.put("id", id);
        payload.put("mobile", mobile);
        String token = JWTUtil.createToken(payload, key.getBytes());
        LOG.info("生成JWT token：{}", token);
        return token;
    }

    /**
     * 校验，校验token是否有效，时间是否有效
     * @param token
     * @return
     */
    public static boolean validate(String token) {
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        // validate包含了verify
        boolean validate = jwt.validate(0);
        LOG.info("JWT token校验结果：{}", validate);
        return validate;
    }

    /**
     * 根据JSON拿到具体的值
     * @param token
     * @return 用户的信息，JSON格式
     */
    public static JSONObject getJSONObject(String token) {
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        JSONObject payloads = jwt.getPayloads();
        //移除三大时间就是用户的信息，以JSON的形式返回
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        LOG.info("根据token获取原始内容：{}", payloads);
        return payloads;
    }

    public static void main(String[] args) {
        createToken(1L, "123");

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE3MDIxMjgwMDQsIm1vYmlsZSI6IjEyMyIsImlkIjoxLCJleHAiOjE3MDIyMTQ0MDQsImlhdCI6MTcwMjEyODAwNH0.sVFt2EHP04IszOIuD7YhTlWzV2yDwZbagF3qaoLOljg";
        validate(token);

        getJSONObject(token);
    }
}
