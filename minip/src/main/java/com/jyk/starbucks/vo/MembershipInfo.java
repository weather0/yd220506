package com.jyk.starbucks.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MembershipInfo {
	private String id;
	private String password;
	private String contact;
	private String tier;
	private Date signupdate;
	private int star;
	private int coupon;

	public String toString() {
		System.out.println(id + " : " + password + " : " + contact + " : " + tier + " : " + signupdate + " : " + star + " : " + coupon);

		return null;
	}

}
