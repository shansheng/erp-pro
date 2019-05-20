/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.purshchaselist.web;

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
import org.springframework.web.bind.annotation.RequestBody;
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
import com.pt.modules.mds.purshchaselist.entiry.MdsPurchaseList;
import com.pt.modules.mds.purshchaselist.service.MdsPurchaseListService;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;


/**
 * 批量采购计划Controller
 * @author zjj
 * @version 2018-09-19
 */
@Controller
@RequestMapping(value = "${adminPath}/mds/purchase/mdsPurchaseList")
public class MdsPurchaseListController extends BaseController {

	@Autowired
	private MdsPurchaseListService mdsPurchaseListService;
	
	@ModelAttribute
	public MdsPurchaseList get(@RequestParam(required=false) String id) {
		MdsPurchaseList entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsPurchaseListService.get(id);
		}
		if (entity == null){
			entity = new MdsPurchaseList();
		}
		return entity;
	}
	
	/**
	 * 批量采购计划列表页面
	 */
	@RequiresPermissions("mds:purchase:mdsPurchaseList:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/purchase/mdsPurchaseListList";
	}
	
		/**
	 * 批量采购计划列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:purchase:mdsPurchaseList:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data( MdsPurchaseList mdsPurchaseList, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsPurchaseList> page = mdsPurchaseListService.findPage(new Page<MdsPurchaseList>(request, response), mdsPurchaseList); 
		return getBootstrapData(page);
	}
	@ResponseBody
	@RequiresPermissions("mds:purchase:mdsPurchaseList:view")
	@RequestMapping(value="findByItemId")
    public Map<String,Object> findByItemId(MdsPurchaseList mdsPurchaseList,HttpServletRequest request,HttpServletResponse response,Model model){
    	Page<MdsPurchaseList> page = mdsPurchaseListService.findByMdsItemId(new Page<MdsPurchaseList>(request,response), mdsPurchaseList);
    	return getBootstrapData(page);
    }
	/**
	 * 查看，增加，编辑批量采购计划表单页面
	 */
	@RequiresPermissions(value={"mds:purchase:mdsPurchaseList:view","mds:purchase:mdsPurchaseList:add","mds:purchase:mdsPurchaseList:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MdsPurchaseList mdsPurchaseList, Model model) {
		model.addAttribute("mdsPurchaseList", mdsPurchaseList);
		return "modules/mds/purchase/mdsPurchaseListForm";
	}
	@RequiresPermissions(value="mds:purchase:mdsPurchaseList:view")
	@RequestMapping(value = "findAll")
   public String findAll(MdsPurchaseList mdsPurchaseList,Model model) {
	   model.addAttribute("mdsPurchaseList",mdsPurchaseList);
	   return "modules/mds/purchase/mdsPurchaseListView";
   }
	/**
	 * 保存批量采购计划
	 */
	@ResponseBody
	@RequiresPermissions(value={"mds:purchase:mdsPurchaseList:add","mds:purchase:mdsPurchaseList:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MdsPurchaseList mdsPurchaseList, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mdsPurchaseList)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		mdsPurchaseListService.save(mdsPurchaseList);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存批量采购计划成功");
		return j;
	}
/*	@ResponseBody
	@RequiresPermissions("mds:purchase:mdsPurchaseList:edit")
	@RequestMapping(value="update")
	public AjaxJson update(MdsPurchaseList mdsPurchaseList, Model model, RedirectAttributes redirectAttributes) {
		 AjaxJson j = new AjaxJson();
		 if (!beanValidator(model, mdsPurchaseList)){
				j.setSuccess(false);
				j.setMsg("非法参数！");
				return j;
			}
		 mdsPurchaseListService.update(mdsPurchaseList);
		 j.setSuccess(true);
		 j.setMsg("数据已修改!");
		 return j;
	}*/
	/**
	 * 删除批量采购计划
	 */
	@ResponseBody
	@RequiresPermissions("mds:purchase:mdsPurchaseList:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MdsPurchaseList mdsPurchaseList, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mdsPurchaseListService.delete(mdsPurchaseList);
		j.setMsg("删除批量采购计划成功");
		return j;
	}
	
	/**
	 * 批量删除批量采购计划
	 */
	@ResponseBody
	@RequiresPermissions("mds:purchase:mdsPurchaseList:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mdsPurchaseListService.delete(mdsPurchaseListService.get(id));
		}
		j.setMsg("删除批量采购计划成功");
		return j;
	}
	
	@ResponseBody
	@RequiresPermissions("mds:purchase:mdsPurchaseList:addPurchase")
	@RequestMapping(value="addPurchase")
	public AjaxJson addPurchase(String ids,RedirectAttributes redirectAttribures) {
		AjaxJson j = new AjaxJson();
		String[] idArray = ids.split(",");
		//try {
			mdsPurchaseListService.addPurchase(idArray);
			//j.setMsg("添加成功!");
		//} catch (Exception e) {
			//j.setMsg("操作失败");
		//}
        return j;
	}
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mds:purchase:mdsPurchaseList:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MdsPurchaseList mdsPurchaseList, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "批量采购计划"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MdsPurchaseList> page = mdsPurchaseListService.findPage(new Page<MdsPurchaseList>(request, response, -1), mdsPurchaseList);
    		new ExportExcel("批量采购计划", MdsPurchaseList.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出批量采购计划记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mds:purchase:mdsPurchaseList:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MdsPurchaseList> list = ei.getDataList(MdsPurchaseList.class);
			for (MdsPurchaseList mdsPurchaseList : list){
				try{
					mdsPurchaseListService.save(mdsPurchaseList);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条批量采购计划记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条批量采购计划记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入批量采购计划失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mds/purchase/mdsPurchaseList/?repage";
    }
	
	/**
	 * 下载导入批量采购计划数据模板
	 */
	@RequiresPermissions("mds:purchase:mdsPurchaseList:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "批量采购计划数据导入模板.xlsx";
    		List<MdsPurchaseList> list = Lists.newArrayList(); 
    		new ExportExcel("批量采购计划数据", MdsPurchaseList.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mds/purchase/mdsPurchaseList/?repage";
    }

}