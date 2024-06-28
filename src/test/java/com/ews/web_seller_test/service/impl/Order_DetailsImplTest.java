package com.ews.web_seller_test.service.impl;

import com.ews.web_seller_test.dao.Order_DetailsDao;
import com.ews.web_seller_test.model.Order;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class Order_DetailsImplTest {

    @Mock
    private Order_DetailsDao orderDetailsDao;

    @InjectMocks
    private Order_DetailsImpl orderDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insertOrder_Details() {
        Order_Details orderDetails = new Order_Details();
        orderDetailsService.insertOrder_Details(orderDetails);
        verify(orderDetailsDao, times(1)).insertOrder_Details(orderDetails);
    }

    @Test
    void editOrder_Details() {
        Order_Details existingOrderDetails = new Order_Details();
        existingOrderDetails.setId(1);
        when(orderDetailsDao.getOrder_Details(anyInt())).thenReturn(existingOrderDetails);

        Order_Details newOrderDetails = new Order_Details();
        newOrderDetails.setId(1);
        newOrderDetails.setOrder(new Order());
        newOrderDetails.setProduct(new Product(1, "Product1"));
        newOrderDetails.setQuantity(2);
        newOrderDetails.setPrice(200.0f);
        newOrderDetails.setDiscount(10.0f);

        orderDetailsService.editOrder_Details(newOrderDetails);

        verify(orderDetailsDao, times(1)).getOrder_Details(anyInt());
        verify(orderDetailsDao, times(1)).editOrder_Details(any(Order_Details.class));
    }

    @Test
    void orderDetailsListByOrderId() {
        List<Order_Details> expectedOrderDetailsList = Arrays.asList(new Order_Details(), new Order_Details());
        when(orderDetailsDao.orderDetailsListByOrderId(anyInt())).thenReturn(expectedOrderDetailsList);

        List<Order_Details> retrievedOrderDetailsList = orderDetailsService.orderDetailsListByOrderId(1);

        assertEquals(expectedOrderDetailsList.size(), retrievedOrderDetailsList.size());
        assertEquals(expectedOrderDetailsList, retrievedOrderDetailsList);
        verify(orderDetailsDao, times(1)).orderDetailsListByOrderId(anyInt());
    }

    @Test
    void deleteOrder_Details() {
        int orderDetailsId = 1;
        orderDetailsService.deleteOrder_Details(orderDetailsId);
        verify(orderDetailsDao, times(1)).deleteOrder_Details(orderDetailsId);
    }

    @Test
    void getOrder_Details() {
        Order_Details expectedOrderDetails = new Order_Details();
        expectedOrderDetails.setId(1);
        when(orderDetailsDao.getOrder_Details(anyInt())).thenReturn(expectedOrderDetails);

        Order_Details retrievedOrderDetails = orderDetailsService.getOrder_Details(1);

        assertEquals(expectedOrderDetails, retrievedOrderDetails);
        verify(orderDetailsDao, times(1)).getOrder_Details(anyInt());
    }

    @Test
    void getAllOrder_Details() {
        List<Order_Details> expectedOrderDetailsList = Arrays.asList(new Order_Details(), new Order_Details());
        when(orderDetailsDao.getAllOrder_Details()).thenReturn(expectedOrderDetailsList);

        List<Order_Details> retrievedOrderDetailsList = orderDetailsService.getAllOrder_Details();

        assertEquals(expectedOrderDetailsList.size(), retrievedOrderDetailsList.size());
        assertEquals(expectedOrderDetailsList, retrievedOrderDetailsList);
        verify(orderDetailsDao, times(1)).getAllOrder_Details();
    }

    @Test
    void searchOrder_Details() {
        List<Order_Details> expectedOrderDetailsList = Arrays.asList(new Order_Details(), new Order_Details());
        when(orderDetailsDao.searchOrder_Details(anyString())).thenReturn(expectedOrderDetailsList);

        List<Order_Details> retrievedOrderDetailsList = orderDetailsService.searchOrder_Details("keyword");

        assertEquals(expectedOrderDetailsList.size(), retrievedOrderDetailsList.size());
        assertEquals(expectedOrderDetailsList, retrievedOrderDetailsList);
        verify(orderDetailsDao, times(1)).searchOrder_Details(anyString());
    }
}
