package com.jianganwei.rpccommon.config;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jianganwei
 * @date 2019/9/10
 */

public class ZookeeperConfig {
    @Value("${zk.hosts:127.0.0.1:2181}")
    String zkHosts;
    public static String ROOT_PATH = "/rpc";
    @Bean
    ZkClient zkClient(){
        return new ZkClient(zkHosts);
    }
}
