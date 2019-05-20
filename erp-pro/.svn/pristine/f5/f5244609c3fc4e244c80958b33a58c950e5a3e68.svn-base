/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderdispath.web;

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
import com.pt.common.utils.DateUtils;
import com.pt.common.utils.IdGen;
import com.pt.common.config.Global;
import com.pt.common.json.AjaxJson;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;
import com.pt.modules.mds.mdsquota.entity.MdsQuotaDetail;
import com.pt.modules.mds.mdsquota.service.MdsQuotaDetailService;
import com.pt.modules.mds.mdsquota.service.MdsQuotaService;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.service.SystemService;
import com.pt.modules.sys.utils.UserUtils;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainInventory;
import com.pt.modules.wms.wmsmaininventory.entity.WmsMainRecord;
import com.pt.modules.wms.wmsmaininventory.mapper.WmsMainRecordMapper;
import com.pt.modules.wms.wmsmaininventory.service.WmsMainInventoryService;
import com.pt.modules.work.workorderdispath.entity.WorkOrderDispath;
import com.pt.modules.work.workorderdispath.service.WorkOrderDispathService;

/**
 * 生产出库Controller
 * @author 郑利
 * @version 2018-10-20
 */
@Controller
@RequestMapping(value = "${adminPath}/workorderdispath/workOrderDispath")
public class WorkOrderDispathController extends BaseController {

	@Autowired
	private WorkOrderDispathService workOrderDispathService;
	
	@Autowired
	private MdsQuotaDetailService mdsQuotaDetailService;
	
	@Autowired
	private MdsQuotaService mdsQuotaService;

