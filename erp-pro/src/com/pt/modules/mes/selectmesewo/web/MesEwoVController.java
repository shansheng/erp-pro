/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.selectmesewo.web;

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
import com.pt.modules.mes.selectmesewo.entity.MesEwoV;
import com.pt.modules.mes.selectmesewo.service.MesEwoVService;

/**
 * 生产计划查询Controller
 * @author 郑利
 * @version 2018-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/selectmesewo/mesEwoV")
public class MesEwoVController extends BaseController {

	@Autowired
	private MesEwoVService mesEwoVService;
	
	@ModelAttribute
	public MesEwoV get(@RequestParam(required=false) String id) {
		MesEwoV entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mesEwoVService.get(id);
		}
		if (entity == null){
			entity = new MesEwoV();
		}
		return entity;
	}
	
	/**
	 * 生产计划查询列表页面
	 */
	@RequiresPermissions("selectmesewo:mesEwoV:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mes/selectmesewo/mesEwoVList";
	}
	
		/**
	 * 生产计划查询列表数据
	 */
	@ResponseBody
	@RequiresPermissions("selectmesewo:mesEwoV:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MesEwoV mesEwoV, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MesEwoV> page = mesEwoVService.findPage(new Page<MesEwoV>(request, response), mesEwoV); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑生产计划查询表单页面
	 */
	@RequiresPermissions(value={"selectmesewo:mesEwoV:view","selectmesewo:mesEwoV:add","selectmesewo:mesEwoV:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MesEwoV mesEwoV, Model model) {
		model.addAttribute("mesEwoV", mesEwoV);
		return "modules/mes/selectmesewo/mesEwoVForm";
	}

	/**
	 * 保存生产计划查询
	 */
	@ResponseBody
	@RequiresPermissions(value={"selectmesewo:mesEwoV:add","selectmesewo:mesEwoV:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MesEwoV mesEwoV, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mesEwoV)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		mesEwoVService.save(mesEwoV);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存生产计划查询成功");
		return j;
	}
	
	/**
	 * 删除生产计划查询
	 */
	@ResponseBody
	@RequiresPermissions("selectmesewo:mesEwoV:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MesEwoV mesEwoV, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mesEwoVService.delete(mesEwoV);
		j.setMsg("删除生产计划查询成功");
		return j;
	}
	
	/**
	 * 批量删除生产计划查询
	 */
	@ResponseBody
	@RequiresPermissions("selectmesewo:mesEwoV:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mesEwoVService.delete(mesEwoVService.get(id));
		}
		j.setMsg("删除生产计划查询成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("selectmesewo:mesEwoV:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MesEwoV mesEwoV, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "生产计划查询"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MesEwoV> page = mesEwoVService.findPage(new Page<MesEwoV>(request, response, -1), mesEwoV);
    		new ExportExcel("生产计划查询", MesEwoV.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出生产计划查询记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("selectmesewo:mesEwoV:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MesEwoV> list = ei.getDataList(MesEwoV.class);
			for (MesEwoV mesEwoV : list){
				try{
					mesEwoVService.save(mesEwoV);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条生产计划查询记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条生产计划查询记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入生产计划查询失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/selectmesewo/mesEwoV/?repage";
    }
	
	/**
	 * 下载导入生产计划查询数据模板
	 */
	@RequiresPermissions("selectmesewo:mesEwoV:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "生产计划查询数据导入模板.xlsx";
    		List<MesEwoV> list = Lists.newArrayList(); 
    		new ExportExcel("生产计划查询数据", MesEwoV.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/selectmesewo/mesEwoV/?repage";
    }

}