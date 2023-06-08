package com.warehouse.utility;

import java.util.Scanner;
import java.util.function.Function;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public String getStringInput(String fieldName, Function<String, Boolean> validator) {
        while (true) {
            System.out.print(fieldName + ": ");
            String input = scanner.nextLine().trim();
            if (validator.apply(input)) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter a valid value.");
            }
        }
    }

    public double getDoubleInput(String fieldName, Function<Double, Boolean> validator) {
        while (true) {
            System.out.print(fieldName + ": ");
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (validator.apply(value)) {
                    return value;
                } else {
                    System.out.println("Invalid input. Please enter a valid value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    public int getIntInput(String fieldName, Function<Integer, Boolean> validator) {
        while (true) {
            System.out.print(fieldName + ": ");
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (validator.apply(value)) {
                    return value;
                } else {
                    System.out.println("Invalid input. Please enter a valid value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer number.");
            }
        }
    }
}
