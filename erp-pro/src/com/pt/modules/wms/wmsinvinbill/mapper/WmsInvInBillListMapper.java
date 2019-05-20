/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvinbill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.wms.wmsinvinbill.entity.WmsInvInBill;
import com.pt.modules.wms.wmsinvinbill.entity.WmsInvInBillList;

/**
 * 入库申请单子表MAPPER接口
 * @author 龚志强
 * @version 2018-05-15
 */
@MyBatisMapper
public interface WmsInvInBillListMapper extends BaseMapper<WmsInvInBillList> {
	//入库单明细入库
	public void inBill(WmsInvInBillList wmsInvInBillList);
	
	//查询通过属性和值 查询列表
	public List<WmsInvInBillList> findByProperty(@Param(value="propertyName")String propertyName, @Param(value="value")Object value);
}