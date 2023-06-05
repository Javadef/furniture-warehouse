package com.warehouse.dataaccess.implementation;

import com.warehouse.dataaccess.InventoryRepository;
import com.warehouse.entity.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InventoryRepositoryImpl implements InventoryRepository {
    private final List<Product> inventory;
    private int nextId;

    public InventoryRepositoryImpl() {
        inventory = new ArrayList<>();
        nextId = 1;
        loadDataFromFile();
        updateNextId();
    }

    private void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/inventory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = line.split(";");
                    if (data.length == 8) {
                        String id = data[0];
                        String name = data[1];
                        String category = data[2];
                        double price = Double.parseDouble(data[3]);
                        int quantity = Integer.parseInt(data[4]);
                        String manufacturer = data[5];
                        double weight = Double.parseDouble(data[6]);
                        String dimensions = data[7];
                        Product product = new Product(id, name, category, price, quantity, manufacturer, weight, dimensions);
                        inventory.add(product);
                    } else {
                        System.out.println("Invalid data format found in inventory file. Skipping line: " + line);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numeric value found in inventory file. Skipping line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load inventory data: " + e.getMessage());
        }
    }

    private void updateNextId() {
        for (Product product : inventory) {
            int productId = Integer.parseInt(product.getId());
            nextId = Math.max(nextId, productId + 1);
        }
    }

    @Override
    public void addProduct(Product product) {
        String newId = Integer.toString(nextId++);
        product.setId(newId);
        inventory.add(product);


    }

    @Override
    public void updateProduct(Product product) {
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            if (p.getId().equals(product.getId())) {
                inventory.set(i, product);
                System.out.println("Product updated successfully.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    @Override
    public void deleteProduct(String id) {
        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            if (product.getId().equals(id)) {
                inventory.remove(i);
                System.out.println("Product deleted successfully.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    @Override
    public Product getProductById(String id) {
        for (Product product : inventory) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product[] searchProductsById(String value) {
        return searchProductsByParameter("id", value);
    }

    @Override
    public Product[] searchProductsByName(String value) {
        return searchProductsByParameter("name", value);
    }

    @Override
    public Product[] searchProductsByCategory(String value) {
        return searchProductsByParameter("category", value);
    }

    @Override
    public Product[] searchProductsByManufacturer(String value) {
        return searchProductsByParameter("manufacturer", value);
    }

    @Override
    public Product[] searchProductsByPrice(double minValue, double maxValue) {
        return searchProductsByParameter("price", String.valueOf(minValue));
    }

    @Override
    public Product[] searchProductsByQuantity(int value) {
        return searchProductsByParameter("quantity", String.valueOf(value));
    }

    @Override
    public Product[] searchProductsByWeight(double value) {
        return searchProductsByParameter("weight", String.valueOf(value));
    }

    @Override
    public Product[] searchProductsByDimensions(String value) {
        return searchProductsByParameter("dimensions", value);
    }

    private Product[] searchProductsByParameter(String parameter, String value) {
        List<Product> matchingProducts = new ArrayList<>();

        for (Product product : inventory) {
            if (matchesSearchCriteria(product, parameter, value)) {
                matchingProducts.add(product);
            }
        }

        matchingProducts.sort(Comparator.comparingDouble(Product::getPrice));

        return matchingProducts.toArray(new Product[0]);
    }

    private boolean matchesSearchCriteria(Product product, String parameter, String value) {
        return switch (parameter.toLowerCase()) {
            case "id" -> product.getId().equals(value);
            case "name" -> product.getName().equalsIgnoreCase(value);
            case "category" -> product.getCategory().equalsIgnoreCase(value);
            case "manufacturer" -> product.getManufacturer().equalsIgnoreCase(value);
            case "price" -> product.getPrice() == Double.parseDouble(value);
            case "quantity" -> product.getQuantity() == Integer.parseInt(value);
            case "weight" -> product.getWeight() == Double.parseDouble(value);
            case "dimensions" -> product.getDimensions().equalsIgnoreCase(value);
            default -> false;
        };
    }

    @Override
    public Product[] getAllProducts() {
        List<Product> sortedInventory = new ArrayList<>(inventory);
        sortedInventory.sort(Comparator.comparingDouble(Product::getPrice));

        return sortedInventory.toArray(new Product[0]);
    }
}
