package com.warehouse.service.implementation;
import com.warehouse.dataaccess.InventoryRepository;
import com.warehouse.entity.Product;
import com.warehouse.presentation.ProductView;
import com.warehouse.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final InventoryRepository inventoryRepository;

    public ProductServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void add(Product newProduct) {
        inventoryRepository.add(newProduct);
    }

    @Override
    public void saveChanges() {
        inventoryRepository.saveChanges();
    }

    @Override
    public void deleteProduct(int id) {
        inventoryRepository.removeProduct(id);
    }

    @Override
    public List<Product> listAllProducts() {
        List<Product> products = inventoryRepository.getAllProducts();
        ProductView.displayAllProducts(products.toArray(new Product[0]));
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        return inventoryRepository.getProductById(productId);
    }

    @Override
    public void updateProduct(Product updatedProduct) {
        inventoryRepository.updateProduct(updatedProduct);
    }

    @Override
    public int getLastId() {
        return inventoryRepository.getLastId();
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return inventoryRepository.searchProductsByName(name);
    }

    @Override
    public List<Product> searchProductsByCategory(String category) {
        return inventoryRepository.searchProductsByCategory(category);
    }

    @Override
    public List<Product> searchProductsByPriceRange(double minValue, double maxValue) {
        return inventoryRepository.searchProductsByPriceRange(minValue, maxValue);
    }

    @Override
    public List<Product> searchProductsByQuantity(int quantity) {
        return inventoryRepository.searchProductsByQuantity(quantity);
    }

    @Override
    public List<Product> searchProductsByManufacturer(String manufacturer) {
        return inventoryRepository.searchProductsByManufacturer(manufacturer);
    }

    @Override
    public List<Product> searchProductsByWeight(double weight) {
        return inventoryRepository.searchProductsByWeight(weight);
    }

    @Override
    public List<Product> searchProductsByDimensions(String dimensions) {
        return inventoryRepository.searchProductsByDimensions(dimensions);
    }
}
