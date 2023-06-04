package com.warehouse.businesslogic;

public interface ProductService {
    void addProduct(String name, String category, double price, int quantity);
    void updateProduct(String id, String name, String category, double price, int quantity);

    void deleteProduct(String id);

    void viewProductDetails(String id);

    void searchProductsByParameter(String parameter, String value);

    void listAllProducts();
}
