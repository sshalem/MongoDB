package shabtay.coupon.system.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import shabtay.coupon.system.common.ClientType;
import shabtay.coupon.system.exceptions.WrongLoginInputException;
import shabtay.coupon.system.facade.AdminFacade;
import shabtay.coupon.system.facade.CompanyFacade;
import shabtay.coupon.system.facade.CouponClientFacade;
import shabtay.coupon.system.facade.CustomerFacade;

/**
 *  Class CouponSystem is SingleTon
 *  Allows entry for nominees and execute
 *  actions according their ID
 *  
 * @author Shabtay Shalem
 * 
 */

@Component
@Scope("singleton")
public class CouponSystem {

	@Autowired
	AdminFacade adminFacade;

	@Autowired
	CompanyFacade companyFacade;

	@Autowired
	CustomerFacade customerFacade;

	/**
	 * 
	 * @param name  enter the user name
	 * @param password  ender the user name password
	 * @param type  user type : ADMIN, COMPANY, CUSTOMER
	 * @return CouponClientFacade or null;
	 * @throws WrongLoginInputException  Exception is thrown in case Login input is Wrong 
	 */

	public CouponClientFacade login(String name, String password, ClientType type) throws WrongLoginInputException {

		switch (type) {
		case ADMIN:
			return adminFacade.login(name, password);
		case COMPANY:
			return companyFacade.login(name, password);
		case CUSTOMER:
			return customerFacade.login(name, password);
		}
		return null;
	}

}
