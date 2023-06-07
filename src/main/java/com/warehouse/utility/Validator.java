package com.warehouse.utility;


public class Validator {
    public static boolean validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name. Please enter a valid name.");
        }
        return true;
    }

    public static boolean validateCategory(String category) {
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Invalid category. Please enter a valid category.");
        }
        return true;
    }

    public static boolean validatePrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Invalid price. Please enter a positive value for the price.");
        }
        return true;
    }

    public static boolean validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Invalid quantity. Please enter a positive value for the quantity.");
        }
        return true;
    }

    public static boolean validateManufacturer(String manufacturer) {
        if (manufacturer.isEmpty()) {
            throw new IllegalArgumentException("Invalid manufacturer. Please enter a valid manufacturer.");
        }
        return true;
    }

    public static boolean validateWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Invalid weight. Please enter a positive value for the weight.");
        }
        return true;
    }

    public static boolean validateDimensions(String dimensions) {
        if (dimensions.isEmpty()) {
            throw new IllegalArgumentException("Invalid dimensions. Please enter valid dimensions.");
        }
        return true;
    }

    public static boolean validateCommand(String command) {
        if (command.isEmpty()) {
            throw new IllegalArgumentException("Invalid command. Please enter a command.");
        }
        String[] validCommands = { "add", "view", "update", "delete", "search", "list", "exit", "save", "commands" };
        for (String validCommand : validCommands) {
            if (validCommand.equalsIgnoreCase(command)) {
                return true;
            }
        }
        throw new IllegalArgumentException("Invalid command. Please enter a valid command.");
    }

    public static boolean validateProductId(int productId) {
        if (productId<= 0) {
            throw new IllegalArgumentException("Invalid ID. Please enter a value for the ID.");
        }
        return true;
    }

    public static Boolean validateSearchParameter(String s) {
        String[] validParameters = {
                "id", "name", "category", "price", "quantity", "manufacturer", "weight", "dimensions"
        };

        for (String validParam : validParameters) {
            if (validParam.equalsIgnoreCase(s)) {
                return true;
            }
        }

        return false;
    }


    public static boolean validatePriceParameter(String s) {
        if (s == null || s.isEmpty()) {
            System.out.println("Invalid price range: Empty input.");
            return false;
        }

        String[] rangeValues = s.split("-");
        if (rangeValues.length > 2) {
            System.out.println("Invalid price range: More than one '-' symbol.");
            return false;
        }

        try {
            double minValue = Double.parseDouble(rangeValues[0].trim());
            if (minValue < 0) {
                System.out.println("Invalid price range: Minimum value cannot be negative.");
                return false;
            }

            if (rangeValues.length == 2) {
                double maxValue = Double.parseDouble(rangeValues[1].trim());
                if (maxValue < 0) {
                    System.out.println("Invalid price range: Maximum value cannot be negative.");
                    return false;
                }

                if (maxValue < minValue) {
                    System.out.println("Invalid price range: Maximum value cannot be less than the minimum value.");
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid price range: Invalid numeric values.");
            return false;
        }

        return true;
    }


}


