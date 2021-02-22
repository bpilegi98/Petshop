package service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductServiceDeleteTest {

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

    @Test
    public void deleteProductOkTest() throws PetshopNotExistsException {
        product = new Product(1, "Hueso", 100, 200, 20);
        when(productRepository.existsById(1)).thenReturn(true);
        when(productRepository.findById(1)).thenReturn(Optional.ofNullable(product));
        when(productRepository.delete(1)).thenReturn(product);
        Product productResult = productService.deleteProduct(1);
        assertEquals(product, productResult);
        verify(productRepository, times(1)).delete(1);
    }

    @Test
    public void deleteProductNotExistsExceptionTest()
    {
        assertThrows(PetshopNotExistsException.class, () -> productService.deleteProduct(1));
    }
}
