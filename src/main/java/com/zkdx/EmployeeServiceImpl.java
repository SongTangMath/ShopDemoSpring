package com.zkdx;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO;

    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    private boolean validateArg(String s) {

        return (s != null && !"".equals(s));
    }

    @Override
    public Employee getEmployeeById(int id) {
        if (id <= 0) {
            return null;
        } else {
            return employeeDAO.getEmployeeById(id);
        }
    }

    @Override
    public Employee getEmployeeByIdentityCard(String identityCard) {
        if (!validateArg(identityCard)) {
            return null;
        } else {
            return employeeDAO.getEmployeeByIdentityCard(identityCard);
        }
    }

    @Override
    public List<Employee> listAllEmployees() {
        // TODO Auto-generated method stub
        return employeeDAO.listAllEmployees();
    }

    @Override
    public int clearEmployees() {
        // TODO Auto-generated method stub
        return employeeDAO.clearEmployees();
    }

    @Override
    public int insertNewEmployee(String identityCard, String name, String password, String departmentName, String job,
        int salary) {
        if (!validateArg(identityCard) || !validateArg(name) || !validateArg(password) || !validateArg(departmentName)
            || !validateArg(job) || salary <= 0) {
            return 0;
        } else {
            return employeeDAO.insertNewEmployee(identityCard, name, password, departmentName, job, salary);
        }
    }

    @Override
    public int modifyEmployeeById(int id, String identityCard, String name, String password, String departmentName,
        String job, int salary) {
        if (!validateArg(identityCard) || !validateArg(name) || !validateArg(password) || !validateArg(departmentName)
            || !validateArg(job) || salary <= 0) {
            return 0;
        } else {
            return employeeDAO.modifyEmployeeById(id, identityCard, name, password, departmentName, job, salary);
        }
    }

    @Override
    public int deleteEmployeeById(int id) {
        // TODO Auto-generated method stub
        if (id <= 0) {
            return 0;
        } else {
            return employeeDAO.deleteEmployeeById(id);
        }
    }

    public static void main(String[] args) {

    }

}
