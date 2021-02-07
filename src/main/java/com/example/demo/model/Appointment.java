package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment")
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference(value="appointment-person")
    @JoinColumn(name = "id_person")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value="appointment-pet")
    @JoinColumn(name = "id_pet")
    private Pet pet;

}
