package com.zkdx.servlet;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkdx.database.UserService;
import com.zkdx.util.SpringUtil;

@Controller
public class UserRegister {
    private UserService userService = (UserService)SpringUtil.getBean("userService");

    @ResponseBody
    @RequestMapping(value = "/testUsername/{username}")
    public boolean usernameUsedAlready(@PathVariable("username") String username) {
        System.out.println(username);
        try {
            username = new String(username.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(username);
        return userService.getUserByUsername(username) != null;
    }

    @RequestMapping(value = "/UserRegister")
    public String register(String username, String password, String phone, String address) {
        userService.insertNewUser(username, password, phone, address);
        return "redirect:/index.jsp";
    }
}
