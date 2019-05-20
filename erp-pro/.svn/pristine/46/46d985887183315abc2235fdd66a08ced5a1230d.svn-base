/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.service.grid;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.test.entity.grid.TestContinent;
import com.pt.modules.test.mapper.grid.TestContinentMapper;

/**
 * 洲Service
 * @author lgf
 * @version 2017-06-12
 */
@Service
@Transactional(readOnly = true)
public class TestContinentService extends CrudService<TestContinentMapper, TestContinent> {

	public TestContinent get(String id) {
		return super.get(id);
	}
	
	public List<TestContinent> findList(TestContinent testContinent) {
		return super.findList(testContinent);
	}
	
	public Page<TestContinent> findPage(Page<TestContinent> page, TestContinent testContinent) {
		return super.findPage(page, testContinent);
	}
	
	@Transactional(readOnly = false)
	public void save(TestContinent testContinent) {
		super.save(testContinent);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestContinent testContinent) {
		super.delete(testContinent);
	}
	
}