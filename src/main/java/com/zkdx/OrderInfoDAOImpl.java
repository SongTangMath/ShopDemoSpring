package com.zkdx;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class OrderInfoDAOImpl implements OrderInfoDAO {
    private JdbcTemplate jdbcTemplate = null;
    private RowMapper<OrderInfo> rowMapper = null;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public OrderInfoDAOImpl() {
        super();

        rowMapper = new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class);
    }

    @Override
    public List<OrderInfo> listOrdersByUsername(String name) {
        String sql = "select* from order_info where username=?";
        return jdbcTemplate.query(sql, rowMapper, name);
    }

    @Override
    public List<OrderInfo> listOrdersByTime(Timestamp beginDate, Timestamp endDate) {
        String sql = "select *from order_info where order_datetime between ? and ? ";
        return jdbcTemplate.query(sql, rowMapper, new Object[] {beginDate, endDate});

    }

    @Override
    public List<OrderInfo> listOrdersByIndice(int beginPageIndex, int recordPerPage) {
        String sql = "select *from order_info limit ? , ? ";
        return jdbcTemplate.query(sql, rowMapper, new Object[] {beginPageIndex * recordPerPage, recordPerPage});
    }

    @Override
    public int insertNewOrderInfo(String username, Timestamp orderDatetime, String productName, int productQuantity,
        int price, String extendedAttributeString, int buyingPrice, String productCategory) {
        String sql = "insert into order_info(username, order_datetime, product_name, product_quantity, "
            + "price,extended_attribute_string,buying_price,product_category)values(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[] {username, orderDatetime, productName, productQuantity, price,
            extendedAttributeString, buyingPrice, productCategory});
    }

    @Override
    public int getTotalOrderQuantity() {
        String sql = "select count(*) from order_info";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public static void main(String[] args) {
       
    }

}
