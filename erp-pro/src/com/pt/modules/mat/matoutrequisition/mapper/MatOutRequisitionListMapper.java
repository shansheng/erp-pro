/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matoutrequisition.mapper;

import com.pt.core.persistence.BaseMapper;

import com.pt.core.persistence.annotation.MyBatisMapper;

import com.pt.modules.mat.matrequisition.entity.MatRequisitionList;

/**
 * mat_requisition_listMAPPER接口
 * @author 郑利
 * @version 2018-09-18
 */
@MyBatisMapper
public interface MatOutRequisitionListMapper extends BaseMapper<MatRequisitionList> {

	/**
	 * @param matRequisitionId
	 * @return
	 */
	MatRequisitionList getByMatRequisitionId(String matRequisitionId);


	
}