package com.ews.web_seller_test.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.*;
import com.ews.web_seller_test.service.impl.*;
import com.ews.web_seller_test.tools.SendMail;
import com.ews.web_seller_test.until.Constant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "UserAddController", urlPatterns = {"/admin/user/add"})
public class UserAddController extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj=session.getAttribute("account");
        User user=(User) obj;
        if(user.getRole().getId() == 2) {
            req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
        }
        req.setAttribute("username", user.getUsername());

        String eString = req.getParameter("e");
        if (eString != null) {
            if (eString.equals("1")) {
                req.setAttribute("errMsg", "Username existed...!!! Not Successfully!!!");
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/view/add-user.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<User> userList = userService.getAllUser();
        request.setAttribute("userList", userList);

//        User user = new User();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
//        String role = request.getParameter("role");

        UserService service = new UserServiceImpl();
        String errMsg = "";

        if (service.checkExistEmail(email)) {
            errMsg = "Email already exist!";
            request.setAttribute("alert", errMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        if (service.checkExistUsername(username)) {
            errMsg = "Username already exist!";
            request.setAttribute("alert", errMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        boolean isSuccess = service.registerUser(username, password, email, name, phone, address, gender);

        if (isSuccess) {
//            SendMail.sendMail(email, "EWS", "Welcome to EWS shop. Please Login to use service. Thanks !");
//            request.setAttribute("alert", alertMsg);
//            response.sendRedirect(request.getContextPath() + "/login");
            response.sendRedirect(request.getContextPath() + "/admin/user/list");
        } else {
//            alertMsg = "System error!";
//            request.setAttribute("alert", alertMsg);
//            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request,response);
            response.sendRedirect(request.getContextPath() + "/admin/user/add?e=1");
        }



    }
}


