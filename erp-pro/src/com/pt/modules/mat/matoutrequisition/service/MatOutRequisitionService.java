/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matoutrequisition.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.common.utils.IdGen;
import com.pt.common.utils.StringUtils;
import com.pt.modules.mat.matrequisition.entity.MatRequisition;


import com.pt.modules.mat.matrequisition.entity.MatRequisitionList;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.templan.templanprocurement.entity.TemplanProcurement;
import com.pt.modules.templan.templanprocurement.entity.TemplanProcurementList;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainRecord;
import com.pt.modules.wms.wmsmaininventory.mapper.WmsMainRecordMapper;
import com.pt.modules.mat.matoutrequisition.mapper.MatOutRequisitionListMapper;
import com.pt.modules.mat.matoutrequisition.mapper.MatOutRequisitionMapper;


/**
 * 领料单流程Service
 * @author 郑利
 * @version 2018-09-18
 */
@Service
@Transactional(readOnly = true)
public class MatOutRequisitionService extends CrudService<MatOutRequisitionMapper, MatRequisition> {

	@Autowired
	private MatOutRequisitionListMapper matRequisitionListMapper;
	
	@Autowired
	private WmsMainRecordMapper wmsMainRecordMapper;
	
	
	public MatRequisition get(String id) {
		MatRequisition matRequisition = super.get(id);
		List<MatRequisitionList> matList = new ArrayList<>();
		List<MatRequisitionList> list = matRequisitionListMapper.findList(new MatRequisitionList(matRequisition));
		if(list !=null){
			for(MatRequisitionList mat: list){
				if(mat.getOutputStatus() == null || mat.getOutputStatus() == ""){
					mat.setOutputStatus("未出库");
				}
				matList.add(mat);
			}
		}
		matRequisition.setMatRequisitionListList(matList);
		return matRequisition;
	}
	@Transactional(readOnly = false)
	public void save(List<MatRequisitionList> matList ) {
		User user = null;
		String matRequisitionId = null;
		//List<TemplanProcurementList> tempList = templanProcurementVo.getTemplanProcurementListList();
		for(MatRequisitionList mat: matList){
			mat.setOutputStatus("已出库");
			 user = UserUtils.getUser();
			if(StringUtils.isNotBlank(user.getId())){
				mat.setUpdateBy(user);
			}
			
			mat.setUpdateDate(new Date());
			matRequisitionListMapper.update(mat);
			System.out.println("mat.getMatRequisitionId():"+mat.getMatRequisitionId());
			matRequisitionId = mat.getMatRequisitionId();
		}
		 MatRequisition matRequisition = new MatRequisition();
		 MatRequisitionList matRequisitionList = new MatRequisitionList();
		 matRequisitionList.setMatRequisitionId(matRequisitionId);
		List<MatRequisitionList> list = matRequisitionListMapper.findList(matRequisitionList);
		int num = 0;
		for(MatRequisitionList mat : list){
			if("已出库".equals(mat.getOutputStatus())){
				num++;
			}
		}
		if(list.size() > num && num >0) {
			matRequisition.setState("45");
		} else if(list.size() == num){
			matRequisition.setState("50");
		}
		System.out.println("matRequisitionId:"+matRequisitionId);
		System.out.println("345:"+matRequisition.getState());
		matRequisition.setId(matRequisitionId);
		matRequisition.setUpdateBy(user);
		matRequisition.setUpdateDate(new Date());
		mapper.updateState(matRequisition);
	}
	public List<MatRequisition> findList(MatRequisition matRequisition) {
		return super.findList(matRequisition);
	}
	
	public Page<MatRequisition> findPage(Page<MatRequisition> page, MatRequisition matRequisition) {
		return super.findPage(page, matRequisition);
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
	 * @param enti
	 */
	public void updateState(MatRequisition matRequisition) {
		WmsMainRecord entity=new WmsMainRecord();
		entity.setItemId(matRequisition.getItemId());
		String matRequisitionId=matRequisition.getId();
		MatRequisitionList MatRequisitionList=matRequisitionListMapper.getByMatRequisitionId(matRequisitionId);
		entity.setQty(MatRequisitionList.getApplyovalNumber());
		entity.setUpdateBy(UserUtils.getUser());
		entity.setUpdateDate(new Date());
		entity.setCreateBy(UserUtils.getUser());
		entity.setCreateDate(new Date());
		entity.setState("0");
		entity.setItemId(MatRequisitionList.getMdsItemId());
		entity.setId(IdGen.uuid());
		wmsMainRecordMapper.insert(entity);
		mapper.updateState(matRequisition);
		
	}
	
	public List<MatRequisitionList> findMatList(MatRequisitionList matRequisitionList){
		return matRequisitionListMapper.findList(matRequisitionList);
	}

}