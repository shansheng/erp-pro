/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sm.smuser.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.sm.smuser.entity.SmUser;
import com.pt.modules.sm.smuser.mapper.SmUserMapper;

/**
 * 商户信息Service
 * @author hexl
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class SmUserService extends CrudService<SmUserMapper, SmUser> {

	public SmUser get(String id) {
		return super.get(id);
	}
	
	public List<SmUser> findList(SmUser smUser) {
		return super.findList(smUser);
	}
	
	public Page<SmUser> findPage(Page<SmUser> page, SmUser smUser) {
		return super.findPage(page, smUser);
	}
	
	@Transactional(readOnly = false)
	public void save(SmUser smUser) {
		super.save(smUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmUser smUser) {
		super.delete(smUser);
	}
	
}