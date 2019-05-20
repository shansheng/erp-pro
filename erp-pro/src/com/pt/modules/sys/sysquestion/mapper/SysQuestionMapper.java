/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysquestion.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.sys.sysquestion.dto.SysQuestionDTO;
import com.pt.modules.sys.sysquestion.entity.SysQuestion;

/**
 * 问题管理MAPPER接口
 * @author 郑利
 * @version 2018-07-13
 */
@MyBatisMapper
public interface SysQuestionMapper extends BaseMapper<SysQuestion> {

	/**
	 * @param classify
	 * @return
	 */
	List selectSub(String classify);

	/**
	 * @param sysQuestion
	 * @return
	 */
	void updateById(SysQuestion sysQuestion);

	/**
	 * @param sysQuestion
	 */
	void updateStatus(SysQuestion sysQuestion);
	/**
	 * 统计问题分类情况
	 */
	@Select("select LABEL questionType,count(1) num from SYS_DICT_VALUE v join SYS_DICT_TYPE t on v.dict_type_id = t.id join SYS_QUESTION q on q.CLASSIFY = v.value where t.TYPE = 'classify' GROUP BY LABEL")
	List<SysQuestionDTO> getQuestionType(); 
	
	
}