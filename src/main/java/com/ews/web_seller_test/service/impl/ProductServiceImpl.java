package com.ews.web_seller_test.service.impl;

import com.ews.web_seller_test.dao.ProductDao;
import com.ews.web_seller_test.dao.impl.ProductDaoImpl;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.service.ProductService;

import java.io.File;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao = new ProductDaoImpl();
    @Override
    public void insertProduct(Product product) {
        productDao.insertProduct(product);
    }

    @Override
    public int getIdInsertProduct(Product product) {
        return productDao.getIdInsertProduct(product);
    }

    @Override
    public List<Product> searchProductByName(String productName, int index, int size) {
        return productDao.searchProductByName(productName,index,size);
    }

    @Override
    public void editProduct(Product newProduct) {
        Product oldProduct = productDao.getProduct(newProduct.getId());
        oldProduct.setName(newProduct.getName());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setCategory(newProduct.getCategory());
        oldProduct.setDiscount(newProduct.getDiscount());
        oldProduct.setCategory(newProduct.getCategory());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setTotal_rating(newProduct.getTotal_rating());
        oldProduct.setTotal_starts(newProduct.getTotal_starts());
        oldProduct.setStatus(newProduct.getStatus());
        if (newProduct.getImage() != null) {
            oldProduct.setImage(newProduct.getImage());
        }
        productDao.editProduct(oldProduct);
    }


//    public void edit(Product newProduct) {
//        Product oldProduct = productDao.get(newProduct.getId());
//
//        oldProduct.setName(newProduct.getName());
//        oldProduct.setPrice(newProduct.getPrice());
//        oldProduct.setCategory(newProduct.getCategory());
//        if (newProduct.getImage() != null) {
//            // XOA ANH CU DI
//            String fileName = oldProduct.getImage();
//            final String dir = "F:\\upload";
//            File file = new File(dir + "/" + fileName);
//            if (file.exists()) {
//                file.delete();
//            }
//
//            oldProduct.setImage(newProduct.getImage());
//        }
//
//        productDao.edit(oldProduct);
//
//    }


    @Override
    public int countProduct(String txtSearch) {
        return productDao.countProduct(txtSearch);
    }

    @Override
    public int countProductCategory(String textCategory) {
        return productDao.countProductCategory(textCategory);
    }

    @Override
    public List<Product> searchProductByCategory(String categoryName, int index, int size) {
        return productDao.searchProductByCategory(categoryName,index,size);
    }

    @Override
    public void deleteProduct(int id) {
        productDao.deleteProduct(id);
    }

    @Override
    public Product getProduct(int id) {
        return productDao.getProduct(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }

    @Override
    public List<Product> searchProduct(String product) {
        return productDao.searchProduct(product);
    }

    @Override
    public List<Product> searchProductByCategory(int cate_id) {
        return productDao.searchProductByCategory(cate_id);
    }

    @Override
    public List<Product> searchProductByName(String productName) {
        return productDao.searchProductByName(productName);
    }
}
