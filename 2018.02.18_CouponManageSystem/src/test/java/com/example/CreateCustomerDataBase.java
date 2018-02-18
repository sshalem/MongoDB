package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.common.ClientType;
import com.example.entities.Customer;
import com.example.entry.CouponSystem;
import com.example.facade.AdminFacade;

@Component
public class CreateCustomerDataBase {
	
	@Autowired
	CouponSystem couponSystem;
	public void createDataBase() {

		// Login via CouponSystem (AdminFacde)
		
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		
		/*
		 * Load Customers to DB
		 */
		Customer shabtay = new Customer("shabtay", "121212");
		adminFacade.createCustomer(shabtay);

		Customer karin = new Customer("karin", "2154459");
		adminFacade.createCustomer(karin);

		Customer avigail = new Customer("avigail", "4567897");
		adminFacade.createCustomer(avigail);

		Customer ariel = new Customer("ariel", "784595");
		adminFacade.createCustomer(ariel);

		Customer odel = new Customer("odel", "1200034");
		adminFacade.createCustomer(odel);

	}


}
