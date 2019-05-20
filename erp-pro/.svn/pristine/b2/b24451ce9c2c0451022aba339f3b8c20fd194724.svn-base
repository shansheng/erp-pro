/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmaininventory.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;


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
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainInventory;
import com.pt.modules.wms.wmsmaininventory.service.WmsMainInventoryService;

/**
 * 台账明细表Controller
 * @author cjx
 * @version 2018-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/wms/wmsMainInventory")
public class WmsMainInventoryController extends BaseController {

	@Autowired
	private WmsMainInventoryService wmsMainInventoryService;
	
	@ModelAttribute
	public WmsMainInventory get(@RequestParam(required=false) String id) {
		WmsMainInventory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wmsMainInventoryService.get(id);
		}
		if (entity == null){
			entity = new WmsMainInventory();
		}
		return entity;
	}
	
	/**
	 * 台账列表页面
	 */
	@RequiresPermissions("wms:wmsMainInventory:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmsmaininventory/wmsMainInventoryList";
	}
	
		/**
	 * 台账列表数据
	 */
	@ResponseBody
	@RequiresPermissions("wms:wmsMainInventory:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsMainInventory wmsMainInventory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsMainInventory> page = wmsMainInventoryService.findPage(new Page<WmsMainInventory>(request, response), wmsMainInventory); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑台账表单页面
	 */
	@RequiresPermissions("wms:wmsMainInventory:view")
	@RequestMapping(value = "form")
	public String form(WmsMainInventory wmsMainInventory, Model model) {
		model.addAttribute("wmsMainInventory", wmsMainInventory);
		return "modules/wms/wmsmaininventory/wmsMainInventoryForm";
	}

	/**
	 * 保存台账
	 *//*
	@ResponseBody
	@RequiresPermissions(value={"wms:maininventory:wmsMainInventory:add","wms:maininventory:wmsMainInventory:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WmsMainInventory wmsMainInventory, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, wmsMainInventory)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		wmsMainInventoryService.save(wmsMainInventory);//保存
		j.setSuccess(true);
		j.setMsg("保存台账成功");
		return j;
	}*/
	
	/**
	 * 删除台账
	 *//*
	@ResponseBody
	@RequiresPermissions("wms:maininventory:wmsMainInventory:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WmsMainInventory wmsMainInventory, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		wmsMainInventoryService.delete(wmsMainInventory);
		j.setMsg("删除台账成功");
		return j;
	}
	
	*//**
	 * 批量删除台账
	 *//*
	@ResponseBody
	@RequiresPermissions("wms:maininventory:wmsMainInventory:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			wmsMainInventoryService.delete(wmsMainInventoryService.get(id));
		}
		j.setMsg("删除台账成功");
		return j;
	}*/
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wms:wmsMainInventory:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsMainInventory wmsMainInventory, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "台账"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsMainInventory> page = wmsMainInventoryService.findPage(new Page<WmsMainInventory>(request, response, -1), wmsMainInventory);
    		new ExportExcel("台账", WmsMainInventory.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出台账记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public WmsMainInventory detail(String id) {
		return wmsMainInventoryService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wms:wmsMainInventory:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsMainInventory> list = ei.getDataList(WmsMainInventory.class);
			for (WmsMainInventory wmsMainInventory : list){
				try{
					wmsMainInventoryService.save(wmsMainInventory);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条台账记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条台账记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入台账失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wms/wmsMainInventory/?repage";
    }
	
	/**
	 * 下载导入台账数据模板
	 */
	@RequiresPermissions("wms:wmsMainInventory:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "台账数据导入模板.xlsx";
    		List<WmsMainInventory> list = Lists.newArrayList(); 
    		new ExportExcel("台账数据", WmsMainInventory.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wms/wmsMainInventory/?repage";
    }
	

}