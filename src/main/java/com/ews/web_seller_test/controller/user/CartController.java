package com.ews.web_seller_test.controller.user;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ews.web_seller_test.model.Order;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.OrderService;
import com.ews.web_seller_test.service.Order_DetailsService;
import com.ews.web_seller_test.service.impl.OrderServiceImpl;
import com.ews.web_seller_test.service.impl.Order_DetailsImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CartController", urlPatterns = {"/member/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Get session username
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("user", user);
        }

        int amount = 0;
        HttpSession httpSession = request.getSession();
        Map<Integer, Order_Details> mapList = null;
        if(httpSession.getAttribute("cart") != null) {
            mapList  = (Map<Integer, Order_Details>) httpSession.getAttribute("cart");
            amount = mapList.size();
            request.setAttribute("amount", amount);
        }

        request.getRequestDispatcher("/views/user/my_cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
}

