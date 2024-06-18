package com.ews.web_seller_test.service.impl;

import com.ews.web_seller_test.dao.Order_DetailsDao;
import com.ews.web_seller_test.dao.impl.Order_DetailsDaoImpl;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.service.Order_DetailsService;

import java.util.List;

public class Order_DetailsImpl implements Order_DetailsService {

    Order_DetailsDao orderDetailsDao = new Order_DetailsDaoImpl();
    @Override
    public void insertOrder_Details(Order_Details orderDetails) {
        orderDetailsDao.insertOrder_Details(orderDetails);
    }

    @Override
    public void editOrder_Details(Order_Details newOrderDetails) {
        Order_Details oldOrderDetails = orderDetailsDao.getOrder_Details(newOrderDetails.getId());
        oldOrderDetails.setOrder(newOrderDetails.getOrder());
        oldOrderDetails.setProduct(newOrderDetails.getProduct());
        oldOrderDetails.setQuantity(newOrderDetails.getQuantity());
        oldOrderDetails.setPrice(newOrderDetails.getProduct().getPrice());
        oldOrderDetails.setDiscount(newOrderDetails.getProduct().getDiscount());
        oldOrderDetails.setId(newOrderDetails.getId());

        orderDetailsDao.editOrder_Details(oldOrderDetails);
    }

    @Override
    public List<Order_Details> orderDetailsListByOrderId(int orderId) {
        return orderDetailsDao.orderDetailsListByOrderId(orderId);
    }

    @Override
    public void deleteOrder_Details(int id) {
        orderDetailsDao.deleteOrder_Details(id);
    }

    @Override
    public Order_Details getOrder_Details(int id) {
        return orderDetailsDao.getOrder_Details(id);
    }

    @Override
    public List<Order_Details> getAllOrder_Details() {
        return orderDetailsDao.getAllOrder_Details();
    }

    @Override
    public List<Order_Details> searchOrder_Details(String keyword) {
        return orderDetailsDao.searchOrder_Details(keyword);
    }
}
