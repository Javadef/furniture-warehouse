package com.warehouse.dataaccess;

import com.warehouse.entity.Product;

import java.util.List;

public interface InventoryRepository {
    void add(Product newProduct);

    void addProduct(Product product);

    void removeProduct(int productId);

    Product getProductById(int productId);

    List<Product> getAllProducts();

    void updateProduct(Product updatedProduct);

    List<Product> searchProductsByName(String name);

    List<Product> searchProductsByCategory(String category);

    List<Product> searchProductsByPriceRange(double minValue, double maxValue);

    List<Product> searchProductsByQuantity(int quantity);

    List<Product> searchProductsByManufacturer(String manufacturer);

    List<Product> searchProductsByWeight(double weight);

    List<Product> searchProductsByDimensions(String dimensions);

    void saveChanges();

    int getLastId();
}
