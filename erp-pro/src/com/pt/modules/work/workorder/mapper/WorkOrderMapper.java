/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorder.mapper;



import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.work.workorder.entity.WorkOrder;


/**
 * 工作订单下达MAPPER接口
 * @author 郑利
 * @version 2018-10-09
 */
@MyBatisMapper
public interface WorkOrderMapper extends BaseMapper<WorkOrder> {

	/**
	 * @param entity
	 * @return
	 */
	WorkOrder getEntity(WorkOrder entity);

	/**
	 * @param sysMarketId
	 */
	void updateLookIs(String sysMarketListId);

	/**
	 * @param sysMarketListId
	 */
	void updateNoLookIs(String sysMarketListId);

	/**
	 * @param sysMarketId
	 * @return
	 */
	WorkOrder getBySysMarketId(String sysMarketId);



}