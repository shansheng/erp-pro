/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysmarket.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 销售管理Entity
 * 
 * @author 郑利
 * @version 2018-08-23
 */
public class SysMarket extends DataEntity<SysMarket> {

	private static final long serialVersionUID = 1L;
	private String clientId; // 客户id
	private String contractNo; // 合同编号
	private Date contractDate; // 合同签订时间
	private Date deliveryDate; // 交货日期
	private String contractClass; // 订单类型
	private String money; // money
	private String paymentMethod; // 付款方式
	private String principal; // 负责人
	private String principalName; // 负责人
	private String contractStatus; // 合同状态
	private String assign; // 是否下达令号
	private Date assignDate; // 下达令号时间
	private String shipments; // 是否发货
	private Date beginContractDate; // 开始 合同签订时间
	private Date endContractDate; // 结束 合同签订时间
	
	private String clientName;
	private String mdsItemName;

	private String proMdsItemCode;
	private String clientManager; // 客户经理
	private String status; // 状态
	private String phone; // 电话
	private String email; // 邮箱
	private String postcode; // 邮编
	private String clientClass; // 类型
	private String region; // 地区
	private String address; // 地址
	private String clientNo; // 客户编号
	private String normalStatus;
	private Date skillStartDate;		// 技术开始时间
	private Date skillEndDate;		// 技术结束时间
	private Date purchaseStartDate;		// 采购开始时间
	private Date purchaseEndDate;		// 采购完成时间
	private String milestoneStatus;		// milestone_status
	private Date productionStartDate;		// 生产开始时间
	private Date productionEndDate;		// 生产结束时间
	private String miItemId;
	private String milestoneStatuss;		// milestone_statuss
	private String shipShow;
	private Date shipmentsDate;
	
	private String remake;//备注
	
	private String debt; //尚欠余额
	private String totalPay;//累计还款
	
	private String isKp; //是否开票
	private Date kpDate; //开票日期
	
	private String retPayRecord; //回款记录
	private String shipmentsRecord;//发货记录
	
	private List<SysMarketList> SysMarketListList = Lists.newArrayList();		// 子表列表
	public SysMarket() {
		super();
	}

	public SysMarket(String id) {
		super(id);
	}

	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	

