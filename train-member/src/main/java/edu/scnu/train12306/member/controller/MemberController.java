package edu.scnu.train12306.member.controller;

import edu.scnu.train12306.common.resp.CommonResp;
import edu.scnu.train12306.member.req.MemberRegisterReq;
import edu.scnu.train12306.member.req.MemberSendCodeReq;
import edu.scnu.train12306.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    public CommonResp<Integer> count(){
        CommonResp<Integer> resp = new CommonResp<>();
        int count = memberService.count();
        resp.setContent(count);
        return resp;
    }

    @PostMapping("/member/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req){
        return new CommonResp<Long>(memberService.register(req));
    }

    @PostMapping("/member/sendCode")
    public CommonResp<Long> sendCode(@Valid MemberSendCodeReq req){
        memberService.sendCode(req);
        return new CommonResp<Long>();
    }
}
