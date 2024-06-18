package com.ews.web_seller_test.dao;

import com.ews.web_seller_test.model.Order;
import com.ews.web_seller_test.model.Order_Details;

import java.util.List;

public interface OrderDao {
    void insertOrder(Order cart);

    void editOrder(Order cart);

    void deleteOrder(int id);

    Order getOrder(String name);

    Order getOrder(int id);

    List<Order> getAllOrder();

    List<Order> searchOrder(String name);
    int getOrderId(Order order);
}
