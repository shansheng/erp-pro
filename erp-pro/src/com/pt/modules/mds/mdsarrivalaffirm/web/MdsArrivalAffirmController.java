/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsarrivalaffirm.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.pt.common.utils.DateUtils;
import com.pt.common.config.Global;
import com.pt.common.json.AjaxJson;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;
import com.pt.modules.mds.mdsarrivalaffirm.entity.MdsArrivalAffirm;
import com.pt.modules.mds.mdsarrivalaffirm.service.MdsArrivalAffirmService;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;

/**
 * 到货确认Controller
 * @author cjx
 * @version 2018-10-10
 */
@Controller
@RequestMapping(value = "${adminPath}/mds/mdsarrivalaffirm/mdsArrivalAffirm")
public class MdsArrivalAffirmController extends BaseController {

	@Autowired
	private MdsArrivalAffirmService mdsArrivalAffirmService;
	
	@ModelAttribute
	public MdsArrivalAffirm get(@RequestParam(required=false) String id) {
		MdsArrivalAffirm entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsArrivalAffirmService.get(id);
		}
		if (entity == null){
			entity = new MdsArrivalAffirm();
		}
		return entity;
	}
	
	/**
	 * 到货数量列表页面
	 */
	@RequiresPermissions("mds:mdsarrivalaffirm:mdsArrivalAffirm:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdsarrivalaffirm/mdsArrivalAffirmList";
	}
	
	/**
	 * 到货数量列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsarrivalaffirm:mdsArrivalAffirm:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsArrivalAffirm mdsArrivalAffirm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsArrivalAffirm> page = mdsArrivalAffirmService.findPage(new Page<MdsArrivalAffirm>(request, response), mdsArrivalAffirm); 
		return getBootstrapData(page);
	}
	
	/**
	 * 到货详情
	 * @param mdsArrivalAffirm
	 * @param model
	 * @return
	 */
	@RequiresPermissions("mds:mdsarrivalaffirm:mdsArrivalAffirm:view")
	@RequestMapping(value = "form")
	public String form(MdsArrivalAffirm mdsArrivalAffirm, Model model) {
		model.addAttribute("mdsArrivalAffirm", mdsArrivalAffirm);
		return "modules/mds/mdsarrivalaffirm/mdsArrivalAffirmForm";
	}

	/**
	 * 保存到货数量
	 */
	@ResponseBody
	@RequiresPermissions(value={"mds:mdsarrivalaffirm:mdsArrivalAffirm:add","mds:mdsarrivalaffirm:mdsArrivalAffirm:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(String date, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		String entityArray[] =date.split(",");
		List<MdsArrivalAffirm> list = new ArrayList<MdsArrivalAffirm>();
		for(String entityString : entityArray){
			MdsArrivalAffirm mds = new MdsArrivalAffirm(entityString,"");
			list.add(mds);
		}
		mdsArrivalAffirmService.save(list);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存到货数量成功");
		return j;
	}
}