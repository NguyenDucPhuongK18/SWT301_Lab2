package com.ews.web_seller_test.service.impl;

import com.ews.web_seller_test.dao.OrderDao;
import com.ews.web_seller_test.dao.impl.OrderDaoImpl;
import com.ews.web_seller_test.model.Order;
import com.ews.web_seller_test.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    @Override
    public int insertOrder(Order order) {
//        orderDao.insertOrder(order);
        return orderDao.getOrderId(order);
    }


    @Override
    public void editOrder(Order newOrder) {
        Order oldOrder = orderDao.getOrder(newOrder.getId());
        oldOrder.setCreated_at(newOrder.getCreated_at());
        oldOrder.setBuyer(newOrder.getBuyer());
        orderDao.editOrder(oldOrder);
    }

    @Override
    public void deleteOrder(int id) {
        orderDao.deleteOrder(id);
    }

    @Override
    public Order getOrder(int id) {
        return orderDao.getOrder(id);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderDao.getAllOrder();
    }

    @Override
    public List<Order> searchOrder(String name) {
        return orderDao.searchOrder(name);
    }
}
