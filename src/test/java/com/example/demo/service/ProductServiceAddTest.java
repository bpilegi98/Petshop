package com.example.demo.service;

import com.example.demo.exceptions.PetshopAlreadyExistsException;
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

public class ProductServiceAddTest {

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
    public void addProductOkTest() throws PetshopAlreadyExistsException {
        product = mock(Product.class);
        when(productRepository.save(product)).thenReturn(product);
        Product productResult = productService.addProduct(product);
        verify(productRepository, times(1)).save(product);
        assertEquals(product, productResult);
    }

    @Test
    public void addProductAlreadyExistsTest()
    {
        product = new Product(1, "Alimento", 100, 200, 20);
        assertThrows(PetshopAlreadyExistsException.class, () -> productService.addProduct(product));
    }

}
