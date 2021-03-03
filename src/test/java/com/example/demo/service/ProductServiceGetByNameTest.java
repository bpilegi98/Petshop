package com.example.demo.service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class ProductServiceGetByNameTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    Product product;

    @BeforeEach
    public void setUp()
    {
        openMocks(this);
    }

    @Test
    public void getByNameOkTest() throws PetshopNotExistsException {
        product = new Product(1, "Hueso", 100, 200, 20);
        when(productRepository.findByName("Hueso")).thenReturn(product);
        Product productResult = productService.getByName("Hueso");
        assertEquals(product, productResult);
        verify(productRepository, times(1)).findByName("Hueso");
    }

    @Test
    public void getByNameNotExistsExceptionTest()
    {
        assertThrows(PetshopNotExistsException.class, () -> productService.getByName("Hueso"));
    }
}
