package com.mingyang.bootlaunch.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/6/3 18:19
 * @version: 1.0
 */
@Data
public class Result implements Serializable{
    private Integer code;
    private String msg;
    private Object data;

    public Result() {

    }

    /**
     * 返回成功
     */
    public static Result success() {
        return success(ResultCode.SUCCESS);
    }

    /**
     * 返回成功
     */
    private static Result success(ResultCode resultCode) {
        Result result = new Result();
        result.setCode(resultCode.code());
        result.setMsg(resultCode.message());
        return result;
    }

    /**
     * 返回成功
     */
    public static Result success(Object data) {
        Result result = success(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 返回失败
     * @param resultCode
     * @return
     *
     */
    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setCode(resultCode.code());
        result.setMsg(resultCode.message());
        return result;
    }

    /**
     * 返回失败
     * @param resultCode
     * @return
     *
     */
    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setCode(resultCode.code());
        result.setMsg(resultCode.message());
        result.setData(data);
        return result;
    }
}
