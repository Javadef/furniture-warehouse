    package com.warehouse.presentation;

    import com.warehouse.entity.Product;

    public class ProductView {
        public static void displayProductDetails(Product product) {
            if (product != null) {
                System.out.println("Product Details:");
                printHeader();
                printProduct(product);

            } else {
                System.out.println("Product not found.");
            }
        }

        public static void displayTabular(Product[] products) {
            if (products.length > 0) {
                System.out.println("Matching Products:");
                printHeader();
                for (Product product : products) {
                    printProduct(product);
                }
            } else {
                System.out.println("No products found.");
            }
        }

        public static void displayAllProducts(Product[] products) {
            if (products.length > 0) {
                System.out.println("All Products:");
                printHeader();
                for (Product product : products) {
                    printProduct(product);
                }
            } else {
                System.out.println("No products found.");
            }
        }

        private static void printHeader() {
            System.out.println("ID     | Name                 | Category   | Price      | Quantity | Manufacturer              | Weight     | Dimensions");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        }

        private static void printProduct(Product product) {
            System.out.printf("%-6s | %-20s | %-10s | %-10.2f | %-8d | %-25s | %-10f | %-10s%n",
                    product.getId(), product.getName(), product.getCategory(),
                    product.getPrice(), product.getQuantity(), product.getManufacturer(),
                    product.getWeight(), product.getDimensions());
        }
    }
