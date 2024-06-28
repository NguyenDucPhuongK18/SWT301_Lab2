package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.dao.MyDAO;
import com.ews.web_seller_test.dao.ProductDao;
import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.impl.CategoryServiceImpl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;

public class ProductDaoImpl extends MyDAO implements ProductDao {
    CategoryService categoryService = new CategoryServiceImpl();
    @Override
    public void insertProduct(Product product) {
        try {
            ps = con.prepareStatement("INSERT INTO Product (category_id, name, price, discount, image, description, created_at, updated_at, total_rating, total_starts, status) VALUES (?,?,?,?,?,?,NOW(),NOW(),?,?,?)");
            ps.setInt(1, product.getCategory().getId());
            ps.setString(2, product.getName());
            ps.setFloat(3, product.getPrice());
            ps.setFloat(4, product.getDiscount());
            ps.setString(5, product.getImage());
            ps.setString(6, product.getDescription());
            ps.setInt(7, product.getTotal_rating());
            ps.setInt(8, product.getTotal_starts());
            ps.setInt(9, product.getStatus());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getIdInsertProduct(Product product) {
        int productId = 0;
        xSql = "INSERT INTO Product(category_id, name, price, discount, image, description, created_at, updated_at, total_rating, total_starts, status) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            // Disable auto-commit
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(xSql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, product.getCategory().getId());
            ps.setString(2, product.getName());
            ps.setFloat(3, product.getPrice());
            ps.setFloat(4, product.getDiscount());
            ps.setString(5, product.getImage());
            ps.setString(6, product.getDescription());
            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // created_at
            ps.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // updated_at
            ps.setInt(9, product.getTotal_rating());
            ps.setInt(10, product.getTotal_starts());
            ps.setInt(11, product.getStatus());

            int rowAffected = ps.executeUpdate();
            if (rowAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    productId = rs.getInt(1);
                }
                // Commit transaction
                con.commit();
            }
            ps.close();
        } catch (SQLException e) {
            try {
                // Rollback if an error occurs
                con.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                // Restore auto-commit mode
                con.setAutoCommit(true);
            } catch (SQLException setAutoCommitException) {
                setAutoCommitException.printStackTrace();
            }
        }
        return productId;
    }



