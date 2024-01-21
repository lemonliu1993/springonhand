package com.lemon.service;

import com.lemon.spring.BeanPostProcessor;
import com.lemon.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lemoon on 2024/1/20 21:06
 */
@Component
public class LemonBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("初始化前");

        if (beanName.equals("userService")) {
            ((UserService) bean).setName("lemon");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        System.out.println("初始化后");
        // 匹配
        if (beanName.equals("userServiceImpl")) {
            Object proxyInstance = Proxy.newProxyInstance(LemonBeanPostProcessor.class.getClassLoader(),
                    bean.getClass().getInterfaces(), new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("代理逻辑");
                            return method.invoke(bean, args);
                        }
                    });
            return proxyInstance;
        }

        return bean;
    }
}
