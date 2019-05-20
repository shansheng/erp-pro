/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.client.entity;


import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 客户管理Entity
 * @author 郑利
 * @version 2018-08-21
 */
public class Client extends DataEntity<Client> {
	
	private static final long serialVersionUID = 1L;
	private String clientManager;		// 客户经理
	private String valid;		// 状态
	private String phone;		// 电话
	private String email;		// 邮箱
	private String fax;		// 传真
	private String postcode;		// 邮编
	private String clientClass;		// 类型
	private String industry;		// 行业
	private String region;		// 地区
	private String website;		// 网站
	private String address;		// 地址
	private String remake;		// 备注
	private String upload;		// 附件
	private String clientNo;		// 客户编号
	private String shortTitle;		// 简称
	private String clientName;		// 客户名称
	
	public Client() {
		super();
	}

	public Client(String id){
		super(id);
	}
	
	@ExcelField(title="客户编号", align=2, sort=1)
	public String getClientNo() {
		return clientNo;
	}

	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}
	
	@ExcelField(title="客户名称", align=2, sort=2)
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@ExcelField(title="简称", align=2, sort=3)
	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	@ExcelField(title="联系人", align=2, sort=4)
	public String getClientManager() {
		return clientManager;
	}

	public void setClientManager(String clientManager) {
		this.clientManager = clientManager;
	}
	
	@ExcelField(title="电话", align=2, sort=5)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@ExcelField(title="客户类型", dictType="client_class", align=2, sort=6)
	public String getClientClass() {
		return clientClass;
	}

	public void setClientClass(String clientClass) {
		this.clientClass = clientClass;
	}
	
	@ExcelField(title="是否有效", dictType="valid", align=2, sort=7)
	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
	
	@ExcelField(title="邮箱", align=2, sort=8)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@ExcelField(title="地区", align=2, sort=9)
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	@ExcelField(title="地址", align=2, sort=10)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@ExcelField(title="描述", align=2, sort=11)
	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}
	
	
	
	
	/*@ExcelField(title="传真", align=2, sort=5)*/
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	/*@ExcelField(title="邮编", align=2, sort=6)*/
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
	
	/*@ExcelField(title="行业", align=2, sort=8)*/
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	
	
	/*@ExcelField(title="网站", align=2, sort=10)*/
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	/*@ExcelField(title="附件", align=2, sort=13)*/
	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}
	
	
	
	
}