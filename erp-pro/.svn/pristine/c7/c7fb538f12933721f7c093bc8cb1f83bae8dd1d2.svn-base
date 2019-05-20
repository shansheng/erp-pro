/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdspurchaseconfirmation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.mds.mdspurchaseconfirmation.mapper.MdsPurchaseConfirmationMapper;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.mapper.UserMapper;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 采购员查看页Service
 * @author cjx
 * @version 2018-09-24
 */
@Service
@Transactional(readOnly = true)
public class MdsPurchaseConfirmationService extends CrudService<MdsPurchaseConfirmationMapper, MdsProcurementPlan> {
	
	@Autowired
	private UserMapper userMapper;
	
	public MdsProcurementPlan get(String id) {
		return super.get(id);
	}
	
	
	public Page<MdsProcurementPlan> findPage(Page<MdsProcurementPlan> page, MdsProcurementPlan mdsProcurementPlan) {
		User user = UserUtils.getUser();
		user = userMapper.get(user.getId());
		mdsProcurementPlan.setCreateBy(user);
		return super.findPage(page, mdsProcurementPlan);
	}

	
	//下达
	@Transactional(readOnly = false)
	public void addIndent(MdsProcurementPlan mdsProcurementPlan) {
			// 修改状态为已下达
			mdsProcurementPlan.setState("30");
			mdsProcurementPlan.preUpdate();
			mdsProcurementPlan.setFinishState("4");
			mapper.updateState(mdsProcurementPlan);
	}
	
	//驳回
	@Transactional(readOnly = false)
	public void reject(MdsProcurementPlan mdsProcurementPlan) {
		mdsProcurementPlan.setState("15");
		mdsProcurementPlan.preUpdate();
		mdsProcurementPlan.setFinishState("1");
		mapper.updateState(mdsProcurementPlan);
	}
}