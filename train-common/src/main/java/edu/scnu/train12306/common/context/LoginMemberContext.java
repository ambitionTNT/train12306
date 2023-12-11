package edu.scnu.train12306.common.context;

import edu.scnu.train12306.common.resp.MemberLoginResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author long
 * @version 1.0
 * @ClassName LoginMemberContext
 * @description: TODO
 * @date 2023/12/11 22:19
 */
public class LoginMemberContext {
    private static final Logger LOG = LoggerFactory.getLogger(LoginMemberContext.class);


    private static ThreadLocal<MemberLoginResp> member = new ThreadLocal<>();

    private static MemberLoginResp getMember(){
        return member.get();
    }

    public static void setMember(MemberLoginResp member) {
        LoginMemberContext.member.set(member);
    }

    /**
     * 获取线程本地变量中的memberId
     * @return
     */
    public static Long getId() {
        try {
            return member.get().getId();
        } catch (Exception e) {
            LOG.error("获取登录会员信息异常", e);
            throw e;
        }
    }
}
