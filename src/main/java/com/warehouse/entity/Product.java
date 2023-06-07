package com.warehouse.entity;

public class Product {
    private final int id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private String manufacturer;
    private double weight;
    private String dimensions;

    public Product(int id, String name, String category, double price, int quantity,
                   String manufacturer, double weight, String dimensions) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.weight = weight;
        this.dimensions = dimensions;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + "\n"
                + "Name: " + name + "\n"
                + "Category: " + category + "\n"
                + "Price: " + price + "\n"
                + "Quantity: " + quantity + "\n"
                + "Manufacturer: " + manufacturer + "\n"
                + "Weight: " + weight + "\n"
                + "Dimensions: " + dimensions + "\n";
    }
}
