/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.prickle.web;

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
import com.pt.modules.sys.prickle.entity.Prickle;
import com.pt.modules.sys.prickle.service.PrickleService;

/**
 * 计量单位管理Controller
 * @author 郑利
 * @version 2018-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/prickle/prickle")
public class PrickleController extends BaseController {

	@Autowired
	private PrickleService prickleService;
	
	@ModelAttribute
	public Prickle get(@RequestParam(required=false) String id) {
		Prickle entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = prickleService.get(id);
		}
		if (entity == null){
			entity = new Prickle();
		}
		return entity;
	}
	
	/**
	 * 计量单位列表页面
	 */
	@RequiresPermissions("prickle:prickle:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/sys/prickle/prickleList";
	}
	
		/**
	 * 计量单位列表数据
	 */
	@ResponseBody
	@RequiresPermissions("prickle:prickle:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Prickle prickle, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Prickle> page = prickleService.findPage(new Page<Prickle>(request, response), prickle); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑计量单位表单页面
	 */
	@RequiresPermissions(value={"prickle:prickle:view","prickle:prickle:add","prickle:prickle:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Prickle prickle, Model model) {
		model.addAttribute("prickle", prickle);
		return "modules/sys/prickle/prickleForm";
	}

	/**
	 * 保存计量单位
	 */
	@ResponseBody
	@RequiresPermissions(value={"prickle:prickle:add","prickle:prickle:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Prickle prickle, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, prickle)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		
		prickleService.save(prickle);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存计量单位成功");
		return j;
	}
	
	/**
	 * 删除计量单位
	 */
	@ResponseBody
	@RequiresPermissions("prickle:prickle:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Prickle prickle, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		prickleService.delete(prickle);
		j.setMsg("删除计量单位成功");
		return j;
	}
	
	/**
	 * 批量删除计量单位
	 */
	@ResponseBody
	@RequiresPermissions("prickle:prickle:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			prickleService.delete(prickleService.get(id));
		}
		j.setMsg("删除计量单位成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("prickle:prickle:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Prickle prickle, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "计量单位"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Prickle> page = prickleService.findPage(new Page<Prickle>(request, response, -1), prickle);
    		new ExportExcel("计量单位", Prickle.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出计量单位记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("prickle:prickle:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Prickle> list = ei.getDataList(Prickle.class);
			for (Prickle prickle : list){
				try{
					prickleService.save(prickle);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条计量单位记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条计量单位记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入计量单位失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/prickle/prickle/?repage";
    }
	
	/**
	 * 下载导入计量单位数据模板
	 */
	@RequiresPermissions("prickle:prickle:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "计量单位数据导入模板.xlsx";
    		List<Prickle> list = Lists.newArrayList(); 
    		new ExportExcel("计量单位数据", Prickle.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/prickle/prickle/?repage";
    }

}