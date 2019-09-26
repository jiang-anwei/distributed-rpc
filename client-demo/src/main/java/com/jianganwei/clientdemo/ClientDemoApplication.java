package com.jianganwei.clientdemo;

import com.jianganwei.rpcclient.annotation.EnableRpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRpcClient(basePackage = "com.jianganwei.commonapidemo.service")
@SpringBootApplication
public class ClientDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientDemoApplication.class, args);
    }

}
