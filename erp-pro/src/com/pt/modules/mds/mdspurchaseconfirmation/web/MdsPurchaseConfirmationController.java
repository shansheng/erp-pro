/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdspurchaseconfirmation.web;

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
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.mds.mdspurchaseconfirmation.service.MdsPurchaseConfirmationService;
import com.pt.modules.sys.client.entity.Client;
import com.pt.modules.sys.client.service.ClientService;
import com.pt.modules.sys.sysmarket.entity.SysMarket;
import com.pt.modules.sys.sysmarket.service.SysMarketService;
import com.pt.modules.templan.templanprocurement.entity.TemplanProcurement;
import com.pt.modules.templan.templanprocurement.service.TemplanProcurementService;

/**
 * 采购员查看页
 * @author cjx
 * @version 2018-09-24
 */
@Controller
@RequestMapping(value = "${adminPath}/mds/mdspurchaseconfirmation")
public class MdsPurchaseConfirmationController extends BaseController {

	@Autowired
	private MdsPurchaseConfirmationService mdsPurchaseConfirmationService;
	
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
			entity = mdsPurchaseConfirmationService.get(id);
		}
		if (entity == null){
			entity = new MdsProcurementPlan();
		}
		return entity;
	}
	
	/**
	 * 采购计划申请列表页面
	 */
	@RequiresPermissions("mds:mdspurchaseconfirmation:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdspurchaseconfirmation/mdspurchaseconfirmationList";
	}
	
	/**
	 * 采购计划申请列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdspurchaseconfirmation:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsProcurementPlan mdsProcurementPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsProcurementPlan> page = mdsPurchaseConfirmationService.findPage(new Page<MdsProcurementPlan>(request, response), mdsProcurementPlan); 
		return getBootstrapData(page);
	}

	/**
	 * 批量驳回采购计划申请
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdspurchaseconfirmation:reject")
	@RequestMapping(value = "reject")
	public AjaxJson reject(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mdsPurchaseConfirmationService.reject(mdsPurchaseConfirmationService.get(id));
		}
		j.setMsg("驳回成功!");
		return j;
	}
	
	/**
	 * 批量确定供应商
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdspurchaseconfirmation:addIndent")
	@RequestMapping(value = "addIndent")
	public AjaxJson addIndent(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String entityArray[] =ids.split(",");
		/*List<MdsProcurementPlan> list = new ArrayList<MdsProcurementPlan>();
		for(String entityString : entityArray){
			try{
				MdsProcurementPlan mds = new MdsProcurementPlan(entityString,"");
				mds.preUpdate();
				list.add(mds);
			}catch(Exception e){
				j.setSuccess(false);
				j.setErrorCode("500");
				j.setMsg("确定供应商失败!");
				return j;
			}
		}*/
		for (String id : entityArray) {
			mdsPurchaseConfirmationService.addIndent(mdsPurchaseConfirmationService.get(id));
		}
		j.setSuccess(true);
		j.setMsg("确定供应商成功!");
		return j;
	}
	
	
	/**
	 * 销售订单信息显示页
	 */
	@RequiresPermissions("mds:mdspurchaseconfirmation:list")
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
	@RequiresPermissions("mds:mdspurchaseconfirmation:list")
	@RequestMapping(value = "temporary")
	public String temporary(String id, Model model) {
		//查询临时订单表中的数据
		TemplanProcurement entity = templanProcurementService.get(id);
		model.addAttribute("templanProcurement", entity);
		return "modules/mds/mdspcmplanprocurement/temporaryForm";
	}

	
}