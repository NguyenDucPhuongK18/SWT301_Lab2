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
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDaoImpl extends MyDAO implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class.getName());
    UserService userService = new UserServiceImpl();

    @Override
    public void insertOrder(Order order) {
        String xSql = "INSERT INTO Orders (user_id, price, phone_number, address, note, status, total_discount, total_quantity, total_price, created_at, updated_at) " +
                "VALUES (?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP())";
        try (PreparedStatement ps = con.prepareStatement(xSql)) {
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
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while inserting order", e);
        }
    }

    @Override
    public int getOrderId(Order order) {
        int orderId = 0;
        String xSql = "INSERT INTO Orders (user_id, price, phone_number, address, note, status, total_discount, total_quantity, total_price, created_at, updated_at) " +
                "VALUES (?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP())";
        try (PreparedStatement ps = con.prepareStatement(xSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
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
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        orderId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while getting order ID", e);
        }
        return orderId;
    }

    @Override
    public void editOrder(Order order) {
        String xSql = "UPDATE Orders SET user_id = ?, price = ?, phone_number = ?, address = ?, note = ?, status = ?, total_discount = ?, total_quantity = ?, total_price = ?, updated_at = CURRENT_TIMESTAMP() WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(xSql)) {
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
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while editing order", e);
        }
    }

    @Override
    public void deleteOrder(int id) {
        String xSql = "DELETE FROM Orders WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(xSql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while deleting order", e);
        }
    }

    @Override
    public Order getOrder(String name) {
        return null;
    }

    @Override
    public Order getOrder(int id) {
        String xSql = "SELECT Orders.id, Orders.created_at, User.email, User.username, User.id AS user_id "
                + "FROM Orders INNER JOIN `User` ON Orders.id = `User`.id WHERE Orders.id= ? ";
        try (PreparedStatement ps = con.prepareStatement(xSql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = userService.getUser(rs.getInt("user_id"));
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setCreated_at(rs.getDate("created_at"));
                    order.setBuyer(user);
                    return order;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while getting order", e);
        }
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> orderList = new ArrayList<>();
        String xSql = "SELECT Orders.id, Orders.created_at, User.email, User.username, User.id AS user_id "
                + "FROM Orders INNER JOIN `User` ON Orders.id = `User`.id";
        try (PreparedStatement ps = con.prepareStatement(xSql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = userService.getUser(rs.getInt("user_id"));
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCreated_at(rs.getDate("created_at"));
                order.setBuyer(user);
                orderList.add(order);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while getting all orders", e);
        }
        return orderList;
    }

    @Override
    public List<Order> searchOrder(String name) {
        List<Order> orderList = new ArrayList<>();
        String xSql = "SELECT Orders.id, Orders.created_at, User.email, User.username, User.id AS user_id "
                + "FROM Orders INNER JOIN `User` ON Orders.id = `User`.id WHERE User.full_name LIKE ?";
        try (PreparedStatement ps = con.prepareStatement(xSql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = userService.getUser(rs.getInt("user_id"));
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setCreated_at(rs.getDate("created_at"));
                    order.setBuyer(user);
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while searching orders", e);
        }
        return orderList;
    }
}
