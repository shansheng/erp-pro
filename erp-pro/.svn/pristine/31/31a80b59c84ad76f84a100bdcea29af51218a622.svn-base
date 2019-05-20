/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsitemtransaction.web;

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
import com.pt.modules.wms.wmsitemtransaction.entity.WmsItemTransaction;
import com.pt.modules.wms.wmsitemtransaction.service.WmsItemTransactionService;

/**
 * 库房事务Controller
 * @author 郑利
 * @version 2018-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/wmsitemtransaction/wmsItemTransaction")
public class WmsItemTransactionController extends BaseController {

	@Autowired
	private WmsItemTransactionService wmsItemTransactionService;
	
	@ModelAttribute
	public WmsItemTransaction get(@RequestParam(required=false) String id) {
		WmsItemTransaction entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wmsItemTransactionService.get(id);
		}
		if (entity == null){
			entity = new WmsItemTransaction();
		}
		return entity;
	}
	
	/**
	 * 库房事务列表页面
	 */
	@RequiresPermissions("wmsitemtransaction:wmsItemTransaction:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/wms/wmsitemtransaction/wmsItemTransactionList";
	}
	
		/**
	 * 库房事务列表数据
	 */
	@ResponseBody
	@RequiresPermissions("wmsitemtransaction:wmsItemTransaction:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WmsItemTransaction wmsItemTransaction, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WmsItemTransaction> page = wmsItemTransactionService.findPage(new Page<WmsItemTransaction>(request, response), wmsItemTransaction); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑库房事务表单页面
	 */
	@RequiresPermissions(value={"wmsitemtransaction:wmsItemTransaction:view","wmsitemtransaction:wmsItemTransaction:add","wmsitemtransaction:wmsItemTransaction:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(WmsItemTransaction wmsItemTransaction, Model model) {
		model.addAttribute("wmsItemTransaction", wmsItemTransaction);
		return "modules/wms/wmsitemtransaction/wmsItemTransactionForm";
	}

	/**
	 * 保存库房事务
	 */
	@ResponseBody
	@RequiresPermissions(value={"wmsitemtransaction:wmsItemTransaction:add","wmsitemtransaction:wmsItemTransaction:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WmsItemTransaction wmsItemTransaction, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, wmsItemTransaction)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		wmsItemTransactionService.save(wmsItemTransaction);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存库房事务成功");
		return j;
	}
	
	/**
	 * 删除库房事务
	 */
	@ResponseBody
	@RequiresPermissions("wmsitemtransaction:wmsItemTransaction:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WmsItemTransaction wmsItemTransaction, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		wmsItemTransactionService.delete(wmsItemTransaction);
		j.setMsg("删除库房事务成功");
		return j;
	}
	
	/**
	 * 批量删除库房事务
	 */
	@ResponseBody
	@RequiresPermissions("wmsitemtransaction:wmsItemTransaction:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			wmsItemTransactionService.delete(wmsItemTransactionService.get(id));
		}
		j.setMsg("删除库房事务成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("wmsitemtransaction:wmsItemTransaction:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WmsItemTransaction wmsItemTransaction, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "库房事务"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WmsItemTransaction> page = wmsItemTransactionService.findPage(new Page<WmsItemTransaction>(request, response, -1), wmsItemTransaction);
    		new ExportExcel("库房事务", WmsItemTransaction.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出库房事务记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("wmsitemtransaction:wmsItemTransaction:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WmsItemTransaction> list = ei.getDataList(WmsItemTransaction.class);
			for (WmsItemTransaction wmsItemTransaction : list){
				try{
					wmsItemTransactionService.save(wmsItemTransaction);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条库房事务记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条库房事务记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入库房事务失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsitemtransaction/wmsItemTransaction/?repage";
    }
	
	/**
	 * 下载导入库房事务数据模板
	 */
	@RequiresPermissions("wmsitemtransaction:wmsItemTransaction:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "库房事务数据导入模板.xlsx";
    		List<WmsItemTransaction> list = Lists.newArrayList(); 
    		new ExportExcel("库房事务数据", WmsItemTransaction.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wmsitemtransaction/wmsItemTransaction/?repage";
    }

}