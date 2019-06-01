package com.zkdx.database;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryDAO categoryDAO;

    @Override
    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    @Override
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    private boolean validateArg(String s) {

        return (s != null && !"".equals(s));
    }

    @Override
    public Category getCategoryById(int id) {
        if (id <= 0) {
            return null;
        } else {
            return categoryDAO.getCategoryById(id);
        }
    }

    @Override
    public Category getCategoryByName(String name) {
        if (!validateArg(name)) {
            return null;
        } else {
            return categoryDAO.getCategoryByName(name);
        }
    }

    @Override
    public int deleteCategoryAndItsSubCategoriesByName(String name) {
        if (!validateArg(name)) {
            return 0;
        } else {
            return categoryDAO.deleteCategoryAndItsSubCategoriesByName(name);
        }
    }

    @Override
    public int insertNewCategory(String name, String parentName, int categoryStatus, int categoryLevel) {
        if (!validateArg(name) || parentName == null) {
            return 0;
        } else if (categoryDAO.getCategoryByName(name) == null) {
            return categoryDAO.insertNewCategory(name, parentName, categoryStatus, categoryLevel);
        } else {
            return 0;
        }
    }

    @Override
    public int setIsEnd(String name, int isEnd) {
        if (!validateArg(name)) {
            return 0;
        } else if (isEnd != 0 && isEnd != 1) {
            return 0;
        } else {
            return categoryDAO.setIsEnd(name, isEnd);
        }
    }

    @Override
    public int setStatus(String name, int status) {
        if (!validateArg(name)) {
            return 0;
        } else {
            return categoryDAO.setStatus(name, status);
        }
    }

    @Override
    public List<Category> listCategoriesByParentName(String parentName) {
        if (!validateArg(parentName)) {
            return new ArrayList<Category>();
        } else {
            return categoryDAO.listCategoriesByParentName(parentName);
        }
    }

    @Override
    public List<Category> listLevel0Categories() {
        // TODO Auto-generated method stub
        return categoryDAO.listLevel0Categories();
    }

}
