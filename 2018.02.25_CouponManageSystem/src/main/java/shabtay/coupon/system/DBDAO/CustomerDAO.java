package shabtay.coupon.system.DBDAO;

import java.util.Collection;
import java.util.List;
import shabtay.coupon.system.common.CouponType;
import shabtay.coupon.system.entities.Coupon;
import shabtay.coupon.system.entities.Customer;
import shabtay.coupon.system.exceptions.WrongLoginInputException;

/**
 * This Interface methods is Implemented by CustomerDBDAO class
 * 
 * @author Shabtay Shalem
 *
 */
public interface CustomerDAO {

	/**
	 * Creates new customer
	 * 
	 * @param customer
	 *            is the customer to add
	 */
	void createCustomer(Customer customer);

	/**
	 * 
	 * @param customer The customer to be removed
	 */
	void removeCustomer(Customer customer);

	/**
	 * 
	 * @param customer the customer to be updated
	 */
	void updateCustomer(Customer customer);

	/**
	 * 
	 * @param id
	 *            customer Id
	 * @return Customer object
	 */
	Customer getCustomer(long id);

	/**
	 * Get all the Customers details from Data Base
	 * 
	 * @return Collection of Customer
	 */
	Collection<Customer> getAllCustomer();

	/**
	 * Get all Coupons
	 * 
	 * @return Collection of Coupons
	 */
	Collection<Coupon> getCoupons();

	/**
	 * After Login to CustomerDBDAO is successfully then able to use its methods
	 * 
	 * @param custName
	 *            customer name
	 * @param password
	 *            customer password
	 * @return true or false
	 * @throws WrongLoginInputException
	 *             in case login failed
	 */
	boolean login(String custName, String password) throws WrongLoginInputException;

	/**
	 * Get Customer details by its name
	 * 
	 * @param custName
	 *            customer name
	 * @return Customer object
	 */
	Customer getCustomerByName(String custName);

	/**
	 * 
	 * @param custId
	 *            customer Id
	 * @param minimumPrice
	 *            minimum price to enter
	 * @param maximumPrice
	 *            maximum price to enter
	 * @return List of Coupon by price
	 */
	List<Coupon> findAllPurchasedCouponsByPrice(long custId, double minimumPrice, double maximumPrice);

	/**
	 * Get All purchased coupons by couponType
	 * 
	 * @param custId
	 *            customer ID
	 * @param type
	 *            CouponType
	 * @return List of Coupon by type
	 */
	List<Coupon> findAllPurchasedCouponsType(long custId, CouponType type);

	/**
	 * 
	 * @param custId - customer Id
	 * @param couponTitle - coupon title (name of coupon)
	 * @return Coupon object
	 */
	Coupon findCouponInCustomerDB(Long custId, String couponTitle);

}
