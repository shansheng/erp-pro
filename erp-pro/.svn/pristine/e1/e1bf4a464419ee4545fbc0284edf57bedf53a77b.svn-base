/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matbom.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mat.matbom.entity.MatBom;
import com.pt.modules.mat.matbom.entity.MatBomDetail;
import com.pt.modules.mds.mdsquota.entity.MdsQuota;
import com.pt.modules.mds.mdsquota.entity.MdsQuotaDetail;

/**
 * mat_bomMAPPER接口
 * @author 郑利
 * @version 2018-09-27
 */
@MyBatisMapper
public interface MatBomMapper extends BaseMapper<MatBom> {

	/**
	 * @param matBomDetail
	 */
	public int update(MatBomDetail matBomDetail);
	
	/**
	 * 修改材料定额状态
	 * @param mdsQuota
	 * @return
	 */
	public int changeConfirm(MatBom matBom);

	/**
	 * @param matBom
	 * @return
	 */
	public MatBom getByMarketItemId(MatBom matBom);
	

}