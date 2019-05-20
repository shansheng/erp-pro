/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsfiscalperiod.web;

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
import com.pt.modules.wms.wmsfiscalperiod.entity.WmsFiscalPeriod;
import com.pt.modules.wms.wmsfiscalperiod.service.WmsFiscalPeriodService;

/**
 * 期段管理Controller
 * @author hexl
 * @version 2018-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/wmsfiscalperiod/wmsFiscalPeriod")
public class WmsFiscalPeriodController extends BaseController {

	@Autowired
	private WmsFiscalPeriodService wmsFiscalPeriodService;
	
	@ModelAttribute
	public WmsFiscalPeriod get(@RequestParam(required=false) String id) {
		WmsFiscalPeriod entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wmsFiscalPeriodService.get(id);
		}
		if (entity == null){
			entity = new WmsFiscalPeriod();
		}
		return entity;
	}
	
	/**
	 * 保存“期段”成功列表页面
	 */
	@RequiresPermissions("wmsfiscalperiod:wmsFiscalPeriod:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmsfiscalperiod/wmsFiscalPeriodList";
	}
	
		/**
	 * 保存“期段”成功列表数据
	 */
	@ResponseBody
	@RequiresPermissions("wmsfiscalperiod:wmsFiscalPeriod:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsFiscalPeriod wmsFiscalPeriod, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsFiscalPeriod> page = wmsFiscalPeriodService.findPage(new Page<WmsFiscalPeriod>(request, response), wmsFiscalPeriod); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑保存“期段”成功表单页面
	 */
	@RequiresPermissions(value={"wmsfiscalperiod:wmsFiscalPeriod:view","wmsfiscalperiod:wmsFiscalPeriod:add","wmsfiscalperiod:wmsFiscalPeriod:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WmsFiscalPeriod wmsFiscalPeriod, Model model) {
		model.addAttribute("wmsFiscalPeriod", wmsFiscalPeriod);
		if(StringUtils.isBlank(wmsFiscalPeriod.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/wms/wmsfiscalperiod/wmsFiscalPeriodForm";
	}

	/**
	 * 保存保存“期段”成功
	 */
	@RequiresPermissions(value={"wmsfiscalperiod:wmsFiscalPeriod:add","wmsfiscalperiod:wmsFiscalPeriod:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(WmsFiscalPeriod wmsFiscalPeriod, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, wmsFiscalPeriod)){
			return form(wmsFiscalPeriod, model);
		}
		//新增或编辑表单保存
		wmsFiscalPeriodService.save(wmsFiscalPeriod);//保存
		addMessage(redirectAttributes, "保存保存“期段”成功成功");
		return "redirect:"+Global.getAdminPath()+"/wmsfiscalperiod/wmsFiscalPeriod/?repage";
	}
	
	/**
	 * 删除保存“期段”成功
	 */
	@ResponseBody
	@RequiresPermissions("wmsfiscalperiod:wmsFiscalPeriod:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WmsFiscalPeriod wmsFiscalPeriod, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		wmsFiscalPeriodService.delete(wmsFiscalPeriod);
		j.setMsg("删除保存“期段”成功成功");
		return j;
	}
	
	/**
	 * 批量删除保存“期段”成功
	 */
	@ResponseBody
	@RequiresPermissions("wmsfiscalperiod:wmsFiscalPeriod:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			wmsFiscalPeriodService.delete(wmsFiscalPeriodService.get(id));
		}
		j.setMsg("删除保存“期段”成功成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wmsfiscalperiod:wmsFiscalPeriod:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsFiscalPeriod wmsFiscalPeriod, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "保存“期段”成功"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsFiscalPeriod> page = wmsFiscalPeriodService.findPage(new Page<WmsFiscalPeriod>(request, response, -1), wmsFiscalPeriod);
    		new ExportExcel("保存“期段”成功", WmsFiscalPeriod.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出保存“期段”成功记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wmsfiscalperiod:wmsFiscalPeriod:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsFiscalPeriod> list = ei.getDataList(WmsFiscalPeriod.class);
			for (WmsFiscalPeriod wmsFiscalPeriod : list){
				try{
					wmsFiscalPeriodService.save(wmsFiscalPeriod);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条保存“期段”成功记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条保存“期段”成功记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入保存“期段”成功失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/modules/wms/wmsfiscalperiod/wmsFiscalPeriod/?repage";
    }
	
	/**
	 * 下载导入保存“期段”成功数据模板
	 */
	@RequiresPermissions("wmsfiscalperiod:wmsFiscalPeriod:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "保存“期段”成功数据导入模板.xlsx";
    		List<WmsFiscalPeriod> list = Lists.newArrayList(); 
    		new ExportExcel("保存“期段”成功数据", WmsFiscalPeriod.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/modules/wms/wmsfiscalperiod/wmsFiscalPeriod/?repage";
    }

}