package com.jianganwei.rpcclient.selector;

import com.jianganwei.rpcclient.annotation.RpcClient;
import lombok.Data;
import org.reflections.Reflections;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/26
 */
@Data
public class RpcScanner {
    private String[] basePackage;

    public Set<Class<?>> getRpcClient() {
        return Arrays.stream(basePackage).flatMap(x -> {
            Reflections reflections=new Reflections(x);
            return reflections.getTypesAnnotatedWith(RpcClient.class).stream();
        }).collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        Reflections reflections=new Reflections("com.jianganwei.rpcclient.service");

        System.out.println(reflections.getTypesAnnotatedWith(RpcClient.class));
    }
}
