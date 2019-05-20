/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprocurementplan.mapper;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsprocurementplan.entity.CollectEntity;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;

/**
 * 采购计划申请MAPPER接口
 * @author cjx
 * @version 2018-09-24
 */
@MyBatisMapper
public interface MdsProcurementPlanMapper extends BaseMapper<MdsProcurementPlan> {
	
	/**
	 * 批量插入数据
	 * @param entity
	 * @return
	 */
	int insert(List<MdsProcurementPlan> list);
	
	/**
	 * 修改采购人
	 * @param mdsProcurementPlan
	 * @return
	 */
	int saveBuyer(MdsProcurementPlan mdsProcurementPlan);
	
	/**
	 * 批量添加项目采购计划
	 */
	int insertProjectList(List<MdsProcurementPlan> list);
	
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


	/**
	 * @param sysMarketId
	 * @return
	 */
	MdsProcurementPlan getByMarketId(String sysMarketId);
	/**
	 * 修改时间
	 * @param mdsProcurementPlan
	 */
	void updateRequireDate(MdsProcurementPlan mdsProcurementPlan);
	/**
	 * 逻辑删除
	 */
	int deleteByLogic(MdsProcurementPlan mdsProcurementPlan);

}