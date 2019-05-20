/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvbalancebilllist.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.wms.wmsinvbalancebilllist.entity.WmsInvBalanceBillListV;
import com.pt.modules.wms.wmsinvbalancebilllist.mapper.WmsInvBalanceBillListVMapper;

/**
 * 结转查询Service
 * @author hexl
 * @version 2018-05-18
 */
@Service
@Transactional(readOnly = true)
public class WmsInvBalanceBillListVService extends CrudService<WmsInvBalanceBillListVMapper, WmsInvBalanceBillListV> {

	public WmsInvBalanceBillListV get(String id) {
		return super.get(id);
	}
	
	public List<WmsInvBalanceBillListV> findList(WmsInvBalanceBillListV wmsInvBalanceBillListV) {
		return super.findList(wmsInvBalanceBillListV);
	}
	
	public Page<WmsInvBalanceBillListV> findPage(Page<WmsInvBalanceBillListV> page, WmsInvBalanceBillListV wmsInvBalanceBillListV) {
		return super.findPage(page, wmsInvBalanceBillListV);
	}
	
	@Transactional(readOnly = false)
	public void save(WmsInvBalanceBillListV wmsInvBalanceBillListV) {
		super.save(wmsInvBalanceBillListV);
	}
	
	@Transactional(readOnly = false)
	public void delete(WmsInvBalanceBillListV wmsInvBalanceBillListV) {
		super.delete(wmsInvBalanceBillListV);
	}
	
}