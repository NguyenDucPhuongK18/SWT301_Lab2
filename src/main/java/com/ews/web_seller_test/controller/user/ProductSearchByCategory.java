package com.ews.web_seller_test.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.ProductService;
import com.ews.web_seller_test.service.impl.CategoryServiceImpl;
import com.ews.web_seller_test.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "ProductSearchByCategory", urlPatterns = {"/product/category"})
public class ProductSearchByCategory extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session= request.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User u=(User) session.getAttribute("account");
            request.setAttribute("username", u.getUsername());
            request.setAttribute("user", u);
            request.setAttribute("name", u.getFull_name());
        }

        List<Category> categoryList = new ArrayList<Category>();
        categoryList = categoryService.getAllCategory();
        request.setAttribute("categoryList", categoryList);

        String txtSearch = request.getParameter("txtSearch");

        int count = productService.countProductCategory(txtSearch);
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
        List<Product> productList = productService.searchProductByCategory(txtSearch, indexPage, pageSize);
        List<Product> productList2 = productService.searchProductByCategory("Jackets", indexPage, pageSize);
        productList.addAll(productList2);


        int start = (indexPage * pageSize) - (pageSize - 1);
        int en = indexPage * pageSize;



        boolean checkCategorySearch = true;
        request.setAttribute("checkCategorySearch", checkCategorySearch);

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



