package com.jianganwei.clientdemo.controller;

import com.jianganwei.commonapidemo.service.RpcDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jianganwei
 * @date 2019/9/10
 */
@RestController
public class TestController {
    @Resource(name = "RpcDemoService")
    private RpcDemoService rpcDemoService;

    @GetMapping("/test")
    public Object test(@RequestParam("s") String s) {
        return rpcDemoService.toUpperCase(s);
    }
}
