/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.templan.templanapplication.web;

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
import org.springframework.web.bind.annotation.GetMapping;
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
import com.pt.modules.templan.templanapplication.entity.TestTemporaryPlan;
import com.pt.modules.templan.templanapplication.service.TestTemporaryPlanService;

/**
 * 临时计划申请Controller
 * @author cjx
 * @version 2018-09-04
 */
@Controller
@RequestMapping(value = "${adminPath}/templan/testTemporaryPlan")
public class TestTemporaryPlanController extends BaseController {

	@Autowired
	private TestTemporaryPlanService testTemporaryPlanService;
	
	@ModelAttribute
	public TestTemporaryPlan get(@RequestParam(required=false) String id) {
		TestTemporaryPlan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testTemporaryPlanService.get(id);
		}
		if (entity == null){
			entity = new TestTemporaryPlan();
		}
		return entity;
	}
	
	/**
	 * 临时计划申请表列表页面
	 */
	@RequiresPermissions("templan:testTemporaryPlan:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/templan/templanapplication/testTemporaryPlanList";
	}
	
		/**
	 * 临时计划申请表列表数据
	 */
	@ResponseBody
	@RequiresPermissions("templan:testTemporaryPlan:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(TestTemporaryPlan testTemporaryPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestTemporaryPlan> page = testTemporaryPlanService.findPage(new Page<TestTemporaryPlan>(request, response), testTemporaryPlan); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑临时计划申请表表单页面
	 */
	@RequiresPermissions(value={"templan:testTemporaryPlan:view","templan:testTemporaryPlan:add","templan:testTemporaryPlan:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(TestTemporaryPlan testTemporaryPlan, Model model) {
		model.addAttribute("testTemporaryPlan", testTemporaryPlan);
		return "modules/templan/templanapplication/testTemporaryPlanForm";
	}
	
	/**
	 * 校验领料单单号
	 */
	@ResponseBody
	@RequiresPermissions("templan:testTemporaryPlan:add")
	@RequestMapping(value = "checkCode")
	public AjaxJson checkCode(String code, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		TestTemporaryPlan testTemporaryPlan=testTemporaryPlanService.getByCode(code);
		if(testTemporaryPlan!=null){
			j.setSuccess(false);
			j.setMsg("临时计划申请单号不能重复!");
			return j;
		}

		j.setSuccess(true);
		return j;
	}

	/**
	 * 保存临时计划申请表
	 */
	@ResponseBody
	@RequiresPermissions(value={"templan:testTemporaryPlan:add","templan:testTemporaryPlan:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(TestTemporaryPlan testTemporaryPlan, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, testTemporaryPlan)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		testTemporaryPlanService.save(testTemporaryPlan);//保存
		j.setSuccess(true);
		j.setMsg("保存临时计划申请表成功");
		return j;
	}
	
	/**
     * 提交申请单
     */
	@ResponseBody
	@RequiresPermissions(value={"templan:testTemporaryPlan:doSubmit"},logical=Logical.OR)
	@RequestMapping(value = "doSubmit")
    public AjaxJson doSubmit(TestTemporaryPlan testTemporaryPlan,RedirectAttributes redirectAttributes) throws Exception{
    	AjaxJson j = new AjaxJson();
    	if (StringUtils.isNotBlank(testTemporaryPlan.getId())){
    		testTemporaryPlanService.doSubmit(testTemporaryPlan);
    		j.setSuccess(true);
    		j.setMsg("提交临时计划申请表成功");
    	}else{
    		j.setSuccess(false);
    		j.setMsg("提交临时计划申请表失败");
    	}
    	return j;
    }
	
	/**
	 * 删除临时计划申请表
	 */
	@ResponseBody
	@RequiresPermissions("templan:testTemporaryPlan:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(TestTemporaryPlan testTemporaryPlan, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		testTemporaryPlanService.delete(testTemporaryPlan);
		j.setMsg("删除临时计划申请表成功");
		return j;
	}
	
	/**
	 * 批量删除临时计划申请表
	 */
	@ResponseBody
	@RequiresPermissions("templan:testTemporaryPlan:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			testTemporaryPlanService.delete(testTemporaryPlanService.get(id));
		}
		j.setMsg("删除临时计划申请表成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("templan:testTemporaryPlan:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(TestTemporaryPlan testTemporaryPlan, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "临时计划申请表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TestTemporaryPlan> page = testTemporaryPlanService.findPage(new Page<TestTemporaryPlan>(request, response, -1), testTemporaryPlan);
    		new ExportExcel("临时计划申请表", TestTemporaryPlan.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出临时计划申请表记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
	/**
	 * 显示详情表
	 */
    @ResponseBody
    @RequestMapping(value = "detail")
	public TestTemporaryPlan detail(String id) {
		return testTemporaryPlanService.get(id);
	}
    

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("templan:testTemporaryPlan:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TestTemporaryPlan> list = ei.getDataList(TestTemporaryPlan.class);
			for (TestTemporaryPlan testTemporaryPlan : list){
				try{
					testTemporaryPlanService.save(testTemporaryPlan);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条临时计划申请表记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条临时计划申请表记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入临时计划申请表失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/templan/templanapplication/testTemporaryPlan/?repage";
    }
	
	/**
	 * 下载导入临时计划申请表数据模板
	 */
	@RequiresPermissions("templan:testTemporaryPlan:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "临时计划申请表数据导入模板.xlsx";
    		List<TestTemporaryPlan> list = Lists.newArrayList(); 
    		new ExportExcel("临时计划申请表数据", TestTemporaryPlan.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/templan/templanapplication/testTemporaryPlan/?repage";
    }
	
	/**
	 * 统计临时销售情况
	 * @return
	 */
	@ResponseBody
	@GetMapping("/gettemstate")
	public AjaxJson getTemState(){
		AjaxJson j = new AjaxJson();
		
		try {
			j.setBody(testTemporaryPlanService.getTemporaryState());
		} catch (Exception e) {
			j.setMsg("查询失败");
			j.setSuccess(false);
		}
		return j;
	}
}