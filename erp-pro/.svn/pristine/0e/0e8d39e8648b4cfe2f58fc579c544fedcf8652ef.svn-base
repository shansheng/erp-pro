/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsoperationseq.web;

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
import com.pt.modules.mds.mdsoperationseq.entity.MdsOperationSeq;
import com.pt.modules.mds.mdsoperationseq.service.MdsOperationSeqService;

/**
 * 工序管理Controller
 * @author 郑利
 * @version 2018-05-19
 */
@Controller
@RequestMapping(value = "${adminPath}/mdsoperationseq/mdsOperationSeq")
public class MdsOperationSeqController extends BaseController {

	@Autowired
	private MdsOperationSeqService mdsOperationSeqService;
	
	@ModelAttribute
	public MdsOperationSeq get(@RequestParam(required=false) String id) {
		MdsOperationSeq entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsOperationSeqService.get(id);
		}
		if (entity == null){
			entity = new MdsOperationSeq();
		}
		return entity;
	}
	
	/**
	 * 工序列表页面
	 */
	
	@RequestMapping(value = {"list", ""})
	public String list(String mdsRouteId,Model model) {
		model.addAttribute("mdsRouteId",mdsRouteId);
		
		return "modules/mds/mdsoperationseq/mdsOperationSeqList";
	}
	
		/**
	 * 工序列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mdsoperationseq:mdsOperationSeq:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsOperationSeq mdsOperationSeq, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsOperationSeq> page = mdsOperationSeqService.findPage(new Page<MdsOperationSeq>(request, response), mdsOperationSeq); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑工序表单页面
	 */
	@RequiresPermissions(value={"mdsoperationseq:mdsOperationSeq:view","mdsoperationseq:mdsOperationSeq:add","mdsoperationseq:mdsOperationSeq:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MdsOperationSeq mdsOperationSeq, Model model) {
		model.addAttribute("mdsOperationSeq", mdsOperationSeq);
		return "modules/mds/mdsoperationseq/mdsOperationSeqForm";
	}

	/**
	 * 保存工序
	 */
	@ResponseBody
	@RequiresPermissions(value={"mdsoperationseq:mdsOperationSeq:add","mdsoperationseq:mdsOperationSeq:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MdsOperationSeq mdsOperationSeq, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mdsOperationSeq)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		mdsOperationSeqService.save(mdsOperationSeq);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存工序成功");
		return j;
	}
	
	/**
	 * 删除工序
	 */
	@ResponseBody
	@RequiresPermissions("mdsoperationseq:mdsOperationSeq:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MdsOperationSeq mdsOperationSeq, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mdsOperationSeqService.delete(mdsOperationSeq);
		j.setMsg("删除工序成功");
		return j;
	}
	
	/**
	 * 批量删除工序
	 */
	@ResponseBody
	@RequiresPermissions("mdsoperationseq:mdsOperationSeq:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mdsOperationSeqService.delete(mdsOperationSeqService.get(id));
		}
		j.setMsg("删除工序成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mdsoperationseq:mdsOperationSeq:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MdsOperationSeq mdsOperationSeq, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "工序"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MdsOperationSeq> page = mdsOperationSeqService.findPage(new Page<MdsOperationSeq>(request, response, -1), mdsOperationSeq);
    		new ExportExcel("工序", MdsOperationSeq.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出工序记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mdsoperationseq:mdsOperationSeq:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MdsOperationSeq> list = ei.getDataList(MdsOperationSeq.class);
			for (MdsOperationSeq mdsOperationSeq : list){
				try{
					mdsOperationSeqService.save(mdsOperationSeq);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条工序记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条工序记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入工序失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mdsoperationseq/mdsOperationSeq/?repage";
    }
	
	/**
	 * 下载导入工序数据模板
	 */
	@RequiresPermissions("mdsoperationseq:mdsOperationSeq:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工序数据导入模板.xlsx";
    		List<MdsOperationSeq> list = Lists.newArrayList(); 
    		new ExportExcel("工序数据", MdsOperationSeq.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mdsoperationseq/mdsOperationSeq/?repage";
    }

	
	/**
	 * 工序上移
	 * @param mdsOperationSeq
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value={"mdsoperationseq:mdsOperationSeq:add","mdsoperationseq:mdsOperationSeq:edit"},logical=Logical.OR)
	@RequestMapping(value = "doBefore")
	public AjaxJson doBeforeSeq(MdsOperationSeq mdsOperationSeq, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "工序调整成功";
		try {
			Map<String, Object> result = mdsOperationSeqService.doBeforeSeq(mdsOperationSeq);
			if((Integer)result.get("o_Return_Int") == 1){ //失败
				message = "工序调整失败：" + (String)result.get("o_Return_String");
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			message = "工序调整失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 工序下移
	 * @param mdsOperationSeq
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value={"mdsoperationseq:mdsOperationSeq:add","mdsoperationseq:mdsOperationSeq:edit"},logical=Logical.OR)
	@RequestMapping(value = "doAfter")
	public AjaxJson doAfterSeq(MdsOperationSeq mdsOperationSeq, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "工序调整成功";
		try {
			Map<String, Object> result = mdsOperationSeqService.doAfterSeq(mdsOperationSeq);
			if((Integer)result.get("o_Return_Int") == 1){ //失败
				message = "工序调整失败：" + (String)result.get("o_Return_String");
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			message = "工序调整失败";
		}
		j.setMsg(message);
		return j;
	}
	
}