/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysquestion.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
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
import com.pt.modules.sys.entity.Office;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.sysquestion.entity.SysQueTransfer;
import com.pt.modules.sys.sysquestion.entity.SysQuestion;
import com.pt.modules.sys.sysquestion.service.SysQueTransferService;
import com.pt.modules.sys.sysquestion.service.SysQuestionService;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 问题转移Controller
 * @author 郑利
 * @version 2018-07-21
 */
@Controller
@RequestMapping(value = "${adminPath}/sysquetransfer/sysQueTransfer")
public class SysQueTransferController extends BaseController {

	@Autowired
	private SysQueTransferService sysQueTransferService;
	
	@Autowired
	private SysQuestionService sysQuestionService;
	@ModelAttribute
	public SysQueTransfer get(@RequestParam(required=false) String id) {
		SysQueTransfer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysQueTransferService.get(id);
		}
		if (entity == null){
			entity = new SysQueTransfer();
		}
		return entity;
	}
	
	/**
	 * 问题转移列表页面
	 */
	@RequiresPermissions("sysquetransfer:sysQueTransfer:list")
	@RequestMapping(value = {"list", ""})
	public String list(String questionId,Model model) {
		model.addAttribute("questionId", questionId);
		
		return "modules/sys/sysquetransfer/sysQueTransferList";
	}
	
		/**
	 * 问题转移列表数据
	 */
	@ResponseBody
	@RequiresPermissions("sysquetransfer:sysQueTransfer:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(String questionId,SysQueTransfer sysQueTransfer, HttpServletRequest request, HttpServletResponse response, Model model) {
		sysQueTransfer.setSysQueId(questionId);
		Page<SysQueTransfer> page = sysQueTransferService.findPage(new Page<SysQueTransfer>(request, response), sysQueTransfer); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑问题转移表单页面
	 */
	@RequiresPermissions(value={"sysquetransfer:sysQueTransfer:view","sysquetransfer:sysQueTransfer:add","sysquetransfer:sysQueTransfer:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(String sysQueId,SysQueTransfer sysQueTransfer, Model model,RedirectAttributes redirectAttributes) {
		sysQueTransfer.setSysQueId(sysQueId);
		SysQuestion sysQuestion=sysQuestionService.get(sysQueId);
		String queTitle=sysQuestion.getTitle();
		sysQueTransfer.setQueTitle(queTitle);
	
		model.addAttribute("sysQueTransfer", sysQueTransfer);
		String queStatus=sysQuestion.getStatus();
		String queSt="2";
		if(queStatus.equals(queSt)){
			addMessage(redirectAttributes, "问题已关闭,不可转移! ");
			return  "redirect:" + adminPath + "/sysreply/sysReply/list?sysQueId="+sysQueId;
		}
		String userId=sysQuestion.getProSysUserId();
		String userIdT=sysQuestion.getSysUserId();
		User user=UserUtils.getUser();
		if(!userId.equals(user.getId())&&!userIdT.equals(user.getId())){
			addMessage(redirectAttributes, "没有转移该问题的权限! ");
			return  "redirect:" + adminPath + "/sysquestion/sysQuestion/list";
	}
		return "modules/sys/sysquetransfer/sysQueTransferForm";
	}

	/**
	 * 保存问题转移
	 */
	
	@RequiresPermissions(value={"sysquetransfer:sysQueTransfer:add","sysquetransfer:sysQueTransfer:edit"},logical=Logical.OR)
	
	@RequestMapping(value = "save")
	public String save(String sysQueId,SysQueTransfer sysQueTransfer, Model model, RedirectAttributes redirectAttributes) throws Exception{
		User user=UserUtils.getUser();
		Office office=user.getOffice();
		String sysOfficeId=office.getId();
		String sysOfficeName=office.getName();
		SysQuestion sysQuestion=new SysQuestion();
		sysQueTransfer.setSysOfficeId(sysOfficeId);
		sysQueTransfer.setSysOfficeName(sysOfficeName);
		String userId=user.getId();
		String userName=user.getName();
		sysQuestion.setOldUserName(userName);
		sysQuestion.setOldUserId(userId);
		String newUser=sysQueTransfer.getProSysUserId();
		sysQuestion.setId(sysQueId);
		sysQuestion.setNewUserId(newUser);
		sysQuestionService.updateById(sysQuestion);
		sysQueTransferService.save(sysQueTransfer);//新建或者编辑保存
		return "redirect:" + adminPath + "/sysreply/sysReply/list?sysQueId="+sysQueId;
	}
	
	/**
	 * 删除问题转移
	 */
	@ResponseBody
	@RequiresPermissions("sysquetransfer:sysQueTransfer:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SysQueTransfer sysQueTransfer, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sysQueTransferService.delete(sysQueTransfer);
		j.setMsg("删除问题转移成功");
		return j;
	}
	
	/**
	 * 批量删除问题转移
	 */
	@ResponseBody
	@RequiresPermissions("sysquetransfer:sysQueTransfer:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			sysQueTransferService.delete(sysQueTransferService.get(id));
		}
		j.setMsg("删除问题转移成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("sysquetransfer:sysQueTransfer:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SysQueTransfer sysQueTransfer, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "问题转移"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SysQueTransfer> page = sysQueTransferService.findPage(new Page<SysQueTransfer>(request, response, -1), sysQueTransfer);
    		new ExportExcel("问题转移", SysQueTransfer.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出问题转移记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("sysquetransfer:sysQueTransfer:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SysQueTransfer> list = ei.getDataList(SysQueTransfer.class);
			for (SysQueTransfer sysQueTransfer : list){
				try{
					sysQueTransferService.save(sysQueTransfer);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条问题转移记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条问题转移记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入问题转移失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sysquetransfer/sysQueTransfer/?repage";
    }
	
	/**
	 * 下载导入问题转移数据模板
	 */
	@RequiresPermissions("sysquetransfer:sysQueTransfer:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "问题转移数据导入模板.xlsx";
    		List<SysQueTransfer> list = Lists.newArrayList(); 
    		new ExportExcel("问题转移数据", SysQueTransfer.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sysquetransfer/sysQueTransfer/?repage";
    }

}