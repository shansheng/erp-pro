/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.templan.templandepartment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.sys.entity.Office;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.mapper.UserMapper;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.templan.templandepartment.entity.TemplanDepartment;
import com.pt.modules.templan.templandepartment.entity.TemplanDepartmentList;
import com.pt.modules.templan.templandepartment.mapper.TemplanDepartmentListMapper;
import com.pt.modules.templan.templandepartment.mapper.TemplanDepartmentMapper;

/**
 * 临时计划部门审核Service
 * @author cjx
 * @version 2018-09-05
 */
@Service
@Transactional(readOnly = true)
public class TemplanDepartmentService extends CrudService<TemplanDepartmentMapper, TemplanDepartment> {

	@Autowired
	private TemplanDepartmentListMapper templanDepartmentListMapper;
	@Autowired
	private UserMapper userMapper;
	
	public TemplanDepartment get(String id) {
		TemplanDepartment templanDepartment = super.get(id);
		templanDepartment.setTemplanDepartmentListList(templanDepartmentListMapper.findList(new TemplanDepartmentList(templanDepartment)));
		for (TemplanDepartmentList templanDepartmentList : templanDepartment.getTemplanDepartmentListList()) {
			if(0==templanDepartmentList.getApplyovalNumber()){
				templanDepartmentList.setApplyovalNumber(templanDepartmentList.getApplyNumber());
			}
		}
		return templanDepartment;
	}
	
	public List<TemplanDepartment> findList(TemplanDepartment templanDepartment) {
		User user = UserUtils.getUser();
		user = userMapper.get(user.getId());
		User nowUser = new User();
		nowUser.setOffice(user.getOffice());
		List<User> userList = userMapper.findList(nowUser);
		List<TemplanDepartment> list = new ArrayList<>();
		dataRuleFilter(templanDepartment);
		for (User user1 : userList) {
			templanDepartment.getCreateBy().setId(user1.getId());
			List<TemplanDepartment> user1List = mapper.findList(templanDepartment);
			list.addAll(user1List);
		}
		return list;
	}
	
	public Page<TemplanDepartment> findPage(Page<TemplanDepartment> page, TemplanDepartment templanDepartment) {
		User user = UserUtils.getUser();
		user = userMapper.get(user.getId());
		User nowUser = new User();
		nowUser.setOffice(user.getOffice());
		List<User> userList = userMapper.findList(nowUser);
		List<TemplanDepartment> list = new ArrayList<>();
		dataRuleFilter(templanDepartment);
		templanDepartment.setPage(page);
		for (User user1 : userList) {
			templanDepartment.getCreateBy().setId(user1.getId());
			List<TemplanDepartment> user1List = mapper.findList(templanDepartment);
			list.addAll(user1List);
		}
		page.setList(list);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(TemplanDepartment templanDepartment) {
		templanDepartment.setState("30");
		templanDepartment.preUpdate();
		User user = UserUtils.getUser();
		templanDepartment.setDepartmentAssessing(user.getId());
		templanDepartment.setDepartmentAssessingDate(new Date());
		mapper.update(templanDepartment);
		for (TemplanDepartmentList templanDepartmentList : templanDepartment.getTemplanDepartmentListList()){
			if (templanDepartmentList.getId() == null){
				continue;
			}
			templanDepartmentList.preUpdate();
			templanDepartmentList.setApplyovalNumber(templanDepartmentList.getApplyNumber());
			templanDepartmentListMapper.update(templanDepartmentList);
		}
	}
	
	@Transactional(readOnly = false)
	public void reject(TemplanDepartment templanDepartment) {
		templanDepartment.setState("11");
		templanDepartment.preUpdate();
		User user = UserUtils.getUser();
		templanDepartment.setDepartmentAssessing(user.getId());
		templanDepartment.setDepartmentAssessingDate(new Date());
		mapper.update(templanDepartment);
	}
	
	@Transactional(readOnly = false)
	public void saveTable(TemplanDepartment templanDepartment){
		if("30".equals(templanDepartment.getStatying())){
			templanDepartment.setState("30");
			templanDepartment.preUpdate();
			User user = UserUtils.getUser();
			templanDepartment.setDepartmentAssessing(user.getId());
			templanDepartment.setDepartmentAssessingDate(new Date());
			mapper.update(templanDepartment);
			for (TemplanDepartmentList templanDepartmentList : templanDepartment.getTemplanDepartmentListList()){
				if (templanDepartmentList.getId() == null){
					continue;
				}
				templanDepartmentList.preUpdate();
				templanDepartmentListMapper.update(templanDepartmentList);
			}
		}else if("11".equals(templanDepartment.getStatying())){
			this.reject(templanDepartment);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TemplanDepartment templanDepartment) {
		super.delete(templanDepartment);
		templanDepartmentListMapper.delete(new TemplanDepartmentList(templanDepartment));
	}
	
}