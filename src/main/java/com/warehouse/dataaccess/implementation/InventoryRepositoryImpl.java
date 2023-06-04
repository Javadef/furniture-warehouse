package com.warehouse.dataaccess.implementation;

import com.warehouse.dataaccess.InventoryRepository;
import com.warehouse.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
                String[] data = line.split(",");
                if (data.length == 5) {
                    String id = data[0];
                    String name = data[1];
                    String category = data[2];
                    double price = Double.parseDouble(data[3]);
                    int quantity = Integer.parseInt(data[4]);
                    Product product = new Product(id, name, category, price, quantity);
                    inventory.add(product);
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
                break;
            }
        }
    }

    @Override
    public void deleteProduct(String id) {
        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            if (product.getId().equals(id)) {
                inventory.remove(i);
                break;
            }
        }
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
    public Product[] searchProductsByParameters(String parameter, String value) {
        List<Product> matchingProducts = new ArrayList<>();

        for (Product product : inventory) {
            if (parameter.equalsIgnoreCase("id") && product.getId().equals(value)) {
                matchingProducts.add(product);
            } else if (parameter.equalsIgnoreCase("name") && product.getName().equalsIgnoreCase(value)) {
                matchingProducts.add(product);
            } else if (parameter.equalsIgnoreCase("category") && product.getCategory().equalsIgnoreCase(value)) {
                matchingProducts.add(product);
            } else if (parameter.equalsIgnoreCase("price") && Double.toString(product.getPrice()).equals(value)) {
                matchingProducts.add(product);
            } else if (parameter.equalsIgnoreCase("quantity") && Integer.toString(product.getQuantity()).equals(value)) {
                matchingProducts.add(product);
            }
        }

        return matchingProducts.toArray(new Product[0]);
    }

    @Override
    public Product[] getAllProducts() {
        return inventory.toArray(new Product[0]);
    }
}
