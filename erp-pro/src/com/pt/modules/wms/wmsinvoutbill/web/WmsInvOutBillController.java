/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvoutbill.web;

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
import com.pt.modules.wms.wmsinvoutbill.entity.WmsInvOutBill;
import com.pt.modules.wms.wmsinvoutbill.service.WmsInvOutBillService;

/**
 * 出库管理Controller
 * @author 龚志强
 * @version 2018-05-21
 */
@Controller
@RequestMapping(value = "${adminPath}/wmsinvoutbill/wmsInvOutBill")
public class WmsInvOutBillController extends BaseController {

	@Autowired
	private WmsInvOutBillService wmsInvOutBillService;
	
	@ModelAttribute
	public WmsInvOutBill get(@RequestParam(required=false) String id) {
		WmsInvOutBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wmsInvOutBillService.get(id);
		}
		if (entity == null){
			entity = new WmsInvOutBill();
		}
		return entity;
	}
	
	/**
	 * 出库单列表页面
	 */
	@RequiresPermissions("wmsinvoutbill:wmsInvOutBill:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmsinvoutbill/wmsInvOutBillList";
	}
	
		/**
	 * 出库单列表数据
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvoutbill:wmsInvOutBill:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsInvOutBill wmsInvOutBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsInvOutBill> page = wmsInvOutBillService.findPage(new Page<WmsInvOutBill>(request, response), wmsInvOutBill); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑出库单表单页面
	 */
	@RequiresPermissions(value={"wmsinvoutbill:wmsInvOutBill:view","wmsinvoutbill:wmsInvOutBill:add","wmsinvoutbill:wmsInvOutBill:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WmsInvOutBill wmsInvOutBill, Model model) {
		if(wmsInvOutBill.getId() == null){
			Map<String, Object> result = wmsInvOutBillService.getBillNo(wmsInvOutBill);
			wmsInvOutBill.setBillNo((String)result.get("o_return_code"));
			wmsInvOutBill.setApplySysUserId(UserUtils.getUser().getId());
		}
		model.addAttribute("wmsInvOutBill", wmsInvOutBill);
		return "modules/wms/wmsinvoutbill/wmsInvOutBillForm";
	}

	/**
	 * 保存出库单
	 */
	@ResponseBody
	@RequiresPermissions(value={"wmsinvoutbill:wmsInvOutBill:add","wmsinvoutbill:wmsInvOutBill:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WmsInvOutBill wmsInvOutBill, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		
		wmsInvOutBill.setApplyDate(new Date());
		wmsInvOutBill.setApplySysUserId(UserUtils.getUser().getId());
		
		if(wmsInvOutBill.getStatus() == null){
			wmsInvOutBill.setStatus("0");
		}
		
		if (!beanValidator(model, wmsInvOutBill)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		wmsInvOutBillService.save(wmsInvOutBill);//保存
		j.setSuccess(true);
		j.setMsg("保存出库单成功");
		return j;
	}
	
	/**
	 * 删除出库单
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvoutbill:wmsInvOutBill:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WmsInvOutBill wmsInvOutBill, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		wmsInvOutBillService.delete(wmsInvOutBill);
		j.setMsg("删除出库单成功");
		return j;
	}
	
	/**
	 * 批量删除出库单
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvoutbill:wmsInvOutBill:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			wmsInvOutBillService.delete(wmsInvOutBillService.get(id));
		}
		j.setMsg("删除出库单成功");
		return j;
	}
	
	
	/**
	 * 批量出库
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvoutbill:wmsInvOutBill:outBill")
	@RequestMapping(value = "outBill")
	public AjaxJson outBill(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try{
			String idArray[] =ids.split(",");
			for(String id : idArray){
				wmsInvOutBillService.doOutInvSubmit(wmsInvOutBillService.get(id));
			}
			j.setMsg("出库成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("出库失败！失败信息："+e.getMessage());
		}
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wmsinvoutbill:wmsInvOutBill:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsInvOutBill wmsInvOutBill, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "出库单"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsInvOutBill> page = wmsInvOutBillService.findPage(new Page<WmsInvOutBill>(request, response, -1), wmsInvOutBill);
    		new ExportExcel("出库单", WmsInvOutBill.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出出库单记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public WmsInvOutBill detail(String id) {
		return wmsInvOutBillService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wmsinvoutbill:wmsInvOutBill:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsInvOutBill> list = ei.getDataList(WmsInvOutBill.class);
			for (WmsInvOutBill wmsInvOutBill : list){
				try{
					wmsInvOutBillService.save(wmsInvOutBill);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条出库单记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条出库单记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入出库单失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsinvoutbill/wmsInvOutBill/?repage";
    }
	
	/**
	 * 下载导入出库单数据模板
	 */
	@RequiresPermissions("wmsinvoutbill:wmsInvOutBill:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "出库单数据导入模板.xlsx";
    		List<WmsInvOutBill> list = Lists.newArrayList(); 
    		new ExportExcel("出库单数据", WmsInvOutBill.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsinvoutbill/wmsInvOutBill/?repage";
    }
	

}