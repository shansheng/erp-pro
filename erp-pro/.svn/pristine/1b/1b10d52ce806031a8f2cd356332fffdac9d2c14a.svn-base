/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvbalancebill.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 库存结转Entity
 * @author hexl
 * @version 2018-05-18
 */
public class WmsInvBalanceBill extends DataEntity<WmsInvBalanceBill> {
	
	private static final long serialVersionUID = 1L;
	private String year;		// 会计年度
	private String period;		// 期段号
	private Date startDate;		// 开始日期
	private Date endDate;		// 结束日期
	private String operatSysUserId;		// 结转人
	private Date operatDate;		// 结转日期
	private String wmsInventoryId;		// 结转库房ID
	private String priceDifferentCash;		// 折溢金额
	private String currencyType;		// 币种
	private String wmsFiscalPeriodId;		// 会计期段ID
	private String invQualityType;		// 库房类型ID
	private String invItemSourceType;		// 库存来源   F自制  G国内采购
	private String userName;
	private String inventoryName;
	
	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public WmsInvBalanceBill() {
		super();
	}

	public WmsInvBalanceBill(String id){
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
	
	@ExcelField(title="结转人", align=2, sort=5)
	public String getOperatSysUserId() {
		return operatSysUserId;
	}

	public void setOperatSysUserId(String operatSysUserId) {
		this.operatSysUserId = operatSysUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="结转日期不能为空")
	@ExcelField(title="结转日期", align=2, sort=6)
	public Date getOperatDate() {
		return operatDate;
	}

	public void setOperatDate(Date operatDate) {
		this.operatDate = operatDate;
	}
	
	@ExcelField(title="结转库房ID", align=2, sort=13)
	public String getWmsInventoryId() {
		return wmsInventoryId;
	}

	public void setWmsInventoryId(String wmsInventoryId) {
		this.wmsInventoryId = wmsInventoryId;
	}
	
	@ExcelField(title="折溢金额", align=2, sort=14)
	public String getPriceDifferentCash() {
		return priceDifferentCash;
	}

	public void setPriceDifferentCash(String priceDifferentCash) {
		this.priceDifferentCash = priceDifferentCash;
	}
	
	@ExcelField(title="币种", align=2, sort=15)
	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	@ExcelField(title="会计期段ID", align=2, sort=16)
	public String getWmsFiscalPeriodId() {
		return wmsFiscalPeriodId;
	}

	public void setWmsFiscalPeriodId(String wmsFiscalPeriodId) {
		this.wmsFiscalPeriodId = wmsFiscalPeriodId;
	}
	
	@ExcelField(title="库房类型ID", align=2, sort=17)
	public String getInvQualityType() {
		return invQualityType;
	}

	public void setInvQualityType(String invQualityType) {
		this.invQualityType = invQualityType;
	}
	
	@ExcelField(title="库存来源   F自制  G国内采购", align=2, sort=18)
	public String getInvItemSourceType() {
		return invItemSourceType;
	}

	public void setInvItemSourceType(String invItemSourceType) {
		this.invItemSourceType = invItemSourceType;
	}
	
}