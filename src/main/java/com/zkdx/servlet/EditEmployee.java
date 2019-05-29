 package com.zkdx.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.zkdx.database.Employee;
import com.zkdx.database.EmployeeService;

@Controller
@SessionAttributes(value = {"employees"})
 public class EditEmployee {
   
    
    @RequestMapping(value="/emp",method=RequestMethod.POST)
    public String addNewEmployee(HttpServletRequest request, @RequestParam("identityCard")String identityCard,
        @RequestParam("password")String password, Map<String, Object> map,@RequestParam(value="id" ,required=false) Integer id,
        @RequestParam("name")String name, @RequestParam("departmentName")String departmentName,
        @RequestParam("job")String job, @RequestParam("salary")Integer salary) {
        ApplicationContext ctx =
            (ClassPathXmlApplicationContext)request.getServletContext().getAttribute("applicationContext");

        EmployeeService employeeService = (EmployeeService)ctx.getBean("employeeService");
        map.put("employee", new Employee());
        if(identityCard==null||password==null||name==null||departmentName==null||job==null||salary==null) {
           
            return "manager";
        }
           
        else {
            if(id==null) {
                System.out.println("insertNewEmployee");
                employeeService.insertNewEmployee(identityCard, name, password, departmentName, job, salary);
            }
            else {
                System.out.println("modifyEmployeeById"+id);
                System.out.println(salary);
                System.out.println(employeeService.modifyEmployeeById(id, identityCard, name, password, departmentName, job, salary));
            }
            map.put("employees", employeeService.listAllEmployees());
            return "manager";
        }
        
    }
    
    @RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
    public String deleteEmployee(HttpServletRequest request, @PathVariable("id")Integer id,
         Map<String, Object> map) {
        ApplicationContext ctx =
            (ClassPathXmlApplicationContext)request.getServletContext().getAttribute("applicationContext");

        EmployeeService employeeService = (EmployeeService)ctx.getBean("employeeService");
        map.put("employee", new Employee());
        System.out.println("delete");
        if(id==null) {
            System.out.println("argumentsIncorrect");
            return "manager";
        }
           
        else {
            System.out.println("deleteEmployeeById"+ id);
            employeeService.deleteEmployeeById(id);
            map.put("employees", employeeService.listAllEmployees());
            return "manager";
        }
        
    }
    
     @RequestMapping(value="/emp/{id}",method=RequestMethod.PUT)
    public String modifyEmployee(HttpServletRequest request, @PathVariable("id")Integer id,
        Map<String, Object> map) {
       ApplicationContext ctx =
           (ClassPathXmlApplicationContext)request.getServletContext().getAttribute("applicationContext");

       EmployeeService employeeService = (EmployeeService)ctx.getBean("employeeService");
       if(id==null) return "manager";;
       
       Employee employee=employeeService.getEmployeeById(id);
       map.put("employee", employee);
       return "modify_employee";
          
       
       
   }
}
