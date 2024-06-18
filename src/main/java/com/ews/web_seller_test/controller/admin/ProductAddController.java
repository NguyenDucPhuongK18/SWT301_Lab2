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
@WebServlet(name = "ProductAddController", urlPatterns = {"/admin/product/add"})
public class ProductAddController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService cateService = new CategoryServiceImpl();
    UserService userService = new UserServiceImpl();
    Order_DetailsService orderDetailsService = new Order_DetailsImpl();
    private static String uploadPath=null;

    @Override
    public void init() throws ServletException{
        uploadPath=  getServletContext().getRealPath("/assets/user/img/product");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj=session.getAttribute("account");
        User user=(User) obj;
        if(user.getRole().getId() == 2) {
            req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
        }
        req.setAttribute("username", user.getUsername());

        List<Product> productList = productService.getAllProduct();
        List<User> userList = userService.getAllUser();
        List<Category> categoryList = cateService.getAllCategory();
        List<Order_Details> orderDetailsList = orderDetailsService.getAllOrder_Details();
        req.setAttribute("categories", categoryList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/view/add-product.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String info = request.getParameter("info");

        Product product = new Product();

        if(info.equals("info")) {
            String name = request.getParameter("name");
            float price = Float.parseFloat(request.getParameter("price"));
            float discount = Float.parseFloat(request.getParameter("discount"));
            String des = request.getParameter("des");
            Category category = new Category();
            category = cateService.getCategory(Integer.parseInt(request.getParameter("category")));

            product.setName(name);
            product.setDiscount(discount);
            product.setPrice(price);
            product.setDescription(des);
            product.setCategory(category);
            int pid = productService.getIdInsertProduct(product);
            product.setId(pid);
            HttpSession session = request.getSession();
            session.setAttribute("pidEdit", pid);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/view/add-product.jsp");
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

                HttpSession session = request.getSession();
                int pid= -1;
                if(session.getAttribute("pidEdit")!= null) {
                    pid = (int) session.getAttribute("pidEdit");
                    session.removeAttribute("pidEdit");
                } else {
                    request.setAttribute("notice", "Enter the form above before add image!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/view/add-product.jsp");
                    dispatcher.forward(request, response);
                }
                Product pro = productService.getProduct(pid);

                if (pro != null) {
                    pro.setImage(fileName);
                }
                productService.editProduct(pro);
                response.getWriter().println("File uploaded successfully!");
            } catch (IOException | ServletException e) {
                response.getWriter().println("File upload failed due to an error: " + e.getMessage());
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/product/list");
    }
}
