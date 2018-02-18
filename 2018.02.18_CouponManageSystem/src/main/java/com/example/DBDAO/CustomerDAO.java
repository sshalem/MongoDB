package com.example.DBDAO;

import java.util.Collection;
import java.util.List;
import com.example.common.CouponType;
import com.example.entities.Coupon;
import com.example.entities.Customer;

public interface CustomerDAO {

	void createCustomer(Customer customer);

	void removeCustomer(Customer customer);

	void updateCustomer(Customer customer);

	Customer getCustomer(long id);

	Collection<Customer> getAllCustomer();

	Collection<Coupon> getCoupons();

	boolean login(String custName, String password);

	Customer getCustomerByName(String custName);
	
	List<Coupon> findAllPurchasedCouponsByPrice(long custId, double minimumPrice, double maximumPrice);	
	
	List<Coupon> findAllPurchasedCouponsType(long custId, CouponType type);
	
	Coupon findCouponInCustomerDB(long custId, String couponTitle);

}
