package com.pt.modules.mds.mdsPurchaseSupplier.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsPurchaseSupplier.mapper.MdsPurchaseSupplierMapper;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.mds.mdsprocurementplan.mapper.MdsProcurementPlanMapper;
import com.pt.modules.sys.supplier.entity.Supplier;

@Service
@Transactional(readOnly = true)
public class MdsPurchaseSupplierService extends CrudService<MdsProcurementPlanMapper, MdsProcurementPlan> {
	@Autowired
    private MdsPurchaseSupplierMapper mdsProcurementSupplierMapper;
	
	public MdsProcurementPlan get(String id){
		return super.get(id);
	}
	
	public Page<MdsProcurementPlan> findPage(Page<MdsProcurementPlan> page, MdsProcurementPlan mdsProcurementPlan) {
		mdsProcurementPlan.setPage(page);
		page.setList(mdsProcurementSupplierMapper.findList(mdsProcurementPlan));
		return page;
	}
	@Transactional(readOnly=false)
	public void updateSupplier(MdsProcurementPlan mdsProcurementPlan) {
		String ids = mdsProcurementPlan.getId();
		String[] arrId = ids.split(",");
		for(String id : arrId) {
			MdsProcurementPlan  mdsPro = new MdsProcurementPlan();
			mdsPro.setId(id);
			mdsPro.setSupplierId(mdsProcurementPlan.getSupplierId());
			mdsPro.setSupplierName(mdsProcurementPlan.getSupplierName());
			mdsPro.preUpdate();
			mdsPro.setState("20");
			mdsProcurementSupplierMapper.updateSupplier(mdsPro);
		}
		return ;
	}
}
