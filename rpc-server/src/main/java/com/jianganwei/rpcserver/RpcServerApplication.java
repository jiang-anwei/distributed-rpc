package com.jianganwei.rpcserver;

import com.jianganwei.rpccommon.config.ZookeeperConfig;
import com.jianganwei.rpcserver.annotation.EnableRpcServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableRpcServer
public class RpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcServerApplication.class, args);
    }

}
