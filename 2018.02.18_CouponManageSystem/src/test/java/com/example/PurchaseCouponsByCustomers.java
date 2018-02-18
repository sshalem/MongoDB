package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.DBDAO.CouponDBDAO;
import com.example.common.ClientType;
import com.example.entities.Coupon;
import com.example.entry.CouponSystem;
import com.example.facade.CustomerFacade;

@Component
public class PurchaseCouponsByCustomers {
	
	@Autowired CouponDBDAO couponDBDAO;	
	@Autowired CouponSystem couponSystem;

	public void purchaseCoupons() {
		CustomerFacade customerFacade1 = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		Coupon pc1 = couponDBDAO.getCoupon(4);
		customerFacade1.purchaseCoupon(pc1);		

		CustomerFacade customerFacade2 = (CustomerFacade) couponSystem.login("shabtay", "121212", ClientType.CUSTOMER);
		Coupon pc2 = couponDBDAO.getCoupon(7);
		customerFacade2.purchaseCoupon(pc2);

		CustomerFacade customerFacade3 = (CustomerFacade) couponSystem.login("avigail", "4567897", ClientType.CUSTOMER);
		Coupon pc3 = couponDBDAO.getCoupon(4);
		customerFacade3.purchaseCoupon(pc3);

		CustomerFacade customerFacade4 = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		Coupon pc4 = couponDBDAO.getCoupon(1);
		customerFacade4.purchaseCoupon(pc4);

		CustomerFacade customerFacade5 = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		Coupon pc5 = couponDBDAO.getCoupon(2);
		customerFacade5.purchaseCoupon(pc5);

		CustomerFacade customerFacade6 = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		Coupon pc6 = couponDBDAO.getCoupon(7);
		customerFacade6.purchaseCoupon(pc6);

		CustomerFacade customerFacade7 = (CustomerFacade) couponSystem.login("ariel", "784595", ClientType.CUSTOMER);
		Coupon pc7 = couponDBDAO.getCoupon(8);
		customerFacade7.purchaseCoupon(pc7);

		CustomerFacade customerFacade8 = (CustomerFacade) couponSystem.login("odel", "1200034", ClientType.CUSTOMER);
		Coupon pc8 = couponDBDAO.getCoupon(7);
		customerFacade8.purchaseCoupon(pc8);

		CustomerFacade customerFacade9 = (CustomerFacade) couponSystem.login("odel", "1200034", ClientType.CUSTOMER);
		Coupon pc9 = couponDBDAO.getCoupon(1);
		customerFacade9.purchaseCoupon(pc9);

		CustomerFacade customerFacade10 = (CustomerFacade) couponSystem.login("avigail", "4567897",
				ClientType.CUSTOMER);
		Coupon pc10 = couponDBDAO.getCoupon(5);
		customerFacade10.purchaseCoupon(pc10);
		
		CustomerFacade customerFacade11 = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		Coupon pc11 = couponDBDAO.getCoupon(10);
		customerFacade11.purchaseCoupon(pc11);
		
		CustomerFacade customerFacade12 = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		Coupon pc12 = couponDBDAO.getCoupon(12);
		customerFacade12.purchaseCoupon(pc12); 

	}

	
}
