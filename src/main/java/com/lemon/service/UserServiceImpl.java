package com.lemon.service;

import com.lemon.spring.Autowired;
import com.lemon.spring.Component;
import com.lemon.spring.InitializingBean;
import com.lemon.spring.Scope;

/**
 * Created by lemoon on 2024/1/13 10:39
 */
@Component("userServiceImpl")
@Scope("singleton")
public class UserServiceImpl implements UserInterface {

    @Autowired
    private OrderService orderService;

    private String beanName;

    private String name;

    @Override
    public void test() {
        System.out.println(orderService);
        System.out.println(beanName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
