/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.selectmesewo.web;

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
import com.pt.modules.mes.selectmesewo.entity.MesOperationSeqV;
import com.pt.modules.mes.selectmesewo.service.MesOperationSeqVService;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;


/**
 * 生产计划查询Controller
 * @author 郑利
 * @version 2018-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/mesoperationseqv/mesOperationSeqV")
public class MesOperationSeqVController extends BaseController {

	@Autowired
	private MesOperationSeqVService mesOperationSeqVService;
	
	@ModelAttribute
	public MesOperationSeqV get(@RequestParam(required=false) String id) {
		MesOperationSeqV entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mesOperationSeqVService.get(id);
		}
		if (entity == null){
			entity = new MesOperationSeqV();
		}
		return entity;
	}
	
	/**
	 * 生产计划查询列表页面
	 */
	@RequiresPermissions("mesoperationseqv:mesOperationSeqV:list")
	@RequestMapping(value = {"list", ""})
	public String list(String mesEwoId,MesOperationSeqV mesOperationSeqV, HttpServletRequest request, HttpServletResponse response,Model model) {
		model.addAttribute("mesEwoId", mesEwoId);
		mesOperationSeqV.setMesEwoId(mesEwoId);
		Page<MesOperationSeqV> page = mesOperationSeqVService.findPage(new Page<MesOperationSeqV>(request, response), mesOperationSeqV); 	
		List<MesOperationSeqV> mesList=page.getList();
		model.addAttribute("mesList", mesList);
		return "modules/mes/selectmesewo/mesOperationSeqVList";
	}
	
	/**
	 * 生产计划查询列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mesoperationseqv:mesOperationSeqV:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(String mesEwoId,MesOperationSeqV mesOperationSeqV, HttpServletRequest request, HttpServletResponse response, Model model) {
		mesOperationSeqV.setMesEwoId(mesEwoId);
		Page<MesOperationSeqV> page = mesOperationSeqVService.findPage(new Page<MesOperationSeqV>(request, response), mesOperationSeqV); 	
		List<MesOperationSeqV> mesList=page.getList();
		model.addAttribute("mesList", mesList);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑生产计划查询表单页面
	 */
	@RequiresPermissions(value={"mesoperationseqv:mesOperationSeqV:view","mesoperationseqv:mesOperationSeqV:add","mesoperationseqv:mesOperationSeqV:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MesOperationSeqV mesOperationSeqV, Model model) {
		model.addAttribute("mesOperationSeqV", mesOperationSeqV);
		return "modules/mes/selectmesewo/mesOperationSeqVForm";
	}

	/**
	 * 保存生产计划查询
	 */
	@ResponseBody
	@RequiresPermissions(value={"mesoperationseqv:mesOperationSeqV:add","mesoperationseqv:mesOperationSeqV:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MesOperationSeqV mesOperationSeqV, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mesOperationSeqV)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		mesOperationSeqVService.save(mesOperationSeqV);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存生产计划查询成功");
		return j;
	}
	
	/**
	 * 删除生产计划查询
	 */
	@ResponseBody
	@RequiresPermissions("mesoperationseqv:mesOperationSeqV:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MesOperationSeqV mesOperationSeqV, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mesOperationSeqVService.delete(mesOperationSeqV);
		j.setMsg("删除生产计划查询成功");
		return j;
	}
	
	/**
	 * 批量删除生产计划查询
	 */
	@ResponseBody
	@RequiresPermissions("mesoperationseqv:mesOperationSeqV:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mesOperationSeqVService.delete(mesOperationSeqVService.get(id));
		}
		j.setMsg("删除生产计划查询成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mesoperationseqv:mesOperationSeqV:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MesOperationSeqV mesOperationSeqV, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "生产计划查询"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MesOperationSeqV> page = mesOperationSeqVService.findPage(new Page<MesOperationSeqV>(request, response, -1), mesOperationSeqV);
    		new ExportExcel("生产计划查询", MesOperationSeqV.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出生产计划查询记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mesoperationseqv:mesOperationSeqV:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MesOperationSeqV> list = ei.getDataList(MesOperationSeqV.class);
			for (MesOperationSeqV mesOperationSeqV : list){
				try{
					mesOperationSeqVService.save(mesOperationSeqV);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条生产计划查询记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条生产计划查询记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入生产计划查询失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mesoperationseqv/mesOperationSeqV/?repage";
    }
	
	/**
	 * 下载导入生产计划查询数据模板
	 */
	@RequiresPermissions("mesoperationseqv:mesOperationSeqV:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "生产计划查询数据导入模板.xlsx";
    		List<MesOperationSeqV> list = Lists.newArrayList(); 
    		new ExportExcel("生产计划查询数据", MesOperationSeqV.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mesoperationseqv/mesOperationSeqV/?repage";
    }

}