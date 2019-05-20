/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysmarketmilestone.web;

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
import com.pt.modules.mds.mdsitem.service.MdsItemService;
import com.pt.modules.sys.client.entity.Client;
import com.pt.modules.sys.client.service.ClientService;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.sysmarket.entity.SysMarket;
import com.pt.modules.sys.sysmarketmilestone.service.SysMarketMilestoneService;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;


/**
 * 里程碑节点计划Controller
 * @author 郑利
 * @version 2018-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/sysmarketmilestone/sysMarket")
public class SysMarketMilestoneController extends BaseController {

	@Autowired
	private SysMarketMilestoneService sysMarketService;
	
	@Autowired
	private ClientService sysClientService;
	
	@Autowired
	private MdsItemService mdsItemService;
	@ModelAttribute
	public SysMarket get(@RequestParam(required=false) String id) {
		SysMarket entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysMarketService.get(id);
		}
		if (entity == null){
			entity = new SysMarket();
		}
		return entity;
	}

	
	/**
	 * 销售订单下达列表页面
	 */
	@RequiresPermissions("sysmarketmilestone:sysMarket:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/sys/sysmarketmilestone/sysMarketList";
	}
	/**
	 * 下达
	 */
	@ResponseBody
	@RequiresPermissions(value={"sysmarketmilestone:sysMarket:dispatch"})
	@RequestMapping(value = "dispatch")
	public AjaxJson dispatch(SysMarket SysMarket, Model model, RedirectAttributes redirectAttributes) {
		
		AjaxJson j = new AjaxJson();
		String message = "下达成功";
		try {
			sysMarketService.sysMarketDispatch(SysMarket);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			message = "下达失败";
		}
		j.setMsg(message);
		return j;
	}
		/**
	 * 销售订单下达列表数据
	 */
	@ResponseBody
	@RequiresPermissions("sysmarketmilestone:sysMarket:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SysMarket sysMarket, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysMarket> page = sysMarketService.findPage(new Page<SysMarket>(request, response), sysMarket); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑销售订单下达表单页面
	 */
	@RequiresPermissions(value={"sysmarketmilestone:sysMarket:view","sysmarketmilestone:sysMarket:add","sysmarketmilestone:sysMarket:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(SysMarket sysMarket, Model model,RedirectAttributes redirectAttributes) {
		User user=UserUtils.getUser();
		sysMarket.setPrincipal(user.getId());
		sysMarket.setPrincipalName(user.getName());
		
		if(sysMarket.getClientId()!=null&&!sysMarket.getClientId().equals("")){
			Client client=sysClientService.get(sysMarket.getClientId());
			sysMarket.setPhone(client.getPhone());
			sysMarket.setClientManager(client.getClientManager());
			sysMarket.setAddress(client.getAddress());
			sysMarket.setRegion(client.getRegion());
		}
		model.addAttribute("sysmarketmilestone", sysMarket);
		return "modules/sys/sysmarketmilestone/sysMarketForm";
	}
	
	/**
	 * 查看，增加，编辑销售订单下达表单页面
	 */
	@RequiresPermissions(value={"sysmarketmilestone:sysMarket:view","sysmarketmilestone:sysMarket:add","sysmarketmilestone:sysMarket:edit"},logical=Logical.OR)
	@RequestMapping(value = "view")
	public String view(SysMarket sysMarket, Model model,RedirectAttributes redirectAttributes) {
		User user=UserUtils.getUser();
		sysMarket.setPrincipal(user.getId());
		sysMarket.setPrincipalName(user.getName());
		
		if(sysMarket.getClientId()!=null&&!sysMarket.getClientId().equals("")){
			Client client=sysClientService.get(sysMarket.getClientId());
			sysMarket.setPhone(client.getPhone());
			sysMarket.setClientManager(client.getClientManager());
			sysMarket.setAddress(client.getAddress());
			sysMarket.setRegion(client.getRegion());
		}
		model.addAttribute("sysmarketmilestone", sysMarket);
		return "modules/sys/sysmarketmilestone/sysMarketView";
	}
	@ResponseBody
	@RequestMapping(value = "getClient")
	public Client getClient(String clientId, Model model) {
		Client client=sysClientService.get(clientId);
		return client;
	}
	/**
	 * 保存销售订单下达
	 */
	@ResponseBody
	@RequiresPermissions(value={"sysmarketmilestone:sysMarket:add","sysmarketmilestone:sysMarket:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(SysMarket sysMarket, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, sysMarket)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		sysMarketService.save(sysMarket);//保存
		j.setSuccess(true);
		j.setMsg("保存销售订单下达成功");
		return j;
	}
	
	/**
	 * 删除销售订单下达
	 */
	@ResponseBody
	@RequiresPermissions("sysmarketmilestone:sysMarket:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SysMarket sysMarket, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sysMarketService.delete(sysMarket);
		j.setMsg("删除销售订单下达成功");
		return j;
	}
	
	/**
	 * 批量删除销售订单下达
	 */
	@ResponseBody
	@RequiresPermissions("sysmarketmilestone:sysMarket:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			sysMarketService.delete(sysMarketService.get(id));
		}
		j.setMsg("删除销售订单下达成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("sysmarketmilestone:sysMarket:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SysMarket sysMarket, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "销售订单下达"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SysMarket> page = sysMarketService.findPage(new Page<SysMarket>(request, response, -1), sysMarket);
    		new ExportExcel("销售订单下达", SysMarket.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出销售订单下达记录失败！失败信息："+e.getMessage());
		}
			return j;
    }
    
    @ResponseBody
    @RequestMapping(value = "detail")
	public SysMarket detail(String id) {
		return sysMarketService.get(id);
	}
	

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("sysmarketmilestone:sysMarket:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SysMarket> list = ei.getDataList(SysMarket.class);
			for (SysMarket sysMarket : list){
				try{
					sysMarketService.save(sysMarket);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条销售订单下达记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条销售订单下达记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入销售订单下达失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sysmarketmilestone/sysMarket/?repage";
    }
	
	/**
	 * 下载导入销售订单下达数据模板
	 */
	@RequiresPermissions("sysmarketmilestone:sysMarket:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "销售订单下达数据导入模板.xlsx";
    		List<SysMarket> list = Lists.newArrayList(); 
    		new ExportExcel("销售订单下达数据", SysMarket.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sysmarketmilestone/sysMarket/?repage";
    }
	

}