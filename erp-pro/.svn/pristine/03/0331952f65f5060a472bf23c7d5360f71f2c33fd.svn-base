/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysquestion.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.sys.sysquestion.entity.SysQueTransfer;
import com.pt.modules.sys.sysquestion.mapper.SysQueTransferMapper;

/**
 * 问题转移Service
 * @author 郑利
 * @version 2018-07-21
 */
@Service
@Transactional(readOnly = true)
public class SysQueTransferService extends CrudService<SysQueTransferMapper, SysQueTransfer> {

	public SysQueTransfer get(String id) {
		return super.get(id);
	}
	
	public List<SysQueTransfer> findList(SysQueTransfer sysQueTransfer) {
		return super.findList(sysQueTransfer);
	}
	
	public Page<SysQueTransfer> findPage(Page<SysQueTransfer> page, SysQueTransfer sysQueTransfer) {
		return super.findPage(page, sysQueTransfer);
	}
	
	@Transactional(readOnly = false)
	public void save(SysQueTransfer sysQueTransfer) {
		super.save(sysQueTransfer);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysQueTransfer sysQueTransfer) {
		super.delete(sysQueTransfer);
	}
	
}