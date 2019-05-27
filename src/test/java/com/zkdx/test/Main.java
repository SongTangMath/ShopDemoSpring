package com.zkdx.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.zkdx.*;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderInfoService orderInfoService = (OrderInfoService)ctx.getBean("orderInfoService");
        EmployeeService employeeService = (EmployeeService)ctx.getBean("employeeService");

    }

}
