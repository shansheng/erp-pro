/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsfiscalperiod.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 期段管理Entity
 * @author hexl
 * @version 2018-05-12
 */
public class WmsFiscalPeriod extends DataEntity<WmsFiscalPeriod> {
	
	private static final long serialVersionUID = 1L;
	private String year;		// 会计年度
	private String period;		// 期段号
	private Date startDate;		// 开始日期
	private Date endDate;		// 结束日期
	
	public WmsFiscalPeriod() {
		super();
	}

	public WmsFiscalPeriod(String id){
		super(id);
	}

	@ExcelField(title="会计年度", align=2, sort=1)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@ExcelField(title="期段号", align=2, sort=2)
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="开始日期", align=2, sort=3)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="结束日期", align=2, sort=4)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}