/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderretur.mapper;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.work.workorderdetail.entity.WorkOrderDetail;
import com.pt.modules.work.workorderretur.entity.WorkOrderReturDetail;

/**
 * 工作计划下达MAPPER接口
 * @author 郑利
 * @version 2018-10-13
 */
@MyBatisMapper
public interface WorkOrderReturDetailMapper extends BaseMapper<WorkOrderReturDetail> {

	/**
	 * @param matBomDetailId
	 * @return
	 */
	WorkOrderReturDetail gets(String matBomDetailId);

	/**
	 * @param workOrderReturDetail
	 */
	void updateRetur(WorkOrderReturDetail workOrderReturDetail);

	/**
	 * @param workOrderSonId
	 * @return
	 */
	List<WorkOrderReturDetail> getByWorkOrderSonId(String workOrderSonId);

	/**
	 * @param workOrderDetail
	 * @return
	 */
	WorkOrderReturDetail getByBomItemId(WorkOrderReturDetail workOrderDetail);


	
}