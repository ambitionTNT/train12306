package edu.scnu.train12306.member.controller;

import edu.scnu.train12306.common.resp.CommonResp;
import edu.scnu.train12306.member.req.PassengerSaveReq;
import edu.scnu.train12306.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
