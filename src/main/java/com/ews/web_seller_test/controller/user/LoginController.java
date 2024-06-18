package com.ews.web_seller_test.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.UserService;
import com.ews.web_seller_test.service.impl.UserServiceImpl;
import com.ews.web_seller_test.until.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name="LoginController", urlPatterns={"/login"})
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("account") != null) {
            response.sendRedirect(request.getContextPath() + "/waiting");
            return;
        }

        //Check cookie
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = request.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    response.sendRedirect(request.getContextPath()+ "/waiting");
                    return;
                }
            }
        }
        request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isRememberMe = false;
        String remember = request.getParameter("rememberMe");

        if(remember != null && remember.equals("true")){
            isRememberMe = true;
        }

        String alertMsg = "";

        if(username.isEmpty() || password.isEmpty()){
            alertMsg = "Username and password can't be empty!";
            request.setAttribute("alert", alertMsg);
            request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
            return;
        }

        UserService service = new UserServiceImpl();
        User user = service.loginUser(username, password);

        if(user != null){
            HttpSession session = request.getSession(true);
            session.setAttribute("account", user);

            if(isRememberMe){
                saveRememberMe(response, username);
            }

            response.sendRedirect(request.getContextPath()+"/waiting");
        }else{
            alertMsg = "Username or password isn't correct";
            request.setAttribute("alert", alertMsg);
            request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
        }
    }
    private void saveRememberMe(HttpServletResponse response, String username){
        // Tạo một cookie với tên là Constant.COOKIE_REMEMBER và giá trị là username
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(30 * 60);
        // cookie.setPath("/");
        response.addCookie(cookie);
    }

}
