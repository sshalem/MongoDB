package com.example.DBDAO;

import java.util.Collection;
import java.util.List;
import com.example.common.CouponType;
import com.example.entities.Coupon;

public interface CouponDAO {

	void createCoupon(Coupon coupon);

	void removeCoupon(Coupon coupon);

	void updateCoupon(Coupon coupon);

	Coupon getCoupon(long id);

	Collection<Coupon> getAllCoupon();

	Collection<Coupon> getCouponByType(CouponType couponType);	
	
	Coupon getCouponByTitle(String title);
	
	List<Coupon> findByPrice(double price);	

	
}
