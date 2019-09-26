package com.jianganwei.serverdemo.service;

import com.jianganwei.commonapidemo.service.RpcDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author jianganwei
 * @date 2019/9/10
 */
@Service
public class RpcDemoServiceImpl implements RpcDemoService {
    @Override
    public String toUpperCase(String s) {
        return s.toUpperCase();
    }
}
