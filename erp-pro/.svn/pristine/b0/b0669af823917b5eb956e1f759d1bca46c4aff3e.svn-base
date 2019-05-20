/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsarrivalcheckout.web;

import java.util.ArrayList;
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
import com.pt.common.config.Global;
import com.pt.common.json.AjaxJson;
import com.pt.common.utils.DateUtils;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.modules.mds.mdsarrivalcheckout.entity.MdsArrivalCheckout;
import com.pt.modules.mds.mdsarrivalcheckout.service.MdsArrivalCheckoutService;

/**
 * 到货检验Controller
 * @author cjx
 * @version 2018-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/mds/mdsarrivalcheckout/mdsArrivalCheckout")
public class MdsArrivalCheckoutController extends BaseController {

	@Autowired
	private MdsArrivalCheckoutService mdsArrivalCheckoutService;
	
	@ModelAttribute
	public MdsArrivalCheckout get(@RequestParam(required=false) String id) {
		MdsArrivalCheckout entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsArrivalCheckoutService.get(id);
		}
		if (entity == null){
			entity = new MdsArrivalCheckout();
		}
		return entity;
	}
	
	/**
	 * 到货检验保存列表页面
	 */
	@RequiresPermissions("mds:mdsarrivalcheckout:mdsArrivalCheckout:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdsarrivalcheckout/mdsArrivalCheckoutList";
	}
	
		/**
	 * 到货检验保存列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsarrivalcheckout:mdsArrivalCheckout:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsArrivalCheckout mdsArrivalCheckout, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsArrivalCheckout> page = mdsArrivalCheckoutService.findPage(new Page<MdsArrivalCheckout>(request, response), mdsArrivalCheckout); 
		return getBootstrapData(page);
	}

	/**
	 * 保存到货检验保存
	 */
	@ResponseBody
	@RequiresPermissions(value={"mds:mdsarrivalcheckout:mdsArrivalCheckout:add","mds:mdsarrivalcheckout:mdsArrivalCheckout:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(String date, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		String dateArray[] =date.split(",");
		List<MdsArrivalCheckout> list = new ArrayList<MdsArrivalCheckout>();
		for(String dateString : dateArray){
			MdsArrivalCheckout mds = new MdsArrivalCheckout(dateString,"");
			mds.preUpdate();
			list.add(mds);
		}
		mdsArrivalCheckoutService.save(list);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存到货检验保存成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsarrivalcheckout:mdsArrivalCheckout:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MdsArrivalCheckout mdsArrivalCheckout, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "到货检验保存"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MdsArrivalCheckout> page = mdsArrivalCheckoutService.findPage(new Page<MdsArrivalCheckout>(request, response, -1), mdsArrivalCheckout);
    		new ExportExcel("到货检验保存", MdsArrivalCheckout.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出到货检验保存记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mds:mdsarrivalcheckout:mdsArrivalCheckout:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MdsArrivalCheckout> list = ei.getDataList(MdsArrivalCheckout.class);
			for (MdsArrivalCheckout mdsArrivalCheckout : list){
				try{
					mdsArrivalCheckoutService.save(mdsArrivalCheckout);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条到货检验保存记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条到货检验保存记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入到货检验保存失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mds/mdsarrivalcheckout/mdsArrivalCheckout/?repage";
    }
	
	/**
	 * 下载导入到货检验保存数据模板
	 */
	@RequiresPermissions("mds:mdsarrivalcheckout:mdsArrivalCheckout:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "到货检验保存数据导入模板.xlsx";
    		List<MdsArrivalCheckout> list = Lists.newArrayList(); 
    		new ExportExcel("到货检验保存数据", MdsArrivalCheckout.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mds/mdsarrivalcheckout/mdsArrivalCheckout/?repage";
    }

}