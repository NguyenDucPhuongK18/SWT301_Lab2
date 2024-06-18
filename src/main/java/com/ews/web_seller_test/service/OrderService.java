package com.ews.web_seller_test.service;

import com.ews.web_seller_test.model.Order;

import java.util.List;

public interface OrderService {
    int insertOrder(Order order);

    void editOrder(Order newOrder);

    void deleteOrder(int id);

    Order getOrder(int id);

    List<Order> getAllOrder();

    List<Order> searchOrder(String keyword);
}
