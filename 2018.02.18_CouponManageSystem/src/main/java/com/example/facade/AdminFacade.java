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
import com.example.exceptions.WrongPasswordOrUserNameOrClientTypeException;

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

		if ( (password.equals("1234")) && (name.equals("admin"))) {
			return this;
		}
		throw new WrongPasswordOrUserNameOrClientTypeException(
				" .......  wrong Admin  UserName/Password/ClientType entered ......");
	}

	public void createCompany(Company newCompany) {

		if (companyDBDAO.getCompanyByName(newCompany.getCompName()) != null)
			throw new CompanyAlreadyExistException("   " + newCompany.getCompName() + "  ... already exists...\n");
		companyDBDAO.createCompany(newCompany);
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
	}

	public void createCustomer(Customer newCustomer) {

		if (customerDBDAO.getCustomerByName(newCustomer.getCustName()) != null)
			throw new CustomerAlreadyExistException("   " + newCustomer.getCustName() + "  ... already exists...\n");
		customerDBDAO.createCustomer(newCustomer);
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

}
