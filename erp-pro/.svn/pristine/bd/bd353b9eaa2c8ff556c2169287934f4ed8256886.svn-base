/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderretur.web;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.pt.modules.mat.matbom.entity.MatBom;
import com.pt.modules.mat.matbom.entity.MatBomDetail;
import com.pt.modules.mat.matbom.service.MatBomDetailService;
import com.pt.modules.mat.matbom.service.MatBomService;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.work.workorderdetail.entity.WorkOrderDetail;
import com.pt.modules.work.workorderretur.entity.WorkOrderRetur;
import com.pt.modules.work.workorderretur.entity.WorkOrderReturDetail;
import com.pt.modules.work.workorderretur.service.WorkOrderReturDetailService;
import com.pt.modules.work.workorderretur.service.WorkOrderReturService;
import com.pt.modules.work.workorderson.entity.WorkOrderSon;
import com.pt.modules.work.workorderson.service.WorkOrderSonService;


/**
 * 工作计划下达Controller
 * @author 郑利
 * @version 2018-10-13
 */
@Controller
@RequestMapping(value = "${adminPath}/WorkOrderReturDetail/WorkOrderReturDetail")
public class WorkOrderReturDetailController extends BaseController {

	@Autowired
	private WorkOrderReturDetailService workOrderDetailService;
	@Autowired
	private WorkOrderReturService workOrderSonService;
	@Autowired
	private MatBomDetailService matBomDetailService;


