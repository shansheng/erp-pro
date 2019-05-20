/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsoperationseq.entity;


import com.pt.core.persistence.DataEntity;
import com.pt.modules.mes.mesworkshift.entity.MesWorkshift;
import com.pt.modules.sys.entity.Office;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 工序管理Entity
 * @author 郑利
 * @version 2018-05-19
 */
public class MdsOperationSeq extends DataEntity<MdsOperationSeq> {
	
	private static final long serialVersionUID = 1L;
	private String mdsRouteId;		// 工艺id
	private String operationSeqNo;		// 工序号
	private String operationSeqDesc;		// 工序描述
	private String operationSeqNoNext;		// 下道工序号
	private String planSetupTime;		// 计划准结时间：本工序所需的每批计划的生产准备和结束时间。>0
	private String innerOrder;		// 排序号 开始工序1 结束工序 999
	private String mdsOperationTypeId;		// 工种id
	private String sysOfficeId;		// 加工部门
	private String planRunTime;		// 计划加工时间：本工序加工物料所需的加工时间定额标准。>0
	private String planWaitTime;		// 计划等待时间：从被加工物料到达本工序工作地起，至开始加工之间所需的时间,包括排队时间。>0
	private String planMoveTime;		// 计划转移时间：从本工序完成后到下一工序准备时间前，物料在两工序之间发生转移时所需的时间。>0
	private String planBufferTime;		// 计划缓冲时间： 在工序作业单生成时，在生成最早开工日期和最晚完工日期时，用缓冲时间可进行调整。
	private String costRationTime;		// 成本工时，定额工时
	private String mdsWorkcenterId;		// 加工中心
	private String keyFlag;		// 关键工序标记：'N' --否；  'Y' --是
	private String moveBatch;		// 转移批量：转移批量 上道工序向下道工序转移的批量. >0
	private String balanceBatch;		// 均衡批量
	private String inventoryFlag;		// 工序是否存在在制品库存状态：工序是否存在在制品库存状态，N --否；Y --是
	private String avarageBatchFlag;		// 是否使用平均批量的标记：N --否；Y --是
	private String subrouteFlag;		// 是否采用子工艺：N --否；Y --是
	private String jitFlag;		// 是否JIT驱动点标记：JIT驱动点： JIT拉动的首工序N --否；Y --是
	private String controlledFlag;		// 是否受控的标记：若此工序是非受控的，则在工序作业单生成时，无此工序的相关信息   N --否；Y --是
	private String stepFlag;		// 是否有工步 N --否；Y --是
	private String filterFlag;		// 是否是筛选工序：N --否；Y --是
	private String neckFlag;		// 瓶颈工序标识：N --否；Y --是
	private String collaborativeFlag;		// 外协工序标记：N --否；Y --是
	private String firstItemCheckFlag;		// 首件是否需要检验标记：N --否；Y --是
	private String inspectFlag;		// 检验工序  N --否；Y --是
	private String ncFlag;		// 是否NC程序 N --否；Y --是
	private String seqType;		// 类型：1，FO工序；2，AO
	private String item01;		// 备用字段
	private String item02;		// 备用字段
	private String item03;		// 备用字段
	private String item04;		// 备用字段
	private String item05;		// 备用字段
	
	
	
	//表外字段
	private String mdsRouteNo;
	private String mdsRouteEd;
	private String mdsRouteDesc;
	private String mdsWorkcenterName;
	private String workCenterCode;
	private String sysOfficeName;
	

	public String getSysOfficeName() {
		return sysOfficeName;
	}

	public void setSysOfficeName(String sysOfficeName) {
		this.sysOfficeName = sysOfficeName;
	}

	
	public String getWorkCenterCode() {
		return workCenterCode;
	}

	public void setWorkCenterCode(String workCenterCode) {
		this.workCenterCode = workCenterCode;
	}

	public String getMdsWorkcenterName() {
		return mdsWorkcenterName;
	}

	public void setMdsWorkcenterName(String mdsWorkcenterName) {
		this.mdsWorkcenterName = mdsWorkcenterName;
	}

	public MdsOperationSeq() {
		super();
	}

	public MdsOperationSeq(String id){
		super(id);
	}

	@ExcelField(title="工艺id", align=2, sort=1)
	public String getMdsRouteId() {
		return mdsRouteId;
	}

	public void setMdsRouteId(String mdsRouteId) {
		this.mdsRouteId = mdsRouteId;
	}
	
	@ExcelField(title="工序号", align=2, sort=2)
	public String getOperationSeqNo() {
		return operationSeqNo;
	}

