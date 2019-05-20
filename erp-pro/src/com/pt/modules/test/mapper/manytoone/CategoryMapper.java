/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.mapper.manytoone;

import com.pt.core.persistence.TreeMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.test.entity.manytoone.Category;

/**
 * 商品类型MAPPER接口
 * @author lf
 * @version 2017-06-11
 */
@MyBatisMapper
public interface CategoryMapper extends TreeMapper<Category> {
	
}