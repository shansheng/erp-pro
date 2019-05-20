/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprojectpcmplan.web;

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
import com.pt.modules.mds.mdsprojectpcmplan.entity.MdsProjectpcmplan;
import com.pt.modules.mds.mdsprojectpcmplan.service.MdsProjectpcmplanService;

/**
 * MdsProjectpcmplanController
 * @author cjx
 * @version 2018-09-19
 */
@Controller
@RequestMapping(value = "${adminPath}/mds/mdsprojectpcmplan")
public class MdsProjectpcmplanController extends BaseController {

	@Autowired
	private MdsProjectpcmplanService mdsProjectpcmplanService;
	
	@ModelAttribute
	public MdsProjectpcmplan get(@RequestParam(required=false) String sysMarketListId) {
		MdsProjectpcmplan entity = null;
		if (StringUtils.isNotBlank(sysMarketListId)){
			entity = mdsProjectpcmplanService.get(sysMarketListId);
		}
		if (entity == null){
			entity = new MdsProjectpcmplan();
		}
		return entity;
	}
	
	/**
	 * 项目采购计划列表页面
	 */
	@RequiresPermissions("mds:mdsprojectpcmplan:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdsprojectpcmplan/mdsProjectpcmplan";
	}
	
	/**
	 * 项目采购计划列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsprojectpcmplan:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsProjectpcmplan mdsProjectpcmplan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsProjectpcmplan> page = mdsProjectpcmplanService.findPage(new Page<MdsProjectpcmplan>(request, response), mdsProjectpcmplan); 
		return getBootstrapData(page);
	}
	

	/**
	 * 查看，编辑项目采购计划表单页面
	 */
	@RequiresPermissions(value={"mds:mdsprojectpcmplan:view","mds:mdsprojectpcmplan:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MdsProjectpcmplan mdsProjectpcmplan, Model model) {
		model.addAttribute("MdsProjectpcmplan", mdsProjectpcmplan);
		return "modules/mds/mdsprojectpcmplan/mdsProjectQuotaListForm";
	}
	
	/**
	 * 添加到采购计划
	 */
	@ResponseBody
	@RequiresPermissions(value={"mds:mdsprojectpcmplan:add"},logical=Logical.OR)
	@RequestMapping(value = "addProcurement")
	public AjaxJson addProcurement(MdsProjectpcmplan mdsProjectpcmplan,RedirectAttributes redirectAttributes){
		AjaxJson j = new AjaxJson();
		//添加采购计划
		mdsProjectpcmplanService.addProcurement(mdsProjectpcmplan);
		j.setMsg("添加到采购计划成功");
		return j;
	}

}