package com.ews.web_seller_test.controller.user;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.UserService;
import com.ews.web_seller_test.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 5,   // 5 MB
        maxRequestSize = 1024 * 1024 * 10) // 10 MB
@WebServlet(name = "EditProfileController", urlPatterns = {"/edit_profile"})
public class EditProfileController extends HttpServlet {
    private static String uploadPath=null;

    @Override
    public void init() throws ServletException{
        uploadPath=  getServletContext().getRealPath("/assets/user/img/user");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User user = null;
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("account") != null) {
            user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("user", user);
        }

        int amount = 0;
        HttpSession httpSession = request.getSession();
        Map<Integer, Order_Details> mapList = null;
        if(httpSession.getAttribute("cart") != null) {
            mapList  = (Map<Integer, Order_Details>) httpSession.getAttribute("cart");
            amount = mapList.size();
            request.setAttribute("amount", amount);
        }

        String info = request.getParameter("info");


        if(info.equals("info")) {
            String fullName = request.getParameter("fullName");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");

            if(user != null) {
                user.setGender(gender);
                user.setPhone(phone);
                user.setFull_name(fullName);
                user.setAddress(address);
                user.setEmail(email);
                User tempUser = userService.getUser(user.getUsername());
                user.setId(tempUser.getId());
                userService.editUser(user);
                session.setAttribute("account", user);
            }
            request.getRequestDispatcher("/views/user/users-profile.jsp").forward(request, response);
        }

        if(info.equals("image")) {
            try {
                // Retrieve the file part from the request
                Part filePart = request.getPart("fileImage");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                System.out.println(fileName);

                // Save the file to the server
                InputStream inputStream = filePart.getInputStream();
                Files.copy(inputStream, Paths.get(uploadPath + File.separator + fileName));
                System.out.println(uploadPath + File.separator + fileName);
                if (user != null) {
                    user.setAvatar(fileName);
                }
                response.getWriter().println("File uploaded successfully!");
            } catch (IOException | ServletException e) {
                response.getWriter().println("File upload failed due to an error: " + e.getMessage());
            }


            if(user != null) {
                User tempUser = userService.getUser(user.getUsername());
                user.setId(tempUser.getId());
                userService.editUser(user);
                session.setAttribute("account", user);
            }
            request.getRequestDispatcher("/views/user/users-profile.jsp").forward(request, response);
        }
    }


}

