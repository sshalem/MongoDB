package shabtay.coupon.system.WebService;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import shabtay.coupon.system.entities.Company;
import shabtay.coupon.system.entities.Customer;
import shabtay.coupon.system.exceptions.CompanyAlreadyExistException;
import shabtay.coupon.system.exceptions.CustomerAlreadyExistException;
import shabtay.coupon.system.exceptions.NameOrIdNotExistException;
import shabtay.coupon.system.facade.AdminFacade;

@RestController
@CrossOrigin("*")
public class AdminWebService {

	private static Logger logger = LogManager.getLogger(AdminWebService.class);
			
	private AdminFacade getFacade(HttpServletRequest request) {
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");		
		return adminFacade;
	}
	
	
	@RequestMapping(value = "/adminws/company", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity createCompany(@RequestBody Company comp, HttpServletRequest req) 
	{
		AdminFacade adminFacade = getFacade(req);		
		try {
			adminFacade.createCompany(comp);
			logger.info("AdminWebService createCompany() executed for" + comp);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_MARKDOWN).body("created successfully");
		} catch (CompanyAlreadyExistException | InterruptedException e) {
			logger.error("AdminWebService createCompany() failed ... " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/adminws/company", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getAllCompanies(HttpServletRequest req) {
		AdminFacade adminFacade = getFacade(req);
		try {
			Collection<Company> companies = adminFacade.getAllCompanies();
			logger.info("AdminWebService getAllCompanies() executed");
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_MARKDOWN).body(companies);
		} catch (InterruptedException e) {
			logger.error("AdminWebService getAllCompanies() " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}	

//	@Cacheable(value = "company" , key = "#id")
	@RequestMapping(value = "/adminws/company/id/{id}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getCompanyById(@PathVariable("id") int id, HttpServletRequest req) {
		AdminFacade adminFacade = getFacade(req);	
			try {
				Company company = adminFacade.getCompany(id);
				logger.info("AdminWebService getCompanyById() executed by id " + id );
				return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_MARKDOWN).body(company);
			} catch (InterruptedException | NameOrIdNotExistException e) { 
				logger.error("AdminWebService getCompanyById() " + e.getMessage());
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
			} 
	} 

	@RequestMapping(value = "/adminws/company/name/{name}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getCompanyByName(@PathVariable("name") String name, HttpServletRequest req) {
		AdminFacade adminFacade = getFacade(req);	
		try {
			Company company = adminFacade.getCompByName(name);
			logger.info("AdminWebService getCompanyByName() executed " + name);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_MARKDOWN).body(company);
		} catch (InterruptedException | NameOrIdNotExistException e) { 
			logger.error("AdminWebService getCompanyByName() " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	

	@RequestMapping(value = "/adminws/company/update/{id}", method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity updateCompany(@RequestBody Company comp, @PathVariable("id") int id, HttpServletRequest req)
	{
		AdminFacade adminFacade = getFacade(req);
		try {
			adminFacade.updateCompany(comp);
			logger.info("AdminWebService updateCompany() invoked " + comp);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_MARKDOWN).body("Updated successfully");
		} catch (InterruptedException | NameOrIdNotExistException e) {
			logger.error("AdminWebService updateCompany() was not updated since " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	} 

	@RequestMapping(value = "/adminws/company/delete/{id}", method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity deleteCompany(@PathVariable("id") int id, HttpServletRequest req)
	{
		AdminFacade adminFacade = getFacade(req);
		try {
			adminFacade.removeCompany(adminFacade.getCompany(id));
			logger.info("AdminWebService deleteCompany() invoked by id " + id);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_MARKDOWN).body("deleted successfully");
		} catch (InterruptedException | NameOrIdNotExistException e) {
			logger.error("AdminWebService deleteCompany() was not performed since " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}

	// --------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------

	/*
	 * Admin Facade - Customer methods
	 */

	@RequestMapping(value = "/adminws/customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity createCustomer(@RequestBody Customer customer , HttpServletRequest req){		
		AdminFacade adminFacade = getFacade(req);
		try {
			adminFacade.createCustomer(customer);
			logger.info("AdminWebService createCustomer() invoked " + customer);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_MARKDOWN).body("customer created");
		} catch (CustomerAlreadyExistException | InterruptedException e) {
			logger.error("AdminWebService createCustomer() was not performed since " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/adminws/customer", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getAllCustomers(HttpServletRequest req) {
		AdminFacade adminFacade = getFacade(req);
		try {
			Collection<Customer> customer = adminFacade.getAllCustomer();
			logger.info("AdminWebService getAllCustomers() invoked ");
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_MARKDOWN).body(customer); 
		} catch (InterruptedException e) {		
			logger.error("AdminWebService getAllCustomers() was not performed since " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	} 

	@RequestMapping(value = "/adminws/customer/id/{id}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getCustomerById(@PathVariable("id") int id, HttpServletRequest req) {
		AdminFacade adminFacade = getFacade(req);
		try {
			Customer customer = adminFacade.getCustomer(id);
			logger.info("AdminWebService getCustomerById() invoked " + id);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_MARKDOWN).body(customer);
		} catch (InterruptedException | NameOrIdNotExistException e) {
			logger.error("AdminWebService getCustomerById() was not performed since " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/adminws/customer/name/{name}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getCustomerByName(@PathVariable("name") String name, HttpServletRequest req) {
		AdminFacade adminFacade = getFacade(req);
		try {
			Customer customer = adminFacade.getCustomerByName(name);
			logger.info("AdminWebService getCustomerByName() invoked " + name);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_MARKDOWN).body(customer);
		} catch (InterruptedException | NameOrIdNotExistException e) {
			logger.error("AdminWebService getCustomerByName() was not performed since " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}	
	}

	@RequestMapping(value = "/adminws/customer/update/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity updateCustomer(@RequestBody Customer customer, @PathVariable("id") int id, HttpServletRequest req) {
		AdminFacade adminFacade = getFacade(req);		
		try {
			adminFacade.updateCustomer(customer);
			logger.info("AdminWebService updateCustomer() invoked " + customer);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_MARKDOWN).body("Updated successfully");			
		} catch (InterruptedException e) {	
			logger.error("AdminWebService updateCustomer() was not performed since " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}	 
	}

	@RequestMapping(value = "/adminws/customer/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity deleteCustomer(@PathVariable("id") int id , HttpServletRequest req) {
		AdminFacade adminFacade = getFacade(req);
		try {
			adminFacade.removeCustomer(adminFacade.getCustomer(id));
			logger.info("AdminWebService deleteCustomer() invoked ");
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_MARKDOWN).body(" Customer deleted successfully");
		} catch (InterruptedException | NameOrIdNotExistException e) {	
			logger.error("AdminWebService deleteCustomer() was not performed since " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}

}
