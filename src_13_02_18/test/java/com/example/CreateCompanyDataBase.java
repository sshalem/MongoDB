package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.common.ClientType;
import com.example.entities.Company;
import com.example.entry.CouponSystem;
import com.example.facade.AdminFacade;

@Component
public class CreateCompanyDataBase {

	@Autowired
	CouponSystem couponSystem;

	public void createDataBase() {

		/*
		 * Login to CouponSystem with admin , create Companies and add them DB
		 */
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);

		/*
		 * create RESTURANTS
		 */
		Company coffeeCoffee = new Company("CoffeCoffe", "123123", "coffeecoffee@gmail.com");
		adminFacade.createCompany(coffeeCoffee);

		Company aroma = new Company("Aroma", "456456", "aroma@gmail.com");
		adminFacade.createCompany(aroma);

		Company bistro56 = new Company("Bistro56", "789789", "Bistro56@gmail.com");
		adminFacade.createCompany(bistro56);

		/*
		 * create FOOD
		 */
		Company mega = new Company("mega", "mg1245", "mega@gamil.com");
		adminFacade.createCompany(mega);

		Company shufersal = new Company("shufersal", "sf876", "shufersal@gamil.com");
		adminFacade.createCompany(shufersal);		

		Company ramiLevi = new Company("ramiLevi", "rl876", "ramiLevi@gamil.com");
		adminFacade.createCompany(ramiLevi);

		Company market = new Company("market", "mark109", "market@gamil.com");
		adminFacade.createCompany(market);


	}

}
