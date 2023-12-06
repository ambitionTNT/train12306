package edu.scnu.train12306.member.service.impl;

import cn.hutool.core.collection.CollUtil;
import edu.scnu.train12306.common.exception.BusinessException;
import edu.scnu.train12306.common.exception.BusinessExceptionEnum;
import edu.scnu.train12306.common.utils.SnowUtil;
import edu.scnu.train12306.member.domain.Member;
import edu.scnu.train12306.member.domain.MemberExample;
import edu.scnu.train12306.member.mapper.MemberMapper;
import edu.scnu.train12306.member.req.MemberRegisterReq;
import edu.scnu.train12306.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author long
 * @version 1.0
 * @ClassName MemberServiceImpl
 * @description: TODO
 * @date 2023/12/5 11:38
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public int count() {
        return (int) memberMapper.countByExample(null);
    }

    @Override
    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);

        if (CollUtil.isNotEmpty(members)){
             throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();


    }
}
