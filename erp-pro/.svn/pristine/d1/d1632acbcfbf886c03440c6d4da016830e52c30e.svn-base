/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsbom.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsbom.entity.MdsBom;
import com.pt.modules.mds.mdsbom.mapper.MdsBomMapper;

/**
 * BOMService
 * @author 龚志强
 * @version 2018-07-14
 */
@Service
@Transactional(readOnly = true)
public class MdsBomService extends CrudService<MdsBomMapper, MdsBom> {

	public MdsBom get(String id) {
		return super.get(id);
	}
	
	public List<MdsBom> findList(MdsBom mdsBom) {
		return super.findList(mdsBom);
	}
	
	public Page<MdsBom> findPage(Page<MdsBom> page, MdsBom mdsBom) {
		return super.findPage(page, mdsBom);
	}
	
	@Transactional(readOnly = false)
	public void save(MdsBom mdsBom) {
		super.save(mdsBom);
	}
	
	@Transactional(readOnly = false)
	public void delete(MdsBom mdsBom) {
		super.delete(mdsBom);
	}
	
}