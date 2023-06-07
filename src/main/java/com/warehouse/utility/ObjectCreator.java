package com.warehouse.utility;

import com.warehouse.entity.Product;
import com.warehouse.service.ProductService;

import java.util.List;

public class ObjectCreator {
    private final InputHandler inputHandler;
    private final ProductService productService;

    public ObjectCreator(InputHandler inputHandler, ProductService productService) {
        this.inputHandler = inputHandler;
        this.productService = productService;
    }

    public Product createProductFromUserInput() {
        String name = inputHandler.getStringInput("Name", Validator::validateName);
        String category = inputHandler.getStringInput("Category", Validator::validateCategory);
        double price = inputHandler.getDoubleInput("Price", Validator::validatePrice);
        int quantity = inputHandler.getIntInput("Quantity", Validator::validateQuantity);
        String manufacturer = inputHandler.getStringInput("Manufacturer", Validator::validateManufacturer);
        double weight = inputHandler.getDoubleInput("Weight", Validator::validateWeight);
        String dimensions = inputHandler.getStringInput("Dimensions", Validator::validateDimensions);

        int lastId = productService.getLastId();
        if (lastId == 0) {
            List<Product> products = productService.listAllProducts();
            if (!products.isEmpty()) {
                lastId = products.get(products.size() - 1).getId();
            }
        }
        lastId++;

        return new Product(lastId, name, category, price, quantity, manufacturer, weight, dimensions);
    }

}
