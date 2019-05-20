/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmslocator.mapper;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.wms.wmslocator.entity.WmsLocator;

/**
 * 库位增删改查MAPPER接口
 * @author 孙会楠
 * @version 2018-05-05
 */
@MyBatisMapper
public interface WmsLocatorMapper extends BaseMapper<WmsLocator> {
	/**
	 * 通过WmsinventoryId库房id 获取库房中所有的库位--龚志强
	 * @param WmsinventoryId
	 * @return
	 */
	public List<WmsLocator> getDataByWmsinventoryId(WmsLocator wmsLocator);
}