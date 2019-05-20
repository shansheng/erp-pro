/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.service.tree;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.service.TreeService;
import com.pt.common.utils.StringUtils;
import com.pt.modules.test.entity.tree.TestTreeForm;
import com.pt.modules.test.mapper.tree.TestTreeFormMapper;

/**
 * 组织机构Service
 * @author liugf
 * @version 2017-06-11
 */
@Service
@Transactional(readOnly = true)
public class TestTreeFormService extends TreeService<TestTreeFormMapper, TestTreeForm> {

	public TestTreeForm get(String id) {
		return super.get(id);
	}
	
	public List<TestTreeForm> findList(TestTreeForm testTreeForm) {
		if (StringUtils.isNotBlank(testTreeForm.getParentIds())){
			testTreeForm.setParentIds(","+testTreeForm.getParentIds()+",");
		}
		return super.findList(testTreeForm);
	}
	
	@Transactional(readOnly = false)
	public void save(TestTreeForm testTreeForm) {
		super.save(testTreeForm);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestTreeForm testTreeForm) {
		super.delete(testTreeForm);
	}
	
}