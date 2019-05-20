/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderretur.web;

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
import com.pt.modules.work.workorderretur.entity.WorkOrderRetur;
import com.pt.modules.work.workorderretur.service.WorkOrderReturService;



/**
 * 工作计划Controller
 * @author 郑利
 * @version 2018-10-13
 */
@Controller
@RequestMapping(value = "${adminPath}/WorkOrderRetur/WorkOrderRetur")
public class WorkOrderReturController extends BaseController {

	@Autowired
	private WorkOrderReturService workOrderSonService;
	
	@ModelAttribute
	public WorkOrderRetur get(@RequestParam(required=false) String id) {
		WorkOrderRetur entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workOrderSonService.get(id);
		}
		if (entity == null){
			entity = new WorkOrderRetur();
		}
		return entity;
	}
	
	/**
	 * 工作计划列表页面
	 */
	@RequiresPermissions("WorkOrderRetur:WorkOrderRetur:list")
	@RequestMapping(value = {"list", ""})
	public String list(String workOrderId,Model model) {
		model.addAttribute("workOrderId",workOrderId);
		return "modules/work/workorderretur/workOrderReturList";
	}
	
		/**
	 * 工作计划列表数据
	 */
	@ResponseBody
	@RequiresPermissions("WorkOrderRetur:WorkOrderRetur:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(String workOrderId,WorkOrderRetur WorkOrderRetur, HttpServletRequest request, HttpServletResponse response, Model model) {
		WorkOrderRetur.setWorkOrderId(workOrderId);
		Page<WorkOrderRetur> page = workOrderSonService.findPage(new Page<WorkOrderRetur>(request, response), WorkOrderRetur); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑工作计划表单页面
	 */
	@RequiresPermissions(value={"WorkOrderRetur:WorkOrderRetur:view","WorkOrderRetur:WorkOrderRetur:add","WorkOrderRetur:WorkOrderRetur:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WorkOrderRetur WorkOrderRetur, Model model) {
		model.addAttribute("WorkOrderRetur", WorkOrderRetur);
		return "modules/work/workorderretur/workOrderReturForm";
	}

	/**
	 * 保存工作计划
	 */
	@ResponseBody
	@RequiresPermissions(value={"WorkOrderRetur:WorkOrderRetur:add","WorkOrderRetur:WorkOrderRetur:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WorkOrderRetur WorkOrderRetur, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, WorkOrderRetur)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		workOrderSonService.saves(WorkOrderRetur);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存工作计划成功");
		return j;
	}
	
	/**
	 * 删除工作计划
	 */
	@ResponseBody
	@RequiresPermissions("WorkOrderRetur:WorkOrderRetur:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WorkOrderRetur WorkOrderRetur, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		workOrderSonService.delete(WorkOrderRetur);
		j.setMsg("删除工作计划成功");
		return j;
	}
	
	/**
	 * 大屏幕看板中各生产状态下合同的数量
	 */
	@ResponseBody
	@RequestMapping(value = "statsOfWO")
	public AjaxJson statsOfWO(RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String data = workOrderSonService.statsOfWO();
		String msg = "{'categories':'[未生产,生产中,生产完成]','data':'"+data+"'}";
		j.setMsg(msg);
		return j;
	}
	
	/**
	 * 大屏幕看板中各产品的生产状态
	 */
	@ResponseBody
	@RequestMapping(value = "getOfProduct")
	public AjaxJson getOfProduct(RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		List<String> list = workOrderSonService.getOfProduct();
		if(list.isEmpty()){
			j.setSuccess(false);
			return j;
		}
		String msg = "{\"itemName\": \""+list.get(0)+"\",\"item1\": \""+list.get(1)+"\",\"item2\": \""+list.get(2)+"\"}";
		j.setMsg(msg);
		return j;
	}
	
	/**
	 * 批量删除工作计划
	 */
	@ResponseBody
	@RequiresPermissions("WorkOrderRetur:WorkOrderRetur:del")
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
	@RequiresPermissions("WorkOrderRetur:WorkOrderRetur:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WorkOrderRetur WorkOrderRetur, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "工作计划"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WorkOrderRetur> page = workOrderSonService.findPage(new Page<WorkOrderRetur>(request, response, -1), WorkOrderRetur);
    		new ExportExcel("工作计划", WorkOrderRetur.class).setDataList(page.getList()).write(response, fileName).dispose();
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
	@RequiresPermissions("WorkOrderRetur:WorkOrderRetur:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WorkOrderRetur> list = ei.getDataList(WorkOrderRetur.class);
			for (WorkOrderRetur WorkOrderRetur : list){
				try{
					workOrderSonService.save(WorkOrderRetur);
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
		return "redirect:"+Global.getAdminPath()+"/WorkOrderRetur/WorkOrderRetur/?repage";
    }
	
	/**
	 * 下载导入工作计划数据模板
	 */
	@RequiresPermissions("WorkOrderRetur:WorkOrderRetur:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工作计划数据导入模板.xlsx";
    		List<WorkOrderRetur> list = Lists.newArrayList(); 
    		new ExportExcel("工作计划数据", WorkOrderRetur.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/WorkOrderRetur/WorkOrderRetur/?repage";
    }

}