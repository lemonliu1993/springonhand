package com.lemon.service;

import com.lemon.spring.BeanPostProcessor;
import com.lemon.spring.Component;

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
        return bean;
    }
}
