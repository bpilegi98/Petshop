package service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductServiceSetStockTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    Product product;

    @BeforeEach
    public void setUp()
    {
        initMocks(this);
    }

    @Disabled("No funca, ver como resolver problema")
    @Test
    public void setStockOkTest() throws PetshopNotExistsException {
        product = mock(Product.class);
        when(productRepository.setProductStock(1, 30)).thenReturn(product);
        Product productResult = productService.setProductStock(1, 30);
        verify(productRepository, times(1)).setProductStock(1, 30);
        assertEquals(product, productResult);
    }
}
