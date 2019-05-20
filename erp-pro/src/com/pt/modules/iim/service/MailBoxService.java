/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.iim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.iim.entity.MailBox;
import com.pt.modules.iim.entity.MailPage;
import com.pt.modules.iim.mapper.MailBoxMapper;

/**
 * 收件箱Service
 * @author pt
 * @version 2015-11-13
 */
@Service
@Transactional(readOnly = true)
public class MailBoxService extends CrudService<MailBoxMapper, MailBox> {

	@Autowired
	private MailBoxMapper mailBoxMapper;
	public MailBox get(String id) {
		return super.get(id);
	}
	
	public List<MailBox> findList(MailBox mailBox) {
		return super.findList(mailBox);
	}
	
	public Page<MailBox> findPage(MailPage<MailBox> page, MailBox mailBox) {
		return super.findPage(page, mailBox);
	}
	
	@Transactional(readOnly = false)
	public void save(MailBox mailBox) {
		super.save(mailBox);
	}
	
	@Transactional(readOnly = false)
	public void delete(MailBox mailBox) {
		super.delete(mailBox);
	}
	
	public int getCount(MailBox mailBox) {
		return mailBoxMapper.getCount(mailBox);
	}
	
}