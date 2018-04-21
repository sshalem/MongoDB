package shabtay.coupon.system.WebService;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import shabtay.coupon.system.common.ClientType;
import shabtay.coupon.system.common.CouponType;
import shabtay.coupon.system.entities.Coupon;
import shabtay.coupon.system.entry.CouponSystem;
import shabtay.coupon.system.exceptions.AmountOfCouponsZeroException;
import shabtay.coupon.system.exceptions.CouponAlreadyPurchsedByCustomerException;
import shabtay.coupon.system.exceptions.CouponExpiredException;
import shabtay.coupon.system.exceptions.WrongLoginInputException;
import shabtay.coupon.system.facade.CustomerFacade;

//after I upload the file into the server I need to remove the cross origin *
//since the html and the server are in the same domain + port
@RestController
@CrossOrigin("*")
public class CustomerWebService {

	@Autowired
	private CouponSystem couponSystem;
	
	private CustomerFacade getFacade() throws WrongLoginInputException, InterruptedException {
		CustomerFacade cf = (CustomerFacade) couponSystem.login("odel", "1234", ClientType.CUSTOMER);
		return cf;
	}
	
	@RequestMapping(value = "/customerws/purchasecoupon", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void purchaseCoupon(@RequestBody Coupon coupon) 
			throws WrongLoginInputException, InterruptedException, 
			CouponAlreadyPurchsedByCustomerException, AmountOfCouponsZeroException, CouponExpiredException 
	{
		CustomerFacade cf = getFacade();
		cf.purchaseCoupon(coupon);
	}
	
	@RequestMapping(value = "/customerws/getpurchsedcoupons", method = RequestMethod.GET)
	public Collection<Coupon> getAllPurchasedCoupons() throws WrongLoginInputException, InterruptedException{
		CustomerFacade cf = getFacade();
		System.out.println(cf.getAllPurchasedCoupons());
		return cf.getAllPurchasedCoupons();
	}
	
	@RequestMapping(value = "/customerws/getpurchsedcoupons/bytype/{type}", method = RequestMethod.GET)
	public List<Coupon> getAllPurchasedCouponsByType(@PathVariable("type")String type) throws WrongLoginInputException, InterruptedException{
		CustomerFacade cf = getFacade();
		CouponType couponType = CouponType.valueOf(type.toUpperCase());
		System.out.println(cf.getAllPurchasedCouponsByType(couponType));
		return cf.getAllPurchasedCouponsByType(couponType); 
	}
	
	@RequestMapping(value = "/customerws/getpurchsedcoupons/byprice/{minimumPrice}/{maximumPrice}", method = RequestMethod.GET)
	public List<Coupon> getAllPurchasedCouponsByPrice(
			@PathVariable("minimumPrice")double minimumPrice, @PathVariable double maximumPrice)
					throws WrongLoginInputException, InterruptedException
	{
		CustomerFacade cf = getFacade();
		System.out.println();
		return cf.getAllPurchasedCouponsByPrice(minimumPrice, maximumPrice);
	}
	
	@RequestMapping(value = "/customerws/getpurchsedcoupons/bytitle/{title}", method = RequestMethod.GET)
	public Coupon getCouponbyTitle(@PathVariable("title")String title) throws WrongLoginInputException, InterruptedException {
		CustomerFacade cf = getFacade();
		System.out.println();
		return cf.getCouponFromCustomerDB(title);
	}	
	
}
