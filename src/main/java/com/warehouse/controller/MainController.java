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
        menuView.displayApplicationInfo();

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

    private void addProduct() {
        try {
            String name = inputHandler.getStringInput("Enter product name");
            String category = inputHandler.getStringInput("Enter product category");
            double price = inputHandler.getDoubleInput("Enter product price");
            int quantity = inputHandler.getIntInput("Enter product quantity");
            String manufacturer = inputHandler.getStringInput("Enter product manufacturer");
            double weight = inputHandler.getDoubleInput("Enter product weight");
            String dimensions = inputHandler.getStringInput("Enter product dimensions");

            productService.addProduct(name, category, price, quantity, manufacturer, weight, dimensions);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private void viewProductDetails() {
        String id = inputHandler.getStringInput("Enter product ID");

        productService.viewProductDetails(id);
    }

    private void updateProduct() {
        try {
            String id = inputHandler.getStringInput("Enter product ID");
            String name = inputHandler.getStringInput("Enter new product name");
            String category = inputHandler.getStringInput("Enter new product category");
            double price = inputHandler.getDoubleInput("Enter new product price");
            int quantity = inputHandler.getIntInput("Enter new product quantity");
            String manufacturer = inputHandler.getStringInput("Enter new product manufacturer");
            double weight = inputHandler.getDoubleInput("Enter new product weight");
            String dimensions = inputHandler.getStringInput("Enter new product dimensions");

            productService.updateProduct(id, name, category, price, quantity, manufacturer, weight, dimensions);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private void deleteProduct() {
        String id = inputHandler.getStringInput("Enter product ID");

        productService.deleteProduct(id);
    }

    private void searchProductsByParameter() {
        String parameter = inputHandler.getStringInput("Enter search parameter ( Supported parameters: id, name, category, manufacturer, price, quantity, weight, dimensions.)");
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
