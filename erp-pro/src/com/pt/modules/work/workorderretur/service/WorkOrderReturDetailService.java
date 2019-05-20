/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderretur.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.work.workorderdetail.entity.WorkOrderDetail;
import com.pt.modules.work.workorderretur.entity.WorkOrderReturDetail;
import com.pt.modules.work.workorderretur.mapper.WorkOrderReturDetailMapper;


/**
 * 工作计划下达Service
 * @author 郑利
 * @version 2018-10-13
 */
@Service
@Transactional(readOnly = true)
public class WorkOrderReturDetailService extends CrudService<WorkOrderReturDetailMapper, WorkOrderReturDetail> {

	public WorkOrderReturDetail get(String id) {
		return super.get(id);
	}
	
	public List<WorkOrderReturDetail> findList(WorkOrderReturDetail WorkOrderReturDetail) {
		return super.findList(WorkOrderReturDetail);
	}
	
	public Page<WorkOrderReturDetail> findPage(Page<WorkOrderReturDetail> page, WorkOrderReturDetail WorkOrderReturDetail) {
		return super.findPage(page, WorkOrderReturDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkOrderReturDetail WorkOrderReturDetail) {
		super.save(WorkOrderReturDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(WorkOrderReturDetail WorkOrderReturDetail) {
		super.delete(WorkOrderReturDetail);
	}

	/**
	 * @param matBomDetailId
	 * @return
	 */
	public WorkOrderReturDetail gets(String matBomDetailId) {
		
		return mapper.gets(matBomDetailId);
	}

	/**
	 * @param workOrderReturDetail
	 */
	public void updateRetur(WorkOrderReturDetail workOrderReturDetail) {
		mapper.updateRetur(workOrderReturDetail);
		
	}

	/**
	 * @param workOrderSonId
	 * @return
	 */
	public List<WorkOrderReturDetail> getByWorkOrderSonId(String workOrderSonId) {
		return mapper.getByWorkOrderSonId(workOrderSonId);
	}

	/**
	 * @param workOrderDetail
	 * @return
	 */
	public WorkOrderReturDetail getByBomItemId(WorkOrderReturDetail workOrderDetail) {
		return mapper.getByBomItemId(workOrderDetail);
	}






	
}