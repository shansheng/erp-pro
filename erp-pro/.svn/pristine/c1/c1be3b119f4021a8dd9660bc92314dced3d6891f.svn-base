/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsequipment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsequipment.entity.MdsEquipment;
import com.pt.modules.mds.mdsequipment.mapper.MdsEquipmentMapper;

/**
 * 设备管理Service
 * @author 郑利
 * @version 2018-05-26
 */
@Service
@Transactional(readOnly = true)
public class MdsEquipmentService extends CrudService<MdsEquipmentMapper, MdsEquipment> {

	public MdsEquipment get(String id) {
		return super.get(id);
	}
	
	public List<MdsEquipment> findList(MdsEquipment mdsEquipment) {
		return super.findList(mdsEquipment);
	}
	
	public Page<MdsEquipment> findPage(Page<MdsEquipment> page, MdsEquipment mdsEquipment) {
		return super.findPage(page, mdsEquipment);
	}
	
	@Transactional(readOnly = false)
	public void save(MdsEquipment mdsEquipment) {
		super.save(mdsEquipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(MdsEquipment mdsEquipment) {
		super.delete(mdsEquipment);
	}
	
}