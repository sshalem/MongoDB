package com.example.facade;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.DBDAO.CompanyDBDAO;
import com.example.DBDAO.CouponDBDAO;
import com.example.DBDAO.CustomerDBDAO;
import com.example.common.ClientType;
import com.example.entities.Company;
import com.example.entities.Coupon;
import com.example.entities.Customer;
import com.example.exceptions.CompanyAlreadyExistException;
import com.example.exceptions.CustomerAlreadyExistException;
import com.example.exceptions.WrongPasswordOrUserNameOrClientType;

@Component
public class AdminFacade implements CouponClientFacade {

	@Autowired
	CompanyDBDAO companyDBDAO;

	@Autowired
	CustomerDBDAO customerDBDAO;

	@Autowired
	CouponDBDAO couponDBDAO;

	public AdminFacade() {

	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {

		if ((password.equals("1234")) && (name.equals("admin"))) {
			return this;
		}
		throw new WrongPasswordOrUserNameOrClientType(
				" .......  wrong Admin  UserName/Password/ClientType entered ......");
	}

	/*
	 *  Company methods
	 * 
	 */

	public void createCompany(Company newCompany) {
		// check if Company already exist
		try {
			checkIfCompanyExistBeforeCreate(newCompany);
			companyDBDAO.createCompany(newCompany);
		} catch (CompanyAlreadyExistException e) {
			e.printStackTrace();
			System.out.println(" \n company " + "\"" + newCompany.getCompName() + "\""
					+ " .................. already exists.....\n");
		}
	}

	public void removeCompany(Company company) {
		companyDBDAO.removeCompany(company);
	}

	public void updateCompany(Company company) {

		companyDBDAO.updateCompany(company);
	}

	public Company getCompany(long id) {
		return companyDBDAO.getCompany(id);
	}

	public Collection<Company> getAllCompanies() {
		return companyDBDAO.getAllCompanies();
	}

	public Collection<Coupon> getAllCoupon() {

		return couponDBDAO.getAllCoupon();

		// Collection<Company> companies = companyDBDAO.getAllCompanies();
		// Collection<Coupon> coupons = new ArrayList<>();
		//
		// int companyIncremant = 1;
		//
		// while (getCompany(companyIncremant) != null) {
		// coupons.addAll(companyDBDAO.getCompany(companyIncremant).getCoupons());
		// companyIncremant++;
		// }
		// return coupons;
	}

	/*
	 *  Customer methods
	 * 
	 */
	public void createCustomer(Customer newCustomer) {

		// check if Customer already exist
		try {
			checkIfCustomerExistBeforeCreate(newCustomer);
			customerDBDAO.createCustomer(newCustomer);
		} catch (CustomerAlreadyExistException e) {
			e.printStackTrace();
			System.out.println("\n customer " + "\"" + newCustomer.getCustName() + "\""
					+ "  ............... already exists.....\n");
		}
	}

	public void removeCustomer(Customer customer) {
		customerDBDAO.removeCustomer(customer);
	}

	public void updateCustomer(Customer customer) {
		customerDBDAO.updateCustomer(customer);
	}

	public Customer getCustomer(long id) {
		return customerDBDAO.getCustomer(id);
	}

	public Collection<Customer> getAllCustomer() {
		return customerDBDAO.getAllCustomer();
	}

	/*
	 * ============================================================================
	 * Help methods (non Overriden methods for this class only)
	 * ============================================================================
	 */

	private void checkIfCompanyExistBeforeCreate(Company newCompany) throws CompanyAlreadyExistException {

		Collection<Company> allCompanies = getAllCompanies();
		for (Company companyFromDB : allCompanies) {
			if (companyFromDB.getCompName().equals(newCompany.getCompName())) {
				throw new CompanyAlreadyExistException(
						" company " + "\"" + newCompany.getCompName() + "\"" + " ......... already exist ..... \n");
			}
		}
	}

	private void checkIfCustomerExistBeforeCreate(Customer newCustomer) throws CustomerAlreadyExistException {

		Collection<Customer> allCustomers = getAllCustomer();
		for (Customer customerFromDB : allCustomers) {
			if (customerFromDB.getCustName().equals(newCustomer.getCustName())) {
				throw new CustomerAlreadyExistException(" customer " + "\"" + newCustomer.getCustName() + "\""
						+ " .......... already exist ......... \n");
			}
		}
	}

}
