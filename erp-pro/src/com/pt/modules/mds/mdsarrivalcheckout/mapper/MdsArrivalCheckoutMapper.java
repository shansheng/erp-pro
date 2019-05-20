/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsarrivalcheckout.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsarrivalcheckout.entity.MdsArrivalCheckout;

/**
 * 到货检验MAPPER接口
 * @author cjx
 * @version 2018-10-11
 */
@MyBatisMapper
public interface MdsArrivalCheckoutMapper extends BaseMapper<MdsArrivalCheckout> {

	/**
	 * @param mdsProcurmentPlanId
	 * @return
	 */
	MdsArrivalCheckout getByMdsProcurmentPlanId(String mdsProcurmentPlanId);
	
}