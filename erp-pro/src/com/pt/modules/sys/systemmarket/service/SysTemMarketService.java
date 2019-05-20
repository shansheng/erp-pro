/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.systemmarket.service;


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
import com.pt.modules.sys.sysmarket.entity.SysMarket;
import com.pt.modules.sys.sysmarket.entity.SysMarketList;

import com.pt.modules.sys.systemmarket.mapper.SysTemMarketListMapper;
import com.pt.modules.sys.systemmarket.mapper.SysTemMarketMapper;
import com.pt.modules.sys.utils.UserUtils;


/**
 * 销售管理Service
 * @author 郑利
 * @version 2018-08-23
 */
@Service
@Transactional(readOnly = true)
public class SysTemMarketService extends CrudService<SysTemMarketMapper, SysMarket> {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private RoleMapper roleMapper;
    @Autowired
    private SysTemMarketListMapper sysMarketListMapper;

    @Autowired
    private SysTemMarketMapper sysMarketMapper;
	public SysMarket get(String id) {
		
		SysMarket sysMarket = super.get(id);
		sysMarket.setSysMarketListList(sysMarketListMapper.findList(new SysMarketList(sysMarket)));

		return sysMarket;
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
	public void save(SysMarket sysMarket) {
		super.save(sysMarket);
		for (SysMarketList sysMarketList : sysMarket.getSysMarketListList()){
			if (sysMarketList.getId() == null){
				continue;
			}
			
			if (SysMarketList.DEL_FLAG_NORMAL.equals(sysMarketList.getDelFlag())){
				if (StringUtils.isBlank(sysMarketList.getId())){
					sysMarketList.setSysMarketId(sysMarket.getId());
					
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
	@Transactional(readOnly = false)
	public Map<String, Object> getBillNo(SysMarket sysMarket) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();  
		String statement = "com.pt.modules.sys.sysMarket.mapper.SysMarketMapper.getBillNo";
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
	public void delete(SysMarket sysMarket) {
		super.delete(sysMarket);
		sysMarketListMapper.delete(new SysMarketList(sysMarket));
	}

	
}