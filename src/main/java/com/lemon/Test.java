package com.lemon;

import com.lemon.service.UserService;
import com.lemon.service.UserServiceImpl;
import com.lemon.spring.LemonApplicationContext;

/**
 * Created by lemoon on 2024/1/13 10:15
 */
public class Test {

    public static void main(String[] args) {
        LemonApplicationContext applicationContext = new LemonApplicationContext(AppConfig.class);

//        System.out.println(applicationContext.getBean("userService"));
//        System.out.println(applicationContext.getBean("userService"));
//        System.out.println(applicationContext.getBean("userService"));
//        UserService userService1 = (UserService)applicationContext.getBean("userService");
//        Object userService2 = applicationContext.getBean("userService");
//        userService1.test();
        UserServiceImpl userService = (UserServiceImpl) applicationContext.getBean("userServiceImpl");
        userService.test(); //1. 代理对象   2.业务test

    }
}
