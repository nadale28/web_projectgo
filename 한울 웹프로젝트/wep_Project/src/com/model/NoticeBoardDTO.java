package com.model;

import java.sql.Date;

public class NoticeBoardDTO {
private int no,num, reg_People, rec_People,readCnt,regId;
public int getRegId() {
	return regId;
}
public void setRegId(int regId) {
	this.regId = regId;
}
private String deadline,id;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getDeadline() {
	return deadline;
}
public void setDeadline(String deadline) {
	this.deadline = deadline;
}
public int getNo() {
	return no;
}
public void setNo(int no) {
	this.no = no;
}
private Date start_Day,end_Day;
private String region_Total,title,content,author ;
private String region1, region2, city1, city2;
public int getNum() {
	return num;
}

public void setNum(int num) {
	this.num = num;
}
public int getReg_People() {
	return reg_People;
}
public void setReg_People(int reg_People) {
	this.reg_People = reg_People;
}
public int getRec_People() {
	return rec_People;
}
public void setRec_People(int rec_People) {
	this.rec_People = rec_People;
}
public int getReadCnt() {
	return readCnt;
}
public void setReadCnt(int readCnt) {
	this.readCnt = readCnt;
}
public Date getStart_Day() {
	return start_Day;
}
public void setStart_Day(Date start_Day) {
	this.start_Day = start_Day;
}
public Date getEnd_Day() {
	return end_Day;
}
public void setEnd_Day(Date end_Day) {
	this.end_Day = end_Day;
}
public String getRegion_Total() {
	return region_Total;
}
public void setRegion_Total(String region_Total) {
	this.region_Total = region_Total;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getRegion1() {
	return region1;
}
public void setRegion1(String region1) {
	this.region1 = region1;
}
public String getRegion2() {
	return region2;
}
public void setRegion2(String region2) {
	this.region2 = region2;
}
public String getCity1() {
	return city1;
}
public void setCity1(String city1) {
	this.city1 = city1;
}
public String getCity2() {
	return city2;
}
public void setCity2(String city2) {
	this.city2 = city2;
}


}
