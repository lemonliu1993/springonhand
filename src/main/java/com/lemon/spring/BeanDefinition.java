package com.lemon.spring;

/**
 * Created by lemoon on 2024/1/16 21:38
 */
public class BeanDefinition {

    private Class clazz;
    private String scope;

    //beanName 不再BeanDefinition中，

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public BeanDefinition(Class clazz, String scope) {
        this.clazz = clazz;
        this.scope = scope;
    }

    public BeanDefinition() {
    }
}
