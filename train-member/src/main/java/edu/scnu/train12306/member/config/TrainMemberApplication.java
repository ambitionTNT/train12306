package edu.scnu.train12306.member.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author long
 * @version 1.0
 * @ClassName TraninMemberApplication
 * @description: TODO
 * @date 2023/12/4 14:35
 */
@SpringBootApplication
@ComponentScan("edu.scnu.train12306")
@Slf4j
public class TrainMemberApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TrainMemberApplication.class);
        ConfigurableEnvironment environment = application.run(args).getEnvironment();
        log.info("启动成功");

        log.info("地址：\thttp://127.0.0.1:{}{}", environment.getProperty("server.port"),environment.getProperty("server.servlet.context-path"));
    }
}
