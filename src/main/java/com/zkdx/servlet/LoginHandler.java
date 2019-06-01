package com.zkdx.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.zkdx.database.*;
import com.zkdx.util.*;

@Controller
@RequestMapping("/login")
@SessionAttributes(value = {"user", "admin", "employees", "products", "orderInfoMap", "employee"})
public class LoginHandler {

    private EmployeeService employeeService = (EmployeeService)SpringUtil.getBean("employeeService");
    private UserService userService = (UserService)SpringUtil.getBean("userService");
    private ProductService productService = (ProductService)SpringUtil.getBean("productService");
    private OrderInfoService orderInfoService = (OrderInfoService)SpringUtil.getBean("orderInfoService");

    @RequestMapping("/admin")
    public String adminLogin(HttpServletRequest request, @RequestParam(value = "admin_name") String adminName,
        String password, Map<String, Object> map) {

        if (adminName == null || password == null) {
            return "login_failed";
        }
        System.out.println("admin_name: " + adminName + " password: " + password);
        if (!"admin".equals(adminName) || !"123".equals(password)) {
            return "login_failed";

        } else {
            map.put("adminName", "admin");
            map.put("employee", new Employee());

            map.put("employees", employeeService.listAllEmployees());

            return "manager";
        }
    }

    @RequestMapping("/user")
    public String userLogin(Map<String, Object> map, String username, String password) {

        if (username == null || password == null) {
            return "login_failed";
        } else {

            User user = userService.getUserByUsername(username);
            if (user != null) {
                map.put("user", user);
                map.put("products", productService.listStatus0Products());
                map.put("orderInfoMap", orderInfoService.mapOrdersByUsername(username));
                return "products";
            }
            System.out.println("username: " + username + " password: " + password);
        }
        return "login_failed";
    }

    @RequestMapping("/employee")
    public String employeeLogin(Map<String, Object> map, @RequestParam(value = "employee_id") String identityCard,
        String password) {

        if (identityCard == null || password == null) {
            return "login_failed";
        } else {

            Employee employee = employeeService.getEmployeeByIdentityCard(identityCard);
            if (employee != null) {
                System.out.println(employee);
                map.put("employee", employee);
                if ("销售部".equals(employee.getDepartmentName())) {
                    List<ProductInfo> list = productService.listAllProducts();

                    map.put("products", list);
                    return "purchaser";
                } else if ("客服部".equals(employee.getDepartmentName())) {
                    int totalQuantity = orderInfoService.getTotalOrderQuantity();
                    int totalPages = -1;
                    if (totalQuantity % 10 == 0) {
                        totalPages = totalQuantity / 10;
                    } else {
                        totalPages = totalQuantity / 10 + 1;
                    }
                    map.put("totalPages", totalPages);
                    return "customer_service";
                } else {
                    return "employee";
                }
            }

        }
        return "login_failed";
    }
}
