/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.mapper;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.sys.entity.DictValue;

/**
 * 数据字典MAPPER接口
 * @author lgf
 * @version 2017-01-16
 */
@MyBatisMapper
public interface DictValueMapper extends BaseMapper<DictValue> {

	public List<DictValue> findListSql(DictValue dictValue);

	/**
	 * @param dictValue
	 * @return
	 */
	public List<DictValue> getByClassify();
}