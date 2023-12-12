package edu.scnu.train12306.member.controller;

import edu.scnu.train12306.common.context.LoginMemberContext;
import edu.scnu.train12306.common.resp.CommonResp;
import edu.scnu.train12306.common.resp.PageResp;
import edu.scnu.train12306.member.req.PassengerQueryReq;
import edu.scnu.train12306.member.req.PassengerSaveReq;
import edu.scnu.train12306.member.resp.PassengerQueryResp;
import edu.scnu.train12306.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author long
 * @version 1.0
 * @ClassName PassengerController
 * @description: TODO
 * @date 2023/12/11 21:49
 */
@RestController
@RequestMapping("/passenger")
public class PassengerController {


    @Resource
    private PassengerService passengerService;
    @PostMapping("/save")
    public CommonResp savePassenger(@Valid @RequestBody PassengerSaveReq req){
        passengerService.save(req);
        return new CommonResp();
    }
    @GetMapping("/list-query")
    public CommonResp<PageResp<PassengerQueryResp>> listQuery(@Valid  PassengerQueryReq req){
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> passengerQueryResps = passengerService.queryList(req);
        return new CommonResp<>(passengerQueryResps);
    }
    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp commonResp = new CommonResp();
        int success = passengerService.delete(id);
        if (success>0){
            commonResp.setMessage("删除成功");
        }else {
            commonResp.setSuccess(false);
            commonResp.setMessage("删除失败");
        }
        return commonResp;
    }
}
