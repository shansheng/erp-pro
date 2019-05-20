/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysquestion.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.sysquestion.entity.SysQuestion;
import com.pt.modules.sys.sysquestion.service.SysQuestionService;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 问题管理Controller
 * @author 郑利
 * @version 2018-07-13
 */
@Controller
@RequestMapping(value = "${adminPath}/sysquestion/sysQuestion")
public class SysQuestionController extends BaseController {

	@Autowired
	private SysQuestionService sysQuestionService;
	
	@ModelAttribute
	public SysQuestion get(@RequestParam(required=false) String id) {
		SysQuestion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysQuestionService.get(id);
		}
		if (entity == null){
			entity = new SysQuestion();
		}
		return entity;
	}
	
	@RequestMapping(value="updateStatus")
	public String  upadateStatus(String sysQueId,Model model,SysQuestion sysQuestion,RedirectAttributes redirectAttributes) throws Exception{
		
		sysQuestion=sysQuestionService.get(sysQueId);
		User user=UserUtils.getUser();
		if(!user.getId().equals(sysQuestion.getSysUserId())){
			addMessage(redirectAttributes, "没有关闭该问题的权限! ");
			return  "redirect:" + adminPath + "/sysreply/sysReply/list?sysQueId="+sysQueId;
		}
		if("2".equals(sysQuestion.getStatus())){
			addMessage(redirectAttributes, "问题已关闭,请勿重复关闭! ");
			return  "redirect:" + adminPath + "/sysreply/sysReply/list?sysQueId="+sysQueId;
			
		}
		sysQuestion.setId(sysQueId);
		
		sysQuestion.setUpdateBy(user);  
		sysQuestion.setUpdateDate(new Date());
		sysQuestionService.updateStatus(sysQuestion);//新建或者编辑保存
		addMessage(redirectAttributes, "问题关闭成功! ");
		return  "redirect:" + adminPath + "/sysreply/sysReply/list?sysQueId="+sysQueId;
	
		
	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="selectSub")
	public @ResponseBody Map<String,Object> findSecondTypeById(String classify,HttpServletResponse response )throws Exception{

		List<SysQuestion>  lTbCategories =  sysQuestionService.selectSub(classify);

		Map<String,Object> jsonMap = new HashMap<String, Object>();
		 
		List list = null;

		       if(null != lTbCategories && lTbCategories.size()>0){
		           list = new ArrayList();
		           
		           for(SysQuestion sysQuestion : lTbCategories){
		               Map<String,String> taskMap=new HashMap<String,String>();
		               
		               taskMap.put("id", sysQuestion.getSubclassification());
		               taskMap.put("name", sysQuestion.getSubclassificationName());
		               list.add(taskMap);
		           }
		       }
		       jsonMap.put("tasks", list);
		       return jsonMap;
		 

		}
	/**
	 * 问题管理列表页面
	 */
	@RequiresPermissions("sysquestion:sysQuestion:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/sys/sysquestion/sysQuestionList";
	}
	
		/**
	 * 问题管理列表数据
	 */
	@ResponseBody
	@RequiresPermissions("sysquestion:sysQuestion:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SysQuestion sysQuestion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysQuestion> page = sysQuestionService.findPage(new Page<SysQuestion>(request, response), sysQuestion);
		
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑问题管理表单页面
	 */
	@RequiresPermissions(value={"sysquestion:sysQuestion:view","sysquestion:sysQuestion:add","sysquestion:sysQuestion:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(SysQuestion sysQuestion, Model model,HttpSession session) {
		
		model.addAttribute("sysQuestion", sysQuestion);
		return "modules/sys/sysquestion/sysQuestionForm";
	}

	/**
	 * 保存问题管理
	 */
	@ResponseBody
	@RequiresPermissions(value={"sysquestion:sysQuestion:add","sysquestion:sysQuestion:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(SysQuestion sysQuestion, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, sysQuestion)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		sysQuestion.setCreateDate(new Date());
		sysQuestionService.save(sysQuestion);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存问题管理成功");
		return j;
	}
	
	/**
	 * 删除问题管理
	 */
	@ResponseBody
	@RequiresPermissions("sysquestion:sysQuestion:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SysQuestion sysQuestion, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sysQuestionService.delete(sysQuestion);
		j.setMsg("删除问题管理成功");
		return j;
	}
	
	/**
	 * 批量删除问题管理
	 */
	@ResponseBody
	@RequiresPermissions("sysquestion:sysQuestion:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			sysQuestionService.delete(sysQuestionService.get(id));
		}
		j.setMsg("删除问题管理成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("sysquestion:sysQuestion:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SysQuestion sysQuestion, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "问题管理"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SysQuestion> page = sysQuestionService.findPage(new Page<SysQuestion>(request, response, -1), sysQuestion);
    		new ExportExcel("问题管理", SysQuestion.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出问题管理记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("sysquestion:sysQuestion:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SysQuestion> list = ei.getDataList(SysQuestion.class);
			for (SysQuestion sysQuestion : list){
				try{
					sysQuestionService.save(sysQuestion);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条问题管理记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条问题管理记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入问题管理失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sysquestion/sysQuestion/?repage";
    }
	
	/**
	 * 下载导入问题管理数据模板
	 */
	@RequiresPermissions("sysquestion:sysQuestion:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "问题管理数据导入模板.xlsx";
    		List<SysQuestion> list = Lists.newArrayList(); 
    		new ExportExcel("问题管理数据", SysQuestion.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sysquestion/sysQuestion/?repage";
    }
	/**
	 *问题类型统计
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getQuestion")
   public AjaxJson getQuestion(){
	   AjaxJson j = new AjaxJson();
	  try {
	j.setBody(sysQuestionService.getQuestion());
	} catch (Exception e) {
		j.setMsg("查询失败");
		j.setSuccess(false);
	}
	   return j;
			   
   }
}