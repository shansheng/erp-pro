/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.templan.templandepartment.web;

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
import com.pt.modules.templan.templandepartment.entity.TemplanDepartment;
import com.pt.modules.templan.templandepartment.service.TemplanDepartmentService;

/**
 * 临时计划部门审核Controller
 * @author cjx
 * @version 2018-09-05
 */
@Controller
@RequestMapping(value = "${adminPath}/templan/templandepartment/templanDepartment")
public class TemplanDepartmentController extends BaseController {

	@Autowired
	private TemplanDepartmentService templanDepartmentService;
	
	@ModelAttribute
	public TemplanDepartment get(@RequestParam(required=false) String id) {
		TemplanDepartment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = templanDepartmentService.get(id);
		}
		if (entity == null){
			entity = new TemplanDepartment();
		}
		return entity;
	}
	
	/**
	 * 部门领导审核列表页面
	 */
	@RequiresPermissions("templan:templandepartment:templanDepartment:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/templan/templandepartment/templanDepartmentList";
	}
	
		/**
	 * 部门领导审核列表数据
	 */
	@ResponseBody
	@RequiresPermissions("templan:templandepartment:templanDepartment:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(TemplanDepartment templanDepartment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TemplanDepartment> page = templanDepartmentService.findPage(new Page<TemplanDepartment>(request, response), templanDepartment); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑部门领导审核表单页面
	 */
	@RequiresPermissions(value={"templan:templandepartment:templanDepartment:view","templan:templandepartment:templanDepartment:add","templan:templandepartment:templanDepartment:reject"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(TemplanDepartment templanDepartment, Model model) {
		model.addAttribute("templanDepartment", templanDepartment);
		return "modules/templan/templandepartment/templanDepartmentForm";
	}

	/**
	 * 部门领导审核同意
	 */
	@ResponseBody
	@RequiresPermissions(value={"templan:templandepartment:templanDepartment:add"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(TemplanDepartment templanDepartment, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, templanDepartment)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//同意表单
		templanDepartmentService.save(templanDepartment);//保存		
		j.setSuccess(true);
		j.setMsg("部门领导审核通过");
		return j;
	}
	
	/**
	 * 部门领导驳回
	 */
	@ResponseBody
	@RequiresPermissions(value={"templan:templandepartment:templanDepartment:reject"},logical=Logical.OR)
	@RequestMapping(value = "doreject")
	public AjaxJson reject(TemplanDepartment templanDepartment, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, templanDepartment)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//驳回表单
		templanDepartmentService.reject(templanDepartment);//驳回	
		j.setSuccess(true);
		j.setMsg("部门领导驳回完毕");
		return j;
	}
	
	/**
	 * 保存表单页面(同意或驳回)
	 */
	@ResponseBody
	@RequiresPermissions(value={"templan:templandepartment:templanDepartment:reject","templan:templandepartment:templanDepartment:add"},logical=Logical.OR)
	@RequestMapping(value = "saveTable")
	public AjaxJson saveTable(TemplanDepartment templanDepartment, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, templanDepartment)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//驳回表单
		templanDepartmentService.saveTable(templanDepartment);//
		j.setSuccess(true);
		j.setMsg("部门领导审核完毕");
		return j;
	}
	
	/**
	 * 删除部门领导审核
	 */
	@ResponseBody
	@RequiresPermissions("templan:templandepartment:templanDepartment:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(TemplanDepartment templanDepartment, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		templanDepartmentService.delete(templanDepartment);
		j.setMsg("删除部门领导审核成功");
		return j;
	}
	
	/**
	 * 批量删除部门领导审核
	 */
	@ResponseBody
	@RequiresPermissions("templan:templandepartment:templanDepartment:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			templanDepartmentService.delete(templanDepartmentService.get(id));
		}
		j.setMsg("删除部门领导审核成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("templan:templandepartment:templanDepartment:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(TemplanDepartment templanDepartment, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "部门领导审核"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TemplanDepartment> page = templanDepartmentService.findPage(new Page<TemplanDepartment>(request, response, -1), templanDepartment);
    		new ExportExcel("部门领导审核", TemplanDepartment.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出部门领导审核记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public TemplanDepartment detail(String id) {
		return templanDepartmentService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("templan:templandepartment:templanDepartment:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TemplanDepartment> list = ei.getDataList(TemplanDepartment.class);
			for (TemplanDepartment templanDepartment : list){
				try{
					templanDepartmentService.save(templanDepartment);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条部门领导审核记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条部门领导审核记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入部门领导审核失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/templan/templandepartment/templanDepartment/?repage";
    }
	
	/**
	 * 下载导入部门领导审核数据模板
	 */
	@RequiresPermissions("templan:templandepartment:templanDepartment:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "部门领导审核数据导入模板.xlsx";
    		List<TemplanDepartment> list = Lists.newArrayList(); 
    		new ExportExcel("部门领导审核数据", TemplanDepartment.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/templan/templandepartment/templanDepartment/?repage";
    }
	

}