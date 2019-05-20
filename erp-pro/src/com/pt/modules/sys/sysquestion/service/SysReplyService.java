/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysquestion.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.sys.sysquestion.entity.SysReply;
import com.pt.modules.sys.sysquestion.mapper.SysReplyMapper;

/**
 * 回复Service
 * @author 郑利
 * @version 2018-07-13
 */
@Service
@Transactional(readOnly = true)
public class SysReplyService extends CrudService<SysReplyMapper, SysReply> {

	public SysReply get(String id) {
		return super.get(id);
	}
	
	public List<SysReply> findList(SysReply sysReply) {
		return super.findList(sysReply);
	}
	
	public Page<SysReply> findPage(Page<SysReply> page, SysReply sysReply) {
		return super.findPage(page, sysReply);
	}
	
	@Transactional(readOnly = false)
	public void save(SysReply sysReply) {
		super.save(sysReply);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysReply sysReply) {
		super.delete(sysReply);
	}
	
}