/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workordersynthesize.web;

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
import com.mysql.fabric.xmlrpc.base.Array;
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
import com.pt.modules.mat.matbom.entity.MatBomTable;
import com.pt.modules.mat.matbom.service.MatBomDetailService;
import com.pt.modules.mat.matbom.service.MatBomService;
import com.pt.modules.mat.matbom.service.MatBomTableService;
import com.pt.modules.mds.mdsarrivalcheckout.entity.MdsArrivalCheckout;
import com.pt.modules.mds.mdsarrivalcheckout.service.MdsArrivalCheckoutService;
import com.pt.modules.mds.mdsprocurementplan.entity.MdsProcurementPlan;
import com.pt.modules.mds.mdsprocurementplan.service.MdsProcurementPlanService;
import com.pt.modules.mds.mdsquota.entity.MdsQuota;
import com.pt.modules.mds.mdsquota.entity.MdsQuotaDetail;
import com.pt.modules.mds.mdsquota.service.MdsQuotaDetailService;
import com.pt.modules.mds.mdsquota.service.MdsQuotaService;
import com.pt.modules.work.workordersynthesize.entity.WorkOrderSynthesize;
import com.pt.modules.work.workordersynthesize.service.WorkOrderSynthesizeService;

/**
 * 工作计划Controller
 * @author 郑利
 * @version 2018-10-13
 */
@Controller
@RequestMapping(value = "${adminPath}/WorkOrderSynthesize/WorkOrderSynthesize")
public class WorkOrderSynthesizeController extends BaseController {

