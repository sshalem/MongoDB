package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.common.CouponType;
import com.example.entities.Coupon;
import java.util.List;

public interface CouponRepository extends CrudRepository<Coupon, Long> {

	List<Coupon> findByType(CouponType type); 

	Coupon findByTitle(String title);

}
