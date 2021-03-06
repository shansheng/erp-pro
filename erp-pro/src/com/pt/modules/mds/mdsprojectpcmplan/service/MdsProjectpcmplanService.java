/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprojectpcmplan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.mds.mdsprocurementplan.mapper.MdsProcurementPlanMapper;
import com.pt.modules.mds.mdsprojectpcmplan.entity.MdsProjectQuotaList;
import com.pt.modules.mds.mdsprojectpcmplan.entity.MdsProjectpcmplan;
import com.pt.modules.mds.mdsprojectpcmplan.mapper.MdsProjectQuotaListMapper;
import com.pt.modules.mds.mdsprojectpcmplan.mapper.MdsProjectpcmplanMapper;
import com.pt.modules.mds.mdsquota.entity.MdsQuotaDetail;
import com.pt.modules.mds.mdsquota.mapper.MdsQuotaDetailMapper;

/**
 * MdsProjectpcmplanService
 * @author cjx
 * @version 2018-09-19
 */
@Service
@Transactional(readOnly = true)
public class MdsProjectpcmplanService extends CrudService<MdsProjectpcmplanMapper, MdsProjectpcmplan> {

	@Autowired
	private MdsProjectQuotaListMapper mdsProjectQuotaListMapper;
	@Autowired
	private MdsProcurementPlanMapper mdsProcurementPlanMapper;
	@Autowired
	private MdsQuotaDetailMapper mdsQuotaDetailMapper;
	
	public MdsProjectpcmplan get(String id) {
		//获取当前产品的项目类型
		MdsProjectpcmplan mdsProjectpcmplan = super.get(id);
		//按照项目类型查询不同的表
		List<MdsProjectQuotaList> mdsProjectQuotaLists  = new ArrayList<>();
		//销售订单
		if("0".equals(mdsProjectpcmplan.getContractClass())){
			mdsProjectpcmplan = mapper.getQuota(mdsProjectpcmplan);
			mdsProjectQuotaLists = mdsProjectQuotaListMapper.findList(new MdsProjectQuotaList(mdsProjectpcmplan));
		//临时销售订单
		}else{
			mdsProjectpcmplan = mapper.getTemporary(mdsProjectpcmplan);
			mdsProjectQuotaLists = mdsProjectQuotaListMapper.findTemporaryList(new MdsProjectQuotaList(mdsProjectpcmplan));
		}
		for(MdsProjectQuotaList mdsProjectQuotaList : mdsProjectQuotaLists){
			if("0".equals(mdsProjectQuotaList.getState())){
				mdsProjectQuotaList.setState("未添加");
			}else{
				mdsProjectQuotaList.setState("已添加");
			}
		}
		mdsProjectpcmplan.setMdsProjectQuotaList(mdsProjectQuotaLists);
		return mdsProjectpcmplan;
	}
	
	public List<MdsProjectpcmplan> findList(MdsProjectpcmplan mdsProjectpcmplan) {
		return super.findList(mdsProjectpcmplan);
	}
	@Transactional(readOnly = false)
	public Page<MdsProjectpcmplan> findPage(Page<MdsProjectpcmplan> page, MdsProjectpcmplan MdsProjectpcmplan) {
		Page<MdsProjectpcmplan> pageDate = super.findPage(page, MdsProjectpcmplan);
		List<MdsProjectpcmplan> list = pageDate.getList();
		
		 for(int i=0;i<list.size();i++){  
			 MdsProjectpcmplan mdsProjectpcmplan2 = list.get(i);
			 if("1".equals(mdsProjectpcmplan2.getContractClass())){
					List<MdsProjectQuotaList> mdsProjectQuotaLists = mdsProjectQuotaListMapper.findTemporaryList(new MdsProjectQuotaList(mdsProjectpcmplan2));
					if(mdsProjectQuotaLists.size()==0){
						mdsProjectpcmplan2.setState("1");
						//修改_market_list中的状态,表示该产品已经添加到采购计划中
						mapper.update(mdsProjectpcmplan2);
						list.remove(i);
					}
				}
			 if("0".equals(mdsProjectpcmplan2.getContractClass())){
					List<MdsProjectQuotaList> mdsProjectQuotaLists = mdsProjectQuotaListMapper.findList(new MdsProjectQuotaList(mdsProjectpcmplan2));
					if(mdsProjectQuotaLists.size()==0){
						mdsProjectpcmplan2.setState("1");
						//修改_market_list中的状态,表示该产品已经添加到采购计划中
						mapper.update(mdsProjectpcmplan2);
						list.remove(i);
					}
				}
	        }
		 
		pageDate.setList(list);
		return pageDate;
	}
	
	
	@Transactional(readOnly = false)
	public void addProcurement (MdsProjectpcmplan mdsProjectpcmplan){
		mdsProjectpcmplan.setState("1");
		List<MdsProcurementPlan> mpplist = new ArrayList<>();
		List<MdsQuotaDetail> mqdList = new ArrayList<MdsQuotaDetail>();
//		User user = UserUtils.getUser();
		//根据获取的列添加采购计划对象,用来插入数据
		for (MdsProjectQuotaList mdsProjectQuotaList : mdsProjectpcmplan.getMdsProjectQuotaList()) {
			MdsProcurementPlan mdsProcurementPlan = new MdsProcurementPlan(mdsProjectQuotaList);
			mdsProcurementPlan.preInsert();
			/*if (StringUtils.isNotBlank(user.getId())){
				mdsProcurementPlan.setCreateBy(user);
				mdsProcurementPlan.setUpdateBy(user);
			}
			mdsProcurementPlan.setCreateDate(new Date());
			mdsProcurementPlan.setUpdateDate(new Date());*/
			mdsProcurementPlan.setClassify(mdsProjectQuotaListMapper.findClassify(mdsProcurementPlan.getClassify()));
			mpplist.add(mdsProcurementPlan);
			mqdList.add(new MdsQuotaDetail(mdsProjectQuotaList));
		}
		//修改sys_market_list中的状态,表示该产品已经添加到采购计划中
		mapper.update(mdsProjectpcmplan);
		//修改mds_quota_detail中add_procurement的值,表示已经添加到采购计划
		if(!mqdList.isEmpty()){
			mdsQuotaDetailMapper.updateAddProcurement(mqdList);
		}
		//将采购信息添加到mds_procurementplan表中
		if(!mpplist.isEmpty()){
			mdsProcurementPlanMapper.insertProjectList(mpplist);
		}
	}
	
}