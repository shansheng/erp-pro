/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysmarketship.web;


import java.util.ArrayList;
import java.util.Date;
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
import com.pt.common.config.Global;
import com.pt.common.json.AjaxJson;
import com.pt.common.utils.DateUtils;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.modules.mds.mdsitem.entity.MdsItem;
import com.pt.modules.mds.mdsitem.service.MdsItemService;
import com.pt.modules.sys.client.entity.Client;
import com.pt.modules.sys.client.service.ClientService;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.sysmarketship.entity.SysMarketShip;
import com.pt.modules.sys.sysmarketship.entity.SysMarketShipList;
import com.pt.modules.sys.sysmarketship.service.SysMarketShipService;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.work.workorder.entity.WorkOrder;
import com.pt.modules.work.workorder.service.WorkOrderService;
import com.pt.modules.work.workorderdetail.entity.WorkOrderDetail;
import com.pt.modules.work.workorderdetail.service.WorkOrderDetailService;
import com.pt.modules.work.workorderson.entity.WorkOrderSon;
import com.pt.modules.work.workorderson.service.WorkOrderSonService;

/**
 * 销售管理Controller
 * @author 郑利
 * @version 2018-08-23
 */
@Controller
@RequestMapping(value = "${adminPath}/SysMarketShip/SysMarketShip")
public class SysMarketShipController extends BaseController {

	@Autowired
	private SysMarketShipService sysMarketService;
	
	@Autowired
	private ClientService sysClientService;
	
	@Autowired
	private MdsItemService mdsItemService;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private WorkOrderDetailService workOrderDetailService;
	
	@Autowired
	private WorkOrderSonService workOrderSonService;
	@ModelAttribute
	public SysMarketShip get(@RequestParam(required=false) String id) {
		SysMarketShip entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysMarketService.get(id);
		}
		if (entity == null){
			entity = new SysMarketShip();
		}
		return entity;
	}
	
	/**
	 * 销售列表页面
	 */
	@RequiresPermissions("SysMarketShip:SysMarketShip:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/sys/sysmarketship/sysMarketShipList";
	}
	
	
	
	
	
		/**
	 * 销售列表数据
	 */
	@ResponseBody
	@RequiresPermissions("SysMarketShip:SysMarketShip:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(SysMarketShip SysMarketShip, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysMarketShip> page = sysMarketService.findPage(new Page<SysMarketShip>(request, response), SysMarketShip);
		return getBootstrapData(page);
	}
	/**
	 * 
	 * 开票
	 */
	@ResponseBody
	@RequiresPermissions(value={"sysmarket:sysMarket:kp"})
	@RequestMapping(value = "kp")
	public AjaxJson kp(SysMarketShip SysMarketShip, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		SysMarketShip.setIsKp("1");
		SysMarketShip.setKpDate(new Date());
		sysMarketService.kp(SysMarketShip);
		String message = "开票成功!";
		j.setMsg(message);
		j.setSuccess(true);
		return j;
	}
