/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matbom.web;


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

import com.pt.modules.mat.matbom.entity.MatBomTable;
import com.pt.modules.mat.matbom.service.MatBomTableService;


/**
 * mat_bom_tableController
 * @author 郑利
 * @version 2018-09-30
 */
@Controller
@RequestMapping(value = "${adminPath}/matbom/matBomTable")
public class MatBomTableController extends BaseController {

	@Autowired
	private MatBomTableService matBomTableService;

	@ModelAttribute
	public MatBomTable get(@RequestParam(required=false) String id) {
		MatBomTable entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = matBomTableService.get(id);
		}
		if (entity == null){
			entity = new MatBomTable();
		}
		return entity;
	}
	
	/**
	 * mat_bom_table列表页面
	 */
	@RequiresPermissions("matbom:matBomTable:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mat/matbom/matBomList";
	}
	
		/**
	 * mat_bom_table列表数据
	 */
	@ResponseBody
	@RequiresPermissions("matbom:matBomTable:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MatBomTable matBomTable, HttpServletRequest request, HttpServletResponse response, Model model) {

		Page<MatBomTable> page = matBomTableService.findPage(new Page<MatBomTable>(request, response), matBomTable); 
		List<MatBomTable> list=page.getList();
		for(int i=0;i<list.size();i++){
			MatBomTable matBomTables=list.get(i);
			if(matBomTables.getMdsItemName()==null||matBomTables.getMdsItemName()==""){
				matBomTables.setMdsItemCode(matBomTables.getItemCode());
				matBomTables.setMdsItemName(matBomTables.getItemName());
				matBomTables.setMdsItemspecs(matBomTables.getItemspecs());
				matBomTables.setMdsItemmodelNum(matBomTables.getItemmodelNum());
				list.set(i, matBomTables);
				
			}
		}
		page.setList(list);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑mat_bom_table表单页面
	 */
	@RequiresPermissions(value={"matbom:matBomTable:view","matbom:matBomTable:add","matbom:matBomTable:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(String matBomDetailId,MatBomTable matBomTable, Model model) {
		
		model.addAttribute("matBomDetailId", matBomDetailId);
		model.addAttribute("matBomTable", matBomTable);
		return "modules/mat/matbom/matBomDetailForm";
	}

	/**
	 * 从材料定额选取form页
	 */
	@RequiresPermissions(value={"matbom:matBomTable:view","matbom:matBomTable:add","matbom:matBomTable:edit"},logical=Logical.OR)
	@RequestMapping(value = "quotaForm")
	public String quotaForm(String matBomDetailId,String sysMarketListId,MatBomTable matBomTable, Model model) {
		model.addAttribute("sysMarketListId", sysMarketListId);
		model.addAttribute("matBomDetailId", matBomDetailId);
		model.addAttribute("matBomTable", matBomTable);
		return "modules/mat/matbom/matBomQuotaForm";
	}
	/**
	 * 保存mat_bom_table
	 */
	@ResponseBody
	@RequiresPermissions(value={"matbom:matBomTable:add","matbom:matBomTable:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(String matBomDetailId,MatBomTable matBomTable, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, matBomTable)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		
		
		
		
		matBomTable.setMatBomDetailId(matBomDetailId);
		matBomTableService.save(matBomTable);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存物料成功");  
		return j;
	}
	
	

	
	/**
	 * 删除mat_bom_table
	 */
	@ResponseBody
	@RequiresPermissions("matbom:matBomTable:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MatBomTable matBomTable, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		matBomTableService.delete(matBomTable);
		j.setMsg("删除物料成功");
		return j;
	}
	
	/**
	 * 批量删除mat_bom_table
	 */
	@ResponseBody
	@RequiresPermissions("matbom:matBomTable:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			matBomTableService.delete(matBomTableService.get(id));
		}
		j.setMsg("删除mat_bom_table成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("matbom:matBomTable:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MatBomTable matBomTable, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "mat_bom_table"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MatBomTable> page = matBomTableService.findPage(new Page<MatBomTable>(request, response, -1), matBomTable);
    		new ExportExcel("mat_bom_table", MatBomTable.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出mat_bom_table记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("matbom:matBomTable:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MatBomTable> list = ei.getDataList(MatBomTable.class);
			for (MatBomTable matBomTable : list){
				try{
					matBomTableService.save(matBomTable);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条mat_bom_table记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条mat_bom_table记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入mat_bom_table失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/matbom/matBomTable/?repage";
    }
	
	/**
	 * 下载导入mat_bom_table数据模板
	 */
	@RequiresPermissions("matbom:matBomTable:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "mat_bom_table数据导入模板.xlsx";
    		List<MatBomTable> list = Lists.newArrayList(); 
    		new ExportExcel("mat_bom_table数据", MatBomTable.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/matbom/matBomTable/?repage";
    }

}