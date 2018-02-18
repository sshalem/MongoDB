package com.example.DBDAO;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import com.example.common.CouponType;
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
	 
	Company getCompanyByName(String companyName);
	
	List<Coupon> findCompanyCouponByType(long compId, CouponType type);
	
	List<Coupon> findCompanyCouponByPrice(long compId, double minimumPrice ,double maximumPrice);
	
	List<Coupon> findCouponBetweenDates(long compId, Date startingDate, Date endingDate);

}
