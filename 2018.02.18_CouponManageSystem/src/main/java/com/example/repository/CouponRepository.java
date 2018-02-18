package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Coupon;

public interface CouponRepository extends CrudRepository<Coupon, Long> {

	Coupon findByTitle(String title);

}
