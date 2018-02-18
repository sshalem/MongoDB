package com.example.DBDAO;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.common.CouponType;
import com.example.entities.Company;
import com.example.entities.Coupon;
import com.example.exceptions.WrongPasswordOrUserNameOrClientTypeException;
import com.example.repository.CompanyRepository;
import com.example.repository.CouponRepository;

@Component
public class CompanyDBDAO implements CompanyDAO {

	@Autowired
	CompanyRepository companyRepo;

	@Autowired
	CouponRepository couponRepo;

	@Override
	public boolean login(String compName, String password) {
		for (Company company : getAllCompanies()) {
			if (company.getCompName().equals(compName) && (company.getPassword().equals(password))) {
				return true;
			}
		}
		throw new WrongPasswordOrUserNameOrClientTypeException(
				" *********   worng UserName / Password / ClientType  .........");
	}

	@Override
	public void createCompany(Company company) {
		companyRepo.save(company);
	}

	@Override
	public void removeCompany(Company company) {
		companyRepo.delete(company);
	}

	@Override
	public void updateCompany(Company company) {
		companyRepo.save(company);
	}

	@Override
	public Company getCompany(long id) {
		return companyRepo.findOne(id);
	}

	@Override
	public Collection<Company> getAllCompanies() {
		return (Collection<Company>) companyRepo.findAll();
	}

	@Override
	public Collection<Coupon> getCoupons() {
		return (Collection<Coupon>) couponRepo.findAll();
	}

	@Override
	public Company getCompanyByName(String companyName) {
		return companyRepo.findByCompName(companyName);
	}

	@Override
	public List<Coupon> findCompanyCouponByPrice(long compId, double minimumPrice, double maximumPrice) {
		return companyRepo.findCompanyCouponByPrice(compId, minimumPrice, maximumPrice);
	}

	@Override
	public List<Coupon> findCompanyCouponByType(long compId, CouponType type) {
		return companyRepo.findCompanyCouponByType(compId, type);
	}

	@Override
	public List<Coupon> findCouponBetweenDates(long compId, Date startingDate, Date endingDate) {
		return companyRepo.findCouponBetweenDates(compId, startingDate, endingDate);
	}

}
