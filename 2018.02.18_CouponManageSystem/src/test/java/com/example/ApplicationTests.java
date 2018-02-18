package com.example;

import java.util.Calendar;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTests {

	@Autowired UpdateCompaniesWithCoupons updateCompaniesWithCoupons;
	@Autowired CreateCompanyDataBase createCompanyDataBase;
	@Autowired CreateCustomerDataBase createCustomerDataBase;
	@Autowired PurchaseCouponsByCustomers purchaseCouponsByCustomers;
	@Autowired CouponDBDAO couponDBDAO;
	@Autowired CouponSystem couponSystem;

	@Test
	public void contextLoads() {

		createCompanyDataBase.createDataBase();
		createCustomerDataBase.createDataBase();
		updateCompaniesWithCoupons.createCoupons();
		purchaseCouponsByCustomers.purchaseCoupons();
	}

	@Test
	public void test01_adminFacde() {

		/*
		 * Methods below checks the ADMIN_FACADE methods according the projects sheet
		 */
		
		// Just select/Deselect the method you want to Check/Uncheck
		
//		 adminFacade_CreateCompany();
//		 adminFacade_RemoveCompany();
//		 adminFacade_UpdateCompany();
//		 adminFacade_GetCompany();
//		 adminFacade_GetAllCompanies();
//		 adminFacade_CreateCustomer();
//		 adminFacade_RemoveCustomer();
//		 adminFacade_UpdateCustomer();
//		 adminFacade_GeCustomer();
//		 adminFacade_GetAllCustomers();
//		 adminFacade_GetAllCoupon();
//		 adminFacade_DublicatedCustomerVerification();
//		 adminFacade_DublicatedCompanyVerification();

	}

	@Test
	public void test02_companyFacade() {

		// Just select/Deselect the method you want to Check/Uncheck
		
//		companyFacade_CreateCoupon();
//		companyFacade_RemoveCoupon();
//		companyFacade_UpdateCoupon();
//		companyFacade_GetCouponById();
//		companyFacade_GetAllCoupon();
//		companyFacade_GetCouponByType();
//		companyFacade_GetAllCouponsByPrice();
//		companyFacade_DuplicatedCouponVerification();
//		companyFacade_getCouponBetweenDates();

	}

	@Test
	public void test03_customerFacade() {

		// Just select/Deselect the method you want to Check/Uncheck
		
//		 customerFacade_PurchaseCoupon();
//		 customerFacade_GetAllPurchasedCoupons();
//		 customerFacade_GetAllPurchasedCouponsByType();
//		 customerFacade_GetAllPurchasedCouponsByPrice();	
//		 customerFacade_checkCouponAlreadyPurchasedException();
//		 customerFacade_checkAmountIsZeroException();
//		 customerFacade_checkCouponExpiredDateException();

	}
	

	/*
	 * ---------------------- ADMIN FACADE METHODS CHECK -------------------------
	 * ---------------------- ADMIN FACADE METHODS CHECK -------------------------
	 * ---------------------- ADMIN FACADE METHODS CHECK -------------------------
	 */

	public void adminFacade_CreateCompany() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n companies Before adding company \n " + adminFacade.getAllCompanies());
		Company adidas = new Company("adidas", "adadi777", "adidas@gamil.com");
		adminFacade.createCompany(adidas);
		System.out.println("\n companies After adding company \n " + adminFacade.getAllCompanies());
	}

	public void adminFacade_RemoveCompany() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n companies Before removeing company \n " + adminFacade.getAllCompanies());
		System.out.println("\n removed company :" + adminFacade.getCompany(1));
		adminFacade.removeCompany(adminFacade.getCompany(1));
		System.out.println("\n companies After removeing company :\n " + adminFacade.getAllCompanies());
	}

	public void adminFacade_UpdateCompany() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company tempComany = adminFacade.getCompany(2);
		System.out.println("\n" + tempComany);
		System.out.println(" \n update password to company");
		tempComany.setPassword("1977");
		adminFacade.updateCompany(tempComany);
		System.out.println("\n" + tempComany + "\n");
		adminFacade_GetAllCompanies();
	}

	public void adminFacade_GetCompany() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n" + adminFacade.getCompany(2) + "\n");		
	}

	public void adminFacade_GetAllCompanies() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n" + adminFacade.getAllCompanies() + "\n");
	}

	public void adminFacade_CreateCustomer() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n customers Before adding customer \n " + adminFacade.getAllCustomer());
		Customer cust = new Customer("avraham", "1234");
		adminFacade.createCustomer(cust);
		System.out.println("\n customers After adding customer \n " + adminFacade.getAllCustomer());
	}

	public void adminFacade_RemoveCustomer() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n customers Before removing customer: \n " + adminFacade.getAllCustomer());
		System.out.println("\n removed customer " + adminFacade.getCustomer(2));
		adminFacade.removeCustomer(adminFacade.getCustomer(2));
		System.out.println("\n customers After removing customer :\n " + adminFacade.getAllCustomer());
	}

	public void adminFacade_UpdateCustomer() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer cust = adminFacade.getCustomer(5);
		System.out.println("\n" + cust);
		System.out.println(" \n update password to customer");
		cust.setPassword("1977");
		adminFacade.updateCustomer(cust);
		System.out.println("\n" + cust + "\n");
	}

	public void adminFacade_GeCustomer() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n" + adminFacade.getCustomer(4) + "\n");
	}

	public void adminFacade_GetAllCustomers() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n" + adminFacade.getAllCustomer() + "\n");
	}

	public void adminFacade_DublicatedCompanyVerification() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company aroma1 = new Company("Aroma", "4564", "aroma@gmail.com");
		adminFacade.createCompany(aroma1);
	}

	public void adminFacade_DublicatedCustomerVerification() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer shabtay = new Customer("shabtay", "1268712");
		adminFacade.createCustomer(shabtay);
	}

	public void adminFacade_GetAllCoupon() {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println(adminFacade.getAllCoupon());
	}

	/*
	 * ---------------------- COMPANY FACADE METHODS CHECK -------------------------
	 * ---------------------- COMPANY FACADE METHODS CHECK -------------------------
	 * ---------------------- COMPANY FACADE METHODS CHECK -------------------------
	 */
	public void companyFacade_CreateCoupon() {

		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		adminFacade_GetAllCoupon();
		Coupon testCoupon = new Coupon("Aroma ultimate breakfast", null, null, 20, CouponType.RESTURANTS, "the best",
				199, null, null);
		companyFacde.createCoupon(testCoupon);
		adminFacade_GetAllCoupon();
	}

	public void companyFacade_RemoveCoupon() {

		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);

		System.out.println(adminFacade.getAllCoupon());
		System.out.println(companyFacde.getAllCoupon());
		Coupon coupon = companyFacde.getCoupon(7);
		companyFacde.removeCoupon(coupon);
		System.out.println(companyFacde.getAllCoupon());
		System.out.println(adminFacade.getAllCoupon());

		customerFacade_GetAllPurchasedCoupons();

	}

	public void companyFacade_UpdateCoupon() {

		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);

		System.out.println(companyFacde.getCoupon(7));
		Coupon coupon = companyFacde.getCoupon(7);
		coupon.setAmount(777);
		companyFacde.updateCoupon(coupon);
		System.out.println(companyFacde.getCoupon(7));
	}

	public void companyFacade_GetCouponById() {

		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		Coupon coupon = companyFacde.getCoupon(8);
		System.out.println(coupon);
	}

	public void companyFacade_GetAllCoupon() {

		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		System.out.println(companyFacde.getAllCoupon());

	}

	// need to finish implementation
	public void companyFacade_GetCouponByType() {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		System.out.println(companyFacde.getCouponByType(CouponType.RESTURANTS));
	}
	
	public void companyFacade_GetAllCouponsByPrice() {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		System.out.println(companyFacde.getAllCouponsByPrice(200, 400));
	}

	public void companyFacade_DuplicatedCouponVerification() {

		Date startd1 = new Date(2018 - 1900, 0, 27);
		Date endD1 = new Date(2018 - 1900, 5, 30);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		Coupon aromaBreakfastforOne = new Coupon("aroma", startd1, endD1, 30, CouponType.RESTURANTS,
				"Aroma Brekfast for one person", 69, null, null);
		companyFacade.createCoupon(aromaBreakfastforOne);

	}
	
	public void companyFacade_getCouponBetweenDates() {		
		Date startD = new Date(2018 - 1900, 0, 1);
		Date endD = new Date(2018 - 1900, 7, 31);
		
		CompanyFacade cf = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);		
		System.out.println(cf.getCouponBetweenDates(startD, endD) + " \n");		
	}

	/*
	 * ---------------------- CUSTOMER FACADE METHODS CHECK ----------------------
	 * CUSTOMER FACADE METHODS CHECK ---------------------- CUSTOMER FACADE METHODS
	 * CHECK ---------------------
	 */

	public void customerFacade_DuplicatedCouponVerification() {
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		Coupon pc1 = couponDBDAO.getCoupon(4);
		customerFacade.purchaseCoupon(pc1);
	}

	public void customerFacade_PurchaseCoupon() {
		CustomerFacade cf = (CustomerFacade) couponSystem.login("shabtay", "121212", ClientType.CUSTOMER);
		System.out.println("before purchase" + cf.getAllPurchasedCoupons() + "\n");
		Coupon pc = couponDBDAO.getCoupon(8);
		cf.purchaseCoupon(pc);
		System.out.println("after purchse" + cf.getAllPurchasedCoupons() + "\n");
	}
	
	public void customerFacade_GetAllPurchasedCoupons() {
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		System.out.println(customerFacade.getAllPurchasedCoupons());
	}

	public void customerFacade_GetAllPurchasedCouponsByType() {
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		System.out.println(customerFacade.getAllPurchasedCouponsByType(CouponType.RESTURANTS));

	}

	public void customerFacade_GetAllPurchasedCouponsByPrice() {
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		System.out.println("\n get all purchased coupons between 50 - 120" + customerFacade.getAllPurchasedCouponsByPrice(50, 120));
		System.out.println(customerFacade.getAllPurchasedCoupons());
	}
	
	public void customerFacade_checkCouponAlreadyPurchasedException() {
		CustomerFacade customerFacade2 = (CustomerFacade) couponSystem.login("shabtay", "121212", ClientType.CUSTOMER);
		Coupon pc2 = couponDBDAO.getCoupon(7);
		customerFacade2.purchaseCoupon(pc2);
	}
	
	public void customerFacade_checkAmountIsZeroException() {
		
		Date sd = new Date(2018-1900, 5, 1);
		Date ed = new Date(2018-1900, 7, 1);
		
		CompanyFacade compF = (CompanyFacade) couponSystem.login("CoffeCoffe", "123123", ClientType.COMPANY);
		Coupon coup = new Coupon("Coffee test", sd, ed, 1, CouponType.RESTURANTS,
				"test", 65, null, null);
		compF.createCoupon(coup);		
		
		CustomerFacade custF1 = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		Coupon pc1 = couponDBDAO.getCouponByTitle("Coffee test");
		custF1.purchaseCoupon(pc1);		

		CustomerFacade custF2 = (CustomerFacade) couponSystem.login("shabtay", "121212", ClientType.CUSTOMER);
		Coupon pc2 = couponDBDAO.getCouponByTitle("Coffee test");
		custF2.purchaseCoupon(pc2);
	}
	
	public void customerFacade_checkCouponExpiredDateException() { 
		
		Date endDate = new Date(2018-1900, 1, 1);
		CompanyFacade compF = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		Coupon coup = compF.getCoupon(6);		
		coup.setEndDate(endDate);
		compF.updateCoupon(coup);		
					
		CustomerFacade cf2 = (CustomerFacade) couponSystem.login("shabtay", "121212", ClientType.CUSTOMER);
		Coupon pc2 = couponDBDAO.getCoupon(6);
		System.out.println("current date : " + Calendar.getInstance().getTime());
		System.out.println("\n Coupon end date :" + pc2.getEndDate() + "\n");
		cf2.purchaseCoupon(pc2);				
	}

}
