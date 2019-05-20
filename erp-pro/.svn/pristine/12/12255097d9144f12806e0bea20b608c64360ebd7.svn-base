/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsbom.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsbom.entity.MdsBomInstance;
import com.pt.modules.mds.mdsbom.mapper.MdsBomInstanceMapper;

/**
 * BOM实例Service
 * @author 龚志强
 * @version 2018-07-14
 */
@Service
@Transactional(readOnly = true)
public class MdsBomInstanceService extends CrudService<MdsBomInstanceMapper, MdsBomInstance> {

	public MdsBomInstance get(String id) {
		return super.get(id);
	}
	
	public List<MdsBomInstance> findList(MdsBomInstance mdsBomInstance) {
		return super.findList(mdsBomInstance);
	}
	
	public Page<MdsBomInstance> findPage(Page<MdsBomInstance> page, MdsBomInstance mdsBomInstance) {
		return super.findPage(page, mdsBomInstance);
	}
	
	@Transactional(readOnly = false)
	public void save(MdsBomInstance mdsBomInstance) {
		super.save(mdsBomInstance);
	}
	
	@Transactional(readOnly = false)
	public void delete(MdsBomInstance mdsBomInstance) {
		super.delete(mdsBomInstance);
	}
	
}