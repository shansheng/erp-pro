/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdspcmplanprocurement.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsprocurementplan.entity.CollectEntity;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.sys.supplier.entity.Supplier;

/**
 * 采购计划申请MAPPER接口
 * @author cjx
 * @version 2018-09-24
 */
@MyBatisMapper
public interface MdsPcmPlanProcurementMapper extends BaseMapper<MdsProcurementPlan> {
	
	/**
	 * 修改采购计划申请状态
	 * @param mdsProcurementPlan
	 * @return
	 */
	int updateState(MdsProcurementPlan mdsProcurementPlan);
	
	/**
	 * 统计查询
	 * @param collect
	 * @return
	 */
	List<MdsProcurementPlan> findCollect(CollectEntity collect);
}