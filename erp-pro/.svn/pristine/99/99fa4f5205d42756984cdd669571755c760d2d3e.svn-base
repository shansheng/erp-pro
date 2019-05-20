/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.mesworkshift.mapper;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.mes.mesworkshift.entity.MesWorkshift;
import com.pt.modules.mes.mesworkshift.entity.MesWorkshiftUser;

/**
 * 班组用户关系表MAPPER接口
 * @author 孙会楠
 * @version 2018-05-05
 */
@MyBatisMapper
public interface MesWorkshiftUserMapper extends BaseMapper<MesWorkshiftUser> {
	
	public  List<MesWorkshiftUser> findSubList(MesWorkshiftUser mesWorkshiftUser);

	public List<MesWorkshiftUser> findUserList(MesWorkshiftUser mesWorkshiftUser);

	public MesWorkshiftUser getMesWorkshift(String mesWorkshiftId);

	public void deluser(@Param("id")String id);

	public MesWorkshiftUser selectUser(@Param("mesWorkshiftId")String mesWorkshiftId, @Param("sysUserId")String sysUserId);

	public void saveUsers(@Param("mesWorkshiftId")String mesWorkshiftId, @Param("sysUserId")String sysUserId, @Param("userId")String userId, @Param("createDate")Date createDate,@Param("updateDate") Date updateDate);

}