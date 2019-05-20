/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matbom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mat.matbom.entity.MatBom;
import com.pt.modules.mat.matbom.entity.MatBomDetail;
import com.pt.modules.mat.matbom.mapper.MatBomMapper;
import com.pt.modules.sys.sysmarket.mapper.SysMarketMapper;
import com.pt.modules.work.workorder.mapper.WorkOrderMapper;

/**
 * mat_bomService
 * @author 郑利
 * @version 2018-09-27
 */
@Service
@Transactional(readOnly = true)
public class MatBomService extends CrudService<MatBomMapper, MatBom> {

	@Autowired
	private WorkOrderMapper workOrderMapper;
	@Autowired
	private SysMarketMapper sysMarketMapper;
	public MatBom get(String id) {
		return super.get(id);
	}
	
	public List<MatBom> findList(MatBom matBom) {
		return super.findList(matBom);
	}
	
	public Page<MatBom> findPage(Page<MatBom> page, MatBom matBom) {
		return super.findPage(page, matBom);
	}
	
	@Transactional(readOnly = false)
	public void save(MatBom matBom) {
		super.save(matBom);
	}
	
	@Transactional(readOnly = false)
	public void delete(MatBom matBom) {
		super.delete(matBom);
	}

	/**
	 * @param matBomDetail
	 */
	public void update(MatBomDetail matBomDetail) {
		matBomDetail.preUpdate();
		mapper.update(matBomDetail);
		
	}
	@Transactional(readOnly = false)
	public void confirm(MatBom matBom) {
		matBom.setState("30");
		matBom.preUpdate();
		String matBomId=matBom.getId();
		MatBom matBoms=mapper.get(matBomId);
		String sysMarketListId=matBoms.getSysMarketListId();
		workOrderMapper.updateLookIs(sysMarketListId);
		mapper.changeConfirm(matBom);
		String sysMarketId=matBoms.getSysMarketId();
		sysMarketMapper.updateShow(sysMarketId);
	}
	
	@Transactional(readOnly = false)
	public void delconfirm(MatBom matBom) {
		matBom.setState("20");
		matBom.preUpdate();
		String matBomId=matBom.getId();
		MatBom matBoms=mapper.get(matBomId);
		String sysMarketListId=matBoms.getSysMarketListId();
		workOrderMapper.updateNoLookIs(sysMarketListId);
		mapper.changeConfirm(matBom);
	}

	/**
	 * @param matBom
	 * @return
	 */
	public MatBom getByMarketItemId(MatBom matBom) {
		
		return mapper.getByMarketItemId(matBom);
	}
}