 package com.zkdx.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zkdx.*;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService=(UserService)ctx.getBean("userServiceImpl");
        System.out.println(userService);
        System.out.println(userService.insertNewUser("lisi", "123", "12345", "Washington DC"));
    }

}
