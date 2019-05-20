/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprocurementplanfinish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsprocurementplanfinish.entity.MdsPcmPlanFinishList;
import com.pt.modules.mds.mdsprocurementplanfinish.entity.MdsProcurementPlanFinish;
import com.pt.modules.mds.mdsprocurementplanfinish.mapper.MdsPcmPlanFinishListMapper;
import com.pt.modules.mds.mdsprocurementplanfinish.mapper.MdsProcurementPlanFinishMapper;

/**
 * 采购完成状态查看页Service
 * @author cjx
 * @version 2018-09-24
 */
@Service
@Transactional(readOnly = true)
public class MdsProcurementPlanFinishService extends CrudService<MdsProcurementPlanFinishMapper, MdsProcurementPlanFinish> {
	
	@Autowired
	private MdsPcmPlanFinishListMapper mdsPcmPlanFinishListMapper;
	
	
	public MdsProcurementPlanFinish get(String id) {
		MdsProcurementPlanFinish mdsProcurementPlanFinish = super.get(id);
		mdsProcurementPlanFinish.setMdspcmplanfinishlist(mdsPcmPlanFinishListMapper.findList(new MdsPcmPlanFinishList(mdsProcurementPlanFinish)));
		return mdsProcurementPlanFinish;
	}
	

	
	public Page<MdsProcurementPlanFinish> findPage(Page<MdsProcurementPlanFinish> page, MdsProcurementPlanFinish MdsProcurementPlanFinish) {
		return super.findPage(page, MdsProcurementPlanFinish);
	}
	
}