/**
 * 发货
 * @param SysMarketShip
 * @param model
 * @param redirectAttributes
 * @return
 */
	@ResponseBody
	@RequiresPermissions(value={"SysMarket:sysMarket:dispatch"})
	@RequestMapping(value = "dispatch")
	public AjaxJson dispatch(SysMarketShip SysMarketShip, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String sysMarketId=SysMarketShip.getId();
		WorkOrder workOrder=workOrderService.getBySysMarketId(sysMarketId);
		String workOrderId=workOrder.getId();
		List<WorkOrderDetail> workOrderDetailList=workOrderDetailService.getByWorkOrderId(workOrderId);
		
		
		List<String> strList=new ArrayList<String>();
		for(int i=0;i<workOrderDetailList.size();i++){
			strList.add(workOrderDetailList.get(i).getFinishState());
		}
		for(int b=0;b<strList.size();b++){
			if(strList.get(b).equals("0")){
				String message = "当前订单未完成!";
				j.setMsg(message);
				j.setSuccess(false);
				return j;
			}
		}
		
		String message = "发货成功";
		try {
			SysMarketShip.setShipmentsDate(new Date());
			sysMarketService.sysMarketDispatch(SysMarketShip);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			message = "发货失败";
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 发货
	 */
	@ResponseBody
	@RequiresPermissions(value={"SysMarketShip:SysMarketShip:add","SysMarketShip:SysMarketShip:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(SysMarketShip SysMarketShip, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, SysMarketShip)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		String sysMarketId=SysMarketShip.getId();
		WorkOrder workOrder=workOrderService.getBySysMarketId(sysMarketId);
		String workOrderId=workOrder.getId();
		WorkOrderSon workOrderSon =new WorkOrderSon();
		workOrderSon.setWorkOrderId(workOrderId);
		List<WorkOrderSon> workOrderSonLists=new ArrayList<>();
		for(int b=0;b<SysMarketShip.getSysMarketShipListList().size();b++){
			String mdsItemId=SysMarketShip.getSysMarketShipListList().get(b).getMdsItemId();
			workOrderSon.setMdsItemId(mdsItemId);
			List<WorkOrderSon> workOrderSonList=workOrderSonService.findByWorkOrderId(workOrderSon);
			if(workOrderSonList.size()==0){
				j.setSuccess(false);
				j.setMsg("当前没有完成的产品，不能发货！");
				return j;
			}
			for(int a=0;a<workOrderSonList.size();a++){
				if(workOrderSonList.get(a).getFinishSche().equals("100%")){
					workOrderSonLists.add(a, workOrderSonList.get(a));
				}
				Integer si=Integer.parseInt(SysMarketShip.getSysMarketShipListList().get(b).getDeliverNum());
				if(si>workOrderSonLists.size()){
					j.setSuccess(false);
					j.setMsg("发货数大于已完成产品数，请重新输入！");
					return j;
				}
			}
		}
		List<SysMarketShipList> list=SysMarketShip.getSysMarketShipListList();
		
		for(int i=0;i<list.size();i++){
			Integer num=list.get(i).getNum();//该产品订单数
			String deliverNum=list.get(i).getDeliverNum();//要发货数
			int delivernum=Integer.parseInt(deliverNum);
			String ListId=list.get(i).getId();
			SysMarketShipList sysMarketList=sysMarketService.getListById(ListId);
			String deliverNums=sysMarketList.getDeliverNums();//历史累计发货数
			if(deliverNums==null){
				deliverNums="0";
			}
			int  delivernums=Integer.parseInt(deliverNums);
			Integer dnums=delivernums+delivernum; //总发货数
			if(dnums>num){
				j.setSuccess(false);
				j.setMsg("累计发货数不能大于订单数！");
				return j;
			}
			String dNums=String.valueOf(dnums);
			list.get(i).setDeliverNums(dNums);
			if(dnums==num){
				SysMarketShip.setShipments("2");
			}
			if(dnums<num){
			SysMarketShip.setShipments("1");
			}
		}

		SysMarketShip.setShipmentsDate(new Date());
		SysMarketShip.setSysMarketShipListList(list);
		sysMarketService.sysMarketDispatch(SysMarketShip);//发货
		j.setSuccess(true);
		j.setMsg("发货成功");
		return j;
		
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
	 * 查看表单页面
	 */
	@RequiresPermissions(value={"sysmarket:sysMarket:view","sysmarket:sysMarket:add","sysmarket:sysMarket:edit"},logical=Logical.OR)
	@RequestMapping(value = "view")
	public String view(SysMarketShip SysMarketShip, Model model) {
		if(SysMarketShip.getClientId()!=null&&!SysMarketShip.getClientId().equals("")){
			Client client=sysClientService.get(SysMarketShip.getClientId());
			SysMarketShip.setPhone(client.getPhone());
			SysMarketShip.setClientManager(client.getClientManager());
			SysMarketShip.setAddress(client.getAddress());
			SysMarketShip.setRegion(client.getRegion());
		}
	
		
		model.addAttribute("sysMarket", SysMarketShip);
		return "modules/sys/sysmarketship/sysMarketView";
	}
	/**
	 * 删除销售
	 */
	@ResponseBody
	@RequiresPermissions("SysMarketShip:SysMarketShip:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(SysMarketShip SysMarketShip, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		sysMarketService.delete(SysMarketShip);
		j.setMsg("删除销售成功");
		return j;
	}
	
	/**
	 * 批量删除销售
	 */
	@ResponseBody
	@RequiresPermissions("SysMarketShip:SysMarketShip:del")
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
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("SysMarketShip:SysMarketShip:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(SysMarketShip SysMarketShip, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "销售"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SysMarketShip> page = sysMarketService.findPage(new Page<SysMarketShip>(request, response, -1), SysMarketShip);
    		new ExportExcel("销售", SysMarketShip.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出销售记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("SysMarketShip:SysMarketShip:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SysMarketShip> list = ei.getDataList(SysMarketShip.class);
			for (SysMarketShip SysMarketShip : list){
				try{
					sysMarketService.save(SysMarketShip);
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
		return "redirect:"+Global.getAdminPath()+"/SysMarketShip/SysMarketShip/?repage";
    }
	
	/**
	 * 下载导入销售数据模板
	 */
	@RequiresPermissions("SysMarketShip:SysMarketShip:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "销售数据导入模板.xlsx";
    		List<SysMarketShip> list = Lists.newArrayList(); 
    		new ExportExcel("销售数据", SysMarketShip.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/SysMarketShip/SysMarketShip/?repage";
    }

}