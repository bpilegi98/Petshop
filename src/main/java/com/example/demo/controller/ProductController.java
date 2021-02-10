package com.example.demo.controller;


import com.example.demo.exceptions.ProductAlreadyExistsException;
import com.example.demo.exceptions.ProductNotExistsException;
import com.example.demo.model.Product;
import com.example.demo.projections.ProductStock;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/")
    public ResponseEntity<String> addProduct(@RequestBody Product newProduct) throws ProductAlreadyExistsException {
        productService.addProduct(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body("The product was created successfully");
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAll()
    {
        return ResponseEntity.ok(productService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) throws ProductNotExistsException {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @PutMapping("/stock/{id}{stock}")
    public ResponseEntity<Product> setProductStock(@RequestParam int id, @RequestParam int stock) throws ProductNotExistsException {
        return ResponseEntity.ok(productService.setProductStock(id, stock));
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<ProductStock> getProductStock(@PathVariable int id) throws ProductNotExistsException {
        return ResponseEntity.ok(productService.getProductStock(id));
    }
}
