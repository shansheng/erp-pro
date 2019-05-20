/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysqueclass.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.sys.sysqueclass.entity.SysQueClass;
import com.pt.modules.sys.sysqueclass.mapper.SysQueClassMapper;

/**
 * 问题分类管理Service
 * @author 郑利
 * @version 2018-10-24
 */
@Service
@Transactional(readOnly = true)
public class SysQueClassService extends CrudService<SysQueClassMapper, SysQueClass> {

	public SysQueClass get(String id) {
		return super.get(id);
	}
	
	public List<SysQueClass> findList(SysQueClass sysQueClass) {
		return super.findList(sysQueClass);
	}
	
	public Page<SysQueClass> findPage(Page<SysQueClass> page, SysQueClass sysQueClass) {
		return super.findPage(page, sysQueClass);
	}
	
	@Transactional(readOnly = false)
	public void save(SysQueClass sysQueClass) {
		super.save(sysQueClass);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysQueClass sysQueClass) {
		super.delete(sysQueClass);
	}
	
}