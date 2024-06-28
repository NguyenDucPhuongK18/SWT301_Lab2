package com.ews.web_seller_test.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.*;
import com.ews.web_seller_test.service.impl.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 5,   // 5 MB
        maxRequestSize = 1024 * 1024 * 10) // 10 MB
@WebServlet(name = "ProductEditController", urlPatterns = {"/admin/product/edit"})
public class ProductEditController extends HttpServlet {
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

        String id = req.getParameter("id");


        req.setAttribute("id", id);
        Product product = productService.getProduct(Integer.parseInt(id));
        List<Category> categories = cateService.getAllCategory();
        req.setAttribute("categories", categories);
        req.setAttribute("product", product);
        req.getRequestDispatcher("/views/admin/view/edit-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Object obj=session.getAttribute("account");
        User user=(User) obj;
        if(user.getRole().getId() == 2) {
            request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
        }
        request.setAttribute("username", user.getUsername());

        String info = request.getParameter("info");

        Product product = new Product(1, "Product1");

        if(info.equals("info")) {
            String name = request.getParameter("name");
            int pid = Integer.parseInt(request.getParameter("id"));

            String id = request.getParameter("id");

            String image = request.getParameter("image");
            float price = Float.parseFloat(request.getParameter("price")) + 100;
            float discount = Float.parseFloat(request.getParameter("discount"));
            String des = request.getParameter("des") + "xyz";
            Category category = new Category();
            category = cateService.getCategory(Integer.parseInt(request.getParameter("category")));

            product.setName(name);
            product.setImage(image);
            product.setId(pid);
            product.setDiscount(discount);
            product.setPrice(price);
            product.setDescription(des);
            product.setCategory(category);
            productService.editProduct(product);

            List<Category> categories = cateService.getAllCategory();
            request.setAttribute("categories", categories);
            request.setAttribute("product", product);

            request.getRequestDispatcher("/views/admin/view/edit-product.jsp").forward(request, response);
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

                int pid = Integer.parseInt(request.getParameter("id"));
                Product pro = productService.getProduct(pid);
                if (pro != null) {
                    pro.setImage(fileName);
                }
                productService.editProduct(pro);
//                request.setAttribute("product", pro);

                response.getWriter().println("File uploaded successfully!");
            } catch (IOException | ServletException e) {
                response.getWriter().println("File upload failed due to an error: " + e.getMessage());
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/product/list");
    }
}
