/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mrp.mrpwofind.web;

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
import com.pt.modules.mrp.mrpwofind.entity.MrpWoFeeding;
import com.pt.modules.mrp.mrpwofind.service.MrpWoFeedingService;

/**
 * 任务查询Controller
 * @author 孙会楠
 * @version 2018-06-07
 */
@Controller
@RequestMapping(value = "${adminPath}/mrpwofind/mrpWoFeeding")
public class MrpWoFeedingController extends BaseController {

	@Autowired
	private MrpWoFeedingService mrpWoFeedingService;
	
	@ModelAttribute
	public MrpWoFeeding get(@RequestParam(required=false) String id) {
		MrpWoFeeding entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mrpWoFeedingService.get(id);
		}
		if (entity == null){
			entity = new MrpWoFeeding();
		}
		return entity;
	}
	
	/**
	 * 任务查询列表页面
	 */
	@RequiresPermissions("mrpwofind:mrpWoFeeding:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mrp/mrpwofind/mrpWoFeedingList";
	}
	
		/**
	 * 任务查询列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mrpwofind:mrpWoFeeding:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MrpWoFeeding mrpWoFeeding, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MrpWoFeeding> page = mrpWoFeedingService.findPage(new Page<MrpWoFeeding>(request, response), mrpWoFeeding); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑任务查询表单页面
	 */
	@RequiresPermissions(value={"mrpwofind:mrpWoFeeding:view","mrpwofind:mrpWoFeeding:add","mrpwofind:mrpWoFeeding:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MrpWoFeeding mrpWoFeeding, Model model) {
		model.addAttribute("MrpWoFeeding", mrpWoFeeding);
		return "modules/mrp/mrpwofind/mrpWoFeedingForm";
	}

	/**
	 * 保存任务查询
	 */
	@ResponseBody
	@RequiresPermissions(value={"mrpwofind:mrpWoFeeding:add","mrpwofind:mrpWoFeeding:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MrpWoFeeding mrpWoFeeding, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mrpWoFeeding)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		mrpWoFeedingService.save(mrpWoFeeding);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存任务查询成功");
		return j;
	}
	
	/**
	 * 删除任务查询
	 */
	@ResponseBody
	@RequiresPermissions("mrpwofind:mrpWoFeeding:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MrpWoFeeding mrpWoFeeding, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mrpWoFeedingService.delete(mrpWoFeeding);
		j.setMsg("删除任务查询成功");
		return j;
	}
	
	/**
	 * 批量删除任务查询
	 */
	@ResponseBody
	@RequiresPermissions("mrpwofind:mrpWoFeeding:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mrpWoFeedingService.delete(mrpWoFeedingService.get(id));
		}
		j.setMsg("删除任务查询成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mrpwofind:mrpWo:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MrpWoFeeding mrpWoFeeding, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "任务查询"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MrpWoFeeding> page = mrpWoFeedingService.findPage(new Page<MrpWoFeeding>(request, response, -1), mrpWoFeeding);
    		new ExportExcel("任务查询", MrpWoFeeding.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出任务查询记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mrpwofind:mrpWoFeeding:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MrpWoFeeding> list = ei.getDataList(MrpWoFeeding.class);
			for (MrpWoFeeding mrpWoFeeding : list){
				try{
					mrpWoFeedingService.save(mrpWoFeeding);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条任务查询记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条任务查询记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入任务管理失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mrpwofind/mrpWoFeeding/?repage";
    }
	
	/**
	 * 下载导入任务查询数据模板
	 */
	@RequiresPermissions("mrpwofind:mrpWoFeeding:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "任务查询数据导入模板.xlsx";
    		List<MrpWoFeeding> list = Lists.newArrayList(); 
    		new ExportExcel("任务查询数据", MrpWoFeeding.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mrpwofind/mrpWoFeeding/?repage";
    }

}