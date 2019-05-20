/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderson.web;

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
import com.pt.modules.work.workorderson.entity.WorkOrderSon;
import com.pt.modules.work.workorderson.service.WorkOrderSonService;

/**
 * 工作计划Controller
 * @author 郑利
 * @version 2018-10-13
 */
@Controller
@RequestMapping(value = "${adminPath}/workorderson/workOrderSon")
public class WorkOrderSonController extends BaseController {

	@Autowired
	private WorkOrderSonService workOrderSonService;
	
	@ModelAttribute
	public WorkOrderSon get(@RequestParam(required=false) String id) {
		WorkOrderSon entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workOrderSonService.get(id);
		}
		if (entity == null){
			entity = new WorkOrderSon();
		}
		return entity;
	}
	
	/**
	 * 工作计划列表页面
	 */
	@RequiresPermissions("workorderson:workOrderSon:list")
	@RequestMapping(value = {"list", ""})
	public String list(String workOrderId,Model model) {
		model.addAttribute("workOrderId",workOrderId);
		return "modules/work/workorderson/workOrderSonList";
	}
	
		/**
	 * 工作计划列表数据
	 */
	@ResponseBody
	@RequiresPermissions("workorderson:workOrderSon:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(String workOrderId,WorkOrderSon workOrderSon, HttpServletRequest request, HttpServletResponse response, Model model) {
		workOrderSon.setWorkOrderId(workOrderId);
		Page<WorkOrderSon> page = workOrderSonService.findPage(new Page<WorkOrderSon>(request, response), workOrderSon); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑工作计划表单页面
	 */
	@RequiresPermissions(value={"workorderson:workOrderSon:view","workorderson:workOrderSon:add","workorderson:workOrderSon:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WorkOrderSon workOrderSon, Model model) {
		model.addAttribute("workOrderSon", workOrderSon);
		return "modules/work/workorderson/workOrderSonForm";
	}

	/**
	 * 保存工作计划
	 */
	@ResponseBody
	@RequiresPermissions(value={"workorderson:workOrderSon:add","workorderson:workOrderSon:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WorkOrderSon workOrderSon, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, workOrderSon)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		workOrderSonService.saves(workOrderSon);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存工作计划成功");
		return j;
	}
	
	/**
	 * 删除工作计划
	 */
	@ResponseBody
	@RequiresPermissions("workorderson:workOrderSon:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WorkOrderSon workOrderSon, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		workOrderSonService.delete(workOrderSon);
		j.setMsg("删除工作计划成功");
		return j;
	}
	
	/**
	 * 批量删除工作计划
	 */
	@ResponseBody
	@RequiresPermissions("workorderson:workOrderSon:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			workOrderSonService.delete(workOrderSonService.get(id));
		}
		j.setMsg("删除工作计划成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("workorderson:workOrderSon:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WorkOrderSon workOrderSon, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "工作计划"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WorkOrderSon> page = workOrderSonService.findPage(new Page<WorkOrderSon>(request, response, -1), workOrderSon);
    		new ExportExcel("工作计划", WorkOrderSon.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出工作计划记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("workorderson:workOrderSon:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WorkOrderSon> list = ei.getDataList(WorkOrderSon.class);
			for (WorkOrderSon workOrderSon : list){
				try{
					workOrderSonService.save(workOrderSon);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条工作计划记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条工作计划记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入工作计划失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/workorderson/workOrderSon/?repage";
    }
	
	/**
	 * 下载导入工作计划数据模板
	 */
	@RequiresPermissions("workorderson:workOrderSon:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工作计划数据导入模板.xlsx";
    		List<WorkOrderSon> list = Lists.newArrayList(); 
    		new ExportExcel("工作计划数据", WorkOrderSon.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/workorderson/workOrderSon/?repage";
    }

}