package com.jianganwei.rpcclient.service;

import com.jianganwei.rpcclient.proxy.ProxyHelper;
import com.jianganwei.rpcclient.proxy.ProxyHelper2;
import com.jianganwei.rpcclient.selector.RpcClientSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * describe:动态代理客户端的方法
 *
 * @author jianganwei
 * @date 2019/08/26
 */
@Slf4j
public class ProxyService implements BeanPostProcessor {

    private Set<String> beanNames = RpcClientSelector.rpcClients.stream().map(Class::getSimpleName).collect(Collectors.toSet());
    private Map<String, Class<?>> beanNameMap = RpcClientSelector.rpcClients.stream().collect(Collectors.toMap(Class::getSimpleName, x -> x));
    private ProxyHelper2 proxyHelper2 = new ProxyHelper2();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("加载bean：{}", beanName);
//        if (beanNames.contains(beanName)) {
//            return proxyHelper2.getInstance(beanNameMap.get(beanName));
//        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
