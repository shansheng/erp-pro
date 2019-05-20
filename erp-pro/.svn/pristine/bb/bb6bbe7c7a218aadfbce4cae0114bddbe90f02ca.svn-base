package com.pt.modules.mds.mdsMissQuery.mapper;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsMissQuery.entity.MdsMissQuery;

@MyBatisMapper
public interface MdsMissQueryMapper extends BaseMapper<MdsMissQuery>{
	/**
	 * 缺件查询
	 * @param fromId
	 * @return
	 */
     List<MdsMissQuery> findByFromId(String fromId);
     /**
      * 批量缺件查询
      */
     List<MdsMissQuery> findPurchaseMiss(MdsMissQuery mdsMissQuery);
}
