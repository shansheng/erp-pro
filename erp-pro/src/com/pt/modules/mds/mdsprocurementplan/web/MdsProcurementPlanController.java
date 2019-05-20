/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprocurementplan.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
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
import com.pt.modules.mds.mdsprocurementplan.entity.CollectEntity;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.mds.mdsprocurementplan.service.MdsProcurementPlanService;
import com.pt.modules.sys.client.entity.Client;
import com.pt.modules.sys.client.service.ClientService;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.sysmarket.entity.SysMarket;
import com.pt.modules.sys.sysmarket.service.SysMarketService;
import com.pt.modules.templan.templanprocurement.entity.TemplanProcurement;
import com.pt.modules.templan.templanprocurement.service.TemplanProcurementService;

/**
 * 采购计划申请Controller
 * @author cjx
 * @version 2018-09-24
 */
@Controller
@RequestMapping(value = "${adminPath}/mds/mdsProcurementPlan")
public class MdsProcurementPlanController extends BaseController {

	@Autowired
	private MdsProcurementPlanService mdsProcurementPlanService;
	
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
			entity = mdsProcurementPlanService.get(id);
		}
		if (entity == null){
			entity = new MdsProcurementPlan();
		}
		return entity;
	}
	
	/**
	 * 采购计划申请列表页面
	 */
	@RequiresPermissions("mds:mdsProcurementPlan:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdsprocurementplan/mdsProcurementPlanList";
	}
	
	/**
	 * 采购计划申请列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsProcurementPlan:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsProcurementPlan mdsProcurementPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsProcurementPlan> page = mdsProcurementPlanService.findPage(new Page<MdsProcurementPlan>(request, response), mdsProcurementPlan); 
		return getBootstrapData(page);
	}
	
	/**
	 * 保存采购计划申请
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsProcurementPlan:edit")
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
		mdsProcurementPlanService.save(list);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存采购计划申请成功");
		return j;
	}
	
	/**
	 * 编辑采购人信息
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsProcurementPlan:edit")
	@RequestMapping(value = "selectBuyer")
	public AjaxJson selectBuyer(String selectId,String ids,String name, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		String entityId[] =selectId.split(",");
		List<MdsProcurementPlan> list = new ArrayList<MdsProcurementPlan>();
		for(String id : entityId){
			try{
				MdsProcurementPlan mds = new MdsProcurementPlan(id);
				mds.setBuyer(new User(ids));
				mds.preUpdate();
				list.add(mds);
			}catch(Exception e){
				j.setSuccess(false);
				j.setErrorCode("500");
				j.setMsg("保存采购人失败");
				return j;
			}
		}
		mdsProcurementPlanService.saveBuyer(list);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存采购人成功");
		return j;
	}
	
	/**
	 * 批量提交采购计划申请
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsProcurementPlan:submit")
	@RequestMapping(value = "submit")
	public AjaxJson submit(String date, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String entityArray[] =date.split(",");
		for(String entityString : entityArray){
			try{
				MdsProcurementPlan mds = new MdsProcurementPlan(entityString,"");
				MdsProcurementPlan mds1 = mdsProcurementPlanService.get(mds.getId());
				if(mds1.getBuyer()!=null){
					//提交采购计划
					mdsProcurementPlanService.submit(mds);
				}else{
					j.setSuccess(false);
					j.setErrorCode("500");
					j.setMsg("保存采购计划失败,采购人未选取");
					return j;
				}
			}catch(Exception e){
				j.setSuccess(false);
				j.setErrorCode("500");
				j.setMsg("保存采购计划失败");
				return j;
			}
		}
		j.setMsg("提交采购计划申请成功");
		return j;
	}
	
	/**
	 * 汇总显示页面
	 * @param mdsProcurementPlan
	 * @param model
	 * @return
	 */
	@RequiresPermissions("mds:mdsProcurementPlan:collect")
	@RequestMapping(value = "collectAll")
	public String collectAll(MdsProcurementPlan mdsProcurementPlan, Model model) {
		CollectEntity collect = new CollectEntity(mdsProcurementPlan);
		/*//按照条件查找表中数据，物料信息相同时，采购数相加
		List<MdsProcurementPlan> list = mdsProcurementPlanService.findList(collect);*/
		model.addAttribute("collect", collect);
		return "modules/mds/mdsprocurementplan/collectForm";
	}
	
	/**
	 * 汇总显示页面数据
	 * mdsProcurementPlan 中有查询框中的数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsProcurementPlan:collect")
	@RequestMapping(value = "collectAllDate")
	public Map<String, Object> collectAllDate(CollectEntity collectEntity, Model model) {
		//按照条件查找表中数据，物料信息相同时，采购数相加
		List<MdsProcurementPlan> list = mdsProcurementPlanService.findList(collectEntity);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", list.size());
		return map;
	}
	
	/**
	 * 销售订单信息显示页
	 */
	@RequiresPermissions(value= {"mds:mdsProcurementPlan:list","mds:mdsrepalceitem:mdsReplaceItem:applyList"},logical=Logical.OR)
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
		return "modules/mds/mdsprocurementplan/projectForm";
	}
	
	
	
	/**
	 * 临时申请页显示
	 */
	@RequiresPermissions(value= {"mds:mdsProcurementPlan:list","mds:mdsrepalceitem:mdsReplaceItem:applyList"},logical=Logical.OR)
	@RequestMapping(value = "temporary")
	public String temporary(String id, Model model) {
		//查询临时订单表中的数据
		TemplanProcurement entity = templanProcurementService.get(id);
		model.addAttribute("templanProcurement", entity);
		return "modules/mds/mdsprocurementplan/temporaryForm";
	}
	/**
	 * 修改时间
	 * @param mdsProcurementPlan
	 * @param model
	 * @return
	 */
	@RequiresPermissions("mds:mdsProcurementPlan:update")
	@RequestMapping(value="updateDate")
	public String updateDate(MdsProcurementPlan mdsProcurementPlan,Model model){
		model.addAttribute("mdsProcurementPlan",mdsProcurementPlan);
		return "modules/mds/mdsprocurementplan/updateDateForm";
	}
	
	@RequiresPermissions(value="mds:mdsPurchaseSupplier:list")
	@RequestMapping(value="purchaseList")
	public String purchaseList() {
		return "modules/mds/mdsPurchaseSupplier/mdsPurchaseSupplier";
	}
	@ResponseBody
	@RequestMapping("updateRequireDate")
	public AjaxJson updateRequireDate(MdsProcurementPlan mdsProcurementPlan){
		AjaxJson ajaxJson = new AjaxJson();
		try {
			mdsProcurementPlanService.updateRequireDate(mdsProcurementPlan);
			ajaxJson.setMsg("修改成功");
		} catch (Exception e) {
			ajaxJson.setErrorCode("修改失败");
		}
		return ajaxJson;
	}
	/**
	 * 批量删除物料
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsProcurementPlan:delete")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
			mdsProcurementPlanService.deleteByLogic(ids);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setErrorCode("删除失败！");
		}
		return j;
	}
	
}