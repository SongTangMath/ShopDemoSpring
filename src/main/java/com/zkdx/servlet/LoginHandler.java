package com.zkdx.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.zkdx.database.*;

@Controller
@RequestMapping("/login")
@SessionAttributes(value = {"user", "admin", "employees"})
public class LoginHandler {
   

    @RequestMapping("/admin")
    public String adminLogin(HttpServletRequest request, 
        @RequestParam(value = "admin_name") String adminName, String password,
        Map<String, Object> map) {
        ApplicationContext ctx =
            (ClassPathXmlApplicationContext)request.getServletContext().getAttribute("applicationContext");

        EmployeeService employeeService = (EmployeeService)ctx.getBean("employeeService");
        if (adminName == null || password == null) {
            return "login_failed";
        }
        System.out.println("admin_name: " + adminName + " password: " + password);
        if (!adminName.equals("admin") || !password.equals("123")) {
            return "login_failed";

        } else {
            map.put("adminName", "admin");
            map.put("employee", new Employee());            
            map.put("employees", employeeService.listAllEmployees());
            return "manager";
        }
    }

    @RequestMapping("/user")
    public String userLogin(HttpServletRequest request,
        Map<String, Object> map, String username, String password) {
        ApplicationContext ctx =
            (ClassPathXmlApplicationContext)request.getServletContext().getAttribute("applicationContext");

        UserService userService = (UserService)ctx.getBean("userService");
        if (username == null || password == null) {
            return "login_failed";
        } else {

            User user = userService.getUserByUsername(username);
            if (user != null) {
                map.put("user", user);
                return "products";
            }
            System.out.println("username: " + username + " password: " + password);
        }
        return "login_failed";
    }

    @RequestMapping("/employee")
    public String userLogin(HttpServletRequest request, Map<String, Object> map,
        @RequestParam(value = "employee_id") Integer employeeID, String password) {
        ApplicationContext ctx =
            (ClassPathXmlApplicationContext)request.getServletContext().getAttribute("applicationContext");

        EmployeeService employeeService = (EmployeeService)ctx.getBean("employeeService");
        System.out.println("employeeID " + employeeID + "password " + password);
        if (employeeID == null || password == null) {
            return "login_failed";
        } else {

            Employee employee = employeeService.getEmployeeById(employeeID);
            if (employee != null) {
                System.out.println(employee);
                map.put("employee", employee);
                return "employee";
            }

        }
        return "login_failed";
    }
}
