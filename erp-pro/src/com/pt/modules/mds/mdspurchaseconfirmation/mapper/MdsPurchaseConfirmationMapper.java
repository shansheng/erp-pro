/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdspurchaseconfirmation.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;

/**
 * 采购员查看页MAPPER接口
 * @author cjx
 * @version 2018-09-24
 */
@MyBatisMapper
public interface MdsPurchaseConfirmationMapper extends BaseMapper<MdsProcurementPlan> {
	
	/**
	 * 修改采购计划申请状态
	 * @param mdsProcurementPlan
	 * @return
	 */
	int updateState(MdsProcurementPlan mdsProcurementPlan);

}