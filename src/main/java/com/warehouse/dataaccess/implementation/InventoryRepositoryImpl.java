package com.warehouse.dataaccess.implementation;

import com.warehouse.dataaccess.InventoryRepository;
import com.warehouse.entity.Product;
import com.warehouse.utility.FileWriterUtil;
import com.warehouse.utility.LoadFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryRepositoryImpl implements InventoryRepository {
    private final Map<Integer, Product> inventory;
    private int lastId;

    public InventoryRepositoryImpl() {
        this.inventory = new HashMap<>();
        this.lastId = 0;
        LoadFile.loadInventoryData(this);
    }

    @Override
    public void add(Product newProduct) {
        inventory.put(newProduct.getId(), newProduct);
    }

    @Override
    public void addProduct(Product product) {
        inventory.put(product.getId(), product);
    }

    @Override
    public void removeProduct(int productId) {
        inventory.remove(productId);
    }

    @Override
    public Product getProductById(int productId) {
        return inventory.get(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(inventory.values());
    }

    @Override
    public void updateProduct(Product updatedProduct) {
        inventory.put(updatedProduct.getId(), updatedProduct);
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : inventory.values()) {
            if (product.getName().equalsIgnoreCase(name)) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    @Override
    public List<Product> searchProductsByCategory(String category) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : inventory.values()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    @Override
    public List<Product> searchProductsByPriceRange(double minValue, double maxValue) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : inventory.values()) {
            if (product.getPrice() >= minValue && product.getPrice() <= maxValue) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    @Override
    public List<Product> searchProductsByQuantity(int quantity) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : inventory.values()) {
            if (product.getQuantity() == quantity) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    @Override
    public List<Product> searchProductsByManufacturer(String manufacturer) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : inventory.values()) {
            if (product.getManufacturer().equalsIgnoreCase(manufacturer)) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    @Override
    public List<Product> searchProductsByWeight(double weight) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : inventory.values()) {
            if (product.getWeight() == weight) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    @Override
    public List<Product> searchProductsByDimensions(String dimensions) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : inventory.values()) {
            if (product.getDimensions().equalsIgnoreCase(dimensions)) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    @Override
    public void saveChanges() {
        FileWriterUtil.writeInventoryToFile(inventory);
    }

    public int getLastId() {
        if (lastId == 0 && !inventory.isEmpty()) {
            for (Product product : inventory.values()) {
                if (product.getId() > lastId) {
                    lastId = product.getId();
                }
            }
        }
        return lastId;
    }
}
