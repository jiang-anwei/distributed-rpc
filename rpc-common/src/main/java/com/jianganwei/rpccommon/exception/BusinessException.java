package com.jianganwei.rpccommon.exception;

import lombok.Data;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/07/10
 */
@Data
public class BusinessException extends RuntimeException {
    private String msg;
    private String code;

     BusinessException(ExceptionEnum exceptionEnum) {
        this.msg = exceptionEnum.getMsg();
        this.code = exceptionEnum.getCode();
    }

    public BusinessException(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }
}
