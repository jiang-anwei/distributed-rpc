package com.jianganwei.rpcclient.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;


/**
 *需要被动态代理的类
 */
@Documented
@Target(ElementType.TYPE)
@Service
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcClient {
}
