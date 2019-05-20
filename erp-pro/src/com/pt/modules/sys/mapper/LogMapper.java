/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.sys.entity.Log;

/**
 * 日志MAPPER接口
 * @author pt
 * @version 2017-05-16
 */
@MyBatisMapper
public interface LogMapper extends BaseMapper<Log> {

	public void empty();
}
