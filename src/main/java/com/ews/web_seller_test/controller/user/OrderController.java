package com.ews.web_seller_test.controller.user;


import java.io.IOException;
import java.util.Map;

import com.ews.web_seller_test.model.Order;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.OrderService;
import com.ews.web_seller_test.service.Order_DetailsService;
import com.ews.web_seller_test.service.UserService;
import com.ews.web_seller_test.service.impl.OrderServiceImpl;
import com.ews.web_seller_test.service.impl.Order_DetailsImpl;
import com.ews.web_seller_test.service.impl.UserServiceImpl;
import com.ews.web_seller_test.tools.SendMail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "OrderController", urlPatterns = {"/checkout"})
public class OrderController extends HttpServlet {
    UserService userService = new UserServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    Order_DetailsService orderDetailsService = new Order_DetailsImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int amount = 0;
        HttpSession httpSession = request.getSession();
        Map<Integer, Order_Details> mapList = null;
        if(httpSession.getAttribute("cart") != null) {
            mapList  = (Map<Integer, Order_Details>) httpSession.getAttribute("cart");
            amount = mapList.size();
            request.setAttribute("amount", amount);
        }

        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("user", user);
        }

        request.getRequestDispatcher("/views/user/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Object obj = null;
        HttpSession session = request.getSession();
        obj = session.getAttribute("account");

        int amount = 0;
        HttpSession httpSession = request.getSession();
        Map<Integer, Order_Details> mapList = null;
        if(httpSession.getAttribute("cart") != null) {
            mapList  = (Map<Integer, Order_Details>) httpSession.getAttribute("cart");
            amount = mapList.size();
            request.setAttribute("amount", amount);
        }

        //Get session username
        if(session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("user", user);
        }

        Object objOrder = session.getAttribute("cart");
        User buyer = (User) obj;

        Order order = new Order();
        float price, total_price, total_discount;
        String phone, address, note;
        int status, total_quantity;

        price = Float.parseFloat(request.getParameter("total_price_origin"));
        total_price = Float.parseFloat(request.getParameter("total_price"));
        total_discount = Float.parseFloat(request.getParameter("total_discount"));

        phone = request.getParameter("phone");
        address = request.getParameter("address");
        note = request.getParameter("note");
        status = 1;
        total_quantity = Integer.parseInt(request.getParameter("total_quantity"));

        order.setNote(note);
        order.setAddress(address);
        order.setStatus(status);
        order.setPhone(phone);
        order.setTotal_discount(total_discount);
        order.setTotal_price(total_price);
        order.setTotal_quantity(total_quantity);
        order.setBuyer(buyer);
        order.setPrice(price);
        int orderId = orderService.insertOrder(order);
        order.setId(orderId);
        if(objOrder != null) {
            Map<Integer, Order_Details> map = (Map<Integer, Order_Details>) objOrder;
            for (Order_Details order_detail : map.values()) {
                order_detail.setOrder(order);
                orderDetailsService.insertOrder_Details(order_detail);
            }
            SendMail.sendMail(order.getBuyer().getEmail(), "Order", "Payment success. We will contact you soon ! ");
        }
        session.removeAttribute("cart");
        request.getRequestDispatcher("/views/user/order_success.jsp").forward(request, response);
    }
}

