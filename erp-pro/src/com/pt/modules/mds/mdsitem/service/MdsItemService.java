/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsitem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsitem.entity.MdsItem;
import com.pt.modules.mds.mdsitem.mapper.MdsItemMapper;

/**
 * 物料管理Service
 * @author 郑利
 * @version 2018-04-24
 */
@Service
@Transactional(readOnly = true)
public class MdsItemService extends CrudService<MdsItemMapper, MdsItem> {
	@Autowired
	private MdsItemMapper mdsItemMapper;
	public MdsItem get(String id) {
		return super.get(id);
	}
	
	public List<MdsItem> findList(MdsItem mdsItem) {
		return super.findList(mdsItem);
	}
	
	public Page<MdsItem> findPage(Page<MdsItem> page, MdsItem mdsItem) {
		return super.findPage(page, mdsItem);
	}
	

	
	@Transactional(readOnly = false)
	public void save(MdsItem mdsItem) {
		super.save(mdsItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(MdsItem mdsItem) {
		super.delete(mdsItem);
	}

	/**
	 * @param itemCode
	 * @return
	 */
	public List<MdsItem> getByCode(String itemCode) {
		
		return mapper.getByCode(itemCode);
	}

	/**
	 * @param itemName
	 * @return
	 */
	public List<MdsItem> getByName(String itemName) {
		
		return mapper.getByName(itemName);
	}
	
}