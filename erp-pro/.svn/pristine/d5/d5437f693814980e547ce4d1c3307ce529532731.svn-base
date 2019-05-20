/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprojectpcmplan.mapper;

import java.util.List;
import java.util.Map;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsprojectpcmplan.entity.MdsProjectQuotaList;

/**
 * 材料定额明细表MAPPER接口
 * @author cjx
 * @version 2018-09-11
 */
@MyBatisMapper
public interface MdsProjectQuotaListMapper extends BaseMapper<MdsProjectQuotaList> {
	
	/**
	 * 通过value查找分类字典的值
	 */
	String findClassify(String id);
	
	/**
	 * 查找订单类型为临时的产品
	 */
	public List<MdsProjectQuotaList> findTemporaryList(MdsProjectQuotaList mdsProjectQuotaList);

	
}