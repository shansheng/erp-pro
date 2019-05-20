/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsequipmentuser.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.Page;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mds.mdsequipment.entity.MdsEquipment;
import com.pt.modules.mds.mdsequipmentuser.entity.MdsEquipmentUser;

/**
 * 设备与人员关系表MAPPER接口
 * @author 郑利
 * @version 2018-05-05
 */
@MyBatisMapper
public interface MdsEquipmentUserMapper extends BaseMapper<MdsEquipmentUser> {

	/**
	 * 查询关联用户列表
	 * @param mdsEquipmentUser
	 * @return
	 */
	public List<MdsEquipmentUser> findSubList(MdsEquipmentUser mdsEquipmentUser);
	
	public List<MdsEquipmentUser> findUserList(MdsEquipmentUser mdsEquipmentUser);
	
	public void saveUsers(@Param("mdsEquipmentId")String mdsEquipmentId, @Param("sysUserId")String sysUserId, @Param("userId")String userId,@Param("createDate")Date createDate,@Param("updateDate")Date updateDate);

	/**
	 * @param mdsEquipmentId
	 * @return
	 */
	public MdsEquipmentUser getMdsEquipmentUser(String mdsEquipmentId);

	/**
	 * @param userId
	 * @param mdsEquipmentId
	 */
	public void deluser(@Param("id")String id);

	/**
	 * @param mdsEquipmentId
	 * @param sysUserId
	 * @return
	 */
	public MdsEquipmentUser selectUser(@Param("mdsEquipmentId")String mdsEquipmentId, @Param("sysUserId")String sysUserId);


}