package com.example.facade;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.DBDAO.CompanyDBDAO;
import com.example.DBDAO.CouponDBDAO;
import com.example.common.ClientType;
import com.example.common.CouponType;
import com.example.entities.Company;
import com.example.entities.Coupon;
import com.example.exceptions.CouponAlreadyExistInCompanyDBException;

@Component
public class CompanyFacade implements CouponClientFacade {

	@Autowired
	CouponDBDAO couponDBDAO;

	@Autowired
	CompanyDBDAO companyDBDAO;

	private long companyId;	

	public CompanyFacade() {

	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {

		Collection<Company> companies = companyDBDAO.getAllCompanies();

		if (clientType == ClientType.COMPANY) {
			if (companyDBDAO.login(name, password) == true) {
				for (Company company : companies) {
					if ((name.equals(company.getCompName()))) {
						companyId = company.getId();
						return this;
					}
				}
			}
		}
		// should make here an exception
		return null;

	}

	public void createCoupon(Coupon newCoupon) {

		Company company = companyDBDAO.getCompany(companyId);
		Collection<Coupon> coupons = companyDBDAO.getCompany(companyId).getCoupons();	
		
		for (Coupon couponDB : coupons) {
			if (couponDB.getTitle().equals(newCoupon.getTitle())) {
				throw new CouponAlreadyExistInCompanyDBException(
						" Coupon " + "\"" + newCoupon.getTitle() + "\"" + " already exist ........... ");
			}
		}
		coupons.add(newCoupon);
		company.setCoupons(coupons);
		companyDBDAO.updateCompany(company);
	}

	public void removeCoupon(Coupon coupon) {
		
		Collection<Coupon> coupons = companyDBDAO.getCompany(companyId).getCoupons();

		for (Coupon couponDB : coupons) {
			if (couponDB.getTitle().equals(coupon.getTitle())) {
				couponDBDAO.removeCoupon(couponDB);
			}
		}
	}

	public void updateCoupon(Coupon coupon) {
		
		Collection<Coupon> coupons = companyDBDAO.getCompany(companyId).getCoupons();
		
		for (Coupon couponDB : coupons) {
			if (couponDB.getTitle().equals(coupon.getTitle())) {
				couponDBDAO.updateCoupon(coupon);				
			}
		}
	}

	public Coupon getCoupon(long id) {			
		return couponDBDAO.getCoupon(id);
	}

	public Collection<Coupon> getAllCoupon() {
		return companyDBDAO.getCompany(companyId).getCoupons();
	}

	public Collection<Coupon> getCouponByType(CouponType couponType) {
		return couponDBDAO.getCouponByType(couponType);
	}
	
//	public void findCouponsByPrice() {
//		couponDBDAO.
//	}
	
	

}
