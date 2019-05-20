/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.mesworkshift.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mes.mesworkshift.entity.MesWorkshift;
import com.pt.modules.mes.mesworkshift.entity.MesWorkshiftUser;

/**
 * 班组管理MAPPER接口
 * @author 孙会楠
 * @version 2018-05-05
 */
@MyBatisMapper
public interface MesWorkshiftMapper extends BaseMapper<MesWorkshift> {
	
}