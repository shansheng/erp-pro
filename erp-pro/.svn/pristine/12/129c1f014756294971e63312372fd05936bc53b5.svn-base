/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.templan.templanprocurement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.common.utils.StringUtils;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.templan.templanprocurement.entity.TemplanProcurement;
import com.pt.modules.templan.templanprocurement.entity.TemplanProcurementList;
import com.pt.modules.templan.templanprocurement.mapper.TemplanProcurementListMapper;
import com.pt.modules.templan.templanprocurement.mapper.TemplanProcurementMapper;

/**
 * 采购查看Service
 * @author cjx
 * @version 2018-09-06
 */
@Service
@Transactional(readOnly = true)
public class TemplanProcurementService extends CrudService<TemplanProcurementMapper, TemplanProcurement> {

	@Autowired
	private TemplanProcurementListMapper templanProcurementListMapper;
	
	public TemplanProcurement get(String id) {
		TemplanProcurement templanProcurement = super.get(id);
		List<TemplanProcurementList> tempList = new ArrayList<>();
		List<TemplanProcurementList> list = templanProcurementListMapper.findList(new TemplanProcurementList(templanProcurement));
		if(list !=null){
			for(TemplanProcurementList temp: list){
				if(temp.getShipmentsStatus() == null || temp.getShipmentsStatus() == ""){
					temp.setShipmentsStatus("未发货");
				}
				tempList.add(temp);
			}
		}
		templanProcurement.setTemplanProcurementListList(tempList);
		if(Integer.parseInt(templanProcurement.getState()) > 20){
			templanProcurement.setDepartmentAssessingState("30");
		}else if(Integer.parseInt(templanProcurement.getState()) == 11){
			templanProcurement.setDepartmentAssessingState("11");
		}else{
			templanProcurement.setDepartmentAssessingState("20");
		}
		
		if(Integer.parseInt(templanProcurement.getState()) > 30){
			templanProcurement.setDeputyAssessingState("40");
		}else if(Integer.parseInt(templanProcurement.getState()) == 21){
			templanProcurement.setDeputyAssessingState("21");
		}else{
			templanProcurement.setDepartmentAssessingState("30");
		}
		return templanProcurement;
	}
	
	public List<TemplanProcurement> findList(TemplanProcurement templanProcurement) {
		return super.findList(templanProcurement);
	}
	
	public Page<TemplanProcurement> findPage(Page<TemplanProcurement> page, TemplanProcurement templanProcurement) {
		List<TemplanProcurement> tempList = mapper.findList(templanProcurement);
		List<TemplanProcurement> tempLists = new ArrayList<>();
		for(TemplanProcurement temp : tempList){
			List<TemplanProcurementList> list = templanProcurementListMapper.findList(new TemplanProcurementList(temp));
			temp.setTemplanProcurementListList(list);
			tempLists.add(temp);
		}
		templanProcurement.setPage(page);
		page.setList(tempLists);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(List<TemplanProcurementList> tempList ) {
		User user = null;
		String temporaryPlanId = null;
		//List<TemplanProcurementList> tempList = templanProcurementVo.getTemplanProcurementListList();
		for(TemplanProcurementList temp: tempList){
			temp.setShipmentsStatus("已发货");
			 user = UserUtils.getUser();
			if(StringUtils.isNotBlank(user.getId())){
				temp.setUpdateBy(user);
			}
			
			temp.setUpdateDate(new Date());
			templanProcurementListMapper.update(temp);
			temporaryPlanId = temp.getTemporaryPlanId();
		}
		 TemplanProcurement templanProcurement = new TemplanProcurement();
		 TemplanProcurementList  templanProcurementList = new TemplanProcurementList();
		 templanProcurementList.setTemporaryPlanId(temporaryPlanId);
		 System.out.println("temporaryPlanId:"+temporaryPlanId);
		List<TemplanProcurementList> list = templanProcurementListMapper.findList(templanProcurementList);
		int num = 0;
		for(TemplanProcurementList templan : list){
			System.out.println("temSatatus"+templan.getShipmentsStatus());
			if("已发货".equals(templan.getShipmentsStatus())){
				num++;
			}
		}
		System.out.println("num:"+num);
		System.out.println("list.size:"+list.size());
		if(list.size() > num && num >0) {
			templanProcurement.setState("45");//部分发货
		} else if(list.size() == num){
			templanProcurement.setState("50");
		}
		templanProcurement.setId(temporaryPlanId);
		templanProcurement.setUpdateBy(user);
		templanProcurement.setUpdateDate(new Date());
		mapper.update(templanProcurement);
	}
	
	@Transactional(readOnly = false)
	public void delete(TemplanProcurement templanProcurement) {
		super.delete(templanProcurement);
		templanProcurementListMapper.delete(new TemplanProcurementList(templanProcurement));
	}
	public List<TemplanProcurementList> findTempList(TemplanProcurementList templanProcurementList){
		return templanProcurementListMapper.findList(templanProcurementList);
	}
}