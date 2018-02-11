package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Customer;
import java.util.Collection;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	
	
}
