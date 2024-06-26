package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.dao.MyDAO;
import com.ews.web_seller_test.dao.OrderDao;
import com.ews.web_seller_test.model.Order;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.UserService;
import com.ews.web_seller_test.service.impl.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends MyDAO implements OrderDao {
    UserService userService = new UserServiceImpl();

    @Override
    public void insertOrder(Order order) {
        xSql = "INSERT INTO Orders (user_id, price, phone_number, address, note, status, total_discount, total_quantity, total_price, created_at, updated_at) " +
                "VALUES (?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP())";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, order.getBuyer().getId());
            ps.setFloat(2, order.getPrice());
            ps.setString(3, order.getPhone());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getNote());
            ps.setInt(6, order.getStatus());
            ps.setFloat(7, order.getTotal_discount());
            ps.setInt(8, order.getTotal_quantity());
            ps.setFloat(9, order.getTotal_price());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getOrderId(Order order) {
        int orderId = 0;
        String xSql = "INSERT INTO Orders (user_id, price, phone_number, address, note, status, total_discount, total_quantity, total_price, created_at, updated_at) " +
                "VALUES (?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP())";
        try {
            PreparedStatement ps = con.prepareStatement(xSql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getBuyer().getId());
            ps.setFloat(2, order.getPrice());
            ps.setString(3, order.getPhone());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getNote());
            ps.setInt(6, order.getStatus());
            ps.setFloat(7, order.getTotal_discount());
            ps.setInt(8, order.getTotal_quantity());
            ps.setFloat(9, order.getTotal_price());
            int rowAffected = ps.executeUpdate();
            if (rowAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
                con.commit();
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }


    @Override
    public void editOrder(Order order) {
        xSql = "UPDATE Orders SET user_id = ?, price = ?, phone_number = ?, address = ?, note = ?, status = ?, total_discount = ?, total_quantity = ?, total_price = ?,updated_at = CURRENT_TIMESTAMP(), id = ? WHERE id = ? ";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, order.getBuyer().getId());
            ps.setFloat(2, order.getPrice());
            ps.setString(3, order.getPhone());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getNote());
            ps.setInt(6, order.getStatus());
            ps.setFloat(7, order.getTotal_discount());
            ps.setInt(8, order.getTotal_quantity());
            ps.setFloat(9, order.getTotal_price());

            ps.setInt(10, order.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        xSql = "DELETE FROM Orders WHERE id = ?";
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
    public Order getOrder(String name) {
        return null;
    }

    @Override
    public Order getOrder(int id) {
        xSql = "SELECT Orders.id, Orders.created_at, User.email, User.username, User.id AS user_id "
                + "FROM Orders INNER JOIN `User` " + "ON Orders.id = `User`.id WHERE Orders.id= ? ";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = userService.getUser(rs.getInt("user_id"));

                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCreated_at(rs.getDate("created_at"));
                order.setBuyer(user);
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> orderList = new ArrayList<Order>();
        xSql = "SELECT Orders.id, Orders.created_at, User.email, User.username, User.id AS user_id "
                + "FROM Orders INNER JOIN `User` " + "ON Orders.id = `User`.id";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = userService.getUser(rs.getInt("user_id"));

                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCreated_at(rs.getDate("created_at"));
                order.setBuyer(user);
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<Order> searchOrder(String name) {
        List<Order> orderList = new ArrayList<Order>();
        xSql = "SELECT Orders.id, Orders.created_at, User.email, User.username, User.id AS user_id "
                + "FROM Orders INNER JOIN `User` " + "ON Orders.id = `User`.id WHERE User.full_name LIKE ? ";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = userService.getUser(rs.getInt("user_id"));
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCreated_at(rs.getDate("created_at"));
                order.setBuyer(user);
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}


