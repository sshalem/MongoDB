package com.example.facade;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
		if (companyDBDAO.login(name, password) == true) {
			companyId = companyDBDAO.getCompanyByName(name).getId();
			return this;
		}
		return null;
	}

	public void createCoupon(Coupon newCoupon) {

		Company company = companyDBDAO.getCompany(companyId);
		Set<Coupon> coupons = companyDBDAO.getCompany(companyId).getCoupons();

		if (couponDBDAO.getCouponByTitle(newCoupon.getTitle()) != null) {
			throw new CouponAlreadyExistInCompanyDBException(".... Coupon " + newCoupon.getTitle() + " already exist .....");
		}
		coupons.add(newCoupon);
		company.setCoupons(coupons);
		companyDBDAO.updateCompany(company);
	}

	public void removeCoupon(Coupon coupon) {
		couponDBDAO.removeCoupon(couponDBDAO.getCouponByTitle(coupon.getTitle()));
	}

	public void updateCoupon(Coupon coupon) {		
			couponDBDAO.updateCoupon(coupon);
	}

	public Coupon getCoupon(long id) {
		return couponDBDAO.getCoupon(id);
	}

	public Collection<Coupon> getAllCoupon() {
		return companyDBDAO.getCompany(companyId).getCoupons();
	}

	public List<Coupon> getCouponByType(CouponType couponType) {
		return companyDBDAO.findCompanyCouponByType(this.companyId, couponType);
	}

	public List<Coupon> getAllCouponsByPrice(double minimumPrice, double maximumPrice) {
		return companyDBDAO.findCompanyCouponByPrice(this.companyId, minimumPrice, maximumPrice);
	}

	public List<Coupon> getCouponBetweenDates(Date startingDate, Date endingDate ){
		return companyDBDAO.findCouponBetweenDates(this.companyId, startingDate, endingDate);
	}
}
