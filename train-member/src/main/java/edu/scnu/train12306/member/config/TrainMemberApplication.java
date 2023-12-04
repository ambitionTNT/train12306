package edu.scnu.train12306.member.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author long
 * @version 1.0
 * @ClassName TraninMemberApplication
 * @description: TODO
 * @date 2023/12/4 14:35
 */
@SpringBootApplication
@ComponentScan("edu.scnu.train12306")
public class TrainMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrainMemberApplication.class, args);
    }
}
