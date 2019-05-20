/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmaininstorage.web;

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
import com.pt.common.config.Global;
import com.pt.common.json.AjaxJson;
import com.pt.common.utils.DateUtils;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.modules.wms.wmsmaininstorage.entity.WmsMainInList;
import com.pt.modules.wms.wmsmaininstorage.entity.WmsMainInStorage;
import com.pt.modules.wms.wmsmaininstorage.service.WmsMainInStorageService;

/**
 * 入库管理Controller
 * @author cjx
 * @version 2018-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/wms/wmsmaininstorage/wmsMainInStorage")
public class WmsMainInStorageController extends BaseController {

	@Autowired
	private WmsMainInStorageService wmsMainInStorageService;
	
	@ModelAttribute
	public WmsMainInStorage get(@RequestParam(required=false) String id) {
		WmsMainInStorage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wmsMainInStorageService.get(id);
		}
		if (entity == null){
			entity = new WmsMainInStorage();
		}
		return entity;
	}
	
	/**
	 * 入库列表页面
	 */
	@RequiresPermissions("wms:wmsmaininstorage:wmsMainInStorage:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmsmaininstorage/wmsMainInStorageList";
	}
	
	/**
	 * 入库列表数据
	 */
	@ResponseBody
	@RequiresPermissions("wms:wmsmaininstorage:wmsMainInStorage:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsMainInStorage wmsMainInStorage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsMainInStorage> page = wmsMainInStorageService.findPage(new Page<WmsMainInStorage>(request, response), wmsMainInStorage); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑入库表单页面
	 */
	@RequiresPermissions(value={"wms:wmsmaininstorage:wmsMainInStorage:view","wms:wmsmaininstorage:wmsMainInStorage:add","wms:wmsmaininstorage:wmsMainInStorage:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WmsMainInList wmsMainInList, Model model) {
		model.addAttribute("wmsMainInList", wmsMainInList);
		return "modules/wms/wmsmaininstorage/wmsMainInStorageForm";
	}

	/**
	 * 保存入库
	 */
	@ResponseBody
	@RequiresPermissions(value={"wms:wmsmaininstorage:wmsMainInStorage:add","wms:wmsmaininstorage:wmsMainInStorage:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WmsMainInList wmsMainInList, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, wmsMainInList)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		wmsMainInStorageService.save(wmsMainInList);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存入库成功");
		return j;
	}
	
	/**
	 * 批量入库
	 */
	@ResponseBody
	@RequiresPermissions("wms:wmsmaininstorage:wmsMainInStorage:inStorage")
	@RequestMapping(value = "inStorage")
	public AjaxJson inStorage(String ids,String sources, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		String sourceArray[] =sources.split(",");
		for(int i = 0;i<idArray.length;i++){
			wmsMainInStorageService.inStorage(wmsMainInStorageService.get(idArray[i],sourceArray[i]));
		}
		j.setMsg("入库成功");
		return j;
	}
	
	/**
	 * 删除入库
	 */
	@ResponseBody
	@RequiresPermissions("wms:wmsmaininstorage:wmsMainInStorage:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WmsMainInStorage wmsMainInStorage, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		wmsMainInStorageService.delete(wmsMainInStorage);
		j.setMsg("删除入库成功");
		return j;
	}
	
	/**
	 * 批量删除入库
	 */
	@ResponseBody
	@RequiresPermissions("wms:wmsmaininstorage:wmsMainInStorage:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			wmsMainInStorageService.deleteAll(id);
		}
		j.setMsg("删除入库成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wms:wmsmaininstorage:wmsMainInStorage:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsMainInStorage wmsMainInStorage, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "入库"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsMainInStorage> page = wmsMainInStorageService.findPage(new Page<WmsMainInStorage>(request, response, -1), wmsMainInStorage);
    		new ExportExcel("入库", WmsMainInStorage.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出入库记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wms:wmsmaininstorage:wmsMainInStorage:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsMainInStorage> list = ei.getDataList(WmsMainInStorage.class);
			for (WmsMainInStorage wmsMainInStorage : list){
				try{
					wmsMainInStorageService.save(wmsMainInStorage);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条入库记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条入库记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入入库失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wms/wmsmaininstorage/wmsMainInStorage/?repage";
    }
	
	/**
	 * 下载导入入库数据模板
	 */
	@RequiresPermissions("wms:wmsmaininstorage:wmsMainInStorage:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "入库数据导入模板.xlsx";
    		List<WmsMainInStorage> list = Lists.newArrayList(); 
    		new ExportExcel("入库数据", WmsMainInStorage.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wms/wmsmaininstorage/wmsMainInStorage/?repage";
    }

}