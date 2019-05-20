/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmainstoragevs.web;

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
import org.springframework.web.bind.annotation.GetMapping;
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
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainRecord;
import com.pt.modules.wms.wmsmainstoragevs.service.WmsMainRecordService;

/**
 * 库房流水记录Controller
 * @author cjx
 * @version 2018-10-18
 */
@Controller
@RequestMapping(value = "${adminPath}/wms/wmsmainstoragevs/wmsMainRecord")
public class WmsMainRecordController extends BaseController {

	@Autowired
	private WmsMainRecordService wmsMainRecordService;
	
	@ModelAttribute
	public WmsMainRecord get(@RequestParam(required=false) String id) {
		WmsMainRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wmsMainRecordService.get(id);
		}
		if (entity == null){
			entity = new WmsMainRecord();
		}
		return entity;
	}
	
	/**
	 * 库房流水记录列表页面
	 */
	@RequiresPermissions("wms:wmsmainstoragevs:wmsMainRecord:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmsmainstoragevs/wmsMainRecordList";
	}
	
		/**
	 * 库房流水记录列表数据
	 */
	@ResponseBody
	@RequiresPermissions("wms:wmsmainstoragevs:wmsMainRecord:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsMainRecord wmsMainRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsMainRecord> page = wmsMainRecordService.findPage(new Page<WmsMainRecord>(request, response), wmsMainRecord); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑库房流水记录表单页面
	 */
	@RequiresPermissions(value={"wms:wmsmainstoragevs:wmsMainRecord:view","wms:wmsmainstoragevs:wmsMainRecord:add","wms:wmsmainstoragevs:wmsMainRecord:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WmsMainRecord wmsMainRecord, Model model) {
		model.addAttribute("wmsMainRecord", wmsMainRecord);
		return "modules/wms/wmsmainstoragevs/wmsMainRecordForm";
	}

	/**
	 * 保存库房流水记录
	 */
	@ResponseBody
	@RequiresPermissions(value={"wms:wmsmainstoragevs:wmsMainRecord:add","wms:wmsmainstoragevs:wmsMainRecord:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WmsMainRecord wmsMainRecord, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, wmsMainRecord)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		wmsMainRecordService.save(wmsMainRecord);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存库房流水记录成功");
		return j;
	}
	
	/**
	 * 删除库房流水记录
	 */
	@ResponseBody
	@RequiresPermissions("wms:wmsmainstoragevs:wmsMainRecord:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WmsMainRecord wmsMainRecord, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		wmsMainRecordService.delete(wmsMainRecord);
		j.setMsg("删除库房流水记录成功");
		return j;
	}
	
	/**
	 * 批量删除库房流水记录
	 */
	@ResponseBody
	@RequiresPermissions("wms:wmsmainstoragevs:wmsMainRecord:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			wmsMainRecordService.delete(wmsMainRecordService.get(id));
		}
		j.setMsg("删除库房流水记录成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wms:wmsmainstoragevs:wmsMainRecord:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsMainRecord wmsMainRecord, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "库房流水记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsMainRecord> page = wmsMainRecordService.findPage(new Page<WmsMainRecord>(request, response, -1), wmsMainRecord);
    		new ExportExcel("库房流水记录", WmsMainRecord.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出库房流水记录记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wms:wmsmainstoragevs:wmsMainRecord:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsMainRecord> list = ei.getDataList(WmsMainRecord.class);
			for (WmsMainRecord wmsMainRecord : list){
				try{
					wmsMainRecordService.save(wmsMainRecord);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条库房流水记录记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条库房流水记录记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入库房流水记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wms/wmsmainstoragevs/wmsMainRecord/?repage";
    }
	
	/**
	 * 下载导入库房流水记录数据模板
	 */
	@RequiresPermissions("wms:wmsmainstoragevs:wmsMainRecord:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "库房流水记录数据导入模板.xlsx";
    		List<WmsMainRecord> list = Lists.newArrayList(); 
    		new ExportExcel("库房流水记录数据", WmsMainRecord.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wms/wmsmainstoragevs/wmsMainRecord/?repage";
    }
	/**
	 * 按月统计出入库数
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getOutIn")
	public AjaxJson getOutIn(){
		AjaxJson j = new AjaxJson();
        try {
			j.setBody(wmsMainRecordService.getOutNum());
		} catch (Exception e) {
			j.setMsg("查询失败");
			j.setSuccess(false);
		}
		return j;
	}
}