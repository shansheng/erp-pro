/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmaininstorage.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.wms.wmsmaininstorage.entity.WmsMainInStorage;

/**
 * 入库管理MAPPER接口
 * @author cjx
 * @version 2018-10-17
 */
@MyBatisMapper
public interface WmsMainInStorageMapper extends BaseMapper<WmsMainInStorage> {
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public WmsMainInStorage getSource(String id);
	
	/**
	 * 手动录入更新数据
	 * @param entity
	 * @return
	 */
	public int updateSource(WmsMainInStorage entity);
	
	void deleteAll(String id);
}