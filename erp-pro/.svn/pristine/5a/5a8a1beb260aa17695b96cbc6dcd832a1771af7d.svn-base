/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmslocator.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.wms.wmslocator.entity.WmsLocator;
import com.pt.modules.wms.wmslocator.mapper.WmsLocatorMapper;

/**
 * 库位增删改查Service
 * @author 孙会楠
 * @version 2018-05-05
 */
@Service
@Transactional(readOnly = true)
public class WmsLocatorService extends CrudService<WmsLocatorMapper, WmsLocator> {
	@Resource
	private WmsLocatorMapper wmsLocatorMapper;

	public WmsLocator get(String id) {
		return super.get(id);
	}
	
	public List<WmsLocator> findList(WmsLocator wmsLocator) {
		return super.findList(wmsLocator);
	}
	
	public Page<WmsLocator> findPage(Page<WmsLocator> page, WmsLocator wmsLocator) {
		return super.findPage(page, wmsLocator);
	}
	
	@Transactional(readOnly = false)
	public void save(WmsLocator wmsLocator) {
		super.save(wmsLocator);
	}
	
	@Transactional(readOnly = false)
	public void delete(WmsLocator wmsLocator) {
		super.delete(wmsLocator);
	}
	
	public Page<WmsLocator> getDataByWmsinventoryId(Page<WmsLocator> page, WmsLocator wmsLocator) {
		dataRuleFilter(wmsLocator);
		wmsLocator.setPage(page);
		page.setList(wmsLocatorMapper.getDataByWmsinventoryId(wmsLocator));
		return page;
	}
}