package com.warehouse.presentation;

public class MenuView {

    public static void displayMenu() {
        printHeader();
        printMenuItems();
        printFooter();
    }
    public static void displaySearchingMenu() {
        displaySearchMenu();
        printFooter();
    }
    private static void printHeader() {
        System.out.println("<=================================>");
        System.out.println("         Warehouse Menu          ");
        System.out.println("<=================================>");
    }

    private static void printMenuItems() {
        System.out.println("Available Commands:");
        System.out.println(" - add:    Add a new product to the warehouse");
        System.out.println(" - view:   View details of a specific product");
        System.out.println(" - update: Update information of an existing product");
        System.out.println(" - delete: Delete a product from the warehouse");
        System.out.println(" - search: Search for products based on different parameters");
        System.out.println(" - list:   List all products in the warehouse");
        System.out.println(" - save:   save changes to the database ");
        System.out.println(" - exit:   Exit the application");
    }

    public static void displayApplicationInfo() {
        System.out.println("Warehouse Search System v1.0 (Created on 2023-06-04)");
        System.out.println("Developer: Java");
        System.out.println("Email: criff913@gmail.com");
    }
    private static void printFooter() {
        System.out.println("<=================================>");
    }
    public static void displaySeparator() {
        System.out.println("<--------------------------------------------------------------------------------------------------------------------->");
    }

    private static void displaySearchMenu() {
        System.out.println("Available Commands for Search:");
        System.out.println(" - id:  Search by product id");
        System.out.println(" - name: Search by product name");
        System.out.println(" - category: Search by product category");
        System.out.println(" - quantity: Search by product quantity");
        System.out.println(" - price(range): Search by product price range");
        System.out.println(" - weight:  Search by product weight");
        System.out.println(" - dimensions: Search by product dimensions");
    }

}

