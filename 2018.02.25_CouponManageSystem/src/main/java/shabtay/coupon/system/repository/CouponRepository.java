package shabtay.coupon.system.repository;

import org.springframework.data.repository.CrudRepository;

import shabtay.coupon.system.entities.Coupon;

public interface CouponRepository extends CrudRepository<Coupon, Long> {

	Coupon findByTitle(String title);

}
