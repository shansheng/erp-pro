/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmaininstorage.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.common.utils.IdGen;
import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.wms.wmsmaininstorage.entity.WmsMainInList;
import com.pt.modules.wms.wmsmaininstorage.entity.WmsMainInStorage;
import com.pt.modules.wms.wmsmaininstorage.mapper.WmsMainInStorageMapper;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainInventory;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainRecord;
import com.pt.modules.wms.wmsmaininventory.mapper.WmsMainInventoryMapper;
import com.pt.modules.wms.wmsmaininventory.mapper.WmsMainRecordMapper;

/**
 * 入库管理Service
 * @author cjx
 * @version 2018-10-17
 */
@Service
@Transactional(readOnly = true)
public class WmsMainInStorageService extends CrudService<WmsMainInStorageMapper, WmsMainInStorage> {
	
	@Autowired
	private WmsMainRecordMapper wmsMainRecordMapper;
	
	@Autowired
	private WmsMainInventoryMapper wmsMainInventoryMapper;
	@Autowired
	private WmsMainInStorageMapper wmsMainInStorageMapper;

	public WmsMainInStorage get(String id) {
		return super.get(id);
	}
	
	public WmsMainInStorage get(String id,String source) {
		if("0".equals(source)){
			return super.get(id);
		}else{
			return mapper.getSource(id);
		}

	}
	
	public List<WmsMainInStorage> findList(WmsMainInStorage wmsMainInStorage) {
		return super.findList(wmsMainInStorage);
	}
	
	public Page<WmsMainInStorage> findPage(Page<WmsMainInStorage> page, WmsMainInStorage wmsMainInStorage) {
		return super.findPage(page, wmsMainInStorage);
	}
	
	@Transactional(readOnly = false)
	public void save(WmsMainInList wmsMainInList) {
		for (WmsMainInStorage wmsMainInStorage : wmsMainInList.getWmsMainInStorageList()) {
			super.save(wmsMainInStorage);
		}
	}
	
	/**
	 * 入库
	 * @param wmsMainInStorage
	 */
	@Transactional(readOnly = false)
	public void inStorage(WmsMainInStorage wmsMainInStorage) {
		/*//获取数据
		wmsMainInStorage = get(wmsMainInStorage.getId());*/
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			wmsMainInStorage.setInStorageBy(user);
		}
		wmsMainInStorage.setInStorageDate(new Date());
		wmsMainInStorage.setInStoargeState("1");
		if("0".equals(wmsMainInStorage.getSource())){
		//修改到货检查表中的入库数,入库人,入库时间以及入库状态
			wmsMainInStorage.setState("2");
			mapper.update(wmsMainInStorage);
		}else{
			
			mapper.updateSource(wmsMainInStorage);
		}
		//添加到库房记录表
		WmsMainRecord wmsMainRecord = new WmsMainRecord(wmsMainInStorage);
		wmsMainRecord.setId(IdGen.uuid());
		wmsMainRecordMapper.insert(wmsMainRecord);
		//修改或新增台账记录
		WmsMainInventory wmsMainInventory = wmsMainInventoryMapper.getByItemId(wmsMainInStorage.getItemId());
		if(wmsMainInventory==null){
			wmsMainInventory = new WmsMainInventory();
			wmsMainInventory.setItemId(wmsMainRecord.getItemId());
			wmsMainInventory.setCurrentOnhandQty(wmsMainRecord.getQty());
			wmsMainInventory.preInsert();
			wmsMainInventoryMapper.insert(wmsMainInventory);
		}else{
			wmsMainInventory.preUpdate();
			wmsMainInventory.setCurrentOnhandQty(wmsMainInventory.getCurrentOnhandQty()+wmsMainRecord.getQty());
			wmsMainInventoryMapper.update(wmsMainInventory);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(WmsMainInStorage wmsMainInStorage) {
		super.delete(wmsMainInStorage);
	}
	public void deleteAll(String id) {
		wmsMainInStorageMapper.deleteAll(id);
	}
}