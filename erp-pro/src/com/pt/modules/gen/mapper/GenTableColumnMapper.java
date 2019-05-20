package com.pt.modules.gen.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.gen.entity.GenTable;
import com.pt.modules.gen.entity.GenTableColumn;

import java.util.List;

@MyBatisMapper
public abstract interface GenTableColumnMapper extends BaseMapper<GenTableColumn> {
	public abstract void deleteByGenTable(GenTable paramGenTable);

	public abstract void deleteByGenTableByLogic(GenTable paramGenTable);

	public abstract List<GenTableColumn> findListAllStatus(GenTable paramGenTable);
}