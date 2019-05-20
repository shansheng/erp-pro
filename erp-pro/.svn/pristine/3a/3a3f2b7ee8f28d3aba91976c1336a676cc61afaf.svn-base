/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.supplier.web;

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
import com.pt.modules.sys.supplier.entity.Supplier;
import com.pt.modules.sys.supplier.service.SupplierService;

/**
 * 供应商管理Controller
 * @author 郑利
 * @version 2018-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/supplier/supplier")
public class SupplierController extends BaseController {

	@Autowired
	private SupplierService supplierService;
	
	@ModelAttribute
	public Supplier get(@RequestParam(required=false) String id) {
		Supplier entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = supplierService.get(id);
		}
		if (entity == null){
			entity = new Supplier();
		}
		return entity;
	}
	
	/**
	 * 供应商列表页面
	 */
	@RequiresPermissions("supplier:supplier:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/sys/supplier/supplierList";
	}
	
		/**
	 * 供应商列表数据
	 */
	@ResponseBody
	@RequiresPermissions("supplier:supplier:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Supplier supplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Supplier> page = supplierService.findPage(new Page<Supplier>(request, response), supplier); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑供应商表单页面
	 */
	@RequiresPermissions(value={"supplier:supplier:view","supplier:supplier:add","supplier:supplier:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Supplier supplier, Model model) {
		model.addAttribute("supplier", supplier);
		return "modules/sys/supplier/supplierForm";
	}

	/**
	 * 保存供应商
	 */
	@ResponseBody
	@RequiresPermissions(value={"supplier:supplier:add","supplier:supplier:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Supplier supplier, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, supplier)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		supplierService.save(supplier);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存供应商成功");
		return j;
	}
	
	/**
	 * 删除供应商
	 */
	@ResponseBody
	@RequiresPermissions("supplier:supplier:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Supplier supplier, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		supplierService.delete(supplier);
		j.setMsg("删除供应商成功");
		return j;
	}
	
	/**
	 * 批量删除供应商
	 */
	@ResponseBody
	@RequiresPermissions("supplier:supplier:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			supplierService.delete(supplierService.get(id));
		}
		j.setMsg("删除供应商成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("supplier:supplier:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Supplier supplier, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "供应商"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Supplier> page = supplierService.findPage(new Page<Supplier>(request, response, -1), supplier);
    		new ExportExcel("供应商", Supplier.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出供应商记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("supplier:supplier:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Supplier> list = ei.getDataList(Supplier.class);
			for (Supplier supplier : list){
				try{
					supplierService.save(supplier);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条供应商记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条供应商记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入供应商失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/supplier/supplier/?repage";
    }
	
	/**
	 * 下载导入供应商数据模板
	 */
	@RequiresPermissions("supplier:supplier:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "供应商数据导入模板.xlsx";
    		List<Supplier> list = Lists.newArrayList(); 
    		new ExportExcel("供应商数据", Supplier.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/supplier/supplier/?repage";
    }

}