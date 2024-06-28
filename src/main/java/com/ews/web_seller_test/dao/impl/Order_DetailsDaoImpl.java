package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.dao.MyDAO;
import com.ews.web_seller_test.dao.Order_DetailsDao;
import com.ews.web_seller_test.dao.UserDao;
import com.ews.web_seller_test.model.Order;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.OrderService;
import com.ews.web_seller_test.service.ProductService;
import com.ews.web_seller_test.service.impl.OrderServiceImpl;
import com.ews.web_seller_test.service.impl.ProductServiceImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Order_DetailsDaoImpl extends MyDAO implements Order_DetailsDao {

    OrderService orderService = new OrderServiceImpl();
    ProductService productService = new ProductServiceImpl();
    UserDao userDao = new UserDaoImpl();

    @Override
    public void insertOrder_Details(Order_Details order_details) {
        xSql = "INSERT INTO Order_Details (order_id, product_id, price, quantity, discount) VALUES (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, order_details.getOrder().getId());
            ps.setInt(2, order_details.getProduct().getId());
            ps.setFloat(3, order_details.getPrice());
            ps.setInt(4, order_details.getQuantity());
            ps.setFloat(5, order_details.getDiscount());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editOrder_Details(Order_Details order_details) {
        xSql = "UPDATE Order_Details SET product_id = ?, price = ?, quantity = ?, discount = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, order_details.getProduct().getId());
            ps.setFloat(2, order_details.getPrice());
            ps.setInt(3, order_details.getQuantity());
            ps.setFloat(4, order_details.getDiscount());
            ps.setInt(5, order_details.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order_Details> orderDetailsListByOrderId(int orderId) {
        List<Order_Details> order_detailsList = new ArrayList<Order_Details>();
        xSql = "SELECT Order_Details.id AS id_od, " +
                "Order_Details.quantity, " +
                "Order_Details.price AS price_od, " +
                "Order_Details.discount AS discount, " +
                "Orders.id as id_o, " +
                "Orders.user_id as u_id, " +
                "Product.name as p_name, " +
                "Product.id AS p_id " +
                "FROM Order_Details " +
                "INNER JOIN Orders ON Order_Details.order_id = Orders.id " +
                "INNER JOIN Product ON Order_Details.product_id = Product.id WHERE Orders.id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = userDao.getUser(rs.getInt("u_id"));
                Order order = new Order();
                order.setId(orderId);
                order.setBuyer(user);
//                order.setCreated_at(rs.getDate("created_at"));
                Product product = new Product(1, "Product1");
                product.setName(rs.getString("p_name"));
                product.setPrice(rs.getFloat("price_od"));
                Order_Details order_details = new Order_Details();
                order_details.setOrder(order);
                order_details.setId(rs.getInt("id_od"));
                order_details.setProduct(product);
                order_details.setDiscount(rs.getFloat("discount"));
                order_details.setQuantity(rs.getInt("quantity"));
                order_details.setPrice(rs.getInt("price_od"));
                order_detailsList.add(order_details);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order_detailsList;
    }

    @Override
    public void deleteOrder_Details(int id) {
        xSql = "DELETE FROM Order_Details WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order_Details getOrder_Details(String name) {
        return null;
    }

    @Override
    public Order_Details getOrder_Details(int id) {
        xSql = "SELECT " +
                "Order_Details.id, " +
                "Order_Details.quantity, " +
                "Order_Details.price AS price_od, " +
                "Orders.id as order_id, " +
                "Orders.user_id, " +
                "Orders.created_at, " +
                "Product.name, " +
                "Product.price AS price_p " +
                "FROM Order_Details " +
                "INNER JOIN Orders " +
                "ON Order_Details.order_id = Orders.id " +
                "INNER JOIN Product " +
                "ON Order_Details.product_id = Product.id " +
                "WHERE Order_Details.id = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = userDao.getUser(rs.getInt("user_id"));
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setBuyer(user);
                order.setCreated_at(rs.getDate("created_at"));

                Product product = new Product(1, "Product1");
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price_p"));

                Order_Details order_details = new Order_Details();

                order_details.setOrder(order);

                order_details.setProduct(product);
                order_details.setQuantity(rs.getInt("quantity"));
                order_details.setPrice(rs.getInt("price_od"));
                return order_details;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order_Details> getAllOrder_Details() {
        List<Order_Details> order_detailsList = new ArrayList<Order_Details>();
        xSql = "SELECT Order_Details.id AS id_od, " +
                "Order_Details.quantity, " +
                "Order_Details.price AS price_od, " +
                "Order_Details.discount AS discount, " +
                "Orders.id as id_o, " +
                "Orders.user_id as u_id, " +
                "Product.name as p_name, " +
                "Product.id AS p_id, " +
                "Orders.created_at " +
                "FROM Order_Details " +
                "INNER JOIN Orders ON Order_Details.order_id = Orders.id " +
                "INNER JOIN Product ON Order_Details.product_id = Product.id";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = userDao.getUser(rs.getInt("u_id"));
                Order order = new Order();
                order.setBuyer(user);
                order.setCreated_at(rs.getDate("created_at"));
                Product product = new Product(1, "Product1");
                product.setName(rs.getString("p_name"));
                product.setPrice(rs.getFloat("price_od"));
                Order_Details order_details = new Order_Details();
                order_details.setOrder(order);
                order_details.setId(rs.getInt("id_od"));
                order_details.setProduct(product);
                order_details.setDiscount(rs.getFloat("discount"));
                order_details.setQuantity(rs.getInt("quantity"));
                order_details.setPrice(rs.getInt("price_od"));
                order_detailsList.add(order_details);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order_detailsList;
    }

    @Override
    public List<Order_Details> searchOrder_Details(String name) {
        return null;
    }
}
