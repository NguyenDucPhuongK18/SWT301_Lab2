package com.ews.web_seller_test.controller.user;


import java.io.IOException;
import java.io.PrintWriter;

import com.ews.web_seller_test.until.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name="LogoutController", urlPatterns={"/logout"})
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        session.removeAttribute("account");

        Cookie[] cookies = request.getCookies();

        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(Constant.COOKIE_REMEMBER.equals(cookie.getName())){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        response.sendRedirect("./login");
    }
}
