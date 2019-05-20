/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprocurementplanfinish.web;

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

import com.pt.common.utils.StringUtils;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.modules.mds.mdsprocurementplanfinish.entity.MdsProcurementPlanFinish;
import com.pt.modules.mds.mdsprocurementplanfinish.service.MdsProcurementPlanFinishService;

/**
 * 采购完成状态Controller
 * @author 郑利
 * @version 2018-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/mds/MdsProcurementPlanFinish")
public class MdsProcurementPlanFinishController extends BaseController {

	@Autowired
	private MdsProcurementPlanFinishService mdsProcurementPlanFinishService;
	
		@ModelAttribute
		public MdsProcurementPlanFinish get(@RequestParam(required=false) String id) {
			MdsProcurementPlanFinish entity = null;
			if (StringUtils.isNotBlank(id)){
				entity = mdsProcurementPlanFinishService.get(id);
			}
			if (entity == null){
				entity = new MdsProcurementPlanFinish();
			}
			return entity;
		}
	
	/**
	 * 采购计划申请列表页面
	 */
	@RequiresPermissions("mds:MdsProcurementPlanFinish:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdsprocurementplanfinish/mdsProcurementPlanFinishList";
	}
	
	/**
	 * 采购计划申请列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:MdsProcurementPlanFinish:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsProcurementPlanFinish MdsProcurementPlanFinish, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsProcurementPlanFinish> page = mdsProcurementPlanFinishService.findPage(new Page<MdsProcurementPlanFinish>(request, response), MdsProcurementPlanFinish); 
		return getBootstrapData(page);
	}
	
	/**
	 * 到货检验详细记录表
	 * @param model
	 * @return
	 */
	@RequiresPermissions("mds:MdsProcurementPlanFinish:view")
	@RequestMapping(value = "view")
	public String form(MdsProcurementPlanFinish mdsProcurementPlanFinish, Model model) {
		model.addAttribute("mdsProcurementPlanFinish", mdsProcurementPlanFinish);
		return "modules/mds/mdsprocurementplanfinish/mdsProcurementPlanFinishFrom";
	}
	
}