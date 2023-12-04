package edu.scnu.train12306.member.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author long
 * @version 1.0
 * @ClassName TestController
 * @description: TODO
 * @date 2023/12/4 14:38
 */
@RestController
public class TestController {
    @RequestMapping("/test/hello")
    public String hello(){
        return "hello Train12306";
    }
}
