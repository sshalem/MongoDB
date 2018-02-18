package com.example.facade;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.DBDAO.CouponDBDAO;
import com.example.DBDAO.CustomerDBDAO;
import com.example.common.ClientType;
import com.example.common.CouponType;
import com.example.entities.Coupon;
import com.example.entities.Customer;
import com.example.exceptions.AmountIOfCouponsZeroException;
import com.example.exceptions.CouponAlreadyPurchsedByCustomerException;
import com.example.exceptions.CouponExpiredException;

@Component
public class CustomerFacade implements CouponClientFacade {

	@Autowired CouponDBDAO couponDBDAO;
	@Autowired CustomerDBDAO customerDBDAO; 

	private long customerId;

	public CustomerFacade() {

	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		if (customerDBDAO.login(name, password) == true) {
			customerId = customerDBDAO.getCustomerByName(name).getId();
			return this;
		}
		return null;
	}

	public void purchaseCoupon(Coupon coupon) {

		Collection<Coupon> customerCoupons = getAllPurchasedCoupons();
		Customer customer = customerDBDAO.getCustomer(customerId);
		Coupon couponFromDB = couponDBDAO.getCouponByTitle(coupon.getTitle());
		
		if (couponFromDB != null) {
			if (checkIfCouponDateExpired(couponFromDB.getEndDate())) {
				throw new CouponExpiredException("...... Coupon Expired ...........");
			}
			if (couponFromDB.getAmount() == 0) {
				throw new AmountIOfCouponsZeroException(" ........ No more Coupons left ......");
			}
		}
		
		if (customerDBDAO.findCouponInCustomerDB(this.customerId, coupon.getTitle()) != null) {
			throw new CouponAlreadyPurchsedByCustomerException(" ...... Coupon already purchased by Customer ..... ");
		}
		
		customerCoupons.add(couponFromDB);
		couponFromDB.setAmount(couponFromDB.getAmount() - 1);
		couponDBDAO.updateCoupon(couponFromDB);
		customer.setCoupons(customerCoupons);
		customerDBDAO.updateCustomer(customer);
	}

	public Collection<Coupon> getAllPurchasedCoupons() {
		return customerDBDAO.getCustomer(customerId).getCoupons();
	}

	public List<Coupon> getAllPurchasedCouponsByType(CouponType couponType) {
		return customerDBDAO.findAllPurchasedCouponsType(this.customerId, couponType);
	}

	public List<Coupon> getAllPurchasedCouponsByPrice(double minimumPrice, double maximumPrice) {
		return customerDBDAO.findAllPurchasedCouponsByPrice(this.customerId, minimumPrice, maximumPrice);
	}

	public boolean checkIfCouponDateExpired(Date endDate) {
		Date currentDate = Calendar.getInstance().getTime();
		return endDate.before(currentDate);
	}

}
