package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.projections.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "DELETE * FROM products WHERE id = ?1", nativeQuery = true)
    Product delete(int id);

    @Query(value = "UPDATE products SET stock = ?2 WHERE id = ?1", nativeQuery = true)
    Product setProductStock(int id, int stock);

    @Query(value = "SELECT p.name, p.stock FROM products WHERE id = ?1", nativeQuery = true)
    ProductStock getProductStock(int id);
}
