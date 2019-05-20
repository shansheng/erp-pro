/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderdispath.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainInventory;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainRecord;
import com.pt.modules.wms.wmsmaininventory.mapper.WmsMainRecordMapper;
import com.pt.modules.work.workorderdispath.entity.WorkOrderDispath;
import com.pt.modules.work.workorderdispath.mapper.WorkOrderDispathMapper;

/**
 * 生产出库Service
 * @author 郑利
 * @version 2018-10-20
 */
@Service
@Transactional(readOnly = true)
public class WorkOrderDispathService extends CrudService<WorkOrderDispathMapper, WorkOrderDispath> {
	@Autowired
	private WmsMainRecordMapper wmsMainRecordMapper;
	public WorkOrderDispath get(String id) {
		return super.get(id);
	}
	
	public List<WorkOrderDispath> findList(WorkOrderDispath workOrderDispath) {
		return super.findList(workOrderDispath);
	}
	
	public Page<WorkOrderDispath> findPage(Page<WorkOrderDispath> page, WorkOrderDispath workOrderDispath) {
		return super.findPage(page, workOrderDispath);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkOrderDispath workOrderDispath) {
		super.save(workOrderDispath);
	}
	
	@Transactional(readOnly = false)
	public void delete(WorkOrderDispath workOrderDispath) {
		super.delete(workOrderDispath);
	}

	/**
	 * @param wmsMainRecord
	 */
	public void dispath(WmsMainRecord wmsMainRecord) {
		wmsMainRecordMapper.insert(wmsMainRecord);
		
	}
	
}