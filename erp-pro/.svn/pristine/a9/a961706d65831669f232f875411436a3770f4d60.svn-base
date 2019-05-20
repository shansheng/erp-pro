/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsReplaceItem.mapper;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsReplaceItem.entity.MdsReplaceItem;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;


/**
 * 代料申请MAPPER接口
 * @author zjj
 * @version 2018-10-11
 */
@MyBatisMapper
public interface MdsReplaceItemMapper extends BaseMapper<MdsReplaceItem> {
	List<MdsProcurementPlan> findProList(MdsProcurementPlan mdsProcurementPlan);
	MdsReplaceItem findById(String mdsProcurementPlanId);
	/*
	 * 更改采购计划的物料
	 */
	int updateMdsPro(MdsProcurementPlan mdsProcurementPlan);
	/**
	 * 修改状态
	 * @param mdsProcurementPlan
	 * @return
	 */
	int updateReplaceState(MdsReplaceItem mdsReplaceItem);
	List<MdsReplaceItem> findReplaceList(MdsReplaceItem mdsReplaceItem);
}