package com.lemon.spring;

/**
 * Created by lemoon on 2024/1/18 23:37
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
