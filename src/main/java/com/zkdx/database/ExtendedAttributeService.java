package com.zkdx.database;

import java.util.List;

public interface ExtendedAttributeService {
    public ExtendedAttributeDAO getExtendedAttributeDAO();

    public void setExtendedAttributeDAO(ExtendedAttributeDAO extendedAttributeDAO);

    public List<ExtendedAttribute> listAttributesByProductName(String name);

    public List<ExtendedAttribute> listAttributesByProductID(int id);

    public int insertNewExtendedAttribute(int productID, String attributeName, String attributeValue);

    public int deleteExtendedAttributeByID(int id);
}