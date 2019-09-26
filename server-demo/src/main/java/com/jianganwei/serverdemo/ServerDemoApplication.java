package com.jianganwei.serverdemo;

import com.jianganwei.rpcserver.annotation.EnableRpcServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRpcServer
public class ServerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerDemoApplication.class, args);
    }

}
