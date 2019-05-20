/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.service.tree;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.service.TreeService;
import com.pt.common.utils.StringUtils;
import com.pt.modules.test.entity.tree.TestTreeDialog;
import com.pt.modules.test.mapper.tree.TestTreeDialogMapper;

/**
 * 组织机构Service
 * @author liugf
 * @version 2017-06-19
 */
@Service
@Transactional(readOnly = true)
public class TestTreeDialogService extends TreeService<TestTreeDialogMapper, TestTreeDialog> {

	public TestTreeDialog get(String id) {
		return super.get(id);
	}
	
	public List<TestTreeDialog> findList(TestTreeDialog testTreeDialog) {
		if (StringUtils.isNotBlank(testTreeDialog.getParentIds())){
			testTreeDialog.setParentIds(","+testTreeDialog.getParentIds()+",");
		}
		return super.findList(testTreeDialog);
	}
	
	@Transactional(readOnly = false)
	public void save(TestTreeDialog testTreeDialog) {
		super.save(testTreeDialog);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestTreeDialog testTreeDialog) {
		super.delete(testTreeDialog);
	}
	
}