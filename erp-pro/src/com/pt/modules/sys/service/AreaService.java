/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.service.TreeService;
import com.pt.modules.sys.entity.Area;
import com.pt.modules.sys.mapper.AreaMapper;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author pt
 * @version 2017-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaMapper, Area> {

	
	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
}
