package shabtay.coupon.system;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

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
/**
 * 
 * @author Shabtay Shalem
 *
 */
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

	/**
	 * test01_adminFacade_CreateCompany() - checks method createCompany() in
	 * AdminFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 * @throws CompanyAlreadyExistException in case Company already exist
	 */
	@Test
	public void test01_adminFacade_CreateCompany() throws WrongLoginInputException, CompanyAlreadyExistException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company adidas = new Company("adidas", "adadi777", "adidas@gmail.com");
		adminFacade.createCompany(adidas);
		String expected = adidas.getCompName();
		String actual = adminFacade.getCompByName("adidas").getCompName();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * test02_adminFacade_RemoveCompany() - checks method removeCompany() in
	 * AdminFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test02_adminFacade_RemoveCompany() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		adminFacade.removeCompany(adminFacade.getCompany(1));
		String expected = null;
		Company actual = adminFacade.getCompany(1);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * test03_adminFacade_UpdateCompany() - checks method updateCompany() in
	 * AdminFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
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

	/**
	 * test04_adminFacade_GetCompany() - checks method getCompany() in AdminFacade
	 * class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test04_adminFacade_GetCompany() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n test04 \n Get Company details by Id :" + adminFacade.getCompany(2) + "\n");
	}

	/**
	 * test05_adminFacade_GetAllCompanies() - checks method getAllCompanies() in
	 * AdminFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test05_adminFacade_GetAllCompanies() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n test05 \n Get detailed List of All Companies : " + adminFacade.getAllCompanies() + "\n");
	}

	/**
	 * test06_adminFacade_CreateCustomer() - checks method createCustomer() in
	 * AdminFacade class
	 * 
	 * @throws CustomerAlreadyExistException in case Customer already exist
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test06_adminFacade_CreateCustomer() throws CustomerAlreadyExistException, WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer cust = new Customer("avraham", "1234");
		adminFacade.createCustomer(cust);
		String expected = cust.getCustName();
		String actual = adminFacade.getCustomerByName("avraham").getCustName();
		Assert.assertEquals(expected, actual);
		cleanDB();
	}

	/**
	 * test07_adminFacade_RemoveCustomer() - checks method removeCustomer() in
	 * AdminFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test07_adminFacade_RemoveCustomer() throws WrongLoginInputException {
		initializeDB();
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		adminFacade.removeCustomer(adminFacade.getCustomerByName("shabtay"));
		String expected = null;
		String actual = " ";
		try {
			actual = adminFacade.getCustomerByName("shabtay").getCustName();
		} catch (NullPointerException e) {
			actual = null;
		}
		Assert.assertEquals(expected, actual);
		cleanDB();
	}

	/**
	 * test08_adminFacade_UpdateCustomer() - checks method updateCustomer() in
	 * AdminFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test08_adminFacade_UpdateCustomer() throws WrongLoginInputException {
		initializeDB();
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer cust = adminFacade.getCustomerByName("ariel");
		cust.setPassword("2013");
		adminFacade.updateCustomer(cust);
		String expected = "2013";
		String actual = adminFacade.getCustomerByName("ariel").getPassword();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * test09_adminFacade_GetCustomer() - checks method getCustomer() in
	 * AdminFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test09_adminFacade_GetCustomer() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println("\n test09 \n Get Customer details by Id : " + adminFacade.getCustomer(15) + "\n");
	}

	/**
	 * test10_adminFacade_GetAllCustomers() - checks method getAllCustomer() in
	 * AdminFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
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

	/**
	 * test12_adminFacade_DublicatedCompanyExceptionCheck() - checks if
	 * CompanyAlreadyExistException is thrown
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test12_adminFacade_DublicatedCompanyExceptionCheck() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company aroma1 = new Company("Aroma", "4564", "aroma@gmail.com");
		boolean thrown = false;
		try {
			adminFacade.createCompany(aroma1);
		} catch (CompanyAlreadyExistException e) {
			thrown = true;
		}
		Assert.assertTrue(" ..Expected CompanyAlreadyExistException not thrown , check code ....", thrown);
	}

	/**
	 * test13_adminFacade_DublicatedCustomerExceptionCheck() - checks if
	 * CustomerAlreadyExistException is thrown
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test13_adminFacade_DublicatedCustomerExceptionCheck() throws WrongLoginInputException {
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer karin = new Customer("karin", "1268712");
		boolean thrown = false;
		try {
			adminFacade.createCustomer(karin);
		} catch (CustomerAlreadyExistException e) {
			thrown = true;
		}
		Assert.assertTrue(" ..Expected CustomerAlreadyExistException not thrown , check code ....", thrown);
	}

	/**
	 * test14_companyFacade_CreateCoupon() - checks method createCoupon() in
	 * CompanyFacade class
	 * 
	 * @throws CouponAlreadyExistInCompanyDBException in case Coupon alreadyExist in Company DB
	 * @throws WrongLoginInputException in case login fails
	 */
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

	/**
	 * test15_companyFacade_RemoveCoupon() - checks method removeCoupon() in
	 * CompanyFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
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

	/**
	 * test16_companyFacade_UpdateCoupon() - checks method updateCoupon() in
	 * CompanyFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
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

	/**
	 * test17_companyFacade_GetCouponById() - checks method getCoupon() by passing
	 * it Id in CompanyFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test17_companyFacade_GetCouponById() throws WrongLoginInputException {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		Coupon coupon = companyFacde.getCoupon(30);
		System.out.println("\n test17 \n Get Company Coupon by Id" + coupon);
	}

	/**
	 * test18_companyFacade_GetCouponByName() - checks method getCouponByName() in
	 * CompanyFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test18_companyFacade_GetCouponByName() throws WrongLoginInputException {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		String actual = companyFacde.getCouponByName("CoffeeCoffee lunch").getTitle();
		String expected = "CoffeeCoffee lunch";
		Assert.assertEquals(expected, actual);
	}

	/**
	 * test19_companyFacade_GetAllCoupon() - checks method getAllCoupon() in
	 * CompanyFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test19_companyFacade_GetAllCoupon() throws WrongLoginInputException {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		System.out.println("\n test19 \n Get List Of Company's Coupons : " + companyFacde.getAllCoupon());
	}

	/**
	 * test20_companyFacade_GetCouponByType() - checks method getCouponByType() in
	 * CompanyFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test20_companyFacade_GetCouponByType() throws WrongLoginInputException {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		System.out.println("\n test20 \n Get List of Company's Coupons By Type :"
				+ companyFacde.getCouponByType(CouponType.RESTURANTS));
	}

	/**
	 * test21_companyFacade_GetAllCouponsByPrice() - checks method
	 * getAllCouponsByPrice() in CompanyFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test21_companyFacade_GetAllCouponsByPrice() throws WrongLoginInputException {
		CompanyFacade companyFacde = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		System.out.println("\n test21 \n List of Company's coupons by price between  Specified price range :"
				+ companyFacde.getAllCouponsByPrice(200, 400));
	}

	/**
	 * test22_companyFacade_DuplicatedCouponCheck() - checks if
	 * CouponAlreadyExistInCompanyDBException is thrown
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test22_companyFacade_DuplicatedCouponCheck() throws WrongLoginInputException {
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		Coupon coup = new Coupon("Aroma Dinner", null, null, 30, CouponType.RESTURANTS, "Aroma Brekfast for one person",
				69, null, null);

		boolean thrown = false;
		try {
			companyFacade.createCoupon(coup);
		} catch (CouponAlreadyExistInCompanyDBException e) {
			thrown = true;
		}
		Assert.assertTrue("... Expected CouponAlreadyExistInCompanyDBException   ... not thrown... check code ...",
				thrown);
	}

	/**
	 * test23_companyFacade_getCouponBetweenDates() - checks method
	 * getCouponBetweenDates() in CompanyFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test23_companyFacade_getCouponBetweenDates() throws WrongLoginInputException {
		Date startD = new Date(2018 - 1900, 0, 1);
		Date endD = new Date(2018 - 1900, 7, 31);
		CompanyFacade cf = (CompanyFacade) couponSystem.login("Aroma", "456456", ClientType.COMPANY);
		System.out.println("\n test23 \n List of Company's Coupons between Dates : "
				+ cf.getCouponBetweenDates(startD, endD) + " \n");
	}

	/**
	 * test24_customerFacade_PurchaseCoupon() - checks method purchaseCoupon() in
	 * CustomerFacade class
	 * 
	 * @throws CouponAlreadyPurchsedByCustomerException in case Coupon already purchased by Customer
	 * @throws AmountOfCouponsZeroException in case Amount of Coupon is Zero
	 * @throws CouponExpiredException in case Coupon Date Expired
	 * @throws WrongLoginInputException in case login fails
	 */
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

	/**
	 * test25_customerFacade_GetAllPurchasedCoupons() - checks method
	 * getAllPurchasedCoupons() in CustomerFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test25_customerFacade_GetAllPurchasedCoupons() throws WrongLoginInputException {
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		System.out.println("\n test25 \n Get List of All purchased Coupons by Customer :"
				+ customerFacade.getAllPurchasedCoupons());
	}

	/**
	 * test26_customerFacade_GetAllPurchasedCouponsByType() - checks method
	 * getAllPurchasedCouponsByType() in CustomerFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test26_customerFacade_GetAllPurchasedCouponsByType() throws WrongLoginInputException {
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		System.out.println("\n test26 \n List of Customer's Coupons per coupon type"
				+ customerFacade.getAllPurchasedCouponsByType(CouponType.RESTURANTS));

	}

	/**
	 * test27_customerFacade_GetAllPurchasedCouponsByPrice() - checks method
	 * getAllPurchasedCouponsByPrice() in CustomerFacade class
	 * 
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test27_customerFacade_GetAllPurchasedCouponsByPrice() throws WrongLoginInputException {
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		System.out.println("\n test27 \n List of  Customer's coupons by Price: "
				+ customerFacade.getAllPurchasedCouponsByPrice(50, 120));
	}

	/**
	 * test28_customerFacade_DuplicatedCouponPurchaseByCustomerCheck() - checks if
	 * CouponAlreadyPurchsedByCustomerException is thrown
	 * 
	 * @throws AmountOfCouponsZeroException in case Amount of Coupon is Zero
	 * @throws CouponExpiredException in case Coupon Date Expired
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test28_customerFacade_DuplicatedCouponPurchaseByCustomerCheck()
			throws AmountOfCouponsZeroException, CouponExpiredException, WrongLoginInputException {

		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
		Coupon pc = couponDBDAO.getCouponByTitle("CoffeeCoffee israeli Breakfast");

		boolean thrown = false;
		try {
			customerFacade.purchaseCoupon(pc);
		} catch (CouponAlreadyPurchsedByCustomerException e) {
			thrown = true;
		}
		Assert.assertTrue(" \n test28 \n Exception not caught verify you entered data", true);
	}

	/**
	 * test29_customerFacade_checkAmountIsZeroException() - checks if
	 * AmountOfCouponsZeroException is thrown
	 * 
	 * @throws CouponAlreadyExistInCompanyDBException in case Coupon alreadyExist in Company DB
	 * @throws CouponAlreadyPurchsedByCustomerException in case Coupon already purchased by Customer
	 * @throws CouponExpiredException in case Coupon Date Expired
	 * @throws WrongLoginInputException in case login fails
	 */
	@Test
	public void test29_customerFacade_checkAmountIsZeroException() throws CouponAlreadyExistInCompanyDBException,
			CouponAlreadyPurchsedByCustomerException, CouponExpiredException, WrongLoginInputException {

		Date sd = new Date(2018 - 1900, 5, 1);
		Date ed = new Date(2018 - 1900, 7, 1);

		CompanyFacade compF = (CompanyFacade) couponSystem.login("CoffeCoffe", "123123", ClientType.COMPANY);

		boolean thrown = false;
		try {

			Coupon coup = new Coupon("Coffee test", sd, ed, 1, CouponType.RESTURANTS, "test", 65, null, null);
			compF.createCoupon(coup);

			CustomerFacade custF1 = (CustomerFacade) couponSystem.login("karin", "2154459", ClientType.CUSTOMER);
			Coupon pc1 = couponDBDAO.getCouponByTitle("Coffee test");
			custF1.purchaseCoupon(pc1);

			CustomerFacade custF2 = (CustomerFacade) couponSystem.login("shabtay", "121212", ClientType.CUSTOMER);
			Coupon pc2 = couponDBDAO.getCouponByTitle("Coffee test");

			custF2.purchaseCoupon(pc2);

		} catch (AmountOfCouponsZeroException e) {
			thrown = true;
		}
		Assert.assertTrue(
				" \n test29 \n AmountOfCouponsZeroException not caught verify you entered data correctly for testing",
				true);
	}

	/**
	 * test30_customerFacade_checkCouponExpiredDateException() - checks if
	 * CouponExpiredException is thrown
	 * 
	 * @throws CouponAlreadyPurchsedByCustomerException  in case Coupon already purchased by Customer
	 * @throws AmountOfCouponsZeroException in case Amount of Coupon is Zero
	 * @throws WrongLoginInputException in case login fails
	 */
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
		boolean thrown = false;
		try {
			cf2.purchaseCoupon(pc2);

		} catch (CouponExpiredException e) {
			thrown = true;
		}
		Assert.assertTrue(" \n test30 \n ...CouponExpiredException not thrown ...... ", true);
	}

	/**
	 * test31_Check_For_WrongLoginInputException() - checks if
	 * WrongLoginInputException is thrown
	 */
	@Test
	public void test31_Check_For_WrongLoginInputException() {

		// check if Exception thrown in case Admin login fails
		boolean adminThrown = false;
		try {
			AdminFacade af = (AdminFacade) couponSystem.login("admin", "1230004", ClientType.ADMIN);
		} catch (WrongLoginInputException e) {
			adminThrown = true;
		}
		Assert.assertTrue(" \n test31 \n WrongLoginInputException not thrown for testing Admin Login ...", adminThrown);

		// check if Exception thrown in case Company login fails
		boolean compThrown = false;
		try {
			CompanyFacade compF = (CompanyFacade) couponSystem.login("CoffeCoffe", "home", ClientType.COMPANY);
		} catch (WrongLoginInputException e) {
			compThrown = true;
		}
		Assert.assertTrue(" \n test31 \n WrongLoginInputException not thrown for testing Company Login ...",
				compThrown);

		// check if Exception thrown in case Customer login fails
		boolean custThrown = false;
		try {
			CustomerFacade custF = (CustomerFacade) couponSystem.login("shabtay", "122121", ClientType.CUSTOMER);
		} catch (WrongLoginInputException e) {
			custThrown = true;
		}
		Assert.assertTrue(" \n test31 \n WrongLoginInputException not thrown for testing Customer Login ...",
				custThrown);
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
