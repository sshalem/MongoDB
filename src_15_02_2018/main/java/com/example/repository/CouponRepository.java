package com.example.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.example.common.CouponType;
import com.example.entities.Coupon;

public interface CouponRepository extends CrudRepository<Coupon, Long> {

	Collection<Coupon> findByType(CouponType type); 

	Coupon findByTitle(String title);
	
	Collection<Coupon> findByPrice(double price);

}
