/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.templan.templanprocurement.web;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.pt.common.utils.DateUtils;
import com.pt.common.config.Global;
import com.pt.common.json.AjaxJson;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;
import com.pt.modules.templan.templanprocurement.entity.TemplanProcurement;
import com.pt.modules.templan.templanprocurement.entity.TemplanProcurementList;
import com.pt.modules.templan.templanprocurement.service.TemplanProcurementService;

/**
 * 采购查看Controller
 * @author cjx
 * @version 2018-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/templan/templanprocurement/templanProcurement")
public class TemplanProcurementController extends BaseController {

	@Autowired
	private TemplanProcurementService templanProcurementService;
	
	@ModelAttribute
	public TemplanProcurement get(@RequestParam(required=false) String id) {
		TemplanProcurement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = templanProcurementService.get(id);
		}
		if (entity == null){
			entity = new TemplanProcurement();
		}
		return entity;
	}
	
	/**
	 * 采购发货列表页面
	 */
	@RequiresPermissions("templan:templanprocurement:templanProcurement:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/templan/templanprocurement/templanProcurementList";
	}
	
		/**
	 * 采购发货列表数据
	 */
	@ResponseBody
	@RequiresPermissions("templan:templanprocurement:templanProcurement:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(TemplanProcurement templanProcurement, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TemplanProcurement> page = templanProcurementService.findPage(new Page<TemplanProcurement>(request, response), templanProcurement); 
		return getBootstrapData(page);
	}

	/**
	 * 查看采购发货表单页面
	 */
	@RequiresPermissions(value={"templan:templanprocurement:templanProcurement:view"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(TemplanProcurement templanProcurement, Model model) {
		model.addAttribute("templanProcurement", templanProcurement);
		return "modules/templan/templanprocurement/templanProcurementForm";
	}
    
	/**
	 * 保存采购发货
	 */
	@ResponseBody
	@RequiresPermissions(value={"templan:templanprocurement:templanProcurement:deliver"},logical=Logical.OR)
	@RequestMapping(value = "deliver")
	public AjaxJson deliver(@RequestBody String data, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
		  List<TemplanProcurementList> tempList =JSON.parseArray(data,TemplanProcurementList.class);
			//新增或编辑表单保存
			templanProcurementService.save(tempList);//保存
			j.setSuccess(true);
			j.setMsg("发货成功");
		} catch (Exception e) {
			j.setMsg("发货失败");
		}
		return j;
	}
	
	/**
	 * 批量保存采购发货
	 */
	@ResponseBody
	@RequiresPermissions(value={"templan:templanprocurement:templanProcurement:deliver"},logical=Logical.OR)
	@RequestMapping(value = "deliverAll")
	public AjaxJson deliverAll(String ids, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			 TemplanProcurementList templanProcurementList = new TemplanProcurementList();
			 templanProcurementList.setTemporaryPlanId(id);
			 List<TemplanProcurementList> tempList= templanProcurementService.findTempList(templanProcurementList);
			templanProcurementService.save(tempList);//保存
		}
		//新增或编辑表单保存
		j.setSuccess(true);
		j.setMsg("保存采购发货成功");
		return j;
	}
	
	
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("templan:templanprocurement:templanProcurement:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(TemplanProcurement templanProcurement, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "采购发货"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TemplanProcurement> page = templanProcurementService.findPage(new Page<TemplanProcurement>(request, response, -1), templanProcurement);
    		new ExportExcel("采购发货", TemplanProcurement.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出采购发货记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public TemplanProcurement detail(String id) {
		return templanProcurementService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("templan:templanprocurement:templanProcurement:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TemplanProcurement> list = ei.getDataList(TemplanProcurement.class);
			for (TemplanProcurement templanProcurement : list){
				try{
					templanProcurementService.save(templanProcurement);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条采购发货记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条采购发货记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入采购发货失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/templan/templanprocurement/templanProcurement/?repage";
    }
	
	/**
	 * 下载导入采购发货数据模板
	 */
	@RequiresPermissions("templan:templanprocurement:templanProcurement:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "采购发货数据导入模板.xlsx";
    		List<TemplanProcurement> list = Lists.newArrayList(); 
    		new ExportExcel("采购发货数据", TemplanProcurement.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/templan/templanprocurement/templanProcurement/?repage";
    }
	

}