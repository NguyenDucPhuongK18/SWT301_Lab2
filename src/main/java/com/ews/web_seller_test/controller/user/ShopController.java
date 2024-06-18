package com.ews.web_seller_test.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.ProductService;
import com.ews.web_seller_test.service.impl.CategoryServiceImpl;
import com.ews.web_seller_test.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="ShopController", urlPatterns={"/shop"})
public class ShopController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Category> categoryList = new ArrayList<Category>();
        categoryList = categoryService.getAllCategory();
        request.setAttribute("categoryList", categoryList);

        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("user", user);
        }

        int amount = 0;
        Map<Integer, Order_Details> mapList = null;
        if(session.getAttribute("cart") != null) {
            mapList = (Map<Integer, Order_Details>) session.getAttribute("cart");
            amount = mapList.size();
            request.setAttribute("amount", amount);
        }

        int count = productService.countProduct("");
        int pageSize = 8;
        int endPage = 0;

        endPage = count%pageSize==0? (int) count/pageSize : (int) count/pageSize +1;

        int indexPage = Integer.parseInt(request.getParameter("indexPage"));
        if(indexPage < 1) {
            indexPage = 1;
        }
        if(indexPage > endPage) {
            indexPage = endPage;
        }
        List<Product> productList = productService.searchProductByName("", indexPage, pageSize);
        int start = (indexPage * pageSize) - (pageSize - 1);
        int en = indexPage * pageSize;

        request.setAttribute("end", endPage);
        request.setAttribute("en", en);
        request.setAttribute("start", start);
        request.setAttribute("count", count);
        request.setAttribute("productList", productList);
        request.setAttribute("indexPage", indexPage);
        request.getRequestDispatcher("/views/user/shopping.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User u=(User) session.getAttribute("account");
            request.setAttribute("username", u.getUsername());
        }

        List<Category> categoryList = new ArrayList<Category>();
        categoryList = categoryService.getAllCategory();
        request.setAttribute("categoryList", categoryList);

        String txtSearch = request.getParameter("txtSearch");

        int count = productService.countProduct(txtSearch);
        int pageSize = 8;
        int endPage = 0;

        endPage = count%pageSize==0? (int) count/pageSize : (int) count/pageSize +1;
        int indexPage = Integer.parseInt(request.getParameter("indexPage"));

        if(indexPage < 1) {
            indexPage = 1;
        }
        if(indexPage > endPage) {
            indexPage = endPage;
        }

        List<Product> productList = productService.searchProductByName(txtSearch, indexPage, pageSize);


        int start = (indexPage * pageSize) - (pageSize - 1);
        int en = indexPage * pageSize;


        request.setAttribute("start", start);
        request.setAttribute("en", en);
        request.setAttribute("count", count);
        request.setAttribute("end", endPage);
        request.setAttribute("indexPage", indexPage);
        request.setAttribute("productList", productList);
        request.setAttribute("txtSearch", txtSearch);
        request.getRequestDispatcher("/views/user/shopping.jsp").forward(request, response);
    }
}
