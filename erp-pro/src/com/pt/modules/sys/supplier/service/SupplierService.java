/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.supplier.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.sys.supplier.entity.Supplier;
import com.pt.modules.sys.supplier.mapper.SupplierMapper;

/**
 * 供应商管理Service
 * @author 郑利
 * @version 2018-08-29
 */
@Service
@Transactional(readOnly = true)
public class SupplierService extends CrudService<SupplierMapper, Supplier> {

	public Supplier get(String id) {
		return super.get(id);
	}
	
	public List<Supplier> findList(Supplier supplier) {
		return super.findList(supplier);
	}
	
	public Page<Supplier> findPage(Page<Supplier> page, Supplier supplier) {
		return super.findPage(page, supplier);
	}
	
	@Transactional(readOnly = false)
	public void save(Supplier supplier) {
		super.save(supplier);
	}
	
	@Transactional(readOnly = false)
	public void delete(Supplier supplier) {
		super.delete(supplier);
	}
	
}