/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmaininventory.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.common.utils.StringUtils;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainInventory;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainRecord;
import com.pt.modules.wms.wmsmaininventory.mapper.WmsMainInventoryMapper;
import com.pt.modules.wms.wmsmaininventory.mapper.WmsMainRecordMapper;

/**
 * 台账明细表Service
 * @author cjx
 * @version 2018-10-17
 */
@Service
@Transactional(readOnly = true)
public class WmsMainInventoryService extends CrudService<WmsMainInventoryMapper, WmsMainInventory> {

	@Autowired
	private WmsMainRecordMapper wmsMainRecordMapper;
	
	@Autowired
	private WmsMainInventoryMapper wmsMainInventoryMapper;
	public WmsMainInventory get(String id) {
		WmsMainInventory wmsMainInventory = super.get(id);
		wmsMainInventory.setWmsMainRecordList(wmsMainRecordMapper.findList(new WmsMainRecord(wmsMainInventory)));
		return wmsMainInventory;
	}
	
	public List<WmsMainInventory> findList(WmsMainInventory wmsMainInventory) {
		return super.findList(wmsMainInventory);
	}
	
	public Page<WmsMainInventory> findPage(Page<WmsMainInventory> page, WmsMainInventory wmsMainInventory) {
		return super.findPage(page, wmsMainInventory);
	}

	/**
	 * @param entity
	 */
	public void update(WmsMainInventory entity) {
		entity.setUpdateBy(UserUtils.getUser());
		entity.setUpdateDate(new Date());
		wmsMainInventoryMapper.update(entity);
		
	}

	/**
	 * @param wmsMainInventory
	 * @return
	 */
	public WmsMainInventory getByItem(WmsMainInventory wmsMainInventory) {
		
		return wmsMainInventoryMapper.getByItem(wmsMainInventory);
	}
	
	/*@Transactional(readOnly = false)
	public void save(WmsMainInventory wmsMainInventory) {
		super.save(wmsMainInventory);
		for (WmsMainRecord wmsMainRecord : wmsMainInventory.getWmsMainRecordList()){
			if (wmsMainRecord.getId() == null){
				continue;
			}
			if (WmsMainRecord.DEL_FLAG_NORMAL.equals(wmsMainRecord.getDelFlag())){
				if (StringUtils.isBlank(wmsMainRecord.getId())){
					wmsMainRecord.setItemId(wmsMainInventory);
					wmsMainRecord.preInsert();
					wmsMainRecordMapper.insert(wmsMainRecord);
				}else{
					wmsMainRecord.preUpdate();
					wmsMainRecordMapper.update(wmsMainRecord);
				}
			}else{
				wmsMainRecordMapper.delete(wmsMainRecord);
			}
		}
	}*/
	
	/*@Transactional(readOnly = false)
	public void delete(WmsMainInventory wmsMainInventory) {
		super.delete(wmsMainInventory);
		wmsMainRecordMapper.delete(new WmsMainRecord(wmsMainInventory));
	}*/
	
}