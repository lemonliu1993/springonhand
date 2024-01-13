package com.lemon.spring;

/**
 * Created by lemoon on 2024/1/13 10:15
 */
public class LemonApplicationContext {

    private Class configClass;

    public LemonApplicationContext(Class configClass) {
        this.configClass = configClass;

        // 解析配置类
        // ComponentScan注解 ---> 扫描路径 ---> 扫描
    }

    public Object getBean(String beanName) {
        return null;
    }
}
