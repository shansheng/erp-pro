/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.supplier.entity;

import org.hibernate.validator.constraints.Length;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 供应商管理Entity
 * @author 郑利
 * @version 2018-08-29
 */
public class Supplier extends DataEntity<Supplier> {
	
	private static final long serialVersionUID = 1L;
	private String supplierNo;		// 供应商编码
	private String suName;		// 名称
	private String shortName;		// 简称
	private String linkman;		// 联系人
	private String phone;		// 电话
	private String suClass;		// 类型
	private String valid;		// 是否有效
	private String industry;		// 行业
	private String agent;		// 采购负责人
	private String payment;		// 付款条件
	private String bankName;		// 银行名称
	private String bankLog;		// 银行账户
	private String region;		// 地区
	private String postcode;		// 邮编
	private String address;		// 地址
	private String remake;		// 描述
	private String agentName;
	public Supplier() {
		super();
	}

	public Supplier(String id){
		super(id);
	}

	@ExcelField(title="供应商编码", align=2, sort=1)
	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	
	@ExcelField(title="名称", align=2, sort=2)
	public String getSuName() {
		return suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}
	
	@ExcelField(title="简称", align=2, sort=3)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	@ExcelField(title="联系人", align=2, sort=4)
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	@Length(min=7, max=16, message="电话长度必须介于 7 和 16 之间")
	@ExcelField(title="电话", align=2, sort=5)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@ExcelField(title="类型", dictType="su_class", align=2, sort=6)
	public String getSuClass() {
		return suClass;
	}

	public void setSuClass(String suClass) {
		this.suClass = suClass;
	}
	
	@ExcelField(title="是否有效", dictType="valid", align=2, sort=7)
	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
	
	@ExcelField(title="行业", align=2, sort=8)
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@ExcelField(title="采购负责人", fieldType=String.class, value="", align=2, sort=9)
	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
	
	@ExcelField(title="付款条件", dictType="payment", align=2, sort=10)
	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	@ExcelField(title="银行名称", align=2, sort=11)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Length(min=0, max=19, message="银行账户长度必须介于 0 和 19 之间")
	@ExcelField(title="银行账户", align=2, sort=12)
	public String getBankLog() {
		return bankLog;
	}

	public void setBankLog(String bankLog) {
		this.bankLog = bankLog;
	}
	
	@ExcelField(title="地区", align=2, sort=13)
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	@Length(min=0, max=6, message="邮编长度必须介于 0 和 6 之间")
	@ExcelField(title="邮编", align=2, sort=14)
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@ExcelField(title="地址", align=2, sort=15)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
	@ExcelField(title="描述", align=2, sort=16)
	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
}