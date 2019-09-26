package com.jianganwei.rpcserver;

import org.I0Itec.zkclient.ZkClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RpcServerApplicationTests {
    @Autowired
    private ZkClient zkClient;
    @Test
    public void contextLoads() {
        System.out.println(zkClient.getChildren("/rpc"));
    }

}
