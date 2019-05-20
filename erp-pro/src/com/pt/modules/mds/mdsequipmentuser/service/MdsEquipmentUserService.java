/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsequipmentuser.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.common.utils.StringUtils;
import com.pt.modules.mds.mdsequipment.entity.MdsEquipment;
import com.pt.modules.mds.mdsequipment.mapper.MdsEquipmentMapper;
import com.pt.modules.mds.mdsequipmentuser.entity.MdsEquipmentUser;
import com.pt.modules.mds.mdsequipmentuser.mapper.MdsEquipmentUserMapper;
import com.pt.modules.sys.entity.Role;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 设备人员管理Service
 * @author 郑利
 * @version 2018-05-05
 */
@Service
@Transactional(readOnly = true)
public class MdsEquipmentUserService extends CrudService<MdsEquipmentMapper, MdsEquipment> {

	@Autowired
	private MdsEquipmentUserMapper mdsEquipmentUserMapper;
	
	public MdsEquipment get(String id) {
		return super.get(id);
	}
	
	public List<MdsEquipment> findList(MdsEquipment mdsEquipment) {
		return super.findList(mdsEquipment);
	}
	
	public Page<MdsEquipment> findPage(Page<MdsEquipment> page, MdsEquipment mdsEquipment) {
		return super.findPage(page, mdsEquipment);
	}
	
	@Transactional(readOnly = false)
	public void save(MdsEquipment mdsEquipment) {
		super.save(mdsEquipment);
		for (MdsEquipmentUser mdsEquipmentUser : mdsEquipment.getMdsEquipmentUserList()){
			if (mdsEquipmentUser.getId() == null){
				continue;
			}
			if (MdsEquipmentUser.DEL_FLAG_NORMAL.equals(mdsEquipmentUser.getDelFlag())){
				if (StringUtils.isBlank(mdsEquipmentUser.getId())){
					mdsEquipmentUser.setMdsEquipmentId(mdsEquipment.getId());
					mdsEquipmentUser.preInsert();
					mdsEquipmentUserMapper.insert(mdsEquipmentUser);
				}else{
					mdsEquipmentUser.preUpdate();
					mdsEquipmentUserMapper.update(mdsEquipmentUser);
				}
			}else{
				mdsEquipmentUserMapper.delete(mdsEquipmentUser);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(MdsEquipment mdsEquipment) {
		super.delete(mdsEquipment);
		mdsEquipmentUserMapper.delete(mdsEquipment.getId());
	}

	/**
	 * 查询子列表
	 * @param page
	 * @param mdsEquipmentUser
	 * @return
	 */
	@Transactional(readOnly = false)
	public Page<MdsEquipmentUser> findSubPage(Page<MdsEquipmentUser> page, MdsEquipmentUser mdsEquipmentUser) {

		// 设置分页参数
		mdsEquipmentUser.setPage(page);
		// 执行分页查询
		page.setList(mdsEquipmentUserMapper.findSubList(mdsEquipmentUser));
		return page;
	}
	
	@Transactional(readOnly = false)
	public Page<MdsEquipmentUser> findUserPage(Page<MdsEquipmentUser> page, MdsEquipmentUser mdsEquipmentUser) {

		// 设置分页参数
		mdsEquipmentUser.setPage(page);
		// 执行分页查询
		page.setList(mdsEquipmentUserMapper.findUserList(mdsEquipmentUser));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void saveUsers(String mdsEquipmentId, String sysUserId) {
		Date createDate=new Date();
		Date updateDate=new Date();
		mdsEquipmentUserMapper.saveUsers(mdsEquipmentId, sysUserId, UserUtils.getUser().getId(),createDate,updateDate);
	}

	/**
	 * @param mdsEquipmentId
	 * @return
	 */
	public MdsEquipmentUser getMdsEquipmentUser(String mdsEquipmentId) {
		return mdsEquipmentUserMapper.getMdsEquipmentUser(mdsEquipmentId);
	}

	/**
	 * @param userId
	 * @param mdsEquipmentId
	 */
	@Transactional(readOnly = false)
	public void deluser(String id) {
		mdsEquipmentUserMapper.deluser(id);
		
	}

	/**
	 * @param mdsEquipmentId
	 * @param string
	 * @return
	 */
	@Transactional(readOnly = false)
	public MdsEquipmentUser selectUser(String mdsEquipmentId, String sysUserId) {
		
		return mdsEquipmentUserMapper.selectUser(mdsEquipmentId,sysUserId);
	}




	
}