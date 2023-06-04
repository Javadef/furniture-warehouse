package com.warehouse.dataaccess;

import com.warehouse.model.Product;

public interface InventoryRepository {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(String id);
    Product getProductById(String id);
    Product[] searchProductsByParameters(String parameter, String value);
    Product[] getAllProducts();
}
