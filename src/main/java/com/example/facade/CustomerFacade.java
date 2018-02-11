package com.example.facade;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.DBDAO.CompanyDBDAO;
import com.example.DBDAO.CouponDBDAO;
import com.example.DBDAO.CustomerDBDAO;
import com.example.common.ClientType;
import com.example.common.CouponType;
import com.example.entities.Coupon;
import com.example.entities.Customer;
import com.example.exceptions.CouponAlreadyPurchsedByCustomerException;
import com.example.exceptions.EmptyCouponException;

@Component
public class CustomerFacade implements CouponClientFacade {

	@Autowired
	CouponDBDAO couponDBDAO;

	@Autowired
	CustomerDBDAO customerDBDAO;

	private long customerId;
	private long couponId;

	public CustomerFacade() {

	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {

		Collection<Customer> customers = customerDBDAO.getAllCustomer();

		if (clientType == ClientType.CUSTOMER) {
			if (customerDBDAO.login(name, password) == true) {
				for (Customer customer : customers) {
					if ((name.equals(customer.getCustName()))) {
						customerId = customer.getId();
						return this;
					}
				}
			}
		}
		// should make here an exception
		return null;
	}

	public void purchaseCoupon(Coupon coupon) {

		Collection<Coupon> customerCoupons = customerDBDAO.getCustomer(customerId).getCoupons();
		// get customer by Id
		Customer customer = customerDBDAO.getCustomer(customerId);

		for (Coupon c : customerCoupons) {
			if (c.getTitle().equals(coupon.getTitle())) {
				throw new CouponAlreadyPurchsedByCustomerException("Coupon already purchased by Customer .... ");
			}
		}

		// load all coupons
		Collection<Coupon> allCouponDB = customerDBDAO.getCoupons();

		for (Coupon couponFromDB : allCouponDB) {
			if (couponFromDB.getTitle().equals(coupon.getTitle())) {

				customerCoupons.add(couponFromDB);
				couponFromDB.setAmount(couponFromDB.getAmount() - 1);
				couponDBDAO.updateCoupon(couponFromDB);
				customer.setCoupons(customerCoupons);
				customerDBDAO.updateCustomer(customer);
				System.out.println(customer.getCoupons());
			}
		}

	}

	public void getAllPurchasedCoupons() {
		System.out.println(customerDBDAO.getCustomer(customerId).getCoupons());
	}

	public void getAllPurchasedCouponsByType(CouponType couponType) {

	}

	public void getAllPurchasedCouponsByPrice(double price) {

	}

}
