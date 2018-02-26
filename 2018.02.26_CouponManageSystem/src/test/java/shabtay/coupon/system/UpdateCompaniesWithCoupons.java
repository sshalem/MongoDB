package shabtay.coupon.system;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shabtay.coupon.system.common.ClientType;
import shabtay.coupon.system.common.CouponType;
import shabtay.coupon.system.entities.Coupon;
import shabtay.coupon.system.entry.CouponSystem;
import shabtay.coupon.system.exceptions.CouponAlreadyExistInCompanyDBException;
import shabtay.coupon.system.exceptions.WrongLoginInputException;
import shabtay.coupon.system.facade.CompanyFacade;

@Component
public class UpdateCompaniesWithCoupons {

	@Autowired
	CouponSystem couponSystem;

	public void createCoupons() throws WrongLoginInputException {

		/*
		 * CoffeeCoffee coupons
		 */
		CompanyFacade cf = (CompanyFacade) couponSystem.login("CoffeCoffe", "123123", ClientType.COMPANY);

		Date sd1 = new Date(2018 - 1900, 0, 27);
		Date sd2 = new Date(2018 - 1900, 2, 1);
		Date sd3 = new Date(2018 - 1900, 2, 5);
		Date sd4 = new Date(2018 - 1900, 3, 1);
		Date sd5 = new Date(2018 - 1900, 4, 5);
		Date sd6 = new Date(2018 - 1900, 0, 1);
		Date sd7 = new Date(2018 - 1900, 2, 5);
		Date sd8 = new Date(2018 - 1900, 3, 1);
		Date sd9 = new Date(2018 - 1900, 4, 5);

		Date ed1 = new Date(2018 - 1900, 5, 30);
		Date ed2 = new Date(2018 - 1900, 4, 30);
		Date ed3 = new Date(2018 - 1900, 11, 31);
		Date ed4 = new Date(2018 - 1900, 7, 31);
		Date ed5 = new Date(2018 - 1900, 10, 30);
		Date ed6 = new Date(2018 - 1900, 6, 1);
		Date ed7 = new Date(2018 - 1900, 11, 31);
		Date ed8 = new Date(2018 - 1900, 7, 31);
		Date ed9 = new Date(2018 - 1900, 10, 30);

		// SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		// String curDateToStr = format.format(d1);

		/*
		 * CoffeeCoffee coupons
		 */

		try {

			Coupon cfone = new Coupon("CoffeeCoffee Breakfast", sd1, ed1, 10, CouponType.RESTURANTS,
					"Breakfast for one person", 65, null, null);
			cf.createCoupon(cfone);

			Coupon cftwo = new Coupon("CoffeeCoffee lunch", sd2, ed2, 10, CouponType.RESTURANTS,
					"lunch for two persons", 105, null, null);
			cf.createCoupon(cftwo);
			
			Coupon cfthree = new Coupon("CoffeeCoffee dinner", sd3, ed3, 10, CouponType.RESTURANTS, "Dinner for two",
					125, null, null);
			cf.createCoupon(cfthree);
			
			Coupon cffour = new Coupon("CoffeeCoffee israeli Breakfast", sd4, ed4, 10, CouponType.RESTURANTS,
					"Dinner for two", 125, null, null);
			cf.createCoupon(cffour);

			CompanyFacade cf1 = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);

			Coupon aroma = new Coupon("aroma", sd5, ed5, 10, CouponType.RESTURANTS, "Aroma Brekfast for one person", 69,
					null, null);
			cf1.createCoupon(aroma);
			Coupon abf = new Coupon("Aroma breakfast for 2", sd6, ed6, 10, CouponType.RESTURANTS,
					"Aroma Brekfast for two ", 129, null, null);
			cf1.createCoupon(abf);
			Coupon aromaDinner = new Coupon("Aroma Dinner", sd7, ed7, 10, CouponType.RESTURANTS, "Aroma dinner", 229,
					null, null);
			cf1.createCoupon(aromaDinner);
			Coupon ad = new Coupon("Aroma Family", sd8, ed8, 10, CouponType.RESTURANTS, "Aroma dinner for family", 399,
					null, null);
			cf1.createCoupon(ad);
			Coupon ae = new Coupon("Aroma express", sd9, ed9, 10, CouponType.RESTURANTS, "Aroma express", 159, null,
					null);
			cf1.createCoupon(ae);

			CompanyFacade cf2 = (CompanyFacade) couponSystem.login("shufersal", "sf876", ClientType.COMPANY);

			Coupon vegtables = new Coupon("shufersal vegtables", sd1, ed5, 10, CouponType.FOOD, "vegtables", 69, null,
					null);
			cf2.createCoupon(vegtables);

			Coupon fruits = new Coupon("shufersal fruits", sd2, ed6, 10, CouponType.FOOD, "fruits ", 129, null, null);
			cf2.createCoupon(fruits);

			Coupon spageties = new Coupon("shufersal spageties", sd3, ed7, 10, CouponType.FOOD, "spageties", 229, null,
					null);
			cf2.createCoupon(spageties);

		} catch (CouponAlreadyExistInCompanyDBException e) {
			e.printStackTrace();
		}
	}

}
