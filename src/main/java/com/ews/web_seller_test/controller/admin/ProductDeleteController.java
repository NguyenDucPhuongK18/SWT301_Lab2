package com.ews.web_seller_test.controller.admin;

import java.io.IOException;
import java.util.List;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.*;
import com.ews.web_seller_test.service.impl.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ProductDeleteController", urlPatterns = {"/admin/product/delete"})
public class ProductDeleteController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService cateService = new CategoryServiceImpl();
    UserService userService = new UserServiceImpl();
    Order_DetailsService orderDetailsService = new Order_DetailsImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("account");
        if (obj instanceof User) {
            User user = (User) obj;
            if (user.getRole() != null && user.getRole().getId() == 2) {
                req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
                return; // Exit the method after forwarding
            }
            req.setAttribute("username", user.getUsername());
            String id = req.getParameter("id");
            productService.deleteProduct(Integer.parseInt(id));
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access");
        }
    }

}
