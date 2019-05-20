/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.selectmesewo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mes.selectmesewo.entity.MesEwoV;
import com.pt.modules.mes.selectmesewo.mapper.MesEwoVMapper;

/**
 * 生产计划查询Service
 * @author 郑利
 * @version 2018-06-28
 */
@Service
@Transactional(readOnly = true)
public class MesEwoVService extends CrudService<MesEwoVMapper, MesEwoV> {

	public MesEwoV get(String id) {
		return super.get(id);
	}
	
	public List<MesEwoV> findList(MesEwoV mesEwoV) {
		return super.findList(mesEwoV);
	}
	
	public Page<MesEwoV> findPage(Page<MesEwoV> page, MesEwoV mesEwoV) {
		return super.findPage(page, mesEwoV);
	}
	
	@Transactional(readOnly = false)
	public void save(MesEwoV mesEwoV) {
		super.save(mesEwoV);
	}
	
	@Transactional(readOnly = false)
	public void delete(MesEwoV mesEwoV) {
		super.delete(mesEwoV);
	}
	
}