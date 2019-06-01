package com.zkdx.database;

import java.util.List;

/**
 * 
 * @author ts
 * @date 2019/06/01
 */
public interface EmployeeDAO {
    public Employee getEmployeeById(int id);

    public Employee getEmployeeByIdentityCard(String identityCard);

    public List<Employee> listAllEmployees();

    public int clearEmployees();

    public int insertNewEmployee(String identityCard, String name, String password, String departmentName, String job,
        int salary);

    public int modifyEmployeeById(int id, String identityCard, String name, String password, String departmentName,
        String job, int salary);

    public int deleteEmployeeById(int id);

}
