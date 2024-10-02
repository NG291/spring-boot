package com.demospringboot.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
}
