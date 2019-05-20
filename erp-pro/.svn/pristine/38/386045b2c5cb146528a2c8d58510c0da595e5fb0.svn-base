/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsReplaceItem.web;

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
import com.pt.common.config.Global;
import com.pt.common.json.AjaxJson;
import com.pt.common.utils.DateUtils;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.modules.mds.mdsReplaceItem.entity.MdsReplaceItem;
import com.pt.modules.mds.mdsReplaceItem.service.MdsReplaceItemService;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.mds.mdsprocurementplan.service.MdsProcurementPlanService;


/**
 * 代料申请Controller
 * @author zjj
 * @version 2018-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/mds/mdsReplaceItem")
public class MdsReplaceItemController extends BaseController {

	@Autowired
	private MdsReplaceItemService mdsReplaceItemService;
	@Autowired
	private MdsProcurementPlanService mdsProcurementPlanService;
	
	@ModelAttribute
	public MdsReplaceItem get(@RequestParam(required=false) String id) {
		MdsReplaceItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsReplaceItemService.get(id);
		}
		if (entity == null){
			entity = new MdsReplaceItem();
		}
		return entity;
	}
	@ModelAttribute
	public MdsProcurementPlan getId(@RequestParam(required=false) String id) {
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
	 * 代料历史查询列表页面
	 */
	@RequiresPermissions("mds:mdsrepalceitem:mdsReplaceItem:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdsrepalceitem/mdsReplaceItemList";
	}
	
	/**
	 * 代料申请页面
	 * @return
	 */
	@RequiresPermissions("mds:mdsrepalceitem:mdsReplaceItem:applyList")
	@RequestMapping(value="applyList")
	public String applyList() {
		return "modules/mds/mdsrepalceitem/mdsReplaceItemApply";
	}
	/**
	 * 代料申请列表
	 * @param mdsProcurementPlan
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsrepalceitem:mdsReplaceItem:applyList")
	@RequestMapping(value="applyData")
	public Map<String,Object> applyData(MdsProcurementPlan mdsProcurementPlan,HttpServletRequest request,HttpServletResponse response,Model model){
		  Page<MdsProcurementPlan> page = mdsReplaceItemService.findProPage(new Page<MdsProcurementPlan>(request,response), mdsProcurementPlan);
		  return getBootstrapData(page);
	}
	/**
	 * 代料申请弹出页
	 * @return
	 */
	@RequiresPermissions("mds:mdsrepalceitem:mdsReplaceItem:apply")
	@RequestMapping(value="applyForm")
	public String applyForm(MdsReplaceItem mdsReplaceItem,Model model) {
		model.addAttribute(mdsReplaceItem);
		return "modules/mds/mdsrepalceitem/mdsReplaceItemApplyForm";
	}
	/**
	 * 代料审批
	 * @return
	 */
	@RequiresPermissions("mds:mdsrepalceitem:mdsReplaceItem:approve")
	@RequestMapping(value="approve")
	public String approve() {
		return "modules/mds/mdsrepalceitem/mdsReplaceItemApprove";
	}
	@ResponseBody
	@RequiresPermissions("mds:mdsrepalceitem:mdsReplaceItem:approve")
	@RequestMapping(value="approveList")
	public Map<String,Object> approveList(MdsReplaceItem mdsReplaceItem,HttpServletRequest request,HttpServletResponse response,Model model){
		  mdsReplaceItem.setReplaceState("30");
		  Page<MdsReplaceItem> page = mdsReplaceItemService.findPage(new Page<MdsReplaceItem>(request, response), mdsReplaceItem); 
		  return getBootstrapData(page);
	}
	/**
	 * 代料历史查询列表数据
	 */
	@ResponseBody
	@RequiresPermissions(value= {"mds:mdsrepalceitem:mdsReplaceItem:list","mds:mdsrepalceitem:mdsReplaceItem:approve"},logical=Logical.OR)
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsReplaceItem mdsReplaceItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsReplaceItem> page = mdsReplaceItemService.findReplacePage(new Page<MdsReplaceItem>(request, response), mdsReplaceItem); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑代料申请表单页面
	 */
	@RequiresPermissions(value={"mds:mdsrepalceitem:mdsReplaceItem:view","mds:mdsrepalceitem:mdsReplaceItem:add","mds:mdsrepalceitem:mdsReplaceItem:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MdsReplaceItem mdsReplaceItem, Model model) {
		model.addAttribute("mdsReplaceItem", mdsReplaceItem);
		return "modules/mds/mdsrepalceitem/mdsReplaceItemForm";
	}
	@ResponseBody
	@RequiresPermissions(value="mds:mdsrepalceitem:mdsReplaceItem:agree")
	@RequestMapping(value="agree")
    public AjaxJson approveAgree(MdsReplaceItem mdsReplaceItem) {
    	AjaxJson j = new AjaxJson();
    	try {
			mdsReplaceItemService.approveAgree(mdsReplaceItem);
			j.setMsg("申请已通过！");
		} catch (Exception e) {
			j.setMsg("操作失败！");
		}
    	return j;
    }
	@ResponseBody
	@RequiresPermissions(value="mds:mdsrepalceitem:mdsReplaceItem:reject")
	@RequestMapping(value="reject")
	public AjaxJson reject(MdsReplaceItem mdsReplaceItem) {
		AjaxJson j = new AjaxJson();
		try {
			mdsReplaceItemService.update(mdsReplaceItem);
			j.setMsg("申请已驳回！");
		} catch (Exception e) {
			j.setMsg("操作失败！");
		}
		return j;
	}
	/**
	 * 保存代料申请
	 */
	@ResponseBody
	@RequiresPermissions(value={"mds:mdsrepalceitem:mdsReplaceItem:add","mds:mdsrepalceitem:mdsReplaceItem:edit","mds:mdsrepalceitem:mdsReplaceItem:apply"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MdsReplaceItem mdsReplaceItem, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mdsReplaceItem)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		mdsReplaceItemService.save(mdsReplaceItem);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存代料申请成功");
		return j;
	}
	
	/**
	 * 删除代料申请
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsrepalceitem:mdsReplaceItem:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MdsReplaceItem mdsReplaceItem, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mdsReplaceItemService.delete(mdsReplaceItem);
		j.setMsg("删除代料申请成功");
		return j;
	}
	
	/**
	 * 批量删除代料申请
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsrepalceitem:mdsReplaceItem:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mdsReplaceItemService.delete(mdsReplaceItemService.get(id));
		}
		j.setMsg("删除代料申请成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsrepalceitem:mdsReplaceItem:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MdsReplaceItem mdsReplaceItem, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "代料申请"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MdsReplaceItem> page = mdsReplaceItemService.findPage(new Page<MdsReplaceItem>(request, response, -1), mdsReplaceItem);
    		new ExportExcel("代料申请", MdsReplaceItem.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出代料申请记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mds:mdsrepalceitem:mdsReplaceItem:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MdsReplaceItem> list = ei.getDataList(MdsReplaceItem.class);
			for (MdsReplaceItem mdsReplaceItem : list){
				try{
					mdsReplaceItemService.save(mdsReplaceItem);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条代料申请记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条代料申请记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入代料申请失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mds/mdsrepalceitem/mdsReplaceItem/?repage";
    }
	
	/**
	 * 下载导入代料申请数据模板
	 */
	@RequiresPermissions("mds:mdsrepalceitem:mdsReplaceItem:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "代料申请数据导入模板.xlsx";
    		List<MdsReplaceItem> list = Lists.newArrayList(); 
    		new ExportExcel("代料申请数据", MdsReplaceItem.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mds/mdsrepalceitem/mdsReplaceItem/?repage";
    }

}