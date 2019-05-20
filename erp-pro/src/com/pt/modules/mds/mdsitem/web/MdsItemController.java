/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsitem.web;

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
import com.pt.common.utils.DateUtils;
import com.pt.common.utils.MyBeanUtils;
import com.pt.common.config.Global;
import com.pt.common.json.AjaxJson;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;
import com.pt.modules.mds.mdsitem.entity.MdsItem;
import com.pt.modules.mds.mdsitem.service.MdsItemService;

/**
 * 物料管理Controller
 * @author 郑利
 * @version 2018-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/mdsitem/mdsItem")
public class MdsItemController extends BaseController {

	@Autowired
	private MdsItemService mdsItemService;
	
	@ModelAttribute
	public MdsItem get(@RequestParam(required=false) String id) {
		MdsItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsItemService.get(id);
		}
		if (entity == null){
			entity = new MdsItem();
		}
		return entity;
	}
	
	/**
	 * 物料列表页面
	 */
	@RequiresPermissions("mdsitem:mdsItem:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdsitem/mdsItemList";
	}

	
		/**
	 * 物料列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mdsitem:mdsItem:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsItem mdsItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsItem> page = mdsItemService.findPage(new Page<MdsItem>(request, response), mdsItem); 
		return getBootstrapData(page);
	}
	
	/**
	 * 物料列表数据(查询所有物料来源类型为自制的物料）
	 */
	@ResponseBody
	@RequiresPermissions("mdsitem:mdsItem:list")
	@RequestMapping(value = "dataOfSelf")
	public Map<String, Object> dataOfSelf(MdsItem mdsItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		mdsItem.setSourceType("0");
		Page<MdsItem> page = mdsItemService.findPage(new Page<MdsItem>(request, response), mdsItem); 
		return getBootstrapData(page);
	}

	
	/**
	 * 物料列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mdsitem:mdsItem:list")
	@RequestMapping(value = "datas")
	public Map<String, Object> datas(MdsItem mdsItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsItem> page = mdsItemService.findPage(new Page<MdsItem>(request, response), mdsItem); 
		List<MdsItem> list=page.getList();
		List<MdsItem> itemList=new ArrayList<MdsItem>(list.size());
		int it=0;
		for(int i=0;i<list.size();i++){
			if(!"G".equals(list.get(i).getItemType())){
				itemList.add(it, list.get(i));
				it++;
			}
		}
		page.setList(itemList);
		return getBootstrapData(page);
	}
	/**
	 * 查看，增加，编辑物料表单页面
	 */
	@RequiresPermissions(value={"mdsitem:mdsItem:view","mdsitem:mdsItem:add","mdsitem:mdsItem:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MdsItem mdsItem, Model model) {
		model.addAttribute("mdsItem", mdsItem);
		if(StringUtils.isBlank(mdsItem.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/mds/mdsitem/mdsItemForm";
	}

	/**
	 * 保存物料
	 */
	@ResponseBody
	@RequiresPermissions(value={"mdsitem:mdsItem:add","mdsitem:mdsItem:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MdsItem mdsItem, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mdsItem)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		String itemCode=mdsItem.getItemCode();
		String itemName=mdsItem.getItemName();
		String itemId=mdsItem.getId();
		if(mdsItem.getId()!=""&&mdsItem.getId()!=null) {
			List<MdsItem> list=mdsItemService.getByCode(itemCode);
			if(list.size()==1){
				if(!list.get(0).getId().equals(itemId)) {
					j.setSuccess(false);
					j.setMsg("物料编码已存在!");
					return j;
				}
				
			}
			
			List<MdsItem> list1=mdsItemService.getByName(itemName);
			if(list1.size()==1){
				if(!list1.get(0).getId().equals(itemId)) {
				j.setSuccess(false);
				j.setMsg("物料名称已存在!");
				return j;
			}
			}
		}else {
			List<MdsItem> list=mdsItemService.getByCode(itemCode);
			if(list.size()==1){
					j.setSuccess(false);
					j.setMsg("物料编码已存在!");
					return j;
			}
			
			List<MdsItem> list1=mdsItemService.getByName(itemName);
			if(list1.size()==1){
				j.setSuccess(false);
				j.setMsg("物料名称已存在!");
				return j;
			}
		}
		
		
		
	
		mdsItemService.save(mdsItem);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存物料成功");
		return j;
	}
	
	/**
	 * 删除物料
	 */
	@ResponseBody
	@RequiresPermissions("mdsitem:mdsItem:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MdsItem mdsItem, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mdsItemService.delete(mdsItem);
		j.setMsg("删除物料成功");
		return j;
	}
	
	/**
	 * 批量删除物料
	 */
	@ResponseBody
	@RequiresPermissions("mdsitem:mdsItem:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String[] idArray =ids.split(",");
		for(String id : idArray){
			mdsItemService.delete(mdsItemService.get(id));
		}
		j.setMsg("删除物料成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mdsitem:mdsItem:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MdsItem mdsItem, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "物料"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MdsItem> page = mdsItemService.findPage(new Page<MdsItem>(request, response, -1), mdsItem);
    		new ExportExcel("物料", MdsItem.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出物料记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mdsitem:mdsItem:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MdsItem> list = ei.getDataList(MdsItem.class);
			for (MdsItem mdsItem : list){
				try{
					mdsItemService.save(mdsItem);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条物料记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条物料记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入物料失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mdsitem/mdsItem/?repage";
    }
	
	/**
	 * 下载导入物料数据模板
	 */
	@RequiresPermissions("mdsitem:mdsItem:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "物料数据导入模板.xlsx";
    		List<MdsItem> list = Lists.newArrayList(); 
    		new ExportExcel("物料数据", MdsItem.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mdsitem/mdsItem/?repage";
    }

}