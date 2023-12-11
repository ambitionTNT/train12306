package edu.scnu.train12306.member.config;

import edu.scnu.train12306.common.interceptor.LogInterceptor;
import edu.scnu.train12306.common.interceptor.MemberInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author long
 * @version 1.0
 * @ClassName SpringMvcConfig
 * @description: TODO
 * @date 2023/12/11 22:31
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {




    @Resource
    MemberInterceptor memberInterceptor;
    @Resource
    LogInterceptor logInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);

        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/member/hello",
                        "/member/member/sendCode",
                        "/member/member/login"
                );
    }
}
