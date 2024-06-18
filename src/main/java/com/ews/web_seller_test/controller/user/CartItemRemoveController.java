package com.ews.web_seller_test.controller.user;



import java.io.IOException;
import java.util.Map;

import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CartItemRemoveController", urlPatterns = {"/member/cart/remove"})
public class CartItemRemoveController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession httpSession = request.getSession();

        Object obj = httpSession.getAttribute("cart");

        String pId = request.getParameter("pId");

        if(obj != null) {
            Map<Integer, Order_Details> map = (Map<Integer, Order_Details>) obj;
            map.remove(Integer.parseInt(pId));
            httpSession.setAttribute("cart", map);
        }

        //Get session username
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("user", user);
        }

        int amount = 0;
        Map<Integer, Order_Details> mapList = null;
        if(httpSession.getAttribute("cart") != null) {
            mapList  = (Map<Integer, Order_Details>) httpSession.getAttribute("cart");
            amount = mapList.size();
            request.setAttribute("amount", amount);
        }

        response.sendRedirect(request.getContextPath() + "/member/cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
}

