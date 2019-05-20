/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.service.one;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.test.entity.one.LeaveForm;
import com.pt.modules.test.mapper.one.LeaveFormMapper;

/**
 * 请假表单Service
 * @author lgf
 * @version 2017-06-11
 */
@Service
@Transactional(readOnly = true)
public class LeaveFormService extends CrudService<LeaveFormMapper, LeaveForm> {

	public LeaveForm get(String id) {
		return super.get(id);
	}
	
	public List<LeaveForm> findList(LeaveForm leaveForm) {
		return super.findList(leaveForm);
	}
	
	public Page<LeaveForm> findPage(Page<LeaveForm> page, LeaveForm leaveForm) {
		return super.findPage(page, leaveForm);
	}
	
	@Transactional(readOnly = false)
	public void save(LeaveForm leaveForm) {
		super.save(leaveForm);
	}
	
	@Transactional(readOnly = false)
	public void delete(LeaveForm leaveForm) {
		super.delete(leaveForm);
	}
	
}