/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorder.web;

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
import com.pt.modules.mat.matbom.entity.MatBomDetail;
import com.pt.modules.mat.matbom.service.MatBomDetailService;
import com.pt.modules.work.workorder.entity.WorkOrder;
import com.pt.modules.work.workorder.service.WorkOrderService;

/**
 * 工作订单下达Controller
 * @author 郑利
 * @version 2018-10-09
 */
@Controller
@RequestMapping(value = "${adminPath}/workorder/workOrder")
public class WorkOrderController extends BaseController {

	@Autowired
	private WorkOrderService workOrderService;

	@ModelAttribute
	public WorkOrder get(@RequestParam(required=false) String id) {
		WorkOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workOrderService.get(id);
		}
		if (entity == null){
			entity = new WorkOrder();
		}
		return entity;
	}
	
	/**
	 * 工作订单列表页面
	 */
	@RequiresPermissions("workorder:workOrder:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/work/workorder/workOrderList";
	}
	
		/**
	 * 工作订单列表数据
	 */
	@ResponseBody
	@RequiresPermissions("workorder:workOrder:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WorkOrder workOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WorkOrder> page = workOrderService.findPage(new Page<WorkOrder>(request, response), workOrder); 
		return getBootstrapData(page);
	}

	/**
 * 工作订单列表数据
 */
@ResponseBody
@RequiresPermissions("workorder:workOrder:list")
@RequestMapping(value = "detailData")
public Map<String, Object> detailData(String orderId,WorkOrder workOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
	workOrder.setId(orderId);
	Page<WorkOrder> page = workOrderService.findPage(new Page<WorkOrder>(request, response), workOrder); 
	List<WorkOrder> list=new ArrayList<WorkOrder>(100);
	Integer num=page.getList().get(0).getNedNum();
	for(int i=0;i<num;i++){
		list.add(i, page.getList().get(0));
	}
	page.setList(list);

	return getBootstrapData(page);
}

	/**
	 * 查看，增加，编辑工作订单表单页面
	 */
	@RequiresPermissions(value={"workorder:workOrder:view","workorder:workOrder:add","workorder:workOrder:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WorkOrder workOrder, Model model) {
		
		if(StringUtils.isBlank(workOrder.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		Integer nedNum=workOrder.getNedNum();
		String orderId=workOrder.getId();
		String mdsItemId=workOrder.getMdsItemId();
		if(nedNum==1){
			model.addAttribute("orderId", orderId);
			
			
			return "modules/work/workorder/workOrderDetailForm";
		}else{
			model.addAttribute("workOrderId", orderId);
			return "modules/work/workorderson/workOrderSonList";
		}
		
	}

	
	
	/**
	 * 查看，增加，编辑工作订单表单页面
	 */
	@RequiresPermissions(value={"workorder:workOrder:view","workorder:workOrder:add","workorder:workOrder:edit"},logical=Logical.OR)
	@RequestMapping(value = "DetailForm")
	public String DetailForm(WorkOrder workOrder, Model model) {
		
		if(StringUtils.isBlank(workOrder.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		
		String orderId=workOrder.getId();
		String mdsItemId=workOrder.getMdsItemId();
		model.addAttribute("orderId", orderId);
		
		return "modules/work/workorder/workOrderDetailForm";
		
		
	}
	




	/**
	 * 保存工作订单
	 */
	@RequiresPermissions(value={"workorder:workOrder:add","workorder:workOrder:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(WorkOrder workOrder, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, workOrder)){
			return form(workOrder, model);
		}
		//新增或编辑表单保存
		workOrderService.save(workOrder);//保存
		addMessage(redirectAttributes, "保存工作订单成功");
		return "redirect:"+Global.getAdminPath()+"/workorder/workOrder/?repage";
	}
	
	/**
	 * 删除工作订单
	 */
	@ResponseBody
	@RequiresPermissions("workorder:workOrder:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WorkOrder workOrder, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		workOrderService.delete(workOrder);
		j.setMsg("删除工作订单成功");
		return j;
	}
	
	/**
	 * 批量删除工作订单
	 */
	@ResponseBody
	@RequiresPermissions("workorder:workOrder:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			workOrderService.delete(workOrderService.get(id));
		}
		j.setMsg("删除工作订单成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("workorder:workOrder:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WorkOrder workOrder, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "工作订单"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WorkOrder> page = workOrderService.findPage(new Page<WorkOrder>(request, response, -1), workOrder);
    		new ExportExcel("工作订单", WorkOrder.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出工作订单记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("workorder:workOrder:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WorkOrder> list = ei.getDataList(WorkOrder.class);
			for (WorkOrder workOrder : list){
				try{
					workOrderService.save(workOrder);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条工作订单记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条工作订单记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入工作订单失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/workorder/workOrder/?repage";
    }
	
	/**
	 * 下载导入工作订单数据模板
	 */
	@RequiresPermissions("workorder:workOrder:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工作订单数据导入模板.xlsx";
    		List<WorkOrder> list = Lists.newArrayList(); 
    		new ExportExcel("工作订单数据", WorkOrder.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/workorder/workOrder/?repage";
    }

}