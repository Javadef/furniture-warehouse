package com.warehouse.service.implementation;

import com.warehouse.service.ProductService;
import com.warehouse.dataaccess.InventoryRepository;
import com.warehouse.entity.Product;

import java.util.Arrays;
import java.util.Comparator;

public class ProductServiceImpl implements ProductService {
    private final InventoryRepository inventoryRepository;

    public ProductServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void addProduct(String name, String category, double price, int quantity,
                           String manufacturer, double weight, String dimensions) {
        try {
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Invalid input provided. Name field cannot be empty.");
            }
            if (category.isEmpty()) {
                throw new IllegalArgumentException("Invalid input provided. Category field cannot be empty.");
            }
            if (price <= 0) {
                throw new IllegalArgumentException("Invalid input provided. Price must be greater than 0.");
            }
            if (quantity <= 0) {
                throw new IllegalArgumentException("Invalid input provided. Quantity must be greater than 0.");
            }
            if (manufacturer.isEmpty()) {
                throw new IllegalArgumentException("Invalid input provided. Manufacturer field cannot be empty.");
            }
            if (weight <= 0) {
                throw new IllegalArgumentException("Invalid input provided. Weight must be greater than 0.");
            }
            if (dimensions.isEmpty()) {
                throw new IllegalArgumentException("Invalid input provided. Dimensions field cannot be empty.");
            }

            String id = generateNextId();
            Product product = new Product(id, name, category, price, quantity, manufacturer, weight, dimensions);
            inventoryRepository.addProduct(product);
            System.out.println("Product added successfully. ID: " + id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please provide the input in the following format:");
            System.out.println("/* ID;Name;Category;Price;Quantity;Manufacturer;Weight;Dimensions */");
            System.out.println("Example: 1;Chair;Furniture;49.99;10;ABC Furniture;15.2;25x25x40");
        } catch (Exception e) {
            System.out.println("Error: Invalid input format. Please provide the input in the following format:");
            System.out.println("/* ID;Name;Category;Price;Quantity;Manufacturer;Weight;Dimensions */");
            System.out.println("Example: 1;Chair;Furniture;49.99;10;ABC Furniture;15.2;25x25x40");
        }
    }



    @Override
    public void updateProduct(String id, String name, String category, double price, int quantity,
                              String manufacturer, double weight, String dimensions) {
        Product product = inventoryRepository.getProductById(id);
        if (product != null) {
            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setManufacturer(manufacturer);
            product.setWeight(weight);
            product.setDimensions(dimensions);
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
        Product[] matchingProducts;
        switch (parameter.toLowerCase()) {
            case "id" -> matchingProducts = inventoryRepository.searchProductsById(value);
            case "name" -> matchingProducts = inventoryRepository.searchProductsByName(value);
            case "category" -> matchingProducts = inventoryRepository.searchProductsByCategory(value);
            case "manufacturer" -> matchingProducts = inventoryRepository.searchProductsByManufacturer(value);
            case "price" -> {
                // Handle price range input
                double minValue;
                double maxValue = 0;
                if (value.contains("-")) {
                    String[] range = value.split("-");
                    try {
                        minValue = Double.parseDouble(range[0].trim());
                        maxValue = Double.parseDouble(range[1].trim());
                        matchingProducts = inventoryRepository.searchProductsByPrice(minValue, maxValue);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price range format. Please provide a valid range in the format: min-max");
                        return;
                    }
                } else {
                    try {
                        double price = Double.parseDouble(value);
                        matchingProducts = inventoryRepository.searchProductsByPrice(price, maxValue);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price value. Please provide a valid price.");
                        return;
                    }
                }
            }
            case "quantity" -> matchingProducts = inventoryRepository.searchProductsByQuantity(Integer.parseInt(value));
            case "weight" -> matchingProducts = inventoryRepository.searchProductsByWeight(Double.parseDouble(value));
            case "dimensions" -> matchingProducts = inventoryRepository.searchProductsByDimensions(value);
            default -> {
                System.out.println("Invalid search parameter. Supported parameters: id, name, category, manufacturer, price, quantity, weight, dimensions.");
                return;
            }
        }

        if (matchingProducts.length > 0) {
            System.out.println("Matching Products:");
            System.out.println("ID     | Name                 | Category   | Price      | Quantity | Manufacturer              | Weight    | Dimensions");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            for (Product product : matchingProducts) {
                System.out.printf("%-6s | %-20s | %-10s | %-10.2f | %-8d | %-25s | %-10f | %-10s%n",
                        product.getId(), product.getName(), product.getCategory(),
                        product.getPrice(), product.getQuantity(), product.getManufacturer(),
                        product.getWeight(), product.getDimensions());
            }
        } else {
            System.out.println("No products found.");
        }
    }

    @Override
    public void listAllProducts() {
        Product[] allProducts = inventoryRepository.getAllProducts();
        if (allProducts.length > 0) {
            Arrays.sort(allProducts, Comparator.comparing(Product::getPrice).thenComparing(Product::getName));
            System.out.println("All Products:");
            System.out.println("ID     | Name                 | Category   | Price      | Quantity | Manufacturer              | Weight    | Dimensions");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            for (Product product : allProducts) {
                System.out.printf("%-6s | %-20s | %-10s | %-10.2f | %-8d | %-25s | %-10f | %-10s%n",
                        product.getId(), product.getName(), product.getCategory(),
                        product.getPrice(), product.getQuantity(), product.getManufacturer(),
                        product.getWeight(), product.getDimensions());
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
