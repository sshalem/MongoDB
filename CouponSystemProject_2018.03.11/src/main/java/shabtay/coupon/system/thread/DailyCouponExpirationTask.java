package shabtay.coupon.system.thread;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shabtay.coupon.system.DBDAO.CouponDBDAO;

/**
 * Class DailyCouponExpirationTask implemets Runnable interface
 * in this class defined the logic of the thread 
 * @author shabtay shalem
 *
 */
@Component
public class DailyCouponExpirationTask implements Runnable {

	@Autowired
	CouponDBDAO couponDBDAO;

	/**
	 * run the Daily Coupon Expiration Task
	 * deletes Coupons which there endDate is expired
	 */
	@Override
	public void run() {
		while (true) {
			
			// this try/catch block runs every 24hours 
			// once it awake , it will perform "deleteExpiredCoupons" 
			try {
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				if (!couponDBDAO.getAllCoupon().isEmpty()) {
					couponDBDAO.deleteExpiredCoupons(new Date());
				}
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}