	public void setOperationSeqNo(String operationSeqNo) {
		this.operationSeqNo = operationSeqNo;
	}
	
	@ExcelField(title="工序描述", align=2, sort=3)
	public String getOperationSeqDesc() {
		return operationSeqDesc;
	}

	public void setOperationSeqDesc(String operationSeqDesc) {
		this.operationSeqDesc = operationSeqDesc;
	}
	
	@ExcelField(title="下道工序号", align=2, sort=4)
	public String getOperationSeqNoNext() {
		return operationSeqNoNext;
	}

	public void setOperationSeqNoNext(String operationSeqNoNext) {
		this.operationSeqNoNext = operationSeqNoNext;
	}
	
	@ExcelField(title="计划准结时间：本工序所需的每批计划的生产准备和结束时间。>0", align=2, sort=5)
	public String getPlanSetupTime() {
		return planSetupTime;
	}

	public void setPlanSetupTime(String planSetupTime) {
		this.planSetupTime = planSetupTime;
	}
	
	@ExcelField(title="排序号 开始工序1 结束工序 999", align=2, sort=6)
	public String getInnerOrder() {
		return innerOrder;
	}

	public void setInnerOrder(String innerOrder) {
		this.innerOrder = innerOrder;
	}
	
	@ExcelField(title="工种id", align=2, sort=7)
	public String getMdsOperationTypeId() {
		return mdsOperationTypeId;
	}

	public void setMdsOperationTypeId(String mdsOperationTypeId) {
		this.mdsOperationTypeId = mdsOperationTypeId;
	}
	
	@ExcelField(title="加工部门", fieldType=String.class, value="", align=2, sort=8)
	public String getSysOfficeId() {
		return sysOfficeId;
	}

	public void setSysOfficeId(String sysOfficeId) {
		this.sysOfficeId = sysOfficeId;
	}
	
	@ExcelField(title="计划加工时间：本工序加工物料所需的加工时间定额标准。>0", align=2, sort=9)
	public String getPlanRunTime() {
		return planRunTime;
	}



	public void setPlanRunTime(String planRunTime) {
		this.planRunTime = planRunTime;
	}
	
	@ExcelField(title="计划等待时间：从被加工物料到达本工序工作地起，至开始加工之间所需的时间,包括排队时间。>0", align=2, sort=10)
	public String getPlanWaitTime() {
		return planWaitTime;
	}

	public void setPlanWaitTime(String planWaitTime) {
		this.planWaitTime = planWaitTime;
	}
	
	@ExcelField(title="计划转移时间：从本工序完成后到下一工序准备时间前，物料在两工序之间发生转移时所需的时间。>0", align=2, sort=11)
	public String getPlanMoveTime() {
		return planMoveTime;
	}

	public void setPlanMoveTime(String planMoveTime) {
		this.planMoveTime = planMoveTime;
	}
	
	@ExcelField(title="计划缓冲时间： 在工序作业单生成时，在生成最早开工日期和最晚完工日期时，用缓冲时间可进行调整。", align=2, sort=12)
	public String getPlanBufferTime() {
		return planBufferTime;
	}

	public void setPlanBufferTime(String planBufferTime) {
		this.planBufferTime = planBufferTime;
	}
	
	@ExcelField(title="成本工时，定额工时", align=2, sort=13)
	public String getCostRationTime() {
		return costRationTime;
	}

	public void setCostRationTime(String costRationTime) {
		this.costRationTime = costRationTime;
	}
	


	@ExcelField(title="加工中心", fieldType=String.class, value="", align=2, sort=14)
	public String getMdsWorkcenterId() {
		return mdsWorkcenterId;
	}

	public void setMdsWorkcenterId(String mdsWorkcenterId) {
		this.mdsWorkcenterId = mdsWorkcenterId;
	}
	
	@ExcelField(title="关键工序标记：'N' --否；  'Y' --是", align=2, sort=15)
	public String getKeyFlag() {
		return keyFlag;
	}

	public void setKeyFlag(String keyFlag) {
		this.keyFlag = keyFlag;
	}
	
	@ExcelField(title="转移批量：转移批量 上道工序向下道工序转移的批量. >0", align=2, sort=16)
	public String getMoveBatch() {
		return moveBatch;
	}

	public void setMoveBatch(String moveBatch) {
		this.moveBatch = moveBatch;
	}
	
	@ExcelField(title="均衡批量", align=2, sort=17)
	public String getBalanceBatch() {
		return balanceBatch;
	}

	public void setBalanceBatch(String balanceBatch) {
		this.balanceBatch = balanceBatch;
	}
	
