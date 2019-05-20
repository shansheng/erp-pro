/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.templan.mapper.templandeputy;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.templan.entity.templandeputy.TemplanDeputyist;

/**
 * 临时订单物料详情表MAPPER接口
 * @author cjx
 * @version 2018-09-08
 */
@MyBatisMapper
public interface TemplanDeputyistMapper extends BaseMapper<TemplanDeputyist> {
	List<TemplanDeputyist> findTempMds(String temporaryPlanId);
}