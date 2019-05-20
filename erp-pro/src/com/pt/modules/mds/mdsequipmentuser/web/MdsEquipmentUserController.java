/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsequipmentuser.web;

import java.util.LinkedHashMap;
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
import com.pt.modules.mds.mdsequipmentuser.entity.MdsEquipmentUser;
import com.pt.modules.mds.mdsequipmentuser.service.MdsEquipmentUserService;
import com.pt.modules.sys.entity.Role;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 设备人员管理Controller
 * @author 郑利
 * @version 2018-05-05
 */
@Controller
@RequestMapping(value = "${adminPath}/mdsequipmentuser/mdsEquipment")
public class MdsEquipmentUserController extends BaseController {

	@Autowired
	private MdsEquipmentUserService mdsEquipmentUserService;
	

	/**
	 * 查询子表数据
	 * @param mdsEquipmentUser
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("mdsequipmentuser:mdsEquipment:assign")
	@RequestMapping(value = "assign")
	public Map<String, Object> getSubList(String sysUserId,MdsEquipmentUser mdsEquipmentUser, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("mdsEquipmentUser", mdsEquipmentUser);
		Page<MdsEquipmentUser> page = mdsEquipmentUserService.findSubPage(new Page<MdsEquipmentUser>(request, response), mdsEquipmentUser); 		
		return  getBootstrapData(page);
	}


	@ResponseBody
	@RequestMapping(value = "saveUsers")
	public AjaxJson saveUsers(String mdsEquipmentId,String []ids, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "保存成功";
		for (int i = 0; i < ids.length; i++) {
			MdsEquipmentUser entity=mdsEquipmentUserService.selectUser(mdsEquipmentId,ids[i]);
			if (entity!=null){
				j.setMsg("保存失败,不可添加重复人员!");
				return j;
			}
			
		}
		try {
			for (int i = 0; i < ids.length; i++) {
				mdsEquipmentUserService.saveUsers(mdsEquipmentId,ids[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			message = "保存失败";
		}
		j.setMsg(message);
		return j;
	}

	
	
	/**
	 * 角色分配 -- 从角色中移除用户
	 * @param userId
	 * @param mdsEquipmentId
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deluser")
	public AjaxJson deluser(String id, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "删除成功";
		try {
			mdsEquipmentUserService.deluser(id);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			message = "删除失败";
		}
		j.setMsg(message);
		return j;
	}
	

	@ModelAttribute
	public MdsEquipment get(@RequestParam(required=false) String id) {
		MdsEquipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsEquipmentUserService.get(id);
		}
		if (entity == null){
			entity = new MdsEquipment();
		}
		return entity;
	}
	
	/**
	 * 设备人员列表页面
	 */
	@RequiresPermissions("mdsequipmentuser:mdsEquipment:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/mds/mdsequipmentuser/mdsEquipmentList";
	}
	
		/**
	 * 设备人员列表数据
	 */
	@ResponseBody
	@RequiresPermissions("mdsequipmentuser:mdsEquipment:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(@ModelAttribute("mdsEquipment")MdsEquipment mdsEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsEquipment> page = mdsEquipmentUserService.findPage(new Page<MdsEquipment>(request, response), mdsEquipment); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑设备人员表单页面
	 */
	@RequiresPermissions(value={"mdsequipmentuser:mdsEquipment:view","mdsequipmentuser:mdsEquipment:add","mdsequipmentuser:mdsEquipment:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MdsEquipment mdsEquipment, Model model) {
		model.addAttribute("mdsEquipment", mdsEquipment);
		return "modules/mds/mdsequipmentuser/mdsEquipmentForm";
	}

	/**
	 * 保存设备人员
	 */
	@ResponseBody
	@RequiresPermissions(value={"mdsequipmentuser:mdsEquipment:add","mdsequipmentuser:mdsEquipment:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(MdsEquipment mdsEquipment, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mdsEquipment)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		mdsEquipmentUserService.save(mdsEquipment);//保存
		j.setSuccess(true);
		j.setMsg("保存设备人员成功");
		return j;
	}
	
	/**
	 * 删除设备人员
	 */
	@ResponseBody
	@RequiresPermissions("mdsequipmentuser:mdsEquipment:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MdsEquipment mdsEquipment, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mdsEquipmentUserService.delete(mdsEquipment);
		j.setMsg("删除设备人员成功");
		return j;
	}
	
	/**
	 * 批量删除设备人员
	 */
	@ResponseBody
	@RequiresPermissions("mdsequipmentuser:mdsEquipment:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mdsEquipmentUserService.delete(mdsEquipmentUserService.get(id));
		}
		j.setMsg("删除设备人员成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("mdsequipmentuser:mdsEquipment:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(MdsEquipment mdsEquipment, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "设备人员"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MdsEquipment> page = mdsEquipmentUserService.findPage(new Page<MdsEquipment>(request, response, -1), mdsEquipment);
    		new ExportExcel("设备人员", MdsEquipment.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出设备人员记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public MdsEquipment detail(String id) {
		return mdsEquipmentUserService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mdsequipmentuser:mdsEquipment:import")
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
					mdsEquipmentUserService.save(mdsEquipment);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条设备人员记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条设备人员记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入设备人员失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mdsequipmentuser/mdsEquipment/?repage";
    }
	
	/**
	 * 下载导入设备人员数据模板
	 */
	@RequiresPermissions("mdsequipmentuser:mdsEquipment:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "设备人员数据导入模板.xlsx";
    		List<MdsEquipment> list = Lists.newArrayList(); 
    		new ExportExcel("设备人员数据", MdsEquipment.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mdsequipmentuser/mdsEquipment/?repage";
    }
	

}