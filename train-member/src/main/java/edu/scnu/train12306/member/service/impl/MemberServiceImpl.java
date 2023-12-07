package edu.scnu.train12306.member.service.impl;

import cn.hutool.core.collection.CollUtil;
import edu.scnu.train12306.common.exception.BusinessException;
import edu.scnu.train12306.common.exception.BusinessExceptionEnum;
import edu.scnu.train12306.common.utils.SnowUtil;
import edu.scnu.train12306.member.domain.Member;
import edu.scnu.train12306.member.domain.MemberExample;
import edu.scnu.train12306.member.mapper.MemberMapper;
import edu.scnu.train12306.member.req.MemberRegisterReq;
import edu.scnu.train12306.member.req.MemberSendCodeReq;
import edu.scnu.train12306.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);
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

    @Override
    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        // 如果手机号不存在，则插入一条记录
        if (!CollUtil.isNotEmpty(members)){
            LOG.info("手机号不存在，插入一条记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        }else {
            LOG.info("手机号存在");
        }
        //生成随机验证码
//        String code = RandomUtil.randomString(4);
        String code = "0000";
        LOG.info("生成短信验证码：{}", code);

        //保存验证码到短信记录表
        // 保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        LOG.info("保存短信记录表");

        //对接短信通道
        LOG.info("对接短信通道,发短信给前端");

    }
}
