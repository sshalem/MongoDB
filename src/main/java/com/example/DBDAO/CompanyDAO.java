package com.example.DBDAO;

import java.util.Collection;
import com.example.entities.Company;
import com.example.entities.Coupon;


public interface CompanyDAO {

	void createCompany(Company company);

	void removeCompany(Company company);

	void updateCompany(Company company);

	Company getCompany(long id);

	Collection<Company> getAllCompanies();

	Collection<Coupon> getCoupons();

	boolean login(String compName, String password);

}
