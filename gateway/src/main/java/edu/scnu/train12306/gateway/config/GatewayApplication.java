package edu.scnu.train12306.gateway.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author long
 * @version 1.0
 * @ClassName GatewayAppllication
 * @description: TODO
 * @date 2023/12/4 21:08
 */
@SpringBootApplication
@ComponentScan("edu.scnu.train12306")
public class GatewayApplication {
    private final static Logger LOG = LoggerFactory.getLogger(GatewayApplication.class);
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(GatewayApplication.class);
        ConfigurableEnvironment environment = application.run(args).getEnvironment();
        LOG.info("启动成功");

        LOG.info("网关地址：\t http://127.0.0.1:{}", environment.getProperty("server.port"));
    }
}
