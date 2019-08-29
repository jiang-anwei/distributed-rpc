package com.jianganwei.rpcclient.selector;

import com.jianganwei.rpcclient.proxy.ProxyHelper;
import com.jianganwei.rpcclient.service.TestDemo;
import com.jianganwei.rpcclient.util.SpringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RpcScannerTest {
    @Autowired
    TestDemo demo;
    @Autowired
    ProxyHelper proxyHelper;
    @Autowired
    SpringUtils springUtils;
    @Test
    public void test() {
        System.out.println(demo.test());
    }
}