package com.warehouse.utility;

import com.warehouse.dataaccess.InventoryRepository;
import com.warehouse.entity.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadFile {
    public static void loadInventoryData(InventoryRepository inventoryRepository) {
        String filePath = "src/main/resources/inventory.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine();

            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;

                String[] fields = line.split(";");
                if (fields.length != 8) {
                    System.out.println("Skipping line " + lineNumber + ": Invalid data format in the inventory file: " + line);
                    continue;
                }

                try {
                    int id = Integer.parseInt(fields[0]);
                    String name = fields[1];
                    String category = fields[2];
                    double price = Double.parseDouble(fields[3]);
                    int quantity = Integer.parseInt(fields[4]);
                    String manufacturer = fields[5];
                    double weight = Double.parseDouble(fields[6]);
                    String dimensions = fields[7];

                    Product product = new Product(id, name, category, price, quantity, manufacturer, weight, dimensions);
                    inventoryRepository.addProduct(product);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line " + lineNumber + ": Invalid numeric value in the inventory file: " + line);
                }
            }

            System.out.println("Inventory data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while loading inventory data: " + e.getMessage());
        }
    }
}
