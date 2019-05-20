/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinventory.web;

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
import com.pt.modules.wms.wmsinventory.entity.WmsInventory;
import com.pt.modules.wms.wmsinventory.service.WmsInventoryService;

/**
 * 库房增删改查Controller
 * @author 孙会楠
 * @version 2018-05-05
 */
@Controller
@RequestMapping(value = "${adminPath}/wmsinventory/wmsInventory")
public class WmsInventoryController extends BaseController {

	@Autowired
	private WmsInventoryService wmsInventoryService;
	
	@ModelAttribute
	public WmsInventory get(@RequestParam(required=false) String id) {
		WmsInventory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wmsInventoryService.get(id);
		}
		if (entity == null){
			entity = new WmsInventory();
		}
		return entity;
	}
	
	/**
	 * 添加库房列表页面
	 */
	@RequiresPermissions("wmsinventory:wmsInventory:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmsinventory/wmsInventoryList";
	}
	
		/**
	 * 添加库房列表数据
	 */
	@ResponseBody
	@RequiresPermissions("wmsinventory:wmsInventory:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsInventory wmsInventory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsInventory> page = wmsInventoryService.findPage(new Page<WmsInventory>(request, response), wmsInventory); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑添加库房表单页面
	 */
	@RequiresPermissions(value={"wmsinventory:wmsInventory:view","wmsinventory:wmsInventory:add","wmsinventory:wmsInventory:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WmsInventory wmsInventory, Model model) {
		model.addAttribute("wmsInventory", wmsInventory);
		return "modules/wms/wmsinventory/wmsInventoryForm";
	}

	/**
	 * 保存添加库房
	 */
	@ResponseBody
	@RequiresPermissions(value={"wmsinventory:wmsInventory:add","wmsinventory:wmsInventory:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WmsInventory wmsInventory, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, wmsInventory)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		wmsInventoryService.save(wmsInventory);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存添加库房成功");
		return j;
	}
	
	/**
	 * 删除添加库房
	 */
	@ResponseBody
	@RequiresPermissions("wmsinventory:wmsInventory:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WmsInventory wmsInventory, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		wmsInventoryService.delete(wmsInventory);
		j.setMsg("删除添加库房成功");
		return j;
	}
	
	/**
	 * 批量删除添加库房
	 */
	@ResponseBody
	@RequiresPermissions("wmsinventory:wmsInventory:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			wmsInventoryService.delete(wmsInventoryService.get(id));
		}
		j.setMsg("删除添加库房成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wmsinventory:wmsInventory:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsInventory wmsInventory, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "添加库房"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsInventory> page = wmsInventoryService.findPage(new Page<WmsInventory>(request, response, -1), wmsInventory);
    		new ExportExcel("添加库房", WmsInventory.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出添加库房记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wmsinventory:wmsInventory:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsInventory> list = ei.getDataList(WmsInventory.class);
			for (WmsInventory wmsInventory : list){
				try{
					wmsInventoryService.save(wmsInventory);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条添加库房记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条添加库房记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入添加库房失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsinventory/wmsInventory/?repage";
    }
	
	/**
	 * 下载导入添加库房数据模板
	 */
	@RequiresPermissions("wmsinventory:wmsInventory:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "添加库房数据导入模板.xlsx";
    		List<WmsInventory> list = Lists.newArrayList(); 
    		new ExportExcel("添加库房数据", WmsInventory.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsinventory/wmsInventory/?repage";
    }

}