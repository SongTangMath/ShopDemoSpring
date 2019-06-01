package com.zkdx.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zkdx.database.*;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext ctx1 = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(ctx);
        System.out.println(ctx1);
        
        
        System.out.println(ctx.getBean("orderInfoService"));
        System.out.println(ctx1.getBean("orderInfoService"));
        OrderInfoService orderInfoService = (OrderInfoService)ctx.getBean("orderInfoService");
        EmployeeService employeeService = (EmployeeService)ctx.getBean("employeeService");
       CategoryService dao=(CategoryService)ctx.getBean("categoryService");
       ProductService productService=(ProductService)ctx.getBean("productService");
       /*
       dao.insertNewCategory("家用电器", "", 0, 0);
       dao.insertNewCategory("手机", "", 0, 0);
       dao.insertNewCategory("运营商", "", 0, 0);
       dao.insertNewCategory("数码", "", 0, 0);
       dao.insertNewCategory("电脑", "", 0, 0);
       
       dao.insertNewCategory("电脑整机", "电脑", 0, 1);
       
       dao.insertNewCategory("电脑配件", "电脑", 0, 1);
       dao.insertNewCategory("外设产品", "电脑", 0, 1);

       dao.insertNewCategory("电视", "家用电器", 0, 1);
       dao.insertNewCategory("空调", "家用电器", 0, 1);
       dao.insertNewCategory("洗衣机", "家用电器", 0, 1);
       dao.insertNewCategory("冰箱", "家用电器", 0, 1);

       dao.insertNewCategory("超薄电视", "电视", 0, 2);
       dao.insertNewCategory("全面屏电视", "电视", 0, 2);
       dao.insertNewCategory("智能电视", "电视", 0, 2);

       dao.insertNewCategory("空调挂机", "空调", 0, 2);
       dao.insertNewCategory("空调柜机", "空调", 0, 2);
       dao.insertNewCategory("中央空调", "空调", 0, 2);
       
       dao.insertNewCategory("男鞋", "", 0, 0);
       dao.insertNewCategory("流行男鞋", "男鞋", 0, 1);
       dao.insertNewCategory("新品推荐", "流行男鞋", 0, 2);
       dao.insertNewCategory("商务休闲鞋", "流行男鞋", 0, 2);
       dao.insertNewCategory("休闲鞋", "流行男鞋", 0, 2);
       
       productService.insertNewProduct("海信(Hisense)HZ55E5A 55英寸 超高清4K ", 0,3999,10,"https://img11.360buyimg.com/n7/jfs/t29518/5/50591075/273007/2bc96965/5be66874N2b394fba.jpg",  "", 3000, "家用电器 电视  超薄电视");
       productService.insertNewProduct("小(MI)小米全面屏电视E55A 55英寸", 0,4999,20,"https://img14.360buyimg.com/n7/jfs/t27709/201/1586417750/361172/4e970d11/5be66bf1N8f00d10e.jpg",  "", 3700, "家用电器 电视  智能电视");
    
       for(int i=0;i<100;i++)
       orderInfoService.insertNewOrderInfo("user"+i, 
           new java.sql.Timestamp(System.currentTimeMillis()-86400000L*i), 
           "product"+i, i+1, 100+i, "", i, "testCategory");*/
    }

}
