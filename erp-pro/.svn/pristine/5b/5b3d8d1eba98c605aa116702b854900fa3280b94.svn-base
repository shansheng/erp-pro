/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matoutrequisition.web;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.pt.common.json.AjaxJson;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.common.utils.StringUtils;

import com.pt.modules.mat.matrequisition.entity.MatRequisition;
import com.pt.modules.mat.matrequisition.entity.MatRequisitionList;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.templan.templanprocurement.entity.TemplanProcurementList;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainInventory;
import com.pt.modules.wms.wmsmaininventory.service.WmsMainInventoryService;
import com.pt.modules.mat.matoutrequisition.service.MatOutRequisitionService;


/**
 * 领料单流程Controller
 * @author 郑利
 * @version 2018-09-18 
 */
@Controller
@RequestMapping(value = "${adminPath}/matoutrequisition/matOutRequisition")
public class MatOutRequisitionController extends BaseController {

	@Autowired
	private MatOutRequisitionService matRequisitionService;
	
	@Autowired
	private WmsMainInventoryService wmsMainInventoryService;
	
	
	
	@ModelAttribute
	public MatRequisition get(@RequestParam(required=false) String id) {
		MatRequisition entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = matRequisitionService.get(id);
		}
		if (entity == null){
			entity = new MatRequisition();
		}
		return entity;
	}

	
	/**
	 * 领料单流程列表页面
	 */
	@RequiresPermissions("matoutrequisition:matOutRequisition:out")
	@RequestMapping(value = {"out", ""})
	public String out() {
		return "modules/mat/matrequisition/matRequisitionOut";
	}
	/**
	 * 领料单出库
	 */
	@ResponseBody
	@RequiresPermissions(value={"matoutrequisition:matOutRequisition:out:deliver"},logical=Logical.OR)
	@RequestMapping(value = "deliver")
	public AjaxJson deliver(@RequestBody String data, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
		  List<MatRequisitionList> matList =JSON.parseArray(data,MatRequisitionList.class);
			//新增或编辑表单保存
		  matRequisitionService.save(matList);//保存
			j.setSuccess(true);
			j.setMsg("出库成功");
		} catch (Exception e) {
			j.setMsg("出库失败");
		}
		return j;
	}
	@ResponseBody
	@RequiresPermissions(value={"matoutrequisition:matOutRequisition:out:deliver"},logical=Logical.OR)
	@RequestMapping(value = "deliverAll")
	public AjaxJson deliverAll(String ids, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		try {
			for (String id : idArray) {
				MatRequisitionList matRequisitionList = new MatRequisitionList();
				matRequisitionList.setMatRequisitionId(id);
				List<MatRequisitionList> matList = matRequisitionService.findMatList(matRequisitionList);
				matRequisitionService.save(matList);//保存
			}
			//新增或编辑表单保存
			j.setSuccess(true);
			j.setMsg("出库成功");
		} catch (Exception e) {
			j.setMsg("出库失败");
		}
		return j;
	}
	/**
	 * 领料单查看页
	 * @param matRequisition
	 * @param model
	 * @return
	 */
	@RequiresPermissions("matoutrequisition:matOutRequisition:view")
	@RequestMapping(value= "form")
    public String from(MatRequisition matRequisition,Model model){
    	model.addAttribute("matRequisition", matRequisition);
    	return "modules/mat/matrequisition/matRequisitionOutForm";
    }
		/**
	 * 领料单流程列表数据
	 */
	@ResponseBody
	@RequiresPermissions("matoutrequisition:matOutRequisition:out")
	@RequestMapping(value = "outData")
	public Map<String, Object> outData(MatRequisition matRequisition, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MatRequisition> page = matRequisitionService.findPage(new Page<MatRequisition>(request, response), matRequisition); 
		return getBootstrapData(page);
	}
	@ResponseBody
	@RequestMapping(value = "detail")
    public MatRequisition detail(String id) {
			return matRequisitionService.get(id);
		}
		
	  
	@ResponseBody
	@RequestMapping(value="outLocation") 
    public AjaxJson outLocation(String id){
		AjaxJson j=new AjaxJson();
		MatRequisition matRequisition=matRequisitionService.get(id);
		WmsMainInventory wmsMainInventory=new WmsMainInventory();
	
		for(int i=0;i<matRequisition.getMatRequisitionListList().size();i++){
			int num=matRequisition.getMatRequisitionListList().get(i).getApplyovalNumber();
			String mdsItemId=matRequisition.getMatRequisitionListList().get(i).getMdsItemId();
			wmsMainInventory.setItemId(mdsItemId);
			WmsMainInventory entity=wmsMainInventoryService.getByItem(wmsMainInventory);
			if(entity==null){
				j.setSuccess(false);
				j.setMsg("该物料库存数量为0!");
				return j;
			}
			else if(entity.getCurrentOnhandQty()>=num){
				int qty=entity.getCurrentOnhandQty()-num;
				entity.setCurrentOnhandQty(qty);
				
				matRequisition.setUpdateBy(UserUtils.getUser());
				matRequisition.setUpdateDate(new Date());
				matRequisition.setState("50");
				matRequisitionService.updateState(matRequisition);
				wmsMainInventoryService.update(entity);
			}else {
				j.setSuccess(false);
				j.setMsg("审批数量不能大于库存数量!");
				return j;
			}
		
		
	}
		j.setSuccess(true);
		j.setMsg("出库成功!");
		return j;
	}
}