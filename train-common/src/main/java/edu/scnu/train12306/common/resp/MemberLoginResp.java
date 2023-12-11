package edu.scnu.train12306.common.resp;

/**
 * @author long
 * @version 1.0
 * @ClassName MemberLoginResp
 * @description: 这种类只是用来封装一些返回的信息，而排除一些敏感信息的返回，比如说密码之类
 * @date 2023/12/8 19:47
 */
public class MemberLoginResp {

    private Long id;


    private String mobile;

    private  String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberLoginResp{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
