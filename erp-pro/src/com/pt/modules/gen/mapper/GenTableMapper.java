package com.pt.modules.gen.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.gen.entity.GenTable;
import org.apache.ibatis.annotations.Param;

@MyBatisMapper
public abstract interface GenTableMapper extends BaseMapper<GenTable> {
	public abstract int buildTable(@Param("sql") String paramString);
}