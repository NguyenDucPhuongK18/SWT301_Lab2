package com.ews.web_seller_test.service;

import com.ews.web_seller_test.model.Product;

import java.util.List;

public interface ProductService {
    void insertProduct(Product product);
    int getIdInsertProduct(Product product);

    void editProduct(Product newProduct);

    void deleteProduct(int id);

    Product getProduct(int id);

    List<Product> getAllProduct();

    List<Product> searchProduct(String product);

    List<Product> searchProductByCategory(int cate_id);

    List<Product> searchProductByName(String productName);
    int countProduct(String txtSearch);
    int countProductCategory(String textCategory);

    List<Product> searchProductByName(String productName, int index, int size);
    List<Product> searchProductByCategory(String categoryName, int index, int size);

}
