/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderdetail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.work.workorderdetail.entity.WorkOrderDetail;

/**
 * 工作计划下达MAPPER接口
 * @author 郑利
 * @version 2018-10-13
 */
@MyBatisMapper
public interface WorkOrderDetailMapper extends BaseMapper<WorkOrderDetail> {

	/**
	 * @param matBomDetailId
	 * @return
	 */
	WorkOrderDetail gets(String matBomDetailId);

	/**
	 * @param workOrderId
	 * @return
	 */
	List<WorkOrderDetail> getByWorkOrderId(String workOrderId);

	/**
	 * @param workOrderDetail
	 * @return
	 */
	WorkOrderDetail getByBomItemId(WorkOrderDetail workOrderDetail);


    @Select( { "select count(*) from work_order_detail where work_order_son_id = #{workOrderSonId} and begin_plan_date IS null" })
    int count(@Param("workOrderSonId")String workOrderSonId);
	
}