	@ExcelField(title="工序是否存在在制品库存状态：工序是否存在在制品库存状态，N --否；Y --是", align=2, sort=18)
	public String getInventoryFlag() {
		return inventoryFlag;
	}

	public void setInventoryFlag(String inventoryFlag) {
		this.inventoryFlag = inventoryFlag;
	}
	
	@ExcelField(title="是否使用平均批量的标记：N --否；Y --是", align=2, sort=19)
	public String getAvarageBatchFlag() {
		return avarageBatchFlag;
	}

	public void setAvarageBatchFlag(String avarageBatchFlag) {
		this.avarageBatchFlag = avarageBatchFlag;
	}
	
	@ExcelField(title="是否采用子工艺：N --否；Y --是", align=2, sort=20)
	public String getSubrouteFlag() {
		return subrouteFlag;
	}

	public void setSubrouteFlag(String subrouteFlag) {
		this.subrouteFlag = subrouteFlag;
	}
	
	@ExcelField(title="是否JIT驱动点标记：JIT驱动点： JIT拉动的首工序N --否；Y --是", align=2, sort=21)
	public String getJitFlag() {
		return jitFlag;
	}

	public void setJitFlag(String jitFlag) {
		this.jitFlag = jitFlag;
	}
	
	@ExcelField(title="是否受控的标记：若此工序是非受控的，则在工序作业单生成时，无此工序的相关信息   N --否；Y --是", align=2, sort=22)
	public String getControlledFlag() {
		return controlledFlag;
	}

	public void setControlledFlag(String controlledFlag) {
		this.controlledFlag = controlledFlag;
	}
	
	@ExcelField(title="是否有工步 N --否；Y --是", align=2, sort=23)
	public String getStepFlag() {
		return stepFlag;
	}

	public void setStepFlag(String stepFlag) {
		this.stepFlag = stepFlag;
	}
	
	@ExcelField(title="是否是筛选工序：N --否；Y --是", align=2, sort=24)
	public String getFilterFlag() {
		return filterFlag;
	}

	public void setFilterFlag(String filterFlag) {
		this.filterFlag = filterFlag;
	}
	
	@ExcelField(title="瓶颈工序标识：N --否；Y --是", align=2, sort=25)
	public String getNeckFlag() {
		return neckFlag;
	}

	public void setNeckFlag(String neckFlag) {
		this.neckFlag = neckFlag;
	}
	
	@ExcelField(title="外协工序标记：N --否；Y --是", align=2, sort=26)
	public String getCollaborativeFlag() {
		return collaborativeFlag;
	}

	public void setCollaborativeFlag(String collaborativeFlag) {
		this.collaborativeFlag = collaborativeFlag;
	}
	
	@ExcelField(title="首件是否需要检验标记：N --否；Y --是", align=2, sort=27)
	public String getFirstItemCheckFlag() {
		return firstItemCheckFlag;
	}

	public void setFirstItemCheckFlag(String firstItemCheckFlag) {
		this.firstItemCheckFlag = firstItemCheckFlag;
	}
	
	@ExcelField(title="检验工序  N --否；Y --是", align=2, sort=28)
	public String getInspectFlag() {
		return inspectFlag;
	}

	public void setInspectFlag(String inspectFlag) {
		this.inspectFlag = inspectFlag;
	}
	
	@ExcelField(title="是否NC程序 N --否；Y --是", align=2, sort=29)
	public String getNcFlag() {
		return ncFlag;
	}

	public void setNcFlag(String ncFlag) {
		this.ncFlag = ncFlag;
	}
	
	@ExcelField(title="类型：1，FO工序；2，AO", align=2, sort=30)
	public String getSeqType() {
		return seqType;
	}

	public void setSeqType(String seqType) {
		this.seqType = seqType;
	}
	
	@ExcelField(title="备用字段", align=2, sort=31)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="备用字段", align=2, sort=32)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="备用字段", align=2, sort=33)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="备用字段", align=2, sort=34)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="备用字段", align=2, sort=35)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}

	public String getMdsRouteNo() {
		return mdsRouteNo;
	}

	public void setMdsRouteNo(String mdsRouteNo) {
		this.mdsRouteNo = mdsRouteNo;
	}

	public String getMdsRouteEd() {
		return mdsRouteEd;
	}

	public void setMdsRouteEd(String mdsRouteEd) {
		this.mdsRouteEd = mdsRouteEd;
	}

	public String getMdsRouteDesc() {
		return mdsRouteDesc;
	}

	public void setMdsRouteDesc(String mdsRouteDesc) {
		this.mdsRouteDesc = mdsRouteDesc;
	}



	
}