package shabtay.coupon.system.DBDAO;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import shabtay.coupon.system.common.CouponType;
import shabtay.coupon.system.entities.Company;
import shabtay.coupon.system.entities.Coupon;
import shabtay.coupon.system.exceptions.WrongLoginInputException;

/**
 * This Interface methods is Implemented by CompanyDBDAO class
 * 
 * @author Shabtay Shalem
 *
 */

public interface CompanyDAO {

	/**
	 * Creates new company
	 * 
	 * @param company
	 *            is the company to add
	 */
	void createCompany(Company company);

	/**
	 * Removes a Company
	 * 
	 * @param company
	 *            The company to be removed
	 */
	void removeCompany(Company company);

	/**
	 * Updates Company
	 * 
	 * @param company
	 *            The company to be updated
	 */
	void updateCompany(Company company);

	/**
	 * Get Company details by Company Id
	 * 
	 * @param id
	 *            company Id
	 * @return Company object
	 */
	Company getCompany(long id);

	/**
	 * Get all the Companies details from Data Base
	 * 
	 * @return Collection of Company
	 */
	Collection<Company> getAllCompanies();

	/**
	 * Get all coupons from DB
	 * 
	 * @return Collection of Coupons
	 */
	Collection<Coupon> getCoupons();

	/**
	 * After Login to CompanyDBDAO is successfully then able to use its methods
	 * 
	 * @param compName
	 *            company name
	 * @param password
	 *            company password
	 * @return true or false
	 * @throws WrongLoginInputException
	 *             in case login failed
	 */
	boolean login(String compName, String password) throws WrongLoginInputException;

	Company getCompanyByName(String companyName);

	/**
	 * Get List of coupons by type
	 * 
	 * @param compId
	 *            companyId
	 * @param type
	 *            coupon type
	 * @return List of Coupon objects
	 */
	List<Coupon> findCompanyCouponByType(long compId, CouponType type);

	/**
	 * 
	 * @param compId
	 *            company Id
	 * @param minimumPrice
	 *            minimum price to enter
	 * @param maximumPrice
	 *            maximum price to enter
	 * @return List of Coupon  objects by price
	 */
	List<Coupon> findCompanyCouponByPrice(long compId, double minimumPrice, double maximumPrice);

	/**
	 * 
	 * @param compId
	 *            COmpany Id
	 * @param startingDate
	 *            start date of coupon
	 * @param endingDate
	 *            end date of coupon
	 * @return List of Coupon objects between dates
	 */
	List<Coupon> findCouponBetweenDates(long compId, Date startingDate, Date endingDate);

}
