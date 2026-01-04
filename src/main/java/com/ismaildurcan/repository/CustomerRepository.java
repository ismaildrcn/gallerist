package com.ismaildurcan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ismaildurcan.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
