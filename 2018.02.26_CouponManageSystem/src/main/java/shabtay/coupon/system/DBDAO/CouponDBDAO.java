package shabtay.coupon.system.DBDAO;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shabtay.coupon.system.entities.Coupon;
import shabtay.coupon.system.repository.CouponRepository;

/**
 * CouponDBDAO implements the methods from Interface CouponDAO and uses
 * CouponRepository interface to execute the methods in the class
 * 
 * @author Shabtay Shalem
 *
 */
@Component
public class CouponDBDAO implements CouponDAO {

	@Autowired
	CouponRepository couponRepo;

	@Override
	public void createCoupon(Coupon coupon) {
		couponRepo.save(coupon);
	}

	@Override
	public void removeCoupon(Coupon coupon) {
		couponRepo.delete(coupon);
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		couponRepo.save(coupon);
	}

	@Override
	public Coupon getCoupon(long id) {
		return couponRepo.findOne(id);
	}

	@Override
	public Collection<Coupon> getAllCoupon() {
		return (Collection<Coupon>) couponRepo.findAll();
	}

	@Override
	public Coupon getCouponByTitle(String title) {
		return couponRepo.findByTitle(title);
	}

}
