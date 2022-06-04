package com.mingyang.bootlaunch.common.exception;

import com.mingyang.bootlaunch.common.result.Result;
import com.mingyang.bootlaunch.common.result.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import static com.mingyang.bootlaunch.common.result.Result.failure;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**-------- 通用异常处理方法 --------**/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return failure(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**-------- 指定异常处理方法 --------**/
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result error(NullPointerException e) {
        e.printStackTrace();
        return failure(ResultCode.PARAM_IS_EMPTY);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public Result error(IndexOutOfBoundsException e) {
        e.printStackTrace();
        return failure(ResultCode.NOT_FOUND);
    }

    /**-------- 自定义定异常处理方法 --------**/
    @ExceptionHandler(BuzException.class)
    @ResponseBody
    public Result error(BuzException e) {
        e.printStackTrace();
        return failure(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}