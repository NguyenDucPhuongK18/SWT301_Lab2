package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.dao.CategoryDao;
import com.ews.web_seller_test.dao.MyDAO;
import com.ews.web_seller_test.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends MyDAO implements CategoryDao {
    @Override
    public boolean insertCategory(Category category) {
        int rowAffected = 0;
        xSql = "INSERT INTO Category(name) VALUES (?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, category.getName());
            rowAffected = ps.executeUpdate();
            ps.close();
            return rowAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void editCategory(Category category) {
        xSql = "UPDATE Category SET name = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(int id) {
        xSql = "DELETE FROM Category WHERE id = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category getCategory(int id) {
        xSql = "SELECT * FROM Category WHERE id = ? ";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category getCategory(String name) {
        xSql = "SELECT * FROM Category WHERE name = ? ";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, name);
            rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category();

                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                return category;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<Category>();
        xSql = "SELECT * FROM Category";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Category> searchCategory(String keyword) {
        List<Category> categories = new ArrayList<Category>();
        xSql = "SELECT * FROM Category WHERE name LIKE ? ";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category();

                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
