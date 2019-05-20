/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsquota.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsquota.entity.MdsQuota;
import com.pt.modules.mds.mdsquota.entity.MdsQuotaDetail;

/**
 * 材料定额管理MAPPER接口
 * @author cjx
 * @version 2018-09-11
 */
@MyBatisMapper
public interface MdsQuotaMapper extends BaseMapper<MdsQuota> {
	
	/**
	 * 修改材料定额状态
	 * @param mdsQuota
	 * @return
	 */
	public int changeConfirm(MdsQuota mdsQuota);
	
	/**
	 * 修改材料定额状态
	 * @param mdsQuotaDetail
	 * @return
	 */
	public int update(MdsQuotaDetail mdsQuotaDetail);



	/**
	 * @param sysMarketListId
	 * @return
	 */
	public MdsQuota findByMarketListId(String sysMarketListId);

	/**
	 * @param mdsQuota
	 * @return
	 */
	public MdsQuota getByMarketItemId(MdsQuota mdsQuota);
	/**
	 * 修改检验人
	 * @param verify
	 * @param id
	 */
	@Update("update mds_quota_detail set VERIFY_BY =#{verifyby} where MDS_QUOTA_ID = #{id}")
	void updateVerify(@Param("verifyby")String verifyby,@Param("id")String id);
}