package com.mingyang.bootlaunch.common.exception;

import com.mingyang.bootlaunch.common.result.ResultCode;
import lombok.Data;

@Data
public class BuzException extends RuntimeException {
    private Integer code;

    public BuzException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BuzException(ResultCode resultCode) {
        super(resultCode.message());
        this.code = resultCode.code();
    }

    @Override
    public String toString() {
        return "BuzException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }
}