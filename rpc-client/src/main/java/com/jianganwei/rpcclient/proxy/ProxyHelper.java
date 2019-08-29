package com.jianganwei.rpcclient.proxy;

import com.jianganwei.rpcclient.service.RpcClientService;
import com.jianganwei.rpccommon.model.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/26
 */
@Component
public class ProxyHelper implements MethodInterceptor {
    @Autowired
    private RpcClientService rpcClientService;


    public <T> T getInstance(Class<T> calzz) {
        Enhancer enhancer = new Enhancer();
        //设置目标代理类的父类为目标代理类
        enhancer.setSuperclass(calzz);
        //设置回调对象本身
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        RequestModel requestModel = new RequestModel();
        requestModel.setMethodName(method.getName());
        requestModel.setClassName(o.getClass().getName());
        requestModel.setPara(objects);
        return rpcClientService.sendRequest(requestModel);
    }
}
