/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysqueclass.web;

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
import com.pt.modules.sys.sysqueclass.entity.SysQueClass;
import com.pt.modules.sys.sysqueclass.service.SysQueClassService;

/**
 * 问题分类管理Controller
 * @author 郑利
 * @version 2018-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/sysqueclass/sysQueClass")
public class SysQueClassController extends BaseController {

	@Autowired
	private SysQueClassService sysQueClassService;
	
	@ModelAttribute
	public SysQueClass get(@RequestParam(required=false) String id) {
		SysQueClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysQueClassService.get(id);
		}
		if (entity == null){
			entity = new SysQueClass();
		}
		return entity;
	}
	
	/**
	 * 问题分类列表页面
	 */
	@RequiresPermissions("sysqueclass:sysQueClass:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/sys/sysqueclass/sysQueClassList";
	}
	
		/**
	 * 问题分类列表数据
	 */
	@ResponseBody
	@RequiresPermissions("sysqueclass:sysQueClass:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SysQueClass sysQueClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysQueClass> page = sysQueClassService.findPage(new Page<SysQueClass>(request, response), sysQueClass); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑问题分类表单页面
	 */
	@RequiresPermissions(value={"sysqueclass:sysQueClass:view","sysqueclass:sysQueClass:add","sysqueclass:sysQueClass:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(SysQueClass sysQueClass, Model model) {
		model.addAttribute("sysQueClass", sysQueClass);
		return "modules/sys/sysqueclass/sysQueClassForm";
	}

	/**
	 * 保存问题分类
	 */
	@ResponseBody
	@RequiresPermissions(value={"sysqueclass:sysQueClass:add","sysqueclass:sysQueClass:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(SysQueClass sysQueClass, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, sysQueClass)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		sysQueClassService.save(sysQueClass);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存问题分类成功");
		return j;
	}
	
	/**
	 * 删除问题分类
	 */
	@ResponseBody
	@RequiresPermissions("sysqueclass:sysQueClass:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SysQueClass sysQueClass, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sysQueClassService.delete(sysQueClass);
		j.setMsg("删除问题分类成功");
		return j;
	}
	
	/**
	 * 批量删除问题分类
	 */
	@ResponseBody
	@RequiresPermissions("sysqueclass:sysQueClass:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			sysQueClassService.delete(sysQueClassService.get(id));
		}
		j.setMsg("删除问题分类成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("sysqueclass:sysQueClass:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SysQueClass sysQueClass, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "问题分类"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SysQueClass> page = sysQueClassService.findPage(new Page<SysQueClass>(request, response, -1), sysQueClass);
    		new ExportExcel("问题分类", SysQueClass.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出问题分类记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("sysqueclass:sysQueClass:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SysQueClass> list = ei.getDataList(SysQueClass.class);
			for (SysQueClass sysQueClass : list){
				try{
					sysQueClassService.save(sysQueClass);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条问题分类记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条问题分类记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入问题分类失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sysqueclass/sysQueClass/?repage";
    }
	
	/**
	 * 下载导入问题分类数据模板
	 */
	@RequiresPermissions("sysqueclass:sysQueClass:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "问题分类数据导入模板.xlsx";
    		List<SysQueClass> list = Lists.newArrayList(); 
    		new ExportExcel("问题分类数据", SysQueClass.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sysqueclass/sysQueClass/?repage";
    }

}