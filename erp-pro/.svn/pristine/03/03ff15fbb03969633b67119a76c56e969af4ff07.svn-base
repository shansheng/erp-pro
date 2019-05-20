/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmainstoragevs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainRecord;
import com.pt.modules.wms.wmsmainstoragevs.dto.WmsoutInRecordDTO;

/**
 * 库房流水记录MAPPER接口
 * @author cjx
 * @version 2018-10-18
 */
@MyBatisMapper
public interface WmsMainRecordVsMapper extends BaseMapper<WmsMainRecord> {
	/**
	 * 统计出库记录
	 * @return
	 */
	@Select("SELECT TO_CHAR(CREATE_DATE,'fmMM') month,count(1) outNum FROM WMS_MAIN_RECORD WHERE TO_CHAR(CREATE_DATE,'fmyyyy') = TO_CHAR(SYSDATE,'fmyyyy') AND STATE = '0' GROUP BY TO_CHAR(CREATE_DATE,'fmMM')")
	public List<WmsoutInRecordDTO> getOutNum();
	/**
	 * 统计入库记录
	 * @return
	 */
	@Select("SELECT TO_CHAR(CREATE_DATE,'fmMM') month,count(1) inNum FROM WMS_MAIN_RECORD WHERE TO_CHAR(CREATE_DATE,'fmyyyy') = TO_CHAR(SYSDATE,'fmyyyy') AND STATE = '1' GROUP BY TO_CHAR(CREATE_DATE,'fmMM')")
	public List<WmsoutInRecordDTO> getInNum();
}