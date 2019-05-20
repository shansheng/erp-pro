/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mrp.mrpwofind.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mrp.mrpwofind.entity.MrpWoFeeding;
import com.pt.modules.mrp.mrpwofind.mapper.MrpWoFeedingMapper;

/**
 * 任务查询Service
 * @author 孙会楠
 * @version 2018-06-07
 */
@Service
@Transactional(readOnly = true)
public class MrpWoFeedingService extends CrudService<MrpWoFeedingMapper, MrpWoFeeding> {

	public MrpWoFeeding get(String id) {
		return super.get(id);
	}
	
	public List<MrpWoFeeding> findList(MrpWoFeeding mrpWoFeeding) {
		return super.findList(mrpWoFeeding);
	}
	
	public Page<MrpWoFeeding> findPage(Page<MrpWoFeeding> page, MrpWoFeeding mrpWoFeeding) {
		return super.findPage(page, mrpWoFeeding);
	}
	
	@Transactional(readOnly = false)
	public void save(MrpWoFeeding mrpWoFeeding) {
		super.save(mrpWoFeeding);
	}
	
	@Transactional(readOnly = false)
	public void delete(MrpWoFeeding mrpWoFeeding) {
		super.delete(mrpWoFeeding);
	}
	
}