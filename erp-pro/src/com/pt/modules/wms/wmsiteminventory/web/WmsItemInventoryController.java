/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsiteminventory.web;

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
import com.pt.modules.wms.wmsiteminventory.entity.WmsItemInventory;
import com.pt.modules.wms.wmsiteminventory.service.WmsItemInventoryService;

/**
 * 台账增删改查Controller
 * @author 孙会楠
 * @version 2018-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/wmsiteminventory/wmsItemInventory")
public class WmsItemInventoryController extends BaseController {

	@Autowired
	private WmsItemInventoryService wmsItemInventoryService;
	
	@ModelAttribute
	public WmsItemInventory get(@RequestParam(required=false) String id) {
		WmsItemInventory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wmsItemInventoryService.get(id);
		}
		if (entity == null){
			entity = new WmsItemInventory();
		}
		return entity;
	}
	
	/**
	 * 台账管理列表页面
	 */
	@RequiresPermissions("wmsiteminventory:wmsItemInventory:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmsiteminventory/wmsItemInventoryList";
	}
	
		/**
	 * 台账管理列表数据
	 */
	@ResponseBody
	@RequiresPermissions("wmsiteminventory:wmsItemInventory:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsItemInventory wmsItemInventory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsItemInventory> page = wmsItemInventoryService.findPage(new Page<WmsItemInventory>(request, response), wmsItemInventory); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑台账管理表单页面
	 */
	@RequiresPermissions(value={"wmsiteminventory:wmsItemInventory:view","wmsiteminventory:wmsItemInventory:add","wmsiteminventory:wmsItemInventory:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WmsItemInventory wmsItemInventory, Model model) {
		model.addAttribute("wmsItemInventory", wmsItemInventory);
		return "modules/wms/wmsiteminventory/wmsItemInventoryForm";
	}

	/**
	 * 保存台账管理
	 */
	@ResponseBody
	@RequiresPermissions(value={"wmsiteminventory:wmsItemInventory:add","wmsiteminventory:wmsItemInventory:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WmsItemInventory wmsItemInventory, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, wmsItemInventory)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		wmsItemInventoryService.save(wmsItemInventory);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存台账管理成功");
		return j;
	}
	
	/**
	 * 删除台账管理
	 */
	@ResponseBody
	@RequiresPermissions("wmsiteminventory:wmsItemInventory:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WmsItemInventory wmsItemInventory, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		wmsItemInventoryService.delete(wmsItemInventory);
		j.setMsg("删除台账管理成功");
		return j;
	}
	
	/**
	 * 批量删除台账管理
	 */
	@ResponseBody
	@RequiresPermissions("wmsiteminventory:wmsItemInventory:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			wmsItemInventoryService.delete(wmsItemInventoryService.get(id));
		}
		j.setMsg("删除台账管理成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wmsiteminventory:wmsItemInventory:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsItemInventory wmsItemInventory, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "台账管理"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsItemInventory> page = wmsItemInventoryService.findPage(new Page<WmsItemInventory>(request, response, -1), wmsItemInventory);
    		new ExportExcel("台账管理", WmsItemInventory.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出台账管理记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 台账导入
	 */
	@ResponseBody
	@RequiresPermissions("wmsiteminventory:wmsItemInventory:import")
	@RequestMapping(value = "import")
	public AjaxJson drimport(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try{
			String idArray[] =ids.split(",");
			for(String id : idArray){
//				WmsItemInventoryService.doInSubmit(WmsItemInventoryService.get(id));
			}
			j.setMsg("导入成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入失败！失败信息："+e.getMessage());
		}
		return j;
	}
	
	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wmsiteminventory:wmsItemInventory:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsItemInventory> list = ei.getDataList(WmsItemInventory.class);
			for (WmsItemInventory wmsItemInventory : list){
				try{
					wmsItemInventoryService.save(wmsItemInventory);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条台账管理记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条台账管理记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入台账管理失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsiteminventory/wmsItemInventory/?repage";
    }
	
	/**
	 * 下载导入台账管理数据模板
	 */
	@RequiresPermissions("wmsiteminventory:wmsItemInventory:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "台账管理数据导入模板.xlsx";
    		List<WmsItemInventory> list = Lists.newArrayList(); 
    		new ExportExcel("台账管理数据", WmsItemInventory.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsiteminventory/wmsItemInventory/?repage";
    }

}