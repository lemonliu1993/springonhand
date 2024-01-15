package com.lemon;

import com.lemon.spring.LemonApplicationContext;

/**
 * Created by lemoon on 2024/1/13 10:15
 */
public class Test {

    public static void main(String[] args) {
        LemonApplicationContext applicationContext = new LemonApplicationContext(AppConfig.class);

        Object userService = applicationContext.getBean("userService");
        Object userService1 = applicationContext.getBean("userService");
        Object userService2 = applicationContext.getBean("userService");

    }
}
