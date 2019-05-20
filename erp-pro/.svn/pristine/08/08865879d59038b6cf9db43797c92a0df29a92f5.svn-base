/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprocurementplan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsprocurementplan.entity.CollectEntity;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.mds.mdsprocurementplan.mapper.MdsProcurementPlanMapper;

/**
 * 采购计划申请Service
 * @author cjx
 * @version 2018-09-24
 */
@Service
@Transactional(readOnly = true)
public class MdsProcurementPlanService extends CrudService<MdsProcurementPlanMapper, MdsProcurementPlan> {
	
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
	
	//保存采购人
	@Transactional(readOnly = false)
	public void saveBuyer(List<MdsProcurementPlan> mdsProcurementPlan) {
		for (MdsProcurementPlan mdsProcurementPlan2 : mdsProcurementPlan) {
			mapper.saveBuyer(mdsProcurementPlan2);
		}
		
	}
	
	@Transactional(readOnly = false)
	public void submit(MdsProcurementPlan mdsProcurementPlan) {
		mdsProcurementPlan.setState("10");
		mdsProcurementPlan.setFinishState("1");
		mdsProcurementPlan.preUpdate();
		mapper.updateState(mdsProcurementPlan);
	}

	/**
	 * @param sysMarketId
	 * @return
	 */
	public MdsProcurementPlan getByMarketId(String sysMarketId) {
		
		return mapper.getByMarketId(sysMarketId);
	}
	/**
	 * 修改时间
	 * @param mdsProcurementPlan
	 */
	@Transactional(readOnly = false)
	public void updateRequireDate(MdsProcurementPlan mdsProcurementPlan){
	    System.out.println("mdsProcurementPlan:" + mdsProcurementPlan);
	    String mdsId = mdsProcurementPlan.getId();
	    String[] ids = mdsId.split(",");
	    for(String id : ids){
	    	MdsProcurementPlan mds = new MdsProcurementPlan();
	    	mds.setId(id);
	    	mds.setRequiredDate(mdsProcurementPlan.getRequiredDate());
	    	mapper.updateRequireDate(mds);
	    }
	    		
	}
	/**
	 * 删除
	 * @param ids
	 */
	@Transactional(readOnly = false)
	public void deleteByLogic(String ids){
		if(null != ids && "" != ids) {
			String[] idArray =ids.split(",");
			for(String id : idArray){
				MdsProcurementPlan mdsProcurementPlan = new MdsProcurementPlan();
				mdsProcurementPlan.setId(id);
				mdsProcurementPlan.setDelFlag("1");
				mapper.deleteByLogic(mdsProcurementPlan);
			}
		}
		return;
	}
	/**
	 * 统计方法,用来将查询出的list统计
	 * @param list
	 * @return
	 *//*
	public List<MdsProcurementPlan> statistics(List<MdsProcurementPlan> list) {
		List<MdsProcurementPlan> result = new ArrayList<>();
		// 遍历list
		for (MdsProcurementPlan mdsProcurementPlan : list) {
			// 判断结果list是否为空,如果为空就将查询到的类直接插入
			if (result.size() == 0) {
				result.add(mdsProcurementPlan);
			} else {
				for (MdsProcurementPlan resultMPC : result) {
					// 遍历结果集,如果查询到的类中属性的值与结果集中的相等,那么就将采购数目相加
					if (resultMPC.equals(mdsProcurementPlan)) {
						resultMPC.setPurchaseAmount(resultMPC.getPurchaseAmount() + mdsProcurementPlan.getPurchaseAmount());
					} else {
						// 否则就将类添加到结果集中
						result.add(mdsProcurementPlan);
					}
				}
			}
		}
		return result;
	}*/
}