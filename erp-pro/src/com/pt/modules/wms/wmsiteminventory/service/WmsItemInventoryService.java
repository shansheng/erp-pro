/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsiteminventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.common.utils.StringUtils;
import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.wms.wmsinvoutbill.entity.WmsInvOutBillList;
import com.pt.modules.wms.wmsiteminventory.entity.WmsItemInventory;
import com.pt.modules.wms.wmsiteminventory.mapper.WmsItemInventoryMapper;

/**
 * 台账增删改查Service
 * @author 孙会楠
 * @version 2018-05-16
 */
@Service
@Transactional(readOnly = true)
public class WmsItemInventoryService extends CrudService<WmsItemInventoryMapper, WmsItemInventory> {
	
	@Autowired
	private WmsItemInventoryMapper wmsItemInventoryMapper;

	public WmsItemInventory get(String id) {
		return super.get(id);
	}
	
	public List<WmsItemInventory> findList(WmsItemInventory wmsItemInventory) {
		return super.findList(wmsItemInventory);
	}
	
	public Page<WmsItemInventory> findPage(Page<WmsItemInventory> page, WmsItemInventory wmsItemInventory) {
		return super.findPage(page, wmsItemInventory);
	}
	
	@Transactional(readOnly = false)
	public void save(WmsItemInventory wmsItemInventory) {
		super.save(wmsItemInventory);
	}
	
	@Transactional(readOnly = false)
	public void delete(WmsItemInventory wmsItemInventory) {
		super.delete(wmsItemInventory);
	}

	@Transactional(readOnly = false)
	public void doInSubmit(WmsItemInventory wmsItemInventory, String inventoryName) {
		if(wmsItemInventory.getInventoryName() == null || "".equals(wmsItemInventory.getInventoryName())){
			wmsItemInventory.setInventoryName(inventoryName);
		}
		super.save(wmsItemInventory);
		
	}
}