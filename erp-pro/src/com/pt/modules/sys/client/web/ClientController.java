/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.client.web;

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
import com.pt.modules.sys.client.entity.Client;
import com.pt.modules.sys.client.service.ClientService;

/**
 * 客户管理Controller
 * @author 郑利
 * @version 2018-08-21
 */
@Controller
@RequestMapping(value = "${adminPath}/client/client")
public class ClientController extends BaseController {

	@Autowired
	private ClientService clientService;
	
	@ModelAttribute
	
	public Client get(@RequestParam(required=false) String id) {
		Client entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = clientService.get(id);
		}
		if (entity == null){
			entity = new Client();
		}
		return entity;
	}
	
	/**
	 * 客户管理列表页面
	 */
	@RequiresPermissions("client:client:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/sys/client/clientList";
	}
	
		/**
	 * 客户管理列表数据
	 */
	@ResponseBody
	@RequiresPermissions("client:client:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(Client client, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Client> page = clientService.findPage(new Page<Client>(request, response), client); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑客户管理表单页面
	 */
	@RequiresPermissions(value={"client:client:view","client:client:add","client:client:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Client client, Model model) {
		model.addAttribute("client", client);
		return "modules/sys/client/clientForm";
	}

	/**
	 * 保存客户管理
	 */
	@ResponseBody
	@RequiresPermissions(value={"client:client:add","client:client:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(Client client, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, client)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		clientService.save(client);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存客户管理成功");
		return j;
	}
	
	/**
	 * 删除客户管理
	 */
	@ResponseBody
	@RequiresPermissions("client:client:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(Client client, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		clientService.delete(client);
		j.setMsg("删除客户管理成功");
		return j;
	}
	
	/**
	 * 批量删除客户管理
	 */
	@ResponseBody
	@RequiresPermissions("client:client:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			clientService.delete(clientService.get(id));
		}
		j.setMsg("删除客户管理成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("client:client:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(Client client, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "客户管理"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Client> page = clientService.findPage(new Page<Client>(request, response, -1), client);
    		new ExportExcel("客户管理", Client.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出客户管理记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("client:client:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Client> list = ei.getDataList(Client.class);
			for (Client client : list){
				try{
					clientService.save(client);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条客户管理记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条客户管理记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入客户管理失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/client/client/?repage";
    }
	
	/**
	 * 下载导入客户管理数据模板
	 */
	@RequiresPermissions("client:client:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "客户管理数据导入模板.xlsx";
    		List<Client> list = Lists.newArrayList(); 
    		new ExportExcel("客户管理数据", Client.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/client/client/?repage";
    }

}