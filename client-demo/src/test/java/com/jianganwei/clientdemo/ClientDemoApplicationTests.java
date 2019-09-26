package com.jianganwei.clientdemo;

import com.jianganwei.commonapidemo.service.RpcDemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientDemoApplicationTests {
    @Autowired
    RpcDemoService rpcDemoService;
    @Test
    public void contextLoads() {
        System.out.println(rpcDemoService.toUpperCase("adsf"));
    }

}
