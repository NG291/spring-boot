package com.demospringboot.service;

import com.demospringboot.model.Customer;

import java.util.Optional;

public interface ICustomerService {
    Iterable<Customer> findAll();

    Optional<Customer> findByID(Long id);

    void save(Customer customer);

    void delete(Long id);
}
