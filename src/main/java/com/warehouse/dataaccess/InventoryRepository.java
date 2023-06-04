package com.warehouse.dataaccess;

import com.warehouse.model.Product;

public interface InventoryRepository {
    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(String id);

    Product getProductById(String id);

    Product[] searchProductsById(String value);

    Product[] searchProductsByName(String value);

    Product[] searchProductsByCategory(String value);

    Product[] searchProductsByManufacturer(String value);

    Product[] searchProductsByPrice(double minValue, double maxValue);

    Product[] searchProductsByQuantity(int value);

    Product[] searchProductsByWeight(double value);

    Product[] searchProductsByDimensions(String value);

    Product[] getAllProducts();


}