	@ExcelField(title = "合同编号", align = 2, sort = 4)
	public String getContractNo() {
		return contractNo;
	}

	
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title = "合同签订时间", align = 2, sort = 5)
	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title = "约定交货日期", align = 2, sort = 6)
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@ExcelField(title = "订单类型", dictType = "contract_class", align = 2, sort = 7)
	public String getContractClass() {
		return contractClass;
	}

	public void setContractClass(String contractClass) {
		this.contractClass = contractClass;
	}

	@ExcelField(title = "金额(万)", align = 2, sort = 8)
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@ExcelField(title = "付款方式",dictType = "payment", align = 2, sort = 9)
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	
	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@ExcelField(title = "合同状态",  dictType ="contract_status", align = 2, sort = 11)
	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	@ExcelField(title = "是否下达令号", dictType = "assign", align = 2, sort = 12)
	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title = "下达令号时间", align = 2, sort = 13)
	public Date getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}

	@ExcelField(title = "是否发货", dictType = "shipments", align = 2, sort = 14)
	public String getShipments() {
		return shipments;
	}

	public void setShipments(String shipments) {
		this.shipments = shipments;
	}

	public Date getBeginContractDate() {
		return beginContractDate;
	}

	public void setBeginContractDate(Date beginContractDate) {
		this.beginContractDate = beginContractDate;
	}

	public Date getEndContractDate() {
		return endContractDate;
	}

	public void setEndContractDate(Date endContractDate) {
		this.endContractDate = endContractDate;
	}
	@ExcelField(title = "客户名称", align = 2, sort = 1)
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getMdsItemName() {
		return mdsItemName;
	}

	public void setMdsItemName(String mdsItemName) {
		this.mdsItemName = mdsItemName;
	}

	public String getClientManager() {
		return clientManager;
	}

	public void setClientManager(String clientManager) {
		this.clientManager = clientManager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getClientClass() {
		return clientClass;
	}

	public void setClientClass(String clientClass) {
		this.clientClass = clientClass;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClientNo() {
		return clientNo;
	}

	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}


	@ExcelField(title = "负责人", align = 2, sort = 10)
	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getProMdsItemCode() {
		return proMdsItemCode;
	}

	public void setProMdsItemCode(String proMdsItemCode) {
		this.proMdsItemCode = proMdsItemCode;
	}

	public List<SysMarketList> getSysMarketListList() {
		return SysMarketListList;
	}

	public void setSysMarketListList(List<SysMarketList> sysMarketListList) {
		SysMarketListList = sysMarketListList;
	}

	public String getNormalStatus() {
		return normalStatus;
	}

	public void setNormalStatus(String normalStatus) {
		this.normalStatus = normalStatus;
	}

	public Date getSkillStartDate() {
		return skillStartDate;
	}

	public void setSkillStartDate(Date skillStartDate) {
		this.skillStartDate = skillStartDate;
	}

	public Date getSkillEndDate() {
		return skillEndDate;
	}

	public void setSkillEndDate(Date skillEndDate) {
		this.skillEndDate = skillEndDate;
	}

	public Date getPurchaseStartDate() {
		return purchaseStartDate;
	}

	public void setPurchaseStartDate(Date purchaseStartDate) {
		this.purchaseStartDate = purchaseStartDate;
	}

	public Date getPurchaseEndDate() {
		return purchaseEndDate;
	}

	public void setPurchaseEndDate(Date purchaseEndDate) {
		this.purchaseEndDate = purchaseEndDate;
	}

	public String getMilestoneStatus() {
		return milestoneStatus;
	}

	public void setMilestoneStatus(String milestoneStatus) {
		this.milestoneStatus = milestoneStatus;
	}

	public Date getProductionStartDate() {
		return productionStartDate;
	}

	public void setProductionStartDate(Date productionStartDate) {
		this.productionStartDate = productionStartDate;
	}

	public Date getProductionEndDate() {
		return productionEndDate;
	}

	public void setProductionEndDate(Date productionEndDate) {
		this.productionEndDate = productionEndDate;
	}

	public String getMiItemId() {
		return miItemId;
	}

	public void setMiItemId(String miItemId) {
		this.miItemId = miItemId;
	}

	public String getMilestoneStatuss() {
		return milestoneStatuss;
	}

	public void setMilestoneStatuss(String milestoneStatuss) {
		this.milestoneStatuss = milestoneStatuss;
	}

	public String getShipShow() {
		return shipShow;
	}

	public void setShipShow(String shipShow) {
		this.shipShow = shipShow;
	}
	@ExcelField(title = "发货时间",  align = 2, sort = 15)
	public Date getShipmentsDate() {
		return shipmentsDate;
	}

	public void setShipmentsDate(Date shipmentsDate) {
		this.shipmentsDate = shipmentsDate;
	}
	@ExcelField(title = "备注",  align = 2, sort = 22)
	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}
	@ExcelField(title = "尚欠余额",  align = 2, sort = 19)
	public String getDebt() {
		return debt;
	}

	public void setDebt(String debt) {
		this.debt = debt;
	}
	@ExcelField(title = "累计还款",  align = 2, sort = 20)
	public String getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(String totalPay) {
		this.totalPay = totalPay;
	}
	@ExcelField(title = "是否开票", dictType = "is_kp", align = 2, sort = 16)
	public String getIsKp() {
		return isKp;
	}

	public void setIsKp(String isKp) {
		this.isKp = isKp;
	}
	@ExcelField(title = "开票时间", align = 2, sort = 17)
	public Date getKpDate() {
		return kpDate;
	}

	public void setKpDate(Date kpDate) {
		this.kpDate = kpDate;
	}
	@ExcelField(title = "回款记录",  align = 2, sort = 18)
	public String getRetPayRecord() {
		return retPayRecord;
	}

	public void setRetPayRecord(String retPayRecord) {
		this.retPayRecord = retPayRecord;
	}
	@ExcelField(title = "发货记录", align = 2, sort = 21)
	public String getShipmentsRecord() {
		return shipmentsRecord;
	}

	public void setShipmentsRecord(String shipmentsRecord) {
		this.shipmentsRecord = shipmentsRecord;
	}


}