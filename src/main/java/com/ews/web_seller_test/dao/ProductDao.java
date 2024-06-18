package com.ews.web_seller_test.dao;

import com.ews.web_seller_test.model.Product;

import java.util.List;

public interface ProductDao {
    void insertProduct(Product product);

    int getIdInsertProduct(Product product);

    void editProduct(Product product);

    int countProduct(String txtSearch);
    int countProductCategory(String textCategory);
    List<Product> searchProductByCategory(String categoryName, int index, int size);
    void deleteProduct(int id);

    Product getProduct(int id);

    List<Product> getAllProduct();

    List<Product> searchProduct(String username);

    List<Product> searchProductByCategory(int cate_id);

    List<Product> searchProductByName(String productName);

    List<Product> searchProductByName(String productName, int index, int size);

}
