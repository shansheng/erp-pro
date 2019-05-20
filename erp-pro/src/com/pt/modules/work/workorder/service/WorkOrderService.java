/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.common.utils.StringUtils;
import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.work.workorder.entity.WorkOrder;
import com.pt.modules.work.workorder.mapper.WorkOrderMapper;

/**
 * 工作订单下达Service
 * @author 郑利
 * @version 2018-10-09
 */
@Service
@Transactional(readOnly = true)
public class WorkOrderService extends CrudService<WorkOrderMapper, WorkOrder> {

	@Autowired
	private WorkOrderMapper WorkOrderMapper;
	public WorkOrder get(String id) {
		return super.get(id);
	}
	
	public List<WorkOrder> findList(WorkOrder workOrder) {
		return super.findList(workOrder);
	}
	
	public Page<WorkOrder> findPage(Page<WorkOrder> page, WorkOrder workOrder) {
		return super.findPage(page, workOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkOrder workOrder) {
		super.save(workOrder);
	}
	
	
	@Transactional(readOnly = false)
	public void delete(WorkOrder workOrder) {
		super.delete(workOrder);
	}

	/**
	 * 
	 */
	public void update(WorkOrder workOrder) {
		mapper.update(workOrder);
		
	}

	/**
	 * @param entity
	 * @return
	 */
	public WorkOrder gets(WorkOrder entity) {
		return WorkOrderMapper.getEntity(entity);
	}

	/**
	 * @param sysMarketId
	 * @return
	 */
	public WorkOrder getBySysMarketId(String sysMarketId) {
		return  WorkOrderMapper.getBySysMarketId(sysMarketId);
	}

	

	
}