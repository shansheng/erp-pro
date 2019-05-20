/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprojectpcmplan.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsprojectpcmplan.entity.MdsProjectpcmplan;

/**
 * SysMarKetMAPPER接口
 * @author cjx
 * @version 2018-09-19
 */
@MyBatisMapper
public interface MdsProjectpcmplanMapper extends BaseMapper<MdsProjectpcmplan> {
	
	//获取订单类型为项目的产品对象
	public MdsProjectpcmplan getQuota(MdsProjectpcmplan mdsProjectpcmplan);
	
	//获取订单类型为临时的产品对象
	public MdsProjectpcmplan getTemporary(MdsProjectpcmplan mdsProjectpcmplan);
}