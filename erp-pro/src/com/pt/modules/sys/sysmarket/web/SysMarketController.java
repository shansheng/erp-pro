/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysmarket.web;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.h2.engine.SysProperties;
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
import com.pt.modules.mds.mdsitem.entity.MdsItem;
import com.pt.modules.mds.mdsitem.service.MdsItemService;
import com.pt.modules.mrp.mrpwo.entity.MrpWo;
import com.pt.modules.sys.client.entity.Client;
import com.pt.modules.sys.client.service.ClientService;
import com.pt.modules.sys.entity.Role;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.sysmarket.entity.SysMarket;
import com.pt.modules.sys.sysmarket.entity.SysMarketOfMap;
import com.pt.modules.sys.sysmarket.service.SysMarketService;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 销售管理Controller
 * @author 郑利
 * @version 2018-08-23
 */
@Controller
@RequestMapping(value = "${adminPath}/sysmarket/sysMarket")
public class SysMarketController extends BaseController {

	@Autowired
	private SysMarketService sysMarketService;
	
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
	 * 销售列表页面
	 */
	@RequiresPermissions("sysmarket:sysMarket:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/sys/sysmarket/sysMarketList";
	}
	
	
	
	
	
		/**
	 * 销售列表数据
	 */
	@ResponseBody
	@RequiresPermissions("sysmarket:sysMarket:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SysMarket sysMarket, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysMarket> page = sysMarketService.findPage(new Page<SysMarket>(request, response), sysMarket);
		User user=UserUtils.getUser();
		List list=user.getRoleIdList();
		Role role=new Role();
		List<Role> roleList=sysMarketService.findList(role);
		String roleId="";
		for(int a=0;a<roleList.size();a++){
			if(roleList.get(a).getEnname().equals("system")){
				roleId=roleList.get(a).getId();
				break;
			}
		}
		for(int b=0;b<list.size();b++){
			if(list.get(b).equals(roleId)){
				for(int i=0;i<page.getList().size();i++){
						page.getList().get(i).setNormalStatus("2");
					}
				return getBootstrapData(page);
			}
	}
		for(int i=0;i<page.getList().size();i++){
			if(page.getList().get(i).getCreateBy().getId().equals(user.getId())){
				page.getList().get(i).setNormalStatus("2");
			}else{
				page.getList().get(i).setNormalStatus("1");
			}
		}
		return getBootstrapData(page);
	}

	
	/**
	 * 查看，增加，编辑销售表单页面
	 */
	@RequiresPermissions(value={"sysmarket:sysMarket:view","sysmarket:sysMarket:add","sysmarket:sysMarket:edit"},logical=Logical.OR)
	@RequestMapping(value = "view")
	public String view(SysMarket sysMarket, Model model) {
		/*User user=UserUtils.getUser();
		sysMarket.setPrincipal(user.getId());
		sysMarket.setPrincipalName(user.getName());*/
		if(sysMarket.getClientId()!=null&&!sysMarket.getClientId().equals("")){
			Client client=sysClientService.get(sysMarket.getClientId());
			sysMarket.setPhone(client.getPhone());
			sysMarket.setClientManager(client.getClientManager());
			sysMarket.setAddress(client.getAddress());
			sysMarket.setRegion(client.getRegion());
		}
	
		
		model.addAttribute("sysMarket", sysMarket);
		return "modules/sys/sysmarket/sysMarketView";
	}
	@ResponseBody
	@RequiresPermissions(value={"sysmarket:sysMarket:add","sysmarket:sysMarket:edit"},logical=Logical.OR)
	@RequestMapping(value = "checkNo")
	public AjaxJson checkNo(String contractNo, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		String No=sysMarketService.checkNo(contractNo);
		if(No!=null){
			j.setSuccess(false);
			j.setMsg("该合同编号已存在!");
			return j;
		}

		j.setSuccess(true);
		
		return j;
	}
	/**
	 * 查看，增加，编辑销售表单页面
	 */
	@RequiresPermissions(value={"sysmarket:sysMarket:view","sysmarket:sysMarket:add","sysmarket:sysMarket:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(SysMarket sysMarket, Model model) {
		
		if(sysMarket.getClientId()!=null&&!sysMarket.getClientId().equals("")){
			Client client=sysClientService.get(sysMarket.getClientId());
			sysMarket.setPhone(client.getPhone());
			sysMarket.setClientManager(client.getClientManager());
			sysMarket.setAddress(client.getAddress());
			sysMarket.setRegion(client.getRegion());
		}
	
		
		model.addAttribute("sysMarket", sysMarket);
		return "modules/sys/sysmarket/sysMarketForm";
	}
	@ResponseBody
	@RequestMapping(value = "getClient")
	public Client getClient(String clientId, Model model) {
		Client client=sysClientService.get(clientId);
		return client;
	}
	
	@ResponseBody
	@RequestMapping(value = "getItem")
	public MdsItem getItem(String mdsItemId, Model model) {
		MdsItem mdsitem=mdsItemService.get(mdsItemId);
		return mdsitem;
	}
	
	
	
	/**
	 * 修改回款页面
	 */
	@RequiresPermissions(value={"sysmarket:sysMarket:view","sysmarket:sysMarket:add","sysmarket:sysMarket:edit"},logical=Logical.OR)
	@RequestMapping(value = "editPay")
	public String editPay(SysMarket sysMarket, Model model) {
		
		if(sysMarket.getClientId()!=null&&!sysMarket.getClientId().equals("")){
			Client client=sysClientService.get(sysMarket.getClientId());
			sysMarket.setPhone(client.getPhone());
			sysMarket.setClientManager(client.getClientManager());
			sysMarket.setAddress(client.getAddress());
			sysMarket.setRegion(client.getRegion());
		}
	
		
		model.addAttribute("sysMarket", sysMarket);
		return "modules/sys/sysmarket/sysMarketPayForm";
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 保存销售
	 */
	@ResponseBody
	@RequiresPermissions(value={"sysmarket:sysMarket:add","sysmarket:sysMarket:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(SysMarket sysMarket, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, sysMarket)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		String Debt=sysMarket.getDebt();
		String totalPay=sysMarket.getTotalPay();
		if(Debt==null){
			Debt="0";
		}
		if(totalPay==null){
			totalPay="0";
		}
		if(sysMarket.getId().equals("")){
			sysMarket.setDebt(sysMarket.getMoney());
			sysMarket.setTotalPay("0");
			sysMarket.setShipments("0");
			sysMarketService.save(sysMarket);//新建或者编辑保存
			j.setSuccess(true);
			j.setMsg("保存销售成功");
			return j;
		}
		String id=sysMarket.getId();
		String money=sysMarket.getMoney();
		double totalpay=Double.parseDouble(totalPay);
		double Money=Double.parseDouble(money);
		String tota="";
		if(!id.equals("")){
			SysMarket sysMarkets=sysMarketService.get(id);
			 tota=sysMarkets.getTotalPay();
			 double TOTA=Double.parseDouble(tota);
				if(sysMarket.getTotalPay()!=null&&sysMarket.getTotalPay()!=""){
					double tot=TOTA+totalpay;
					if(tot>Money){
						j.setSuccess(false);
						j.setMsg("累计还款不能大于订单总额！");
						return j;
					}
					if(totalpay==0){
						double deb=Money-tot;
						String DEB=String.valueOf(deb);
						String TOT=String.valueOf(tot);
						sysMarket.setDebt(DEB);
						sysMarket.setTotalPay(TOT);
					}
					double deb=Money-tot;
					String DEB=String.valueOf(deb);
					String TOT=String.valueOf(tot);
					sysMarket.setDebt(DEB);
					sysMarket.setTotalPay(TOT);
				}
		}
//		sysMarket.setShipments("0");
		sysMarketService.save(sysMarket);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存销售成功");
		return j;
	}
	
	/**
	 * 删除销售
	 */
	@ResponseBody
	@RequiresPermissions("sysmarket:sysMarket:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SysMarket sysMarket, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sysMarketService.delete(sysMarket);
		j.setMsg("删除销售成功");
		return j;
	}
	
	/**
	 * 批量删除销售
	 */
	@ResponseBody
	@RequiresPermissions("sysmarket:sysMarket:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			sysMarketService.delete(sysMarketService.get(id));
		}
		j.setMsg("删除销售成功");
		return j;
	}
	
	/**
	 * 大屏幕看板中各地区的销售订单的数量
	 */
	@ResponseBody
	@RequestMapping(value = "getOfMap")
	public AjaxJson getOfProduct(RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		List<List<SysMarketOfMap>> list = sysMarketService.getOfMap();
		if(list.isEmpty()){
			j.setSuccess(false);
			return j;
		}
		 LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();
		 body.put("data", list);
		j.setBody(body);
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("sysmarket:sysMarket:export")
    @RequestMapping(value = "export", method=RequestMethod.GET)
    public String exportFile(SysMarket sysMarket, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
	
		try {
            String fileName = "销售"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SysMarket> page = sysMarketService.findPage(new Page<SysMarket>(request, response, -1), sysMarket);
    		new ExportExcel("销售", SysMarket.class).setDataList(page.getList()).write(response, fileName).dispose();
    		addMessage(redirectAttributes,"导出成功！");
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes,"导出销售记录失败！失败信息："+e.getMessage());
		}
			return "redirect:"+Global.getAdminPath()+"/sysmarket/sysMarket/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("sysmarket:sysMarket:import")
    @RequestMapping(value = "import", method=RequestMethod.GET)
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
				failureMsg.insert(0, "，失败 "+failureNum+" 条销售记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条销售记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入销售失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sysmarket/sysMarket/?repage";
    }
	
	/**
	 * 下载导入销售数据模板
	 */
	@RequiresPermissions("sysmarket:sysMarket:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "销售数据导入模板.xlsx";
    		List<SysMarket> list = Lists.newArrayList(); 
    		new ExportExcel("销售数据", SysMarket.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sysmarket/sysMarket/?repage";
    }
	/**
	 * 按年份统计合同数
	 * @return
	 */
	@GetMapping("/getCount")
	@ResponseBody
   public AjaxJson getCount(){
		AjaxJson j = new AjaxJson();
	   try {
		    j.setBody(sysMarketService.getNumberByDate());
		} catch (Exception e) {
			j.setMsg("查询失败");
			j.setSuccess(false);
		}
	   return j;
   }
	/**
	 * 统计销售情况
	 * @return
	 */
	@GetMapping("/getSysmarketState")
	@ResponseBody
	public AjaxJson getSysmarketState(){
		AjaxJson j = new AjaxJson();
		   try {
			    j.setBody(sysMarketService.getSysmarketState());
			} catch (Exception e) {
				j.setMsg("查询失败");
				j.setSuccess(false);
			}
		   return j;
	}

}