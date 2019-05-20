/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysmarketship.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;

import com.pt.modules.sys.sysmarketship.entity.SysMarketShipList;


/**
 * 销售管理字表MAPPER接口
 * @author 郑利
 * @version 2018-09-04
 */
@MyBatisMapper
public interface SysMarketShipListMapper extends BaseMapper<SysMarketShipList> {

	/**
	 * @param listId
	 * @return
	 */
	SysMarketShipList getListById(String listId);


	
}