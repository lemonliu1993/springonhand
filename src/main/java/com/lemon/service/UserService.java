package com.lemon.service;

import com.lemon.spring.Autowired;
import com.lemon.spring.Component;
import com.lemon.spring.InitializingBean;
import com.lemon.spring.Scope;

/**
 * Created by lemoon on 2024/1/13 10:39
 */
@Component("userService")
@Scope("singleton")
public class UserService implements InitializingBean {

    @Autowired
    private OrderService orderService;

    private String beanName;

    public void test() {
        System.out.println(orderService);
        System.out.println(beanName);
    }

//    @Override
//    public void setBeanName(String name) {
//        this.beanName = name;
//    }


    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("xxx");
    }
}
