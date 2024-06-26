package com.ews.web_seller_test.service.impl;

import com.ews.web_seller_test.dao.OrderDao;
import com.ews.web_seller_test.model.Order;
import com.ews.web_seller_test.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Mock
    private OrderDao orderDao;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insertOrder() {
        Order order = new Order();
        order.setBuyer(new User());
        when(orderDao.getOrderId(any())).thenReturn(1);

        int insertedOrderId = orderService.insertOrder(order);

        assertEquals(1, insertedOrderId);
        verify(orderDao, times(1)).getOrderId(any());
    }

    @Test
    void editOrder() {
        Order existingOrder = new Order();
        existingOrder.setId(1);
        existingOrder.setBuyer(new User());

        when(orderDao.getOrder(anyInt())).thenReturn(existingOrder);

        Order newOrder = new Order();
        newOrder.setId(1);
        newOrder.setBuyer(new User());
        newOrder.setPhone("1234567890");
        newOrder.setAddress("456 Another St");

        orderService.editOrder(newOrder);

        verify(orderDao, times(1)).getOrder(anyInt());
        verify(orderDao, times(1)).editOrder(any());
    }

    @Test
    void deleteOrder() {
        int orderId = 1;

        orderService.deleteOrder(orderId);

        verify(orderDao, times(1)).deleteOrder(orderId);
    }

    @Test
    void getOrder() {
        Order expectedOrder = new Order();
        expectedOrder.setId(1);

        when(orderDao.getOrder(anyInt())).thenReturn(expectedOrder);

        Order retrievedOrder = orderService.getOrder(1);

        assertEquals(expectedOrder, retrievedOrder);
        verify(orderDao, times(1)).getOrder(1);
    }

    @Test
    void getAllOrder() {
        List<Order> expectedOrders = Arrays.asList(new Order(), new Order());

        when(orderDao.getAllOrder()).thenReturn(expectedOrders);

        List<Order> retrievedOrders = orderService.getAllOrder();

        assertEquals(expectedOrders.size(), retrievedOrders.size());
        assertEquals(expectedOrders, retrievedOrders);
        verify(orderDao, times(1)).getAllOrder();
    }

    @Test
    void searchOrder() {
        List<Order> expectedOrders = Arrays.asList(new Order(), new Order());

        when(orderDao.searchOrder(anyString())).thenReturn(expectedOrders);

        List<Order> retrievedOrders = orderService.searchOrder("John");

        assertEquals(expectedOrders.size(), retrievedOrders.size());
        assertEquals(expectedOrders, retrievedOrders);
        verify(orderDao, times(1)).searchOrder("John");
    }
}
