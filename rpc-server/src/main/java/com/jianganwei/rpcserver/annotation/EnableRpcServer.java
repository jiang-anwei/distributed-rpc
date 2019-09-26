package com.jianganwei.rpcserver.annotation;

import com.jianganwei.rpccommon.config.ZookeeperConfig;
import com.jianganwei.rpcserver.init.StartServer;
import com.jianganwei.rpcserver.util.SpringUtils;
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
@Import({ZookeeperConfig.class, StartServer.class, SpringUtils.class})
public @interface EnableRpcServer {
}
