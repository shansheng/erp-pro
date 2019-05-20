/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.service.grid;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.test.entity.grid.TestCountry;
import com.pt.modules.test.mapper.grid.TestCountryMapper;

/**
 * 国家Service
 * @author lgf
 * @version 2017-06-12
 */
@Service
@Transactional(readOnly = true)
public class TestCountryService extends CrudService<TestCountryMapper, TestCountry> {

	public TestCountry get(String id) {
		return super.get(id);
	}
	
	public List<TestCountry> findList(TestCountry testCountry) {
		return super.findList(testCountry);
	}
	
	public Page<TestCountry> findPage(Page<TestCountry> page, TestCountry testCountry) {
		return super.findPage(page, testCountry);
	}
	
	@Transactional(readOnly = false)
	public void save(TestCountry testCountry) {
		super.save(testCountry);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestCountry testCountry) {
		super.delete(testCountry);
	}
	
}