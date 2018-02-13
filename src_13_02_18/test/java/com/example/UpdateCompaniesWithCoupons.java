package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.common.ClientType;
import com.example.common.CouponType;
import com.example.entities.Coupon;
import com.example.entry.CouponSystem;
import com.example.facade.CompanyFacade;

@Component
public class UpdateCompaniesWithCoupons {

	@Autowired
	CouponSystem couponSystem;	
	

	public void createCoupons() {
		
		/*
		 *  CoffeeCoffee  coupons
		 */
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe", "123123", ClientType.COMPANY);

//		Date startd1 = new Date(2018 - 1900, 0, 27);
//		Date startd2 = new Date(2018 - 1900, 2, 1);
//		Date startd3 = new Date(2018 - 1900, 2, 5);
//		Date startd4 = new Date(2018 - 1900, 3, 1);
//		Date startd5 = new Date(2018 - 1900, 4, 5);
//		
//		Date endD1 = new Date(2018 - 1900, 5, 30);
//		Date endD2 = new Date(2018 - 1900, 4, 30);
//		Date endD3 = new Date(2018 - 1900, 11, 31);
//		Date endD4 = new Date(2018 - 1900, 7, 31);
//		Date endD5 = new Date(2018 - 1900, 10, 30);
		
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        String curDateToStr = format.format(d1);
        
        
		/*
		 *  CoffeeCoffee  coupons
		 */

		CouponType couponType = null;

		Coupon cfcfBreakfastforOne = new Coupon("CoffeeCoffee Breakfast", null, null, 10, couponType.RESTURANTS,
				"Breakfast for one person", 65, null, null);
		companyFacade.createCoupon(cfcfBreakfastforOne);
		Coupon cfcfBreakfastForTwo = new Coupon("CoffeeCoffee lunch", null, null, 10, couponType.RESTURANTS, "lunch for two persons",
				105, null, null);
		companyFacade.createCoupon(cfcfBreakfastForTwo);
		Coupon cfcfDinner = new Coupon("CoffeeCoffee dinner", null, null, 10, couponType.RESTURANTS, "Dinner for two", 125, null,
				null);
		companyFacade.createCoupon(cfcfDinner);
		
		Coupon cfcfUisraeliBreakfast = new Coupon("CoffeeCoffee israeli Breakfast", null, null, 10, couponType.RESTURANTS, "Dinner for two", 125, null,
				null);
		companyFacade.createCoupon(cfcfUisraeliBreakfast);
		
		/*
		 *  AROMA  coupons
		 */

		CompanyFacade companyFacade1 = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		
		Coupon aromaBreakfastforOne = new Coupon("aroma", null, null, 10, CouponType.RESTURANTS,
				"Aroma Brekfast for one person", 69, null, null);
		companyFacade1.createCoupon(aromaBreakfastforOne);
		Coupon aromaBreakfastForTwo = new Coupon("Aroma breakfast for 2", null, null, 10, CouponType.RESTURANTS,
				"Aroma Brekfast for two ", 129, null, null);
		companyFacade1.createCoupon(aromaBreakfastForTwo);
		Coupon aromaDinner = new Coupon("Aroma Dinner", null, null, 10, CouponType.RESTURANTS, "Aroma dinner", 229,
				null, null);
		companyFacade1.createCoupon(aromaDinner);
		Coupon aromafamilyDinner = new Coupon("Aroma Family", null, null, 10, CouponType.RESTURANTS,
				"Aroma dinner for family", 399, null, null);
		companyFacade1.createCoupon(aromafamilyDinner);
		Coupon aromaExpress = new Coupon("Aroma express", null, null, 10, CouponType.RESTURANTS, "Aroma express", 159,
				null, null);
		companyFacade1.createCoupon(aromaExpress);


		
		
		
		
	}

}
