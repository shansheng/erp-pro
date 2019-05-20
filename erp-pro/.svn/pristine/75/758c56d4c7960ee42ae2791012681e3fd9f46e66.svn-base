/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsarrivalcheckout.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsarrivalcheckout.entity.MdsArrivalCheckout;
import com.pt.modules.mds.mdsarrivalcheckout.mapper.MdsArrivalCheckoutMapper;

/**
 * 到货检验Service
 * @author cjx
 * @version 2018-10-11
 */
@Service
@Transactional(readOnly = true)
public class MdsArrivalCheckoutService extends CrudService<MdsArrivalCheckoutMapper, MdsArrivalCheckout> {

	public MdsArrivalCheckout get(String id) {
		return super.get(id);
	}
	
	public List<MdsArrivalCheckout> findList(MdsArrivalCheckout mdsArrivalCheckout) {
		return super.findList(mdsArrivalCheckout);
	}
	
	public Page<MdsArrivalCheckout> findPage(Page<MdsArrivalCheckout> page, MdsArrivalCheckout mdsArrivalCheckout) {
		return super.findPage(page, mdsArrivalCheckout);
	}
	
	@Transactional(readOnly = false)
	public void save(List<MdsArrivalCheckout> list) {
		for (MdsArrivalCheckout mdsArrivalCheckout : list) {
			mdsArrivalCheckout.setState("1");
			mapper.update(mdsArrivalCheckout);
		}
	}

	/**
	 * @param mdsProcurmentPlanId
	 * @return
	 */
	public MdsArrivalCheckout getByMdsProcurmentPlanId(String mdsProcurmentPlanId) {
		
		return mapper.getByMdsProcurmentPlanId(mdsProcurmentPlanId);
	}
	
}