package com.warehouse.controller;

import com.warehouse.entity.Product;
import com.warehouse.presentation.Prints;
import com.warehouse.presentation.ProductView;
import com.warehouse.service.ProductService;
import com.warehouse.service.implementation.ProductServiceImpl;
import com.warehouse.dataaccess.InventoryRepository;
import com.warehouse.dataaccess.implementation.InventoryRepositoryImpl;
import com.warehouse.presentation.MenuView;
import com.warehouse.utility.InputHandler;
import com.warehouse.utility.ObjectCreator;
import com.warehouse.utility.Validator;


public class MainController {
    private final ProductService productService;
    private final InputHandler inputHandler;
    private final SearchHandler searchHandler;
    private final ObjectCreator objectCreator;


    public MainController() {
        InventoryRepository inventoryRepository = new InventoryRepositoryImpl();
        productService = new ProductServiceImpl(inventoryRepository);
        inputHandler = new InputHandler();
        searchHandler = new SearchHandler(productService);
        objectCreator = new ObjectCreator(inputHandler, productService);
    }

    public void start() {
        MenuView.displayApplicationInfo();

        while (true) {
            MenuView.displayMenu();
            String command = inputHandler.getStringInput("Enter command", Validator::validateCommand);

            switch (command.toLowerCase()) {
                case "add" -> addProduct();
                case "view" -> viewProductDetails();
                case "update" -> updateProduct();
                case "delete" -> deleteProduct();
                case "search" -> searchHandler.searchEngine();
                case "list" -> listAllProducts();
                case "save" -> saveChanges();
                case "exit" -> {
                    Prints.exitMessage();
                    return;
                }
                default -> Prints.invalidCommandMessage();
            }
            MenuView.displaySeparator();
        }
    }

    private void saveChanges() {
        try {
            productService.saveChanges();
            System.out.println("Changes saved successfully.");
        } catch (Exception e) {
            handleException("An error occurred while saving changes: " + e.getMessage());
        }
    }

    private void addProduct() {
        try {
            System.out.println("Enter product details:");
            Product newProduct = objectCreator.createProductFromUserInput();
            productService.add(newProduct);
            ProductView.displayProductDetails(newProduct);
            System.out.println("Product added successfully. Product ID: " + newProduct.getId());
        } catch (Exception e) {
            handleException("An error occurred while adding the product: " + e.getMessage());
        }
    }
    private void updateProduct() {
        try {
            int productId = inputHandler.getIntInput("Enter product ID", Validator::validateProductId);
            Product productToUpdate = productService.getProductById(productId);
            if (productToUpdate == null) {
                System.out.println("Product not found with the provided ID.");
                return;
            }
            ProductView.displayProductDetails(productToUpdate);
            System.out.println("Enter updated product details:");
            Product updatedProduct = objectCreator.createProductFromUserInput();
            productService.updateProduct(updatedProduct);
            System.out.println("Product updated successfully. Product ID: " + productId);
        } catch (Exception e) {
            handleException("An error occurred while updating the product: " + e.getMessage());
        }
    }
    private void deleteProduct() {
        int id = inputHandler.getIntInput("Enter product ID", Validator::validateProductId);
        Product product = productService.getProductById(id);

        if (product == null) {
            System.out.println("Product with ID " + id + " does not exist.");
        } else {
            ProductView.displayProductDetails(product);
            productService.deleteProduct(id);
            System.out.println("Product deleted successfully. Product ID: " + id);
        }
    }

    private void listAllProducts() {
        productService.listAllProducts();
    }
    private void viewProductDetails() {
        int productId = inputHandler.getIntInput("Enter product ID", Validator::validateProductId);
        Product product = productService.getProductById(productId);
        ProductView.displayProductDetails(product);
    }
    private void handleException(String errorMessage) {
        System.out.println(errorMessage);
    }
}
