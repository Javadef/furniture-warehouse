package com.warehouse.service;

import com.warehouse.entity.Product;

import java.util.List;

public interface ProductService {

    void add(Product newProduct);

    int getLastId();

    void saveChanges();

    void deleteProduct(int id);

    List<Product> listAllProducts();

    Product getProductById(int productId);

    void updateProduct(Product updatedProduct);

    List<Product> searchProductsByName(String name);

    List<Product> searchProductsByCategory(String category);

    List<Product> searchProductsByPriceRange(double minValue, double maxValue);

    List<Product> searchProductsByQuantity(int quantity);

    List<Product> searchProductsByManufacturer(String manufacturer);

    List<Product> searchProductsByWeight(double weight);

    List<Product> searchProductsByDimensions(String dimensions);


}
