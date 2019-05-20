/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprocurementplanfinish.mapper;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;

import com.pt.modules.mds.mdsprocurementplanfinish.entity.MdsProcurementPlanFinish;

/**
 * 采购计划申请MAPPER接口
 * @author cjx
 * @version 2018-09-24
 */
@MyBatisMapper
public interface MdsProcurementPlanFinishMapper extends BaseMapper<MdsProcurementPlanFinish> {
	
	/**
	 * 批量插入数据
	 * @param entity
	 * @return
	 */
	int insert(List<MdsProcurementPlanFinish> list);
	
	
	/**
	 * 批量添加项目采购计划
	 */
	int insertProjectList(List<MdsProcurementPlanFinish> list);
	
	/**
	 * 修改采购计划申请状态
	 * @param MdsProcurementPlanFinish
	 * @return
	 */
	int updateState(MdsProcurementPlanFinish MdsProcurementPlanFinish);
	

	

}