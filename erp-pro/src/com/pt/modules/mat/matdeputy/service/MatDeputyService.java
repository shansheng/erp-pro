/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matdeputy.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.common.utils.StringUtils;
import com.pt.modules.mat.matdeputy.mapper.MatDeputyListMapper;
import com.pt.modules.mat.matdeputy.mapper.MatDeputyMapper;
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
public class MatDeputyService extends CrudService<MatDeputyMapper, MatRequisition> {

	@Autowired
	private MatDeputyListMapper matDeputyListMapper;
	@Autowired
	private MatDeputyMapper deputyMapper;
	
	public MatRequisition get(String id) {
		MatRequisition matRequisition = super.get(id);
		matRequisition.setMatRequisitionListList(matDeputyListMapper.findList(new MatRequisitionList(matRequisition)));
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
		matRequisition.setState(matRequisition.getStatying());
		User user = UserUtils.getUser();
		matRequisition.setDeputyAssessing(user.getId());
		matRequisition.setDeputyAssessingDate(new Date());
		matRequisition.preUpdate();
		mapper.update(matRequisition);
		for (MatRequisitionList matRequisitionList : matRequisition.getMatRequisitionListList()){
			if (matRequisitionList.getId() == null){
				continue;
			}
			if (MatRequisitionList.DEL_FLAG_NORMAL.equals(matRequisitionList.getDelFlag())){
				if (StringUtils.isBlank(matRequisitionList.getId())){
					matRequisitionList.setMatRequisitionId(matRequisition.getId());
					matRequisitionList.preInsert();
					matDeputyListMapper.insert(matRequisitionList);
				}else{
					matRequisitionList.preUpdate();
					matDeputyListMapper.update(matRequisitionList);
				}
			}else{
				matDeputyListMapper.delete(matRequisitionList);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(MatRequisition matRequisition) {
		super.delete(matRequisition);
		matDeputyListMapper.delete(new MatRequisitionList(matRequisition));
	}
	public int rejectState(String id){
		MatRequisition matRequisition = new MatRequisition();
		User user = UserUtils.getUser();
		matRequisition.setId(id);
		matRequisition.setState("21");
		matRequisition.setDeputyAssessing(user.getId());
		matRequisition.setDeputyAssessingDate(new Date());
		return deputyMapper.rejectState(matRequisition);
	}
	public int argeeState(String id){
		MatRequisition matRequisition = new MatRequisition();
		User user = UserUtils.getUser();
		matRequisition.setId(id);
		matRequisition.setState("40");
		matRequisition.setDeputyAssessing(user.getId());
		matRequisition.setDeputyAssessingDate(new Date());
		return deputyMapper.argeeState(matRequisition);
	}
	
}