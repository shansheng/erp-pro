/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matbom.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mat.matbom.entity.MatBom;
import com.pt.modules.mat.matbom.entity.MatBomTable;
import com.pt.modules.mat.matbom.mapper.MatBomTableMapper;

/**
 * mat_bom_tableService
 * @author 郑利
 * @version 2018-09-30
 */
@Service
@Transactional(readOnly = true)
public class MatBomTableService extends CrudService<MatBomTableMapper, MatBomTable> {

	public MatBomTable get(String id) {
		return super.get(id);
	}
	
	public List<MatBomTable> findList(MatBomTable matBomTable) {
		return super.findList(matBomTable);
	}
	
	public Page<MatBomTable> findPage(Page<MatBomTable> page, MatBomTable matBomTable) {
		return super.findPage(page, matBomTable);
	}
	
	@Transactional(readOnly = false)
	public void save(MatBomTable matBomTable) {
		super.save(matBomTable);
	}
	
	@Transactional(readOnly = false)
	public void delete(MatBomTable matBomTable) {
		super.delete(matBomTable);
	}

	/**
	 * @param matBomDetailId
	 * @return
	 */
	public MatBom gets(String matBomDetailId) {
		
		return mapper.gets(matBomDetailId);
	}
	
}