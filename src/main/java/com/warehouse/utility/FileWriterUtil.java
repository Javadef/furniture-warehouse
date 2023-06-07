package com.warehouse.utility;

import com.warehouse.entity.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileWriterUtil {

    public static void writeInventoryToFile(Map<Integer, Product> inventory) {
        String filePath = "src/main/resources/inventory.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the header line
            writer.write("ID;Name;Category;Price;Quantity;Manufacturer;Weight;Dimensions");
            writer.newLine();

            // Write each product as a line in the file
            for (Product product : inventory.values()) {
                writer.write(product.getId() + ";" + product.getName() + ";" + product.getCategory() + ";" +
                        product.getPrice() + ";" + product.getQuantity() + ";" + product.getManufacturer() + ";" +
                        product.getWeight() + ";" + product.getDimensions());
                writer.newLine();
            }

            System.out.println("Inventory data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving inventory data: " + e.getMessage());
        }
    }

}