	@Autowired
	private WorkOrderSynthesizeService workOrderSonService;
	@Autowired
	private MdsQuotaService mdsQuotaService;
	@Autowired
	private MdsQuotaDetailService mdsQuotaDetailService;
	@Autowired
	private MatBomService matBomService;
	@Autowired
	private MdsProcurementPlanService mdsProcurementPlanService;
	@Autowired
	private MdsArrivalCheckoutService mdsArrivalCheckoutService;
	@Autowired
	private MatBomTableService matBomTableService;
	@Autowired
	private MatBomDetailService matBomDetailService;
	@ModelAttribute
	public WorkOrderSynthesize get(@RequestParam(required=false) String id) {
		WorkOrderSynthesize entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workOrderSonService.get(id);
		}
		if (entity == null){
			entity = new WorkOrderSynthesize();
		}
		return entity;
	}
	/**
	 * 主页查看bom表单页面
	 */
	@RequiresPermissions("WorkOrderSynthesize:WorkOrderSynthesize:matBom")
	@RequestMapping(value = "matBom")
	public String matBom(String id,String clientId, Model model) {
		WorkOrderSynthesize WorkOrderSynthesize=workOrderSonService.get(id);
		MatBom matBom=new MatBom();
		matBom.setSysMarketId(WorkOrderSynthesize.getSysMarketId());
		matBom.setMdsItemId(WorkOrderSynthesize.getMdsItemId());
		MatBom matBoms=matBomService.getByMarketItemId(matBom);
		String sysMarketListId=matBoms.getSysMarketListId();
		String matBomId=matBoms.getId();
		
		model.addAttribute("clientId", clientId);
		model.addAttribute("matBomId", matBomId);
		model.addAttribute("sysMarketListId", sysMarketListId);
		return "modules/work/workordersynthesize/matBomListView";
	}
	
	
	
	/**
	 * mat_bom_table列表数据
	 */
	@ResponseBody
	@RequiresPermissions("WorkOrderSynthesize:WorkOrderSynthesize:matBom")
	@RequestMapping(value = "bomTableData")
	public Map<String, Object> bomTableData(String matBomId, HttpServletRequest request, HttpServletResponse response, Model model) {
		MatBomDetail matBomDetail=new MatBomDetail();
		matBomDetail.setMatBomId(matBomId);
		Page<MatBomDetail> pages=matBomDetailService.findPage(new Page<MatBomDetail>(request, response), matBomDetail);
		List<MatBomDetail> lists=pages.getList();
		String matBomDetailId="";
		for(int i=0;i<lists.size();i++){
			if(lists.get(i).getParentId().equals("0")){
				 matBomDetailId=lists.get(i).getId();
				
			}
		}
		MatBomTable matBomTable=new MatBomTable();
		matBomTable.setMatBomDetailId(matBomDetailId);
		Page<MatBomTable> page = matBomTableService.findPage(new Page<MatBomTable>(request, response), matBomTable); 
		List<MatBomTable> list=page.getList();
		for(int i=0;i<list.size();i++){
			MatBomTable matBomTables=list.get(i);
			if(matBomTables.getMdsItemName()==null||matBomTables.getMdsItemName()==""){
				matBomTables.setMdsItemCode(matBomTables.getItemCode());
				matBomTables.setMdsItemName(matBomTables.getItemName());
				matBomTables.setMdsItemspecs(matBomTables.getItemspecs());
				matBomTables.setMdsItemmodelNum(matBomTables.getItemmodelNum());
				list.set(i, matBomTables);
				
			}
		}
		page.setList(list);
		return getBootstrapData(page);
	}

	
	
	
	/**
	 * 主页查看材料定额表单页面
	 */
	@RequiresPermissions("WorkOrderSynthesize:WorkOrderSynthesize:show")
	@RequestMapping(value = "show")
	public String show(String id,String clientId, Model model) {
		
		WorkOrderSynthesize WorkOrderSynthesize=workOrderSonService.get(id);
		MdsQuota mdsQuota=new MdsQuota();
		mdsQuota.setSysMarketId(WorkOrderSynthesize.getSysMarketId());
		mdsQuota.setMdsItemId(WorkOrderSynthesize.getMdsItemId());
		MdsQuota mdsQuotas=mdsQuotaService.getByMarketItemId(mdsQuota);

		String mdsQuotaId=mdsQuotas.getId();
		
		
		model.addAttribute("clientId", clientId);
		model.addAttribute("mdsQuotaId", mdsQuotaId);
	
		return "modules/work/workordersynthesize/mdsQuotaDetailView";
	}
	
	/**
	 * 显示第二主页数据
	 * 材料定额详情列表数据
	 */
	@ResponseBody
	@RequiresPermissions("WorkOrderSynthesize:WorkOrderSynthesize:show")
	@RequestMapping(value = "QuotaData")
	public Map<String, Object> QuotaData(MdsQuotaDetail mdsQuotaDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<MdsQuotaDetail> page = mdsQuotaDetailService.findPage(new Page<MdsQuotaDetail>(request, response), mdsQuotaDetail); 
		
		return getBootstrapData(page);
	}
	/**
	 * 工作计划列表页面
	 */
	@RequiresPermissions("WorkOrderSynthesize:WorkOrderSynthesize:list")
	@RequestMapping(value = {"list", ""})
	public String list(String workOrderId,String clientId,Model model) {
		WorkOrderSynthesize WorkOrderSynthesize=new WorkOrderSynthesize();
		WorkOrderSynthesize.setClientId(clientId);
		model.addAttribute("WorkOrderSynthesize",WorkOrderSynthesize);
		model.addAttribute("workOrderId",workOrderId);
		return "modules/work/workordersynthesize/workOrderSynthesizeList";
	}
	
		/**
	 * 工作计划列表数据
	 */
	@ResponseBody
	@RequiresPermissions("WorkOrderSynthesize:WorkOrderSynthesize:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(String workOrderId,String clientId,WorkOrderSynthesize WorkOrderSynthesize, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(!clientId.equals("")&&clientId!=null){
			WorkOrderSynthesize.setClientId(clientId);
		}
		WorkOrderSynthesize.setWorkOrderId(workOrderId);
		
		Page<WorkOrderSynthesize> page = workOrderSonService.findPage(new Page<WorkOrderSynthesize>(request, response), WorkOrderSynthesize); 
		List<WorkOrderSynthesize> list=page.getList();
		List<WorkOrderSynthesize> lists=new ArrayList<WorkOrderSynthesize>(list.size());
		for(int i=0;i<list.size();i++){
			String finishSche=list.get(i).getFinishSche();
			if(finishSche != null){
				if(finishSche.equals("0%")){
					list.get(i).setFinishSche("未开工");
				}
				if(finishSche.equals("100%")){
					list.get(i).setFinishSche("已完成");
				}
			}
			lists.add(i, list.get(i));
			
		}
		page.setList(lists);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑工作计划表单页面
	 */
	
	@RequestMapping(value = "arrival")
	public String arrival(String id, Model model) {
		WorkOrderSynthesize WorkOrderSynthesize=workOrderSonService.get(id);
		String sysMarketId=WorkOrderSynthesize.getSysMarketId();
		MdsProcurementPlan mdsProcurement= mdsProcurementPlanService.getByMarketId(sysMarketId);
		String mdsProcurmentPlanId=mdsProcurement.getId();
		MdsArrivalCheckout arrival=mdsArrivalCheckoutService.getByMdsProcurmentPlanId(mdsProcurmentPlanId);
		model.addAttribute("arrival", arrival);
		return "modules/work/workordersynthesize/workOrderArrivalForm";
	}
	
	
	@RequestMapping(value = "mdsPlan")
	public String mdsPlan(String id, Model model) {
		WorkOrderSynthesize WorkOrderSynthesize=workOrderSonService.get(id);
		String sysMarketId=WorkOrderSynthesize.getSysMarketId();
		MdsProcurementPlan mdsProcurement= mdsProcurementPlanService.getByMarketId(sysMarketId);

		model.addAttribute("mdsProcurement", mdsProcurement);
		return "modules/work/workordersynthesize/workOrderSynthesizeForm";
	}

	
	
	/**
	 * 删除工作计划
	 */
	@ResponseBody
	@RequiresPermissions("WorkOrderSynthesize:WorkOrderSynthesize:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WorkOrderSynthesize WorkOrderSynthesize, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		workOrderSonService.delete(WorkOrderSynthesize);
		j.setMsg("删除工作计划成功");
		return j;
	}
	
	/**
	 * 批量删除工作计划
	 */
	@ResponseBody
	@RequiresPermissions("WorkOrderSynthesize:WorkOrderSynthesize:del")
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
	@RequiresPermissions("WorkOrderSynthesize:WorkOrderSynthesize:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WorkOrderSynthesize WorkOrderSynthesize, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "工作计划"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WorkOrderSynthesize> page = workOrderSonService.findPage(new Page<WorkOrderSynthesize>(request, response, -1), WorkOrderSynthesize);
    		new ExportExcel("工作计划", WorkOrderSynthesize.class).setDataList(page.getList()).write(response, fileName).dispose();
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
	@RequiresPermissions("WorkOrderSynthesize:WorkOrderSynthesize:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WorkOrderSynthesize> list = ei.getDataList(WorkOrderSynthesize.class);
			for (WorkOrderSynthesize WorkOrderSynthesize : list){
				try{
					workOrderSonService.save(WorkOrderSynthesize);
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
		return "redirect:"+Global.getAdminPath()+"/WorkOrdersynthesize/WorkOrderSynthesize/?repage";
    }
	
	/**
	 * 下载导入工作计划数据模板
	 */
	@RequiresPermissions("WorkOrderSynthesize:WorkOrderSynthesize:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工作计划数据导入模板.xlsx";
    		List<WorkOrderSynthesize> list = Lists.newArrayList(); 
    		new ExportExcel("工作计划数据", WorkOrderSynthesize.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/WorkOrdersynthesize/WorkOrderSynthesize/?repage";
    }

}