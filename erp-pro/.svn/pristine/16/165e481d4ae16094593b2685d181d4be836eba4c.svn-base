/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workordersynthesize.service;

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
import com.pt.modules.work.workordersynthesize.entity.WorkOrderSynthesize;
import com.pt.modules.work.workordersynthesize.mapper.WorkOrderSynthesizeMapper;

/**
 * 工作计划Service
 * @author 郑利
 * @version 2018-10-13
 */
@Service
@Transactional(readOnly = true)
public class WorkOrderSynthesizeService extends CrudService<WorkOrderSynthesizeMapper, WorkOrderSynthesize> {
	
	@Autowired
	private WorkOrderDetailMapper workOrderDetailMapper;
	public WorkOrderSynthesize get(String id) {
		return super.get(id);
	}
	
	public List<WorkOrderSynthesize> findList(WorkOrderSynthesize workOrderSon) {
		return super.findList(workOrderSon);
	}
	
	public Page<WorkOrderSynthesize> findPage(Page<WorkOrderSynthesize> page, WorkOrderSynthesize workOrderSon) {
		return super.findPage(page, workOrderSon);
	}
	

	
}