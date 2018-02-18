package com.example.DBDAO;

import java.util.Collection;
import com.example.entities.Coupon;

public interface CouponDAO {

	void createCoupon(Coupon coupon);

	void removeCoupon(Coupon coupon);

	void updateCoupon(Coupon coupon);

	Coupon getCoupon(long id);

	Collection<Coupon> getAllCoupon();	

	Coupon getCouponByTitle(String title);

}
