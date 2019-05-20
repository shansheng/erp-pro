/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvinbill.web;

import java.util.Date;
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
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.wms.wmsinvinbill.entity.WmsInvInBill;
import com.pt.modules.wms.wmsinvinbill.service.WmsInvInBillService;

/**
 * 入库单Controller
 * @author 龚志强
 * @version 2018-05-15
 */
@Controller
@RequestMapping(value = "${adminPath}/wmsinvinbill/wmsInvInBill")
public class WmsInvInBillController extends BaseController {

	@Autowired
	private WmsInvInBillService wmsInvInBillService;
	
	@ModelAttribute
	public WmsInvInBill get(@RequestParam(required=false) String id) {
		WmsInvInBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wmsInvInBillService.get(id);
		}
		if (entity == null){
			entity = new WmsInvInBill();
		}
		return entity;
	}
	
	/**
	 * 入库单列表页面
	 */
	@RequiresPermissions("wmsinvinbill:wmsInvInBill:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmsinvinbill/wmsInvInBillList";
	}
	
		/**
	 * 入库单列表数据
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvinbill:wmsInvInBill:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsInvInBill wmsInvInBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsInvInBill> page = wmsInvInBillService.findPage(new Page<WmsInvInBill>(request, response), wmsInvInBill); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑入库单表单页面
	 */
	@RequiresPermissions(value={"wmsinvinbill:wmsInvInBill:view","wmsinvinbill:wmsInvInBill:add","wmsinvinbill:wmsInvInBill:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WmsInvInBill wmsInvInBill, Model model) {
		if(wmsInvInBill.getId() == null){
			Map<String, Object> result = wmsInvInBillService.getBillNo(wmsInvInBill);
			wmsInvInBill.setBillNo((String)result.get("o_return_code"));
			wmsInvInBill.setApplySysUserId(UserUtils.getUser().getId());
		}
		model.addAttribute("wmsInvInBill", wmsInvInBill);
		return "modules/wms/wmsinvinbill/wmsInvInBillForm";
	}

	/**
	 * 保存入库单
	 */
	@ResponseBody
	@RequiresPermissions(value={"wmsinvinbill:wmsInvInBill:add","wmsinvinbill:wmsInvInBill:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WmsInvInBill wmsInvInBill, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		
		wmsInvInBill.setApplyDate(new Date());
		wmsInvInBill.setApplySysUserId(UserUtils.getUser().getId());
		
		if(wmsInvInBill.getStatus() == null){
			wmsInvInBill.setStatus("0");
		}
		
		if (!beanValidator(model, wmsInvInBill)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		wmsInvInBillService.save(wmsInvInBill);//保存
		j.setSuccess(true);
		j.setMsg("保存入库单成功");
		return j;
	}
	
	/**
	 * 删除入库单
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvinbill:wmsInvInBill:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WmsInvInBill wmsInvInBill, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		wmsInvInBillService.delete(wmsInvInBill);
		j.setMsg("删除入库单成功");
		return j;
	}
	
	/**
	 * 批量删除入库单
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvinbill:wmsInvInBill:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			wmsInvInBillService.delete(wmsInvInBillService.get(id));
		}
		j.setMsg("删除入库单成功");
		return j;
	}
	
	/**
	 * 批量入库
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvinbill:wmsInvInBill:inBill")
	@RequestMapping(value = "inBill")
	public AjaxJson inBill(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			//wmsInvInBillService.inBill(wmsInvInBillService.get(id));
			wmsInvInBillService.doInInvSubmit(wmsInvInBillService.get(id));
		}
		j.setMsg("入库成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvinbill:wmsInvInBill:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsInvInBill wmsInvInBill, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "入库单"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsInvInBill> page = wmsInvInBillService.findPage(new Page<WmsInvInBill>(request, response, -1), wmsInvInBill);
    		new ExportExcel("入库单", WmsInvInBill.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出入库单记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public WmsInvInBill detail(String id) {
		return wmsInvInBillService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wmsinvinbill:wmsInvInBill:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsInvInBill> list = ei.getDataList(WmsInvInBill.class);
			for (WmsInvInBill wmsInvInBill : list){
				try{
					wmsInvInBillService.save(wmsInvInBill);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条入库单记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条入库单记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入入库单失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsinvinbill/wmsInvInBill/?repage";
    }
	
	/**
	 * 下载导入入库单数据模板
	 */
	@RequiresPermissions("wmsinvinbill:wmsInvInBill:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "入库单数据导入模板.xlsx";
    		List<WmsInvInBill> list = Lists.newArrayList(); 
    		new ExportExcel("入库单数据", WmsInvInBill.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsinvinbill/wmsInvInBill/?repage";
    }
	

}