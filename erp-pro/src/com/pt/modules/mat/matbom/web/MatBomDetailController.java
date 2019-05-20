/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matbom.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pt.common.json.AjaxJson;

import com.pt.core.web.BaseController;
import com.pt.common.utils.StringUtils;
import com.pt.modules.mat.matbom.entity.MatBom;
import com.pt.modules.mat.matbom.entity.MatBomDetail;
import com.pt.modules.mat.matbom.service.MatBomDetailService;
import com.pt.modules.mat.matbom.service.MatBomService;
import com.pt.modules.work.workorder.entity.WorkOrder;
import com.pt.modules.work.workorder.service.WorkOrderService;
import com.pt.modules.work.workorderdetail.entity.WorkOrderDetail;
import com.pt.modules.work.workorderdetail.service.WorkOrderDetailService;
import com.pt.modules.work.workorderson.entity.WorkOrderSon;
import com.pt.modules.work.workorderson.service.WorkOrderSonService;

/**
 * Bom管理Controller
 * @author 郑利
 * @version 2018-09-27
 */
@Controller
@RequestMapping(value = "${adminPath}/matbom/matBomDetail")
public class MatBomDetailController extends BaseController {
	@Autowired
	private MatBomService matBomService;
	@Autowired
	private MatBomDetailService matBomDetailService;
	@Autowired
	private WorkOrderDetailService workOrderDetailService;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private WorkOrderSonService workOrderSonService;
	@ModelAttribute
	@RequestMapping(value = "get")
	public MatBomDetail get(@RequestParam(required=false) String id) {
		MatBomDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = matBomDetailService.get(id);
		}
		if (entity == null){
			entity = new MatBomDetail();
		}
		return entity;
	}
	@ResponseBody
	@RequestMapping(value = "getDetail")
	public AjaxJson getDetail(@RequestParam(required=false) String id) {
		AjaxJson j = new AjaxJson();
		MatBomDetail entity = matBomDetailService.get(id);
		System.out.println(entity.getParentId());
		if(entity.getParentId().equals("0")){
			j.setSuccess(false);
			j.setMsg("不能对根数据修改!");
			return j;
		}
		j.setSuccess(true);
		return j;
	}
	/**
	 * Bom列表页面
	 */
	@RequestMapping(value = {"list", ""})
	public String list(MatBomDetail matBomDetail,  HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/mat/matbom/matBomDetailList";
	}

	/**
	 * 查看，增加，编辑Bom表单页面
	 */
	@RequestMapping(value = "form")
	public String form(String id,MatBomDetail matBomDetail, Model model) {
		if(id!=null &&id!=""){
			MatBomDetail matBomDetails=matBomDetailService.get(id);
			String parentId=matBomDetails.getParent().getId();
			MatBomDetail matParent=matBomDetailService.get(parentId);
			matBomDetails.setParent(matParent);
			
			model.addAttribute("matBomDetail", matBomDetails);
			String deId=matBomDetails.getParent().getMatBomId();	
			
			
			model.addAttribute("matBomId", deId);
			return "modules/mat/matbom/matBomForm";
		}
		
		if (matBomDetail.getParent()!=null && StringUtils.isNotBlank(matBomDetail.getParent().getId())){
			matBomDetail.setParent(matBomDetailService.get(matBomDetail.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(matBomDetail.getId())){
				MatBomDetail matBomDetailChild = new MatBomDetail();
				matBomDetailChild.setParent(new MatBomDetail(matBomDetail.getParent().getId()));
				List<MatBomDetail> list = matBomDetailService.findList(matBomDetail); 
				if (list.size() > 0){
					matBomDetail.setSort(list.get(list.size()-1).getSort());
					if (matBomDetail.getSort() != null){
						matBomDetail.setSort(matBomDetail.getSort() + 30);
					}
				}
			}
		}
		if (matBomDetail.getSort() == null){
			matBomDetail.setSort(30);
		}
		String deId=matBomDetail.getParent().getMatBomId();	
	
		
		model.addAttribute("matBomId", deId);
		return "modules/mat/matbom/matBomForm";
	}

	/**
	 * 保存Bom
	 */  
	@ResponseBody
	@RequestMapping(value = "save")
	public AjaxJson save(MatBomDetail matBomDetail, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, matBomDetail)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		MatBomDetail mb=matBomDetailService.getByIBId(matBomDetail);
		if(mb!=null){
			j.setSuccess(false);
			j.setMsg("该部件已存在！");
			return j;
		}
		MatBom matBom=matBomService.get(matBomDetail.getMatBomId());
		WorkOrder entity=new WorkOrder();
		entity.setSysMarketListId(matBom.getSysMarketListId());
		WorkOrder workOrder=workOrderService.gets(entity);
		WorkOrderSon workOrderSon=new WorkOrderSon();
		workOrderSon.setWorkOrderId(workOrder.getId());
		List<WorkOrderSon> workOrderSonList=workOrderSonService.findList(workOrderSon);
		String parentId=matBomDetail.getParentId();
		MatBomDetail matBomDetails=matBomDetailService.get(parentId);
		String parentItemId=matBomDetails.getMdsItemId();
		for(int i=0;i<workOrder.getNedNum();i++){
			WorkOrderDetail workOrderDetail=new WorkOrderDetail();
			workOrderDetail.setWorkOrderSonId(workOrderSonList.get(i).getId());
			workOrderDetail.setFinishPlan("0");
			workOrderDetail.setFinishState("0");
			workOrderDetail.setRemarks(parentItemId);
			workOrderDetail.setMatBomId(matBomDetail.getMatBomId());
			workOrderDetail.setWorkOrderId(workOrder.getId());
			workOrderDetail.setMdsItemId(matBomDetail.getMdsItemId());
			workOrderDetailService.save(workOrderDetail);
		}
		
		//新增或编辑表单保存
		matBomDetailService.save(matBomDetail);//保存
		j.setSuccess(true);
		j.put("matBomDetail", matBomDetail);
		j.setMsg("保存Bom成功");
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value = "getChildren")
	public List<MatBomDetail> getChildren(String parentId){
		if("-1".equals(parentId)){//如果是-1，没指定任何父节点，就从根节点开始查找
			parentId = "0";
		}
		return matBomDetailService.getChildren(parentId);
	}
	
	/**
	 * 删除Bom
	 */
	@ResponseBody
	@RequestMapping(value = "delete")
	public AjaxJson delete(MatBomDetail matBomDetail, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		matBomDetailService.delete(matBomDetail);
		j.setSuccess(true);
		j.setMsg("删除Bom成功");
		return j;
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false)String matBomId,@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		MatBomDetail matBomDetail=new MatBomDetail();
		matBomDetail.setMatBomId(matBomId);
		List<MatBomDetail> list = matBomDetailService.findList(matBomDetail);
		for (int i=0; i<list.size(); i++){
			MatBomDetail e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("text", e.getMdsItemName());
				if(StringUtils.isBlank(e.getParentId()) || "0".equals(e.getParentId())){
					map.put("parent", "#");
					Map<String, Object> state = Maps.newHashMap();
					state.put("opened", true);
					map.put("state", state);
				}else{
					map.put("parent", e.getParentId());
				}
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}