package com.mongoDB.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongoDB.collection.Company;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String>{

	Company findByCompaName(String name);
	
	
}
