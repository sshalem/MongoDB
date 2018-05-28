package com.ajaxRest;

import java.io.Serializable;

//now ready to travel in the web cosmos
public class Coupon implements Serializable {

	private int id;
	private String title;

	public Coupon(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Coupon() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", name=" + title + "]";
	}

}
