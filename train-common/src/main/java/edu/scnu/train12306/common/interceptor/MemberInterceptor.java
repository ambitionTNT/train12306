package edu.scnu.train12306.common.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import edu.scnu.train12306.common.context.LoginMemberContext;
import edu.scnu.train12306.common.resp.MemberLoginResp;
import edu.scnu.train12306.common.utils.JwTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author long
 * @version 1.0
 * @ClassName MemberInterceptor
 * @description: 拦截器：Spring框架特有的，常用于登录校验，权限校验，请求日志打印
 * @date 2023/12/11 22:25
 */

@Component
public class MemberInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(MemberInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取header的token参数
        String token = request.getHeader("token");
        if (StrUtil.isNotBlank(token)) {
            LOG.info("获取会员登录token：{}", token);
            JSONObject loginMember = JwTUtil.getJSONObject(token);
            LOG.info("当前登录会员：{}", loginMember);
            //将JSON格式的信息转成member登录返回的信息
            MemberLoginResp memberLoginResp = JSONUtil.toBean(loginMember, MemberLoginResp.class);
            LoginMemberContext.setMember(memberLoginResp);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
