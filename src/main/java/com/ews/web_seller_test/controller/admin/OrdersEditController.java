package com.ews.web_seller_test.controller.admin;


import java.io.IOException;
import java.util.List;

import com.ews.web_seller_test.model.*;
import com.ews.web_seller_test.service.*;
import com.ews.web_seller_test.service.impl.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "OrdersEditController", urlPatterns = {"/admin/order/edit"})
public class OrdersEditController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService cateService = new CategoryServiceImpl();
    UserService userService = new UserServiceImpl();
    Order_DetailsService orderDetailsService = new Order_DetailsImpl();
    OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj=session.getAttribute("account");
        User user=(User) obj;
        req.setAttribute("username", user.getUsername());

        List<Product> productList = productService.getAllProduct();
        List<User> userList = userService.getAllUser();
        List<Category> categoryList = cateService.getAllCategory();

        List<Order_Details> orderDetailsList = orderDetailsService.getAllOrder_Details();

        req.setAttribute("orderDetailsList", orderDetailsList);
        req.setAttribute("userList", userList);
        req.setAttribute("productList", productList);

        String orderD_id = req.getParameter("id");
        req.setAttribute("orderD_id",  orderD_id);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/view/edit-order.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Object obj=session.getAttribute("account");
        User user=(User) obj;
        if(user.getRole().getId() == 2) {
            request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
        }
        request.setAttribute("username", user.getUsername());

        String p_id = request.getParameter("product");
        int xp_id = Integer.parseInt(p_id);

        String quantity = request.getParameter("quantity");
        int x_quantity = Integer.parseInt(quantity);


        String orderD_id = request.getParameter("orderD_id");
        int x_orderD_id = Integer.parseInt(orderD_id);

        Order_Details order_details = orderDetailsService.getOrder_Details(x_orderD_id);


        order_details.setId(x_orderD_id);
        order_details.setQuantity(x_quantity);
        order_details.setProduct(productService.getProduct(xp_id));
        orderDetailsService.editOrder_Details(order_details);

//        int id_order = order_details.getOrder().getId();
//        int price_Order;
//        Order order = orderService.getOrder(id_order);

//        List<Order_Details> orderDetailsListByOrderId = null;
//        orderDetailsListByOrderId = orderDetailsService.orderDetailsListByOrderId(id_order);

        response.sendRedirect(request.getContextPath() + "/admin/order/list");
    }
}

