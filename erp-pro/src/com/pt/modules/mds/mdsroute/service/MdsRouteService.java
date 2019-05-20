/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsroute.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsroute.entity.MdsRoute;
import com.pt.modules.mds.mdsroute.mapper.MdsRouteMapper;

/**
 * 工艺管理Service
 * @author 郑利
 * @version 2018-05-12
 */
@Service
@Transactional(readOnly = true)
public class MdsRouteService extends CrudService<MdsRouteMapper, MdsRoute> {

	public MdsRoute get(String id) {
		return super.get(id);
	}
	
	public List<MdsRoute> findList(MdsRoute mdsRoute) {
		return super.findList(mdsRoute);
	}
	
	public Page<MdsRoute> findPage(Page<MdsRoute> page, MdsRoute mdsRoute) {
		return super.findPage(page, mdsRoute);
	}
	
	@Transactional(readOnly = false)
	public void save(MdsRoute mdsRoute) {
		super.save(mdsRoute);
	}
	
	@Transactional(readOnly = false)
	public void delete(MdsRoute mdsRoute) {
		super.delete(mdsRoute);
	}
	
}