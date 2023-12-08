package edu.scnu.train12306.common.exception;

/**
 * @author long
 * @version 1.0
 * @ClassName BusinessException
 * @description: TODO
 * @date 2023/12/5 22:26
 */
public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("手机号已经被注册了"),
    MEMBER_MOBILE_NOT_EXIST("手机号未注册"),
    MEMBER_CODE_ERROR("验证码错误");
    private String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
