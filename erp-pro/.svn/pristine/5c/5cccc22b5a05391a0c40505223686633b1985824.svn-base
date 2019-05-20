/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmaininventory.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainInventory;

/**
 * 台账明细表MAPPER接口
 * @author cjx
 * @version 2018-10-17
 */
@MyBatisMapper
public interface WmsMainInventoryMapper extends BaseMapper<WmsMainInventory> {
	
	//通过物料id查询台账信息
	WmsMainInventory getByItemId(String itemId);

	/**
	 * @param wmsMainInventory
	 * @return
	 */
	WmsMainInventory getByItem(WmsMainInventory wmsMainInventory);
}