/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsiteminventoryvs.web;

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
import com.pt.modules.wms.wmsiteminventoryvs.entity.WmsItemInventoryVs;
import com.pt.modules.wms.wmsiteminventoryvs.service.WmsItemInventoryVsService;

/**
 * 流水查询Controller
 * @author 郑利
 * @version 2018-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/wmsiteminventoryvs/wmsItemInventoryVs")
public class WmsItemInventoryVsController extends BaseController {

	@Autowired
	private WmsItemInventoryVsService wmsItemInventoryVsService;
	
	@ModelAttribute
	public WmsItemInventoryVs get(@RequestParam(required=false) String id) {
		WmsItemInventoryVs entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wmsItemInventoryVsService.get(id);
		}
		if (entity == null){
			entity = new WmsItemInventoryVs();
		}
		return entity;
	}
	
	/**
	 * 流水列表页面
	 */
	@RequiresPermissions("wmsiteminventoryvs:wmsItemInventoryVs:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmsiteminventoryvs/wmsItemInventoryVsList";
	}
	
		/**
	 * 流水列表数据
	 */
	@ResponseBody
	@RequiresPermissions("wmsiteminventoryvs:wmsItemInventoryVs:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsItemInventoryVs wmsItemInventoryVs, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsItemInventoryVs> page = wmsItemInventoryVsService.findPage(new Page<WmsItemInventoryVs>(request, response), wmsItemInventoryVs); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑流水表单页面
	 */
	@RequiresPermissions(value={"wmsiteminventoryvs:wmsItemInventoryVs:view","wmsiteminventoryvs:wmsItemInventoryVs:add","wmsiteminventoryvs:wmsItemInventoryVs:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WmsItemInventoryVs wmsItemInventoryVs, Model model) {
		model.addAttribute("wmsItemInventoryVs", wmsItemInventoryVs);
		return "modules/wms/wmsiteminventoryvs/wmsItemInventoryVsForm";
	}

	/**
	 * 保存流水
	 */
	@ResponseBody
	@RequiresPermissions(value={"wmsiteminventoryvs:wmsItemInventoryVs:add","wmsiteminventoryvs:wmsItemInventoryVs:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WmsItemInventoryVs wmsItemInventoryVs, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, wmsItemInventoryVs)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		wmsItemInventoryVsService.save(wmsItemInventoryVs);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存流水成功");
		return j;
	}
	
	/**
	 * 删除流水
	 */
	@ResponseBody
	@RequiresPermissions("wmsiteminventoryvs:wmsItemInventoryVs:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WmsItemInventoryVs wmsItemInventoryVs, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		wmsItemInventoryVsService.delete(wmsItemInventoryVs);
		j.setMsg("删除流水成功");
		return j;
	}
	
	/**
	 * 批量删除流水
	 */
	@ResponseBody
	@RequiresPermissions("wmsiteminventoryvs:wmsItemInventoryVs:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			wmsItemInventoryVsService.delete(wmsItemInventoryVsService.get(id));
		}
		j.setMsg("删除流水成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wmsiteminventoryvs:wmsItemInventoryVs:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsItemInventoryVs wmsItemInventoryVs, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "流水"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsItemInventoryVs> page = wmsItemInventoryVsService.findPage(new Page<WmsItemInventoryVs>(request, response, -1), wmsItemInventoryVs);
    		new ExportExcel("流水", WmsItemInventoryVs.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出流水记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wmsiteminventoryvs:wmsItemInventoryVs:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsItemInventoryVs> list = ei.getDataList(WmsItemInventoryVs.class);
			for (WmsItemInventoryVs wmsItemInventoryVs : list){
				try{
					wmsItemInventoryVsService.save(wmsItemInventoryVs);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条流水记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条流水记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入流水失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsiteminventoryvs/wmsItemInventoryVs/?repage";
    }
	
	/**
	 * 下载导入流水数据模板
	 */
	@RequiresPermissions("wmsiteminventoryvs:wmsItemInventoryVs:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "流水数据导入模板.xlsx";
    		List<WmsItemInventoryVs> list = Lists.newArrayList(); 
    		new ExportExcel("流水数据", WmsItemInventoryVs.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsiteminventoryvs/wmsItemInventoryVs/?repage";
    }

}