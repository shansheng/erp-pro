/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.oa.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.oa.entity.OaNotify;

/**
 * 通知通告MAPPER接口
 * @author pt
 * @version 2017-05-16
 */
@MyBatisMapper
public interface OaNotifyMapper extends BaseMapper<OaNotify> {
	
	/**
	 * 获取通知数目
	 * @param oaNotify
	 * @return
	 */
	public Long findCount(OaNotify oaNotify);
	
}