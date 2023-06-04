package com.warehouse.businesslogic.implementation;

import com.warehouse.businesslogic.ProductService;
import com.warehouse.dataaccess.InventoryRepository;
import com.warehouse.model.Product;

public class ProductServiceImpl implements ProductService {
    private final InventoryRepository inventoryRepository;

    public ProductServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void addProduct(String name, String category, double price, int quantity) {
        String id = generateNextId();
        Product product = new Product(id, name, category, price, quantity);
        inventoryRepository.addProduct(product);
        System.out.println("Product added successfully.");
    }

    @Override
    public void updateProduct(String id, String name, String category, double price, int quantity) {
        Product product = inventoryRepository.getProductById(id);
        if (product != null) {
            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);
            product.setQuantity(quantity);
            inventoryRepository.updateProduct(product);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public void deleteProduct(String id) {
        Product product = inventoryRepository.getProductById(id);
        if (product != null) {
            inventoryRepository.deleteProduct(id);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public void viewProductDetails(String id) {
        Product product = inventoryRepository.getProductById(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public void searchProductsByParameter(String parameter, String value) {
        Product[] matchingProducts = inventoryRepository.searchProductsByParameters(parameter, value);
        if (matchingProducts.length > 0) {
            for (Product product : matchingProducts) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products found.");
        }
    }

    @Override
    public void listAllProducts() {
        Product[] allProducts = inventoryRepository.getAllProducts();
        if (allProducts.length > 0) {
            for (Product product : allProducts) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products found.");
        }
    }

    private String generateNextId() {
        Product[] allProducts = inventoryRepository.getAllProducts();
        int maxId = 0;
        for (Product product : allProducts) {
            int productId = Integer.parseInt(product.getId());
            maxId = Math.max(maxId, productId);
        }
        return String.valueOf(maxId + 1);
    }
}
