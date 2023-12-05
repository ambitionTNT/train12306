package edu.scnu.train12306.member.service;

import edu.scnu.train12306.member.req.MemberRegisterReq;

/**
 * @version 1.0
 * @Description: member会员的业务层
 * @Author: long
 * @Date: 2023/12/5 11:38
 */

public interface MemberService {
    public int count();

    public long register(MemberRegisterReq req);
}
