/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvinbill.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.pt.common.utils.excel.annotation.ExcelField;
import com.pt.core.persistence.DataEntity;

import com.pt.modules.wms.wmsinventory.entity.WmsInventory;

/**
 * 入库单Entity
 * @author 龚志强
 * @version 2018-05-15
 */
public class WmsInvInBill extends DataEntity<WmsInvInBill> {
	
	private static final long serialVersionUID = 1L;
	private String billNo;		// 申请单号
	private String billType;		// 入库类型
	private String applySysUserId;		// 申请人
	private Date applyDate;		// 申请时间
	private WmsInventory wmsInventory;		// 库房
	private String status;		// 状态0-已生成，10-部分入库，20-已完成
	private String item01;		// item_01
	private String item02;		// item_02
	private String item03;		// item_03
	private String item04;		// item_04
	private String item05;		// item_05
	private List<WmsInvInBillList> wmsInvInBillListList = Lists.newArrayList();		// 子表列表
	
	
	private String applySysUserName;
	
	public String getApplySysUserName() {
		return applySysUserName;
	}

	public void setApplySysUserName(String applySysUserName) {
		this.applySysUserName = applySysUserName;
	}

	public WmsInvInBill() {
		super();
	}

	public WmsInvInBill(String id){
		super(id);
	}

	@ExcelField(title="申请单号", align=2, sort=1)
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	@ExcelField(title="入库类型", align=2, sort=2)
	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	@ExcelField(title="申请人", align=2, sort=3)
	public String getApplySysUserId() {
		return applySysUserId;
	}

	public void setApplySysUserId(String applySysUserId) {
		this.applySysUserId = applySysUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="申请时间不能为空")
	@ExcelField(title="申请时间", align=2, sort=4)
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@NotNull(message="库房不能为空")
	@ExcelField(title="库房", align=2, sort=5)
	public WmsInventory getWmsInventory() {
		return wmsInventory;
	}

	public void setWmsInventory(WmsInventory wmsInventory) {
		this.wmsInventory = wmsInventory;
	}
	
	@ExcelField(title="状态0-已生成，10-部分入库，20-已完成", dictType="mds_in_bill_status", align=2, sort=6)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@ExcelField(title="item_01", align=2, sort=7)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="item_02", align=2, sort=8)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="item_03", align=2, sort=9)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="item_04", align=2, sort=10)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="item_05", align=2, sort=11)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}
	
	public List<WmsInvInBillList> getWmsInvInBillListList() {
		return wmsInvInBillListList;
	}

	public void setWmsInvInBillListList(List<WmsInvInBillList> wmsInvInBillListList) {
		this.wmsInvInBillListList = wmsInvInBillListList;
	}
}