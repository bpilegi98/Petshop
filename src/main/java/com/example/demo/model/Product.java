package com.example.demo.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private int id;

    @NotNull
    private String name;
    private float cost;
    private float price;
    private int stock;
}
