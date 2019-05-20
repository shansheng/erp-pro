/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.mapper.grid;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.test.entity.grid.TestCountry;

/**
 * 国家MAPPER接口
 * @author lgf
 * @version 2017-06-12
 */
@MyBatisMapper
public interface TestCountryMapper extends BaseMapper<TestCountry> {
	
}