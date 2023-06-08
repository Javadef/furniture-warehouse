package com.warehouse.controller;

import com.warehouse.dataaccess.InventoryRepository;
import com.warehouse.entity.Product;
import com.warehouse.service.ProductService;
import com.warehouse.service.implementation.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {
    @Mock
    private ProductService productService;
    @Mock
    private InventoryRepository inventoryRepository;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(inventoryRepository);

    }

    @Test
    void shouldAddProduct() {

        Product mockProduct = new Product(1, "Chair", "Furniture", 49.99, 10, "ABC Furniture", 15.2, "25x25x409");



        inventoryRepository.add(mockProduct);

        verify(inventoryRepository, times(1)).add(mockProduct);
    }

    @Test
    void shouldUpdateProduct() {

        Product mockProduct = new Product(1, "Chair", "Furniture", 49.99, 10, "ABC Furniture", 15.2, "25x25x409");


        productService.updateProduct(mockProduct);

        verify(inventoryRepository, times(1)).updateProduct(mockProduct);
    }



    @Test
    void shouldDeleteProduct() {
        int productId = 1;
        productService.deleteProduct(productId);

        verify(inventoryRepository, times(1)).removeProduct(productId);
    }

    @Test
    void shouldViewProduct() {

        int productId = 1;

        inventoryRepository.getProductById(productId);
        verify(inventoryRepository, times(1)).getProductById(productId);
    }
    @Test

    void shouldListAllProduct() {
        productService.listAllProducts();
        verify(inventoryRepository, times(1)).getAllProducts();
    }
}
