/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.service.manytoone;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.service.TreeService;
import com.pt.common.utils.StringUtils;
import com.pt.modules.test.entity.manytoone.Category;
import com.pt.modules.test.mapper.manytoone.CategoryMapper;

/**
 * 商品类型Service
 * @author lf
 * @version 2017-06-11
 */
@Service
@Transactional(readOnly = true)
public class CategoryService extends TreeService<CategoryMapper, Category> {

	public Category get(String id) {
		return super.get(id);
	}
	
	public List<Category> findList(Category category) {
		if (StringUtils.isNotBlank(category.getParentIds())){
			category.setParentIds(","+category.getParentIds()+",");
		}
		return super.findList(category);
	}
	
	@Transactional(readOnly = false)
	public void save(Category category) {
		super.save(category);
	}
	
	@Transactional(readOnly = false)
	public void delete(Category category) {
		super.delete(category);
	}
	
}