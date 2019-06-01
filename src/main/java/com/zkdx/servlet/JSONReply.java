package com.zkdx.servlet;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkdx.database.*;

import com.zkdx.util.SpringUtil;

@Controller
public class JSONReply {

    private EmployeeService employeeService = (EmployeeService)SpringUtil.getBean("employeeService");
    private UserService userService = (UserService)SpringUtil.getBean("userService");
    private ProductService productService = (ProductService)SpringUtil.getBean("productService");
    CategoryService categoryService = (CategoryService)SpringUtil.getBean("categoryService");
    ExtendedAttributeService extendedAttributeService =
        (ExtendedAttributeService)SpringUtil.getBean("extendedAttributeService");

    @ResponseBody
    @RequestMapping("JSONExtendedAttributeMap")
    public HashMap<String, ArrayList<String>> JSONExtendedAttributeMap(Integer buyProductID) {
        if (buyProductID == null)
            return new HashMap<String, ArrayList<String>>();
        else
            return extendedAttributeService.getExtendedAttributeMapByProductID(buyProductID);

    }
    
    @ResponseBody
    @RequestMapping("JSONCategoryLevel0")
    public List<Category> JSONLevel0Categories() {  
       
            return categoryService.listLevel0Categories();
    }
    
    @ResponseBody
    @RequestMapping("JSONCategoryLevel1")
    public List<Category> JSONLevel1Categories(String level0) {  
       
            return categoryService.listCategoriesByParentName(level0);
    }
    
    @ResponseBody
    @RequestMapping("JSONCategoryLevel2")
    public List<Category> JSONLevel2Categories(String level1) {    
       
        return categoryService.listCategoriesByParentName(level1);
    }
    
  
}
