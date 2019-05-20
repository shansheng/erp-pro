/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.selectmesewo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mes.selectmesewo.entity.MesOperationSeqV;
import com.pt.modules.mes.selectmesewo.mapper.MesOperationSeqVMapper;


/**
 * 生产计划查询Service
 * @author 郑利
 * @version 2018-06-28
 */
@Service
@Transactional(readOnly = true)
public class MesOperationSeqVService extends CrudService<MesOperationSeqVMapper, MesOperationSeqV> {

	public MesOperationSeqV get(String id) {
		return super.get(id);
	}
	
	public List<MesOperationSeqV> findList(MesOperationSeqV mesOperationSeqV) {
		return super.findList(mesOperationSeqV);
	}
	
	public Page<MesOperationSeqV> findPage(Page<MesOperationSeqV> page, MesOperationSeqV mesOperationSeqV) {
		return super.findPage(page, mesOperationSeqV);
	}
	
	@Transactional(readOnly = false)
	public void save(MesOperationSeqV mesOperationSeqV) {
		super.save(mesOperationSeqV);
	}
	
	@Transactional(readOnly = false)
	public void delete(MesOperationSeqV mesOperationSeqV) {
		super.delete(mesOperationSeqV);
	}
	
}