package edu.scnu.train12306.member.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@MapperScan("edu.scnu.train12306.member.mapper")
@SpringBootApplication
@ComponentScan("edu.scnu.train12306")
public class TrainMemberApplication {
    private final static Logger LOG = LoggerFactory.getLogger(TrainMemberApplication.class);
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TrainMemberApplication.class);
        ConfigurableEnvironment environment = application.run(args).getEnvironment();
        LOG.info("启动成功");

        LOG.info("地址：\t http://127.0.0.1:{}{}", environment.getProperty("server.port"),environment.getProperty("server.servlet.context-path"));
    }
}
