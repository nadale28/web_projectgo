package com.example.slidingsimplesample;

public class Book_info_detail {
	
	
	private String title;
	private String camera_pic;//등록한 사진
	private String record_time;//문구를 남긴 시간
	private int record_page;//문구를 남긴 페이지
	private String record_content; //적은 문구
	private String voice_file;//음성녹음 위치
	
	
	public String getVoice_file() {
		return voice_file;
	}
	public void setVoice_file(String voice_file) {
		this.voice_file = voice_file;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCamera_pic() {
		return camera_pic;
	}
	public void setCamera_pic(String camera_pic) {
		this.camera_pic = camera_pic;
	}
	public String getRecord_time() {
		return record_time;
	}
	public void setRecord_time(String record_time) {
		this.record_time = record_time;
	}
	public int getRecord_page() {
		return record_page;
	}
	public void setRecord_page(int record_page) {
		this.record_page = record_page;
	}
	public String getRecord_content() {
		return record_content;
	}
	public void setRecord_content(String record_content) {
		this.record_content = record_content;
	}
	
	
	
}
