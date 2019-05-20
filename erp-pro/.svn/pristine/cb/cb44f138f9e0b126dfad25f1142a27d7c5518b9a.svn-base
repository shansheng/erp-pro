/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matbom.web;


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
import com.pt.modules.mat.matbom.entity.MatBom;
import com.pt.modules.mat.matbom.entity.MatBomDetail;

import com.pt.modules.mat.matbom.service.MatBomService;


/**
 * mat_bomController
 * @author 郑利
 * @version 2018-09-27
 */
@Controller
@RequestMapping(value = "${adminPath}/matbom/matBom")
public class MatBomController extends BaseController {

	@Autowired
	private MatBomService matBomService;

	
	
	@ModelAttribute
	public MatBom get(@RequestParam(required=false) String id) {
		MatBom entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = matBomService.get(id);
		}
		if (entity == null){
			entity = new MatBom();
		}
		return entity;
	}
	
	
	/**
	 * 主页查看材料定额表单页面
	 */
	@RequiresPermissions("matbom:matBom:view")
	@RequestMapping(value = "show")
	public String show(String id,MatBomDetail  matBomDetail, Model model) {
		matBomDetail.setMatBomId(id);
		if("10".equals(matBomDetail.getColumn4())){
			matBomDetail.setColumn4("20");
			matBomService.update(matBomDetail);
		}
		String matBomId=matBomDetail.getMatBomId();
		MatBom matBom=matBomService.get(matBomId);
		String sysMarketListId=matBom.getSysMarketListId();
		model.addAttribute("sysMarketListId", sysMarketListId);
		model.addAttribute("matBomDetail", matBomDetail);
		return "modules/mat/matbom/matBomListView";
	}

	/**
	 * 主页编辑Bom表单页面
	 */
	@RequiresPermissions("matBomIndex:matBomIndex:edit")
	@RequestMapping(value = "edit")
	public String edit(String id,MatBomDetail matBomDetail, Model model) {
		matBomDetail.setMatBomId(id);
		if("10".equals(matBomDetail.getColumn4())){
			matBomDetail.setColumn4("20");
			matBomService.update(matBomDetail);
		}
		String matBomId=matBomDetail.getMatBomId();
		MatBom matBom=matBomService.get(matBomId);
		String sysMarketListId=matBom.getSysMarketListId();
		model.addAttribute("sysMarketListId", sysMarketListId);
		model.addAttribute("matBomDetail", matBomDetail);
		return "modules/mat/matbom/matBomList";
	}
	

	
	

	/**
	 * 主页定版bom
	 */
	@ResponseBody
	@RequiresPermissions("matBomIndex:matBomIndex:confirm")
	@RequestMapping(value = "confirm")
	public AjaxJson confirm(String id) {
		AjaxJson j = new AjaxJson();
		MatBom matBom=new MatBom();
		matBom.setId(id);
		matBomService.confirm(matBom);
		j.setMsg("bom定版成功");
		return j;
	}
	
	/**
	 * 主页撤销定版bom
	 */
	@ResponseBody
	@RequiresPermissions("matBomIndex:matBomIndex:delconfirm")
	@RequestMapping(value = "delconfirm")
	public AjaxJson delconfirm(String id) {
		AjaxJson j = new AjaxJson();
		MatBom matBom=new MatBom();
		matBom.setId(id);
		matBomService.delconfirm(matBom);
		j.setMsg("撤销定版成功");
		return j;
	}
	


	
	/**
	 * mat_bom列表页面
	 */
	@RequiresPermissions("matbom:matBom:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mat/matbom/matBomIndexList";
	}
	
		/**
	 * mat_bom列表数据
	 */
	@ResponseBody
	@RequiresPermissions("matbom:matBom:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MatBom matBom, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MatBom> page = matBomService.findPage(new Page<MatBom>(request, response), matBom); 
		return getBootstrapData(page);
	}

	


	/**
	 * 查看，增加，编辑mat_bom表单页面
	 */
	@RequiresPermissions(value={"matbom:matBom:view","matbom:matBom:add","matbom:matBom:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MatBom matBom, Model model) {
		model.addAttribute("matBom", matBom);
		if(StringUtils.isBlank(matBom.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/mat/matbom/matBomForm";
	}

	/**
	 * 保存mat_bom
	 */
	@RequiresPermissions(value={"matbom:matBom:add","matbom:matBom:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MatBom matBom, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, matBom)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		matBomService.save(matBom);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存物料成功");
		return j;
		
		
	}
	
	/**
	 * 删除bom
	 */
	@ResponseBody
	@RequiresPermissions("matbom:matBom:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MatBom matBom, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		matBomService.delete(matBom);
		j.setMsg("删除bom成功");
		return j;
	}
	
	/**
	 * 批量删除mat_bom
	 */
	@ResponseBody
	@RequiresPermissions("matbom:matBom:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			matBomService.delete(matBomService.get(id));
		}
		j.setMsg("删除mat_bom成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("matbom:matBom:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MatBom matBom, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "mat_bom"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MatBom> page = matBomService.findPage(new Page<MatBom>(request, response, -1), matBom);
    		new ExportExcel("mat_bom", MatBom.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出mat_bom记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("matbom:matBom:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MatBom> list = ei.getDataList(MatBom.class);
			for (MatBom matBom : list){
				try{
					matBomService.save(matBom);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条mat_bom记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条mat_bom记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入mat_bom失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/matbom/matBom/?repage";
    }
	
	/**
	 * 下载导入mat_bom数据模板
	 */
	@RequiresPermissions("matbom:matBom:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "mat_bom数据导入模板.xlsx";
    		List<MatBom> list = Lists.newArrayList(); 
    		new ExportExcel("mat_bom数据", MatBom.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/matbom/matBom/?repage";
    }

}