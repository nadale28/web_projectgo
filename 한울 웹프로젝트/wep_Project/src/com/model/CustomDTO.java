package com.model;

import java.sql.Timestamp;
import java.util.ArrayList;



public class CustomDTO 
{
	private String id;
	private String passwd;
	private String name;
	private String email;
	private String email2;
	private Timestamp reg_date;
	private String zipcode;
	private String address;
	private String phone1, phone2, phone3;
	private String detail_address;
	private String user_type;
	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getDetail_address() {
		return detail_address;
	}

	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getId() 
	{ return id; }
	
	public void setId(String id) 
	{ this.id = id; }
	
	public String getPasswd() 
	{ return passwd; }
	
	public void setPasswd(String passwd) 
	{ this.passwd = passwd; }
	
	public String getName() 
	{ return name; }
	
	public void setName(String name) 
	{ this.name = name; }
	
	
	
	public String getEmail() 
	{ return email; }
	
	public void setEmail(String email) 
	{ this.email = email; }
	
	
	
	public Timestamp getReg_date() 
	{ return reg_date; }
	
	public void setReg_date(Timestamp reg_date)
	{ this.reg_date = reg_date; }
	
	public String getZipcode() 
	{ return zipcode; }

	public void setZipcode(String zipcode) 
	{ this.zipcode = zipcode; }

	public String getAddress() 
	{ return address; }

	public void setAddress(String address) 
	{ this.address = address; }

	public ArrayList<CustomDTO> select(String jumin1, String jumin2) {
		// TODO Auto-generated method stub
		return null;
	}
}