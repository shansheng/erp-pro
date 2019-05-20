/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvbalancebilllist.web;

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
import com.pt.modules.wms.wmsinvbalancebilllist.entity.WmsInvBalanceBillListV;
import com.pt.modules.wms.wmsinvbalancebilllist.service.WmsInvBalanceBillListVService;

/**
 * 结转查询Controller
 * @author hexl
 * @version 2018-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/wmsinvbalancebilllist/wmsInvBalanceBillListV")
public class WmsInvBalanceBillListVController extends BaseController {

	@Autowired
	private WmsInvBalanceBillListVService wmsInvBalanceBillListVService;
	
	@ModelAttribute
	public WmsInvBalanceBillListV get(@RequestParam(required=false) String wmsInvBalanceBillId) {
		
		WmsInvBalanceBillListV entity = new WmsInvBalanceBillListV();
		if (StringUtils.isNotBlank(wmsInvBalanceBillId)){
			entity.setWmsInvBalanceBillId(wmsInvBalanceBillId); 
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmsinvbalancebilllist/wmsInvBalanceBillListVList";
	}
		/**
	 * 结转查询列表数据
	 */
	@ResponseBody
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsInvBalanceBillListV wmsInvBalanceBillListV, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsInvBalanceBillListV> page = wmsInvBalanceBillListVService.findPage(new Page<WmsInvBalanceBillListV>(request, response), wmsInvBalanceBillListV); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑结转查询表单页面
	 */
	@RequiresPermissions(value={"wmsinvbalancebilllist:wmsInvBalanceBillListV:view","wmsinvbalancebilllist:wmsInvBalanceBillListV:add","wmsinvbalancebilllist:wmsInvBalanceBillListV:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WmsInvBalanceBillListV wmsInvBalanceBillListV, Model model) {
		model.addAttribute("wmsInvBalanceBillListV", wmsInvBalanceBillListV);
		if(StringUtils.isBlank(wmsInvBalanceBillListV.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "wms/wmsinvbalancebilllist/wmsInvBalanceBillListVForm";
	}

	/**
	 * 保存结转查询
	 */
	@RequiresPermissions(value={"wmsinvbalancebilllist:wmsInvBalanceBillListV:add","wmsinvbalancebilllist:wmsInvBalanceBillListV:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(WmsInvBalanceBillListV wmsInvBalanceBillListV, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, wmsInvBalanceBillListV)){
			return form(wmsInvBalanceBillListV, model);
		}
		//新增或编辑表单保存
		wmsInvBalanceBillListVService.save(wmsInvBalanceBillListV);//保存
		addMessage(redirectAttributes, "保存结转查询成功");
		return "redirect:"+Global.getAdminPath()+"/wmsinvbalancebilllist/wmsInvBalanceBillListV/?repage";
	}
	
	/**
	 * 删除结转查询
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvbalancebilllist:wmsInvBalanceBillListV:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WmsInvBalanceBillListV wmsInvBalanceBillListV, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		wmsInvBalanceBillListVService.delete(wmsInvBalanceBillListV);
		j.setMsg("删除结转查询成功");
		return j;
	}
	
	/**
	 * 批量删除结转查询
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvbalancebilllist:wmsInvBalanceBillListV:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			wmsInvBalanceBillListVService.delete(wmsInvBalanceBillListVService.get(id));
		}
		j.setMsg("删除结转查询成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvbalancebilllist:wmsInvBalanceBillListV:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsInvBalanceBillListV wmsInvBalanceBillListV, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "结转查询"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsInvBalanceBillListV> page = wmsInvBalanceBillListVService.findPage(new Page<WmsInvBalanceBillListV>(request, response, -1), wmsInvBalanceBillListV);
    		new ExportExcel("结转查询", WmsInvBalanceBillListV.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出结转查询记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wmsinvbalancebilllist:wmsInvBalanceBillListV:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsInvBalanceBillListV> list = ei.getDataList(WmsInvBalanceBillListV.class);
			for (WmsInvBalanceBillListV wmsInvBalanceBillListV : list){
				try{
					wmsInvBalanceBillListVService.save(wmsInvBalanceBillListV);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条结转查询记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条结转查询记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入结转查询失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsinvbalancebilllist/wmsInvBalanceBillListV/?repage";
    }
	
	/**
	 * 下载导入结转查询数据模板
	 */
	@RequiresPermissions("wmsinvbalancebilllist:wmsInvBalanceBillListV:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "结转查询数据导入模板.xlsx";
    		List<WmsInvBalanceBillListV> list = Lists.newArrayList(); 
    		new ExportExcel("结转查询数据", WmsInvBalanceBillListV.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsinvbalancebilllist/wmsInvBalanceBillListV/?repage";
    }

}