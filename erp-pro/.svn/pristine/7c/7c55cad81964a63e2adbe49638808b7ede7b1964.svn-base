package com.pt.modules.mds.mdsprocurementplanfinish.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pt.core.persistence.DataEntity;
import com.pt.modules.sys.entity.User;

/**
 * 采购状态查看页list表
 * @author Administrator
 *
 */
public class MdsPcmPlanFinishList extends DataEntity<MdsPcmPlanFinishList>{
	
	private static final long serialVersionUID = 1L;
	private String mdsProcurmentPlanId;		// 采购计划表id
	private int arrivalNum;		// 到货数量
	private int qualified;		// 合格数
	private int disqualification;		// 不合格数
	/*private User createBy;	//到货确认人
	private Date createDate;	//到货确认时间
	private User updateBy;		//检验人
	private Date updateDate;   //检验时间 */
	private int inStorage;		// 入库数
	private User inStorageBy;		// 入库人
	private Date inStorageDate;		// 入库时间
	private String checkoutState;   //检验状态
	
	public MdsPcmPlanFinishList() {
		super();
	}

	public MdsPcmPlanFinishList(String id){
		super(id);
	}
	
	public MdsPcmPlanFinishList(MdsProcurementPlanFinish mdsProcurementPlanFinish) {
		this.mdsProcurmentPlanId = mdsProcurementPlanFinish.getId();
	}
	
	public String getMdsProcurmentPlanId() {
		return mdsProcurmentPlanId;
	}
	public void setMdsProcurmentPlanId(String mdsProcurmentPlanId) {
		this.mdsProcurmentPlanId = mdsProcurmentPlanId;
	}
	public int getArrivalNum() {
		return arrivalNum;
	}
	public void setArrivalNum(int arrivalNum) {
		this.arrivalNum = arrivalNum;
	}
	public int getQualified() {
		return qualified;
	}
	public void setQualified(int qualified) {
		this.qualified = qualified;
	}
	public int getDisqualification() {
		return disqualification;
	}
	public void setDisqualification(int disqualification) {
		this.disqualification = disqualification;
	}
	public int getInStorage() {
		return inStorage;
	}
	public void setInStorage(int inStorage) {
		this.inStorage = inStorage;
	}
	public User getInStorageBy() {
		return inStorageBy;
	}
	public void setInStorageBy(User inStorageBy) {
		this.inStorageBy = inStorageBy;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInStorageDate() {
		return inStorageDate;
	}
	public void setInStorageDate(Date inStorageDate) {
		this.inStorageDate = inStorageDate;
	}
	public String getCheckoutState() {
		return checkoutState;
	}
	public void setCheckoutState(String checkoutState) {
		this.checkoutState = checkoutState;
	}
}
