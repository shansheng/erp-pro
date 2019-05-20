/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workordersynthesize.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.work.workorderson.entity.WorkOrderSon;
import com.pt.modules.work.workordersynthesize.entity.WorkOrderSynthesize;

/**
 * 工作计划MAPPER接口
 * @author 郑利
 * @version 2018-10-13
 */
@MyBatisMapper
public interface WorkOrderSynthesizeMapper extends BaseMapper<WorkOrderSynthesize> {

	/**
	 * @param workOrderId
	 * @return
	 */
	WorkOrderSon findByOrderId(String workOrderId);

	

	/**
	 * @param workOrderId
	 * @return
	 */
	WorkOrderSon getByWorkOrderId(String workOrderId);
	
}