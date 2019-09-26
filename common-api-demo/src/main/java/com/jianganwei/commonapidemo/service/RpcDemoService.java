package com.jianganwei.commonapidemo.service;

import com.jianganwei.rpcclient.annotation.RpcClient;

/**
 * @author jianganwei
 * @date 2019/9/10
 */
@RpcClient
public interface RpcDemoService {
    String toUpperCase(String s);
}