	@ModelAttribute
	public WorkOrderReturDetail get(@RequestParam(required=false) String id) {
		WorkOrderReturDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workOrderDetailService.get(id);
		}
		if (entity == null){
			entity = new WorkOrderReturDetail();
		}
		return entity;
	}
	
	/**
	 * 查看，增加，编辑工作计划表单页面
	 */
	@RequiresPermissions(value={"WorkOrderReturDetail:WorkOrderReturDetail:view","WorkOrderReturDetail:WorkOrderReturDetail:add","WorkOrderReturDetail:WorkOrderReturDetail:edit"},logical=Logical.OR)
	@RequestMapping(value = "form") 
	public String form(String workOrderSonId, Model model) {
		WorkOrderRetur workOrderSon=workOrderSonService.get(workOrderSonId);
		WorkOrderReturDetail WorkOrderReturDetail=new WorkOrderReturDetail();
		WorkOrderReturDetail.setWorkOrderSonId(workOrderSonId);
		List<WorkOrderReturDetail> workOrderDetailList=workOrderDetailService.findList(WorkOrderReturDetail);
		workOrderSon.setWorkOrderDetailList(workOrderDetailList);
		model.addAttribute("workOrderSon", workOrderSon);
		model.addAttribute("workOrderSonId", workOrderSonId);
		return "modules/work/workorderretur/workOrderReturDetailForm";
	}
	
	/**
	 * 工作计划列表页面
	 */
	@RequiresPermissions("WorkOrderReturDetail:WorkOrderReturDetail:list")
	@RequestMapping(value = {"lists", ""})
	public String lists(String workOrderId,String workOrderSonId, Model model) {
	
		model.addAttribute("workOrderId", workOrderId);
		model.addAttribute("workOrderSonId", workOrderSonId);
		return "modules/work/workorderretur/workOrderReturDetailLists";
	}
	
	/**
	 * 工作计划列表页面
	 */
	@RequiresPermissions("WorkOrderReturDetail:WorkOrderReturDetail:list")
	@RequestMapping(value = {"list", ""})
	public String list(String workOrderId,String workOrderSonId, Model model) {
		model.addAttribute("workOrderId", workOrderId);
		model.addAttribute("workOrderSonId", workOrderSonId);
		return "modules/work/workorderretur/workOrderReturDetailList";
	}
	
	/**
	 * 工作计划列表数据
	 */
	@ResponseBody
	@RequiresPermissions("WorkOrderReturDetail:WorkOrderReturDetail:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(String workOrderSonId,WorkOrderReturDetail WorkOrderReturDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		WorkOrderReturDetail.setWorkOrderSonId(workOrderSonId);
		Page<WorkOrderReturDetail> page = workOrderDetailService.findPage(new Page<WorkOrderReturDetail>(request, response), WorkOrderReturDetail); 
		List<WorkOrderReturDetail> list=page.getList();
		MatBomDetail matBomDetail=new MatBomDetail();
		List<WorkOrderReturDetail> newList=new ArrayList<>();
		List<WorkOrderReturDetail> newLisst=new ArrayList<>();
		for(int i=0;i<list.size();i++){
			matBomDetail.setMdsItemId(list.get(i).getMdsItemId());
			matBomDetail.setMatBomId(list.get(i).getMatBomId());
			MatBomDetail matBomDeta=matBomDetailService.getByItemBomId(matBomDetail);
			String[] str=matBomDeta.getParentIds().split(",");
			Integer inte=str.length-2;
			String mdsItemName=list.get(i).getMdsItemName();
			for(int a=0;a<inte;a++){
				mdsItemName="&nbsp;&nbsp;&nbsp;&nbsp;"+mdsItemName;
			}
			list.get(i).setMdsItemName(mdsItemName);
			if(inte==0){
				newList.add(list.get(i));
			}else{
				newLisst.add(list.get(i));
			}
		}
		int d=0;
		List<WorkOrderReturDetail> newLists=new ArrayList<>();
		for(int b=0;b<newList.size();b++){
			newLists.add(d, newList.get(b));
			String itemId=newLists.get(d).getMdsItemId();
			String bomId=newLists.get(d).getMatBomId();
			for(int c=0;c<newLisst.size();c++){
				if(newLisst.get(c).getRemarks().equals(itemId)&&newLisst.get(c).getMatBomId().equals(bomId)){
					
					newLists.add(d,newLisst.get(c));
					d++;
				}
			}
		}
		page.setList(newLists);
		return getBootstrapData(page);
	}



	/**
	 * 保存工作计划
	 */

	@ResponseBody
	@RequiresPermissions(value={"WorkOrderReturDetail:WorkOrderReturDetail:add","WorkOrderReturDetail:WorkOrderReturDetail:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(String date,String workOrderSonId, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		WorkOrderReturDetail WorkOrderReturDetail=new WorkOrderReturDetail();
		String[] list=date.split(",");
		for(int i=0;i<list.length;i++){
			String entityArray[]=list[i].split(":");
			String id=entityArray[0];
			String finishPlan=entityArray[1];
			WorkOrderReturDetail.setId(id);
			WorkOrderReturDetail.setFinishPlan(finishPlan);
			
			WorkOrderReturDetail.setUpdateBy(UserUtils.getUser());
			WorkOrderReturDetail.setUpdateDate(new Date());
			
			
			int fi=Integer.parseInt(finishPlan);
			if(fi<0){
				j.setSuccess(false);
				j.setMsg("工作进度不能小于0！");
				return j;
			}
			if(fi>100){
				j.setSuccess(false);
				j.setMsg("工作进度不能大于100！");
				return j;
			}
			if(fi==0){
				j.setSuccess(false);
				j.setMsg("工作进度为0不可提交！");
				return j;
			}
			if(!finishPlan.equals("0")){
				WorkOrderReturDetail.setRelBeginDate(new Date());
				WorkOrderReturDetail.setFinishState("1");
			}
			if(finishPlan.equals("100")){
				WorkOrderReturDetail.setFinishState("2");
				WorkOrderReturDetail.setRelEndDate(new Date());
				
			}
			
		
			workOrderDetailService.updateRetur(WorkOrderReturDetail);
		}
		List<WorkOrderReturDetail> DetailList=workOrderDetailService.getByWorkOrderSonId(workOrderSonId);
		String[] str=new String[100];
		for(int a=0;a<DetailList.size();a++){
			String finishPlan=DetailList.get(a).getFinishPlan();
			str[a]=finishPlan;
		}
		Integer inte=DetailList.size();
		Integer finishPlanNum=0;
		int integ=0;
		for(int b=0;b<DetailList.size();b++){
			integ=Integer.parseInt(str[b]);
			finishPlanNum+=integ;
		}
		int finishSche=finishPlanNum/inte;
		String finishsche=String.valueOf(finishSche)+"%";
		WorkOrderRetur workOrderRetur=new WorkOrderRetur();
		workOrderRetur.setFinishSche(finishsche);
		workOrderRetur.setId(workOrderSonId);
		workOrderSonService.updateFinishSche(workOrderRetur);
		
		j.setSuccess(true);
		j.setMsg("保存工作计划成功");
		return j;
}

	
	
	
		
		
		
	/**
	 * 删除工作计划
	 */
	@ResponseBody
	@RequiresPermissions("WorkOrderReturDetail:WorkOrderReturDetail:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WorkOrderReturDetail WorkOrderReturDetail, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		workOrderDetailService.delete(WorkOrderReturDetail);
		j.setMsg("删除工作计划成功");
		return j;
	}
	
	/**
	 * 批量删除工作计划
	 */
	@ResponseBody
	@RequiresPermissions("WorkOrderReturDetail:WorkOrderReturDetail:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			workOrderDetailService.delete(workOrderDetailService.get(id));
		}
		j.setMsg("删除工作计划成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("WorkOrderReturDetail:WorkOrderReturDetail:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WorkOrderReturDetail WorkOrderReturDetail, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "工作计划"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WorkOrderReturDetail> page = workOrderDetailService.findPage(new Page<WorkOrderReturDetail>(request, response, -1), WorkOrderReturDetail);
    		new ExportExcel("工作计划", WorkOrderReturDetail.class).setDataList(page.getList()).write(response, fileName).dispose();
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
	@RequiresPermissions("WorkOrderReturDetail:WorkOrderReturDetail:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WorkOrderReturDetail> list = ei.getDataList(WorkOrderReturDetail.class);
			for (WorkOrderReturDetail WorkOrderReturDetail : list){
				try{
					workOrderDetailService.save(WorkOrderReturDetail);
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
		return "redirect:"+Global.getAdminPath()+"/WorkOrderReturDetail/WorkOrderReturDetail/?repage";
    }
	
	/**
	 * 下载导入工作计划数据模板
	 */
	@RequiresPermissions("WorkOrderReturDetail:WorkOrderReturDetail:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工作计划数据导入模板.xlsx";
    		List<WorkOrderReturDetail> list = Lists.newArrayList(); 
    		new ExportExcel("工作计划数据", WorkOrderReturDetail.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/WorkOrderReturDetail/WorkOrderReturDetail/?repage";
    }

}