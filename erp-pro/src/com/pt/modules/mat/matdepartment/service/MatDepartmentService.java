/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matdepartment.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.common.utils.StringUtils;
import com.pt.modules.mat.matdepartment.mapper.MatDepartmentListMapper;
import com.pt.modules.mat.matdepartment.mapper.MatDepartmentMapper;
import com.pt.modules.mat.matrequisition.entity.MatRequisition;
import com.pt.modules.mat.matrequisition.entity.MatRequisitionList;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.utils.UserUtils;


/**
 * 领料单流程Service
 * @author 郑利
 * @version 2018-09-18
 */
@Service
@Transactional(readOnly = true)
public class MatDepartmentService extends CrudService<MatDepartmentMapper, MatRequisition> {

	@Autowired
	private MatDepartmentListMapper matDepartmentListMapper;
	
	public MatRequisition get(String id) {
		MatRequisition matRequisition = super.get(id);
		matRequisition.setMatRequisitionListList(matDepartmentListMapper.findList(new MatRequisitionList(matRequisition)));
		for (MatRequisitionList matRequisitionList  : matRequisition.getMatRequisitionListList()) {
			if(0==matRequisitionList.getApplyovalNumber()){
				matRequisitionList.setApplyovalNumber(matRequisitionList.getApplyNumber());
			}
		}
		return matRequisition;
	}
	
	public List<MatRequisition> findList(MatRequisition matRequisition) {
		return super.findList(matRequisition);
	}
	
	public Page<MatRequisition> findPage(Page<MatRequisition> page, MatRequisition matRequisition) {
		return super.findPage(page, matRequisition);
	}
	
	@Transactional(readOnly = false)
	public void save(MatRequisition matRequisition) {
		matRequisition.setState("30");
		matRequisition.preUpdate();
		User user = UserUtils.getUser();
		matRequisition.setDepartmentassessing(user.getId());
		matRequisition.setDepartmentAssessingDate(new Date());
		mapper.update(matRequisition);
		for (MatRequisitionList matRequisitionList : matRequisition.getMatRequisitionListList()){
			if (matRequisitionList.getId() == null){
				continue;
			}
			if (MatRequisitionList.DEL_FLAG_NORMAL.equals(matRequisitionList.getDelFlag())){
				if (StringUtils.isBlank(matRequisitionList.getId())){
					matRequisitionList.setMatRequisitionId(matRequisition.getId());
					matRequisitionList.preInsert();
					matDepartmentListMapper.insert(matRequisitionList);
				}else{
					matRequisitionList.preUpdate();
					matDepartmentListMapper.update(matRequisitionList);
				}
			}else{
				matDepartmentListMapper.delete(matRequisitionList);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(MatRequisition matRequisition) {
		super.delete(matRequisition);
		matDepartmentListMapper.delete(new MatRequisitionList(matRequisition));
	}
	@Transactional(readOnly = false)
	public void reject(MatRequisition matRequisition) {
		matRequisition.setState("11");
		matRequisition.preUpdate();
		User user = UserUtils.getUser();
		matRequisition.setDepartmentassessing(user.getId());
		matRequisition.setDepartmentAssessingDate(new Date());
		mapper.update(matRequisition);
	}
	
	@Transactional(readOnly = false)
	public void saveTable(MatRequisition matRequisition){
		if("30".equals(matRequisition.getStatying())){
			matRequisition.setState("30");
			matRequisition.preUpdate();
			User user = UserUtils.getUser();
			matRequisition.setDepartmentassessing(user.getId());
			matRequisition.setDepartmentAssessingDate(new Date());
			mapper.update(matRequisition);
			for (MatRequisitionList matRequisitionList : matRequisition.getMatRequisitionListList()){
				if (matRequisitionList.getId() == null){
					continue;
				}
				matRequisitionList.preUpdate();
				matDepartmentListMapper.update(matRequisitionList);
			}
		}else if("11".equals(matRequisition.getStatying())){
			this.reject(matRequisition);
		}
	}
	
}