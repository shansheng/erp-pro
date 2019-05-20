/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsquota.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pt.common.config.Global;
import com.pt.common.json.AjaxJson;
import com.pt.common.utils.IdGen;
import com.pt.common.utils.StringUtils;
import com.pt.common.utils.excel.ExportExcel;
import com.pt.common.utils.excel.ImportExcel;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.modules.mds.mdsitem.entity.MdsItem;
import com.pt.modules.mds.mdsquota.entity.MdsQuota;
import com.pt.modules.mds.mdsquota.entity.MdsQuotaDetail;
import com.pt.modules.mds.mdsquota.service.MdsQuotaDetailService;
import com.pt.modules.mds.mdsquota.service.MdsQuotaService;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.utils.UserUtils;


/**
 * 材料定额管理Controller
 * @author cjx
 * @version 2018-09-11
 */
@Controller
@RequestMapping(value = "${adminPath}/mds/mdsquota/mdsQuotaDetail")
public class MdsQuotaDetailController extends BaseController {
	
	@Autowired
	private MdsQuotaDetailService mdsQuotaDetailService;
	
	@Autowired
	private MdsQuotaService mdsQuotaService;
	
	@ModelAttribute
	public MdsQuotaDetail get(@RequestParam(required=false) String id) {
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
	 * 编辑材料定额表单页面
	 */
	@RequiresPermissions("mds:mdsquota:mdsQuota:edit")
	@RequestMapping(value = "detailEdit")
	public String edit(MdsQuotaDetail mdsQuotaDetail, Model model) {
		model.addAttribute("mdsQuotaDetail", mdsQuotaDetail);
		if(StringUtils.isBlank(mdsQuotaDetail.getId())){//如果ID是空为添加
			model.addAttribute("isAdd", true);
		}
		return "modules/mds/mdsquota/mdsQuotaDetailForm";
	}

	/**
	 * 保存材料定额详情表
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsquota:mdsQuota:edit")
	@RequestMapping(value = "save")
	public AjaxJson save(MdsQuotaDetail mdsQuotaDetail, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!beanValidator(model, mdsQuotaDetail)){
			j.setSuccess(false);
			j.setMsg("非法参数！");
			return j;
		}
		//新增或编辑表单保存
		mdsQuotaDetailService.save(mdsQuotaDetail);//保存
		j.setSuccess(true);
		j.setMsg("保存物料成功");
		return j;
	}
	
	/**
	 * 批量撤销材料定额详情定版状态
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsquota:mdsQuota:delconfirm")
	@RequestMapping(value = "delconfirm")
	public AjaxJson delconfirm(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		List<MdsQuotaDetail> childList = new ArrayList<MdsQuotaDetail>();
		for(String id : idArray){
			MdsQuotaDetail mdsQuotaDetail = get(id);
			childList.add(mdsQuotaDetail);
		}
		//修改所有物料的定版状态
		j = mdsQuotaDetailService.delconfirm(childList);
		//修改主表中的状态
		mdsQuotaService.delconfirm(new MdsQuota(childList.get(0).getMdsQuotaId()));
		return j;
	}
	
	/**
	 * 删除材料定额详情
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsquota:mdsQuota:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(MdsQuotaDetail mdsQuotaDetail, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		mdsQuotaDetailService.delete(mdsQuotaDetail);
		j.setMsg("删除材料定额详情成功");
		return j;
	}
	
	/**
	 * 批量删除材料定额详情
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsquota:mdsQuota:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			mdsQuotaDetailService.delete(mdsQuotaDetailService.get(id));
		}
		j.setMsg("删除材料定额详情成功");
		return j;
	}
	
	/**
	 * 传入的id是sys_market_list中的id,然后根据这个id查询该产品所需的物料
	 * @param mdsItem
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findItemByMarket")
	public Map<String, Object> findItemByMarkList(MdsItem mdsItem,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<MdsItem> page = mdsQuotaDetailService.findItemByMarkList(new Page<MdsItem>(request, response), mdsItem);
		return getBootstrapData(page);
	}

	/**
	 * 导入
	 * @param file
	 * @param redirectAttributes
	 * @return
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@RequiresPermissions("mds:mdsquota:mdsQuota:import")
	@RequestMapping(value="import")
	public String importFile(String id,MultipartFile file,RedirectAttributes redirectAttributes) throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException {
		try {
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file,1,0);
			List<MdsQuotaDetail> list = ei.getDataList(MdsQuotaDetail.class);
			List<MdsQuotaDetail> quotaList = new ArrayList<>();
			List<MdsQuotaDetail> updateList = new ArrayList<>();
			for(int i=0;i<list.size();i++) {
				MdsQuotaDetail mdsQuotaDetail = list.get(i);
				mdsQuotaDetail.setMdsQuotaId(id);
				if(!mdsQuotaDetailService.classifyValidate(mdsQuotaDetail)) {
					failureMsg.insert(0,"第"+ (i+1) +"条的分类数据不合法");
				}else
				if(!mdsQuotaDetailService.serialValidate(mdsQuotaDetail)){
					failureMsg.insert(0,"第"+ (i+1) +"条的序号数据不合法");
				}else
				if(!mdsQuotaDetailService.itemCodeValidate(mdsQuotaDetail)) {
					failureMsg.insert(0,"第"+ (i+1) +"条的物料编码数据不合法");
				}else
				if(!mdsQuotaDetailService.quotaValidate(mdsQuotaDetail)){
					failureMsg.insert(0,"第"+(i+1) +"条的单台定额数据不合法");
				}else
				if(mdsQuotaDetail.getRemarks()==null){
					failureMsg.insert(0,"第"+(i+1) +"条备注不能为空");
				}else{
					MdsItem mdsItem = mdsQuotaDetailService.getMdsItem(mdsQuotaDetail.getItemCode());
					if(mdsItem==null) {
						failureMsg.insert(0,"第"+(i+1) +"条的物料信息不存在数据库中");
					}else {
						mdsQuotaDetail.setItemId(mdsItem.getId());
						mdsQuotaDetail.setItemName(mdsItem.getItemName());
						mdsQuotaDetail.setNormalField(mdsItem.getNormalField());
						mdsQuotaDetail.setSpecs(mdsItem.getSpecs());
						mdsQuotaDetail.setModelNum(mdsItem.getModelNum());
						mdsQuotaDetail.setClassify(mdsQuotaDetailService.getValue(mdsQuotaDetail.getClassify()).getValue());
						mdsQuotaDetail.setId(IdGen.uuid());
						mdsQuotaDetail.preInsert();
					    	//查看表中数据是否重复
							List<MdsQuotaDetail> mdslist=mdsQuotaDetailService.findList(mdsQuotaDetail);
							if(mdslist==null || mdslist.size()==0) {
								quotaList.add(mdsQuotaDetail);
							}else{
								 updateList.add(mdsQuotaDetail);
							}
					}
				}
			}
			try {
				if(quotaList!=null && quotaList.size()!=0) {
					System.out.println("quta:"+quotaList+"size():"+quotaList.size());
					mdsQuotaDetailService.saveList(quotaList);
				}
				if(updateList!=null && updateList.size()!=0) {
					mdsQuotaDetailService.updateList(updateList);
				}
				if(StringUtils.isBlank(failureMsg)) {
					addMessage(redirectAttributes,"导入成功");
				}else {
					addMessage(redirectAttributes,"导入信息出错:"+failureMsg);
				}
			} catch (Exception e) {
				addMessage(redirectAttributes, "导入材料定额失败！失败信息："+e.getMessage());
			}
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入材料定额失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mds/mdsquota/mdsQuota/edit?id="+id;
	}
	/**
	 * 下载导入模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws IOException 
	 */
	
	@RequiresPermissions("mds:mdsquota:mdsQuota:import")
	@RequestMapping(value = "template")
	public String  getFileTemplate(String id,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException {
		try {
			String fileName ="材料定额数据导入模板.xlsx";
			String title="红色字体为必填字段,序号和单台定额必须为整数,备注信息不能超过50个字";
			List<String > headerList = new ArrayList<>();
			//示例数据
			List<String> exampleList = new ArrayList<>();
			ArrayList<String> classifyList = mdsQuotaDetailService.getMdsClassify();
			 ExportExcel exportExcel = new ExportExcel();
			 exportExcel.insertList(headerList, exampleList).createExcel(title,headerList,exampleList,classifyList).write(response,fileName).dispose();
			addMessage(redirectAttributes,"模板下载成功");
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes,"模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mds/mdsquota/mdsQuota/edit?id="+id;
	}

	public static String getType(Object o){ //获取变量类型方法
		return o.getClass().toString(); //使用int类型的getClass()方法
		} 
	
	
   /*  
	 private StringBuilder isValidate(MdsQuotaDetail mdsQuotaDetail,int i){
		 StringBuilder failureMsg = new StringBuilder();
			if(!mdsQuotaDetailService.classifyValidate(mdsQuotaDetail)) {
				failureMsg.insert(0,"第"+ (i+1) +"条的分类数据不合法");
			}
			if(!mdsQuotaDetailService.serialValidate(mdsQuotaDetail)){
				failureMsg.insert(0,"第"+ (i+1) +"条的序号数据不合法");
			}
			if(!mdsQuotaDetailService.itemCodeValidate(mdsQuotaDetail)) {
				failureMsg.insert(0,"第"+ (i+1) +"条的物料编码数据不合法");
			}
			if(!mdsQuotaDetailService.quotaValidate(mdsQuotaDetail)){
				failureMsg.insert(0,"第"+(i+1) +"条的单台定额数据不合法");
			}
			if(mdsQuotaDetail.getRemarks()==null || "".equals(mdsQuotaDetail.getRemarks())){
				failureMsg.insert(0,"第"+(i+1) +"条备注不能为空");
			}
			return failureMsg;
	 }*/
}









