package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {

	List<Company> findByEmail(String email);
	
	
	
}
