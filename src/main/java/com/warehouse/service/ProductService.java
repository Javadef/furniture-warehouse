package com.warehouse.service;

public interface ProductService {
    void addProduct(String name, String category, double price, int quantity,
                    String manufacturer, double weight, String dimensions);

    void updateProduct(String id, String name, String category, double price, int quantity,
                       String manufacturer, double weight, String dimensions);

    void deleteProduct(String id);

    void viewProductDetails(String id);

    void searchProductsByParameter(String parameter, String value);

    void listAllProducts();
}
