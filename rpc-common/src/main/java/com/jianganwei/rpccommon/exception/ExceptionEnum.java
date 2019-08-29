package com.jianganwei.rpccommon.exception;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/07/10
 */


public enum ExceptionEnum {
    /**
     * 比较器不支持某种类型时抛出该异常
     */
    GET_RPC_RESULT_EXCEPTION("000012", "获取远程调用结果失败");


    private String code;
    private String msg;

    ExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException newInstance() {
        return new BusinessException(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
