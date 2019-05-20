/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.templan.mapper.templandeputy;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.templan.entity.templandeputy.TemplanDeputy;

/**
 * 临时计划副总审核MAPPER接口
 * @author cjx
 * @version 2018-09-08
 */
@MyBatisMapper
public interface TemplanDeputyMapper extends BaseMapper<TemplanDeputy> {
	int rejectState(TemplanDeputy templanDeputy);
	 int argeeState(TemplanDeputy templanDeputy);

}