package shabtay.coupon.system.facade;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shabtay.coupon.system.DBDAO.CouponDBDAO;
import shabtay.coupon.system.DBDAO.CustomerDBDAO;
import shabtay.coupon.system.common.CouponType;
import shabtay.coupon.system.entities.Coupon;
import shabtay.coupon.system.entities.Customer;
import shabtay.coupon.system.exceptions.AmountOfCouponsZeroException;
import shabtay.coupon.system.exceptions.CouponAlreadyPurchsedByCustomerException;
import shabtay.coupon.system.exceptions.CouponExpiredException;
import shabtay.coupon.system.exceptions.WrongLoginInputException;

/**
 * Class CustomerFacade gives the Customer to perform the following actions ,
 * after login was successfully: purchaseCoupon, getAllPurchasedCoupons,
 * getAllPurchasedCouponsByType, getAllPurchasedCouponsByPrice,
 * getCouponInCustomerDB
 * 
 * 
 * @author Shabtay Shalem
 *
 */
@Component
public class CustomerFacade implements CouponClientFacade {

	@Autowired
	CouponDBDAO couponDBDAO;
	@Autowired
	CustomerDBDAO customerDBDAO;

	/**
	 * The Id to used once specific customer was logged in
	 */
	private long customerId;

	public CustomerFacade() {

	}

	/**
	 * if login was successful customer is able to perform all methods in this class
	 */
	@Override
	public CouponClientFacade login(String name, String password) throws WrongLoginInputException {
		if (customerDBDAO.login(name, password) == true) {
			customerId = customerDBDAO.getCustomerByName(name).getId();
			return this;
		}
		return null;
	}

	/**
	 * Purchase coupon by Customer
	 * 
	 * @param coupon
	 *            the Coupon customer wants to purchase
	 * @throws CouponAlreadyPurchsedByCustomerException
	 *             thrown in case coupon already purchased by customer
	 * @throws AmountOfCouponsZeroException
	 *             thrown if coupon amount is zero
	 * @throws CouponExpiredException
	 *             thrown if coupon date is expired
	 */
	public void purchaseCoupon(Coupon coupon)
			throws CouponAlreadyPurchsedByCustomerException, AmountOfCouponsZeroException, CouponExpiredException {

		Collection<Coupon> customerCoupons = getAllPurchasedCoupons();
		Customer customer = customerDBDAO.getCustomer(customerId);
		Coupon couponFromDB = couponDBDAO.getCouponByTitle(coupon.getTitle());

		if (couponFromDB != null) {
			if (checkIfCouponDateExpired(couponFromDB.getEndDate())) {
				throw new CouponExpiredException("...... Coupon Expired ...........");
			}
			if (couponFromDB.getAmount() == 0) {
				throw new AmountOfCouponsZeroException(" ........ No more Coupons left ......");
			}
		}

		if (getCouponFromCustomerDB(coupon.getTitle()) != null) {
			throw new CouponAlreadyPurchsedByCustomerException(
					" ...... Coupon already purchased by Customer " + customer.getCustName() + "..... ");
		}

		customerCoupons.add(couponFromDB);
		couponFromDB.setAmount(couponFromDB.getAmount() - 1);
		couponDBDAO.updateCoupon(couponFromDB);
		customer.setCoupons(customerCoupons);
		customerDBDAO.updateCustomer(customer);
	}

	/**
	 * Get all purchased coupons by customer
	 * 
	 * @return Collection of Coupon
	 */
	public Collection<Coupon> getAllPurchasedCoupons() {
		return customerDBDAO.getCustomer(customerId).getCoupons();
	}

	/**
	 * Get all purchased coupons by type per customer
	 * 
	 * @param couponType couponType coupon type (See CouponType ENUM)
	 * @return List of Coupon
	 */
	public List<Coupon> getAllPurchasedCouponsByType(CouponType couponType) {
		return customerDBDAO.findAllPurchasedCouponsType(this.customerId, couponType);
	}

	/**
	 * Get all purchased coupons between range of prices per customer
	 * 
	 * @param minimumPrice
	 *            minimum price that was entered
	 * @param maximumPrice
	 *            minimum price that was entered
	 * @return List of Coupon
	 */
	public List<Coupon> getAllPurchasedCouponsByPrice(double minimumPrice, double maximumPrice) {
		return customerDBDAO.findAllPurchasedCouponsByPrice(this.customerId, minimumPrice, maximumPrice);
	}

	/**
	 * Get Coupon From Customer DB by title
	 * @param title name of coupon
	 * @return Coupon object 
	 */
	public Coupon getCouponFromCustomerDB(String title) {
		return customerDBDAO.findCouponInCustomerDB(this.customerId, title);
	}

	private boolean checkIfCouponDateExpired(Date endDate) {
		Date currentDate = Calendar.getInstance().getTime();
		return endDate.before(currentDate);
	}

}
