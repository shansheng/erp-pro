/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsbom.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsbom.entity.MdsBomInstance;

/**
 * BOM实例MAPPER接口
 * @author 龚志强
 * @version 2018-07-14
 */
@MyBatisMapper
public interface MdsBomInstanceMapper extends BaseMapper<MdsBomInstance> {
	
}