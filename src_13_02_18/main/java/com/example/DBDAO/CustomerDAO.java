package com.example.DBDAO;

import java.util.Collection;

import com.example.entities.Coupon;
import com.example.entities.Customer;

public interface CustomerDAO {

	void createCustomer(Customer customer);

	void removeCustomer(Customer customer);

	void updateCustomer(Customer customer);

	Customer getCustomer(long id);

	Collection<Customer> getAllCustomer();

	Collection<Coupon> getCoupons();

	boolean login(String custName, String password);
	
	Customer getCustomerByName(String custName);

}
