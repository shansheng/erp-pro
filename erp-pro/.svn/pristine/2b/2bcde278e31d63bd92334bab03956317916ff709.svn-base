package com.pt.modules.mds.mdsPurchaseSupplier.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.sys.supplier.entity.Supplier;

@MyBatisMapper
public interface MdsPurchaseSupplierMapper extends BaseMapper<MdsProcurementPlan>{

	/**
	 * 修改供应商
	 */
	int updateSupplier(MdsProcurementPlan mdsProcurementPlan);
	List<MdsProcurementPlan> findList(MdsProcurementPlan mdsProcurementPlan);
}
