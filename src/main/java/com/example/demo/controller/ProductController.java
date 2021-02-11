package com.example.demo.controller;


import com.example.demo.exceptions.PetshopAlreadyExistsException;
import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Product;
import com.example.demo.projections.ProductStock;
import com.example.demo.service.ProductService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/product")
@Log
public class ProductController {

    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/")
    public ResponseEntity<String> addProduct(@RequestBody Product newProduct) throws PetshopAlreadyExistsException {
        ResponseEntity responseEntity = null;
        try
        {
            productService.addProduct(newProduct);
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("The product was created successfully");
            log.log(Level.FINE, "Product added.");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Make sure all data is filled in.");
            log.log(Level.WARNING, "Couldn't add product.");
        }
        return responseEntity;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAll()
    {
        return ResponseEntity.ok(productService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) throws PetshopNotExistsException {
        ResponseEntity responseEntity = null;
        try {
            productService.deleteProduct(id);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body("The product has been deleted successfully.");
            log.log(Level.FINE, "Product deleted.");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include the id.");
            log.log(Level.WARNING, "Couldn't delete product.");
        }
        return responseEntity;
    }

    @PutMapping("/stock/{id}{stock}")
    public ResponseEntity<Product> setProductStock(@RequestParam int id, @RequestParam int stock) throws PetshopNotExistsException {
        ResponseEntity responseEntity = null;
        try
        {
            productService.setProductStock(id, stock);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body("The stock has been updated successfully.");
            log.log(Level.FINE, "Product updated.");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include the id and stock.");
            log.log(Level.WARNING, "Couldn't update product.");
        }
        return responseEntity;
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<ProductStock> getProductStock(@PathVariable int id) throws PetshopNotExistsException {
        ResponseEntity responseEntity = null;
        try
        {
            responseEntity = ResponseEntity.ok(productService.getProductStock(id));
            log.log(Level.FINE, "Product listed.");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include the id.");
            log.log(Level.WARNING, "Couldn't list product.");
        }
        return responseEntity;
    }
}
