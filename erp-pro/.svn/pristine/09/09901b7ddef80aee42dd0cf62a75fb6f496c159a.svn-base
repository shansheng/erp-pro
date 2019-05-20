/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matrequisition.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.common.utils.StringUtils;
import com.pt.modules.mat.matrequisition.dto.RequistionOutDTO;
import com.pt.modules.mat.matrequisition.entity.MatRequisition;
import com.pt.modules.mat.matrequisition.mapper.MatRequisitionMapper;

import com.pt.modules.mat.matrequisition.entity.MatRequisitionList;
import com.pt.modules.mat.matrequisition.mapper.MatRequisitionListMapper;

/**
 * 领料单流程Service
 * @author 郑利
 * @version 2018-09-18
 */
@Service
@Transactional(readOnly = true)
public class MatRequisitionService extends CrudService<MatRequisitionMapper, MatRequisition> {

	@Autowired
	private MatRequisitionListMapper matRequisitionListMapper;
	@Autowired
	private MatRequisitionMapper matRequisitionMapper;
	
	
	public MatRequisition get(String id) {
		MatRequisition matRequisition = super.get(id);
		matRequisition.setMatRequisitionListList(matRequisitionListMapper.findList(new MatRequisitionList(matRequisition)));
		return matRequisition;
	}
	
	public List<MatRequisition> findList(MatRequisition matRequisition) {
		return super.findList(matRequisition);
	}
	
	public Page<MatRequisition> findPage(Page<MatRequisition> page, MatRequisition matRequisition) {
		return super.findPage(page, matRequisition);
	}
	
	@Transactional(readOnly = false)
	public void doSubmit(MatRequisition matRequisition){
		matRequisition.preUpdate();
		matRequisition.setState("20");
		mapper.doSubmit(matRequisition);
		}
	@Transactional(readOnly = false)
	public void save(MatRequisition matRequisition) {
		matRequisition.setCreateDate(new Date());
		if (matRequisition.getIsNewRecord()){
			matRequisition.setState("10");
			matRequisition.preInsert();
			mapper.insert(matRequisition);
		}else{
			matRequisition.preUpdate();
			 mapper.update(matRequisition);
		}
		for (MatRequisitionList matRequisitionList : matRequisition.getMatRequisitionListList()){
			if (matRequisitionList.getId() == null){
				continue;
			}
			if (MatRequisitionList.DEL_FLAG_NORMAL.equals(matRequisitionList.getDelFlag())){
				if (StringUtils.isBlank(matRequisitionList.getId())){
					matRequisitionList.setMatRequisitionId(matRequisition.getId());
					matRequisitionList.preInsert();
					matRequisitionListMapper.insert(matRequisitionList);
				}else{
					matRequisitionList.preUpdate();
					matRequisitionListMapper.update(matRequisitionList);
				}
			}else{
				matRequisitionListMapper.delete(matRequisitionList);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(MatRequisition matRequisition) {
		super.delete(matRequisition);
		matRequisitionListMapper.delete(new MatRequisitionList(matRequisition));
	}

	/**
	 * @param code
	 * @return
	 */
	public MatRequisition getByCode(String code) {
		
		return mapper.getByCode(code);
	}

	public LinkedHashMap<String,Object> getMatCount(){
		int matApply = matRequisitionMapper.getOnMatApply();
		int partOut = matRequisitionMapper.getPartOut();
		//int allOut = matRequisitionMapper.getAllOut();
		LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
		RequistionOutDTO r = new RequistionOutDTO();
	    r.setStatus("待审批数,待出库数");
	    r.setNum(matApply + "," +partOut);
		map.put("data",r);
		return map;
	}
	
}