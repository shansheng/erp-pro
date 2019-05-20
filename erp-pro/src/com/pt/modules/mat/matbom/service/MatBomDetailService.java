/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matbom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.service.TreeService;
import com.pt.common.utils.StringUtils;
import com.pt.modules.mat.matbom.entity.MatBomDetail;
import com.pt.modules.mat.matbom.mapper.MatBomDetailMapper;

/**
 * Bom管理Service
 * @author 郑利
 * @version 2018-09-27
 */
@Service
@Transactional(readOnly = true)
public class MatBomDetailService extends TreeService<MatBomDetailMapper, MatBomDetail> {
@Autowired
private MatBomDetailMapper matBomDetailMapper;
	public MatBomDetail get(String id) {
		return super.get(id);
	}
	public MatBomDetail getD(String parentId) {
		return matBomDetailMapper.getD(parentId);
	}
	public List<MatBomDetail> findList(MatBomDetail matBomDetail) {
		if (StringUtils.isNotBlank(matBomDetail.getParentIds())){
			matBomDetail.setParentIds(","+matBomDetail.getParentIds()+",");
		}
		return super.findList(matBomDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(MatBomDetail matBomDetail) {
		super.save(matBomDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(MatBomDetail matBomDetail) {
		super.delete(matBomDetail);
	}
	/**
	 * @param matBomDetail
	 */
	public void update(MatBomDetail matBomDetail) {
		mapper.update(matBomDetail);
		
	}
	/**
	 * @param matBomDetail
	 * @return
	 */
	public MatBomDetail getByItemBomId(MatBomDetail matBomDetail) {
	
		return mapper.getByItemBomId(matBomDetail);
	}
	/**
	 * @param matBomId
	 * @return
	 */
	public List<MatBomDetail> getByMatBomId(String matBomId) {
		
		return mapper.getByMatBomId(matBomId);
	}
	/**
	 * @param matBomDetail
	 * @return
	 */
	public MatBomDetail getByIBId(MatBomDetail matBomDetail) {
		
		return mapper.getByIBId(matBomDetail);
	}

	
}