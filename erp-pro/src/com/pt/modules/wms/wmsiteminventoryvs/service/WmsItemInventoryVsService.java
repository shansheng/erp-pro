/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsiteminventoryvs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.wms.wmsiteminventoryvs.entity.WmsItemInventoryVs;
import com.pt.modules.wms.wmsiteminventoryvs.mapper.WmsItemInventoryVsMapper;

/**
 * 流水查询Service
 * @author 郑利
 * @version 2018-05-16
 */
@Service
@Transactional(readOnly = true)
public class WmsItemInventoryVsService extends CrudService<WmsItemInventoryVsMapper, WmsItemInventoryVs> {

	public WmsItemInventoryVs get(String id) {
		return super.get(id);
	}
	
	public List<WmsItemInventoryVs> findList(WmsItemInventoryVs wmsItemInventoryVs) {
		return super.findList(wmsItemInventoryVs);
	}
	
	public Page<WmsItemInventoryVs> findPage(Page<WmsItemInventoryVs> page, WmsItemInventoryVs wmsItemInventoryVs) {
		return super.findPage(page, wmsItemInventoryVs);
	}
	
	@Transactional(readOnly = false)
	public void save(WmsItemInventoryVs wmsItemInventoryVs) {
		super.save(wmsItemInventoryVs);
	}
	
	@Transactional(readOnly = false)
	public void delete(WmsItemInventoryVs wmsItemInventoryVs) {
		super.delete(wmsItemInventoryVs);
	}
	
}