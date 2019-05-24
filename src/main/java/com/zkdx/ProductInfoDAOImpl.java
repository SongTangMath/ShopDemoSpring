package com.zkdx;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;

public class ProductInfoDAOImpl implements ProductInfoDAO {
    private JdbcTemplate jdbcTemplate = null;
    private RowMapper<ProductInfo> rowMapper = null;

    public ProductInfoDAOImpl() {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(JdbcUtil.getDataSource());
        rowMapper = new BeanPropertyRowMapper<ProductInfo>(ProductInfo.class);
    }

    @Override
    public ProductInfo getProductInfoById(int id) {
        String sql = "SELECT* from product_info where product_info.id=?";

        ProductInfo info = null;
        
        try{
            info=jdbcTemplate.queryForObject(sql, rowMapper, id);
        }
        catch(EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public ProductInfo getProductInfoByProductName(String name) {
        String sql = "SELECT* from product_info where product_info.product_name=?";
        
        ProductInfo info = null;
        try{
            info=jdbcTemplate.queryForObject(sql, rowMapper, name);
        }
        catch(EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public int modifyProductByProductID(int id, String productName, int productStatus, int price, int inventoryQuantity,
        String pictureLink, String productPlan, int buyingPrice, String productCategory) {
        String sql = "update product_info set product_name=?,product_status=?,price=?,inventory_quantity=?,"
            + "picture_link=?,product_plan=?,buying_price=?,product_category=? where id=?)";
        return jdbcTemplate.update(sql, new Object[] {productName, productStatus, price, inventoryQuantity, pictureLink,
            productPlan, buyingPrice, productCategory, id});
    }

    @Override
    public int modifyProductPictureLinkByProductID(int id, String pictureLink) {
        String sql = "update product_info set picture_link=? where id=?";
        return jdbcTemplate.update(sql, new Object[] {pictureLink, id});
    }

    @Override
    public int modifyProductPlanByProductName(String productName, String productPlan) {
        String sql = "update product_info set product_plan=? where product_name=?";
        return jdbcTemplate.update(sql, new Object[] {productPlan, productName});
    }

    @Override
    public int modifyProductIntentoryQuantityByProductId(int id, int number) {
        String sql = "update product_info set inventory_quantity=? where id=?";
        return jdbcTemplate.update(sql, new Object[] {number, id});
    }

    @Override
    public int clearProducts() {
        String sql = "delete from product_info ";
        return jdbcTemplate.update(sql);

    }

    @Override
    public int insertNewProduct(String productName, int productStatus, int price, int inventoryQuantity,
        String pictureLink, String productPlan, int buyingPrice, String productCategory) {
        String sql = "insert into product_info (product_name,product_status,price,inventory_quantity,"
            + "picture_link,product_plan,buying_price,product_category) values(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[] {productName, productStatus, price, inventoryQuantity, pictureLink,
            productPlan, buyingPrice, productCategory});
    }

    @Override
    public int deleteProductByProductID(int id) {
        String sql = "delete from product_info where id=?";
        return jdbcTemplate.update(sql, new Object[] {id});

    }

    @Override
    public List<ProductInfo> listAllProducts() {
        String sql = "select* from product_info ";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<ProductInfo> listProductsByProductCategory(String pattern) {
        String sql = "select* from product_info where product_category like ? or product_name like ?";
        return jdbcTemplate.query(sql, rowMapper, new Object[] {"%" + pattern + "%", "%" + pattern + "%"});
    }

    public static void main(String[] args) {

        ProductInfoDAO obj = new ProductInfoDAOImpl();
        obj.modifyProductIntentoryQuantityByProductId(10, 100);
       
        //System.out.println(obj.getProductInfoByProductName("菊花"));
        
    }

}
