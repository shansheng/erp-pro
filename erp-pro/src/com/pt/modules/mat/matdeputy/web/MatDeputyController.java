/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matdeputy.web;

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
import com.pt.modules.mat.matdepartment.service.MatDepartmentService;
import com.pt.modules.mat.matdeputy.service.MatDeputyService;
import com.pt.modules.mat.matrequisition.entity.MatRequisition;
import com.pt.modules.templan.templandepartment.entity.TemplanDepartment;


/**
 * 领料单流程Controller
 * @author 郑利
 * @version 2018-09-18
 */
@Controller
@RequestMapping(value = "${adminPath}/matdeputy/matDeputy")
public class MatDeputyController extends BaseController {

	@Autowired
	private MatDeputyService matDeputyService;
	
	@ModelAttribute
	public MatRequisition get(@RequestParam(required=false) String id) {
		MatRequisition entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = matDeputyService.get(id);
		}
		if (entity == null){
			entity = new MatRequisition();
		}
		return entity;
	}
	
	/**
	 * 领料单流程列表页面
	 */
	@RequiresPermissions("matdeputy:matDeputy:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mat/matdeputy/matRequisitionList";
	}
	
		/**
	 * 领料单流程列表数据
	 */
	@ResponseBody
	@RequiresPermissions("matdeputy:matDeputy:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MatRequisition matRequisition, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MatRequisition> page = matDeputyService.findPage(new Page<MatRequisition>(request, response), matRequisition); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑领料单流程表单页面
	 */
	@RequiresPermissions(value={"matdeputy:matDeputy:view","matdeputy:matDeputy:add","matdeputy:matDeputy:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MatRequisition matRequisition, Model model) {
		model.addAttribute("matRequisition", matRequisition);
		return "modules/mat/matdeputy/matRequisitionForm";
	}

	
	/**
	 * 驳回
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("matdeputy:matDeputy:reject")
	@RequestMapping(value="reject")
	public AjaxJson reject(String id){
		AjaxJson j = new AjaxJson();
		int a = matDeputyService.rejectState(id);
		if(a<1){
			j.setMsg("操作失败");
		}else{
			j.setMsg("已驳回");
		}
		return j;
	}
	@ResponseBody
	@RequiresPermissions("matdeputy:matDeputy:argee")
	@RequestMapping(value="argee")
	public AjaxJson argee(String id){
		AjaxJson j = new AjaxJson();
		int a = matDeputyService.argeeState(id);
		if(a<1){
			j.setMsg("操作失败");
		}else{
			j.setMsg("审核通过");
		}
		return j;
	}
	

	@ResponseBody
	@RequiresPermissions(value={"matdeputy:matDeputy:add","matdeputy:matDeputy:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MatRequisition matRequisition, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, matRequisition)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		matDeputyService.save(matRequisition);//保存
		j.setSuccess(true);
		j.setMsg("保存领料单流程成功");
		return j;
	}
	
	
	

	
	
	/**
	 * 删除领料单流程
	 */
	@ResponseBody
	@RequiresPermissions("matdeputy:matDeputy:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MatRequisition matRequisition, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		matDeputyService.delete(matRequisition);
		j.setMsg("删除领料单流程成功");
		return j;
	}
	
	/**
	 * 批量删除领料单流程
	 */
	@ResponseBody
	@RequiresPermissions("matdeputy:matDeputy:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			matDeputyService.delete(matDeputyService.get(id));
		}
		j.setMsg("删除领料单流程成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("matdeputy:matDeputy:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MatRequisition matRequisition, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "领料单流程"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MatRequisition> page = matDeputyService.findPage(new Page<MatRequisition>(request, response, -1), matRequisition);
    		new ExportExcel("领料单流程", MatRequisition.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出领料单流程记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public MatRequisition detail(String id) {
		return matDeputyService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("matdeputy:matDeputy:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MatRequisition> list = ei.getDataList(MatRequisition.class);
			for (MatRequisition matRequisition : list){
				try{
					matDeputyService.save(matRequisition);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条领料单流程记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条领料单流程记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入领料单流程失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/matdeputy/matDeputy/?repage";
    }
	
	/**
	 * 下载导入领料单流程数据模板
	 */
	@RequiresPermissions("matdeputy:matDeputy:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "领料单流程数据导入模板.xlsx";
    		List<MatRequisition> list = Lists.newArrayList(); 
    		new ExportExcel("领料单流程数据", MatRequisition.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/matdeputy/matDeputy/?repage";
    }
	

}