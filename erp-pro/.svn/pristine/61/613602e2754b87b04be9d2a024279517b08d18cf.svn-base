/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvoutbill.service;

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
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.wms.wmsinvoutbill.entity.WmsInvOutBill;
import com.pt.modules.wms.wmsinvoutbill.entity.WmsInvOutBillList;
import com.pt.modules.wms.wmsinvoutbill.mapper.WmsInvOutBillListMapper;
import com.pt.modules.wms.wmsinvoutbill.mapper.WmsInvOutBillMapper;

/**
 * 出库管理Service
 * @author 龚志强
 * @version 2018-05-21
 */
@Service
@Transactional(readOnly = true)
public class WmsInvOutBillService extends CrudService<WmsInvOutBillMapper, WmsInvOutBill> {

	@Autowired
	private WmsInvOutBillMapper wmsInvOutBillMapper;
	@Autowired
	private WmsInvOutBillListMapper wmsInvOutBillListMapper;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public WmsInvOutBill get(String id) {
		WmsInvOutBill wmsInvOutBill = super.get(id);
		wmsInvOutBill.setWmsInvOutBillListList(wmsInvOutBillListMapper.findList(new WmsInvOutBillList(wmsInvOutBill)));
		return wmsInvOutBill;
	}
	
	public List<WmsInvOutBill> findList(WmsInvOutBill wmsInvOutBill) {
		return super.findList(wmsInvOutBill);
	}
	
	public Page<WmsInvOutBill> findPage(Page<WmsInvOutBill> page, WmsInvOutBill wmsInvOutBill) {
		return super.findPage(page, wmsInvOutBill);
	}
	
	@Transactional(readOnly = false)
	public void save(WmsInvOutBill wmsInvOutBill) {
		if(wmsInvOutBill.getStatus() == null || "".equals(wmsInvOutBill.getStatus())){
			wmsInvOutBill.setStatus("0");
		}
		super.save(wmsInvOutBill);
		for (WmsInvOutBillList wmsInvOutBillList : wmsInvOutBill.getWmsInvOutBillListList()){
			if (wmsInvOutBillList.getId() == null){
				continue;
			}
			if (WmsInvOutBillList.DEL_FLAG_NORMAL.equals(wmsInvOutBillList.getDelFlag())){
				if (StringUtils.isBlank(wmsInvOutBillList.getId())){
					wmsInvOutBillList.setWmsInvOutBill(wmsInvOutBill);
					wmsInvOutBillList.preInsert();
					wmsInvOutBillListMapper.insert(wmsInvOutBillList);
				}else{
					wmsInvOutBillList.preUpdate();
					wmsInvOutBillListMapper.update(wmsInvOutBillList);
				}
			}else{
				wmsInvOutBillListMapper.delete(wmsInvOutBillList);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(WmsInvOutBill wmsInvOutBill) {
		super.delete(wmsInvOutBill);
		wmsInvOutBillListMapper.delete(new WmsInvOutBillList(wmsInvOutBill));
	}
	
	@Transactional(readOnly = false)
	public Map<String, Object> getBillNo(WmsInvOutBill wmsInvOutBill) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();  
		String statement = "com.pt.modules.wms.wmsinvoutbill.mapper.WmsInvOutBillMapper.getBillNo";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_auto_code", "wms_inv_out_bill_no");
        parameterMap.put("i_prefix", "");
        parameterMap.put("i_postfix", "");
        parameterMap.put("i_Sys_User_Id", UserUtils.getUser().getId());
        parameterMap.put("o_return_code", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
        return parameterMap;
	}
	
	@Transactional(readOnly = false)
	public Map<String, Object> doOutInvSubmit(WmsInvOutBill wmsInvOutBill) {
		//查询出库单中是否所有物料的出库数量均<=可用库存数
		Double shortageNum = wmsInvOutBillMapper.shortageNum(wmsInvOutBill);
		if(shortageNum > 0){
			throw new RuntimeException("出库数量不能大于库存数量！");
		}
		
		SqlSession sqlSession = sqlSessionFactory.openSession();  
		String statement = "com.pt.modules.wms.wmsinvoutbill.mapper.WmsInvOutBillMapper.doOutInvSubmit";
		///pt-plus.src.com.pt.modules.wms.wmsinvoutbill.mapper.WmsInvOutBillMapper.java
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_Id", wmsInvOutBill.getId());
        parameterMap.put("i_Sys_User_Id", UserUtils.getUser().getId());
        parameterMap.put("o_Return_Int", null);
        parameterMap.put("o_Return_String", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
        return parameterMap;
	}
}