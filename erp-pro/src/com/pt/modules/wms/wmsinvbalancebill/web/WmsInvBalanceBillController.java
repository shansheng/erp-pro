/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvbalancebill.web;

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
import com.pt.modules.wms.wmsinvbalancebill.entity.WmsInvBalanceBill;
import com.pt.modules.wms.wmsinvbalancebill.service.WmsInvBalanceBillService;

/**
 * 库存结转Controller
 * @author hexl
 * @version 2018-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/wmsinvbalancebill/wmsInvBalanceBill")
public class WmsInvBalanceBillController extends BaseController {

	@Autowired
	private WmsInvBalanceBillService wmsInvBalanceBillService;
	
	@ModelAttribute
	public WmsInvBalanceBill get(@RequestParam(required=false) String id) {
		WmsInvBalanceBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wmsInvBalanceBillService.get(id);
		}
		if (entity == null){
			entity = new WmsInvBalanceBill();
		}
		return entity;
	}
	
	/**
	 * 库存结转列表页面
	 */
	@RequiresPermissions("wmsinvbalancebill:wmsInvBalanceBill:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmsinvbalancebill/wmsInvBalanceBillList";
	}
	
		/**
	 * 库存结转列表数据
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvbalancebill:wmsInvBalanceBill:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsInvBalanceBill wmsInvBalanceBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsInvBalanceBill> page = wmsInvBalanceBillService.findPage(new Page<WmsInvBalanceBill>(request, response), wmsInvBalanceBill); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑库存结转表单页面
	 */
	@RequiresPermissions(value={"wmsinvbalancebill:wmsInvBalanceBill:view","wmsinvbalancebill:wmsInvBalanceBill:add","wmsinvbalancebill:wmsInvBalanceBill:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WmsInvBalanceBill wmsInvBalanceBill, Model model) {
		model.addAttribute("wmsInvBalanceBill", wmsInvBalanceBill);
		if(StringUtils.isBlank(wmsInvBalanceBill.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/wms/wmsinvbalancebill/wmsInvBalanceBillForm";
	}

	/**
	 * 保存库存结转
	 */
	@RequiresPermissions(value={"wmsinvbalancebill:wmsInvBalanceBill:add","wmsinvbalancebill:wmsInvBalanceBill:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(WmsInvBalanceBill wmsInvBalanceBill, Model model, RedirectAttributes redirectAttributes) throws Exception{
//		if (!beanValidator(model, wmsInvBalanceBill)){
//			return form(wmsInvBalanceBill, model);
//		}
		//新增或编辑表单保存
		//wmsInvBalanceBillService.save(wmsInvBalanceBill);//保存
		  Map<String, Object> parameterMap = wmsInvBalanceBillService.carryOver(wmsInvBalanceBill); 
		addMessage(redirectAttributes, parameterMap.get("o_Return_String").toString());
		return "redirect:"+Global.getAdminPath()+"/wmsinvbalancebill/wmsInvBalanceBill/?repage";
	}
	
	/**
	 * 库存反结转
	 */
	@ResponseBody
	@RequestMapping(value = "reverse")
	public AjaxJson carryOverReverse(String id, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		Map map = wmsInvBalanceBillService.carryOverReverse(wmsInvBalanceBillService.get(id));
		j.setMsg(map.get("o_Return_String").toString());
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvbalancebill:wmsInvBalanceBill:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsInvBalanceBill wmsInvBalanceBill, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "库存结转"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsInvBalanceBill> page = wmsInvBalanceBillService.findPage(new Page<WmsInvBalanceBill>(request, response, -1), wmsInvBalanceBill);
    		new ExportExcel("库存结转", WmsInvBalanceBill.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出库存结转记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wmsinvbalancebill:wmsInvBalanceBill:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsInvBalanceBill> list = ei.getDataList(WmsInvBalanceBill.class);
			for (WmsInvBalanceBill wmsInvBalanceBill : list){
				try{
					wmsInvBalanceBillService.save(wmsInvBalanceBill);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条库存结转记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条库存结转记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入库存结转失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsinvbalancebill/wmsInvBalanceBill/?repage";
    }
	
	/**
	 * 下载导入库存结转数据模板
	 */
	@RequiresPermissions("wmsinvbalancebill:wmsInvBalanceBill:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "库存结转数据导入模板.xlsx";
    		List<WmsInvBalanceBill> list = Lists.newArrayList(); 
    		new ExportExcel("库存结转数据", WmsInvBalanceBill.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsinvbalancebill/wmsInvBalanceBill/?repage";
    }

}