package com.rapidfire.accounts.repository;

import com.rapidfire.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
