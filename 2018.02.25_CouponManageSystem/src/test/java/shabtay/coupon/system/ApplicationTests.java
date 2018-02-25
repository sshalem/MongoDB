package shabtay.coupon.system;

import java.util.Collection;
import java.util.Date;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import shabtay.coupon.system.DBDAO.CouponDBDAO;
import shabtay.coupon.system.common.ClientType;
import shabtay.coupon.system.common.CouponType;
import shabtay.coupon.system.entities.Company;
import shabtay.coupon.system.entities.Coupon;
import shabtay.coupon.system.entities.Customer;
import shabtay.coupon.system.entry.CouponSystem;
import shabtay.coupon.system.exceptions.AmountOfCouponsZeroException;
import shabtay.coupon.system.exceptions.CompanyAlreadyExistException;
import shabtay.coupon.system.exceptions.CouponAlreadyExistInCompanyDBException;
import shabtay.coupon.system.exceptions.CouponAlreadyPurchsedByCustomerException;
import shabtay.coupon.system.exceptions.CouponExpiredException;
import shabtay.coupon.system.exceptions.CustomerAlreadyExistException;
import shabtay.coupon.system.exceptions.WrongLoginInputException;
import shabtay.coupon.system.facade.AdminFacade;
import shabtay.coupon.system.facade.CompanyFacade;
import shabtay.coupon.system.facade.CustomerFacade;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTests {

	@Autowired
	UpdateCompaniesWithCoupons updateCompaniesWithCoupons;
	@Autowired
	CreateCompanyDataBase createCompanyDataBase;
	@Autowired
	CreateCustomerDataBase createCustomerDataBase;
	@Autowired
	PurchaseCouponsByCustomers purchaseCouponsByCustomers;
	@Autowired
	CouponDBDAO couponDBDAO;
	@Autowired
	CouponSystem couponSystem;

	@Test
	public void contextLoads() {
	}

	// check if COMPANY is CREATED
	@Test
	public void test01_adminFacade_CreateCompany() throws WrongLoginInputException, CompanyAlreadyExistException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company adidas = new Company("adidas", "adadi777", "adidas@gmail.com");
		adminFacade.createCompany(adidas);
		String expected = adidas.getCompName();
		String actual = "unknown";
		try {
			actual = adminFacade.getCompByName("adidas").getCompName();		
			Assert.assertEquals(expected, actual);
		} catch (Exception e) {			
			Assert.assertEquals(expected, actual);
		}
	}

	// checks if COMPANY is REMOVED
	@Test
	public void test02_adminFacade_RemoveCompany() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		adminFacade.removeCompany(adminFacade.getCompany(1));
		String expected = null;
		Company actual = adminFacade.getCompany(1);
		Assert.assertEquals(expected, actual); 
	}

	// checks if COMPANY PASSWORD field is UPDATED with new password
	@Test
	public void test03_adminFacade_UpdateCompany() throws WrongLoginInputException {
		initializeDB();
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company tempComany = adminFacade.getCompany(5);
		tempComany.setPassword("1977");
		adminFacade.updateCompany(tempComany);
		String expectedPassword = "1977";
		String actualPassword = adminFacade.getCompany(5).getPassword();
		Assert.assertEquals(expectedPassword, actualPassword);
		// cleanDB();
	}

	// checks if COMPANY is FETCHED by Id
	@Test
	public void test04_adminFacade_GetCompany() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n test04 \n Get Company details by Id :" + adminFacade.getCompany(2) + "\n");
	}

	// check if all COMPANIES are FETCHED
	@Test
	public void test05_adminFacade_GetAllCompanies() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n test05 \n Get detailed List of All Companies : " + adminFacade.getAllCompanies() + "\n");
	}

	// check if CUSTOMER is CREATED
	@Test
	public void test06_adminFacade_CreateCustomer() throws CustomerAlreadyExistException, WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer cust = new Customer("avraham", "1234");
		adminFacade.createCustomer(cust);
		String expected = cust.getCustName();		
		String actual = "unknown";
		try {
			actual = adminFacade.getCustomerByName("avraham").getCustName();	
			Assert.assertEquals(expected, actual);
		} catch (Exception e) {			
			Assert.assertEquals(expected, actual);
		}		
		cleanDB();
	}

	// check if CUSTOMER is REMOVED
	@Test
	public void test07_adminFacade_RemoveCustomer() throws WrongLoginInputException {
		initializeDB();
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		adminFacade.removeCustomer(adminFacade.getCustomerByName("shabtay"));
		String expected = null;
		String actual = null;		 
		try {
			actual = adminFacade.getCustomerByName("shabtay").getCustName();	
			Assert.assertEquals(expected, actual); 
		} catch (Exception e) {			
			Assert.assertEquals(expected, actual); 
		}
		cleanDB();
	}

	// checks if Customer PASSWORD field is UPDATED with new password
	@Test
	public void test08_adminFacade_UpdateCustomer() throws WrongLoginInputException {
		initializeDB();
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer cust = adminFacade.getCustomerByName("ariel");
		cust.setPassword("2013");
		adminFacade.updateCustomer(cust);
		String expected = "2013";
		String actual = "unknown";	
		try {
			actual = adminFacade.getCustomerByName("ariel").getPassword();
			Assert.assertEquals(expected, actual);
		} catch (Exception e) {
			Assert.assertEquals(expected, actual);
		}
	}

	// checks if CUSTOMER is FETCHED by Id
	@Test
	public void test09_adminFacade_GetCustomer() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n test09 \n Get Customer details by Id : " + adminFacade.getCustomer(15) + "\n");
	}

	// get all the Customers that were created
	@Test
	public void test10_adminFacade_GetAllCustomers() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n test10 \n Get detailed List of all Customers :" + adminFacade.getAllCustomer() + "\n");
	}

	@Test
	public void test11_adminFacade_GetAllCoupon() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n test11 \n Get List of All Coupons of all Compnaies" + adminFacade.getAllCoupon());
	}

	// check if Exception thrown while creating Company with same name
	@Test
	public void test12_adminFacade_DublicatedCompanyExceptionCheck() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);

		try {
			Company aroma1 = new Company("Aroma", "4564", "aroma@gmail.com");
			adminFacade.createCompany(aroma1);
			Assert.assertTrue(" \n test12 \n Exception not caught verify you entered Company name Correctly", false);
		} catch (CompanyAlreadyExistException e) {
			// e.printStackTrace();
			System.out.println("\n test12 \n CompanyAlreadyExistException ... is Caught ");
		}
	}

	// check if Exception thrown while creating Custmoer with same name
	@Test
	public void test13_adminFacade_DublicatedCustomerExceptionCheck() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer shabtay = new Customer("karin", "1268712");
		try {
			adminFacade.createCustomer(shabtay);
			Assert.assertTrue(" \n test13 \n Exception not caught verify you entered Customer name Correctly", false);
		} catch (CustomerAlreadyExistException e) {
			// e.printStackTrace();
			System.out.println("\n test13 \n Expected CustomerAlreadyExistException ... is Caught ");
		}
	}

	// check coupon created per company
	@Test
	public void test14_companyFacade_CreateCoupon()
			throws CouponAlreadyExistInCompanyDBException, WrongLoginInputException {
		// initializeDB();
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		Coupon testCoupon = new Coupon("Aroma ultimate breakfast", null, null, 20, CouponType.RESTURANTS, "the best",
				199, null, null);
		companyFacde.createCoupon(testCoupon);

		String expected = "Aroma ultimate breakfast";
		String actual = companyFacde.getCouponByName(expected).getTitle();
		Assert.assertEquals(expected, actual);
	}

	// check if coupon removed per company
	@Test
	public void test15_companyFacade_RemoveCoupon() throws WrongLoginInputException {
		CompanyFacade cf = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		Coupon coupon = cf.getCouponByName("aroma");
		cf.removeCoupon(coupon);
		String expected = null;
		Coupon actual = cf.getCouponByName("aroma");
		System.out.println(actual);
		Assert.assertEquals(expected, actual);
	}

	// check if coupon attribute amount is updated per company
	@Test
	public void test16_companyFacade_UpdateCoupon() throws WrongLoginInputException {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		Coupon coupon = companyFacde.getCouponByName("Aroma express");
		coupon.setAmount(777);
		companyFacde.updateCoupon(coupon);
		int expected = 777;
		int actual = companyFacde.getCouponByName("Aroma express").getAmount();
		Assert.assertEquals(expected, actual);
	}

	// check if coupon is fetched by Id per company
	@Test
	public void test17_companyFacade_GetCouponById() throws WrongLoginInputException {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		Coupon coupon = companyFacde.getCoupon(30);
		System.out.println("\n test17 \n Get Company Coupon by Id" + coupon);
	}

	// check if coupon is fetched by name per company
	@Test
	public void test18_companyFacade_GetCouponByName() throws WrongLoginInputException {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		String actual = companyFacde.getCouponByName("CoffeeCoffee lunch").getTitle();
		String expected = "CoffeeCoffee lunch";
		Assert.assertEquals(expected, actual);
	}

	// check if getting all coupons per company
	@Test
	public void test19_companyFacade_GetAllCoupon() throws WrongLoginInputException {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		System.out.println("\n test19 \n Get List Of Company's Coupons : " + companyFacde.getAllCoupon());
	}

	// check if coupon is fetched by type per company
	@Test
	public void test20_companyFacade_GetCouponByType() throws WrongLoginInputException {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		System.out.println("\n test20 \n Get List of Company's Coupons By Type :"
				+ companyFacde.getCouponByType(CouponType.RESTURANTS));
	}

	// check if coupons are fetched between range of price per company
	@Test
	public void test21_companyFacade_GetAllCouponsByPrice() throws WrongLoginInputException {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		System.out.println("\n test21 \n List of Company's coupons by price between  Specified price range :"
				+ companyFacde.getAllCouponsByPrice(200, 400));
	}

	// check if getting Exception when updating company with same coupon
	@Test
	public void test22_companyFacade_DuplicatedCouponCheck() throws WrongLoginInputException {

		// Date sd1 = new Date(2018 - 1900, 0, 27);
		// Date ed1 = new Date(2018 - 1900, 5, 30);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		Coupon coup = new Coupon("Aroma Dinner", null, null, 30, CouponType.RESTURANTS, "Aroma Brekfast for one person",
				69, null, null);
		try {
			companyFacade.createCoupon(coup);
			Assert.assertTrue(" \n test22 \n Exception not caught verify you entered data correctly for testing",
					false);
		} catch (CouponAlreadyExistInCompanyDBException actual) {
			// actual.printStackTrace();
			System.out.println("\n test22 \n Exception of CouponAlreadyExistInCompanyDBException is caught...");
		}

	}

	// check if coupons are fetched between range of dates per company
	@Test
	public void test23_companyFacade_getCouponBetweenDates() throws WrongLoginInputException {
		Date startD = new Date(2018 - 1900, 0, 1);
		Date endD = new Date(2018 - 1900, 7, 31);
		CompanyFacade cf = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		System.out.println("\n test23 \n List of Company's Coupons between Dates : "
				+ cf.getCouponBetweenDates(startD, endD) + " \n");
	}

	// check customer purchase coupon
	@Test
	public void test24_customerFacade_PurchaseCoupon() throws CouponAlreadyPurchsedByCustomerException,
			AmountOfCouponsZeroException, CouponExpiredException, WrongLoginInputException {
		CustomerFacade cf = (CustomerFacade) couponSystem.login("shabtay", "121212", ClientType.CUSTOMER);
		Coupon pc = couponDBDAO.getCouponByTitle("Aroma breakfast for 2");
		cf.purchaseCoupon(pc);
		String expected = pc.getTitle();
		String actual = cf.getCouponFromCustomerDB("Aroma breakfast for 2").getTitle();
		Assert.assertEquals(expected, actual);
	}

	// check for all coupons purchased by customer
	@Test
	public void test25_customerFacade_GetAllPurchasedCoupons() throws WrongLoginInputException {
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		System.out.println("\n test25 \n Get List of All purchased Coupons by Customer :"
				+ customerFacade.getAllPurchasedCoupons());
	}

	// check for purchased coupons by customer per type
	@Test
	public void test26_customerFacade_GetAllPurchasedCouponsByType() throws WrongLoginInputException {
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		System.out.println("\n test26 \n List of Customer's Coupons per coupon type"
				+ customerFacade.getAllPurchasedCouponsByType(CouponType.RESTURANTS));

	}

	// check for purchased coupons by customer between price range
	@Test
	public void test27_customerFacade_GetAllPurchasedCouponsByPrice() throws WrongLoginInputException {
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		System.out.println("\n test27 \n List of  Customer's coupons by Price: "
				+ customerFacade.getAllPurchasedCouponsByPrice(50, 120));
	}

	// check for Exception if customer trying to purchase an existing coupon
	@Test
	public void test28_customerFacade_DuplicatedCouponPurchaseByCustomerCheck()
			throws AmountOfCouponsZeroException, CouponExpiredException, WrongLoginInputException {

		try {
			CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("karin", "2154459",
					ClientType.CUSTOMER);
			Coupon pc = couponDBDAO.getCouponByTitle("CoffeeCoffee israeli Breakfast");
			customerFacade.purchaseCoupon(pc);
			Assert.assertTrue(" \n test28 \n Exception not caught verify you entered data", false);
		} catch (CouponAlreadyPurchsedByCustomerException e) {
			// e.printStackTrace();
			System.out.println("\n test28 \n CouponAlreadyPurchsedByCustomerException ... is Caught ");
		}
	}

	// check for Exception if amount of coupon is zero
	@Test
	public void test29_customerFacade_checkAmountIsZeroException() throws CouponAlreadyExistInCompanyDBException,
			CouponAlreadyPurchsedByCustomerException, CouponExpiredException, WrongLoginInputException {

		Date sd = new Date(2018 - 1900, 5, 1);
		Date ed = new Date(2018 - 1900, 7, 1);

		CompanyFacade compF = (CompanyFacade) couponSystem.login("CoffeCoffe", "123123", ClientType.COMPANY);

		try {

			Coupon coup = new Coupon("Coffee test", sd, ed, 1, CouponType.RESTURANTS, "test", 65, null, null);
			compF.createCoupon(coup);

			CustomerFacade custF1 = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
			Coupon pc1 = couponDBDAO.getCouponByTitle("Coffee test");
			custF1.purchaseCoupon(pc1);

			CustomerFacade custF2 = (CustomerFacade) couponSystem.login("shabtay", "121212", ClientType.CUSTOMER);
			Coupon pc2 = couponDBDAO.getCouponByTitle("Coffee test");

			custF2.purchaseCoupon(pc2);

			Assert.assertTrue(" \n test29 \n Exception not caught verify you entered data correctly for testing",
					false);
		} catch (AmountOfCouponsZeroException e) {
			// e.printStackTrace();
			System.out.println("\n test29 \n AmountOfCouponsZeroException ... is Caught ");

		}

	}

	// check for Exception for if coupon date is expired
	@Test
	public void test30_customerFacade_checkCouponExpiredDateException()
			throws CouponAlreadyPurchsedByCustomerException, AmountOfCouponsZeroException, WrongLoginInputException {

		Date endDate = new Date(2018 - 1900, 1, 1);
		CompanyFacade compF = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		Coupon coup = compF.getCouponByName("Aroma breakfast for 2");
		coup.setEndDate(endDate);
		compF.updateCoupon(coup);

		CustomerFacade cf2 = (CustomerFacade) couponSystem.login("shabtay", "121212", ClientType.CUSTOMER);
		Coupon pc2 = couponDBDAO.getCouponByTitle("Aroma breakfast for 2");

		try {
			cf2.purchaseCoupon(pc2);
			Assert.assertTrue(" CouponExpiredException not caught ", false);
		} catch (CouponExpiredException e) {
			// e.printStackTrace();
			System.out.println("\n test30 \n CouponExpiredException ... is Caught ");
		}
	}

	// Check for login Exception is thrown in case one of the details is wrong
	@Test
	public void test31_Check_For_WrongLoginInputException() {
		try {
			AdminFacade af = (AdminFacade) couponSystem.login("admin", "12340", ClientType.ADMIN);
			Assert.assertTrue(
					" \n test31 \n Exception not caught for testing Admin \n verify you entered wrong details", false);
		} catch (WrongLoginInputException e) {
			// e.printStackTrace();
			System.out.println("\n test31 \n WrongLoginInputException thrown because login to Admin failed ");
		}

		try {
			CompanyFacade compF = (CompanyFacade) couponSystem.login("CoffeCoffe", "home", ClientType.COMPANY);
			Assert.assertTrue(
					" \n test31 \n Exception not caught for testing COMPANY \n verify you entered wrong details ",
					false);
		} catch (WrongLoginInputException e) {
			// e.printStackTrace();
			System.out.println(" WrongLoginInputException thrown because login to Company failed ");
		}

		try {
			CustomerFacade custF = (CustomerFacade) couponSystem.login("shabtay", "122121", ClientType.CUSTOMER);
			Assert.assertTrue(
					" \n test31 \n Exception not caught for testing CUSTOMER \n verify you entered wrong details ",
					false);
		} catch (WrongLoginInputException e) {
			// e.printStackTrace();
			System.out.println(" WrongLoginInputException thrown because login to Customer failed ");
		}

	}

	public void initializeDB() throws WrongLoginInputException {
		createCompanyDataBase.createDataBase();
		createCustomerDataBase.createDataBase();
		updateCompaniesWithCoupons.createCoupons();
		purchaseCouponsByCustomers.purchaseCoupons();
	}

	public void cleanDB() throws WrongLoginInputException {
		removeAllCompany();
		removeAllCustomers();
	}

	public void removeAllCompany() throws WrongLoginInputException {
		AdminFacade af = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Collection<Company> companies = af.getAllCompanies();
		for (Company company : companies) {
			af.removeCompany(company);
		}
	}

	public void removeAllCustomers() throws WrongLoginInputException {
		AdminFacade af = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Collection<Customer> customers = af.getAllCustomer();
		for (Customer customer : customers) {
			af.removeCustomer(customer);
		}
	}
}
