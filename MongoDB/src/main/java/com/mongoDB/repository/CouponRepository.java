package com.mongoDB.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mongoDB.collection.Coupon;
import java.lang.String;

@Repository
public interface CouponRepository extends MongoRepository<Coupon, String>{

	
	
	@Query("{ companyId : ?0 }")
	List<Coupon> findCouponByCopmpanyId(String name);
	
	@Query("{ companyName : ?0 }")
	List<Coupon> findCouponbyCompanName(String compName);

	@Query("{ companyName : ?0 , price : {$gt : ?1, $lt : ?2 } }")
	List<Coupon> findCouponByCompanyNameAndPriceRange(String compNamem ,double minPrice, double maxPrice);
	
	@Query("{ companyName : ?0 , title :?1 }")
	Coupon findCouponByCOmpanyNameAndCouponName(String compName,String coupName);
	
}
