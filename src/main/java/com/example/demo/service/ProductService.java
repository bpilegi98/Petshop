package com.example.demo.service;

import com.example.demo.exceptions.ProductAlreadyExistsException;
import com.example.demo.exceptions.ProductNotExistsException;
import com.example.demo.model.Product;
import com.example.demo.projections.ProductStock;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll()
    {
        return productRepository.findAll();
    }

    public Product addProduct(Product newProduct) throws ProductAlreadyExistsException {
        Product product = null;
        if(!productRepository.existsByName(newProduct.getName()))
        {
            productRepository.save(newProduct);
        }
        return Optional.ofNullable(product).orElseThrow(() -> new ProductAlreadyExistsException("Couldn't create, that product already exists."));
    }

    public Product deleteProduct(int id) throws ProductNotExistsException {
        Product product = null;
        if (productRepository.existsById(id)) {
            product = productRepository.delete(id);
        }
        return Optional.ofNullable(product).orElseThrow(() -> new ProductNotExistsException("Couldn't delete, that product doesn't exists"));
    }

    public Product setProductStock(int id, int stock) throws ProductNotExistsException {
        Product product = null;
        if(productRepository.existsById(id))
        {
           product = productRepository.setProductStock(id, stock);
        }
       return Optional.ofNullable(product).orElseThrow(() -> new ProductNotExistsException("Couldn't update stock, that product doesn't exists"));
    }

    public ProductStock getProductStock(int id) throws ProductNotExistsException {
        ProductStock product = null;
        if(productRepository.existsById(id))
        {
            product = productRepository.getProductStock(id);
        }
        return Optional.ofNullable(product).orElseThrow(() -> new ProductNotExistsException("Couldn't get stock, that product doesn't exists."));
    }
}
