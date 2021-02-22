package service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductServiceGetAllTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    List<Product> productList;

    @BeforeEach
    public void setUp()
    {
        initMocks(this);
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


    //Test getByNameNotExistsTest() y getByNameOkTest()
}
