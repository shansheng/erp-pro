/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvoutbill.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.wms.wmsinvoutbill.entity.WmsInvOutBill;

/**
 * 出库管理MAPPER接口
 * @author 龚志强
 * @version 2018-05-21
 */
@MyBatisMapper
public interface WmsInvOutBillMapper extends BaseMapper<WmsInvOutBill> {
	//出库单出库
	public void doOutInvSubmit(WmsInvOutBill wmsInvOutBill);
	
	//查询出库单中是否所有物料的出库数量均<=可用库存数
	/**
	 * 通过出库单获取短缺数最大的值，短缺为正数
	 * @param wmsInvOutBillId
	 * @return
	 */
	public Double shortageNum(WmsInvOutBill wmsInvOutBill);
}