/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdspcmplanprocurement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdspcmplanprocurement.mapper.MdsPcmPlanProcurementMapper;
import com.pt.modules.mds.mdsprocurementplan.entity.CollectEntity;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;

/**
 * 采购计划申请Service
 * @author cjx
 * @version 2018-09-24
 */
@Service
@Transactional(readOnly = true)
public class MdsPcmPlanProcurementService extends CrudService<MdsPcmPlanProcurementMapper, MdsProcurementPlan> {
	
	public MdsProcurementPlan get(String id) {
		return super.get(id);
	}
	
	public List<MdsProcurementPlan> findList(CollectEntity collectEntity) {
		//查找统计中的数据
		List<MdsProcurementPlan> list = mapper.findCollect(collectEntity);
		return list;
	}
	
	public Page<MdsProcurementPlan> findPage(Page<MdsProcurementPlan> page, MdsProcurementPlan mdsProcurementPlan) {
		return super.findPage(page, mdsProcurementPlan);
	}
	
	//保存修改的采购数量以及采购时间
	@Transactional(readOnly = false)
	public void save(List<MdsProcurementPlan> mdsProcurementPlan) {
		for (MdsProcurementPlan mdsProcurementPlan2 : mdsProcurementPlan) {
			mapper.update(mdsProcurementPlan2);
		}
		
	}
	
	//下达
	@Transactional(readOnly = false)
	public void addIndent(MdsProcurementPlan mdsProcurementPlan) {
			// 修改状态为已下达
			mdsProcurementPlan.setState("25");
			mdsProcurementPlan.preUpdate();
			mdsProcurementPlan.setFinishState("3");
			mapper.updateState(mdsProcurementPlan);
	}
	
	//驳回
	@Transactional(readOnly = false)
	public void reject(MdsProcurementPlan mdsProcurementPlan) {
		mdsProcurementPlan.setState("5");
		mdsProcurementPlan.preUpdate();
		mdsProcurementPlan.setFinishState("0");
		mapper.updateState(mdsProcurementPlan);
	}
}