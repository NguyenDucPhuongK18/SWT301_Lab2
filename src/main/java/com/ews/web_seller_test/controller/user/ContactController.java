package com.ews.web_seller_test.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.tools.SendMail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ContactController", urlPatterns = {"/contact"})
public class ContactController extends HttpServlet {
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

        //Get session username
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("user", user);
        }

        request.getRequestDispatcher("/views/user/contact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

        //Get session username
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("user", user);
        }

        String name = request.getParameter("name");
        String subject = request.getParameter("subject");
        String email = request.getParameter("email");
        String mess = request.getParameter("message");
        SendMail.sendContactMail(email, subject, mess, name);
        request.setAttribute("notice", "Send successfully!");
        request.getRequestDispatcher("/views/user/contact.jsp").forward(request, response);
    }
}

