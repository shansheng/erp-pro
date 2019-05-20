/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.mapper.manytomany;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.test.entity.manytomany.Student;

/**
 * 学生MAPPER接口
 * @author lgf
 * @version 2017-06-12
 */
@MyBatisMapper
public interface StudentMapper extends BaseMapper<Student> {
	
}