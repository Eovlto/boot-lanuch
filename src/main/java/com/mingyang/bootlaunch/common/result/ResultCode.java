package com.mingyang.bootlaunch.common.result;

import lombok.Data;
import lombok.Getter;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description: 统一返回状态码
 * @date: 2022/6/4 13:29
 * @version: 1.0
 */
public enum ResultCode {
/**
 * 200 - 请求成功
 * 301 - 资源（网页等）被永久转移到其它URL
 * 404 - 请求的资源（网页等）不存在
 * 500 - 内部服务器错误
 *
 * #1000～1999 区间表示参数错误
 * #2000～2999 区间表示用户错误
 * #3000～3999 区间表示接口异常
 */
    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),
    /**
     * 资源（网页等）被永久转移到其它URL
     */
    FOUND(301, "资源（网页等）被永久转移到其它URL"),
    /**
     * 请求的资源（网页等）不存在
     * */
    NOT_FOUND(404, "请求的资源（网页等）不存在"),
    /**
     * 内部服务器错误
     * */
    INTERNAL_SERVER_ERROR(500, "内部服务器错误"),
    /**
     * 参数错误
     * */
    PARAM_ERROR(1000, "参数错误"),
    /**
     * 参数无效
     */
    PARAM_INVALID(1001, "参数无效"),
    /**
     * 参数为空
     */
    PARAM_IS_EMPTY(1002, "参数为空"),
    /**
     * 参数类型错误
     */
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    /**
     * 参数缺失
     */
    PARAM_MISSING(1004, "参数缺失"),
    /**
     * 参数过长
     */
    PARAM_TOO_LONG(1005, "参数过长"),
    /**
     * 参数过短
     * */
    PARAM_TOO_SHORT(1006, "参数过短"),
    /**
     * 参数超出范围
     * */
    PARAM_OUT_OF_RANGE(1007, "参数超出范围"),



    /**
     * 用户错误
     * */
    USER_ERROR(2000, "用户错误"),
    /**
     * 用户未登录,访问的路径需要登录,请先登录
     *
     */
    USER_NOT_LOGIN(2001, "用户未登录,访问的路径需要登录,请先登录"),
    /**
     * 账号不存在或密码错误
     */
    USER_NOT_EXIST(2002, "账号不存在或密码错误"),
    /**
     * 账号已被禁用
     */
    USER_DISABLE(2003, "账号已被禁用"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST_ERROR(2004, "用户不存在"),
    /**
     * 用户已存在
     */
    USER_EXIST_ERROR(2005, "用户已存在"),
    /**
     * 用户名或密码错误
     */
    USER_NAME_OR_PASSWORD_ERROR(2006, "用户名或密码错误"),



    /**
     * 接口异常
     * */
    INTERFACE_ERROR(3000, "接口异常"),
    /**
     * 接口调用异常
     * */
    INTERFACE_INVOKE_ERROR(3001, "接口调用异常"),
    /**
     * 接口返回异常
     * */
    INTERFACE_RETURN_ERROR(3002, "接口返回异常"),
    /**
     * 接口超时
     * */
    INTERFACE_TIMEOUT_ERROR(3003, "接口超时"),
    /**
     * 接口系统异常
     * */
    INTERFACE_SYSTEM_ERROR(3004, "接口系统异常"),
    /**
     * 接口调用失败
     * */
    INTERFACE_INVOKE_FAILED(3005, "接口调用失败"),
;


    private Integer code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer code() {
        return this.code;
    }
    public String message() {
        return this.message;
    }
}
