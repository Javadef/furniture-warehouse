package com.warehouse.controller;

import com.warehouse.entity.Product;
import com.warehouse.presentation.MenuView;
import com.warehouse.presentation.ProductView;
import com.warehouse.service.ProductService;
import com.warehouse.utility.InputHandler;
import com.warehouse.utility.Validator;

import java.util.Collections;
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
        System.out.println("Matching Products:");
        ProductView.printHeader();
        if (product != null) {
            ProductView.displayTabular(Collections.singletonList(product).toArray(new Product[0]));
        } else {
            System.out.println("Product not found with the provided ID.");
        }
    }

    private void searchByName() {
        String name = inputHandler.getStringInput("Enter product name", Validator::validateName);
        List<Product> searchResults = productService.searchProductsByName(name);
        System.out.println("Matching Products:");
        ProductView.printHeader();
        if (!searchResults.isEmpty()) {
            for (Product product : searchResults) {
                ProductView.displayTabular(Collections.singletonList(product).toArray(new Product[0]));
            }
        } else {
            System.out.println("No products found with the provided name.");
        }
    }

    private void searchByCategory() {
        String category = inputHandler.getStringInput("Enter product category", Validator::validateCategory);
        List<Product> products = productService.searchProductsByCategory(category);
        System.out.println("Matching Products:");
        ProductView.printHeader();
        if (!products.isEmpty()) {
            for (Product product : products) {
                ProductView.displayTabular(Collections.singletonList(product).toArray(new Product[0]));
            }
        } else {
            System.out.println("No products found in the provided category.");
        }
    }

    private void searchByPriceRange() {
        String priceParam = inputHandler.getStringInput("Enter price range (e.g., 100 or 100-1000)", Validator::validatePriceParameter);
        String[] rangeValues = priceParam.split("-");

        if (rangeValues.length == 1) {
            // Case: Single value entered
            double price = Double.parseDouble(rangeValues[0].trim());
            List<Product> products = productService.searchProductsByPriceRange(price, price);
            System.out.println("Matching Products:");
            ProductView.printHeader();
            if (!products.isEmpty()) {
                for (Product product : products) {
                    ProductView.displayTabular(Collections.singletonList(product).toArray(new Product[0]));
                }
            } else {
                System.out.println("No products found with the provided price.");
            }
        } else if (rangeValues.length == 2) {
            // Case: Range entered (e.g., 100-1000)
            double minValue = Double.parseDouble(rangeValues[0].trim());
            double maxValue = Double.parseDouble(rangeValues[1].trim());
            List<Product> products = productService.searchProductsByPriceRange(minValue, maxValue);
            System.out.println("Matching Products:");
            ProductView.printHeader();
            if (!products.isEmpty()) {
                for (Product product : products) {
                    ProductView.displayTabular(Collections.singletonList(product).toArray(new Product[0]));
                }
            } else {
                System.out.println("No products found in the provided price range.");
            }
        } else {
            System.out.println("Invalid price range format. Please enter a single value or a range.");
        }
    }



    private void searchByQuantity() {
        int quantity = inputHandler.getIntInput("Enter product quantity", Validator::validateQuantity);
        List<Product> products = productService.searchProductsByQuantity(quantity);
        System.out.println("Matching Products:");
        ProductView.printHeader();
        if (!products.isEmpty()) {
            for (Product product : products) {
                ProductView.displayTabular(Collections.singletonList(product).toArray(new Product[0]));
            }
        } else {
            System.out.println("No products found with the provided quantity.");
        }
    }

    private void searchByManufacturer() {
        String manufacturer = inputHandler.getStringInput("Enter product manufacturer", Validator::validateManufacturer);
        List<Product> products = productService.searchProductsByManufacturer(manufacturer);
        System.out.println("Matching Products:");
        ProductView.printHeader();
        if (!products.isEmpty()) {
            for (Product product : products) {
                ProductView.displayTabular(Collections.singletonList(product).toArray(new Product[0]));
            }
        } else {
            System.out.println("No products found with the provided manufacturer.");
        }
    }

    private void searchByWeight() {
        double weight = inputHandler.getDoubleInput("Enter product weight", Validator::validateWeight);
        List<Product> products = productService.searchProductsByWeight(weight);
        System.out.println("Matching Products:");
        ProductView.printHeader();
        if (!products.isEmpty()) {
            for (Product product : products) {
                ProductView.displayTabular(Collections.singletonList(product).toArray(new Product[0]));
            }
        } else {
            System.out.println("No products found with the provided weight.");
        }
    }

    private void searchByDimensions() {
        String dimensions = inputHandler.getStringInput("Enter product dimensions", Validator::validateDimensions);
        List<Product> products = productService.searchProductsByDimensions(dimensions);
        System.out.println("Matching Products:");
        ProductView.printHeader();
        if (!products.isEmpty()) {
            for (Product product : products) {
                ProductView.displayTabular(Collections.singletonList(product).toArray(new Product[0]));
            }
        } else {
            System.out.println("No products found with the provided dimensions.");
        }
    }
}
