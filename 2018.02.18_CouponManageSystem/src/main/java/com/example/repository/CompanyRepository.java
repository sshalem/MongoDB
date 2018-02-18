package com.example.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.common.CouponType;
import com.example.entities.Company;
import com.example.entities.Coupon;

public interface CompanyRepository extends CrudRepository<Company, Long> {

	Company findById(long id);

	Company findByCompName(String compname);

	@Query("SELECT cp FROM COMPANY comp JOIN comp.coupons AS cp WHERE comp.id=:id AND cp.type=:couponType")
	List<Coupon> findCompanyCouponByType(@Param("id") long compId, @Param("couponType") CouponType type);

	@Query("SELECT cp FROM COMPANY comp JOIN comp.coupons AS cp WHERE comp.id=:id AND cp.price>=:minPrice AND cp.price<=:maxPrice")
	List<Coupon> findCompanyCouponByPrice(@Param("id") long compId, @Param("minPrice") double minimumPrice,	@Param("maxPrice") double maximumPrice);

	@Query("SELECT cp FROM COMPANY comp JOIN comp.coupons AS cp WHERE comp.id=:id AND cp.startDate>=:startDate AND cp.endDate<=:endDate")
	List<Coupon> findCouponBetweenDates(@Param("id") long compId, @Param("startDate") Date startingDate, @Param("endDate") Date endingDate);

}
