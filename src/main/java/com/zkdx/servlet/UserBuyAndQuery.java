 package com.zkdx.servlet;

import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.zkdx.database.*;

import com.zkdx.util.SpringUtil;
@Controller
@SessionAttributes(value = {"order_info","products","orderInfoMap"})
public class UserBuyAndQuery {
    
    
    private EmployeeService employeeService = (EmployeeService)SpringUtil.getBean("employeeService");
    private UserService userService = (UserService)SpringUtil.getBean("userService");
    private ProductService productService = (ProductService)SpringUtil.getBean("productService");
    private OrderInfoService orderInfoService= (OrderInfoService)SpringUtil.getBean("orderInfoService");
    ExtendedAttributeService extendedAttributeService =
        (ExtendedAttributeService)SpringUtil.getBean("extendedAttributeService");
     @RequestMapping(value="/userBuy/{username}")
     public String userBuy(@PathVariable("username")String username,HttpServletRequest request,Map<String, Object> map) {
         
             Enumeration<String>enumString= request.getParameterNames();
             while(enumString.hasMoreElements()) {
                 String en = enumString.nextElement();                
                 System.out.println(en+" "+request.getParameter(en));
                 if(en.startsWith("buyProductID")) {
                     int value=-1;
                     try {
                        
                      value = Integer.parseInt(request.getParameter(en));
                         en=en.substring(12);
                         int productID=Integer.parseInt(en);
                         if(value<=0)continue;
                         ProductInfo info=productService.getProductInfoById(productID);
                         if(info!=null) {
                         value=Math.min(value, info.getInventoryQuantity());
                         productService.modifyProductIntentoryQuantityByProductId(productID, -value);
                         List<ExtendedAttribute> list=extendedAttributeService.listAttributesByProductID(productID);
                         String extendedAttributeString = "";
                         for(ExtendedAttribute attr:list) {
                             String parameterName="ProductID" + info.getId() + " " + attr.getAttributeName();
                             String attrValue = request.getParameter(parameterName);
                             System.out.println(parameterName);
                             System.out.println(attrValue);
                             if (attrValue != null) {
                                 
                                 extendedAttributeString += (attr.getAttributeName() + " "+attrValue+" ");
                             }

                         }
                         orderInfoService.insertNewOrderInfo(username, 
                             new java.sql.Timestamp(System.currentTimeMillis()), info.getProductName(),
                             value, info.getPrice(), extendedAttributeString, info.getBuyingPrice(), info.getProductCategory());
                         }
                     }
                     catch(Exception e) {
                         
                     }
                    
                 }
             }
             map.put("orderInfoMap", orderInfoService.mapOrdersByUsername(username));
             map.put("products", productService.listAllProducts());
         return "products";
     }
     
     @RequestMapping(value="/userQueryByKeyWords/{username}")
     public String userQueryByKeyWords(@PathVariable("username")String username, Map<String, Object> map,
         String productCategory  ) {
         map.put("products", productService.listStatus0ProductsByProductCategory(productCategory));
         return "products";
     }
     
     @RequestMapping(value="/userQueryByCategory/{username}")
     public String userQueryByCategory(@PathVariable("username")String username,
         Map<String, Object> map,String level0,String level1,String level2) {
         if(level0==null||level1==null||level2==null)return "redirect:/index.jsp";
       level0=level0.trim();
       level1=level1.trim();
       level2=level2.trim();
       List<ProductInfo>list =  productService.listStatus0ProductsByProductCategory(level2);
       if("".equals(level2)||list.isEmpty()) {
           list = productService.listStatus0ProductsByProductCategory(level1);
         
       }
       if("".equals(level1)||list.isEmpty()) {
           list = productService.listStatus0ProductsByProductCategory(level0);
         
       }
       map.put("products", list);
         return "products";
     }
}
