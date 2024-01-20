package com.lemon.spring;

/**
 * Created by lemoon on 2024/1/20 21:04
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInitialization(Object bean, String beanName);

}
