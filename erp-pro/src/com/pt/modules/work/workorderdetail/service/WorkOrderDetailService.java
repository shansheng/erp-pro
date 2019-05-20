/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderdetail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.work.workorderdetail.entity.WorkOrderDetail;
import com.pt.modules.work.workorderdetail.mapper.WorkOrderDetailMapper;

/**
 * 工作计划下达Service
 * @author 郑利
 * @version 2018-10-13
 */
@Service
@Transactional(readOnly = true)
public class WorkOrderDetailService extends CrudService<WorkOrderDetailMapper, WorkOrderDetail> {
    @Autowired
	private WorkOrderDetailMapper workOrderDetailMapper;
	public WorkOrderDetail get(String id) {
		return super.get(id);
	}
	
	public List<WorkOrderDetail> findList(WorkOrderDetail workOrderDetail) {
		return super.findList(workOrderDetail);
	}
	
	public Page<WorkOrderDetail> findPage(Page<WorkOrderDetail> page, WorkOrderDetail workOrderDetail) {
		return super.findPage(page, workOrderDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkOrderDetail workOrderDetail) {
	     super.save(workOrderDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(WorkOrderDetail workOrderDetail) {
		super.delete(workOrderDetail);
	}
    public int count(String workOrderSonId) {
    	return workOrderDetailMapper.count(workOrderSonId);
    }
	/**
	 * @param matBomDetailId
	 * @return
	 */
	public WorkOrderDetail gets(String matBomDetailId) {
		
		return mapper.gets(matBomDetailId);
	}

	/**
	 * @param workOrderId
	 * @return
	 */
	public List<WorkOrderDetail> getByWorkOrderId(String workOrderId) {
		
		return mapper.getByWorkOrderId(workOrderId);
	}

	/**
	 * @param workOrderDetail
	 * @return
	 */
	public WorkOrderDetail getByBomItemId(WorkOrderDetail workOrderDetail) {
		
		return mapper.getByBomItemId(workOrderDetail);
	}


	
}