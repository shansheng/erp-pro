/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysmarketview.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.common.utils.StringUtils;
import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;

import com.pt.modules.mds.mdsquota.entity.MdsQuota;
import com.pt.modules.mds.mdsquota.service.MdsQuotaService;
import com.pt.modules.sys.entity.Role;
import com.pt.modules.sys.mapper.RoleMapper;
import com.pt.modules.sys.sysmarket.entity.SysMarket;
import com.pt.modules.sys.sysmarket.entity.SysMarketList;
import com.pt.modules.sys.sysmarketview.mapper.SysMarketViewListMapper;
import com.pt.modules.sys.sysmarketview.mapper.SysMarketViewMapper;


/**
 * 销售订单下达Service
 * @author 郑利
 * @version 2018-09-08
 */
@Service
@Transactional(readOnly = true)
public class SysMarketViewService extends CrudService<SysMarketViewMapper, SysMarket> {

	@Autowired
	private SysMarketViewListMapper sysMarketListMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private SysMarketViewMapper sysMarketMapper;

	public SysMarket get(String id) {
		SysMarket SysMarket = sysMarketMapper.get(id);
		SysMarket.setSysMarketListList(sysMarketListMapper.findList(new SysMarketList(SysMarket)));

		return SysMarket;
	}
	
	public List<SysMarket> findList(SysMarket sysMarket) {
		return super.findList(sysMarket);
	}
	public List<Role> findList(Role role) {
		return roleMapper.findList(role);
	}
	public Page<SysMarket> findPage(Page<SysMarket> page, SysMarket sysMarket) {
		return super.findPage(page, sysMarket);
	}
	
	@Transactional(readOnly = false)
	public void save(SysMarket SysMarket) {
		super.save(SysMarket);
		for (SysMarketList sysMarketList : SysMarket.getSysMarketListList()){
			if (sysMarketList.getId() == null){
				continue;
			}
			if (SysMarketList.DEL_FLAG_NORMAL.equals(sysMarketList.getDelFlag())){
				if (StringUtils.isBlank(sysMarketList.getId())){
					sysMarketList.preInsert();
					sysMarketListMapper.insert(sysMarketList);
				}else{
					sysMarketList.preUpdate();
					sysMarketListMapper.update(sysMarketList);
				}
			}else{
				sysMarketListMapper.delete(sysMarketList);
			}
		}
	}
	
	/**
	 * @param sysMarket
	 */
	@Transactional(readOnly = false)
	public void sysMarketDispatch(SysMarket SysMarket) {
		SysMarket.setAssignDate(new Date());
		if(SysMarket.getContractClass()!=""&&"1".equals(SysMarket.getContractClass())){
			SysMarket.setMilestoneStatus("2");
			SysMarket.setMilestoneStatuss("已下达");
		}else{
			SysMarket.setMilestoneStatus("1");
			SysMarket.setMilestoneStatuss("未下达");
		}
		sysMarketMapper.sysMarketDispatch(SysMarket);
		
	}
	
}