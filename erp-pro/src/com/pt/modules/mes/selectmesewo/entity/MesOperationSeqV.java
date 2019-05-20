/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.selectmesewo.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 生产计划查询Entity
 * @author 郑利
 * @version 2018-06-28
 */
public class MesOperationSeqV extends DataEntity<MesOperationSeqV> {
	
	private static final long serialVersionUID = 1L;
	private String mesEwoId;		// ewo_id
	private String mesRouteId;		// 工艺id
	private String operationSeqNo;		// 工序号
	private String operationSeqDesc;		// 工序描述
	private String operationSeqNoNext;		// 下道工序号
	private String innerOrder;		// 排序号 开始工序1 结束工序 999
	private String mdsOperationTypeId;		// 工种id
	private String sysOfficeId;		// 加工部门
	private String planSetupTime;		// 计划准结时间  本工序所需的每批计划的生产准备和结束时间。>0
	private String planRunTime;		// 计划加工时间  本工序加工物料所需的加工时间定额标准。>0
	private String planWaitTime;		// 计划等待时间  从被加工物料到达本工序工作地起，至开始加工之间所需的时间,包括排队时间。>0
	private String planMoveTime;		// 计划转移时间  从本工序完成后到下一工序准备时间前，物料在两工序之间发生转移时所需的时间。>0
	private String planBufferTime;		// 计划缓冲时间   在工序作业单生成时，在生成最早开工日期和最晚完工日期时，用缓冲时间可进行调整。
	private String costRationTime;		// 成本工时，定额工时
	private String mdsWorkcenterId;		// 加工中心
	private String keyFlag;		// 关键工序标记  --否；  Y --是
	private String moveBatch;		// 转移批量  转移批量 上道工序向下道工序转移的批量. >0
	private String balanceBatch;		// 均衡批量
	private String inventoryFlag;		// 工序是否存在在制品库存状态  工序是否存在在制品库存状态，N --否；Y --是
	private String avarageBatchFlag;		// 是否使用平均批量的标记  N --否；Y --是
	private String subrouteFlag;		// 是否采用子工艺  N --否；Y --是
	private String jitFlag;		// 是否JIT驱动点标记  JIT驱动点： JIT拉动的首工序N --否；Y --是
	private String controlledFlag;		// 是否受控的标记  若此工序是非受控的，则在工序作业单生成时，无此工序的相关信息   N --否；Y --是
	private String stepFlag;		// 是否有工步 N --否；Y --是
	private String filterFlag;		// 是否是筛选工序  N --否；Y --是
	private String neckFlag;		// 瓶颈工序标识  N --否；Y --是
	private String collaborativeFlag;		// 外协工序标记  N --否；Y --是
	private String firstItemCheckFlag;		// 首件是否需要检验标记  N --否；Y --是
	private String inspectFlag;		// 检验工序  N --否；Y --是
	private String ncFlag;		// 是否NC程序 N --否；Y --是
	private String seqType;		// 类型：1，FO工序；2，AO
	private String needQty;		// 本工序需加工数量  上道工序下来的数量,首工序需加工数量=需求数量
	private String dispatchedQty;		// 已派工数量  
	private String canDispatchQty;		// 可派工数量  
	private String wasteQty;		// 本道工序废品数 
	private String invertoryQty;		// 已完成数量  
	private String redoQty;		// 返工数量
	private String exceedQty;		// 超差数量
	private String refixQty;		// 返修数量
	private Date planStartDate;		// 工序计划开工日期 
	private Date planEndDate;		// 工序计划完工日期 
	private Date realStartDate;		// 实际完工日期 
	private Date realEndDate;		// 实际开工日期 
	private Date costSetupTime;		// 计划准结时间  小时  本工序所需的每批的成本生产准备和结束时间。>0
	private Date costRunTime;		// 计划加工时间  小时  本工序加工物料所需的成本加工时间定额标准。>0
	private String firstSeqPassFlag;		// 首件检验标识  Y-已检验N- 未检验
	private String seqStatus;		// 状态  0-未确认   1-已确认   2-已下达   3-已开工    4-已完成   5-已作废   6-工艺变更
	private String seqStatusLabel;		// seq_status_label
	private String dispatchUserName;		// 姓名
	private Date dispatchDate;		// 派工时间
	private String workerUserId;		// 工人id
	private String workerUserName;		// 姓名
	private String checkUserName;		// 姓名
	private Date checkDate;		// 检验时间
	private String workcenterCode;		// 班组代码
	private String workcenterName;		// 班组名称
	
	public MesOperationSeqV() {
		super();
	}

