package com.example.demo.service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class ProductServiceSetStockTest {

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
    public void setStockOkTest() throws PetshopNotExistsException {
        product = new Product(1, "Hueso", 100, 200, 20);
        when(productRepository.setProductStock(1, 40)).thenReturn(1);
        when(productRepository.existsById(1)).thenReturn(true);
        when(productRepository.findById(1)).thenReturn(Optional.of(new Product(1, "Hueso", 100, 200, 40)));
        Optional<Product> productResult = productService.setProductStock(1, 40);
        assertEquals(40, productResult.get().getStock());
        verify(productRepository, times(1)).setProductStock(1, 40);
    }

    @Test
    public void setStockProductNotExistsExceptionTest()
    {
        assertThrows(PetshopNotExistsException.class, () -> productService.setProductStock(1, 40));
    }
}
