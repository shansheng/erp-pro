package com.pt.modules.sm.smuser.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
import com.pt.modules.sm.smuser.entity.SmUser;
import com.pt.modules.sm.smuser.service.SmUserService;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 会员信息Controller
 * @author hexl
 * @version 2018-03-13
 */
@Api(value = "SmUserController", description = "会员信息")
@Controller
@RequestMapping(value = "${adminPath}/smuser/smUser")
public class SmUserController extends BaseController {

	@Autowired
	private SmUserService smUserService;
	
	@ModelAttribute
	public SmUser get(@RequestParam(required=false) String id) {
		SmUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smUserService.get(id);
		}
		if (entity == null){
			entity = new SmUser();
		}
		return entity;
	}
	
	/**
	 * 保存“商户信息“成功列表页面
	 */
	@RequiresPermissions("smuser:smUser:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "sm/smuser/smUserList";
	}
	
		/**
	 * 保存“商户信息“成功列表数据
	 */

	/**
	 * app端保存会员信息
	 */
	@RequiresPermissions("smuser:smUser:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SmUser smUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmUser> page = smUserService.findPage(new Page<SmUser>(request, response), smUser); 
		return getBootstrapData(page);
	}

	
	/**
	 * 查看，增加，编辑保存“商户信息“成功表单页面
	 */
	@RequiresPermissions(value={"smuser:smUser:view","smuser:smUser:add","smuser:smUser:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(SmUser smUser, Model model) {
		model.addAttribute("smUser", smUser);
		if(StringUtils.isBlank(smUser.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "sm/smuser/smUserForm";
	}

	/**
	 * 保存保存“商户信息“成功
	 */
	
	@RequiresPermissions(value={"smuser:smUser:add","smuser:smUser:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(SmUser smUser, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, smUser)){
			return form(smUser, model);
		}
		//新增或编辑表单保存
		smUserService.save(smUser);//保存
		addMessage(redirectAttributes, "保存保存“商户信息“成功成功");
		return "redirect:"+Global.getAdminPath()+"/smuser/smUser/?repage";
	}
	
	/**
	 * 删除保存“商户信息“成功
	 */
	@ResponseBody
	@RequiresPermissions("smuser:smUser:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SmUser smUser, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		smUserService.delete(smUser);
		j.setMsg("删除保存“商户信息“成功成功");
		return j;
	}
	
	/**
	 * 批量删除保存“商户信息“成功
	 */
	@ResponseBody
	@RequiresPermissions("smuser:smUser:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			smUserService.delete(smUserService.get(id));
		}
		j.setMsg("删除保存“商户信息“成功成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("smuser:smUser:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SmUser smUser, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "保存“商户信息“成功"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SmUser> page = smUserService.findPage(new Page<SmUser>(request, response, -1), smUser);
    		new ExportExcel("保存“商户信息“成功", SmUser.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出保存“商户信息“成功记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("smuser:smUser:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SmUser> list = ei.getDataList(SmUser.class);
			for (SmUser smUser : list){
				try{
					smUserService.save(smUser);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条保存“商户信息“成功记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条保存“商户信息“成功记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入保存“商户信息“成功失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/smuser/smUser/?repage";
    }
	
	/**
	 * 下载导入保存“商户信息“成功数据模板
	 */
	@RequiresPermissions("smuser:smUser:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "保存“商户信息“成功数据导入模板.xlsx";
    		List<SmUser> list = Lists.newArrayList(); 
    		new ExportExcel("保存“商户信息“成功数据", SmUser.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/smuser/smUser/?repage";
    }
	
	@ApiOperation(value = "根据用户名查询会员信息")
	@RequestMapping(value = "findList", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson findList(
	            @ApiParam(name = "name", value = "用户名") @RequestParam(required = false) String name,HttpServletRequest request, HttpServletResponse response) {
		SmUser smUser = new SmUser();
		smUser.setName(name);
		Page<SmUser> page = smUserService.findPage(new Page<SmUser>(request, response), smUser); 
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		j.put("datas", getBootstrapData(page));
		return j;
	}
	
	/**
	 * app端保存会员信息
	 */
	@ApiOperation(notes = "saveSmUser", httpMethod = "POST", value = "保存会员信息")
	@RequestMapping(value = "/saveSmUser")
	@ResponseBody
	public AjaxJson insertUserInfo(@ApiParam(name = "smUser", value = "会员信息") 
		@RequestBody SmUser smUser, HttpServletRequest request) {
		if(smUser!=null){
			User user = UserUtils.getByLoginName("admin");
			smUser.setCreateBy(user);
			smUser.setUpdateBy(user);
			smUserService.save(smUser);//保存
		}
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		j.setMsg("添加成功");
		return j;
	}
	
	/**
	 * app根据id获得会员信息
	 */
	@ApiOperation(notes = "getSmUserById", httpMethod = "GET", value = "通过id获得用户信息")
	@RequestMapping(value = "/getSmUserById",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getSmUserById(@ApiParam(name = "id", value = "用户id") @RequestParam(required = true) String id, HttpServletRequest request) {
		SmUser smUser = smUserService.get(id);	
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		j.put("data", smUser);
		return j;
	}
}