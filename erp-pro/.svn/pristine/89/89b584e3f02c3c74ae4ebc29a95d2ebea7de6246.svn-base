/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.service.treetable;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.service.TreeService;
import com.pt.common.utils.StringUtils;
import com.pt.modules.test.entity.treetable.CarKind;
import com.pt.modules.test.mapper.treetable.CarKindMapper;

/**
 * 车系Service
 * @author lgf
 * @version 2017-06-12
 */
@Service
@Transactional(readOnly = true)
public class CarKindService extends TreeService<CarKindMapper, CarKind> {

	public CarKind get(String id) {
		return super.get(id);
	}
	
	public List<CarKind> findList(CarKind carKind) {
		if (StringUtils.isNotBlank(carKind.getParentIds())){
			carKind.setParentIds(","+carKind.getParentIds()+",");
		}
		return super.findList(carKind);
	}
	
	@Transactional(readOnly = false)
	public void save(CarKind carKind) {
		super.save(carKind);
	}
	
	@Transactional(readOnly = false)
	public void delete(CarKind carKind) {
		super.delete(carKind);
	}
	
}