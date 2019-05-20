/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matrequisition.mapper;

import org.apache.ibatis.annotations.Select;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mat.matrequisition.entity.MatRequisition;

/**
 * 领料单流程MAPPER接口
 * @author 郑利
 * @version 2018-09-18
 */
@MyBatisMapper
public interface MatRequisitionMapper extends BaseMapper<MatRequisition> {

	/**
	 * @param matRequisition
	 */
	public int doSubmit(MatRequisition matRequisition);

	/**
	 * @param code
	 * @return
	 */
	public MatRequisition getByCode(String code);
	/**
	 * 统计领料单待审批数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM MAT_REQUISITION WHERE TO_CHAR(CREATE_DATE,'yyyy') like CONCAT(TO_CHAR(SYSDATE,'yyyy'),'%') AND STATE IN(10,11,20)")
	public int getOnMatApply();
	/**
	 * 统计领料单待出库
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM MAT_REQUISITION WHERE TO_CHAR(CREATE_DATE,'yyyy') like CONCAT(TO_CHAR(SYSDATE,'yyyy'),'%') AND STATE IN(30,45)")
	public int getPartOut();
	/**
	 * 统计领料单全部出库
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM MAT_REQUISITION WHERE TO_CHAR(CREATE_DATE,'yyyy') like CONCAT(TO_CHAR(SYSDATE,'yyyy'),'%') AND STATE = 50")
	public int getAllOut();
	
}