/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsroute.web;

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
import com.pt.modules.mds.mdsroute.entity.MdsRoute;
import com.pt.modules.mds.mdsroute.service.MdsRouteService;

/**
 * 工艺管理Controller
 * @author 郑利
 * @version 2018-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/mdsroute/mdsRoute")
public class MdsRouteController extends BaseController {

	@Autowired
	private MdsRouteService mdsRouteService;
	
	@ModelAttribute
	public MdsRoute get(@RequestParam(required=false) String id) {
		MdsRoute entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsRouteService.get(id);
		}
		if (entity == null){
			entity = new MdsRoute();
		}
		return entity;
	}
	
	/**
	 * 工艺列表页面
	 */
	@RequiresPermissions("mdsroute:mdsRoute:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdsroute/mdsRouteList";
	}
	
		/**
	 * 工艺列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mdsroute:mdsRoute:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsRoute mdsRoute, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsRoute> page = mdsRouteService.findPage(new Page<MdsRoute>(request, response), mdsRoute); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑工艺表单页面
	 */
	@RequiresPermissions(value={"mdsroute:mdsRoute:view","mdsroute:mdsRoute:add","mdsroute:mdsRoute:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MdsRoute mdsRoute, Model model) {
		model.addAttribute("mdsRoute", mdsRoute);
		return "modules/mds/mdsroute/mdsRouteForm";
	}

	/**
	 * 保存工艺
	 */
	@ResponseBody
	@RequiresPermissions(value={"mdsroute:mdsRoute:add","mdsroute:mdsRoute:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MdsRoute mdsRoute, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mdsRoute)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		mdsRouteService.save(mdsRoute);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存工艺成功");
		return j;
	}
	
	/**
	 * 删除工艺
	 */
	@ResponseBody
	@RequiresPermissions("mdsroute:mdsRoute:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MdsRoute mdsRoute, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mdsRouteService.delete(mdsRoute);
		j.setMsg("删除工艺成功");
		return j;
	}
	
	/**
	 * 批量删除工艺
	 */
	@ResponseBody
	@RequiresPermissions("mdsroute:mdsRoute:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mdsRouteService.delete(mdsRouteService.get(id));
		}
		j.setMsg("删除工艺成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mdsroute:mdsRoute:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MdsRoute mdsRoute, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "工艺"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MdsRoute> page = mdsRouteService.findPage(new Page<MdsRoute>(request, response, -1), mdsRoute);
    		new ExportExcel("工艺", MdsRoute.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出工艺记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mdsroute:mdsRoute:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MdsRoute> list = ei.getDataList(MdsRoute.class);
			for (MdsRoute mdsRoute : list){
				try{
					mdsRouteService.save(mdsRoute);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条工艺记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条工艺记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入工艺失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mdsroute/mdsRoute/?repage";
    }
	
	/**
	 * 下载导入工艺数据模板
	 */
	@RequiresPermissions("mdsroute:mdsRoute:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工艺数据导入模板.xlsx";
    		List<MdsRoute> list = Lists.newArrayList(); 
    		new ExportExcel("工艺数据", MdsRoute.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mdsroute/mdsRoute/?repage";
    }

}