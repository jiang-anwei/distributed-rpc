package com.jianganwei.rpccommon;

import com.jianganwei.rpccommon.config.ZookeeperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class RpcCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcCommonApplication.class, args);
    }

}
