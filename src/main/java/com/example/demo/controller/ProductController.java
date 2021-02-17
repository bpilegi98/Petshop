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

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/")
    public ResponseEntity<String> addProduct(@RequestBody Product newProduct) throws PetshopAlreadyExistsException {
        Product product = productService.addProduct(newProduct);
        return (isNull(product)) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't add that product.") :
                ResponseEntity.status(HttpStatus.CREATED).body("The product was created successfully");
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAll(@RequestParam(required = false) String name)
    {
        if(isNull(name))
        {
            return ResponseEntity.ok(productService.getAll(null));
        }
        return ResponseEntity.ok(productService.getAll(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) throws PetshopNotExistsException {
        Product product = productService.deleteProduct(id);
        return (isNull(product)) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't delete that product.") :
                ResponseEntity.status(HttpStatus.OK).body("The product has been deleted successfully.");
    }

    @PutMapping("/stock/{id}{stock}")
    public ResponseEntity<String> setProductStock(@RequestParam int id, @RequestParam int stock) throws PetshopNotExistsException {
        Product product = productService.setProductStock(id, stock);
        return (isNull(product)) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't update that product.") :
                ResponseEntity.status(HttpStatus.OK).body("The stock has been updated successfully.");
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<ProductStock> getProductStock(@PathVariable int id) throws PetshopNotExistsException {
        return ResponseEntity.ok(productService.getProductStock(id));
    }
}
