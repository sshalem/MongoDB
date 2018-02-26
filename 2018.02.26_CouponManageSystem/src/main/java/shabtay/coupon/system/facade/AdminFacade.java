package shabtay.coupon.system.facade;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shabtay.coupon.system.DBDAO.CompanyDBDAO;
import shabtay.coupon.system.DBDAO.CouponDBDAO;
import shabtay.coupon.system.DBDAO.CustomerDBDAO;
import shabtay.coupon.system.entities.Company;
import shabtay.coupon.system.entities.Coupon;
import shabtay.coupon.system.entities.Customer;
import shabtay.coupon.system.exceptions.CompanyAlreadyExistException;
import shabtay.coupon.system.exceptions.CustomerAlreadyExistException;
import shabtay.coupon.system.exceptions.WrongLoginInputException;

/**
 * Class AdminFacade gives the administrator access to all the data of Customers
 * and Companies
 * 
 * @author Shabtay Shalem
 *
 */
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
	public CouponClientFacade login(String name, String password) throws WrongLoginInputException {

		if ((password.equals("1234")) && (name.equals("admin"))) {
			return this;
		}
		throw new WrongLoginInputException(" .......  wrong Admin  UserName/Password/ClientType entered ......");
	}

	/**
	 * Creates new company
	 * 
	 * @param newCompany
	 *            is the company to add
	 * @throws CompanyAlreadyExistException
	 *             Exception is thrown in case company with same CompanyName exists
	 */
	public void createCompany(Company newCompany) throws CompanyAlreadyExistException {

		if (companyDBDAO.getCompanyByName(newCompany.getCompName()) != null)
			throw new CompanyAlreadyExistException("   " + newCompany.getCompName() + "  ... already exists...\n");
		companyDBDAO.createCompany(newCompany);
	}

	/**
	 * Removes a Company
	 * 
	 * @param company
	 *            The company to be removed
	 */
	public void removeCompany(Company company) {
		companyDBDAO.removeCompany(company);
	}

	/**
	 * Updates Company
	 * 
	 * @param company
	 *            The company to be updated
	 */
	public void updateCompany(Company company) {
		companyDBDAO.updateCompany(company);
	}

	/**
	 * Get Company details by Company Id
	 * 
	 * @param id
	 *            company Id
	 * @return Company
	 */
	public Company getCompany(long id) {
		return companyDBDAO.getCompany(id);
	}

	/**
	 * Get Company details by Company name
	 * 
	 * @param name
	 *            Company name
	 * @return Company
	 */
	public Company getCompByName(String name) {
		return companyDBDAO.getCompanyByName(name);
	}

	/**
	 * Get all the Companies in Data Base
	 * 
	 * @return Collection of Company
	 */
	public Collection<Company> getAllCompanies() {
		return companyDBDAO.getAllCompanies();
	}

	public Collection<Coupon> getAllCoupon() {
		return couponDBDAO.getAllCoupon();
	}

	/**
	 * Creates new Customer
	 * 
	 * @param newCustomer
	 *            is the Customer to add
	 * @throws CustomerAlreadyExistException
	 *             Exception is thrown in case Customer with same CustomerName
	 *             exists
	 */
	public void createCustomer(Customer newCustomer) throws CustomerAlreadyExistException {

		if (customerDBDAO.getCustomerByName(newCustomer.getCustName()) != null)
			throw new CustomerAlreadyExistException("   " + newCustomer.getCustName() + "  ... already exists...\n");
		customerDBDAO.createCustomer(newCustomer);
	}

	/**
	 * Remove Customer From DB
	 * 
	 * @param customer
	 *            The Customer to be removed
	 */
	public void removeCustomer(Customer customer) {
		customerDBDAO.removeCustomer(customer);
	}

	/**
	 * Update Customer From DB
	 * 
	 * @param customer
	 *            The customer to be updated
	 */
	public void updateCustomer(Customer customer) {
		customerDBDAO.updateCustomer(customer);
	}

	/**
	 * Get Customer details by Customer Id
	 * 
	 * @param id
	 *            customer ID
	 * @return Customer 
	 */
	public Customer getCustomer(long id) {
		return customerDBDAO.getCustomer(id);
	}

	/**
	 * Get all customers from DB
	 * 
	 * @return Collection of Customer
	 */
	public Collection<Customer> getAllCustomer() {
		return customerDBDAO.getAllCustomer();
	}

	/**
	 * Get Customer details by customer name
	 * 
	 * @param custName
	 *            Customer name
	 * @return Customer 
	 */
	public Customer getCustomerByName(String custName) {
		return customerDBDAO.getCustomerByName(custName);
	}

}
