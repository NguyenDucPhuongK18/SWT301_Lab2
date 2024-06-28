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

        Product product = new Product(); // Create a new instance to avoid NPE in case of unexpected conditions

        if ("info".equals(info)) {
            try {
                String name = request.getParameter("name");
                float price = Float.parseFloat(request.getParameter("price"));
                float discount = Float.parseFloat(request.getParameter("discount"));
                String des = request.getParameter("des");
                Category category = cateService.getCategory(Integer.parseInt(request.getParameter("category")));

                product.setName(name);
                product.setDiscount(discount);
                product.setPrice(price);
                product.setDescription(des);
                product.setCategory(category);

                int pid = productService.getIdInsertProduct(product);
                product.setId(pid);

                HttpSession session = request.getSession();
                session.setAttribute("pidEdit", pid);

                // Forward to JSP for further interaction if needed
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/view/add-product.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                response.getWriter().println("Error: Invalid number format for price or discount.");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }

        if ("image".equals(info)) {
            try {
                Part filePart = request.getPart("fileImage");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                InputStream inputStream = filePart.getInputStream();
                Files.copy(inputStream, Paths.get(uploadPath + File.separator + fileName));

                HttpSession session = request.getSession();
                int pid = -1;
                if (session.getAttribute("pidEdit") != null) {
                    pid = (int) session.getAttribute("pidEdit");
                    session.removeAttribute("pidEdit");
                } else {
                    response.getWriter().println("Error: No product ID found in session.");
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }

                Product pro = productService.getProduct(pid);

                if (pro != null) {
                    pro.setImage(fileName);
                    productService.editProduct(pro);
                    response.getWriter().println("File uploaded successfully!");
                } else {
                    response.getWriter().println("Error: Product with ID " + pid + " not found.");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (IOException | ServletException e) {
                response.getWriter().println("File upload failed due to an error: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

        // Redirect to product list page after processing
        response.sendRedirect(request.getContextPath() + "/admin/product/list");
    }

}
