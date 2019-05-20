/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mrp.mrpwo.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 生产任务管理Entity
 * @author 孙会楠
 * @version 2018-05-04
 */
public class MrpWo extends DataEntity<MrpWo> {
	
	private static final long serialVersionUID = 1L;
	private String woNo;		// 工作订单号，编号规则为WO-YYMMAAA，即WO-,加上两位年的数字，两位月的数字和三位流水号
	private String woDateStr;		// 年月信息,采用YYYYMM方式，例如200710，200801
	private String mdsItemId;		// 物料ID
	private String proMdsItemId;		// 产品型号，对应物料ID
	private String mainOfficeId;		// 生产部门 
	private String useOfficeId;		// 使用部门
	private String parentWoId;		// 对组合件拆分出的订单，存在父项工作订单ID
	private String parentMdsItemId;		// 父件号
	private String woNote;		// 订单备注
	private String woStatus;		// 订单状态 ^ 0-未确认；1 未下达；2-已下达；3-已开工；4-已完工；
	private String planType;		// 计划类型,'A' 或者‘C’,默认‘A’
	private String reqQty;		// 需求数量，默认0
	private Date planStartDate;		// 计划开工日期
	private Date planEndDate;		// 计划完工日期
	private String actualQty;		// 实际完成数量
	private Date actualStartDate;		// 实际开工日期
	private Date actualEndDate;		// 实际完工日期
	private String grossReqQty;		// 毛需求数量
	private String rejectQty;		// 不合格数量，默认0
	private String batchNo;		// 该需求需求所属批次
	private String woType;		// WO类型, 说明此WO的用途： '1' -- 正常订单；  '2' -- 废补订单; '5'-返修订单；‘10’-复查订单；'15'-零星订单
	private String originalMesWoId;		// 对于废补/返修订单多次废补的情况，需要原始派工号,
	private String releaseSysUserId;		// 订单下达人
	private Date releaseDate;		// 订单下达日期
	private String treeLevel;		// 当前订单所处的树的层数
	private String toEwoQty;		// 生成EWO数量
	private String woSourceType;		// WO来源类型，说明此WO的来源：''1'' -- MES录入； ''2'' -- ERP导入；
	private String pkQty;		// 配套数量
	private String fromHrDeptId;		// 来源部门ID
	private String toHrDeptId;		// 交付部门ID
	private String requirePlanId;		// 需求计划ID
	private String ynGenPickup;		//  ’N‘ - 配套单未生成 ，  ’Y‘ - 配套单已生成
	private String ynGenMaterialRequisition;		// ’N‘  - 领料单未生成  ， ’Y‘ - 领料单已生成
	private String mpsMoId;		// 制造订单ID
	private String mdsRouteId;		// 工艺版本id
	private String missionNo;		// 派工号
	private String ynMrpRecalculate;		// 是否参与mrp计算 ^N-不重算 ;Y-重算 
	private String lowLevelCode;		// 工作订单物料低位码
	private String parentLowLevelCode;		// 父低位码
	private String mdsBomInstanceId;		// BOM实例id
	private String mrpWoPriority;		// 优先级
	private String stagePart;		// 期段
	private String lastFlag;		// 叶节点为0 ,其余为1
	private String totlaCt;		// 到根结点的累积提前期
	private String shopRoute;		// 路线/车间流转(HD交接路线)
	private String shopNo;		// 交接编号
	private String individualQty;		// 单机数量
	private String taskNo;		// 任务编号
	private String outerStatus;		// 是否外协，Y/N
	private String transferStatus;		// 移交状态 1、未移交 2、部分移交 3、已移交
	private String transferQty;		// 移交数量
	private String manufactureOrder;		// 制造顺序，在分工路线中的位置
	private String outputActualQty;		// output_actual_qty
	private String routPrepareStatus;		// rout_prepare_status
	private String quotaRunTime;		// quota_run_time
	private String routeMaterialId;		// 材料定额ID---工艺与材料信息关系表ID
	private String clickQty;		// 单机数量
	private String stage;		// 阶段，M模样,C初样,S试样,D定型A
	private String workLine;		// 分工路线
	private String submitApproveUserId;		// 提交审批人
	private Date submitApproveDate;		// 提交审批日期
	private String needApproveUserIds;		// 指定审批人
	private String approveUserId;		// 审批人
	private String approveIdea;		// 驳回意见
	private Date approveDate;		// 审批时间
	private String item01;		// item_01
	private String item02;		// item_02
	private String item03;		// item_03
	private String item04;		// item_04
	private String item05;		// item_05
	
