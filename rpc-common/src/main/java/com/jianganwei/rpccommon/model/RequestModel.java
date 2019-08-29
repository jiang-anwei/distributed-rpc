package com.jianganwei.rpccommon.model;

import lombok.Data;

import java.util.UUID;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/22
 */
@Data
public class RequestModel {
    private String requestId = UUID.randomUUID().toString();
    private String className;
    private String methodName;
    private Object[] para;
}
