package com.example.demo.service;

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

    public void addProduct(Product newProduct)
    {
        productRepository.save(newProduct);
    }

    public Product deleteProduct(int id)
    {
        return Optional.ofNullable(productRepository.delete(id)).orElse(null);
    }

    public Product setProductStock(int id, int stock)
    {
       return productRepository.setProductStock(id, stock);
    }

    public ProductStock getProductStock(int id)
    {
        return productRepository.getProductStock(id);
    }
}
