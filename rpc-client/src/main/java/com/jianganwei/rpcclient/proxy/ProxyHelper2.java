package com.jianganwei.rpcclient.proxy;

import com.jianganwei.rpcclient.service.RpcClientService;
import com.jianganwei.rpccommon.RequestStatus;
import com.jianganwei.rpccommon.exception.BusinessException;
import com.jianganwei.rpccommon.model.RequestModel;
import com.jianganwei.rpccommon.model.ResponseModel;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/26
 */
public class ProxyHelper2 implements MethodInterceptor {


    public  <T> T getInstance(Class<T> calzz) {
        Enhancer enhancer = new Enhancer();
        //设置目标代理类的父类为目标代理类
        enhancer.setSuperclass(calzz);
        //设置回调对象本身
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        try {
            RequestModel requestModel = new RequestModel();
            requestModel.setMethodName(method.getName());
            requestModel.setClassName(o.getClass().getName().split("\\$\\$")[0]);
            requestModel.setPara(objects);
            ResponseModel responseModel = RpcClientService.sendRequest(requestModel);
            if (responseModel.getStatus().equals(RequestStatus.SUCCESS)) {
                return responseModel.getResult();
            } else {
                throw new BusinessException("123", responseModel.getResult().toString());
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
