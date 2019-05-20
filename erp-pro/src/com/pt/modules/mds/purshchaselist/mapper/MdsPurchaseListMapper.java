/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.purshchaselist.mapper;

import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.purshchaselist.entiry.MdsPurchaseList;


/**
 * 批量采购计划MAPPER接口
 * @author zjj
 * @version 2018-09-19
 */
@MyBatisMapper
public interface MdsPurchaseListMapper extends BaseMapper<MdsPurchaseList> {
	
	  List<MdsPurchaseList> findAll(MdsPurchaseList mdsPurchaseList);
	  
	  MdsPurchaseList check(MdsPurchaseList mdsPurchaseList);
	  
	  List<MdsPurchaseList> findPuraseDeputy(MdsPurchaseList mdsPurchaseList);
	  
	  List<MdsPurchaseList> findPurasev(MdsPurchaseList mdsPurchaseList);
	  
	  void addPurchase(String[] ids);
	  
	  List<MdsPurchaseList> findPuraseTem(MdsPurchaseList mdsPurchaseList);
	  
	  String findItemId(String itemCode); 
	  
	  List<MdsPurchaseList> findByMdsItemId(MdsPurchaseList mdsPurchaseList);
	  /**
	   * 根据物料Id查询
	   * @param mdsItemId
	   * @return
	   */
	  List<MdsPurchaseList> getByMdsItemId(String mdsItemId);
	  /**
	   * 判断是否撤销定版
	   * @param id
	   */
	  void updateIsAdd(String id);
	  /**
	   * 查看库存数
	   */
	  MdsPurchaseList selectInventoryNumber(String mdsItemId);
}