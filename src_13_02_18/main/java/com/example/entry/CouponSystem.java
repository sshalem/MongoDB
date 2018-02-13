package com.example.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.common.ClientType;
import com.example.facade.AdminFacade;
import com.example.facade.CompanyFacade;
import com.example.facade.CouponClientFacade;
import com.example.facade.CustomerFacade;

@Component
@Scope("singleton")
public class CouponSystem {

	@Autowired
	AdminFacade adminFacade;

	@Autowired
	CompanyFacade companyFacade;

	@Autowired
	CustomerFacade customerFacade;

	public CouponClientFacade login(String name, String password, ClientType type) {

		switch (type) {
		case ADMIN:
			return adminFacade.login(name, password, type);
		case COMPANY:
			return companyFacade.login(name, password, type);
		case CUSTOMER:
			return customerFacade.login(name, password, type);
		}
		return null;

	}

}
