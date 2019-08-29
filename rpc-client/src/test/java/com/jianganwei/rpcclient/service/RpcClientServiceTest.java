package com.jianganwei.rpcclient.service;

import com.jianganwei.rpccommon.model.RequestModel;
import org.apache.zookeeper.ZooKeeper;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RpcClientServiceTest {
    @Autowired
    private RpcClientService rpcClientService;
    @Autowired
    private ZooKeeper zooKeeper;

    @Test
    public void test() throws Exception{
        for (int i = 0; i < 5; i++) {
            RequestModel requestModel = new RequestModel();
            requestModel.setMethodName("test");
            System.out.println(rpcClientService.sendRequest(requestModel));
        }
        TimeUnit.SECONDS.sleep(500);
    }
    @Test
    public void test1()throws Exception{

        System.out.println(new String(zooKeeper.getData("/node1",false,null)));
        System.out.println(zooKeeper.getChildren("/",true));
    }
}