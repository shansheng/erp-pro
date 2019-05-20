/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.mapper.manytoone;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.test.entity.manytoone.Goods;

/**
 * 商品MAPPER接口
 * @author liugf
 * @version 2017-06-12
 */
@MyBatisMapper
public interface GoodsMapper extends BaseMapper<Goods> {
	
}