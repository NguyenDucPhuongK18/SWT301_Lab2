package com.ews.web_seller_test.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.UserService;
import com.ews.web_seller_test.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ChangePasswordController", urlPatterns = {"/change-password"})
public class ChangePasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Get session username
        HttpSession session = request.getSession();
        User user = null;
        if(session != null && session.getAttribute("account") != null) {
            user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("user", user);
        }

        String password = (String) request.getParameter("password");
        String new_password = (String) request.getParameter("new_password");
        String renew_password = (String) request.getParameter("renew_password");

        String msgAlert = null;
        UserService userService = new UserServiceImpl();

        if(user != null && user.getPassword().equals(password)) {
            if(new_password.equals(renew_password)) {
                user.setPassword(new_password);
                userService.editUser(user);
                session.removeAttribute("account");
                msgAlert = "Change password success!!!";
                request.setAttribute("msgAlert",msgAlert);
                request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
            } else {
                msgAlert = "New password and re new password are not the same";
            }
        } else {
            msgAlert = "The password you just entered is incorrect!";
        }
        request.setAttribute("msgAlert",msgAlert);
        request.getRequestDispatcher("/views/user/users-profile.jsp").forward(request, response);
    }
}