    @Override
    public void editProduct(Product product) {
        xSql = "UPDATE Product SET category_id = ?, name = ?, price = ?, discount = ?, image = ?, description = ?, created_at = NOW(), updated_at = NOW(), total_rating = ?, total_starts = ?, status = ?  " +
                "WHERE id = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, product.getCategory().getId());
            ps.setString(2, product.getName());
            ps.setFloat(3, product.getPrice());
            ps.setFloat(4, product.getDiscount());
            ps.setString(5, product.getImage());
            ps.setString(6, product.getDescription());
            ps.setInt(7, product.getTotal_rating());
            ps.setInt(8, product.getTotal_starts());
            ps.setInt(9, product.getStatus());
            ps.setInt(10, product.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int id) {
        xSql = "DELETE FROM Product WHERE id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProduct(int id) {
        xSql = "SELECT Product.id, Product.category_id, Product.name AS p_name, Product.price, Product.discount, Product.image, Product.description,Product.total_starts, Product.total_rating, Product.status, Category.name AS c_name, Category.id AS c_id "
                + "FROM Product INNER JOIN Category " + "ON Product.category_id = Category.id WHERE Product.id= ? ";
        Category category;
        Product product = new Product(1, "Product1");
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("p_name"));
                product.setPrice(rs.getFloat("price"));
                product.setDiscount(rs.getFloat("discount"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setTotal_rating(rs.getInt("total_rating"));
                product.setTotal_starts(rs.getInt("total_starts"));
                product.setStatus(rs.getInt("status"));

                category = categoryService.getCategory(rs.getInt("c_id"));
                product.setCategory(category);

                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<Product>();
        xSql = "SELECT Product.id, Product.name AS p_name, Product.price, Product.discount, Product.image, Product.description, Product.total_starts, Product.total_rating, Product.status, Category.name AS c_name, Category.id AS c_id FROM Product INNER JOIN Category ON Product.category_id = Category.id;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = categoryService.getCategory(rs.getInt("c_id"));
                Product product = new Product(1, "Product1");

                product.setId(rs.getInt("id"));
                product.setName(rs.getString("p_name"));
                product.setPrice(rs.getFloat("price"));
                product.setDiscount(rs.getFloat("discount"));
                product.setDescription(rs.getString("description"));
                product.setTotal_rating(rs.getInt("total_rating"));
                product.setTotal_starts(rs.getInt("total_starts"));
                product.setStatus(rs.getInt("status"));
                product.setImage(rs.getString("image"));
                product.setCategory(category);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> searchProduct(String username) {
        List<Product> productList = new ArrayList<Product>();
        String sql = "SELECT * FROM Product WHERE name LIKE ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + username + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(1, "Product1");
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setDiscount(rs.getFloat("discount"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setTotal_rating(rs.getInt("total_rating"));
                product.setTotal_starts(rs.getInt("total_starts"));
                product.setStatus(rs.getInt("status"));
                product.setCategory(categoryService.getCategory(rs.getInt("category_id")));
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Product> searchProductByCategory(int cate_id) {
        List<Product> productList = new ArrayList<Product>();
        xSql = "SELECT Product.id, Product.name AS p_name, Product.price, Product.discount, Product.image, Product.description,Product.total_starts, Product.total_rating, Product.status, Category.name AS c_name, Category.id AS c_id "
                + "FROM Product INNER JOIN Category " + "ON Product.id = Category.id WHERE Category.id = ? ";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cate_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Category category = categoryService.getCategory(rs.getInt("c_id"));
                Product product = new Product(1, "Product1");

                product.setId(rs.getInt("id"));
                product.setName(rs.getString("p_name"));
                product.setPrice(rs.getFloat("price"));
                product.setDiscount(rs.getFloat("discount"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setTotal_rating(rs.getInt("total_rating"));
                product.setTotal_starts(rs.getInt("total_starts"));
                product.setStatus(rs.getInt("status"));
                product.setCategory(category);
                productList.add(product);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return productList;    }

    @Override
    public List<Product> searchProductByName(String productName) {
        List<Product> productList = new ArrayList<Product>();
        xSql = "SELECT Product.id, Product.name AS p_name, Product.price, Product.discount, Product.image, Product.description,Product.total_starts, Product.total_rating, Product.status, Category.name AS c_name, Category.id AS c_id "
                + "FROM Product INNER JOIN Category " + "ON Product.id = Category.id WHERE Product.name LIKE ? ";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + productName + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Category category = categoryService.getCategory(rs.getInt("c_id"));
                Product product = new Product(1, "Product1");

                product.setId(rs.getInt("id"));
                product.setName(rs.getString("p_name"));
                product.setPrice(rs.getFloat("price"));
                product.setDiscount(rs.getFloat("discount"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setTotal_rating(rs.getInt("total_rating"));
                product.setTotal_starts(rs.getInt("total_starts"));
                product.setStatus(rs.getInt("status"));
                product.setCategory(category);
                productList.add(product);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public int countProduct(String txtSearch) {
        xSql = "SELECT COUNT(*) as count FROM Product WHERE name LIKE ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            int count=0;
            while (rs.next()) {
                count = rs.getInt("count");
                return count;
            }
        } catch (SQLException ignored) {
        }
        return 0;
    }

    @Override
    public int countProductCategory(String textCategory) {
        xSql = "SELECT COUNT(*) as count FROM Product INNER JOIN Category C on C.id = Product.category_id WHERE C.name LIKE ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + textCategory + "%");
            rs = ps.executeQuery();
            int count=0;
            while (rs.next()) {
                count = rs.getInt("count");
                return count;
            }
        } catch (SQLException ignored) {
        }
        return 0;
    }

    @Override
    public List<Product> searchProductByCategory(String categoryName, int index, int size) {
        List<Product> productList = new ArrayList<Product>();
//        xSql = "WITH x as (SELECT ROW_NUMBER() over (order by Product.id) as row_number, * " +
//                "FROM Product INNER JOIN dbo.Category C on C.id = Product.category_id WHERE C.name = ?)" +
//                "SELECT * FROM x WHERE  row_number between ? and ?";

        Category tempCate = categoryService.getCategory(categoryName);
        int idC = tempCate.getId();

        xSql = "WITH x as (SELECT ROW_NUMBER() over (order by id) as row_number, * FROM Product WHERE Product.category_id = ?)" +
                "SELECT * FROM x WHERE  row_number between ? and ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, idC);
            int a = (index * size) - (size - 1);
            int b = index * size;
            ps.setInt(2, a);
            ps.setInt(3, b);
            rs = ps.executeQuery();

            while (rs.next()) {
                Category category = categoryService.getCategory(rs.getInt("category_id"));
                Product product = new Product(1, "Product1");

                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setDiscount(rs.getFloat("discount"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setTotal_rating(rs.getInt("total_rating"));
                product.setTotal_starts(rs.getInt("total_starts"));
                product.setStatus(rs.getInt("status"));
                product.setCategory(category);
                productList.add(product);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> searchProductByName(String productName, int index, int size) {
//        if (Objects.equals(productName, "")) {return getAllProduct();}
        List<Product> productList = new ArrayList<Product>();
        xSql = "WITH x as (SELECT ROW_NUMBER() over (order by id) as row_number, * FROM Product WHERE name LIKE ?)" +
                "SELECT * FROM x WHERE  row_number between ? and ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + productName + "%");
            int a = (index * size) - (size - 1);
            int b = index * size;
            ps.setInt(2, a);
            ps.setInt(3, b);
            rs = ps.executeQuery();

            while (rs.next()) {
                Category category = categoryService.getCategory(rs.getInt("category_id"));
                Product product = new Product(1, "Product1");

                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setDiscount(rs.getFloat("discount"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setTotal_rating(rs.getInt("total_rating"));
                product.setTotal_starts(rs.getInt("total_starts"));
                product.setStatus(rs.getInt("status"));
                product.setCategory(category);
                productList.add(product);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return productList;
    }

//    public static void main(String[] args) {
//        ProductDao productDao = new ProductDaoImpl();
//        System.out.println(productDao.countProductCategory("Jeans"));
//        for(Product)
//    }
}
