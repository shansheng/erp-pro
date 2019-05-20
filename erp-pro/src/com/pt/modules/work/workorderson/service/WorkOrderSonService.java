/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.common.utils.StringUtils;
import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.work.workorderdetail.entity.WorkOrderDetail;
import com.pt.modules.work.workorderdetail.mapper.WorkOrderDetailMapper;
import com.pt.modules.work.workorderson.entity.WorkOrderSon;
import com.pt.modules.work.workorderson.mapper.WorkOrderSonMapper;

/**
 * 工作计划Service
 * @author 郑利
 * @version 2018-10-13
 */
@Service
@Transactional(readOnly = true)
public class WorkOrderSonService extends CrudService<WorkOrderSonMapper, WorkOrderSon> {
	
	@Autowired
	private WorkOrderDetailMapper workOrderDetailMapper;
	public WorkOrderSon get(String id) {
		return super.get(id);
	}
	
	public List<WorkOrderSon> findList(WorkOrderSon workOrderSon) {
		return super.findList(workOrderSon);
	}
	
	public Page<WorkOrderSon> findPage(Page<WorkOrderSon> page, WorkOrderSon workOrderSon) {
		return super.findPage(page, workOrderSon);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkOrderSon workOrderSon) {
		super.save(workOrderSon);
	}
	
	@Transactional(readOnly = false)
	public void delete(WorkOrderSon workOrderSon) {
		super.delete(workOrderSon);
	}
	@Transactional(readOnly = false)
	public void saves(WorkOrderSon workOrderSon) {
		super.save(workOrderSon);
		for (WorkOrderDetail workOrderDetail : workOrderSon.getWorkOrderDetailList()){
			if (workOrderDetail.getId() == null){
				continue;
			}
			if (WorkOrderDetail.DEL_FLAG_NORMAL.equals(workOrderDetail.getDelFlag())){
				if (StringUtils.isBlank(workOrderDetail.getId())){
					workOrderDetail.setWorkOrderSonId(workOrderSon.getId());
					workOrderDetail.preInsert();
					workOrderDetailMapper.insert(workOrderDetail);
				}else{
					workOrderDetail.preUpdate();
					workOrderDetailMapper.update(workOrderDetail);
				}
			}else{
				workOrderDetailMapper.delete(workOrderDetail);
			}
		}
	}
	/**
	 * @param workOrderId
	 * @return
	 */
	public WorkOrderSon findByOrderId(String workOrderId) {
		
		return mapper.findByOrderId(workOrderId);
	}

	@Transactional(readOnly = false)
	public void disPatchById(WorkOrderSon workOrderSon) {
		mapper.disPatchById(workOrderSon);
		
	}

	/**
	 * @param workOrders
	 */
	public void updates(WorkOrderSon workOrders) {
		mapper.updates(workOrders);
		
	}

	/**
	 * @param workOrderId
	 * @return
	 */
	public WorkOrderSon getByWorkOrderId(String workOrderId) {
		
		return mapper.getByWorkOrderId(workOrderId);
	}

	/**
	 * @param workOrderSon
	 * @return
	 */
	public List<WorkOrderSon> findByWorkOrderId(WorkOrderSon workOrderSon) {
		// TODO Auto-generated method stub
		return mapper.findByWorkOrderId(workOrderSon);
	}
	
}