/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.mapper.one;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.test.entity.one.LeaveDialog;

/**
 * 请假表单MAPPER接口
 * @author lgf
 * @version 2017-06-11
 */
@MyBatisMapper
public interface LeaveDialogMapper extends BaseMapper<LeaveDialog> {
	
}