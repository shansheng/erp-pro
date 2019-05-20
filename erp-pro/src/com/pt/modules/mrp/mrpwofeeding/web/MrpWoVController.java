/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mrp.mrpwofeeding.web;

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
import com.pt.modules.mrp.mrpwofeeding.entity.MrpWoV;
import com.pt.modules.mrp.mrpwofeeding.service.MrpWoVService;

/**
 * 投料Controller
 * @author 郑利
 * @version 2018-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/mrpwofeeding/mrpWoV")
public class MrpWoVController extends BaseController {

	@Autowired
	private MrpWoVService mrpWoVService;
	
	/**
	 * 投料提交
	 */
	@RequestMapping(value = "doSubmit")
	public String doSubmit(String id,Double tlQty,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception{
		mrpWoVService.doSubmit(id,tlQty);
		addMessage(redirectAttributes, "投料成功");
		return "redirect:"+Global.getAdminPath()+"/mrpwofeeding/mrpWoV/?repage";
	}
	
	@RequestMapping(value = "batchMesEwoInsert")
	public String batchMesEwoInsert(String id,Double tlQty,Model model,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		MrpWoV mrpWoV=this.get(id);
		try {
			if(mrpWoV.getTlQty()>tlQty){
				mrpWoVService.batchMesEwoInsert(id,tlQty);
				addMessage(redirectAttributes, "投料成功");		
			}	
		} catch (Exception e) {
			addMessage(redirectAttributes, e.getMessage());
		}
		
		
		return "redirect:"+Global.getAdminPath()+"/mrpwofeeding/mrpWoV/?repage";
	}
	
	@ModelAttribute
	public MrpWoV get(@RequestParam(required=false) String id) {
		MrpWoV entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mrpWoVService.get(id);
		}
		if (entity == null){
			entity = new MrpWoV();
		}
		return entity;
	}
	
	/**
	 * 投料列表页面
	 */
	@RequiresPermissions("mrpwofeeding:mrpWoV:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mrp/mrpwofeeding/mrpWoVList";
	}
	
		/**
	 * 投料列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mrpwofeeding:mrpWoV:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MrpWoV mrpWoV, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MrpWoV> page = mrpWoVService.findPage(new Page<MrpWoV>(request, response), mrpWoV); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑投料表单页面
	 */
	@RequiresPermissions(value={"mrpwofeeding:mrpWoV:view","mrpwofeeding:mrpWoV:add","mrpwofeeding:mrpWoV:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MrpWoV mrpWoV, Model model) {
		model.addAttribute("mrpWoV", mrpWoV);
		return "modules/mrp/mrpwofeeding/mrpWoVForm";
	}

	/**
	 * 保存投料
	 */
	@ResponseBody
	@RequiresPermissions(value={"mrpwofeeding:mrpWoV:add","mrpwofeeding:mrpWoV:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MrpWoV mrpWoV, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mrpWoV)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		mrpWoVService.save(mrpWoV);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存投料成功");
		return j;
	}
	
	/**
	 * 删除投料
	 */
	@ResponseBody
	@RequiresPermissions("mrpwofeeding:mrpWoV:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MrpWoV mrpWoV, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mrpWoVService.delete(mrpWoV);
		j.setMsg("删除投料成功");
		return j;
	}
	
	/**
	 * 批量删除投料
	 */
	@ResponseBody
	@RequiresPermissions("mrpwofeeding:mrpWoV:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mrpWoVService.delete(mrpWoVService.get(id));
		}
		j.setMsg("删除投料成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mrpwofeeding:mrpWoV:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MrpWoV mrpWoV, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "投料"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MrpWoV> page = mrpWoVService.findPage(new Page<MrpWoV>(request, response, -1), mrpWoV);
    		new ExportExcel("投料", MrpWoV.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出投料记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mrpwofeeding:mrpWoV:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MrpWoV> list = ei.getDataList(MrpWoV.class);
			for (MrpWoV mrpWoV : list){
				try{
					mrpWoVService.save(mrpWoV);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条投料记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条投料记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入投料失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mrpwofeeding/mrpWoV/?repage";
    }
	
	/**
	 * 下载导入投料数据模板
	 */
	@RequiresPermissions("mrpwofeeding:mrpWoV:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "投料数据导入模板.xlsx";
    		List<MrpWoV> list = Lists.newArrayList(); 
    		new ExportExcel("投料数据", MrpWoV.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mrpwofeeding/mrpWoV/?repage";
    }

}