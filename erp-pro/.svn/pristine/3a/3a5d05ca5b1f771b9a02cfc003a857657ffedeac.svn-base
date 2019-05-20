/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.mesworkshift.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.common.utils.StringUtils;
import com.pt.modules.mes.mesworkshift.entity.MesWorkshift;
import com.pt.modules.mes.mesworkshift.mapper.MesWorkshiftMapper;
import com.pt.modules.mes.mesworkshift.entity.MesWorkshiftUser;
import com.pt.modules.mes.mesworkshift.mapper.MesWorkshiftUserMapper;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 班组管理Service
 * @author 孙会楠
 * @version 2018-05-05
 */
@Service
@Transactional(readOnly = true)
public class MesWorkshiftService extends CrudService<MesWorkshiftMapper, MesWorkshift> {

	@Autowired
	private MesWorkshiftMapper mesWorkshiftMapper;
	
	public MesWorkshift get(String id) {
		return super.get(id);
	}
	
	public List<MesWorkshift> findList(MesWorkshift mesWorkshift) {
		return super.findList(mesWorkshift);
	}
	
	public Page<MesWorkshift> findPage(Page<MesWorkshift> page, MesWorkshift mesWorkshift) {
		return super.findPage(page, mesWorkshift);
	}
	
	@Transactional(readOnly = false)
	public void save(MesWorkshift mesWorkshift) {
		super.save(mesWorkshift);
	}
	
	@Transactional(readOnly = false)
	public void delete(MesWorkshift mesWorkshift) {
		super.delete(mesWorkshift);
	}

	
	
}