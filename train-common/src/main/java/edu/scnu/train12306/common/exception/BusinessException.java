package edu.scnu.train12306.common.exception;

/**
 * @author long
 * @version 1.0
 * @ClassName BusinessException
 * @description: TODO
 * @date 2023/12/5 22:31
 */
public class BusinessException extends RuntimeException{
    private BusinessExceptionEnum exceptionEnum;

    public BusinessException(BusinessExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public BusinessExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(BusinessExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }


    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
