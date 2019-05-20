package com.pt.modules.mes.mesworkshift.service;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.common.utils.StringUtils;
import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsequipment.entity.MdsEquipment;
import com.pt.modules.mds.mdsequipmentuser.entity.MdsEquipmentUser;
import com.pt.modules.mes.mesworkshift.entity.MesWorkshift;
import com.pt.modules.mes.mesworkshift.entity.MesWorkshiftUser;
import com.pt.modules.mes.mesworkshift.mapper.MesWorkshiftMapper;
import com.pt.modules.mes.mesworkshift.mapper.MesWorkshiftUserMapper;
import com.pt.modules.sys.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class MesWorkshiftUserService extends CrudService<MesWorkshiftMapper, MesWorkshift>{
	@Autowired
	private MesWorkshiftUserMapper mesWorkshiftUserMapper;
	
	public MesWorkshift get(String id) {
		return super.get(id);
	}
	
	public List<MesWorkshift> findList(MesWorkshift mesWorkshift) {
		return super.findList(mesWorkshift);
	}
	
	public Page<MesWorkshift> findPage(Page<MesWorkshift> page, MesWorkshift mesWorkshift) {
		return super.findPage(page, mesWorkshift);
	}
	
	@Transactional(readOnly = false)
	public void save(MesWorkshift mesWorkshift) {
		super.save(mesWorkshift);
		for (MesWorkshiftUser mesWorkshiftUser : mesWorkshift.getMesWorkshiftUserList()){
			if (mesWorkshiftUser.getId() == null){
				continue;
			}
			if (MesWorkshiftUser.DEL_FLAG_NORMAL.equals(mesWorkshiftUser.getDelFlag())){
				if (StringUtils.isBlank(mesWorkshiftUser.getId())){
					mesWorkshiftUser.setMesWorkshiftId(mesWorkshift.getId());
					mesWorkshiftUser.preInsert();
					mesWorkshiftUserMapper.insert(mesWorkshiftUser);
				}else{
					mesWorkshiftUser.preUpdate();
					mesWorkshiftUserMapper.update(mesWorkshiftUser);
				}
			}else{
				mesWorkshiftUserMapper.delete(mesWorkshiftUser);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(MesWorkshift mesWorkshift) {
		super.delete(mesWorkshift);
		mesWorkshiftUserMapper.delete(mesWorkshift.getId());
	}

	/**
	 * 查询子列表
	 * @param page
	 * @param mesWorkshiftUser
	 * @return
	 */
	@Transactional(readOnly = false)
	public Page<MesWorkshiftUser> findSubPage(Page<MesWorkshiftUser> page, MesWorkshiftUser mesWorkshiftUser) {

		// 设置分页参数
		mesWorkshiftUser.setPage(page);
		// 执行分页查询
		page.setList(mesWorkshiftUserMapper.findSubList(mesWorkshiftUser));
		return page;
	}
	
	@Transactional(readOnly = false)
	public Page<MesWorkshiftUser> findUserPage(Page<MesWorkshiftUser> page, MesWorkshiftUser mesWorkshiftUser) {

		// 设置分页参数
		mesWorkshiftUser.setPage(page);
		// 执行分页查询
		page.setList(mesWorkshiftUserMapper.findUserList(mesWorkshiftUser));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void saveUsers(String mesWorkshiftId, String sysUserId) {
		Date createDate=new Date();
		Date updateDate=new Date();
		mesWorkshiftUserMapper.saveUsers(mesWorkshiftId, sysUserId, UserUtils.getUser().getId(),createDate,updateDate);
	}

	public MesWorkshiftUser getMesWorkshiftUser(String mesWorkshiftId) {
		return mesWorkshiftUserMapper.getMesWorkshift(mesWorkshiftId);
	}

	@Transactional(readOnly = false)
	public void deluser(String id) {
		mesWorkshiftUserMapper.deluser(id);
		
	}
	
	@Transactional(readOnly = false)
	public MesWorkshiftUser selectUser(String mesWorkshiftId, String sysUserId) {
		return mesWorkshiftUserMapper.selectUser(mesWorkshiftId,sysUserId);
	}
}
