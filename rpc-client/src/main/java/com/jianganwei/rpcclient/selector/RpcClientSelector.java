package com.jianganwei.rpcclient.selector;

import com.jianganwei.rpcclient.annotation.EnableRpcClient;
import com.jianganwei.rpcclient.proxy.ProxyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/26
 */
public class RpcClientSelector implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableRpcClient.class.getName());
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(RpcScanner.class);
        beanDefinitionBuilder.addPropertyValue("basePackage", map.get("basePackage"));
        registry.registerBeanDefinition(RpcScanner.class.getName(), beanDefinitionBuilder.getBeanDefinition());
    }
}
