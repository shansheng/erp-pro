/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsitem.mapper;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.Page;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsitem.entity.MdsItem;

/**
 * 物料管理MAPPER接口
 * @author 郑利
 * @version 2018-04-24
 */
@MyBatisMapper
public interface MdsItemMapper extends BaseMapper<MdsItem> {


	/**
	 * @param itemCode
	 * @return
	 */
	List<MdsItem> getByCode(String itemCode);

	/**
	 * @param itemName
	 * @return
	 */
	List<MdsItem> getByName(String itemName);



	
}