package com.jianganwei.rpcserver.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/07/19
 */
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext context;
    private static DefaultListableBeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        beanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    } public static Object getBean(String name) {
        return context.getBean(name);
    }
    /**
     * 动态注入bean
     *
     * @param beanName
     * @author ming
     * @date 2017-11-09 16:50
     */
    public static void registerBean(String beanName, Object object) {
        //创建beanBuilder
        //注册bean
        beanFactory.registerSingleton(beanName, object);
    }
}
