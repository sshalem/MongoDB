package shabtay.coupon.system.DBDAO;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shabtay.coupon.system.common.CouponType;
import shabtay.coupon.system.entities.Coupon;
import shabtay.coupon.system.entities.Customer;
import shabtay.coupon.system.exceptions.WrongLoginInputException;
import shabtay.coupon.system.repository.CouponRepository;
import shabtay.coupon.system.repository.CustomerRepository;

/**
 * CustomerDBDAO implements the methods from Interface CustomerDAO and uses
 * CustomerRepository interface and CouponRepository interface to execute the
 * methods in the class
 * 
 * @author Shabtay Shalem
 *
 */
@Component
public class CustomerDBDAO implements CustomerDAO {

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	CouponRepository couponRepo;

	public CustomerDBDAO() {
	}

	@Override
	public boolean login(String custName, String password) throws WrongLoginInputException {

		for (Customer customer : getAllCustomer()) {
			if (customer.getCustName().equals(custName) && (customer.getPassword().equals(password))) {
				return true;
			}
		}
		throw new WrongLoginInputException(" *********   wrong UserName / Password / ClientType  .........");
	}

	@Override
	public void createCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public void removeCustomer(Customer customer) {
		customerRepo.delete(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public Customer getCustomer(long id) {
		return customerRepo.findOne(id);
	}

	@Override
	public Collection<Customer> getAllCustomer() {
		return (Collection<Customer>) customerRepo.findAll();
	}

	@Override
	public Collection<Coupon> getCoupons() {
		return (Collection<Coupon>) couponRepo.findAll();
	}

	@Override
	public Customer getCustomerByName(String custName) {
		return customerRepo.findByCustName(custName);
	}

	@Override
	public List<Coupon> findAllPurchasedCouponsByPrice(long custId, double minimumPrice, double maximumPrice) {
		return customerRepo.findAllPurchasedCouponsByPrice(custId, minimumPrice, maximumPrice);
	}

	@Override
	public List<Coupon> findAllPurchasedCouponsType(long custId, CouponType type) {
		return customerRepo.findAllPurchasedCouponsType(custId, type);
	}

	@Override
	public Coupon findCouponInCustomerDB(Long custId, String couponTitle) {
		return customerRepo.findCouponInCustomerDB(custId, couponTitle);
	}

}
