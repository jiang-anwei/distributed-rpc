package com.jianganwei.rpcclient.annotation;

import com.jianganwei.rpcclient.proxy.ProxyHelper;
import com.jianganwei.rpcclient.selector.RpcClientSelector;
import com.jianganwei.rpcclient.service.ProxyService;
import com.jianganwei.rpcclient.service.RpcClientService;
import com.jianganwei.rpcclient.util.RemoteUtil;
import com.jianganwei.rpccommon.config.ZookeeperConfig;
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
@Import({ZookeeperConfig.class, ProxyService.class, RpcClientSelector.class,
        RpcClientService.class,  RemoteUtil.class})
public @interface EnableRpcClient {
    /**
     * 需要扫描的包
     *
     * @return
     */
    String[] basePackage();
}
