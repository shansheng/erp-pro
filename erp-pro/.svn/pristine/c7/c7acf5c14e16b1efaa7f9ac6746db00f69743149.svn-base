/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmainstoragevs.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainRecord;
import com.pt.modules.wms.wmsmainstoragevs.dto.WmsoutInRecordDTO;
import com.pt.modules.wms.wmsmainstoragevs.mapper.WmsMainRecordVsMapper;

/**
 * 库房流水记录Service
 * @author cjx
 * @version 2018-10-18
 */
@Service
@Transactional(readOnly = true)
public class WmsMainRecordService extends CrudService<WmsMainRecordVsMapper, WmsMainRecord> {
	   @Autowired
	    private WmsMainRecordVsMapper wmsMapper;
	public WmsMainRecord get(String id) {
		return super.get(id);
	}
	
	public List<WmsMainRecord> findList(WmsMainRecord wmsMainRecord) {
		return super.findList(wmsMainRecord);
	}
	
	public Page<WmsMainRecord> findPage(Page<WmsMainRecord> page, WmsMainRecord wmsMainRecord) {
		return super.findPage(page, wmsMainRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(WmsMainRecord wmsMainRecord) {
		super.save(wmsMainRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(WmsMainRecord wmsMainRecord) {
		super.delete(wmsMainRecord);
	}
	public LinkedHashMap<String,Object> getOutNum(){
		String[] monthStr = {"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
		String[] outNumStr = {"0","0","0","0","0","0","0","0","0","0","0","0"};
		String[] inNumStr = {"0","0","0","0","0","0","0","0","0","0","0","0"};
		List<WmsoutInRecordDTO> outList = wmsMapper.getOutNum();
		if (outList != null) {
			for (WmsoutInRecordDTO w : outList) {
				 String month = w.getMonth() + "月";
				for (int i = 0; i < monthStr.length; i ++) {
					 if (month.equals(monthStr[i])) {
						 outNumStr[i] = w.getOutNum();
					 }
				}
			}
		}
		List<WmsoutInRecordDTO> inList = wmsMapper.getInNum();
		if (inList != null) {
			 for (WmsoutInRecordDTO w : inList) {
				 String month = w.getMonth() + "月";
				 for (int i = 0; i < monthStr.length; i++) {
					 if (month.equals(monthStr[i])) {
						  inNumStr[i] = w.getInNum();
					 }
				 }
			 }
		}
		LinkedHashMap<String,Object> body = new LinkedHashMap<String,Object>();
		WmsoutInRecordDTO wms = new WmsoutInRecordDTO();
		wms.setMonth(strConvert(monthStr));
		wms.setOutNum(strConvert(outNumStr));
		wms.setInNum(strConvert(inNumStr));
		body.put("body", wms);
		return body;
	}
	private String strConvert(String[] arr){
		StringBuilder b= new StringBuilder();
		for(int i = 0; i < arr.length; i++){
			b.append(arr[i]+",");
		}
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
}