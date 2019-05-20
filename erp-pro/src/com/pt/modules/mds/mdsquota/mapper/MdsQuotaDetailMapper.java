/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsquota.mapper;

import java.util.ArrayList;
import java.util.List;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsitem.entity.MdsItem;
import com.pt.modules.mds.mdsquota.entity.DictOfTree;
import com.pt.modules.mds.mdsquota.entity.MdsQuota;
import com.pt.modules.mds.mdsquota.entity.MdsQuotaDetail;
import com.pt.modules.sys.entity.DictValue;

/**
 * 材料定额明细表MAPPER接口
 * @author cjx
 * @version 2018-09-11
 */
@MyBatisMapper
public interface MdsQuotaDetailMapper extends BaseMapper<MdsQuotaDetail> {
	
	/**
	 * 通过sys_market_list_id查询当前产品中的物料
	 * @param id
	 * @return
	 */
	List<MdsItem> findItemByMarkList(String id);
	
	/**
	 * 获取字典数据
	 */
	List<DictOfTree> getChildren(MdsQuota mdsQuota);
	
	/**
	 * 获取该产品下未定版的所有物料
	 * @param mdsQuotaDetail
	 * @return
	 */
	List<MdsQuotaDetail> findNotVersionList(MdsQuotaDetail mdsQuotaDetail);
	
	/**
	 * 获取该产品下指定版本的所有物料
	 * @param version
	 * @return
	 */
	List<MdsQuotaDetail> findVersionList(MdsQuotaDetail mdsQuotaDetail);
	
	/**
	 * 批量修改定版状态
	 * @param list
	 * @return
	 */
	int changVersion(List<MdsQuotaDetail> list);
	
	/**
	 * 批量修改是否添加到采购计划中
	 * @param list
	 * @return
	 */
	int updateAddProcurement(List<MdsQuotaDetail> list);
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	int saveList(List<MdsQuotaDetail> list);
	/**
	 * 获取物料信息
	 * @param itemCode
	 * @return
	 */
	MdsItem getMdsItem(String itemCode);
	/**
	 * 对比字典信息
	 * @param label
	 * @return
	 */
	DictValue findLabel(String label);
	/**
	 * 批量更新
	 */
	int updateList(List<MdsQuotaDetail> list);
	/**
	 * 
	 * @param classify
	 * @return
	 */
	DictValue getValue(String classify);
	/**
	 * @param mdsQuotaId
	 * @return
	 */
	public List<MdsQuotaDetail> getByQuotaId(String mdsQuotaId);
	/**
	 * @param mdsQuotaDetail
	 */
	public void updateOmiNum(MdsQuotaDetail mdsQuotaDetail);

	/**
	 * @param mdsQuotaDetail
	 * @return
	 */
	MdsQuotaDetail getByQuotaItemId(MdsQuotaDetail mdsQuotaDetail);
	/**
	 * 查询物料分类
	 * @return
	 */
	ArrayList<String> getMdsClassify();
	
}