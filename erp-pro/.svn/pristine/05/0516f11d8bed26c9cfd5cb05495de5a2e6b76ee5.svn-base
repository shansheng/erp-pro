/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderretur.mapper;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.work.workorderretur.entity.WorkOrderRetur;
import com.pt.modules.work.workorderson.entity.WorkOrderSon;

/**
 * 工作计划MAPPER接口
 * @author 郑利
 * @version 2018-10-13
 */
@MyBatisMapper
public interface WorkOrderReturMapper extends BaseMapper<WorkOrderRetur> {

	/**
	 * @param workOrderId
	 * @return
	 */
	WorkOrderRetur findByOrderId(String workOrderId);

	/**
	 * @param workOrderRetur
	 */
	void disPatchById(WorkOrderRetur workOrderRetur);

	/**
	 * @param workOrderRetur
	 */
	void updateFinishSche(WorkOrderRetur workOrderRetur);
	
	/**
	 * 查询生产状态为未开始的总数
	 * @return
	 */
	int getOfUnStart();
	
	/**
	 * 查询生产状态为生产中的总数
	 * @return
	 */
	int getOfBegin();
	
	/**
	 * 查询生产状态为生产完成的总数
	 * @return
	 */
	int getOfEnd();
	
	/**
	 * 查询各产品的生产进度 
	 * @return
	 */
	List<WorkOrderRetur> getOfProduct();
	
}