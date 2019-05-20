/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsitemtransaction.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.wms.wmsitemtransaction.entity.WmsItemTransaction;
import com.pt.modules.wms.wmsitemtransaction.mapper.WmsItemTransactionMapper;

/**
 * 库房事务Service
 * @author 郑利
 * @version 2018-05-18
 */
@Service
@Transactional(readOnly = true)
public class WmsItemTransactionService extends CrudService<WmsItemTransactionMapper, WmsItemTransaction> {

	public WmsItemTransaction get(String id) {
		return super.get(id);
	}
	
	public List<WmsItemTransaction> findList(WmsItemTransaction wmsItemTransaction) {
		return super.findList(wmsItemTransaction);
	}
	
	public Page<WmsItemTransaction> findPage(Page<WmsItemTransaction> page, WmsItemTransaction wmsItemTransaction) {
		return super.findPage(page, wmsItemTransaction);
	}
	
	@Transactional(readOnly = false)
	public void save(WmsItemTransaction wmsItemTransaction) {
		super.save(wmsItemTransaction);
	}
	
	@Transactional(readOnly = false)
	public void delete(WmsItemTransaction wmsItemTransaction) {
		super.delete(wmsItemTransaction);
	}
	
}