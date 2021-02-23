package com.example.demo.service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.projections.ProductStock;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductServiceGetStockTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    ProductStock productStock;

    @BeforeEach
    public void setUp()
    {
        initMocks(this);
    }


    @Test
    public void getProductStockOkTest() throws PetshopNotExistsException {

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        productStock = factory.createProjection(ProductStock.class);
        when(productRepository.getProductStock(1)).thenReturn(productStock);
        when(productRepository.existsById(1)).thenReturn(true);
        ProductStock productStockResult = productService.getProductStock(1);
        verify(productRepository, times(1)).getProductStock(1);
        assertEquals(productStock, productStockResult);
    }

}
