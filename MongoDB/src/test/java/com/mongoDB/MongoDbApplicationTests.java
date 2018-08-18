package com.mongoDB;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongoDB.collection.Company;
import com.mongoDB.collection.Coupon;
import com.mongoDB.repository.CompanyRepository;
import com.mongoDB.repository.CouponRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MongoDbApplicationTests {

	@Autowired
	CompanyRepository companyRepo;

	@Autowired
	CouponRepository couponRepo;

	@Test
	public void contextLoads() {
	}

	// CRUD - Create, Read , Update , Delete

	@Test
	public void test01_createCompany() {
		
		couponRepo.deleteAll();
		companyRepo.deleteAll();
		
		Company comp = new Company("bistor", "bixto@gmail.com");
		companyRepo.insert(comp);
		
		Company comp1 = new Company("retuarnt", "resto@gmail.com");
		companyRepo.insert(comp1);
		
		Company comp2 = new Company("bitBubble", "bitBubble@gmail.com");
		companyRepo.insert(comp2);
	}

	// @Test
	// public void test02_getCompanyById() {
	// Company comp = companyRepo.findOne("5b7866b8b926fd09cc9e91c4");
	// System.out.println(comp);
	// }

	@Test
	public void test03_getCompanyByName() {
		System.out.println("test 03");
		Company comp = companyRepo.findByCompaName("bistor");
		System.out.println(comp);
	}

	@Test
	public void test04_updateCompany() {
		System.out.println(" test 04");
		Company comp = companyRepo.findByCompaName("bistor");
		comp.setCompEmail("test@test.com");
		companyRepo.save(comp);
		System.out.println(companyRepo.findByCompaName("bistor"));
	}

//	@Test
//	public void test05_deleteeCompany() {
//		System.out.println(" test 05");
//		companyRepo.delete("5b786b55b926fd22745c468b");
//		System.out.println(companyRepo.findAll());
//	}

	// ------------------------------------------------------------

	@Test
	public void test06_createCOuponPerCOmpanyId() {
		Company company = companyRepo.findByCompaName("bistor");

		String companyId = company.getId();
		String companyName = company.getCompaName();

		Coupon coupon = new Coupon("dinner", 50, 159.59, companyId, companyName);
		couponRepo.save(coupon);
		company.addCoupon(coupon);
		companyRepo.save(company);
		
		Coupon coupon1 = new Coupon("breakfast", 90, 200.59, companyId, companyName);
		couponRepo.save(coupon1);
		company.addCoupon(coupon1);
		companyRepo.save(company);
		
		Coupon coupon2 = new Coupon("supper", 150, 300.59, companyId, companyName);
		couponRepo.save(coupon2);
		company.addCoupon(coupon2);
		companyRepo.save(company);
		
		
		Company compRetuarnt = companyRepo.findByCompaName("retuarnt");
		
		String retuarntId = compRetuarnt.getId();
		String returantName = compRetuarnt.getCompaName();
		
		Coupon retuCoupon = new Coupon("outer", 65, 123.59, retuarntId, returantName);
		couponRepo.save(retuCoupon);
		compRetuarnt.addCoupon(retuCoupon);
		companyRepo.save(compRetuarnt);		
		
	}
	
	@Test
	public void test07_getCouponByCOmpayId() {
		List<Coupon> coupons = couponRepo.findCouponByCopmpanyId("5b78744eb926fd067c3a780e");
		System.out.println(coupons);
	}
	

	@Test
	public void test08_getCouponByCOmpayName() {
		List<Coupon> coupons = couponRepo.findCouponbyCompanName("retuarnt");
		System.out.println(coupons);
	}
	
	@Test
	public void test09_getCouponByCOmpayNameAndPriceRange() {
		List<Coupon> coupons = couponRepo.findCouponByCompanyNameAndPriceRange("bistor", 300, 2000);		
		System.out.println(coupons);
	}
	
	@Test
	public void test10_updateCoupon() {
		Coupon coupon = couponRepo.findCouponByCOmpanyNameAndCouponName("bistor", "supper");
		System.out.println(coupon);
		coupon.setAmount(200);
		couponRepo.save(coupon);
		System.out.println(couponRepo.findCouponbyCompanName("bistor"));
	}

}
