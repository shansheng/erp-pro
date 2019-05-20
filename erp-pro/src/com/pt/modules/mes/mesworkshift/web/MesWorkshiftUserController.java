package com.pt.modules.mes.mesworkshift.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pt.common.json.AjaxJson;
import com.pt.common.utils.StringUtils;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.modules.mes.mesworkshift.entity.MesWorkshift;
import com.pt.modules.mes.mesworkshift.entity.MesWorkshiftUser;
import com.pt.modules.mes.mesworkshift.service.MesWorkshiftService;
import com.pt.modules.mes.mesworkshift.service.MesWorkshiftUserService;
import com.pt.modules.sys.entity.Role;
import com.pt.modules.sys.entity.User;

@Controller
@RequestMapping(value = "${adminPath}/mesworkshiftuser/mesWorkshift")
public class MesWorkshiftUserController extends BaseController{
	@Autowired
	private MesWorkshiftUserService mesWorkshiftUserService;
	
	@ModelAttribute
	public MesWorkshift get(@RequestParam(required=false) String id) {
		MesWorkshift entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mesWorkshiftUserService.get(id);
		}
		if (entity == null){
			entity = new MesWorkshift();
		}
		return entity;
	}
	

	@ResponseBody
	@RequiresPermissions("mes:mesWorkshiftUser:add")
	@RequestMapping(value = "add")
	public Map<String, Object> getSubList(String sysUserId,MesWorkshiftUser mesWorkshiftUser, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("mesWorkshiftUser", mesWorkshiftUser);
		Page<MesWorkshiftUser> page = mesWorkshiftUserService.findSubPage(new Page<MesWorkshiftUser>(request, response), mesWorkshiftUser); 		
		return  getBootstrapData(page);
	}
	
	@ResponseBody
	@RequestMapping(value = "saveUsers")
	public AjaxJson saveUsers(String mesWorkshiftId,String []ids, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "保存成功";
		for (int i = 0; i < ids.length; i++) {
			MesWorkshiftUser entity=mesWorkshiftUserService.selectUser(mesWorkshiftId,ids[i]);
			if (entity!=null){
				j.setMsg("保存失败,不可添加重复人员!");
				return j;
			}
			
		}
		try {
			for (int i = 0; i < ids.length; i++) {
				mesWorkshiftUserService.saveUsers(mesWorkshiftId,ids[i]);
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
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deluser")
	public AjaxJson deluser(String id, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "删除成功";
		try {
			mesWorkshiftUserService.deluser(id);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			message = "删除失败";
		}
		j.setMsg(message);
		return j;
	}
}
