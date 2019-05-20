/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.purshchaselist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.common.utils.IdGen;
import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.mds.mdsprocurementplan.mapper.MdsProcurementPlanMapper;
import com.pt.modules.mds.purshchaselist.entiry.MdsPurchaseList;
import com.pt.modules.mds.purshchaselist.mapper.MdsPurchaseListMapper;


/**
 * 批量采购计划Service
 * @author zjj
 * @version 2018-09-19
 */
@Service
@Transactional(readOnly = true)
public class MdsPurchaseListService extends CrudService<MdsPurchaseListMapper, MdsPurchaseList> {
	@Autowired
    private MdsPurchaseListMapper mdsPurchaseListMapper;
	@Autowired
	private MdsProcurementPlanMapper proPlanMapper;
	public MdsPurchaseList get(String id) {
		return super.get(id);
	}
	
	public List<MdsPurchaseList> findList(MdsPurchaseList mdsPurchaseList) {
		return super.findList(mdsPurchaseList);
	}
	/**
	 * 批量采购明细
	 * @param page
	 * @param mdsPurchaseList
	 * @return
	 */
	public Page<MdsPurchaseList> findByMdsItemId(Page<MdsPurchaseList> page,MdsPurchaseList mdsPurchaseList){
		mdsPurchaseList.setPage(page);
		page.setList(mdsPurchaseListMapper.findByMdsItemId(mdsPurchaseList));
		return page;
	}
	public Page<MdsPurchaseList> findPage(Page<MdsPurchaseList> page, MdsPurchaseList mdsPurchaseList) {
		if(mdsPurchaseList.getBeginDemandDate()!=null && mdsPurchaseList.getEndDemandDate()!=null) {
			insertPurList(mdsPurchaseList);
			insertDeputyPur(mdsPurchaseList);
			insertTemPur(mdsPurchaseList);	
		}
		List<MdsPurchaseList> list= mdsPurchaseListMapper.findList(mdsPurchaseList);
		List<MdsPurchaseList> mdsList = new ArrayList<>();
		for(MdsPurchaseList mds : list){
			System.out.println("mds:" + mds);
			MdsPurchaseList mdsNumber = mdsPurchaseListMapper.selectInventoryNumber(mds.getMdsItemId());
			if(mdsNumber != null ){
				mds.setCurrentOnhandQty(mdsNumber.getCurrentOnhandQty());
			}
			mdsList.add(mds);
			System.out.println("mds" + mds);
		}
		mdsPurchaseList.setPage(page);
		page.setList(mdsList);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(MdsPurchaseList mdsPurchaseList) {
		mdsPurchaseList.setState("未添加");
		mdsPurchaseList.setSourceType("手动录入");
		mdsPurchaseList.setPurchaseState("批量采购");
		String id = mdsPurchaseListMapper.findItemId(mdsPurchaseList.getItemCode());
		mdsPurchaseList.setMdsItemId(id);
		super.save(mdsPurchaseList);
	}
/*	@Transactional(readOnly = false)
	public void update(MdsPurchaseList mdsPurchaseList) {
		mdsPurchaseList.preUpdate();
		mapper.update(mdsPurchaseList);
	}*/
	@Transactional(readOnly = false)
	public void delete(MdsPurchaseList mdsPurchaseList) {
		super.delete(mdsPurchaseList);
	}
	/**
	 * 项目中批量材料汇总
	 * @param mdsPurchaseList
	 */
	@Transactional(readOnly = false)
	public void insertPurList(MdsPurchaseList mdsPurchaseList) {
		List<MdsPurchaseList> purList = mdsPurchaseListMapper.findPurasev(mdsPurchaseList);
		for(MdsPurchaseList mdsPurchase:purList) {
			if(mdsPurchase!=null) {
			MdsPurchaseList m = mdsPurchaseListMapper.check(mdsPurchase);
			if(m==null) {
				mdsPurchase.setState("未添加");
				mdsPurchase.setPurchaseState("批量采购");
				mdsPurchase.setSourceType("项目录入");
				mdsPurchase.setPurchaseNumber(mdsPurchase.getDemandNumber());
				//判断是否撤销定板用
				mdsPurchaseListMapper.updateIsAdd(mdsPurchase.getId());
				mdsPurchase.preInsert();
				mapper.insert(mdsPurchase);
				
				
			}
			}
		}
		
		/*List<MdsPurchaseList> purList =mdsPurchaseListMapper.findAll(mdsPurchaseList);
		for(MdsPurchaseList mdsPurchase:purList) {
			if(mdsPurchase!=null) {
				System.out.println("111111111111111111mdsPurchase"+mdsPurchase.toString());
				MdsPurchaseList m = mdsPurchaseListMapper.check(mdsPurchase);
				if(m==null) {
					mdsPurchase.setId(IdGen.uuid());
					mdsPurchase.setState("未添加");
					mdsPurchase.setPurchaseNumber(mdsPurchase.getDemandNumber());
					System.out.println("2222222222222222222222mdsPurchase"+mdsPurchase.toString());
					mapper.insert(mdsPurchase);
				}
			}
		}*/
	}
	/**
	 * 临时汇总
	 * @param mdsPurchaseList
	 */
	@Transactional(readOnly = false)
	public void insertDeputyPur(MdsPurchaseList mdsPurchaseList) {
		List<MdsPurchaseList> purList = mdsPurchaseListMapper.findPuraseDeputy(mdsPurchaseList);
		for(MdsPurchaseList mdsPurchase:purList) {
			if(mdsPurchase!=null) {
				MdsPurchaseList m = mdsPurchaseListMapper.check(mdsPurchase);
				
				if(m==null) {
					mdsPurchase.setId(IdGen.uuid());
					mdsPurchase.setState("未添加");
					mdsPurchase.setPurchaseState("批量采购");
					mdsPurchase.setSourceType("临时录入");
					mdsPurchase.setPurchaseNumber(mdsPurchase.getDemandNumber());
					mapper.insert(mdsPurchase);
				}
			}
		}
	}
	@Transactional(readOnly = false)
	public void insertTemPur(MdsPurchaseList mdsPurchaseList) {
		 List<MdsPurchaseList> purList = mdsPurchaseListMapper.findPuraseTem(mdsPurchaseList);
		 for(MdsPurchaseList mdsPurchase:purList) {
			 if(mdsPurchase!=null) {
				 MdsPurchaseList m = mdsPurchaseListMapper.check(mdsPurchase);
				 if(m==null) {
					 mdsPurchase.setId(IdGen.uuid());
						mdsPurchase.setState("未添加");
						mdsPurchase.setPurchaseState("批量采购");
						mdsPurchase.setSourceType("项目录入");
						mdsPurchase.setPurchaseNumber(mdsPurchase.getDemandNumber());
						mapper.insert(mdsPurchase);
				 }
			 }
		 }
	}
	@Transactional(readOnly=false)
	public void addPurchase(String[] ids) {
		for(String mdsItemId:ids) {
			List<MdsPurchaseList>mList = mdsPurchaseListMapper.getByMdsItemId(mdsItemId);
			List<MdsProcurementPlan> proList = new ArrayList<>();
			
	        for(MdsPurchaseList m:mList) {
	        	MdsProcurementPlan mp = new MdsProcurementPlan();
	    		mp.setId(IdGen.uuid());
				mp.setMdsItemId(m.getMdsItemId());
				mp.setItemCode(m.getItemCode());
				mp.setItemName(m.getItemName());
				mp.setModelnm(m.getModelNum());
				mp.setNormalField(m.getNormalField());
				mp.setSpecs(m.getSpecs());
				mp.setPurchaseAmount(m.getPurchaseNumber());
				mp.setRequiredDate(m.getDemandDate());
				mp.setProcurementWay(m.getPurchaseState());
				mp.setSourceType(m.getSourceType());
				if(m.getClassify()==null || m.getClassify()=="") {
					mp.setClassify("0");
				}else {
					mp.setClassify(m.getClassify());
				}
				mp.setState("0");
				mp.preInsert();
				if(m.getMarketId()!=null) {
					mp.setFromId(m.getMarketId());
					//mdsPurchaseListMapper.updateSysMarketList(m.getMarketId());
				}else if(m.getTemPlanId()!=null) {
					mp.setFromId(m.getTemPlanId());
				}
				proList.add(mp);
	        }
	        if(null != proList && 0 != proList.size()){
	        	 proPlanMapper.insertProjectList(proList);
	        }
		}
		mdsPurchaseListMapper.addPurchase(ids);
		
	}
	
	
}