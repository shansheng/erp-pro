/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mrp.mrpwo.web;

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
import com.pt.modules.mrp.mrpwo.entity.MrpWo;
import com.pt.modules.mrp.mrpwo.service.MrpWoService;

/**
 * 生产任务管理Controller
 * @author 孙会楠
 * @version 2018-05-04
 */
@Controller
@RequestMapping(value = "${adminPath}/mrpwo/mrpWo")
public class MrpWoController extends BaseController {

	@Autowired
	private MrpWoService mrpWoService;
	
	@ModelAttribute
	public MrpWo get(@RequestParam(required=false) String id) {
		MrpWo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mrpWoService.get(id);
		}
		if (entity == null){
			entity = new MrpWo();
		}
		return entity;
	}
	
	/**
	 * 任务管理列表页面
	 */
	@RequiresPermissions("mrpwo:mrpWo:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mrp/mrpwo/mrpWoList";
	}
	
		/**
	 * 任务管理列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mrpwo:mrpWo:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MrpWo mrpWo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MrpWo> page = mrpWoService.findPage(new Page<MrpWo>(request, response), mrpWo); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑任务管理表单页面
	 */
	/*@RequiresPermissions(value={"mrpwo:mrpWo:view","mrpwo:mrpWo:add","mrpwo:mrpWo:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MrpWo mrpWo, Model model) {
		model.addAttribute("mrpWo", mrpWo);
		if(StringUtils.isBlank(mrpWo.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		if(mrpWo.getId() == null){
			Map<String, Object> result = mrpWoService.getTaskNo(mrpWo);
			mrpWo.setTaskNo((String)result.get("o_return_code"));
		}
		
		return "modules/mrp/mrpwo/mrpWoForm";
	}*/

	/**
	 * 查看，增加，编辑数据表单页面
	 */
	@RequiresPermissions(value={"mrpwo:mrpWo:view","mrpwo:mrpWo:add","mrpwo:mrpWo:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MrpWo mrpWo, Model model) {
		if(mrpWo.getId() == null){
			Map<String, Object> result = mrpWoService.getTaskNo(mrpWo);
			mrpWo.setTaskNo((String)result.get("o_return_code"));
		}
		model.addAttribute("mrpWo", mrpWo);
		return "modules/mrp/mrpWo/mrpWoForm";
	}

	/**
	 * 下达数据表单页面
	 */
	@ResponseBody
	@RequiresPermissions(value={"mrpwo:mrpWo:dispatch"})
	@RequestMapping(value = "dispatch")
	public AjaxJson dispatch(MrpWo mrpWo, Model model, RedirectAttributes redirectAttributes) {
		//mrpWo.setId(ids);
		AjaxJson j = new AjaxJson();
		String message = "下达成功";
		try {
			mrpWoService.mrpWoDispatch(mrpWo);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			message = "下达失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 保存任务管理
	 */
	@RequiresPermissions(value={"mrpwo:mrpWo:add","mrpwo:mrpWo:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(MrpWo mrpWo, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, mrpWo)){
			return form(mrpWo, model);
		}
		//新增或编辑表单保存
		mrpWoService.save(mrpWo);//保存
		addMessage(redirectAttributes, "保存任务管理成功");
		return "redirect:"+Global.getAdminPath()+"/mrpwo/mrpWo/?repage";
	}
	
	/**
	 * 删除任务管理
	 */
	@ResponseBody
	@RequiresPermissions("mrpwo:mrpWo:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MrpWo mrpWo, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mrpWoService.delete(mrpWo);
		j.setMsg("删除任务管理成功");
		return j;
	}
	
	/**
	 * 批量删除任务管理
	 */
	@ResponseBody
	@RequiresPermissions("mrpwo:mrpWo:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mrpWoService.delete(mrpWoService.get(id));
		}
		j.setMsg("删除任务管理成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mrpwo:mrpWo:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MrpWo mrpWo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "任务管理"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MrpWo> page = mrpWoService.findPage(new Page<MrpWo>(request, response, -1), mrpWo);
    		new ExportExcel("任务管理", MrpWo.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出任务管理记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据
	 */
	@RequiresPermissions("mrpwo:mrpWo:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MrpWo> list = ei.getDataList(MrpWo.class);
			for (MrpWo mrpWo : list){
				try{
					mrpWoService.save(mrpWo);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条任务管理记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条任务管理记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入任务管理失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mrpwo/mrpWo/?repage";
    }
	
	/**
	 * 下载导入任务管理数据模板
	 */
	@RequiresPermissions("mrpwo:mrpWo:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "任务管理数据导入模板.xlsx";
    		List<MrpWo> list = Lists.newArrayList(); 
    		new ExportExcel("任务管理数据", MrpWo.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mrpwo/mrpWo/?repage";
    }

	
	
}