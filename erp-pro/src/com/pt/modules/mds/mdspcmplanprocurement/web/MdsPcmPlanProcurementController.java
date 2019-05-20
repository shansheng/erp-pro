/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdspcmplanprocurement.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pt.common.json.AjaxJson;
import com.pt.common.utils.StringUtils;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.modules.mds.mdspcmplanprocurement.service.MdsPcmPlanProcurementService;
import com.pt.modules.mds.mdsprocurementplan.entity.CollectEntity;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.sys.client.entity.Client;
import com.pt.modules.sys.client.service.ClientService;
import com.pt.modules.sys.sysmarket.entity.SysMarket;
import com.pt.modules.sys.sysmarket.service.SysMarketService;
import com.pt.modules.templan.templanprocurement.entity.TemplanProcurement;
import com.pt.modules.templan.templanprocurement.service.TemplanProcurementService;

/**
 * 采购计划申请部门领导审批Controller
 * @author cjx
 * @version 2018-09-24
 */
@Controller
@RequestMapping(value = "${adminPath}/mds/mdspcmplanprocurement")
public class MdsPcmPlanProcurementController extends BaseController {

	@Autowired
	private MdsPcmPlanProcurementService mdsPcmPlanProcurementService;
	
	//客户管理
		@Autowired
		private ClientService sysClientService;
		
		//销售订单
		@Autowired
		private SysMarketService sysMarketService;
		
		//临时订单
		@Autowired
		private TemplanProcurementService templanProcurementService;
	
	@ModelAttribute
	public MdsProcurementPlan get(@RequestParam(required=false) String id) {
		MdsProcurementPlan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsPcmPlanProcurementService.get(id);
		}
		if (entity == null){
			entity = new MdsProcurementPlan();
		}
		return entity;
	}
	
	/**
	 * 采购计划申请列表页面
	 */
	@RequiresPermissions("mds:mdsPcmPlanProcurement:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdspcmplanprocurement/mdsPcmPlanProcurementList";
	}
	
	/**
	 * 采购计划申请列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsPcmPlanProcurement:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsProcurementPlan mdsProcurementPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsProcurementPlan> page = mdsPcmPlanProcurementService.findPage(new Page<MdsProcurementPlan>(request, response), mdsProcurementPlan); 
		return getBootstrapData(page);
	}
	
	/**
	 * 保存采购计划申请
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsPcmPlanProcurement:edit")
	@RequestMapping(value = "save")
	public AjaxJson save(String date, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		String entityArray[] =date.split(",");
		List<MdsProcurementPlan> list = new ArrayList<MdsProcurementPlan>();
		for(String entityString : entityArray){
			try{
				MdsProcurementPlan mds = new MdsProcurementPlan(entityString,"");
				mds.preUpdate();
				list.add(mds);
			}catch(Exception e){
				j.setSuccess(false);
				j.setErrorCode("500");
				j.setMsg("保存采购计划失败");
				return j;
			}
		}
		mdsPcmPlanProcurementService.save(list);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存采购计划申请成功");
		return j;
	}
	

	/**
	 * 批量驳回采购计划申请
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsPcmPlanProcurement:reject")
	@RequestMapping(value = "reject")
	public AjaxJson reject(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mdsPcmPlanProcurementService.reject(mdsPcmPlanProcurementService.get(id));
		}
		j.setMsg("驳回采购计划申请成功");
		return j;
	}
	
	/**
	 * 批量下达采购订单
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsPcmPlanProcurement:addIndent")
	@RequestMapping(value = "addIndent")
	public AjaxJson addIndent(String date, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String entityArray[] =date.split(",");
		List<MdsProcurementPlan> list = new ArrayList<MdsProcurementPlan>();
		for(String entityString : entityArray){
			try{
				MdsProcurementPlan mds = new MdsProcurementPlan(entityString,"");
				mds.preUpdate();
				list.add(mds);
			}catch(Exception e){
				j.setSuccess(false);
				j.setErrorCode("500");
				j.setMsg("保存采购计划失败");
				return j;
			}
		}
		for (MdsProcurementPlan mdsProcurementPlan : list) {
			//下达采购计划
			mdsPcmPlanProcurementService.addIndent(mdsProcurementPlan);
		}
		mdsPcmPlanProcurementService.save(list);//编辑保存
		j.setSuccess(true);
		j.setMsg("下达采购计划申请成功");
		return j;
	}
	
	/**
	 * 汇总显示页面
	 * @param mdsProcurementPlan
	 * @param model
	 * @return
	 */
	@RequiresPermissions("mds:mdsPcmPlanProcurement:collect")
	@RequestMapping(value = "collectAll")
	public String collectAll(MdsProcurementPlan mdsProcurementPlan, Model model) {
		CollectEntity collect = new CollectEntity(mdsProcurementPlan);
		/*//按照条件查找表中数据，物料信息相同时，采购数相加
		List<MdsProcurementPlan> list = mdsProcurementPlanService.findList(collect);*/
		model.addAttribute("collect", collect);
		return "modules/mds/mdspcmplanprocurement/collectForm";
	}
	
	/**
	 * 汇总显示页面数据
	 * mdsProcurementPlan 中有查询框中的数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsPcmPlanProcurement:collect")
	@RequestMapping(value = "collectAllDate")
	public Map<String, Object> collectAllDate(CollectEntity collectEntity, Model model) {
		//按照条件查找表中数据，物料信息相同时，采购数相加
		List<MdsProcurementPlan> list = mdsPcmPlanProcurementService.findList(collectEntity);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", list.size());
		return map;
	}
	
	/**
	 * 销售订单信息显示页
	 */
	@RequiresPermissions("mds:mdsPcmPlanProcurement:list")
	@RequestMapping(value = "project")
	public String project(SysMarket sysMarket, Model model) {
		//查询销售订单中id对应的信息。
		sysMarket = sysMarketService.get(sysMarket.getId());
		if(sysMarket.getClientId()!=null&&!sysMarket.getClientId().equals("")){
			Client client=sysClientService.get(sysMarket.getClientId());
			sysMarket.setPhone(client.getPhone());
			sysMarket.setClientManager(client.getClientManager());
			sysMarket.setAddress(client.getAddress());
			sysMarket.setRegion(client.getRegion());
		}
		model.addAttribute("sysMarket", sysMarket);
		return "modules/mds/mdspcmplanprocurement/projectForm";
	}
	
	/**
	 * 临时申请页显示
	 */
	@RequiresPermissions("mds:mdsPcmPlanProcurement:list")
	@RequestMapping(value = "temporary")
	public String temporary(String id, Model model) {
		//查询临时订单表中的数据
		TemplanProcurement entity = templanProcurementService.get(id);
		model.addAttribute("templanProcurement", entity);
		return "modules/mds/mdspcmplanprocurement/temporaryForm";
	}

	
}