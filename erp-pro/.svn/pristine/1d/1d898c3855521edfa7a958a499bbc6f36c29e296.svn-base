/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysmarketmilestone.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mat.matbom.entity.MatBom;
import com.pt.modules.mat.matbom.entity.MatBomDetail;
import com.pt.modules.mat.matbom.service.MatBomDetailService;
import com.pt.modules.mat.matbom.service.MatBomService;
import com.pt.modules.mds.mdsquota.entity.MdsQuota;
import com.pt.modules.mds.mdsquota.service.MdsQuotaService;
import com.pt.modules.sys.sysmarket.entity.SysMarket;
import com.pt.modules.sys.sysmarket.entity.SysMarketList;

import com.pt.modules.sys.sysmarketmilestone.mapper.SysMarketMilestoneListMapper;
import com.pt.modules.sys.sysmarketmilestone.mapper.SysMarketMilestoneMapper;

import com.pt.modules.work.workorder.entity.WorkOrder;

import com.pt.modules.work.workorder.mapper.WorkOrderMapper;
import com.pt.modules.work.workorderson.entity.WorkOrderSon;
import com.pt.modules.work.workorderson.mapper.WorkOrderSonMapper;
import com.pt.common.utils.StringUtils;


/**
 * 销售订单下达Service
 * @author 郑利
 * @version 2018-09-08
 */
@Service
@Transactional(readOnly = true)
public class SysMarketMilestoneService extends CrudService<SysMarketMilestoneMapper, SysMarket> {

	@Autowired
	private SysMarketMilestoneListMapper sysMarketListMapper;
	@Autowired
	private SysMarketMilestoneMapper sysMarketMapper;
	@Autowired
	private WorkOrderMapper workOrderMapper;
	@Autowired
	private WorkOrderSonMapper workOrderSonMapper;
	@Autowired
	private MdsQuotaService mdsQuotaService;

@Autowired
private MatBomService matBomService;
@Autowired
private MatBomDetailService matBomDetailService;
	public SysMarket get(String id) {
		SysMarket sysMarket = super.get(id);
		sysMarket.setSysMarketListList(sysMarketListMapper.findList(new SysMarketList(sysMarket)));
		return sysMarket;
	}
	
	public List<SysMarket> findList(SysMarket sysMarket) {
		return super.findList(sysMarket);
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
	public void delete(SysMarket sysMarket) {
		super.delete(sysMarket);
		sysMarketListMapper.delete(new SysMarketList(sysMarket));
	}
	/**
	 * @param sysMarket
	 */
	@Transactional(readOnly = false)
	public void sysMarketDispatch(SysMarket SysMarket) {
		SysMarket.setAssignDate(new Date());
		sysMarketMapper.sysMarketDispatch(SysMarket);
		
		for (SysMarketList sysMarketList : SysMarket.getSysMarketListList()) {
			MdsQuota mdsQuota = new MdsQuota();
			mdsQuota.setSysMarketId(sysMarketList.getSysMarketId());
			mdsQuota.setMdsItemId(sysMarketList.getMdsItemId());
			mdsQuota.setSysMarketListId(sysMarketList.getId());
			mdsQuota.setState("10");
			mdsQuotaService.save(mdsQuota);

			WorkOrder workOrder=new WorkOrder();
			workOrder.setSysMarketId(sysMarketList.getSysMarketId());
			workOrder.setSysMarketListId(sysMarketList.getId());
			workOrder.setMdsItemId(sysMarketList.getMdsItemId());
			workOrder.setOrderState("0");
			workOrder.setProductionStartDate(SysMarket.getProductionStartDate());
			workOrder.setProductionEndDate(SysMarket.getProductionEndDate());
			workOrder.setLookIs("0");
			workOrderMapper.insert(workOrder);
			WorkOrder workOrders=workOrderMapper.get(workOrder);
			for(int i=0;i<workOrders.getNedNum();i++){
				WorkOrderSon workOrderSon=new WorkOrderSon();
				workOrderSon.setWorkOrderId(workOrder.getId());
				workOrderSon.setShipmentsState("0");
				workOrderSon.setSysMarketId(sysMarketList.getSysMarketId());
				workOrderSon.setSysMarketListId(sysMarketList.getId());
				workOrderSon.setMdsItemId(sysMarketList.getMdsItemId());
				workOrderSon.setOrderState("0");
				workOrderSonMapper.insert(workOrderSon);
			}
			
			
			MatBom matBom =new MatBom();
			MatBomDetail matBomDetail =new MatBomDetail();
			matBom.setSysMarketId(sysMarketList.getSysMarketId());
			matBom.setMdsItemId(sysMarketList.getMdsItemId());
			matBom.setSysMarketListId(sysMarketList.getId());
			matBom.setState("10");
			matBomDetail.setMdsItemId(sysMarketList.getMdsItemId());
			matBomDetailService.save(matBomDetail);
			String matBomDetailId=matBomDetailService.get(matBomDetail).getId();
			matBom.setMatBomDetailId(matBomDetailId);
			matBomService.save(matBom);
			String matBomId=matBomService.get(matBom).getId();
			matBomDetail.setMatBomId(matBomId);
			matBomDetailService.update(matBomDetail);

		}
	
	
		
		
	
	}
}