/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsReplaceItem.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.common.utils.IdGen;
import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsReplaceItem.entity.MdsReplaceItem;
import com.pt.modules.mds.mdsReplaceItem.mapper.MdsReplaceItemMapper;
import com.pt.modules.mds.mdsitem.entity.MdsItem;
import com.pt.modules.mds.mdsitem.mapper.MdsItemMapper;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.mds.mdsprocurementplan.mapper.MdsProcurementPlanMapper;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.utils.UserUtils;


/**
 * 代料申请Service
 * @author zjj
 * @version 2018-10-11
 */
@Service
@Transactional(readOnly = true)
public class MdsReplaceItemService extends CrudService<MdsReplaceItemMapper, MdsReplaceItem> {
	@Autowired
	private MdsReplaceItemMapper mdsReplaceItemMapper;
	@Autowired
	private MdsItemMapper mdsItemMapper;
	@Autowired
    private MdsProcurementPlanMapper mdsProcurementPlanMapper;
	
	public MdsReplaceItem get(String id) {
		return super.get(id);
	}
	
	public List<MdsReplaceItem> findList(MdsReplaceItem mdsReplaceItem) {
		return super.findList(mdsReplaceItem);
	}
	@Transactional(readOnly=false)
	public Page<MdsProcurementPlan> findProPage(Page<MdsProcurementPlan>page,MdsProcurementPlan mdsProcurementPlan){
			//insertReplace();
		mdsProcurementPlan.setPage(page);
		page.setList(mdsReplaceItemMapper.findProList(mdsProcurementPlan));
		return page;
	}
	
	public Page<MdsReplaceItem> findPage(Page<MdsReplaceItem> page, MdsReplaceItem mdsReplaceItem) {
		return super.findPage(page, mdsReplaceItem);
	}
	public Page<MdsReplaceItem> findReplacePage(Page<MdsReplaceItem> page, MdsReplaceItem mdsReplaceItem){
		mdsReplaceItem.setPage(page);
		page.setList(mdsReplaceItemMapper.findReplaceList(mdsReplaceItem));
		return page;
	}
	@Transactional(readOnly = false)
	public void save(MdsReplaceItem mdsReplaceItem) {
		insertReplace(mdsReplaceItem);
		super.save(mdsReplaceItem);
	}
	@Transactional(readOnly = false)
	public void update(MdsReplaceItem mdsReplaceItem) {
		mdsReplaceItem.setReplaceState("50");
		 User user = UserUtils.getUser();
		  mdsReplaceItem.setApproveBy(user.getName());
		  mdsReplaceItem.setApproveDate(new Date());
		mdsReplaceItemMapper.updateReplaceState(mdsReplaceItem);
	}
	@Transactional(readOnly = false)
	public void delete(MdsReplaceItem mdsReplaceItem) {
		super.delete(mdsReplaceItem);
	}
	@Transactional(readOnly = false)
	public void insertReplace(MdsReplaceItem mdsReplaceItem) {
		//List<MdsProcurementPlan> proList = mdsReplaceItemMapper.findProList(mdsProcurementPlan);
		MdsProcurementPlan mpt = mdsProcurementPlanMapper.get(mdsReplaceItem.getId());
		//MdsReplaceItem mdsReplaceItem = new MdsReplaceItem();
		//for(MdsProcurementPlan mpt:proList) {
			//MdsReplaceItem m = mdsReplaceItemMapper.findById(mpt.getId());
				mdsReplaceItem.setId(IdGen.uuid());
				mdsReplaceItem.setMdsProcurementPlanId(mpt.getId());
				mdsReplaceItem.setBeforeReplaceItemId(mpt.getMdsItemId());
				mdsReplaceItem.setBeforeReplaceItemName(mpt.getItemName());
				mdsReplaceItem.setPurchaseMode(mpt.getProcurementWay());
				mdsReplaceItem.setReplaceState("30");
				mapper.insert(mdsReplaceItem);
		//}
	}
	
	@Transactional(readOnly=false)
	public void approveAgree(MdsReplaceItem mdsReplaceItem) {
		  MdsItem mdsItem = mdsItemMapper.get(mdsReplaceItem.getItemId());
		  MdsProcurementPlan mdsProcurementPlan = new MdsProcurementPlan();
		  if(mdsItem!=null) {
			  mdsProcurementPlan.setMdsItemId(mdsItem.getId());
			  mdsProcurementPlan.setItemCode(mdsItem.getItemCode());
			  mdsProcurementPlan.setItemName(mdsItem.getItemName());
			  mdsProcurementPlan.setNormalField(mdsItem.getNormalField());
			  mdsProcurementPlan.setModelnm(mdsItem.getModelNum());
			  mdsProcurementPlan.setSpecs(mdsItem.getSpecs());
			  mdsProcurementPlan.setClassify("0");
			  mdsProcurementPlan.setPurchaseAmount(Integer.parseInt(mdsReplaceItem.getAfterReplacePuraseNumber()));
			  mdsProcurementPlan.setId(mdsReplaceItem.getMdsProcurementPlanId());
			  mdsReplaceItemMapper.updateMdsPro(mdsProcurementPlan);
			  User user = UserUtils.getUser();
			  mdsReplaceItem.setApproveBy(user.getName());
			  mdsReplaceItem.setApproveDate(new Date());
			  mdsReplaceItem.setReplaceState("40");
			  mapper.update(mdsReplaceItem);
		  }
	}
	
}