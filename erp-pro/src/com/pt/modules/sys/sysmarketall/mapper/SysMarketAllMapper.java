/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysmarketall.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.sys.sysmarket.entity.SysMarket;

/**
 * 销售订单下达MAPPER接口
 * @author 郑利
 * @version 2018-09-08
 */
@MyBatisMapper
public interface SysMarketAllMapper extends BaseMapper<SysMarket> {
	/**
	 * @param sysMarketView
	 */
	void sysMarketDispatch(SysMarket sysMarket);
}