/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderson.mapper;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.sys.sysmarketship.entity.SysMarketShipList;
import com.pt.modules.work.workorderson.entity.WorkOrderSon;

/**
 * 工作计划MAPPER接口
 * @author 郑利
 * @version 2018-10-13
 */
@MyBatisMapper
public interface WorkOrderSonMapper extends BaseMapper<WorkOrderSon> {

	/**
	 * @param workOrderId
	 * @return
	 */
	WorkOrderSon findByOrderId(String workOrderId);

	/**
	 * @param workOrderSon
	 */
	void disPatchById(WorkOrderSon workOrderSon);

	/**
	 * @param workOrders
	 */
	void updates(WorkOrderSon workOrders);

	/**
	 * @param workOrderId
	 * @return
	 */
	WorkOrderSon getByWorkOrderId(String workOrderId);

	/**
	 * @param workOrderSon
	 * @return
	 */
	List<WorkOrderSon> findByWorkOrderId(WorkOrderSon workOrderSon);

	/**
	 * @param sysMarketListId
	 * @return
	 */
	List<WorkOrderSon> getBySysMarketListId(String sysMarketListId);

	/**
	 * @param workOrderSon
	 */
	void updateShipments(WorkOrderSon workOrderSon);
	
}