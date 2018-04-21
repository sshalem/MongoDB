package shabtay.coupon.system.WebService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import shabtay.coupon.system.common.ClientType;
import shabtay.coupon.system.entities.Company;
import shabtay.coupon.system.entities.Customer;
import shabtay.coupon.system.entry.CouponSystem;
import shabtay.coupon.system.exceptions.CompanyAlreadyExistException;
import shabtay.coupon.system.exceptions.CustomerAlreadyExistException;
import shabtay.coupon.system.exceptions.WrongLoginInputException;
import shabtay.coupon.system.facade.AdminFacade;

//after I upload the file into the server I need to remove the cross origin *
//since the html and the server are in the same domain + port
@RestController
@CrossOrigin("*")
public class AdminWebService {

	@Autowired
	private CouponSystem couponSystem;
	
//	private CompanyFacade getFacade(HttpServletRequest request) {
//	if (request.getSession().getAttribute("coompanyFacade") == null) {
//		// user bypass login
//		// decide what to do ....
//		return null;
//	}
//	CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("coompanyFacade");
//	return companyFacade;
//
//	// CompanyFacade result = (new CompanyFacade()).login("TEVA", "12345", "Company");
//	// return result;
//}
//
//// @CrossOrigin(origins = "*")
//@RequestMapping(value = "/getcoupon", method = RequestMethod.GET)
//public Coupon getCoupon(HttpServletRequest request) {
//	CompanyFacade companyFacade = getFacade(request);
//	return companyFacade.getCoupon();
//}
	
	private AdminFacade getFacade() throws WrongLoginInputException, InterruptedException {
	
	// the line below is only for testing , what really sholud be is
	// AdminFacade af = (AdminFacade) couponSystem.login(username, password, type);
	AdminFacade af = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
	return af;		
	}
	
	
	/*
	 *  Admin Facade - Company methods 
	 */
	@RequestMapping(value = "/adminws/company", method = RequestMethod.GET)
	public Collection<Company> getAllCompanies() throws WrongLoginInputException, InterruptedException{
		AdminFacade af = getFacade();
		return af.getAllCompanies();	 	
	}
	
	@RequestMapping(value = "/adminws/company/id/{id}", method = RequestMethod.GET)
	public Company getCompanyById(@PathVariable("id")int id) throws WrongLoginInputException, InterruptedException{
		AdminFacade af = getFacade();		
		return af.getCompany(id); 
	}
	
	@RequestMapping(value = "/adminws/company/name/{name}", method = RequestMethod.GET)
	public Company getCompanyByName(@PathVariable("name")String name) throws WrongLoginInputException, InterruptedException {
		AdminFacade af = getFacade();		
		return af.getCompByName(name);
	}
	
	@RequestMapping(value = "/adminws/company", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCompany(@RequestBody Company comp) throws WrongLoginInputException, InterruptedException, CompanyAlreadyExistException {
		System.out.println(comp);
		AdminFacade af = getFacade();
		af.createCompany(comp);				
	}
	
	@RequestMapping(value = "/adminws/company/update/{id}", method = RequestMethod.PUT)
	public void updateCompany(@RequestBody Company comp, @PathVariable("id")int id) throws WrongLoginInputException, InterruptedException {
		AdminFacade af = getFacade();		
		Collection<Company> companies = af.getAllCompanies();
		for (Company company : companies) {
			if(company.getId() == id)
			{
				if(comp.getCompName() != null)
					company.setCompName(comp.getCompName());
				if(comp.getEmail() != null)
					company.setEmail(comp.getEmail());
				if(comp.getPassword() != null)
					company.setPassword(comp.getPassword());
				af.updateCompany(company);
			}			
		}		
	} 
	 
	@RequestMapping(value = "/adminws/company/delete/{id}", method = RequestMethod.DELETE)
	public void deleteCompany(@PathVariable("id")int id) throws WrongLoginInputException, InterruptedException
	{
		AdminFacade af = getFacade();
		af.removeCompany(af.getCompany(id));
	}	
	
	// --------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------
	
	/* 
	 *  Admin Facade - Customer methods 
	 */
	
	@RequestMapping(value = "/adminws/customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCustomer(@RequestBody Customer customer) throws WrongLoginInputException, InterruptedException, CustomerAlreadyExistException {
		System.out.println(customer);
		AdminFacade af = getFacade();
		af.createCustomer(customer);
	}
	
	@RequestMapping(value = "/adminws/customer", method = RequestMethod.GET)
	public Collection<Customer> getAllCustomers() throws WrongLoginInputException, InterruptedException {
		AdminFacade af = getFacade();
		return af.getAllCustomer();
	}
	
	@RequestMapping(value = "/adminws/customer/id/{id}", method = RequestMethod.GET)
	public Customer getCustomerById(@PathVariable("id")int id) throws WrongLoginInputException, InterruptedException {
		AdminFacade af = getFacade();
		return af.getCustomer(id);
	}
	
	@RequestMapping(value = "/adminws/customer/name/{name}", method = RequestMethod.GET)
	public Customer getCustomerByName(@PathVariable("name")String name) throws WrongLoginInputException, InterruptedException {
		AdminFacade af = getFacade();
		return af.getCustomerByName(name);
	}
	
	@RequestMapping(value = "/adminws/customer/update/{id}", method = RequestMethod.PUT)
	public void updateCustomer(@RequestBody Customer customer, @PathVariable("id")int id) throws WrongLoginInputException, InterruptedException {
		AdminFacade af = getFacade();		
		Collection<Customer> customers = af.getAllCustomer();
		for (Customer c : customers) {
			if(c.getId() == id) {
				if(customer.getCustName() != null)
					c.setCustName(customer.getCustName());
				if(customer.getPassword() != null)
					c.setPassword(customer.getPassword());				
				af.updateCustomer(c);
			}
		}		
	} 
	
	@RequestMapping(value = "/adminws/customer/delete/{id}", method = RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable("id")int id) throws WrongLoginInputException, InterruptedException {
		AdminFacade af = getFacade();
		af.removeCustomer(af.getCustomer(id));
	}
	
}
