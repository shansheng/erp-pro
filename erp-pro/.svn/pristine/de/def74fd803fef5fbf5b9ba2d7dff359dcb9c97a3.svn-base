package com.pt.modules.gen.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.gen.entity.GenTable;
import com.pt.modules.gen.entity.GenTableColumn;

import java.util.List;

@MyBatisMapper
public abstract interface GenDataBaseDictMapper extends BaseMapper<GenTableColumn> {
	public abstract List<GenTable> findTableList(GenTable paramGenTable);

	public abstract List<GenTable> findTableListByName(GenTable paramGenTable);

	public abstract List<GenTableColumn> findTableColumnList(GenTable paramGenTable);

	public abstract List<String> findTablePK(GenTable paramGenTable);
}