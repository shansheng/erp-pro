/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sm.smuser.entity;


import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 商户信息Entity
 * @author hexl
 * @version 2018-03-13
 */
public class SmUser extends DataEntity<SmUser> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 会员名称
	private String phone;		// 电话
	private String mobile;		// 手机
	private String photo;		// 照片
	private String industry;		// 行业
	private String position;		// 职位
	private String business_scope;		// 业务范围
	private String project_info;		// 项目情况
	private String user_level;		// 会员等级
	private String inner_order;		// 排序号
	private String addr;		// 地址
	
	public SmUser() {
		super();
	}

	public SmUser(String id){
		super(id);
	}

	@ExcelField(title="会员名称", align=2, sort=7)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="电话", align=2, sort=8)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@ExcelField(title="手机", align=2, sort=9)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@ExcelField(title="照片", align=2, sort=10)
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@ExcelField(title="行业", align=2, sort=11)
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@ExcelField(title="职位", align=2, sort=12)
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	@ExcelField(title="业务范围", align=2, sort=13)
	public String getBusiness_scope() {
		return business_scope;
	}

	public void setBusiness_scope(String business_scope) {
		this.business_scope = business_scope;
	}
	
	@ExcelField(title="项目情况", align=2, sort=14)
	public String getProject_info() {
		return project_info;
	}

	public void setProject_info(String project_info) {
		this.project_info = project_info;
	}
	
	@ExcelField(title="会员等级", align=2, sort=15)
	public String getUser_level() {
		return user_level;
	}

	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}
	
	@ExcelField(title="排序号", align=2, sort=16)
	public String getInner_order() {
		return inner_order;
	}

	public void setInner_order(String inner_order) {
		this.inner_order = inner_order;
	}
	
	@ExcelField(title="地址", align=2, sort=17)
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}