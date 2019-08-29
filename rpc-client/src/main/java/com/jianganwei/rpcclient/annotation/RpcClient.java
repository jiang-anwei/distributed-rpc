package com.jianganwei.rpcclient.annotation;

import java.lang.annotation.*;


/**
 *需要被动态代理的类
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcClient {
}
