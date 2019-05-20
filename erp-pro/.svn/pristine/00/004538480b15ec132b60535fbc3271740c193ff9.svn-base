/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.mesworkshift.web;

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
import com.pt.modules.mes.mesworkshift.entity.MesWorkshift;
import com.pt.modules.mes.mesworkshift.entity.MesWorkshiftUser;
import com.pt.modules.mes.mesworkshift.service.MesWorkshiftService;
import com.pt.modules.sys.entity.Role;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.service.SystemService;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 班组管理Controller
 * @author 孙会楠
 * @version 2018-05-05
 */
@Controller
@RequestMapping(value = "${adminPath}/mesworkshift/mesWorkshift")
public class MesWorkshiftController extends BaseController {

	@Autowired
	private MesWorkshiftService mesWorkshiftService;
	
	@ModelAttribute
	public MesWorkshift get(@RequestParam(required=false) String id) {
		MesWorkshift entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mesWorkshiftService.get(id);
		}
		if (entity == null){
			entity = new MesWorkshift();
		}
		return entity;
	}
	
	/**
	 * 班组管理列表页面
	 */
	@RequiresPermissions("mesworkshift:mesWorkshift:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mes/mesworkshift/mesWorkshiftList";
	}
	
		/**
	 * 班组管理列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mesworkshift:mesWorkshift:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MesWorkshift mesWorkshift, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MesWorkshift> page = mesWorkshiftService.findPage(new Page<MesWorkshift>(request, response), mesWorkshift); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑班组管理表单页面
	 */
	@RequiresPermissions(value={"mesworkshift:mesWorkshift:view","mesworkshift:mesWorkshift:add","mesworkshift:mesWorkshift:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MesWorkshift mesWorkshift, Model model) {
		model.addAttribute("mesWorkshift", mesWorkshift);
		return "modules/mes/mesworkshift/mesWorkshiftForm";
	}

	/**
	 * 保存班组管理
	 */
	@ResponseBody
	@RequiresPermissions(value={"mesworkshift:mesWorkshift:add","mesworkshift:mesWorkshift:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MesWorkshift mesWorkshift, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mesWorkshift)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		mesWorkshiftService.save(mesWorkshift);//保存
		j.setSuccess(true);
		j.setMsg("保存班组管理成功");
		return j;
	}
	
	/**
	 * 删除班组管理
	 */
	@ResponseBody
	@RequiresPermissions("mesworkshift:mesWorkshift:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MesWorkshift mesWorkshift, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mesWorkshiftService.delete(mesWorkshift);
		j.setMsg("删除班组管理成功");
		return j;
	}
	
	/**
	 * 批量删除班组管理
	 */
	@ResponseBody
	@RequiresPermissions("mesworkshift:mesWorkshift:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mesWorkshiftService.delete(mesWorkshiftService.get(id));
		}
		j.setMsg("删除班组管理成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mesworkshift:mesWorkshift:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MesWorkshift mesWorkshift, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "班组管理"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MesWorkshift> page = mesWorkshiftService.findPage(new Page<MesWorkshift>(request, response, -1), mesWorkshift);
    		new ExportExcel("班组管理", MesWorkshift.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出班组管理记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public MesWorkshift detail(String id) {
		return mesWorkshiftService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mesworkshift:mesWorkshift:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MesWorkshift> list = ei.getDataList(MesWorkshift.class);
			for (MesWorkshift mesWorkshift : list){
				try{
					mesWorkshiftService.save(mesWorkshift);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条班组管理记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条班组管理记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入班组管理失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mesworkshift/mesWorkshift/?repage";
    }
	
	/**
	 * 下载导入班组管理数据模板
	 */
	@RequiresPermissions("mesworkshift:mesWorkshift:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "班组管理数据导入模板.xlsx";
    		List<MesWorkshift> list = Lists.newArrayList(); 
    		new ExportExcel("班组管理数据", MesWorkshift.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mesworkshift/mesWorkshift/?repage";
    }
	
}