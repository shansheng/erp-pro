/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinventory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.wms.wmsinventory.entity.WmsInventory;
import com.pt.modules.wms.wmsinventory.mapper.WmsInventoryMapper;

/**
 * 库房增删改查Service
 * @author 孙会楠
 * @version 2018-05-05
 */
@Service
@Transactional(readOnly = true)
public class WmsInventoryService extends CrudService<WmsInventoryMapper, WmsInventory> {

	public WmsInventory get(String id) {
		return super.get(id);
	}
	
	public List<WmsInventory> findList(WmsInventory wmsInventory) {
		return super.findList(wmsInventory);
	}
	
	public Page<WmsInventory> findPage(Page<WmsInventory> page, WmsInventory wmsInventory) {
		return super.findPage(page, wmsInventory);
	}
	
	@Transactional(readOnly = false)
	public void save(WmsInventory wmsInventory) {
		super.save(wmsInventory);
	}
	
	@Transactional(readOnly = false)
	public void delete(WmsInventory wmsInventory) {
		super.delete(wmsInventory);
	}
	
}