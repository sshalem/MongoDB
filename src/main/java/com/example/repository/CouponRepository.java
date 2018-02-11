package com.example.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.common.CouponType;
import com.example.entities.Coupon;
import java.lang.String;

public interface CouponRepository extends CrudRepository<Coupon, Long>{

	List<Coupon> findByType(CouponType type);
	
	Coupon findByTitle(String title);
	
	
	
	
}

