package com.pt.modules.mds.mdsPurchaseSupplier.web;

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

import com.pt.common.json.AjaxJson;
import com.pt.common.utils.StringUtils;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.modules.mds.mdsPurchaseSupplier.service.MdsPurchaseSupplierService;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;

@Controller
@RequestMapping(value="${adminPath}/mds/mdsPurchaseSupplier")
public class MdsPurchaseSupplierController extends BaseController {
	@Autowired
	private MdsPurchaseSupplierService mdsPurchaseSupplierService;
	
	@ModelAttribute
	public MdsProcurementPlan get(@RequestParam(required=false) String id) {
		MdsProcurementPlan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsPurchaseSupplierService.get(id);
		}
		if (entity == null){
			entity = new MdsProcurementPlan();
		}
		return entity;
	}
	/**
	 * 采购计划申请列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsPurchaseSupplier:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsProcurementPlan mdsProcurementPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsProcurementPlan> page = mdsPurchaseSupplierService.findPage(new Page<MdsProcurementPlan>(request, response), mdsProcurementPlan); 
		return getBootstrapData(page);
	}
	
	@RequestMapping(value="supplierForm")
	public String supplierForm(MdsProcurementPlan mdsProcurementPlan,Model model){
		 model.addAttribute("mdsProcurementPlan",mdsProcurementPlan);
		return "modules/mds/mdsPurchaseSupplier/mdsPurchaseSupplierForm";
	}
	
	@ResponseBody
	@RequestMapping(value="updateSupplier")
	public AjaxJson updateSupplier(MdsProcurementPlan mdsProcurementPlan) {
		AjaxJson j = new AjaxJson();
		try {
			mdsPurchaseSupplierService.updateSupplier(mdsProcurementPlan);
			 j.setMsg("操作成功");
		} catch (Exception e) {
			 j.setMsg("操作失败");
		}
		 return j;
	}
}
