package com.jianganwei.rpcclient.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/26
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext context;
    private static DefaultListableBeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        beanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
    }

    public <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }


    public Object getBean(String name) {
        return context.getBean(name);
    }

    /**
     * 动态注入bean
     *
     * @param beanName
     * @author ming
     * @date 2017-11-09 16:50
     */
    public void registerBean(String beanName, Object object) {
        //创建beanBuilder
        //注册bean
        beanFactory.registerSingleton(beanName, object);
    }
}
