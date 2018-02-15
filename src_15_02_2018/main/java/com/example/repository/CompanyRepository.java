package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {

	Company findById(long id);

	Company findByCompName(String compname);

}
