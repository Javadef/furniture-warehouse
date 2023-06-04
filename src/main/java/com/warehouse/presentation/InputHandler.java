package com.warehouse.presentation;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public String getCommand() {
        System.out.print("Enter command: ");
        return scanner.nextLine().trim();
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public double getDoubleInput(String prompt) {
        double input = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt + ": ");
            try {
                input = Double.parseDouble(scanner.nextLine().trim());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return input;
    }

    public int getIntInput(String prompt) {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt + ": ");
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        return input;
    }
}
