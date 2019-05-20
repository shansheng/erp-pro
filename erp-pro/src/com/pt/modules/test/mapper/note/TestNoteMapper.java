/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.mapper.note;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.test.entity.note.TestNote;

/**
 * 富文本测试MAPPER接口
 * @author liugf
 * @version 2017-06-12
 */
@MyBatisMapper
public interface TestNoteMapper extends BaseMapper<TestNote> {
	
}