	public MesOperationSeqV(String id){
		super(id);
	}

	@ExcelField(title="ewo_id", align=2, sort=1)
	public String getMesEwoId() {
		return mesEwoId;
	}

	public void setMesEwoId(String mesEwoId) {
		this.mesEwoId = mesEwoId;
	}
	
	@ExcelField(title="工艺id", align=2, sort=2)
	public String getMesRouteId() {
		return mesRouteId;
	}

	public void setMesRouteId(String mesRouteId) {
		this.mesRouteId = mesRouteId;
	}
	
	@ExcelField(title="工序号", align=2, sort=3)
	public String getOperationSeqNo() {
		return operationSeqNo;
	}

	public void setOperationSeqNo(String operationSeqNo) {
		this.operationSeqNo = operationSeqNo;
	}
	
	@ExcelField(title="工序描述", align=2, sort=4)
	public String getOperationSeqDesc() {
		return operationSeqDesc;
	}

	public void setOperationSeqDesc(String operationSeqDesc) {
		this.operationSeqDesc = operationSeqDesc;
	}
	
	@ExcelField(title="下道工序号", align=2, sort=5)
	public String getOperationSeqNoNext() {
		return operationSeqNoNext;
	}

	public void setOperationSeqNoNext(String operationSeqNoNext) {
		this.operationSeqNoNext = operationSeqNoNext;
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
	
	@ExcelField(title="加工部门", align=2, sort=8)
	public String getSysOfficeId() {
		return sysOfficeId;
	}

	public void setSysOfficeId(String sysOfficeId) {
		this.sysOfficeId = sysOfficeId;
	}
	
	@ExcelField(title="计划准结时间  本工序所需的每批计划的生产准备和结束时间。>0", align=2, sort=9)
	public String getPlanSetupTime() {
		return planSetupTime;
	}

	public void setPlanSetupTime(String planSetupTime) {
		this.planSetupTime = planSetupTime;
	}
	
	@ExcelField(title="计划加工时间  本工序加工物料所需的加工时间定额标准。>0", align=2, sort=10)
	public String getPlanRunTime() {
		return planRunTime;
	}

	public void setPlanRunTime(String planRunTime) {
		this.planRunTime = planRunTime;
	}
	
	@ExcelField(title="计划等待时间  从被加工物料到达本工序工作地起，至开始加工之间所需的时间,包括排队时间。>0", align=2, sort=11)
	public String getPlanWaitTime() {
		return planWaitTime;
	}

	public void setPlanWaitTime(String planWaitTime) {
		this.planWaitTime = planWaitTime;
	}
	
	@ExcelField(title="计划转移时间  从本工序完成后到下一工序准备时间前，物料在两工序之间发生转移时所需的时间。>0", align=2, sort=12)
	public String getPlanMoveTime() {
		return planMoveTime;
	}

	public void setPlanMoveTime(String planMoveTime) {
		this.planMoveTime = planMoveTime;
	}
	
	@ExcelField(title="计划缓冲时间   在工序作业单生成时，在生成最早开工日期和最晚完工日期时，用缓冲时间可进行调整。", align=2, sort=13)
	public String getPlanBufferTime() {
		return planBufferTime;
	}

	public void setPlanBufferTime(String planBufferTime) {
		this.planBufferTime = planBufferTime;
	}
	
	@ExcelField(title="成本工时，定额工时", align=2, sort=14)
	public String getCostRationTime() {
		return costRationTime;
	}

	public void setCostRationTime(String costRationTime) {
		this.costRationTime = costRationTime;
	}
	
	@ExcelField(title="加工中心", align=2, sort=15)
	public String getMdsWorkcenterId() {
		return mdsWorkcenterId;
	}

	public void setMdsWorkcenterId(String mdsWorkcenterId) {
		this.mdsWorkcenterId = mdsWorkcenterId;
	}
	
	@ExcelField(title="关键工序标记  --否；  Y --是", align=2, sort=16)
	public String getKeyFlag() {
		return keyFlag;
	}

	public void setKeyFlag(String keyFlag) {
		this.keyFlag = keyFlag;
	}
	
	@ExcelField(title="转移批量  转移批量 上道工序向下道工序转移的批量. >0", align=2, sort=17)
	public String getMoveBatch() {
		return moveBatch;
	}

	public void setMoveBatch(String moveBatch) {
		this.moveBatch = moveBatch;
	}
	
	@ExcelField(title="均衡批量", align=2, sort=18)
	public String getBalanceBatch() {
		return balanceBatch;
	}

	public void setBalanceBatch(String balanceBatch) {
		this.balanceBatch = balanceBatch;
	}
	
	@ExcelField(title="工序是否存在在制品库存状态  工序是否存在在制品库存状态，N --否；Y --是", align=2, sort=19)
	public String getInventoryFlag() {
		return inventoryFlag;
	}

	public void setInventoryFlag(String inventoryFlag) {
		this.inventoryFlag = inventoryFlag;
	}
	
	@ExcelField(title="是否使用平均批量的标记  N --否；Y --是", align=2, sort=20)
	public String getAvarageBatchFlag() {
		return avarageBatchFlag;
	}

	public void setAvarageBatchFlag(String avarageBatchFlag) {
		this.avarageBatchFlag = avarageBatchFlag;
	}
	
	@ExcelField(title="是否采用子工艺  N --否；Y --是", align=2, sort=21)
	public String getSubrouteFlag() {
		return subrouteFlag;
	}

	public void setSubrouteFlag(String subrouteFlag) {
		this.subrouteFlag = subrouteFlag;
	}
	
	@ExcelField(title="是否JIT驱动点标记  JIT驱动点： JIT拉动的首工序N --否；Y --是", align=2, sort=22)
	public String getJitFlag() {
		return jitFlag;
	}

	public void setJitFlag(String jitFlag) {
		this.jitFlag = jitFlag;
	}
	
	@ExcelField(title="是否受控的标记  若此工序是非受控的，则在工序作业单生成时，无此工序的相关信息   N --否；Y --是", align=2, sort=23)
	public String getControlledFlag() {
		return controlledFlag;
	}

	public void setControlledFlag(String controlledFlag) {
		this.controlledFlag = controlledFlag;
	}
	
	@ExcelField(title="是否有工步 N --否；Y --是", align=2, sort=24)
	public String getStepFlag() {
		return stepFlag;
	}

	public void setStepFlag(String stepFlag) {
		this.stepFlag = stepFlag;
	}
	
	@ExcelField(title="是否是筛选工序  N --否；Y --是", align=2, sort=25)
	public String getFilterFlag() {
		return filterFlag;
	}

	public void setFilterFlag(String filterFlag) {
		this.filterFlag = filterFlag;
	}
	
	@ExcelField(title="瓶颈工序标识  N --否；Y --是", align=2, sort=26)
	public String getNeckFlag() {
		return neckFlag;
	}

	public void setNeckFlag(String neckFlag) {
		this.neckFlag = neckFlag;
	}
	
	@ExcelField(title="外协工序标记  N --否；Y --是", align=2, sort=27)
	public String getCollaborativeFlag() {
		return collaborativeFlag;
	}

	public void setCollaborativeFlag(String collaborativeFlag) {
		this.collaborativeFlag = collaborativeFlag;
	}
	
	@ExcelField(title="首件是否需要检验标记  N --否；Y --是", align=2, sort=28)
	public String getFirstItemCheckFlag() {
		return firstItemCheckFlag;
	}

	public void setFirstItemCheckFlag(String firstItemCheckFlag) {
		this.firstItemCheckFlag = firstItemCheckFlag;
	}
	
	@ExcelField(title="检验工序  N --否；Y --是", align=2, sort=29)
	public String getInspectFlag() {
		return inspectFlag;
	}

	public void setInspectFlag(String inspectFlag) {
		this.inspectFlag = inspectFlag;
	}
	
	@ExcelField(title="是否NC程序 N --否；Y --是", align=2, sort=30)
	public String getNcFlag() {
		return ncFlag;
	}

	public void setNcFlag(String ncFlag) {
		this.ncFlag = ncFlag;
	}
	
	@ExcelField(title="类型：1，FO工序；2，AO", align=2, sort=31)
	public String getSeqType() {
		return seqType;
	}

	public void setSeqType(String seqType) {
		this.seqType = seqType;
	}
	
	@ExcelField(title="本工序需加工数量  上道工序下来的数量,首工序需加工数量=需求数量", align=2, sort=32)
	public String getNeedQty() {
		return needQty;
	}

	public void setNeedQty(String needQty) {
		this.needQty = needQty;
	}
	
	@ExcelField(title="已派工数量  ", align=2, sort=33)
	public String getDispatchedQty() {
		return dispatchedQty;
	}

	public void setDispatchedQty(String dispatchedQty) {
		this.dispatchedQty = dispatchedQty;
	}
	
	@ExcelField(title="可派工数量  ", align=2, sort=34)
	public String getCanDispatchQty() {
		return canDispatchQty;
	}

	public void setCanDispatchQty(String canDispatchQty) {
		this.canDispatchQty = canDispatchQty;
	}
	
	@ExcelField(title="本道工序废品数 ", align=2, sort=35)
	public String getWasteQty() {
		return wasteQty;
	}

	public void setWasteQty(String wasteQty) {
		this.wasteQty = wasteQty;
	}
	
	@ExcelField(title="已完成数量  ", align=2, sort=36)
	public String getInvertoryQty() {
		return invertoryQty;
	}

	public void setInvertoryQty(String invertoryQty) {
		this.invertoryQty = invertoryQty;
	}
	
	@ExcelField(title="返工数量", align=2, sort=37)
	public String getRedoQty() {
		return redoQty;
	}

	public void setRedoQty(String redoQty) {
		this.redoQty = redoQty;
	}
	
	@ExcelField(title="超差数量", align=2, sort=38)
	public String getExceedQty() {
		return exceedQty;
	}

	public void setExceedQty(String exceedQty) {
		this.exceedQty = exceedQty;
	}
	
	@ExcelField(title="返修数量", align=2, sort=39)
	public String getRefixQty() {
		return refixQty;
	}

	public void setRefixQty(String refixQty) {
		this.refixQty = refixQty;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="工序计划开工日期 ", align=2, sort=40)
	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="工序计划完工日期 ", align=2, sort=41)
	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="实际完工日期 ", align=2, sort=42)
	public Date getRealStartDate() {
		return realStartDate;
	}

	public void setRealStartDate(Date realStartDate) {
		this.realStartDate = realStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="实际开工日期 ", align=2, sort=43)
	public Date getRealEndDate() {
		return realEndDate;
	}

	public void setRealEndDate(Date realEndDate) {
		this.realEndDate = realEndDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="计划准结时间  小时  本工序所需的每批的成本生产准备和结束时间。>0", align=2, sort=44)
	public Date getCostSetupTime() {
		return costSetupTime;
	}

	public void setCostSetupTime(Date costSetupTime) {
		this.costSetupTime = costSetupTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="计划加工时间  小时  本工序加工物料所需的成本加工时间定额标准。>0", align=2, sort=45)
	public Date getCostRunTime() {
		return costRunTime;
	}

	public void setCostRunTime(Date costRunTime) {
		this.costRunTime = costRunTime;
	}
	
	@ExcelField(title="首件检验标识  Y-已检验N- 未检验", align=2, sort=46)
	public String getFirstSeqPassFlag() {
		return firstSeqPassFlag;
	}

	public void setFirstSeqPassFlag(String firstSeqPassFlag) {
		this.firstSeqPassFlag = firstSeqPassFlag;
	}
	
	@ExcelField(title="状态  0-未确认   1-已确认   2-已下达   3-已开工    4-已完成   5-已作废   6-工艺变更", align=2, sort=47)
	public String getSeqStatus() {
		return seqStatus;
	}

	public void setSeqStatus(String seqStatus) {
		this.seqStatus = seqStatus;
	}
	
	@ExcelField(title="seq_status_label", align=2, sort=48)
	public String getSeqStatusLabel() {
		return seqStatusLabel;
	}

	public void setSeqStatusLabel(String seqStatusLabel) {
		this.seqStatusLabel = seqStatusLabel;
	}
	
	@ExcelField(title="姓名", align=2, sort=49)
	public String getDispatchUserName() {
		return dispatchUserName;
	}

	public void setDispatchUserName(String dispatchUserName) {
		this.dispatchUserName = dispatchUserName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="派工时间", align=2, sort=50)
	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	
	@ExcelField(title="工人id", align=2, sort=51)
	public String getWorkerUserId() {
		return workerUserId;
	}

	public void setWorkerUserId(String workerUserId) {
		this.workerUserId = workerUserId;
	}
	
	@ExcelField(title="姓名", align=2, sort=52)
	public String getWorkerUserName() {
		return workerUserName;
	}

	public void setWorkerUserName(String workerUserName) {
		this.workerUserName = workerUserName;
	}
	
	@ExcelField(title="姓名", align=2, sort=53)
	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="检验时间", align=2, sort=54)
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	@ExcelField(title="班组代码", align=2, sort=56)
	public String getWorkcenterCode() {
		return workcenterCode;
	}

	public void setWorkcenterCode(String workcenterCode) {
		this.workcenterCode = workcenterCode;
	}
	
	@ExcelField(title="班组名称", align=2, sort=57)
	public String getWorkcenterName() {
		return workcenterName;
	}

	public void setWorkcenterName(String workcenterName) {
		this.workcenterName = workcenterName;
	}
	
}