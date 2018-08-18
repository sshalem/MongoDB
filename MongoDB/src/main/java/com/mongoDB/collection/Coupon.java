package com.mongoDB.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Coupon")
@TypeAlias("Coupon")
public class Coupon {

	@Id
	private String id;
	private String title;
	private int amount;
	private double price;
	private String companyId;
	private String companyName;

	public Coupon() {
		super();
	}

	public Coupon(String title, int amount, double price, String companyId, String companyName) {
		super();
		this.title = title;
		this.amount = amount;
		this.price = price;
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "\nCoupon [id=" + id + ", title=" + title + ", amount=" + amount + ", price=" + price + ", companyId="
				+ companyId + ", companyName=" + companyName + "]";
	}

}
