package com.warehouse.controller;

import com.warehouse.entity.Product;
import com.warehouse.presentation.MenuView;
import com.warehouse.presentation.ProductView;
import com.warehouse.service.ProductService;
import com.warehouse.utility.InputHandler;
import com.warehouse.utility.Validator;

import java.util.List;

public class SearchHandler {
    private final ProductService productService;
    private final InputHandler inputHandler;

    public SearchHandler(ProductService productService) {
        this.productService = productService;
        inputHandler = new InputHandler();

    }

    public void searchEngine() {
        MenuView.displaySearchingMenu();

        String searchParam = inputHandler.getStringInput("Enter search parameter", Validator::validateSearchParameter);

        switch (searchParam.toLowerCase()) {
            case "id" -> searchById();
            case "name" -> searchByName();
            case "category" -> searchByCategory();
            case "price" -> searchByPriceRange();
            case "quantity" -> searchByQuantity();
            case "manufacturer" -> searchByManufacturer();
            case "weight" -> searchByWeight();
            case "dimensions" -> searchByDimensions();
            default -> System.out.println("Invalid search parameter.");
        }
    }

    private void searchById() {
        int id = inputHandler.getIntInput("Enter product ID", Validator::validateProductId);
        Product product = productService.getProductById(id);

        if (product != null) {
           ProductView.displayProductDetails(product);
        } else {
            System.out.println("Product not found with the provided ID.");
        }
    }

    private void searchByName() {
        String name = inputHandler.getStringInput("Enter product name", Validator::validateName);
        List<Product> searchResults = productService.searchProductsByName(name);

        if (!searchResults.isEmpty()) {
            for (Product product : searchResults) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products found with the provided name.");
        }
    }

    private void searchByCategory() {
        String category = inputHandler.getStringInput("Enter product category", Validator::validateCategory);
        List<Product> products = productService.searchProductsByCategory(category);

        if (!products.isEmpty()) {
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products found in the provided category.");
        }
    }

    private void searchByPriceRange() {
        String priceParam = inputHandler.getStringInput("Enter price range (e.g., 100 or 100-1000)", Validator::validatePriceParameter);
        String[] rangeValues = priceParam.split("-");
        double minValue = Double.parseDouble(rangeValues[0].trim());
        double maxValue = rangeValues.length > 1 ? Double.parseDouble(rangeValues[1].trim()) : Double.MAX_VALUE;

        List<Product> products = productService.searchProductsByPriceRange(minValue, maxValue);

        if (!products.isEmpty()) {
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products found in the provided price range.");
        }
    }

    private void searchByQuantity() {
        int quantity = inputHandler.getIntInput("Enter product quantity", Validator::validateQuantity);
        List<Product> products = productService.searchProductsByQuantity(quantity);

        if (!products.isEmpty()) {
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products found with the provided quantity.");
        }
    }

    private void searchByManufacturer() {
        String manufacturer = inputHandler.getStringInput("Enter product manufacturer", Validator::validateManufacturer);
        List<Product> products = productService.searchProductsByManufacturer(manufacturer);

        if (!products.isEmpty()) {
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products found with the provided manufacturer.");
        }
    }

    private void searchByWeight() {
        double weight = inputHandler.getDoubleInput("Enter product weight", Validator::validateWeight);
        List<Product> products = productService.searchProductsByWeight(weight);

        if (!products.isEmpty()) {
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products found with the provided weight.");
        }
    }

    private void searchByDimensions() {
        String dimensions = inputHandler.getStringInput("Enter product dimensions", Validator::validateDimensions);
        List<Product> products = productService.searchProductsByDimensions(dimensions);

        if (!products.isEmpty()) {
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products found with the provided dimensions.");
        }
    }
}
