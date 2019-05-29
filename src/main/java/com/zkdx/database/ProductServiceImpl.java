package com.zkdx.database;

import java.util.*;

import org.springframework.stereotype.Service;
@Service
public class ProductServiceImpl implements ProductService {
    private ProductInfoDAO productInfoDAO;

    private boolean validateArg(String s) {

        return (s != null && !"".equals(s));
    }

    @Override
    public ProductInfoDAO getProductInfoDAO() {

        return productInfoDAO;
    }

    @Override
    public void setProductInfoDAO(ProductInfoDAO productInfoDAO) {
        this.productInfoDAO = productInfoDAO;

    }

    @Override
    public ProductInfo getProductInfoById(int id) {
        if (id <= 0) {
            return null;
        } else {
            return productInfoDAO.getProductInfoById(id);
        }
    }

    @Override
    public ProductInfo getProductInfoByProductName(String name) {
        if (!validateArg(name)) {
            return null;
        } else {
            return productInfoDAO.getProductInfoByProductName(name);
        }
    }

    @Override
    public int modifyProductByProductID(int id, String productName, int productStatus, int price, int inventoryQuantity,
        String pictureLink, String productPlan, int buyingPrice, String productCategory) {
        boolean isArgsValid = (id >= 1 && validateArg(productName) && price >= 1 && inventoryQuantity >= 0
            && pictureLink != null && productPlan != null && buyingPrice >= 0 && validateArg(productCategory));
        if (!isArgsValid) {
            return 0;
        }
        return productInfoDAO.modifyProductByProductID(id, productName, productStatus, price, inventoryQuantity,
            pictureLink, productPlan, buyingPrice, productCategory);
    }

    @Override
    public int modifyProductPictureLinkByProductID(int id, String pictureLink) {
        if (id <= 0 || pictureLink == null) {
            return 0;
        } else {
            return productInfoDAO.modifyProductPictureLinkByProductID(id, pictureLink);
        }
    }

    @Override
    public int modifyProductPlanByProductName(String productName, String productPlan) {
        if (!validateArg(productName) || productPlan == null) {
            return 0;
        } else {
            return productInfoDAO.modifyProductPlanByProductName(productName, productPlan);
        }
    }

    @Override
    public int modifyProductIntentoryQuantityByProductId(int id, int number) {
        if (id <= 0 || number <= 0) {
            return 0;
        } else {
            return productInfoDAO.modifyProductIntentoryQuantityByProductId(id, number);
        }
    }

    @Override
    public int clearProducts() {

        return productInfoDAO.clearProducts();
    }

    @Override
    public int insertNewProduct(String productName, int productStatus, int price, int inventoryQuantity,
        String pictureLink, String productPlan, int buyingPrice, String productCategory) {
        boolean isArgsValid = (validateArg(productName) && price >= 1 && inventoryQuantity >= 0 && pictureLink != null
            && productPlan != null && buyingPrice >= 0 && validateArg(productCategory));
        if (!isArgsValid) {
            return 0;
        }
        ProductInfo info = productInfoDAO.getProductInfoByProductName(productName);
        if (info != null) {
            return 0;
        }

        else {
            return productInfoDAO.insertNewProduct(productName, productStatus, price, inventoryQuantity, pictureLink,
                productPlan, buyingPrice, productCategory);
        }
    }

    @Override
    public int deleteProductByProductID(int id) {
        if (id <= 0) {
            return 0;
        } else {
            return productInfoDAO.deleteProductByProductID(id);
        }
    }

    @Override
    public List<ProductInfo> listAllProducts() {
        // TODO Auto-generated method stub
        return productInfoDAO.listAllProducts();
    }

    @Override
    public List<ProductInfo> listProductsByProductCategory(String pattern) {
        if (!validateArg(pattern)) {
            return new ArrayList<ProductInfo>();
        } else {
            return productInfoDAO.listProductsByProductCategory(pattern);
        }
    }

}
