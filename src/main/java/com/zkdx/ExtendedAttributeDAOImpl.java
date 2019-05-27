 package com.zkdx;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ExtendedAttributeDAOImpl implements ExtendedAttributeDAO {
    private JdbcTemplate jdbcTemplate = null;
    private RowMapper<ExtendedAttribute> rowMapper = null;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ExtendedAttributeDAOImpl() {
        super();
        rowMapper = new BeanPropertyRowMapper<ExtendedAttribute>(ExtendedAttribute.class);
    }
    @Override
    public List<ExtendedAttribute> listAttributesByProductName(String name) {
        String sql = "select *from extended_attribute where product_name=?";
        return jdbcTemplate.query(sql,rowMapper,name);
    }

    @Override
    public List<ExtendedAttribute> listAttributesByProductID(int id) {
        String sql = "select *from extended_attribute where product_id=?";
        return jdbcTemplate.query(sql,rowMapper,id);
    }

    @Override
    public int insertNewExtendedAttribute(int productID,  String attributeName,
        String attributeValue) {
        String sql =
            "insert into extended_attribute"
            + "(product_id,attribute_name, attribute_value) values(?,?,?,?)";
        return jdbcTemplate.update(sql,new Object[]{productID,attributeName,attributeValue});
    }

    @Override
    public int deleteExtendedAttributeByID(int id) {
        String sql =
            "delete from extended_attribute where attribute_id=?";
        return jdbcTemplate.update(sql,id);
    }

}
