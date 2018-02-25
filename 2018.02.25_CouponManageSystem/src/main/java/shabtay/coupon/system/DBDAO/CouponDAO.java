package shabtay.coupon.system.DBDAO;

import java.util.Collection;

import shabtay.coupon.system.entities.Coupon;

/**
 * This Interface methods is be Implemented by CouponDBDAO class
 * 
 * @author Shabtay Shalem
 *
 */
public interface CouponDAO {

	/**
	 * Create new Coupon
	 * 
	 * @param coupon
	 *            coupon to create
	 */
	void createCoupon(Coupon coupon);

	/**
	 * Remove coupon from DB
	 * 
	 * @param coupon
	 *            coupon to be removed
	 */
	void removeCoupon(Coupon coupon);

	/**
	 * Update Coupon details
	 * 
	 * @param coupon
	 *            coupon to be update
	 */
	void updateCoupon(Coupon coupon);

	/**
	 * Get cooupon details by Coupon Id
	 * 
	 * @param id
	 *            coupon Id
	 * @return Coupon object
	 */
	Coupon getCoupon(long id);

	/**
	 * Get all coupons
	 * 
	 * @return Collection of Coupons
	 */
	Collection<Coupon> getAllCoupon();

	/**
	 * Get coupon details by its Title
	 * @param title coupon title
	 * @return Coupon object
	 */
	Coupon getCouponByTitle(String title);

}
