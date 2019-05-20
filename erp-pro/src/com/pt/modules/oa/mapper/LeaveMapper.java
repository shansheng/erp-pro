/**
 * There are <a href="http://www.pt.org/">pt</a> code generation
 */
package com.pt.modules.oa.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.oa.entity.Leave;

/**
 * 请假MAPPER接口
 * @author liuj
 * @version 2016-8-23
 */
@MyBatisMapper
public interface LeaveMapper extends BaseMapper<Leave> {
	
	/**
	 * 更新流程实例ID
	 * @param leave
	 * @return
	 */
	public int updateProcessInstanceId(Leave leave);
	
	/**
	 * 更新实际开始结束时间
	 * @param leave
	 * @return
	 */
	public int updateRealityTime(Leave leave);
	
}
