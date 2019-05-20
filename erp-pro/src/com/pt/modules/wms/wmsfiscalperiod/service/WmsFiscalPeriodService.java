/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsfiscalperiod.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.wms.wmsfiscalperiod.entity.WmsFiscalPeriod;
import com.pt.modules.wms.wmsfiscalperiod.mapper.WmsFiscalPeriodMapper;

/**
 * 期段管理Service
 * @author hexl
 * @version 2018-05-12
 */
@Service
@Transactional(readOnly = true)
public class WmsFiscalPeriodService extends CrudService<WmsFiscalPeriodMapper, WmsFiscalPeriod> {

	public WmsFiscalPeriod get(String id) {
		return super.get(id);
	}
	
	public List<WmsFiscalPeriod> findList(WmsFiscalPeriod wmsFiscalPeriod) {
		return super.findList(wmsFiscalPeriod);
	}
	
	public Page<WmsFiscalPeriod> findPage(Page<WmsFiscalPeriod> page, WmsFiscalPeriod wmsFiscalPeriod) {
		return super.findPage(page, wmsFiscalPeriod);
	}
	
	@Transactional(readOnly = false)
	public void save(WmsFiscalPeriod wmsFiscalPeriod) {
		super.save(wmsFiscalPeriod);
	}
	
	@Transactional(readOnly = false)
	public void delete(WmsFiscalPeriod wmsFiscalPeriod) {
		super.delete(wmsFiscalPeriod);
	}
	
}