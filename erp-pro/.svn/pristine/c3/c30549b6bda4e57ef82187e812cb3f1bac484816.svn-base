/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.service.treetable;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.test.entity.treetable.Car;
import com.pt.modules.test.mapper.treetable.CarMapper;

/**
 * 车辆Service
 * @author lgf
 * @version 2017-06-12
 */
@Service
@Transactional(readOnly = true)
public class CarService extends CrudService<CarMapper, Car> {

	public Car get(String id) {
		return super.get(id);
	}
	
	public List<Car> findList(Car car) {
		return super.findList(car);
	}
	
	public Page<Car> findPage(Page<Car> page, Car car) {
		return super.findPage(page, car);
	}
	
	@Transactional(readOnly = false)
	public void save(Car car) {
		super.save(car);
	}
	
	@Transactional(readOnly = false)
	public void delete(Car car) {
		super.delete(car);
	}
	
}