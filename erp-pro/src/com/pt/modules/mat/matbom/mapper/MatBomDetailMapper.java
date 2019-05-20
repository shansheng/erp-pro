/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matbom.mapper;

import java.util.List;

import com.pt.core.persistence.TreeMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mat.matbom.entity.MatBomDetail;

/**
 * Bom管理MAPPER接口
 * @author 郑利
 * @version 2018-09-27
 */
@MyBatisMapper
public interface MatBomDetailMapper extends TreeMapper<MatBomDetail> {

	/**
	 * @param parentId
	 * @return
	 */
	MatBomDetail getD(String parentId);

	/**
	 * @param matBomDetail
	 * @return
	 */
	MatBomDetail getByItemBomId(MatBomDetail matBomDetail);

	/**
	 * @param matBomId
	 * @return
	 */
	List<MatBomDetail> getByMatBomId(String matBomId);

	/**
	 * @param matBomDetail
	 * @return
	 */
	MatBomDetail getByIBId(MatBomDetail matBomDetail);
	
}