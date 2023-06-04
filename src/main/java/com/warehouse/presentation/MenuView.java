package com.warehouse.presentation;

public class MenuView {
    public void displayMenu() {
        printHeader();
        printMenuItems();
        printFooter();
    }

    private void printHeader() {
        System.out.println("=================================");
        System.out.println("         Warehouse Menu          ");
        System.out.println("=================================");
    }

    private void printMenuItems() {
        System.out.println("Available Commands:");
        System.out.println(" - add:    Add a new product to the warehouse");
        System.out.println(" - view:   View details of a specific product");
        System.out.println(" - update: Update information of an existing product");
        System.out.println(" - delete: Delete a product from the warehouse");
        System.out.println(" - search: Search for products based on different parameters");
        System.out.println(" - list:   List all products in the warehouse");
        System.out.println(" - exit:   Exit the application");
    }

    public void displayApplicationInfo() {
        System.out.println("Warehouse Search System v1.0 (Created on 2023-06-04)");
        System.out.println("Developer: Java");
        System.out.println("Email: criff913@gmail.com");
    }
    private void printFooter() {
        System.out.println("=================================");
    }
}
