package com.pt.modules.sys.sysmarket.dto;

public class SysmarketDTO {
     String year; // 年份
     int count;  // 统计数量
    
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getCout() {
		return count;
	}
	public void setCout(int cout) {
		this.count = cout;
	}
	public SysmarketDTO(String year, int cout) {
		super();
		this.year = year;
		this.count = cout;
	}
	public SysmarketDTO() {
		super();
	}
     
     
}
