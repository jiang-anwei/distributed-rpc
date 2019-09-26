package com.jianganwei.rpcclient.selector;

import com.jianganwei.rpcclient.annotation.EnableRpcClient;
import com.jianganwei.rpcclient.annotation.RpcClient;
import com.jianganwei.rpcclient.proxy.ProxyHelper;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/26
 */
@Slf4j
public class RpcClientSelector implements ImportBeanDefinitionRegistrar {
    public static Set<Class<?>> rpcClients;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableRpcClient.class.getName());
        rpcClients = Arrays.stream((String[]) map.get("basePackage")).flatMap(x -> {
            Reflections reflections = new Reflections(x);
            return reflections.getTypesAnnotatedWith(RpcClient.class).stream();
        }).peek(clazz -> {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ProxyHelper.class);
            AbstractBeanDefinition definition = beanDefinitionBuilder.getBeanDefinition();
            definition.getPropertyValues().addPropertyValue("tClass",clazz);
            registry.registerBeanDefinition(clazz.getSimpleName(), definition);
        }).collect(Collectors.toSet());
    }
}
