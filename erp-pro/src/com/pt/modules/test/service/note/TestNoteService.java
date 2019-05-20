/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.service.note;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.test.entity.note.TestNote;
import com.pt.modules.test.mapper.note.TestNoteMapper;

/**
 * 富文本测试Service
 * @author liugf
 * @version 2017-06-12
 */
@Service
@Transactional(readOnly = true)
public class TestNoteService extends CrudService<TestNoteMapper, TestNote> {

	public TestNote get(String id) {
		return super.get(id);
	}
	
	public List<TestNote> findList(TestNote testNote) {
		return super.findList(testNote);
	}
	
	public Page<TestNote> findPage(Page<TestNote> page, TestNote testNote) {
		return super.findPage(page, testNote);
	}
	
	@Transactional(readOnly = false)
	public void save(TestNote testNote) {
		super.save(testNote);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestNote testNote) {
		super.delete(testNote);
	}
	
}