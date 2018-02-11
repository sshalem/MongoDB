package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.DBDAO.CouponDBDAO;
import com.example.common.ClientType;
import com.example.common.CouponType;
import com.example.entities.Company;
import com.example.entities.Coupon;
import com.example.entities.Customer;
import com.example.entry.CouponSystem;
import com.example.facade.AdminFacade;
import com.example.facade.CompanyFacade;
import com.example.facade.CustomerFacade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	UpdateCompaniesWithCoupons updateCompaniesWithCoupons;
	@Autowired
	CreateCompanyDataBase createCompanyDataBase;
	@Autowired
	CreateCustomerDataBase createCustomerDataBase;
	
	@Autowired
	CouponDBDAO couponDBDAO;	
	
	@Autowired
	CouponSystem couponSystem;

	@Test
	public void getEntranceToCouponSystem() {

		/*
		 * Ceating the DB checks with following methods of AdminFacade: 1)
		 * createCompany() 2) createCustomer()
		 */
		createCompanyDataBase.createDataBase();
		createCustomerDataBase.createDataBase();

		/*
		 * creating/adding/updaing Coupons to Companies
		 */
		updateCompaniesWithCoupons.createCoupons();

		/*
		 * Methods below checks the ADMIN_FACADE methods according the projects sheet
		 */
		// adminFacadeCreateCompany();
		// adminFacadeRemoveCompany();
		// adminFacadeUpdateCompany();
		// adminFacadeGeCompany();
		// adminFacadeGetAllCompanies();
		// adminFacadeCreateCustomer();
		// adminFacadeRemoveCustomer();
		// adminFacadeUpdateCustomer();
		// adminFacadeGeCustomer();
		// adminFacadeGetAllCustomers();
		// adminFacadeGetAllCoupon();
		// adminFacadeDublicatedCustomerVerification();
		// adminFacadeDublicatedCompanyVerification();

		/*
		 * Methods below checks the COMPANY_FACADE methods
		 */
		// companyFacadeCreateCoupon();
		// companyFacadeRemoveCoupon();
		// companyFacadeUpdateCoupon();
		// companyFacadeGetCouponById();
		// companyFacadeGetAllCoupon();
		// companyFacadeGetCouponByType();

		CustomerFacade customerFacade1 = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		Coupon pc1 = couponDBDAO.getCoupon(4);		
		customerFacade1.purchaseCoupon(pc1);
				
		CustomerFacade customerFacade2 = (CustomerFacade) couponSystem.login("shabtay", "12121212", ClientType.CUSTOMER);
		Coupon pc2 = couponDBDAO.getCoupon(7);		
		customerFacade2.purchaseCoupon(pc2);
		
		CustomerFacade customerFacade3 = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		Coupon pc3 = couponDBDAO.getCoupon(5);		
		customerFacade1.purchaseCoupon(pc3);
		
		
		
		
		
		
		
		
		
//		CustomerFacade customerFacade1 = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
//		Coupon purchaseCoupon1 = new Coupon();
//		purchaseCoupon1.setTitle("aroma");	
//		customerFacade1.purchaseCoupon(purchaseCoupon1);
//
//		CustomerFacade customerFacade2 = (CustomerFacade) couponSystem.login("shabtay", "12121212",ClientType.CUSTOMER);
//		Coupon purchaseCoupon2 = new Coupon();
//		purchaseCoupon2.setTitle("CoffeeCoffee Breakfast");
//		customerFacade2.purchaseCoupon(purchaseCoupon2);		
//
//		
//		CustomerFacade customerFacade3 = (CustomerFacade) couponSystem.login("shabtay", "12121212",	ClientType.CUSTOMER);
//		Coupon purchaseCoupon3 = new Coupon();
//		purchaseCoupon3.setTitle("aroma");
//		customerFacade3.purchaseCoupon(purchaseCoupon3);

	}

	
	
	
	/*
	 * ---------------------- ADMIN FACADE METHODS CHECK -------------------------
	 * ---------------------- ADMIN FACADE METHODS CHECK -------------------------
	 * ---------------------- ADMIN FACADE METHODS CHECK -------------------------
	 */

	public void adminFacadeCreateCompany() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n companies Before adding company \n " + adminFacade.getAllCompanies());
		Company adidas = new Company("adidas", "adadi777", "adidas@gamil.com");
		adminFacade.createCompany(adidas);
		System.out.println("\n companies After adding company \n " + adminFacade.getAllCompanies());
	}

	public void adminFacadeRemoveCompany() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n companies Before removeing company \n " + adminFacade.getAllCompanies());
		System.out.println("\n removed company " + adminFacade.getCompany(2));
		adminFacade.removeCompany(adminFacade.getCompany(2));
		System.out.println("\n companies After removeing company \n " + adminFacade.getAllCompanies());
	}

	public void adminFacadeUpdateCompany() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company tempComany = adminFacade.getCompany(5);
		System.out.println("\n" + tempComany);
		System.out.println(" \n update password to company");
		tempComany.setPassword("1977");
		adminFacade.updateCompany(tempComany);
		System.out.println("\n" + tempComany + "\n");
	}

	public void adminFacadeGeCompany() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n" + adminFacade.getCompany(5) + "\n");
	}

	public void adminFacadeGetAllCompanies() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n" + adminFacade.getAllCompanies() + "\n");
	}

	public void adminFacadeCreateCustomer() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n customers Before adding customer \n " + adminFacade.getAllCustomer());
		Customer cust = new Customer("avraham", "1234");
		adminFacade.createCustomer(cust);
		System.out.println("\n customers After adding customer \n " + adminFacade.getAllCustomer());
	}

	public void adminFacadeRemoveCustomer() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n customers Before removing customer \n " + adminFacade.getAllCustomer());
		System.out.println("\n removed company " + adminFacade.getCustomer(2));
		adminFacade.removeCustomer(adminFacade.getCustomer(2));
		System.out.println("\n customers After removing customer \n " + adminFacade.getAllCustomer());
	}

	public void adminFacadeUpdateCustomer() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer cust = adminFacade.getCustomer(5);
		System.out.println("\n" + cust);
		System.out.println(" \n update password to customer");
		cust.setPassword("1977");
		adminFacade.updateCustomer(cust);
		System.out.println("\n" + cust + "\n");
	}

	public void adminFacadeGeCustomer() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n" + adminFacade.getCustomer(4) + "\n");
	}

	public void adminFacadeGetAllCustomers() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n" + adminFacade.getAllCustomer() + "\n");
	}

	public void adminFacadeDublicatedCompanyVerification() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company aroma1 = new Company("Aroma", "4564", "aroma@gmail.com");
		adminFacade.createCompany(aroma1);
	}

	public void adminFacadeDublicatedCustomerVerification() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer shabtay = new Customer("shabtay", "1268712");
		adminFacade.createCustomer(shabtay);
	}

	public void adminFacadeGetAllCoupon() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println(adminFacade.getAllCoupon());
	}

	/*
	 * ---------------------- COMPANY FACADE METHODS CHECK -------------------------
	 * ---------------------- COMPANY FACADE METHODS CHECK -------------------------
	 * ---------------------- COMPANY FACADE METHODS CHECK -------------------------
	 */
	public void companyFacadeCreateCoupon() {

		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		adminFacadeGetAllCoupon();
		Coupon testCoupon = new Coupon("adidas sport shirts", null, null, 20, CouponType.SPORTS, "Very nice shirts",
				199, null, null);
		companyFacde.createCoupon(testCoupon);
		adminFacadeGetAllCoupon();
	}

	public void companyFacadeRemoveCoupon() {

		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);

		System.out.println(companyFacde.getAllCoupon());
		Coupon coupon = companyFacde.getCoupon(6);
		companyFacde.removeCoupon(coupon);
		System.out.println(companyFacde.getAllCoupon());

	}

	public void companyFacadeUpdateCoupon() {

		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);

		System.out.println(companyFacde.getAllCoupon());
		Coupon coupon = companyFacde.getCoupon(6);
		coupon.setAmount(777);
		companyFacde.updateCoupon(coupon);
		System.out.println(companyFacde.getAllCoupon());
	}

	public void companyFacadeGetCouponById() {

		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		Coupon coupon = companyFacde.getCoupon(6);
		System.out.println(coupon);
	}

	public void companyFacadeGetAllCoupon() {

		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		System.out.println(companyFacde.getAllCoupon());

	}

	// need to finish implementation
	public void companyFacadeGetCouponByType() {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);

	}

}
