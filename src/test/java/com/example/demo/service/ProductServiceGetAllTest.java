package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class ProductServiceGetAllTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    List<Product> productList;

    @BeforeEach
    public void setUp()
    {
        openMocks(this);
    }

    @Test
    public void getAllOkTest()
    {
        productList = Collections.emptyList();
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> productListResult = productService.getAll();
        verify(productRepository, times(1)).findAll();
        assertEquals(productList, productListResult);
    }

}
