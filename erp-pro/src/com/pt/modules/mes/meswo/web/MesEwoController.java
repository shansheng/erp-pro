/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.meswo.web;

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
import com.pt.modules.mes.meswo.entity.MesEwo;
import com.pt.modules.mes.meswo.service.MesEwoService;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;


/**
 * 计划管理Controller
 * @author 郑利
 * @version 2018-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/meswo/mesEwo")
public class MesEwoController extends BaseController {

	@Autowired
	private MesEwoService mesEwoService;
	
	@ModelAttribute
	public MesEwo get(@RequestParam(required=false) String id) {
		MesEwo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mesEwoService.get(id);
		}
		if (entity == null){
			entity = new MesEwo();
		}
		return entity;
	}
	
	
	
	/**
	 * 下达
	 */
	@ResponseBody
	@RequiresPermissions("meswo:mesEwo:dispatch")
	@RequestMapping(value = "dispatch")
	public AjaxJson dispatch(String ids, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		String message = "执行成功";
		try {
			for(String id : idArray){
			MesEwo mesEwo=mesEwoService.get(id);
			String stu=mesEwo.getEwoStatus();
			String str="1";
			if(stu.equals(str)){
				j.setSuccess(false);
				j.setMsg("所选计划中存在已下达计划，请重新选择！");
				return j;
			}
			mesEwoService.mesEwoDispatch(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			message = "执行失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 删除投料
	 */
	@ResponseBody
	@RequiresPermissions("meswo:mesEwo:dispatchDel")
	@RequestMapping(value = "dispatchDel")
	public AjaxJson dispatchDel(String ids, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "执行成功";
		String idArray[] =ids.split(",");
		try {
			for (String id : idArray) {
				MesEwo mesEwo=mesEwoService.get(id);
				String stu=mesEwo.getEwoStatus();
				String str="1";
				if(stu.equals(str)){
					j.setSuccess(false);
					j.setMsg("所选计划中存在已下达计划，请重新选择！");
					return j;
				}
				mesEwoService.mesEwoDispatchDel(id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			message = "执行失败";
		}
		j.setMsg(message);
		return j;
	}

	
	
	
	
	
	/**
	 * 计划列表页面
	 */
	@RequiresPermissions("meswo:mesEwo:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mes/meswo/mesEwoList";
	}
	
		/**
	 * 计划列表数据
	 */
	@ResponseBody
	@RequiresPermissions("meswo:mesEwo:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MesEwo mesEwo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MesEwo> page = mesEwoService.findPage(new Page<MesEwo>(request, response), mesEwo); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑计划表单页面
	 */
	@RequiresPermissions(value={"meswo:mesEwo:view","meswo:mesEwo:add","meswo:mesEwo:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MesEwo mesEwo, Model model) {
		model.addAttribute("mesEwo", mesEwo);
		return "modules/mes/meswo/mesEwoForm";
	}

	/**
	 * 保存计划
	 */
	@ResponseBody
	@RequiresPermissions(value={"meswo:mesEwo:add","meswo:mesEwo:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MesEwo mesEwo, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mesEwo)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		mesEwoService.save(mesEwo);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存计划成功");
		return j;
	}
	
	/**
	 * 删除计划
	 */
	@ResponseBody
	@RequiresPermissions("meswo:mesEwo:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MesEwo mesEwo, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mesEwoService.delete(mesEwo);
		j.setMsg("删除计划成功");
		return j;
	}
	
	/**
	 * 批量删除计划
	 */
	@ResponseBody
	@RequiresPermissions("meswo:mesEwo:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mesEwoService.delete(mesEwoService.get(id));
		}
		j.setMsg("删除计划成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("meswo:mesEwo:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MesEwo mesEwo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "计划"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MesEwo> page = mesEwoService.findPage(new Page<MesEwo>(request, response, -1), mesEwo);
    		new ExportExcel("计划", MesEwo.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出计划记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("meswo:mesEwo:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MesEwo> list = ei.getDataList(MesEwo.class);
			for (MesEwo mesEwo : list){
				try{
					mesEwoService.save(mesEwo);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条计划记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条计划记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入计划失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/meswo/mesEwo/?repage";
    }
	
	/**
	 * 下载导入计划数据模板
	 */
	@RequiresPermissions("meswo:mesEwo:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "计划数据导入模板.xlsx";
    		List<MesEwo> list = Lists.newArrayList(); 
    		new ExportExcel("计划数据", MesEwo.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/meswo/mesEwo/?repage";
    }

}