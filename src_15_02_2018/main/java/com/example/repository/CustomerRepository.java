package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByCustName(String custname);
	
	Customer findByCustNameContains(String custname);	

}