	//表外字段
	private String woStatusName;
	private String mdsItemCode;//物料号
	private String mdsItemName;//物料名称
	private String mdsItemType;//物料类型
	private String mdsItemTypeName;
	private String mainOfficeName;
	
	//根据视图mrp_wo_v条件字段
	private String proMdsItemCode;
	private String proMdsItemName;
	private String mdsItemIdSel;
	private String mdsItemCodeSel;
	private String mdsItemNameSel;
	private String yearStr;
	private String monthStr;
	private String itemTypeLable;
	private String routeShop;
	private String woStatusLable;
	
	public MrpWo() {
		super();
	}

	public MrpWo(String id){
		super(id);
	}

	@ExcelField(title="工作订单号，编号规则为WO-YYMMAAA，即WO-,加上两位年的数字，两位月的数字和三位流水号", align=2, sort=1)
	public String getWoNo() {
		return woNo;
	}

	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}
	
	@ExcelField(title="年月信息,采用YYYYMM方式，例如200710，200801", align=2, sort=2)
	public String getWoDateStr() {
		return woDateStr;
	}

	public void setWoDateStr(String woDateStr) {
		this.woDateStr = woDateStr;
	}
	
	@ExcelField(title="物料ID", align=2, sort=3)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="产品型号，对应物料ID", align=2, sort=4)
	public String getProMdsItemId() {
		return proMdsItemId;
	}

	public void setProMdsItemId(String proMdsItemId) {
		this.proMdsItemId = proMdsItemId;
	}
	
	@ExcelField(title="生产部门 ", align=2, sort=5)
	public String getMainOfficeId() {
		return mainOfficeId;
	}

	public void setMainOfficeId(String mainOfficeId) {
		this.mainOfficeId = mainOfficeId;
	}
	
	@ExcelField(title="使用部门", align=2, sort=6)
	public String getUseOfficeId() {
		return useOfficeId;
	}

	public void setUseOfficeId(String useOfficeId) {
		this.useOfficeId = useOfficeId;
	}
	
	@ExcelField(title="对组合件拆分出的订单，存在父项工作订单ID", align=2, sort=7)
	public String getParentWoId() {
		return parentWoId;
	}

	public void setParentWoId(String parentWoId) {
		this.parentWoId = parentWoId;
	}
	
	@ExcelField(title="父件号", align=2, sort=8)
	public String getParentMdsItemId() {
		return parentMdsItemId;
	}

	public void setParentMdsItemId(String parentMdsItemId) {
		this.parentMdsItemId = parentMdsItemId;
	}
	
	@ExcelField(title="订单备注", align=2, sort=9)
	public String getWoNote() {
		return woNote;
	}

	public void setWoNote(String woNote) {
		this.woNote = woNote;
	}
	
	@ExcelField(title="订单状态 ^ 0-未确认；1 未下达；2-已下达；3-已开工；4-已完工；", align=2, sort=10)
	public String getWoStatus() {
		return woStatus;
	}

	public void setWoStatus(String woStatus) {
		this.woStatus = woStatus;
	}
	
	@ExcelField(title="计划类型,'A' 或者‘C’,默认‘A’", align=2, sort=11)
	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}
	
	@ExcelField(title="需求数量，默认0", align=2, sort=12)
	public String getReqQty() {
		return reqQty;
	}

	public void setReqQty(String reqQty) {
		this.reqQty = reqQty;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="计划开工日期", align=2, sort=13)
	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="计划完工日期", align=2, sort=14)
	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	
	@ExcelField(title="实际完成数量", align=2, sort=15)
	public String getActualQty() {
		return actualQty;
	}

	public void setActualQty(String actualQty) {
		this.actualQty = actualQty;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="实际开工日期", align=2, sort=16)
	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="实际完工日期", align=2, sort=17)
	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	
	@ExcelField(title="毛需求数量", align=2, sort=18)
	public String getGrossReqQty() {
		return grossReqQty;
	}

	public void setGrossReqQty(String grossReqQty) {
		this.grossReqQty = grossReqQty;
	}
	
	@ExcelField(title="不合格数量，默认0", align=2, sort=19)
	public String getRejectQty() {
		return rejectQty;
	}

	public void setRejectQty(String rejectQty) {
		this.rejectQty = rejectQty;
	}
	
	@ExcelField(title="该需求需求所属批次", align=2, sort=20)
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@ExcelField(title="WO类型, 说明此WO的用途： '1' -- 正常订单；  '2' -- 废补订单; '5'-返修订单；‘10’-复查订单；'15'-零星订单", align=2, sort=21)
	public String getWoType() {
		return woType;
	}

	public void setWoType(String woType) {
		this.woType = woType;
	}
	
	@ExcelField(title="对于废补/返修订单多次废补的情况，需要原始派工号,", align=2, sort=22)
	public String getOriginalMesWoId() {
		return originalMesWoId;
	}

	public void setOriginalMesWoId(String originalMesWoId) {
		this.originalMesWoId = originalMesWoId;
	}
	
	@ExcelField(title="订单下达人", align=2, sort=23)
	public String getReleaseSysUserId() {
		return releaseSysUserId;
	}

	public void setReleaseSysUserId(String releaseSysUserId) {
		this.releaseSysUserId = releaseSysUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="订单下达日期", align=2, sort=24)
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	@ExcelField(title="当前订单所处的树的层数", align=2, sort=25)
	public String getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(String treeLevel) {
		this.treeLevel = treeLevel;
	}
	
	@ExcelField(title="生成EWO数量", align=2, sort=26)
	public String getToEwoQty() {
		return toEwoQty;
	}

	public void setToEwoQty(String toEwoQty) {
		this.toEwoQty = toEwoQty;
	}
	
	@ExcelField(title="WO来源类型，说明此WO的来源：''1'' -- MES录入； ''2'' -- ERP导入；", align=2, sort=27)
	public String getWoSourceType() {
		return woSourceType;
	}

	public void setWoSourceType(String woSourceType) {
		this.woSourceType = woSourceType;
	}
	
	@ExcelField(title="配套数量", align=2, sort=28)
	public String getPkQty() {
		return pkQty;
	}

	public void setPkQty(String pkQty) {
		this.pkQty = pkQty;
	}
	
	@ExcelField(title="来源部门ID", align=2, sort=29)
	public String getFromHrDeptId() {
		return fromHrDeptId;
	}

	public void setFromHrDeptId(String fromHrDeptId) {
		this.fromHrDeptId = fromHrDeptId;
	}
	
	@ExcelField(title="交付部门ID", align=2, sort=30)
	public String getToHrDeptId() {
		return toHrDeptId;
	}

	public void setToHrDeptId(String toHrDeptId) {
		this.toHrDeptId = toHrDeptId;
	}
	
	@ExcelField(title="需求计划ID", align=2, sort=31)
	public String getRequirePlanId() {
		return requirePlanId;
	}

	public void setRequirePlanId(String requirePlanId) {
		this.requirePlanId = requirePlanId;
	}
	
	@ExcelField(title=" ’N‘ - 配套单未生成 ，  ’Y‘ - 配套单已生成", align=2, sort=32)
	public String getYnGenPickup() {
		return ynGenPickup;
	}

	public void setYnGenPickup(String ynGenPickup) {
		this.ynGenPickup = ynGenPickup;
	}
	
	@ExcelField(title="’N‘  - 领料单未生成  ， ’Y‘ - 领料单已生成", align=2, sort=33)
	public String getYnGenMaterialRequisition() {
		return ynGenMaterialRequisition;
	}

	public void setYnGenMaterialRequisition(String ynGenMaterialRequisition) {
		this.ynGenMaterialRequisition = ynGenMaterialRequisition;
	}
	
	@ExcelField(title="制造订单ID", align=2, sort=34)
	public String getMpsMoId() {
		return mpsMoId;
	}

	public void setMpsMoId(String mpsMoId) {
		this.mpsMoId = mpsMoId;
	}
	
	@ExcelField(title="工艺版本id", align=2, sort=35)
	public String getMdsRouteId() {
		return mdsRouteId;
	}

	public void setMdsRouteId(String mdsRouteId) {
		this.mdsRouteId = mdsRouteId;
	}
	
	@ExcelField(title="派工号", align=2, sort=36)
	public String getMissionNo() {
		return missionNo;
	}

	public void setMissionNo(String missionNo) {
		this.missionNo = missionNo;
	}
	
	@ExcelField(title="是否参与mrp计算 ^N-不重算 ;Y-重算 ", align=2, sort=37)
	public String getYnMrpRecalculate() {
		return ynMrpRecalculate;
	}

	public void setYnMrpRecalculate(String ynMrpRecalculate) {
		this.ynMrpRecalculate = ynMrpRecalculate;
	}
	
	@ExcelField(title="工作订单物料低位码", align=2, sort=38)
	public String getLowLevelCode() {
		return lowLevelCode;
	}

	public void setLowLevelCode(String lowLevelCode) {
		this.lowLevelCode = lowLevelCode;
	}
	
	@ExcelField(title="父低位码", align=2, sort=39)
	public String getParentLowLevelCode() {
		return parentLowLevelCode;
	}

	public void setParentLowLevelCode(String parentLowLevelCode) {
		this.parentLowLevelCode = parentLowLevelCode;
	}
	
	@ExcelField(title="BOM实例id", align=2, sort=40)
	public String getMdsBomInstanceId() {
		return mdsBomInstanceId;
	}

	public void setMdsBomInstanceId(String mdsBomInstanceId) {
		this.mdsBomInstanceId = mdsBomInstanceId;
	}
	
	@ExcelField(title="优先级", align=2, sort=41)
	public String getMrpWoPriority() {
		return mrpWoPriority;
	}

	public void setMrpWoPriority(String mrpWoPriority) {
		this.mrpWoPriority = mrpWoPriority;
	}
	
	@ExcelField(title="期段", align=2, sort=42)
	public String getStagePart() {
		return stagePart;
	}

	public void setStagePart(String stagePart) {
		this.stagePart = stagePart;
	}
	
	@ExcelField(title="叶节点为0 ,其余为1", align=2, sort=43)
	public String getLastFlag() {
		return lastFlag;
	}

	public void setLastFlag(String lastFlag) {
		this.lastFlag = lastFlag;
	}
	
	@ExcelField(title="到根结点的累积提前期", align=2, sort=44)
	public String getTotlaCt() {
		return totlaCt;
	}

	public void setTotlaCt(String totlaCt) {
		this.totlaCt = totlaCt;
	}
	
	@ExcelField(title="路线/车间流转(HD交接路线)", align=2, sort=45)
	public String getShopRoute() {
		return shopRoute;
	}

	public void setShopRoute(String shopRoute) {
		this.shopRoute = shopRoute;
	}
	
	@ExcelField(title="交接编号", align=2, sort=46)
	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	
	@ExcelField(title="单机数量", align=2, sort=47)
	public String getIndividualQty() {
		return individualQty;
	}

	public void setIndividualQty(String individualQty) {
		this.individualQty = individualQty;
	}
	
	@ExcelField(title="任务编号", align=2, sort=48)
	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
	@ExcelField(title="是否外协，Y/N", align=2, sort=49)
	public String getOuterStatus() {
		return outerStatus;
	}

	public void setOuterStatus(String outerStatus) {
		this.outerStatus = outerStatus;
	}
	
	@ExcelField(title="移交状态 1、未移交 2、部分移交 3、已移交", align=2, sort=50)
	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}
	
	@ExcelField(title="移交数量", align=2, sort=51)
	public String getTransferQty() {
		return transferQty;
	}

	public void setTransferQty(String transferQty) {
		this.transferQty = transferQty;
	}
	
	@ExcelField(title="制造顺序，在分工路线中的位置", align=2, sort=52)
	public String getManufactureOrder() {
		return manufactureOrder;
	}

	public void setManufactureOrder(String manufactureOrder) {
		this.manufactureOrder = manufactureOrder;
	}
	
	@ExcelField(title="output_actual_qty", align=2, sort=53)
	public String getOutputActualQty() {
		return outputActualQty;
	}

	public void setOutputActualQty(String outputActualQty) {
		this.outputActualQty = outputActualQty;
	}
	
	@ExcelField(title="rout_prepare_status", align=2, sort=54)
	public String getRoutPrepareStatus() {
		return routPrepareStatus;
	}

	public void setRoutPrepareStatus(String routPrepareStatus) {
		this.routPrepareStatus = routPrepareStatus;
	}
	
	@ExcelField(title="quota_run_time", align=2, sort=55)
	public String getQuotaRunTime() {
		return quotaRunTime;
	}

	public void setQuotaRunTime(String quotaRunTime) {
		this.quotaRunTime = quotaRunTime;
	}
	
	@ExcelField(title="材料定额ID---工艺与材料信息关系表ID", align=2, sort=56)
	public String getRouteMaterialId() {
		return routeMaterialId;
	}

	public void setRouteMaterialId(String routeMaterialId) {
		this.routeMaterialId = routeMaterialId;
	}
	
	@ExcelField(title="单机数量", align=2, sort=57)
	public String getClickQty() {
		return clickQty;
	}

	public void setClickQty(String clickQty) {
		this.clickQty = clickQty;
	}
	
	@ExcelField(title="阶段，M模样,C初样,S试样,D定型A", align=2, sort=58)
	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
	
	@ExcelField(title="分工路线", align=2, sort=59)
	public String getWorkLine() {
		return workLine;
	}

	public void setWorkLine(String workLine) {
		this.workLine = workLine;
	}
	
	@ExcelField(title="提交审批人", align=2, sort=60)
	public String getSubmitApproveUserId() {
		return submitApproveUserId;
	}

	public void setSubmitApproveUserId(String submitApproveUserId) {
		this.submitApproveUserId = submitApproveUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="提交审批日期", align=2, sort=61)
	public Date getSubmitApproveDate() {
		return submitApproveDate;
	}

	public void setSubmitApproveDate(Date submitApproveDate) {
		this.submitApproveDate = submitApproveDate;
	}
	
	@ExcelField(title="指定审批人", align=2, sort=62)
	public String getNeedApproveUserIds() {
		return needApproveUserIds;
	}

	public void setNeedApproveUserIds(String needApproveUserIds) {
		this.needApproveUserIds = needApproveUserIds;
	}
	
	@ExcelField(title="审批人", align=2, sort=63)
	public String getApproveUserId() {
		return approveUserId;
	}

	public void setApproveUserId(String approveUserId) {
		this.approveUserId = approveUserId;
	}
	
	@ExcelField(title="驳回意见", align=2, sort=64)
	public String getApproveIdea() {
		return approveIdea;
	}

	public void setApproveIdea(String approveIdea) {
		this.approveIdea = approveIdea;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="审批时间", align=2, sort=65)
	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	
	@ExcelField(title="item_01", align=2, sort=66)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="item_02", align=2, sort=67)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="item_03", align=2, sort=68)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="item_04", align=2, sort=69)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="item_05", align=2, sort=70)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}
	
	
	public String getWoStatusName() {
		return woStatusName;
	}

	public void setWoStatusName(String woStatusName) {
		this.woStatusName = woStatusName;
	}

	public String getMdsItemCode() {
		return mdsItemCode;
	}

	public void setMdsItemCode(String mdsItemCode) {
		this.mdsItemCode = mdsItemCode;
	}

	public String getMdsItemName() {
		return mdsItemName;
	}

	public void setMdsItemName(String mdsItemName) {
		this.mdsItemName = mdsItemName;
	}

	public String getMdsItemType() {
		return mdsItemType;
	}

	public void setMdsItemType(String mdsItemType) {
		this.mdsItemType = mdsItemType;
	}

	public String getMdsItemTypeName() {
		return mdsItemTypeName;
	}

	public void setMdsItemTypeName(String mdsItemTypeName) {
		this.mdsItemTypeName = mdsItemTypeName;
	}

	public String getMainOfficeName() {
		return mainOfficeName;
	}

	public void setMainOfficeName(String mainOfficeName) {
		this.mainOfficeName = mainOfficeName;
	}
	
	public String getProMdsItemCode() {
		return proMdsItemCode;
	}

	public void setProMdsItemCode(String proMdsItemCode) {
		this.proMdsItemCode = proMdsItemCode;
	}

	public String getProMdsItemName() {
		return proMdsItemName;
	}

	public void setProMdsItemName(String proMdsItemName) {
		this.proMdsItemName = proMdsItemName;
	}

	public String getMdsItemIdSel() {
		return mdsItemIdSel;
	}

	public void setMdsItemIdSel(String mdsItemIdSel) {
		this.mdsItemIdSel = mdsItemIdSel;
	}

	public String getMdsItemCodeSel() {
		return mdsItemCodeSel;
	}

	public void setMdsItemCodeSel(String mdsItemCodeSel) {
		this.mdsItemCodeSel = mdsItemCodeSel;
	}

	public String getMdsItemNameSel() {
		return mdsItemNameSel;
	}

	public void setMdsItemNameSel(String mdsItemNameSel) {
		this.mdsItemNameSel = mdsItemNameSel;
	}

	public String getYearStr() {
		return yearStr;
	}

	public void setYearStr(String yearStr) {
		this.yearStr = yearStr;
	}

	public String getMonthStr() {
		return monthStr;
	}

	public void setMonthStr(String monthStr) {
		this.monthStr = monthStr;
	}

	public String getItemTypeLable() {
		return itemTypeLable;
	}

	public void setItemTypeLable(String itemTypeLable) {
		this.itemTypeLable = itemTypeLable;
	}

	public String getRouteShop() {
		return routeShop;
	}

	public void setRouteShop(String routeShop) {
		this.routeShop = routeShop;
	}

	public String getWoStatusLable() {
		return woStatusLable;
	}

	public void setWoStatusLable(String woStatusLable) {
		this.woStatusLable = woStatusLable;
	}
}