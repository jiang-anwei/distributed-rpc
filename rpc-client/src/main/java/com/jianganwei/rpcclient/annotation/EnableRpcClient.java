package com.jianganwei.rpcclient.annotation;

import com.jianganwei.rpcclient.selector.RpcClientSelector;
import com.jianganwei.rpcclient.service.ProxyService;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/26
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcClientSelector.class})
public @interface EnableRpcClient {
    /**
     * 需要扫描的包
     *
     * @return
     */
    String[] basePackage();
}