	@Autowired
	private WmsMainInventoryService wmsMainInventoryService;
	
	
	@Autowired
	private SystemService userService;
	@ModelAttribute
	public MdsQuotaDetail gets(@RequestParam(required=false) String id) {
		MdsQuotaDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsQuotaDetailService.get(id);
		}
		if (entity == null){
			entity = new MdsQuotaDetail();
		}
		return entity;
	}
	/**
	 * 生产出库列表页面
	 */
	@RequiresPermissions("workorderdispath:workOrderDispath:list")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/work/workorderdispath/workOrderDispathList";
	}
	
		/**
	 * 生产出库列表数据
	 */
	@ResponseBody
	@RequiresPermissions("workorderdispath:workOrderDispath:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(WorkOrderDispath workOrderDispath, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WorkOrderDispath> page = workOrderDispathService.findPage(new Page<WorkOrderDispath>(request, response), workOrderDispath); 
		return getBootstrapData(page);
	}

	/**
	 * 显示第二主页
	 */
	@RequiresPermissions(value={"workorderdispath:workOrderDispath:view","workorderdispath:workOrderDispath:add","workorderdispath:workOrderDispath:edit"},logical=Logical.OR)
	@RequestMapping(value = "edit")
	public String edit(WorkOrderDispath workOrderDispath, Model model) {
		WorkOrderDispath workOrderDis=workOrderDispathService.get(workOrderDispath);
		String sysMarketListId=workOrderDis.getSysMarketListId();
		String mdsQuotaId=mdsQuotaService.findByMarketListId(sysMarketListId).getId();
		MdsQuotaDetail	mdsQuotaDetail=new MdsQuotaDetail();
		mdsQuotaDetail.setMdsQuotaId(mdsQuotaId);
		model.addAttribute("mdsQuotaId", mdsQuotaId);
		model.addAttribute("mdsQuotaDetail", mdsQuotaDetail);
		return "modules/work/workorderdispath/mdsQuotaDetail";
	}
	/**
	 * 显示第二主页数据
	 * 材料定额详情列表数据
	 */
	@ResponseBody
	@RequiresPermissions("workorderdispath:workOrderDispath:detailData")
	@RequestMapping(value = "detailData")
	public Map<String, Object> detailData(MdsQuotaDetail mdsQuotaDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		mdsQuotaDetail.setIsVersion("1");
		Page<MdsQuotaDetail> page = mdsQuotaDetailService.findPage(new Page<MdsQuotaDetail>(request, response), mdsQuotaDetail); 
		List<MdsQuotaDetail> list= page.getList();
		for(int i=0;i<list.size(); i++){
			if(list.get(i).getOmiNum()==null){
				list.get(i).setOmiNum(0);
			}
			String quo=list.get(i).getQuota();
			Integer	quota=Integer.parseInt(quo);
			Integer omiNum=list.get(i).getOmiNum();
			list.get(i).setMiNum(quota-omiNum);
		}
		page.setList(list);
		return getBootstrapData(page);
	}
	/**
	 * 保存生产出库
	 */
	@ResponseBody
	@RequiresPermissions(value={"workorderdispath:workOrderDispath:add","workorderdispath:workOrderDispath:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(WorkOrderDispath workOrderDispath, Model model, RedirectAttributes redirectAttributes) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, workOrderDispath)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		workOrderDispathService.save(workOrderDispath);//新建或者编辑保存
		j.setSuccess(true);
		j.setMsg("保存生产出库成功");
		return j;
	}
	/**
	 * 出库
	 */
	@ResponseBody
	@RequestMapping(value = "dispath")
	public AjaxJson dispath(String userId,MdsQuotaDetail mdsQuotaDetail, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		if(userId.equals("")){
			j.setSuccess(false);
			j.setMsg("领料人必选!");
			return j;
		}
		
		Integer omiNum=mdsQuotaDetail.getOmiNum();
		Integer miNum=mdsQuotaDetail.getMiNum();
		String qtys=mdsQuotaDetail.getQuota();
		Integer quotaQty=Integer.parseInt(qtys);
		if(omiNum==null){
			omiNum=0;
		}
		if(omiNum+miNum>quotaQty){
			j.setSuccess(false);
			j.setMsg("出库数不能大于单台定额数!");
			return j;
		}
		
		MdsQuotaDetail mdsQuotaDe=mdsQuotaDetailService.get(mdsQuotaDetail);
		WmsMainInventory wmsMainInventory=new WmsMainInventory();
		wmsMainInventory.setItemId(mdsQuotaDe.getItemId());
		WmsMainInventory entity=wmsMainInventoryService.getByItem(wmsMainInventory);
		if(entity==null){
			j.setSuccess(false);
			j.setMsg("该物料库存数量为0!");
			return j;
		}
		else if(entity.getCurrentOnhandQty()>=miNum){
			int qty=entity.getCurrentOnhandQty()-miNum;
			entity.setCurrentOnhandQty(qty);
		
			User user=userService.getUser(userId);
			entity.setCreateBy(user);
			wmsMainInventoryService.update(entity);
	
			WmsMainRecord wmsMainRecord=new WmsMainRecord();
			wmsMainRecord.setItemId(mdsQuotaDe.getItemId());
			wmsMainRecord.setQty(miNum);
			wmsMainRecord.setUpdateBy(UserUtils.getUser());
			wmsMainRecord.setUpdateDate(new Date());
			wmsMainRecord.setCreateBy(UserUtils.getUser());
			wmsMainRecord.setCreateDate(new Date());
			wmsMainRecord.setState("0");
			
			wmsMainRecord.setId(IdGen.uuid());
			workOrderDispathService.dispath(wmsMainRecord);
			mdsQuotaDetail.setOmiNum(omiNum+miNum);
			mdsQuotaDetailService.updateOmiNum(mdsQuotaDetail);
			
			j.setSuccess(true);
			j.setMsg("生产出库成功");
			return j;
		}else {
			j.setSuccess(false);
			j.setMsg("审批数量不能大于库存数量!");
			return j;
		}
	
		
	}
	/**
	 * 删除生产出库
	 */
	@ResponseBody
	@RequiresPermissions("workorderdispath:workOrderDispath:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(WorkOrderDispath workOrderDispath, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		workOrderDispathService.delete(workOrderDispath);
		j.setMsg("删除生产出库成功");
		return j;
	}
	
	/**
	 * 批量删除生产出库
	 */
	@ResponseBody
	@RequiresPermissions("workorderdispath:workOrderDispath:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			workOrderDispathService.delete(workOrderDispathService.get(id));
		}
		j.setMsg("删除生产出库成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("workorderdispath:workOrderDispath:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public AjaxJson exportFile(WorkOrderDispath workOrderDispath, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "生产出库"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WorkOrderDispath> page = workOrderDispathService.findPage(new Page<WorkOrderDispath>(request, response, -1), workOrderDispath);
    		new ExportExcel("生产出库", WorkOrderDispath.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出生产出库记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("workorderdispath:workOrderDispath:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<WorkOrderDispath> list = ei.getDataList(WorkOrderDispath.class);
			for (WorkOrderDispath workOrderDispath : list){
				try{
					workOrderDispathService.save(workOrderDispath);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条生产出库记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条生产出库记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入生产出库失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/workorderdispath/workOrderDispath/?repage";
    }
	
	/**
	 * 下载导入生产出库数据模板
	 */
	@RequiresPermissions("workorderdispath:workOrderDispath:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "生产出库数据导入模板.xlsx";
    		List<WorkOrderDispath> list = Lists.newArrayList(); 
    		new ExportExcel("生产出库数据", WorkOrderDispath.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/workorderdispath/workOrderDispath/?repage";
    }

}