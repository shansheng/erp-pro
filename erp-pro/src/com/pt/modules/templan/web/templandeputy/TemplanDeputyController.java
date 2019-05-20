/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.templan.web.templandeputy;

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
import com.pt.modules.templan.entity.templandeputy.TemplanDeputy;
import com.pt.modules.templan.service.templandeputy.TemplanDeputyService;

/**
 * 临时计划副总审核Controller
 * @author cjx
 * @version 2018-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/templan/templandeputy/templanDeputy")
public class TemplanDeputyController extends BaseController {

	@Autowired
	private TemplanDeputyService templanDeputyService;
	
	@ModelAttribute
	public TemplanDeputy get(@RequestParam(required=false) String id) {
		TemplanDeputy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = templanDeputyService.get(id);
		}
		if (entity == null){
			entity = new TemplanDeputy();
		}
		return entity;
	}
	
	/**
	 * 临时计划副总审核列表页面
	 */
	@RequiresPermissions("templan:templandeputy:templanDeputy:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/templan/templandeputy/templanDeputyList";
	}
	
		/**
	 * 临时计划副总审核列表数据
	 */
	@ResponseBody
	@RequiresPermissions("templan:templandeputy:templanDeputy:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(TemplanDeputy templanDeputy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TemplanDeputy> page = templanDeputyService.findPage(new Page<TemplanDeputy>(request, response), templanDeputy); 
		return getBootstrapData(page);
	}
	/**
	 * 驳回
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("templan:templandeputy:templanDeputy:reject")
	@RequestMapping(value="reject")
	public AjaxJson reject(String id){
		AjaxJson j = new AjaxJson();
		int a = templanDeputyService.rejectState(id);
		if(a<1){
			j.setMsg("操作失败");
		}else{
			j.setMsg("已驳回");
		}
		return j;
	}
	@ResponseBody
	@RequiresPermissions("templan:templandeputy:templanDeputy:edit")
	@RequestMapping(value="argee")
	public AjaxJson argee(String id){
		AjaxJson j = new AjaxJson();
		int a = templanDeputyService.argeeState(id);
		if(a<1){
			j.setMsg("操作失败");
		}else{
			j.setMsg("审核通过");
		}
		return j;
	}
	/**
	 * 查看，增加，编辑临时计划副总审核表单页面
	 */
	@RequiresPermissions(value={"templan:templandeputy:templanDeputy:view","templan:templandeputy:templanDeputy:add","templan:templandeputy:templanDeputy:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(TemplanDeputy templanDeputy, Model model) {
		model.addAttribute("templanDeputy", templanDeputy);
		return "modules/templan/templandeputy/templanDeputyForm";
	}

	/**
	 * 保存临时计划副总审核
	 */
	@ResponseBody
	@RequiresPermissions(value={"templan:templandeputy:templanDeputy:add","templan:templandeputy:templanDeputy:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(TemplanDeputy templanDeputy, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, templanDeputy)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		templanDeputyService.save(templanDeputy);//保存
		j.setSuccess(true);
		j.setMsg("审核成功!");
		return j;
	}
	
	/**
	 * 删除临时计划副总审核
	 */
	@ResponseBody
	@RequiresPermissions("templan:templandeputy:templanDeputy:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(TemplanDeputy templanDeputy, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		templanDeputyService.delete(templanDeputy);
		j.setMsg("删除临时计划副总审核成功");
		return j;
	}
	
	/**
	 * 批量删除临时计划副总审核
	 */
	@ResponseBody
	@RequiresPermissions("templan:templandeputy:templanDeputy:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			templanDeputyService.delete(templanDeputyService.get(id));
		}
		j.setMsg("删除临时计划副总审核成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("templan:templandeputy:templanDeputy:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(TemplanDeputy templanDeputy, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "临时计划副总审核"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TemplanDeputy> page = templanDeputyService.findPage(new Page<TemplanDeputy>(request, response, -1), templanDeputy);
    		new ExportExcel("临时计划副总审核", TemplanDeputy.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出临时计划副总审核记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public TemplanDeputy detail(String id) {
		return templanDeputyService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("templan:templandeputy:templanDeputy:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TemplanDeputy> list = ei.getDataList(TemplanDeputy.class);
			for (TemplanDeputy templanDeputy : list){
				try{
					templanDeputyService.save(templanDeputy);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条临时计划副总审核记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条临时计划副总审核记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入临时计划副总审核失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/templan/templandeputy/templanDeputy/?repage";
    }
	
	/**
	 * 下载导入临时计划副总审核数据模板
	 */
	@RequiresPermissions("templan:templandeputy:templanDeputy:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "临时计划副总审核数据导入模板.xlsx";
    		List<TemplanDeputy> list = Lists.newArrayList(); 
    		new ExportExcel("临时计划副总审核数据", TemplanDeputy.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/templan/templandeputy/templanDeputy/?repage";
    }
	

}