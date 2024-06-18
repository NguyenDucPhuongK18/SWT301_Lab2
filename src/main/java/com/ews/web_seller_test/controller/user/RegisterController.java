package com.ews.web_seller_test.controller.user;

import java.io.IOException;

import com.ews.web_seller_test.service.UserService;
import com.ews.web_seller_test.service.impl.UserServiceImpl;
import com.ews.web_seller_test.tools.SendMail;
import com.ews.web_seller_test.until.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = request.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    response.sendRedirect(request.getContextPath() + "/admin");
                    return;
                }
            }
        }
        request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");

        UserService service = new UserServiceImpl();
        String alertMsg = "";

        if (service.checkExistEmail(email)) {
            alertMsg = "Email already exist!";
            request.setAttribute("alert", alertMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        if (service.checkExistUsername(username)) {
            alertMsg = "Username already exist!";
            request.setAttribute("alert", alertMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        boolean isSuccess = service.registerUser(username, password, email, name, phone, address, gender);

        if (isSuccess) {
            SendMail.sendMail(email, "EWS", "Welcome to EWS shop. Please Login to use service. Thanks !");
            request.setAttribute("alert", alertMsg);
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            alertMsg = "System error!";
            request.setAttribute("alert", alertMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request,response);
        }


    }
}
