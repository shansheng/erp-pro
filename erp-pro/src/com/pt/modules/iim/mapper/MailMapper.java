/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.iim.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.iim.entity.Mail;

/**
 * 发件箱MAPPER接口
 * @author pt
 * @version 2015-11-15
 */
@MyBatisMapper
public interface MailMapper extends BaseMapper<Mail> {
	public int getCount(MailMapper entity);
}