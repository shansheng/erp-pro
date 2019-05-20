/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysmarketship.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.common.utils.StringUtils;
import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.sys.entity.Role;
import com.pt.modules.sys.mapper.RoleMapper;
import com.pt.modules.sys.sysmarketship.entity.SysMarketShip;
import com.pt.modules.sys.sysmarketship.entity.SysMarketShipList;
import com.pt.modules.sys.sysmarketship.mapper.SysMarketShipListMapper;
import com.pt.modules.sys.sysmarketship.mapper.SysMarketShipMapper;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.work.workorderson.entity.WorkOrderSon;
import com.pt.modules.work.workorderson.mapper.WorkOrderSonMapper;


/**
 * 销售管理Service
 * @author 郑利
 * @version 2018-08-23
 */
@Service
@Transactional(readOnly = true)
public class SysMarketShipService extends CrudService<SysMarketShipMapper, SysMarketShip> {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private RoleMapper roleMapper;
@Autowired
private SysMarketShipListMapper sysMarketListMapper;

@Autowired
private WorkOrderSonMapper workOrderSonMapper;

@Autowired
private SysMarketShipMapper sysMarketMapper;
	public SysMarketShip get(String id) {
		
		SysMarketShip SysMarketShip = super.get(id);
		SysMarketShip.setSysMarketShipListList(sysMarketListMapper.findList(new SysMarketShipList(SysMarketShip)));

		return SysMarketShip;
	}
	
	public List<SysMarketShip> findList(SysMarketShip SysMarketShip) {
		return super.findList(SysMarketShip);
	}
	public List<Role> findList(Role role) {
		return roleMapper.findList(role);
	}
	public Page<SysMarketShip> findPage(Page<SysMarketShip> page, SysMarketShip SysMarketShip) {
		return super.findPage(page, SysMarketShip);
	}
	
	@Transactional(readOnly = false)
	public void save(SysMarketShip SysMarketShip) {
		super.save(SysMarketShip);
		for (SysMarketShipList SysMarketShipList : SysMarketShip.getSysMarketShipListList()){
			if (SysMarketShipList.getId() == null){
				continue;
			}
			
			if (SysMarketShipList.DEL_FLAG_NORMAL.equals(SysMarketShipList.getDelFlag())){
				if (StringUtils.isBlank(SysMarketShipList.getId())){
					SysMarketShipList.setSysMarketId(SysMarketShip.getId());
					
					SysMarketShipList.preInsert();
					sysMarketListMapper.insert(SysMarketShipList);
				}else{
					SysMarketShipList.preUpdate();
					sysMarketListMapper.update(SysMarketShipList);
				}
			}else{
				sysMarketListMapper.delete(SysMarketShipList);
			}
			
		}
	}
	@Transactional(readOnly = false)
	public Map<String, Object> getBillNo(SysMarketShip SysMarketShip) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();  
		String statement = "com.pt.modules.sys.SysMarketShip.mapper.SysMarketMapper.getBillNo";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_auto_code", "wms_inv_in_bill_no");
        parameterMap.put("i_prefix", "");
        parameterMap.put("i_postfix", "");
        parameterMap.put("i_Sys_User_Id", UserUtils.getUser().getId());
        parameterMap.put("o_return_code", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
        return parameterMap;
	}
	@Transactional(readOnly = false)
	public void delete(SysMarketShip SysMarketShip) {
		super.delete(SysMarketShip);
		sysMarketListMapper.delete(new SysMarketShipList(SysMarketShip));
	}

	/**
	 * @param sysMarketShip
	 */
	@Transactional(readOnly = false)
	public void sysMarketDispatch(SysMarketShip sysMarketShip) {
		mapper.sysMarketDispatch(sysMarketShip);
		for (SysMarketShipList SysMarketShipList : sysMarketShip.getSysMarketShipListList()){
			SysMarketShipList.preUpdate();
			String n=SysMarketShipList.getDeliverNums();
			int ni=Integer.parseInt(n);
			String SysMarketListId=SysMarketShipList.getId();
			List<WorkOrderSon> list=workOrderSonMapper.getBySysMarketListId(SysMarketListId);
			for(int a=0;a<ni;a++){
				
				workOrderSonMapper.updateShipments(list.get(a));
			}
			sysMarketListMapper.update(SysMarketShipList);

		}
		
	}

	/**
	 * @param listId
	 * @return
	 */
	public SysMarketShipList getListById(String listId) {
	
		return sysMarketListMapper.getListById(listId);
	}

	/**
	 * @param sysMarketShip
	 */
	public void kp(SysMarketShip sysMarketShip) {
		 mapper.kp(sysMarketShip);
		
	}

	
}