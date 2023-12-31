package edu.scnu.train12306.member.req;

import jakarta.validation.constraints.NotBlank;

/**
 * @author long
 * @version 1.0
 * @ClassName MemberRegisterReq
 * @description: 封装的注册请求参数
 * @date 2023/12/5 20:42
 */
public class MemberRegisterReq {
    /**
     *
     * This field corresponds to the database column member.mobile
     */
    @NotBlank(message = "【手机号】不能为空")
    private String mobile;

    public MemberRegisterReq() {
    }

    public MemberRegisterReq(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
