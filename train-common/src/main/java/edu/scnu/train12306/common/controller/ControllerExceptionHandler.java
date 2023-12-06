package edu.scnu.train12306.common.controller;

import edu.scnu.train12306.common.exception.BusinessException;
import edu.scnu.train12306.common.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author long
 * @version 1.0
 * @ClassName ControllerExceptionHandler
 * @description: TODO
 * @date 2023/12/5 21:22
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);


    /**
     * 校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResp exceptionHandler(BindException e){
        CommonResp commonResp = new CommonResp();
        LOG.error("业务异常：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return commonResp;
    }

    /**
     * 自定义异常处理器，用于处理出现的自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResp exceptionHandler(BusinessException e){
        CommonResp commonResp = new CommonResp();
        LOG.error("业务异常：{}", e.getExceptionEnum().getDesc());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getExceptionEnum().getDesc());
        return commonResp;
    }


    /**
     * 公共异常处理器
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResp exceptionHandler(Exception e){
        CommonResp commonResp = new CommonResp();
        commonResp.setSuccess(false);
        commonResp.setMessage("系统出现异常,请联系管理员");
        return commonResp;
    }
}
