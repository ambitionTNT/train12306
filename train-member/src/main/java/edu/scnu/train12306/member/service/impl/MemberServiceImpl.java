package edu.scnu.train12306.member.service.impl;

import edu.scnu.train12306.member.mapper.MemberMapper;
import edu.scnu.train12306.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return memberMapper.count();
    }
}
