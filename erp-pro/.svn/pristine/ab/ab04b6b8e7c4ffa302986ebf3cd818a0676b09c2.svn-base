/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsbom.web;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.pt.common.utils.DateUtils;
import com.pt.common.config.Global;
import com.pt.common.json.AjaxJson;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;
import com.pt.modules.mds.mdsbom.entity.MdsBom;
import com.pt.modules.mds.mdsbom.service.MdsBomService;

/**
 * BOMController
 * @author 龚志强
 * @version 2018-07-14
 */
@Controller
@RequestMapping(value = "${adminPath}/mdsbom/mdsBom")
public class MdsBomController extends BaseController {

	@Autowired
	private MdsBomService mdsBomService;
	
	@ModelAttribute
	public MdsBom get(@RequestParam(required=false) String id) {
		MdsBom entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsBomService.get(id);
		}
		if (entity == null){
			entity = new MdsBom();
		}
		return entity;
	}
	
	/**
	 * BOM列表页面
	 */
	@RequiresPermissions("mdsbom:mdsBom:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdsbom/mdsBomList";
	}
	
	/**
	 * BOM列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mdsbom:mdsBom:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsBom mdsBom, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsBom> page = mdsBomService.findPage(new Page<MdsBom>(request, response), mdsBom); 
		return getBootstrapData(page);
	}
	
	/**
	 * BOM列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mdsbom:mdsBom:list")
	@RequestMapping(value = "treedata")
	public JSONObject treedata(MdsBom mdsBom, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(mdsBom.getMdsItemId() != null && !"".equals(mdsBom.getMdsItemId())){
			List<MdsBom> mdsBomList = mdsBomService.findList(mdsBom);
			
			if(mdsBomList != null && mdsBomList.size() != 0){
				MdsBom mdsBomFirst = mdsBomList.get(0);
				
				JSONObject jsonObj = new JSONObject();
				
				jsonObj.put("id", mdsBomFirst.getId());
				jsonObj.put("text", mdsBomFirst.getItemCode());
				jsonObj.put("parentItemId", mdsBomFirst.getParentItemId());
				jsonObj.put("proMdsItemId", mdsBomFirst.getProMdsItemId());
				jsonObj.put("bomType", mdsBomFirst.getBomType());
				jsonObj.put("children", getChildData(jsonObj));
							
				return jsonObj;
			}
			return null;
		}
		return null;
	}
	
	private JSONArray getChildData(JSONObject mdsBomJson){
		if(mdsBomJson == null){
			return null;
		}
		
		MdsBom mdsBom = new MdsBom();
		
		mdsBom.setParentItemId(mdsBomJson.getString("id"));
		mdsBom.setProMdsItemId(mdsBomJson.getString("proMdsItemId"));
		mdsBom.setBomType(mdsBomJson.getString("bomType"));
		
		List<MdsBom> mdsBomList = mdsBomService.findList(mdsBom);
		
		if(mdsBomList != null && mdsBomList.size() != 0){
			JSONArray jsonArrayTemp = new JSONArray();
			
			for(MdsBom mdsBomTemp : mdsBomList){
				JSONObject mdsBomJsonTemp = new JSONObject();
				
				mdsBomJsonTemp.put("id", mdsBomTemp.getId());
				mdsBomJsonTemp.put("text", mdsBomTemp.getItemCode());
				mdsBomJsonTemp.put("parentItemId", mdsBomTemp.getParentItemId());
				mdsBomJsonTemp.put("proMdsItemId", mdsBomTemp.getProMdsItemId());
				mdsBomJsonTemp.put("bomType", mdsBomTemp.getBomType());
				mdsBomJsonTemp.put("children", getChildData(mdsBomJsonTemp));
				
				jsonArrayTemp.add(mdsBomJsonTemp);
			}
			
			return jsonArrayTemp;
		}
		return null;
	}

	/**
	 * 查看，增加，编辑BOM表单页面
	 */
	@RequiresPermissions(value={"mdsbom:mdsBom:view","mdsbom:mdsBom:add","mdsbom:mdsBom:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MdsBom mdsBom, Model model) {
		model.addAttribute("mdsBom", mdsBom);
		return "modules/mds/mdsbom/mdsBomForm";
	}

	/**
	 * 保存BOM
	 */
	@ResponseBody
	@RequiresPermissions(value={"mdsbom:mdsBom:add","mdsbom:mdsBom:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MdsBom mdsBom, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mdsBom)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		mdsBomService.save(mdsBom);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存BOM成功");
		return j;
	}
	
	/**
	 * 删除BOM
	 */
	@ResponseBody
	@RequiresPermissions("mdsbom:mdsBom:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MdsBom mdsBom, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mdsBomService.delete(mdsBom);
		j.setMsg("删除BOM成功");
		return j;
	}
	
	/**
	 * 批量删除BOM
	 */
	@ResponseBody
	@RequiresPermissions("mdsbom:mdsBom:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mdsBomService.delete(mdsBomService.get(id));
		}
		j.setMsg("删除BOM成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mdsbom:mdsBom:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MdsBom mdsBom, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "BOM"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MdsBom> page = mdsBomService.findPage(new Page<MdsBom>(request, response, -1), mdsBom);
    		new ExportExcel("BOM", MdsBom.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出BOM记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mdsbom:mdsBom:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MdsBom> list = ei.getDataList(MdsBom.class);
			for (MdsBom mdsBom : list){
				try{
					mdsBomService.save(mdsBom);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条BOM记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条BOM记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入BOM失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/modules/mdsbom/mdsBom/?repage";
    }
	
	/**
	 * 下载导入BOM数据模板
	 */
	@RequiresPermissions("mdsbom:mdsBom:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "BOM数据导入模板.xlsx";
    		List<MdsBom> list = Lists.newArrayList(); 
    		new ExportExcel("BOM数据", MdsBom.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/modules/mdsbom/mdsBom/?repage";
    }

}