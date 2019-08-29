package com.jianganwei.rpcclient.service;

import com.jianganwei.rpcclient.proxy.ProxyHelper;
import com.jianganwei.rpcclient.selector.RpcScanner;
import com.jianganwei.rpcclient.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * describe:动态代理客户端的方法
 *
 * @author jianganwei
 * @date 2019/08/26
 */
@Slf4j
@Service
public class ProxyService {
    @Autowired
    private ProxyHelper proxyHelper;
    @Autowired
    private RpcScanner scanner;
    @Autowired
    private SpringUtils springUtils;

    @PostConstruct
    private void init() {
        scanner.getRpcClient().forEach(x -> {
            try {
                springUtils.registerBean(x.getName(), proxyHelper.getInstance(x));
                springUtils.getBean(x.getName());
            } catch (Exception e) {
                log.error("动态代理出错", e);
            }
        });
        log.info("动态代理成功");
    }
}
