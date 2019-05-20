/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.templan.templanapplication.mapper;

import org.apache.ibatis.annotations.Select;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.templan.templanapplication.entity.TestTemporaryPlan;

/**
 * 临时计划申请MAPPER接口
 * @author cjx
 * @version 2018-09-04
 */
@MyBatisMapper
public interface TestTemporaryPlanMapper extends BaseMapper<TestTemporaryPlan> {
	
	/**
	 * 申请单提交
	 * @param testTemporaryPlan
	 * @return
	 */
	public int doSubmit(TestTemporaryPlan testTemporaryPlan);
	
	/**
	 * 查询单号是否有重复
	 * @param code
	 * @return
	 */
	public TestTemporaryPlan getByCode(String code);
	/**
	 * 统计临时申请待部门领导审批数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM TEMPORARY_STATE_V  WHERE TO_CHAR(CREATE_DATE,'yyyy') LIKE CONCAT(TO_CHAR(SYSDATE,'YYYY'),'%') AND STATE = 20")
	public int getApply();
	/**
	 * 统计临时申请待副总审批数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM TEMPORARY_STATE_V  WHERE TO_CHAR(CREATE_DATE,'yyyy') LIKE CONCAT(TO_CHAR(SYSDATE,'YYYY'),'%') AND STATE = 30")
	public int getOnPurchase();
	/**
	 * 统计临时采购完的情况
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM TEMPORARY_STATE_V  WHERE TO_CHAR(CREATE_DATE,'yyyy') LIKE CONCAT(TO_CHAR(SYSDATE,'YYYY'),'%') AND FINISH_STATE = 6")
	public int getEndPurchase();
}