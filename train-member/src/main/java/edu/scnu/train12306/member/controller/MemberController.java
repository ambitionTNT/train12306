package edu.scnu.train12306.member.controller;

import edu.scnu.train12306.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author long
 * @version 1.0
 * @ClassName MemberController
 * @description: TODO
 * @date 2023/12/5 11:39
 */
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;
    @RequestMapping("/member/count")
    public int count(){
        return memberService.count();
    }
}
