/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvinbill.service;

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
import com.pt.common.utils.StringUtils;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.wms.wmsinvinbill.entity.WmsInvInBill;
import com.pt.modules.wms.wmsinvinbill.mapper.WmsInvInBillMapper;
import com.pt.modules.wms.wmsinvinbill.entity.WmsInvInBillList;
import com.pt.modules.wms.wmsinvinbill.mapper.WmsInvInBillListMapper;

/**
 * 入库单Service
 * @author 龚志强
 * @version 2018-05-15
 */
@Service
@Transactional(readOnly = true)
public class WmsInvInBillService extends CrudService<WmsInvInBillMapper, WmsInvInBill> {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	private WmsInvInBillMapper wmsInvInBillMapper;
	@Autowired
	private WmsInvInBillListMapper wmsInvInBillListMapper;
	
	public WmsInvInBill get(String id) {
		WmsInvInBill wmsInvInBill = super.get(id);
		wmsInvInBill.setWmsInvInBillListList(wmsInvInBillListMapper.findList(new WmsInvInBillList(wmsInvInBill)));
		return wmsInvInBill;
	}
	
	public List<WmsInvInBill> findList(WmsInvInBill wmsInvInBill) {
		return super.findList(wmsInvInBill);
	}
	
	public Page<WmsInvInBill> findPage(Page<WmsInvInBill> page, WmsInvInBill wmsInvInBill) {
		return super.findPage(page, wmsInvInBill);
	}
	
	@Transactional(readOnly = false)
	public void save(WmsInvInBill wmsInvInBill) {
		super.save(wmsInvInBill);
		for (WmsInvInBillList wmsInvInBillList : wmsInvInBill.getWmsInvInBillListList()){
			if (wmsInvInBillList.getId() == null){
				continue;
			}
			if (WmsInvInBillList.DEL_FLAG_NORMAL.equals(wmsInvInBillList.getDelFlag())){
				if (StringUtils.isBlank(wmsInvInBillList.getId())){
					wmsInvInBillList.setWmsInvInBill(wmsInvInBill);
					wmsInvInBillList.preInsert();
					wmsInvInBillList.setStatus("0");
					wmsInvInBillListMapper.insert(wmsInvInBillList);
				}else{
					wmsInvInBillList.preUpdate();
					wmsInvInBillListMapper.update(wmsInvInBillList);
				}
			}else{
				wmsInvInBillListMapper.delete(wmsInvInBillList);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(WmsInvInBill wmsInvInBill) {
		super.delete(wmsInvInBill);
		wmsInvInBillListMapper.delete(new WmsInvInBillList(wmsInvInBill));
	}
	
	//入库
	@Transactional(readOnly = false)
	public Boolean inBill(WmsInvInBill wmsInvInBill) {
		//入库单入库
		wmsInvInBillMapper.inBill(wmsInvInBill);
		
		//通过入库单id查询所有的入库单明细
		List<WmsInvInBillList> WmsInvInBillListList = wmsInvInBillListMapper.findByProperty("WMS_INV_IN_BILL_ID",wmsInvInBill.getId());
		
		if(WmsInvInBillListList == null || WmsInvInBillListList.size() == 0){
			return false;
		}else{
			//逐个入库单明细进行入库操作
			for(WmsInvInBillList wmsInvInBillList : WmsInvInBillListList){
				//------查询台账表是否有，有的话update，没有的话insert
				wmsInvInBillListMapper.inBill(wmsInvInBillList);
			}
			return true;
		}
	}
	
	@Transactional(readOnly = false)
	public Map<String, Object> getBillNo(WmsInvInBill wmsInvInBill) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();  
		String statement = "com.pt.modules.wms.wmsinvinbill.mapper.WmsInvInBillMapper.getBillNo";
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
	public Map<String, Object> doInInvSubmit(WmsInvInBill wmsInvInBill) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();  
		String statement = "com.pt.modules.wms.wmsinvinbill.mapper.WmsInvInBillMapper.doInInvSubmit";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_Id", wmsInvInBill.getId());
        parameterMap.put("i_Sys_User_Id", UserUtils.getUser().getId());
        parameterMap.put("o_Return_Int", null);
        parameterMap.put("o_Return_String", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
        return parameterMap;
	}
}