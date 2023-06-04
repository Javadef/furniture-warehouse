package com.warehouse.controller;

import com.warehouse.businesslogic.ProductService;
import com.warehouse.businesslogic.implementation.ProductServiceImpl;
import com.warehouse.dataaccess.InventoryRepository;
import com.warehouse.dataaccess.implementation.InventoryRepositoryImpl;
import com.warehouse.presentation.MenuView;
import com.warehouse.presentation.InputHandler;

public class MainController {
    private final ProductService productService;
    private final MenuView menuView;
    private final InputHandler inputHandler;

    public MainController() {
        InventoryRepository inventoryRepository = new InventoryRepositoryImpl();
        productService = new ProductServiceImpl(inventoryRepository);
        menuView = new MenuView();
        inputHandler = new InputHandler();
    }

    public void start() {
        displayApplicationInfo();

        while (true) {
            menuView.displayMenu();
            String command = inputHandler.getCommand();

            switch (command.toLowerCase()) {
                case "add" -> addProduct();
                case "view" -> viewProductDetails();
                case "update" -> updateProduct();
                case "delete" -> deleteProduct();
                case "search" -> searchProductsByParameter();
                case "list" -> listAllProducts();
                case "exit" -> {
                    System.out.println("Exiting the application...");
                    return;
                }
                default -> System.out.println("Invalid command. Please try again.");
            }

            System.out.println("---------------------------------------------");
        }
    }

    private void displayApplicationInfo() {
        System.out.println("Warehouse Search System v1.0 (Created on 2023-06-04)");
        System.out.println("Developer: John Doe");
        System.out.println("Email: john.doe@example.com");
        System.out.println("---------------------------------------------");
    }

    private void addProduct() {
        String name = inputHandler.getStringInput("Enter product name");
        String category = inputHandler.getStringInput("Enter product category");
        double price = inputHandler.getDoubleInput("Enter product price");
        int quantity = inputHandler.getIntInput("Enter product quantity");

        productService.addProduct(name, category, price, quantity);
    }

    private void viewProductDetails() {
        String id = inputHandler.getStringInput("Enter product ID");

        productService.viewProductDetails(id);
    }

    private void updateProduct() {
        String id = inputHandler.getStringInput("Enter product ID");
        String name = inputHandler.getStringInput("Enter new product name");
        String category = inputHandler.getStringInput("Enter new product category");
        double price = inputHandler.getDoubleInput("Enter new product price");
        int quantity = inputHandler.getIntInput("Enter new product quantity");

        productService.updateProduct(id, name, category, price, quantity);
    }

    private void deleteProduct() {
        String id = inputHandler.getStringInput("Enter product ID");

        productService.deleteProduct(id);
    }

    private void searchProductsByParameter() {
        String parameter = inputHandler.getStringInput("Enter search parameter (id, name, category, price, quantity)");
        String value = inputHandler.getStringInput("Enter search value");

        productService.searchProductsByParameter(parameter, value);
    }

    private void listAllProducts() {
        productService.listAllProducts();
    }

    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.start();
    }
}
