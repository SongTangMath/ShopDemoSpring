package com.zkdx;

import java.util.List;

public interface ExtendedAttributeDAO {
    public List<ExtendedAttribute> listAttributesByProductName(String name);

    public List<ExtendedAttribute> listAttributesByProductID(int id);

    public int insertNewExtendedAttribute(int productID,  String attributeName,
        String attributeValue);

    public int deleteExtendedAttributeByID(int id);
}
