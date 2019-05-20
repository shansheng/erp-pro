/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.service.pic;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.test.entity.pic.TestPic;
import com.pt.modules.test.mapper.pic.TestPicMapper;

/**
 * 图片管理Service
 * @author lgf
 * @version 2017-06-19
 */
@Service
@Transactional(readOnly = true)
public class TestPicService extends CrudService<TestPicMapper, TestPic> {

	public TestPic get(String id) {
		return super.get(id);
	}
	
	public List<TestPic> findList(TestPic testPic) {
		return super.findList(testPic);
	}
	
	public Page<TestPic> findPage(Page<TestPic> page, TestPic testPic) {
		return super.findPage(page, testPic);
	}
	
	@Transactional(readOnly = false)
	public void save(TestPic testPic) {
		super.save(testPic);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestPic testPic) {
		super.delete(testPic);
	}
	
}