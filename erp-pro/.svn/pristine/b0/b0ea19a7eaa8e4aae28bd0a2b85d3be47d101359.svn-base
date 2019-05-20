/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysquestion.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.sys.sysquestion.dto.SysQuestionDTO;
import com.pt.modules.sys.sysquestion.entity.SysQuestion;
import com.pt.modules.sys.sysquestion.mapper.SysQuestionMapper;

/**
 * 问题管理Service
 * @author 郑利
 * @version 2018-07-13
 */
@Service
@Transactional(readOnly = true)
public class SysQuestionService extends CrudService<SysQuestionMapper, SysQuestion> {
@Autowired
private SysQuestionMapper sysQuestionMapper;
	public SysQuestion get(String id) {
		return super.get(id);
	}
	
	public List<SysQuestion> findList(SysQuestion sysQuestion) {
		return super.findList(sysQuestion);
	}
	
	public Page<SysQuestion> findPage(Page<SysQuestion> page, SysQuestion sysQuestion) {
		return super.findPage(page, sysQuestion);
	}
	
	@Transactional(readOnly = false)
	public void save(SysQuestion sysQuestion) {
		super.save(sysQuestion);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysQuestion sysQuestion) {
		super.delete(sysQuestion);
	}

	/**
	 * @param classify
	 * @return
	 */
	@Transactional(readOnly = false)
	public List selectSub(String classify) {
		
		return sysQuestionMapper.selectSub(classify);
	}

	/**
	 * @param sysQuestion
	 * @return 
	 */
	@Transactional(readOnly = false)
	public void updateById(SysQuestion sysQuestion) {
		 sysQuestionMapper.updateById(sysQuestion);
		
	}

	/**
	 * @param sysQuestion
	 */
	@Transactional(readOnly = false)
	public void updateStatus(SysQuestion sysQuestion) {
		sysQuestionMapper.updateStatus(sysQuestion);
		
	}
	public LinkedHashMap<String,Object> getQuestion(){
		List<SysQuestionDTO> list = sysQuestionMapper.getQuestionType();
		StringBuilder builder = new StringBuilder();
		StringBuilder strBuilder = new StringBuilder();
		LinkedHashMap<String,Object> map = null;
		if(list != null) {
			for (SysQuestionDTO s : list) {
				builder.append(s.getQuestionType() + ",");
				strBuilder.append(s.getNum() + ",");
			}
			if (builder.length() > 0 && strBuilder.length() > 0) {
				builder.deleteCharAt(builder.length()-1);
				strBuilder.deleteCharAt(strBuilder.length()-1);
			}
			SysQuestionDTO sq = new SysQuestionDTO();
			sq.setQuestionType(builder.toString());
			sq.setNum(strBuilder.toString());
			map = new LinkedHashMap<String,Object>();
			map.put("data", sq);
		}
		
		return map;
	}
	
}