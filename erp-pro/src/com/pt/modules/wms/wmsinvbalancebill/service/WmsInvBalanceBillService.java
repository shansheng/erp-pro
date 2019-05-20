/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvbalancebill.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.wms.wmsinvbalancebill.entity.WmsInvBalanceBill;
import com.pt.modules.wms.wmsinvbalancebill.mapper.WmsInvBalanceBillMapper;

/**
 * 库存结转Service
 * @author hexl
 * @version 2018-05-18
 */
@Service
@Transactional(readOnly = true)
public class WmsInvBalanceBillService extends CrudService<WmsInvBalanceBillMapper, WmsInvBalanceBill> {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public WmsInvBalanceBill get(String id) {
		return super.get(id);
	}
	
	public List<WmsInvBalanceBill> findList(WmsInvBalanceBill wmsInvBalanceBill) {
		return super.findList(wmsInvBalanceBill);
	}
	
	public Page<WmsInvBalanceBill> findPage(Page<WmsInvBalanceBill> page, WmsInvBalanceBill wmsInvBalanceBill) {
		return super.findPage(page, wmsInvBalanceBill);
	}
	
	@Transactional(readOnly = false)
	public void save(WmsInvBalanceBill wmsInvBalanceBill) {
		super.save(wmsInvBalanceBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(WmsInvBalanceBill wmsInvBalanceBill) {
		super.delete(wmsInvBalanceBill);
	}
	
	/**
	 * 库存结转
	 * @param mesEwo
	 */
	@Transactional(readOnly = false)
	public Map carryOver(WmsInvBalanceBill wmsInvBalanceBill) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "carryOver";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_wms_inventory_id", wmsInvBalanceBill.getWmsInventoryId());
        parameterMap.put("i_year", wmsInvBalanceBill.getYear());
        parameterMap.put("i_Period", wmsInvBalanceBill.getPeriod());
        parameterMap.put("i_user_id", UserUtils.getUser().getId());
        parameterMap.put("o_Return_Int", 0);
        parameterMap.put("o_Return_String", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
        return parameterMap;
	}
	/**
	 * 库存反结转
	 * @param mesEwo
	 */
	@Transactional(readOnly = false)
	public Map carryOverReverse(WmsInvBalanceBill wmsInvBalanceBill) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "carryOverReverse";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_wms_inventory_id", wmsInvBalanceBill.getWmsInventoryId());
        parameterMap.put("i_year", wmsInvBalanceBill.getYear());
        parameterMap.put("i_Period", wmsInvBalanceBill.getPeriod());
        parameterMap.put("i_user_id", UserUtils.getUser().getId());
        parameterMap.put("o_Return_Int", 0);
        parameterMap.put("o_Return_String", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
        return parameterMap;
	}
}