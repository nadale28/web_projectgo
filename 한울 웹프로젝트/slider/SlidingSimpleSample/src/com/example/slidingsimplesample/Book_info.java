package com.example.slidingsimplesample;

import java.sql.Date;

public class Book_info {
	private String title;
	private String author;
	private String image;
	private String bookcase;
	private float grade;
	private String reg_start_date; //읽기시작
	private String reg_end_date; //읽기끝
	private int total_page;
	private int current_page;
	
	
	
	
	public int getCurrent_page() {
		return current_page;
	}
	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}
	public String getReg_start_date() {
		return reg_start_date;
	}
	public void setReg_start_date(String reg_start_date) {
		this.reg_start_date = reg_start_date;
	}
	public String getReg_end_date() {
		return reg_end_date;
	}
	public void setReg_end_date(String reg_end_date) {
		this.reg_end_date = reg_end_date;
	}
	public int getTotal_page() {
		return total_page;
	}
	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}
	public String getImage_source() {
		return image;
	}
	public String getBookcase() {
		return bookcase;
	}
	public void setBookcase(String bookcase) {
		this.bookcase = bookcase;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public void setImage_source(String image_source) {
		this.image = image_source;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	
}
