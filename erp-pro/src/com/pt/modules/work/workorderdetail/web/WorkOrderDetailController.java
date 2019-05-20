/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderdetail.web;


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
import com.google.common.collect.Maps;
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
import com.pt.modules.work.workorderdetail.entity.WorkOrderDetail;
import com.pt.modules.work.workorderdetail.service.WorkOrderDetailService;
import com.pt.modules.work.workorderretur.entity.WorkOrderReturDetail;
import com.pt.modules.work.workorderson.entity.WorkOrderSon;
import com.pt.modules.work.workorderson.service.WorkOrderSonService;

/**
 * 工作计划下达Controller
 * @author 郑利
 * @version 2018-10-13
 */
@Controller
@RequestMapping(value = "${adminPath}/workorderdetail/workOrderDetail")
public class WorkOrderDetailController extends BaseController {

	@Autowired
	private WorkOrderDetailService workOrderDetailService;
	@Autowired
	private WorkOrderSonService workOrderSonService;
	@Autowired
	private MatBomDetailService matBomDetailService;
	@ModelAttribute
	public WorkOrderDetail get(@RequestParam(required=false) String id) {
		WorkOrderDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workOrderDetailService.get(id);
		}
		if (entity == null){
			entity = new WorkOrderDetail();
		}
		return entity;
	}
	
	/**
	 * 查看，增加，编辑工作计划表单页面
	 */
	@RequiresPermissions(value={"workorderdetail:workOrderDetail:view","workorderdetail:workOrderDetail:add","workorderdetail:workOrderDetail:edit"},logical=Logical.OR)
	@RequestMapping(value = "form") 
	public String form(String workOrderSonId, Model model) {
		WorkOrderSon workOrderSon=workOrderSonService.get(workOrderSonId);
		WorkOrderDetail workOrderDetail=new WorkOrderDetail();
		workOrderDetail.setWorkOrderSonId(workOrderSonId);
		List<WorkOrderDetail> workOrderDetailList=workOrderDetailService.findList(workOrderDetail);
		workOrderSon.setWorkOrderDetailList(workOrderDetailList);
		model.addAttribute("workOrderSon", workOrderSon);
		model.addAttribute("workOrderSonId", workOrderSonId);
		return "modules/work/workorderdetail/workOrderDetailForm";
	}
	
	/**
	 * 工作计划列表页面
	 */
	@RequiresPermissions("workorderdetail:workOrderDetail:list")
	@RequestMapping(value = {"lists", ""})
	public String lists(String workOrderId,String workOrderSonId, Model model) {
	
		model.addAttribute("workOrderId", workOrderId);
		model.addAttribute("workOrderSonId", workOrderSonId);
		return "modules/work/workorderdetail/workOrderDetailLists";
	}
	
	/**
	 * 工作计划列表页面
	 */
	@RequiresPermissions("workorderdetail:workOrderDetail:list")
	@RequestMapping(value = {"list", ""})
	public String list(String workOrderId,String workOrderSonId, Model model) {
		model.addAttribute("workOrderId", workOrderId);
		if(workOrderSonId==null){
			WorkOrderSon workOrderSon=workOrderSonService.getByWorkOrderId(workOrderId);
			workOrderSonId=workOrderSon.getId();
			model.addAttribute("Stat", "0");
		}
		model.addAttribute("Stat", "1");
		model.addAttribute("workOrderSonId", workOrderSonId);
		return "modules/work/workorderdetail/workOrderDetailList";
	}
	
	/**
	 * 工作计划列表数据
	 */
	@ResponseBody
	@RequiresPermissions("workorderdetail:workOrderDetail:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(String workOrderSonId,WorkOrderDetail workOrderDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		workOrderDetail.setWorkOrderSonId(workOrderSonId);
		Page<WorkOrderDetail> page = workOrderDetailService.findPage(new Page<WorkOrderDetail>(request, response), workOrderDetail); 
		
		List<WorkOrderDetail> list=page.getList();
		MatBomDetail matBomDetail=new MatBomDetail();
		int len = list.size() ;
		List<WorkOrderDetail> newList=new ArrayList<>(len);
		List<WorkOrderDetail> newLisst=new ArrayList<>(len);
		for(int i=0;i < len;i++){
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
		List<WorkOrderDetail> newLists=new ArrayList<>();
		for(int b=0;b<newList.size();b++){
			newLists.add(d,newList.get(b));
			String itemId=newLists.get(d).getMdsItemId();
			String bomId=newLists.get(d).getMatBomId();
			//newLists.add(newList.get(b));
			for(int c=0;c<newLisst.size();c++){
				if(newLisst.get(c).getRemarks().equals(itemId)&&newLisst.get(c).getMatBomId().equals(bomId)){
					
					newLists.add(d,newLisst.get(c));
					d++;
				}
			}
		}
			page.setList(newLists);
			System.out.println("newLists.size()" +newLists.size() );
			System.out.println("newLists." + newLists.toString());
		return getBootstrapData(page);
	}

	

	/**
	 * 保存工作计划
	 */
	@ResponseBody
	@RequiresPermissions(value={"workorderdetail:workOrderDetail:add","workorderdetail:workOrderDetail:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(String date, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		//String entityArray[] =date.split(":");
		WorkOrderDetail workOrderDetail=new WorkOrderDetail();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
		
		String[] list=date.split(",");
		for(int i=0;i<list.length;i++){
			String entityArray[]=list[i].split(":");
			String id=entityArray[0];
			Date planStartDate=ft.parse(entityArray[1]);
			Date planEndDate=ft.parse(entityArray[2]);
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd"); //格式化为 hhmmss
			int d1Number = Integer.parseInt(f.format(planStartDate).toString()); //将第一个时间格式化后转为int
			int d2Number = Integer.parseInt(f.format(planEndDate).toString()); //将第二个时间格式化后转为int
			if(d1Number==0){
				j.setSuccess(false);
				j.setMsg("开始时间必填!");
				return j;
			}
			if(d2Number==0){
				j.setSuccess(false);
				j.setMsg("结束时间必填!");
				return j;
			}
			
			if(d1Number>d2Number){
				j.setSuccess(false);
				j.setMsg("开始时间不能大于结束时间!");
				return j;
			}
			
			
			workOrderDetail.setId(id);
			workOrderDetail.setPlanStartDate(planStartDate);
			workOrderDetail.setPlanEndDate(planEndDate);
			workOrderDetail.setFinishPlan("0");
			workOrderDetail.setFinishState("0");
		    workOrderDetailService.save(workOrderDetail);
		
			//修改下达工作状态
			WorkOrderDetail workOrderDetails=workOrderDetailService.get(id);
			String workOrderSonId=workOrderDetails.getWorkOrderSonId();
			//未下达的物料数
			int allLen = workOrderDetailService.count(workOrderSonId);
			WorkOrderSon workOrderSon = workOrderSonService.get(workOrderSonId);
			if(allLen == 0) {
				workOrderSon.setOrderState("1");//全部下达
				workOrderSonService.disPatchById(workOrderSon);
			} else {
				workOrderSon.setOrderState("2");//部分下达
				workOrderSonService.disPatchById(workOrderSon);
			}
			
			
			
			
			
			
		}
		

		j.setSuccess(true);
		j.setMsg("保存工作计划成功");
		return j;
	}
	
	/**
	 * 删除工作计划
	 */
	@ResponseBody
	@RequiresPermissions("workorderdetail:workOrderDetail:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WorkOrderDetail workOrderDetail, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		workOrderDetailService.delete(workOrderDetail);
		j.setMsg("删除工作计划成功");
		return j;
	}
	
	/**
	 * 批量删除工作计划
	 */
	@ResponseBody
	@RequiresPermissions("workorderdetail:workOrderDetail:del")
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
	@RequiresPermissions("workorderdetail:workOrderDetail:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WorkOrderDetail workOrderDetail, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "工作计划"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WorkOrderDetail> page = workOrderDetailService.findPage(new Page<WorkOrderDetail>(request, response, -1), workOrderDetail);
    		new ExportExcel("工作计划", WorkOrderDetail.class).setDataList(page.getList()).write(response, fileName).dispose();
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
	@RequiresPermissions("workorderdetail:workOrderDetail:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WorkOrderDetail> list = ei.getDataList(WorkOrderDetail.class);
			for (WorkOrderDetail workOrderDetail : list){
				try{
					workOrderDetailService.save(workOrderDetail);
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
		return "redirect:"+Global.getAdminPath()+"/workorderdetail/workOrderDetail/?repage";
    }
	
	/**
	 * 下载导入工作计划数据模板
	 */
	@RequiresPermissions("workorderdetail:workOrderDetail:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工作计划数据导入模板.xlsx";
    		List<WorkOrderDetail> list = Lists.newArrayList(); 
    		new ExportExcel("工作计划数据", WorkOrderDetail.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/workorderdetail/workOrderDetail/?repage";
    }

}