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
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 5,   // 5 MB
        maxRequestSize = 1024 * 1024 * 10) // 10 MB
@WebServlet(name = "UserEditController", urlPatterns = {"/admin/user/edit"})
public class UserEditController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService cateService = new CategoryServiceImpl();
    UserService userService = new UserServiceImpl();
    Order_DetailsService orderDetailsService = new Order_DetailsImpl();
    private static String uploadPath=null;

    @Override
    public void init() throws ServletException{
        uploadPath=  getServletContext().getRealPath("/assets/user/img/user");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("account");
        User user1=(User) obj;
        if(user1.getRole().getId() == 2) {
            req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
        }
        req.setAttribute("username", user1.getUsername());

        int id = Integer.parseInt(req.getParameter("id"));
        User user = userService.getUser(id);
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/view/edit-user.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String info = request.getParameter("info");

        User user = new User();

        if(info.equals("info")) {
            String fullName = request.getParameter("full_name");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            int id = Integer.parseInt(request.getParameter("id"));
            user = userService.getUser(id);

            user.setUsername(username);
            user.setPassword(password);
            user.setGender(gender);
            user.setPhone(phone);
            user.setFull_name(fullName);
            user.setAddress(address);
            user.setEmail(email);
            user.setId(id);

            userService.editUser(user);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/view/edit-user.jsp");
            dispatcher.forward(request, response);
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

                int id = Integer.parseInt(request.getParameter("id"));
                user = userService.getUser(id);
                if (user != null) {
                    user.setAvatar(fileName);
                }
                userService.editUser(user);
                request.setAttribute("user", user);
                response.getWriter().println("File uploaded successfully!");
            } catch (IOException | ServletException e) {
                response.getWriter().println("File upload failed due to an error: " + e.getMessage());
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/view/edit-user.jsp");
        dispatcher.forward(request, response);
    }
}


