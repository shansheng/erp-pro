/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmslocator.web;

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
import com.pt.modules.wms.wmslocator.entity.WmsLocator;
import com.pt.modules.wms.wmslocator.service.WmsLocatorService;

/**
 * 库位增删改查Controller
 * @author 孙会楠
 * @version 2018-05-05
 */
@Controller
@RequestMapping(value = "${adminPath}/wmslocator/wmsLocator")
public class WmsLocatorController extends BaseController {

	@Autowired
	private WmsLocatorService wmsLocatorService;
	
	@ModelAttribute
	public WmsLocator get(@RequestParam(required=false) String id) {
		WmsLocator entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wmsLocatorService.get(id);
		}
		if (entity == null){
			entity = new WmsLocator();
		}
		return entity;
	}
	
	/**
	 * 添加库位列表页面
	 */
	@RequiresPermissions("wmslocator:wmsLocator:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmslocator/wmsLocatorList";
	}
	
	/**
	 * 添加库位列表数据
	 */
	@ResponseBody
	@RequiresPermissions("wmslocator:wmsLocator:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsLocator wmsLocator, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsLocator> page = wmsLocatorService.findPage(new Page<WmsLocator>(request, response), wmsLocator); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑添加库位表单页面
	 */
	@RequiresPermissions(value={"wmslocator:wmsLocator:view","wmslocator:wmsLocator:add","wmslocator:wmsLocator:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WmsLocator wmsLocator, Model model) {
		model.addAttribute("wmsLocator", wmsLocator);
		return "modules/wms/wmslocator/wmsLocatorForm";
	}

	/**
	 * 保存添加库位
	 */
	@ResponseBody
	@RequiresPermissions(value={"wmslocator:wmsLocator:add","wmslocator:wmsLocator:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WmsLocator wmsLocator, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, wmsLocator)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		wmsLocatorService.save(wmsLocator);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存添加库位成功");
		return j;
	}
	
	/**
	 * 删除添加库位
	 */
	@ResponseBody
	@RequiresPermissions("wmslocator:wmsLocator:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WmsLocator wmsLocator, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		wmsLocatorService.delete(wmsLocator);
		j.setMsg("删除添加库位成功");
		return j;
	}
	
	/**
	 * 批量删除添加库位
	 */
	@ResponseBody
	@RequiresPermissions("wmslocator:wmsLocator:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			wmsLocatorService.delete(wmsLocatorService.get(id));
		}
		j.setMsg("删除添加库位成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wmslocator:wmsLocator:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsLocator wmsLocator, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "添加库位"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsLocator> page = wmsLocatorService.findPage(new Page<WmsLocator>(request, response, -1), wmsLocator);
    		new ExportExcel("添加库位", WmsLocator.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出添加库位记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wmslocator:wmsLocator:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsLocator> list = ei.getDataList(WmsLocator.class);
			for (WmsLocator wmsLocator : list){
				try{
					wmsLocatorService.save(wmsLocator);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条添加库位记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条添加库位记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入添加库位失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmslocator/wmsLocator/?repage";
    }
	
	/**
	 * 下载导入添加库位数据模板
	 */
	@RequiresPermissions("wmslocator:wmsLocator:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "添加库位数据导入模板.xlsx";
    		List<WmsLocator> list = Lists.newArrayList(); 
    		new ExportExcel("添加库位数据", WmsLocator.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmslocator/wmsLocator/?repage";
    }

	/**
	 * 通过库房id获取库位列表
	 */
	@ResponseBody
	@RequestMapping(value = "getDataByWmsinventoryId")
	public Map<String, Object> getDataByWmsinventoryId(WmsLocator wmsLocator, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsLocator> page = wmsLocatorService.getDataByWmsinventoryId(new Page<WmsLocator>(request, response), wmsLocator); 
		return getBootstrapData(page);
	}
}