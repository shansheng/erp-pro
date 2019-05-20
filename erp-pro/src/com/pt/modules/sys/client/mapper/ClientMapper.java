/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.client.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.sys.client.entity.Client;

/**
 * 客户管理MAPPER接口
 * @author 郑利
 * @version 2018-08-21
 */
@MyBatisMapper
public interface ClientMapper extends BaseMapper<Client> {
	
}