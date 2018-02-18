package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.common.CouponType;
import com.example.entities.Coupon;
import com.example.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByCustName(String custname);
	
	@Query("SELECT cp FROM CUSTOMER cust JOIN cust.coupons AS cp WHERE cust.id=:id AND cp.price>=:minPrice AND cp.price<=:maxPrice")
	List<Coupon> findAllPurchasedCouponsByPrice(@Param("id")long custId, @Param("minPrice")double minimumPrice, @Param("maxPrice")double maximumPrice);	 

	@Query("SELECT cp FROM CUSTOMER cust JOIN cust.coupons AS cp WHERE cust.id=:id AND cp.type=:couponType")
	List<Coupon> findAllPurchasedCouponsType(@Param("id")long custId, @Param("couponType")CouponType type);	 
	
	@Query("SELECT cp FROM CUSTOMER cust JOIN cust.coupons AS cp WHERE cust.id=:id AND cp.title=:title")
	Coupon findCouponInCustomerDB(@Param("id")long custId, @Param("title")String couponTitle);
}
