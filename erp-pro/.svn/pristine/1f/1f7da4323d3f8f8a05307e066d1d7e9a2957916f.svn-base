/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.mapper;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.sys.entity.DataRule;
import com.pt.modules.sys.entity.User;

/**
 * 数据权限MAPPER接口
 * @author lgf
 * @version 2017-04-02
 */
@MyBatisMapper
public interface DataRuleMapper extends BaseMapper<DataRule> {

	public void deleteRoleDataRule(DataRule dataRule);
	
	public List<DataRule> findByUserId(User user);
}