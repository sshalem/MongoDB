package com.example.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "CUSTOMER_NAME")
	private String custName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER , cascade = { CascadeType.DETACH , CascadeType.REFRESH })
	@JoinTable(name = "Customer_Coupon", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "coupon_id"))
	private Collection<Coupon> coupons;

	public Customer(String custName, String password) {
		super();
		this.custName = custName;
		this.password = password;		
	}

	public Customer() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "\nCustomer [id=" + id + ", custName=" + custName + ", password=" + password + "]";
	}

}
