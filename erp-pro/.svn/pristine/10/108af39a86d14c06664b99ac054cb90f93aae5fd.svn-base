/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysmarket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.sys.sysmarket.dto.SysmarketStateDTO;
import com.pt.modules.sys.sysmarket.entity.SysMarket;
import com.pt.modules.sys.sysmarket.entity.SysMarketOfMap;

/**
 * 销售管理MAPPER接口
 * @author 郑利
 * @version 2018-08-23
 */
@MyBatisMapper
public interface SysMarketMapper extends BaseMapper<SysMarket> {

	/**
	 * @param sysMarketId
	 */
	void updateShow(String sysMarketId);

	/**
	 * @param contractNo
	 * @return
	 */
	String checkNo(String contractNo);
	
	/**
	 * 查询各个地区的销售订单的统计
	 * @return
	 */
	List<SysMarketOfMap> getOfMap();
	/**
	 * 根据年份统计
	 * @param year
	 * @return
	 */
	@Select("select count(1) from SYS_MARKET where TO_CHAR(CONTRACT_DATE,'yyyyy-MM-dd') like #{year}||'%'")
	int  getNumberByDate(String year);
	/**
	 * 统计已签合同
	 */
	int getNumberByContractStat();
	/**
	 * 统计未签合同
	 * @return
	 */
	int getNumberByUnContractStat();
	/**
	 * 统计未发货
	 * @return
	 */
	int getNumberUnShipments();
	/**
	 * 统计部分发货
	 * @return
	 */
	int getNumberInShipments();
	/**
	 * 全部发货
	 * @return
	 */
	int getNumberAllShipments();
	/**
	 * 按月统计销售订单
	 * @return
	 */
	@Select("select TO_CHAR(CONTRACT_DATE,'fmMM') month,count(1) num from SYS_MARKET WHERE TO_CHAR(CONTRACT_DATE,'yyyy') LIKE CONCAT(TO_CHAR(SYSDATE,'yyyy'),'%') GROUP BY TO_CHAR(CONTRACT_DATE,'fmMM')ORDER BY TO_CHAR(CONTRACT_DATE,'fmMM')")
	List<SysmarketStateDTO> getMonthNum();
	
}