package com.ews.web_seller_test.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.ProductService;
import com.ews.web_seller_test.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CartAddController", urlPatterns = {"/member/cart/add"})
public class CartAddController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

//        HttpSession session = request.getSession();
//        if(session.getAttribute("currentUrl") != null) {
//            session.removeAttribute("currentUrl");
//        }
//        if(session.getAttribute("account") == null) {
//            String currentUrl = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
//            session.setAttribute("currentUrl", currentUrl);
//            System.out.println("currentUrl: " + currentUrl);
//            request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
//        }

        String pId = request.getParameter("pid");
        int xpId = -1;

        String quantity = request.getParameter("quantity");

        try {
             xpId = Integer.parseInt(pId);
        } catch (NumberFormatException ignored) {

        }

        Product product = productService.getProduct(xpId);

        Order_Details order_details = new Order_Details();

        order_details.setProduct(product);
        order_details.setQuantity(Integer.parseInt(quantity));
        order_details.setPrice(product.getPrice());
        order_details.setDiscount(product.getDiscount());

        HttpSession httpSession = request.getSession();
        Object obj = httpSession.getAttribute("cart");

        Map<Integer, Order_Details> map = null;

        int amount = 0;
        Map<Integer, Order_Details> mapList = null;
        if(httpSession.getAttribute("cart") != null) {
            mapList  = (Map<Integer, Order_Details>) httpSession.getAttribute("cart");
            amount = mapList.size();
            request.setAttribute("amount", amount);
        }

        if (obj == null) {
            map = new HashMap<>();
            map.put(order_details.getProduct().getId(), order_details);
            httpSession.setAttribute("cart", map);
        } else if (obj instanceof Map) {
            map = (Map<Integer, Order_Details>) obj;

            Order_Details existedOrderItem = map.get(Integer.valueOf(pId));

            if (existedOrderItem == null) {
                map.put(order_details.getProduct().getId(), order_details);
            } else {
                existedOrderItem.setQuantity(existedOrderItem.getQuantity() + Integer.parseInt(quantity));
            }
        }

        httpSession.setAttribute("cart", map);
        response.sendRedirect(request.getContextPath() + "/member/cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Get session username
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("user", user);
        }


        String[] pIds = request.getParameterValues("pid");
        String[] quantities = request.getParameterValues("quantity");

        HttpSession httpSession = request.getSession();
        Map<Integer, Order_Details> map = (Map<Integer, Order_Details>) httpSession.getAttribute("cart");


        int amount = 0;
        Map<Integer, Order_Details> mapList = null;
        if(httpSession.getAttribute("cart") != null) {
            mapList  = (Map<Integer, Order_Details>) httpSession.getAttribute("cart");
            amount = mapList.size();
            request.setAttribute("amount", amount);
        }

        if (map == null) {
            map = new HashMap<>();
        }

        if (pIds != null && quantities != null && pIds.length == quantities.length) {
            for (int i = 0; i < pIds.length; i++) {
                String pId = pIds[i];
                String quantity = quantities[i];

                int xpId = -1;
                try {
                    xpId = Integer.parseInt(pId);
                } catch (NumberFormatException ignored) {
                    continue;
                }

                Product product = productService.getProduct(xpId);

                if (product != null) {
                    Order_Details order_details = new Order_Details();
                    order_details.setProduct(product);
                    order_details.setQuantity(Integer.parseInt(quantity));
                    order_details.setPrice(product.getPrice());
                    order_details.setDiscount(product.getDiscount());
                    map.put(xpId, order_details);
                }
            }
        }
        httpSession.setAttribute("cart", map);
        response.sendRedirect(request.getContextPath() + "/member/cart");
    }
}

