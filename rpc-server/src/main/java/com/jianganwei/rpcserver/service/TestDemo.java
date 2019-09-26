package com.jianganwei.rpcserver.service;

import org.springframework.stereotype.Service;

/**
 * @author jianganwei
 * @date 2019/9/10
 */
@Service
public class TestDemo {

    public Object test(String s){
        return s.toUpperCase();
    }
}
