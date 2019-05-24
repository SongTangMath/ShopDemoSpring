 package com.zkdx;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeDAOImpl implements EmployeeDAO {
    private JdbcTemplate jdbcTemplate = null;
    private RowMapper<Employee> rowMapper = null;
    
    
    public EmployeeDAOImpl() {
        super();
        
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(JdbcUtil.getDataSource());
        rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
    }

    @Override
    public Employee getEmployeeById(int id) {
        String sql="select* from employee where id=?";
        Employee employee=null;
        try{
            employee=jdbcTemplate.queryForObject(sql, rowMapper, id);
        }
        catch(EmptyResultDataAccessException e) {
            // e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee getEmployeeByIdentityCard(String identityCard) {
        String sql="select* from employee where identity_card=?";
        Employee employee=null;
        try{
            employee=jdbcTemplate.queryForObject(sql, rowMapper, identityCard);
        }
        catch(EmptyResultDataAccessException e) {
            // e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> listAllEmployees() {
        String sql="select* from employee ";        
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public int clearEmployees() {
        String sql="delete from employee";
        return jdbcTemplate.update(sql);
    }

    @Override
    public int insertNewEmployee(String identityCard, String name, String password, String departmentName, String job,
        int salary) {
        String sql="insert into from employee (identity_card,name,password,department_name,job,salary)values(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[] {identityCard, name, password, departmentName, job});
       
    }

    @Override
    public int modifyEmployeeById(int id, String identityCard, String name, String password, String departmentName,
        String job, int salary) {
        String sql="update from employee (set identity_card=?,name=?,password=?,department_name=?,job=?,salary=?";
        return jdbcTemplate.update(sql, new Object[] {identityCard, name, password, departmentName, job,id});
    }

    @Override
    public int deleteEmployeeById(int id) {
        String sql="delete from employee where id=?";
        return jdbcTemplate.update(sql,id);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
