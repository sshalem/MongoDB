package com.mongoDB.collection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Company")
@TypeAlias("Company")
public class Company {

	@Id
	private String id;
	@Field("name")
	private String compaName;
	private String compEmail;
	@DBRef
	private List<Coupon> coupons = new ArrayList<>();

	public Company() {
		super();
	}

	public Company(String compaName, String compEmail) {
		super();
		this.compaName = compaName;
		this.compEmail = compEmail;
	}

	public String getCompaName() {
		return compaName;
	}

	public void setCompaName(String compaName) {
		this.compaName = compaName;
	}

	public String getCompEmail() {
		return compEmail;
	}

	public void setCompEmail(String compEmail) {
		this.compEmail = compEmail;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public String getId() {
		return id;
	}

	public void addCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}

	public void removeCoupon(Coupon coupon) {
		this.coupons.remove(coupon);
	}

	@Override
	public String toString() {
		return "\nCompany [id=" + id + ", compaName=" + compaName + ", compEmail=" + compEmail + ", \ncoupons=" + coupons
				+ "]";
	}

}
