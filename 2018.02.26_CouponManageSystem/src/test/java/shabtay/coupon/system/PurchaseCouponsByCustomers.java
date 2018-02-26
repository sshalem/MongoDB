package shabtay.coupon.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shabtay.coupon.system.DBDAO.CouponDBDAO;
import shabtay.coupon.system.common.ClientType;
import shabtay.coupon.system.entities.Coupon;
import shabtay.coupon.system.entry.CouponSystem;
import shabtay.coupon.system.exceptions.AmountOfCouponsZeroException;
import shabtay.coupon.system.exceptions.CouponAlreadyPurchsedByCustomerException;
import shabtay.coupon.system.exceptions.CouponExpiredException;
import shabtay.coupon.system.exceptions.WrongLoginInputException;
import shabtay.coupon.system.facade.CustomerFacade;

@Component
public class PurchaseCouponsByCustomers {

	@Autowired
	CouponDBDAO couponDBDAO;
	@Autowired
	CouponSystem couponSystem;

	public void purchaseCoupons() {

		try {
			CustomerFacade customerFacade1 = (CustomerFacade) couponSystem.login("karin", "2154459",
					ClientType.CUSTOMER);
			Coupon purchase_coffeeCoffee_israeli_Breakfast = couponDBDAO.getCouponByTitle("CoffeeCoffee israeli Breakfast");
			customerFacade1.purchaseCoupon(purchase_coffeeCoffee_israeli_Breakfast);

			CustomerFacade customerFacade2 = (CustomerFacade) couponSystem.login("shabtay", "121212",
					ClientType.CUSTOMER);
			Coupon aroma_Dinner = couponDBDAO.getCouponByTitle("Aroma Dinner");
			customerFacade2.purchaseCoupon(aroma_Dinner);

			CustomerFacade customerFacade3 = (CustomerFacade) couponSystem.login("avigail", "4567897",
					ClientType.CUSTOMER);
			Coupon pc3 = couponDBDAO.getCouponByTitle("CoffeeCoffee israeli Breakfast");
			customerFacade3.purchaseCoupon(pc3);

			CustomerFacade customerFacade4 = (CustomerFacade) couponSystem.login("karin", "2154459",
					ClientType.CUSTOMER);
			Coupon pc4 = couponDBDAO.getCouponByTitle("CoffeeCoffee Breakfast");
			customerFacade4.purchaseCoupon(pc4);

			CustomerFacade customerFacade5 = (CustomerFacade) couponSystem.login("karin", "2154459",
					ClientType.CUSTOMER);
			Coupon pc5 = couponDBDAO.getCouponByTitle("CoffeeCoffee lunch");
			customerFacade5.purchaseCoupon(pc5);

			CustomerFacade customerFacade6 = (CustomerFacade) couponSystem.login("karin", "2154459",
					ClientType.CUSTOMER);
			Coupon pc6 = couponDBDAO.getCouponByTitle("Aroma Dinner");
			customerFacade6.purchaseCoupon(pc6);

			CustomerFacade customerFacade7 = (CustomerFacade) couponSystem.login("ariel", "784595",
					ClientType.CUSTOMER);
			Coupon pc7 = couponDBDAO.getCouponByTitle("Aroma Family");
			customerFacade7.purchaseCoupon(pc7);

			CustomerFacade customerFacade8 = (CustomerFacade) couponSystem.login("odel", "1200034",
					ClientType.CUSTOMER);
			Coupon pc8 = couponDBDAO.getCouponByTitle("Aroma Dinner");
			customerFacade8.purchaseCoupon(pc8);

			CustomerFacade customerFacade9 = (CustomerFacade) couponSystem.login("odel", "1200034",
					ClientType.CUSTOMER);
			Coupon pc9 = couponDBDAO.getCouponByTitle("CoffeeCoffee Breakfast");
			customerFacade9.purchaseCoupon(pc9);

			CustomerFacade customerFacade10 = (CustomerFacade) couponSystem.login("avigail", "4567897",
					ClientType.CUSTOMER);
			Coupon pc10 = couponDBDAO.getCouponByTitle("aroma");
			customerFacade10.purchaseCoupon(pc10);

			CustomerFacade customerFacade11 = (CustomerFacade) couponSystem.login("karin", "2154459",
					ClientType.CUSTOMER);
			Coupon pc11 = couponDBDAO.getCouponByTitle("shufersal vegtables");
			customerFacade11.purchaseCoupon(pc11);

			CustomerFacade customerFacade12 = (CustomerFacade) couponSystem.login("karin", "2154459",
					ClientType.CUSTOMER);
			Coupon pc12 = couponDBDAO.getCouponByTitle("shufersal spageties");
			customerFacade12.purchaseCoupon(pc12);

		} catch (CouponAlreadyPurchsedByCustomerException | AmountOfCouponsZeroException | CouponExpiredException
				| WrongLoginInputException e) {
			e.printStackTrace();
			System.out.println(" check Purchase Coupon by Customers for abnormality ");
		}

	}

}
