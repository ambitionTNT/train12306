package edu.scnu.train12306.common.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


/**
 * @author long
 * @version 1.0
 * @ClassName JacksonConfig
 * @description: 解决前后端交互long类型精度丢失的问题
 * @date 2023/12/12 19:23
 */
//@Configuration
public class JacksonConfig {
//     @Bean
     public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
         ObjectMapper objectMapper = builder.createXmlMapper(false).build();
         SimpleModule simpleModule = new SimpleModule();
         simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
         objectMapper.registerModule(simpleModule);
         return objectMapper;
     }


}

