/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsquota.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

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
import com.google.common.collect.Maps;
import com.pt.common.config.Global;
import com.pt.common.json.AjaxJson;
import com.pt.common.utils.DateUtils;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.modules.mds.mdsquota.entity.DictOfTree;
import com.pt.modules.mds.mdsquota.entity.MdsQuota;
import com.pt.modules.mds.mdsquota.entity.MdsQuotaDetail;
import com.pt.modules.mds.mdsquota.service.MdsQuotaDetailService;
import com.pt.modules.mds.mdsquota.service.MdsQuotaService;

/**
 * 材料定额管理Controller
 * @author cjx
 * @version 2018-09-11
 */
@Controller
@RequestMapping(value = "${adminPath}/mds/mdsquota/mdsQuota")
public class MdsQuotaController extends BaseController {

	@Autowired
	private MdsQuotaService mdsQuotaService;
	
	@Autowired
	private MdsQuotaDetailService mdsQuotaDetailService;
	
	@ModelAttribute
	public MdsQuota get(@RequestParam(required=false) String id) {
		MdsQuota entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsQuotaService.get(id);
		}
		if (entity == null){
			entity = new MdsQuota();
		}
		return entity;
	}
	
	/**
	 * 材料定额列表页面
	 */
	@RequiresPermissions("mds:mdsquota:mdsQuota:list")
	@RequestMapping(value = {"list", ""})
	public String list(MdsQuota mdsQuota, Model model) {
		model.addAttribute("mdsQuota", mdsQuota);
		return "modules/mds/mdsquota/mdsQuotaList";
	}
	
	/**
	 * 主页查看材料定额表单页面
	 */
	@RequiresPermissions("mds:mdsquota:mdsQuota:view")
	@RequestMapping(value = "show")
	public String show(MdsQuotaDetail mdsQuotaDetail, Model model) {
		model.addAttribute("mdsQuotaDetail", mdsQuotaDetail);
		return "modules/mds/mdsquota/mdsQuotaDetailView";
	}
	
	/**
	 * 显示第二主页
	 */
	@RequiresPermissions("mds:mdsquota:mdsQuota:edit")
	@RequestMapping(value = "edit")
	public String edit(MdsQuotaDetail mdsQuotaDetail, Model model) {
		//修改订单状态为修改中
		if("10".equals(mdsQuotaDetail.getColumn2())){
			mdsQuotaDetail.setColumn2("20");
			mdsQuotaService.update(mdsQuotaDetail);
		}
		model.addAttribute("mdsQuotaDetail", mdsQuotaDetail);
		return "modules/mds/mdsquota/mdsQuotaDetail";
	}
	
	/**
	 * 显示主页
	 * 材料定额列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsquota:mdsQuota:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(MdsQuota mdsQuota, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsQuota> page = mdsQuotaService.findPage(new Page<MdsQuota>(request, response), mdsQuota); 
		return getBootstrapData(page);
	}
	
	
	/**
	 * 显示第二主页数据
	 * 材料定额详情列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsquota:mdsQuota:list")
	@RequestMapping(value = "detailData")
	public Map<String, Object> detailData(MdsQuotaDetail mdsQuotaDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsQuotaDetail> page = mdsQuotaDetailService.findPage(new Page<MdsQuotaDetail>(request, response), mdsQuotaDetail); 
		return getBootstrapData(page);
	}
	
	/**
	 * 主页编制完成
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsquota:mdsQuota:compile")
	@RequestMapping(value = "compile")
	public AjaxJson compile(String id) {
		AjaxJson j = new AjaxJson();
		mdsQuotaService.compile(new MdsQuota(id));
		j.setMsg("材料定额编制完成");
		return j;
	}
	
	/**
	 * 主页撤销编制
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsquota:mdsQuota:unCompile")
	@RequestMapping(value = "unCompile")
	public AjaxJson unCompile(String id) {
		AjaxJson j = mdsQuotaService.unCompile(new MdsQuota(id));
		return j;
	}
	
	
	/**
	 * 主页定版材料定额
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsquota:mdsQuota:confirm")
	@RequestMapping(value = "confirm")
	public AjaxJson confirm(String id) {
		AjaxJson j = new AjaxJson();
		mdsQuotaService.confirm(get(id));
		j.setMsg("材料定额定版成功");
		return j;
	}
	
/*	*//**
	 * 主页撤销定版材料定额
	 *//*
	@ResponseBody
	@RequiresPermissions("mds:mdsquota:mdsQuota:delconfirm")
	@RequestMapping(value = "delconfirm")
	public AjaxJson delconfirm(String id) {
		MdsQuota mdsQuota = new MdsQuota();
		mdsQuota.setId(id);
		AjaxJson j = mdsQuotaService.delconfirm(mdsQuota);
		return j;
	}*/
	
	/**
	 * 分类数据
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "bootstrapTreeData")
	public List<Map<String, Object>> bootstrapTreeData(MdsQuota mdsQuota,HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Map<String, Object> map = Maps.newHashMap();
		map.put("id", "");
		map.put("name", "分类");
		map.put("level", 1);
		map.put("text", "分类");
		deepTree(map,mdsQuota);
		mapList.add(map);
		return mapList;
	}
	
	public void deepTree(Map<String, Object> map,MdsQuota mdsQuota){
		
		List<Map<String, Object>> arra = new ArrayList<Map<String, Object>>();
		for(DictOfTree child:mdsQuotaDetailService.getChildren(mdsQuota)){
			Map<String, Object> childMap = Maps.newHashMap();
			childMap.put("id", child.getValue());
			childMap.put("name", child.getDictName());
			childMap.put("text", child.getDictName());
			arra.add(childMap);
		}
		map.put("children", arra);
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsquota:mdsQuota:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MdsQuota mdsQuota, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "材料定额"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MdsQuota> page = mdsQuotaService.findPage(new Page<MdsQuota>(request, response, -1), mdsQuota);
    		new ExportExcel("材料定额", MdsQuota.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出材料定额记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public MdsQuota detail(String id) {
		return mdsQuotaService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mds:mdsquota:mdsQuota:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MdsQuota> list = ei.getDataList(MdsQuota.class);
			for (MdsQuota mdsQuota : list){
				try{
					mdsQuotaService.save(mdsQuota);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条材料定额记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条材料定额记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入材料定额失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mds/mdsquota/mdsQuota/?repage";
    }
	
	/**
	 * 下载导入材料定额数据模板
	 */
	@RequiresPermissions("mds:mdsquota:mdsQuota:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "材料定额数据导入模板.xlsx";
    		List<MdsQuota> list = Lists.newArrayList(); 
    		new ExportExcel("材料定额数据", MdsQuota.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mds/mdsquota/mdsQuota/?repage";
    }
	

}