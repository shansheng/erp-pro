/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsequipment.web;

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
import com.pt.modules.mds.mdsequipment.entity.MdsEquipment;
import com.pt.modules.mds.mdsequipment.service.MdsEquipmentService;

/**
 * 设备管理Controller
 * @author 郑利
 * @version 2018-05-26
 */
@Controller
@RequestMapping(value = "${adminPath}/mdsequipment/mdsEquipment")
public class MdsEquipmentController extends BaseController {

	@Autowired
	private MdsEquipmentService mdsEquipmentService;
	
	@ModelAttribute
	public MdsEquipment get(@RequestParam(required=false) String id) {
		MdsEquipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsEquipmentService.get(id);
		}
		if (entity == null){
			entity = new MdsEquipment();
		}
		return entity;
	}
	
	/**
	 * 设备列表页面
	 */
	@RequiresPermissions("mdsequipment:mdsEquipment:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdsequipment/mdsEquipmentList";
	}
	
		/**
	 * 设备列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mdsequipment:mdsEquipment:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsEquipment mdsEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsEquipment> page = mdsEquipmentService.findPage(new Page<MdsEquipment>(request, response), mdsEquipment); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑设备表单页面
	 */
	@RequiresPermissions(value={"mdsequipment:mdsEquipment:view","mdsequipment:mdsEquipment:add","mdsequipment:mdsEquipment:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MdsEquipment mdsEquipment, Model model) {
		model.addAttribute("mdsEquipment", mdsEquipment);
		return "modules/mds/mdsequipment/mdsEquipmentForm";
	}

	/**
	 * 保存设备
	 */
	@ResponseBody
	@RequiresPermissions(value={"mdsequipment:mdsEquipment:add","mdsequipment:mdsEquipment:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MdsEquipment mdsEquipment, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mdsEquipment)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		mdsEquipmentService.save(mdsEquipment);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存设备成功");
		return j;
	}
	
	/**
	 * 删除设备
	 */
	@ResponseBody
	@RequiresPermissions("mdsequipment:mdsEquipment:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MdsEquipment mdsEquipment, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mdsEquipmentService.delete(mdsEquipment);
		j.setMsg("删除设备成功");
		return j;
	}
	
	/**
	 * 批量删除设备
	 */
	@ResponseBody
	@RequiresPermissions("mdsequipment:mdsEquipment:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mdsEquipmentService.delete(mdsEquipmentService.get(id));
		}
		j.setMsg("删除设备成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mdsequipment:mdsEquipment:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MdsEquipment mdsEquipment, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "设备"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MdsEquipment> page = mdsEquipmentService.findPage(new Page<MdsEquipment>(request, response, -1), mdsEquipment);
    		new ExportExcel("设备", MdsEquipment.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出设备记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mdsequipment:mdsEquipment:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MdsEquipment> list = ei.getDataList(MdsEquipment.class);
			for (MdsEquipment mdsEquipment : list){
				try{
					mdsEquipmentService.save(mdsEquipment);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条设备记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条设备记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入设备失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mdsequipment/mdsEquipment/?repage";
    }
	
	/**
	 * 下载导入设备数据模板
	 */
	@RequiresPermissions("mdsequipment:mdsEquipment:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "设备数据导入模板.xlsx";
    		List<MdsEquipment> list = Lists.newArrayList(); 
    		new ExportExcel("设备数据", MdsEquipment.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mdsequipment/mdsEquipment/?repage";
    }

}