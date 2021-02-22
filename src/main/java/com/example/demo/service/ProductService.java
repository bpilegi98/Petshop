package com.example.demo.service;

import com.example.demo.exceptions.PetshopAlreadyExistsException;
import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Product;
import com.example.demo.projections.ProductStock;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

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

    public Product getByName(String name) throws PetshopNotExistsException {
        Product product = null;
        if(!isNull(name))
        {
            product = productRepository.findByName(name);
        }
        return Optional.ofNullable(product).orElseThrow(() -> new PetshopNotExistsException("Couldn't find any product with that name."));
    }

    public Product addProduct(Product newProduct) throws PetshopAlreadyExistsException
    {
        Product product = null;
        if(!productRepository.existsByName(newProduct.getName()))
        {
            product = productRepository.save(newProduct);
        }
        return Optional.ofNullable(product).orElseThrow(() -> new PetshopAlreadyExistsException("Couldn't create, that product already exists."));
    }

    public Product deleteProduct(int id) throws PetshopNotExistsException {
        Product product = null;
        if (productRepository.existsById(id)) {
            product = productRepository.delete(id);
        }
        return Optional.ofNullable(product).orElseThrow(() -> new PetshopNotExistsException("Couldn't delete, that product doesn't exists"));
    }

    public Optional<Product> setProductStock(int id, int stock) throws PetshopNotExistsException {
        Optional<Product> product = null;
        if(productRepository.existsById(id))
        {
            product = productRepository.findById(id);
            productRepository.setProductStock(id, stock);
        }
       return Optional.ofNullable(product).orElseThrow(() -> new PetshopNotExistsException("Couldn't update stock, that product doesn't exists"));
    }

    public ProductStock getProductStock(int id) throws PetshopNotExistsException {
        ProductStock product = null;
        if(productRepository.existsById(id))
        {
            product = productRepository.getProductStock(id);
        }
        return Optional.ofNullable(product).orElseThrow(() -> new PetshopNotExistsException("Couldn't get stock, that product doesn't exists."));
    }
}
