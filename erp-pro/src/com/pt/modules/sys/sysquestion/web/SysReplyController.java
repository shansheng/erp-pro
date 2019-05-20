/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysquestion.web;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.ibatis.annotations.Param;
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
import com.pt.modules.sys.entity.DictValue;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.service.DictTypeService;
import com.pt.modules.sys.sysquestion.entity.SysQuestion;
import com.pt.modules.sys.sysquestion.entity.SysReply;
import com.pt.modules.sys.sysquestion.service.SysQuestionService;
import com.pt.modules.sys.sysquestion.service.SysReplyService;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 回复Controller
 * @author 郑利
 * @version 2018-07-13
 */
@Controller
@RequestMapping(value = "${adminPath}/sysreply/sysReply")
public class SysReplyController extends BaseController {

	@Autowired
	private SysReplyService sysReplyService;
	@Autowired
	private SysQuestionService sysQuestionService;
	@Autowired
	private DictTypeService dictTypeService;
	@ModelAttribute
	public SysReply get(@RequestParam(required=false) String id) {
		SysReply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysReplyService.get(id);
		}
		if (entity == null){
			entity = new SysReply();
		}
		return entity;
	}
	
	/**
	 * 回复列表页面
	 */
	@RequiresPermissions("sysreply:sysReply:list")
	@RequestMapping(value = {"list", ""})
	public String list(String sysQueId,Model model,RedirectAttributes redirectAttributes) {
		SysReply sysReply=new SysReply();
		sysReply.setQuestionId(sysQueId);
		model.addAttribute("sysQueId", sysQueId);
		SysQuestion sysQuestion=sysQuestionService.get(sysQueId);
		String userId=sysQuestion.getProSysUserId();
		String userIdT=sysQuestion.getSysUserId();
		User user=UserUtils.getUser();
		if(!userId.equals(user.getId())&&!userIdT.equals(user.getId())){
				addMessage(redirectAttributes, "没有查看该问题的权限! ");
				return  "redirect:" + adminPath + "/sysquestion/sysQuestion/list";
		}
			
		List<SysReply> replyList=sysReplyService.findList(sysReply);
		String str=sysQuestion.getUpl();
		if(str!=null && !str.equals("")){
			String[] uploads=str.split("\\|");
			model.addAttribute("uploads", uploads);
		}else{
			model.addAttribute("uploads", "无");
		}
		
		

		List<DictValue> list=dictTypeService.getByClassify();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getValue().equals(sysQuestion.getClassify())){
				sysQuestion.setClassifyName(list.get(i).getLabel());
			}
		}
		
		model.addAttribute("sysQuestion", sysQuestion);
		model.addAttribute("replyList", replyList);
		return "modules/sys/sysreply/sysReplyList";
	}



	/**
	 * 查看，增加，编辑回复表单页面
	 */
	@RequiresPermissions(value={"sysreply:sysReply:view","sysreply:sysReply:add","sysreply:sysReply:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(String questionId, Model model,RedirectAttributes redirectAttributes) {	
		model.addAttribute("questionId", questionId);
		
		return "modules/sys/sysreply/sysReplyForm";
	}

	/**
	 * 保存回复
	 */
	@RequiresPermissions(value={"sysreply:sysReply:add","sysreply:sysReply:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(@Param("sysQueId") String sysQueId,SysReply sysReply, Model model, RedirectAttributes redirectAttributes) throws Exception{
		sysReply.setQuestionId(sysQueId);
		SysQuestion sysQuestion=sysQuestionService.get(sysQueId);
		String queStatus=sysQuestion.getStatus();
		String queSt="2";
		User user=UserUtils.getUser();
		if(!user.getId().equals(sysQuestion.getProSysUserId())){
			addMessage(redirectAttributes, "没有回复该问题的权限! ");
			return  "redirect:" + adminPath + "/sysreply/sysReply/list?sysQueId="+sysQueId;
		}
		
		
		if(queStatus.equals(queSt)){
			addMessage(redirectAttributes, "问题已关闭,禁止回复! ");
			return  "redirect:" + adminPath + "/sysreply/sysReply/list?sysQueId="+sysQueId;
		}
		sysReplyService.save(sysReply);//新建或者编辑保存
		return  "redirect:" + adminPath + "/sysreply/sysReply/list?sysQueId="+sysQueId;
				
	}
	
	/**
	 * 删除回复
	 */
	@ResponseBody
	@RequiresPermissions("sysreply:sysReply:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SysReply sysReply, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sysReplyService.delete(sysReply);
		j.setMsg("删除回复成功");
		return j;
	}
	
	/**
	 * 批量删除回复
	 */
	@ResponseBody
	@RequiresPermissions("sysreply:sysReply:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			sysReplyService.delete(sysReplyService.get(id));
		}
		j.setMsg("删除回复成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("sysreply:sysReply:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SysReply sysReply, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "回复"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SysReply> page = sysReplyService.findPage(new Page<SysReply>(request, response, -1), sysReply);
    		new ExportExcel("回复", SysReply.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出回复记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("sysreply:sysReply:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SysReply> list = ei.getDataList(SysReply.class);
			for (SysReply sysReply : list){
				try{
					sysReplyService.save(sysReply);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条回复记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条回复记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入回复失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sysreply/sysReply/?repage";
    }
	
	/**
	 * 下载导入回复数据模板
	 */
	@RequiresPermissions("sysreply:sysReply:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "回复数据导入模板.xlsx";
    		List<SysReply> list = Lists.newArrayList(); 
    		new ExportExcel("回复数据", SysReply.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sysreply/sysReply/?repage";
    }

}