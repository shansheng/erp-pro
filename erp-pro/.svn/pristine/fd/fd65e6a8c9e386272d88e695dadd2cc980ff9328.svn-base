/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.prickle.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.sys.prickle.entity.Prickle;
import com.pt.modules.sys.prickle.mapper.PrickleMapper;

/**
 * 计量单位管理Service
 * @author 郑利
 * @version 2018-08-29
 */
@Service
@Transactional(readOnly = true)
public class PrickleService extends CrudService<PrickleMapper, Prickle> {

	public Prickle get(String id) {
		return super.get(id);
	}
	
	public List<Prickle> findList(Prickle prickle) {
		return super.findList(prickle);
	}
	
	public Page<Prickle> findPage(Page<Prickle> page, Prickle prickle) {
		return super.findPage(page, prickle);
	}
	
	@Transactional(readOnly = false)
	public void save(Prickle prickle) {
		super.save(prickle);
	}
	
	@Transactional(readOnly = false)
	public void delete(Prickle prickle) {
		super.delete(prickle);
	}
	
}