package com.pt.modules.mds.mdsMissQuery.web;

import java.util.List;
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

import com.pt.common.utils.StringUtils;
import com.pt.core.persistence.Page;
import com.pt.core.web.BaseController;
import com.pt.modules.mds.mdsMissQuery.entity.MdsMissQuery;
import com.pt.modules.mds.mdsMissQuery.service.MdsMissQueryService;
@Controller
@RequestMapping(value="${adminPath}/mds/mdsProcurementPlan")
public class mdsMissQueryController extends BaseController{
	@Autowired
	private MdsMissQueryService mdsMissQueryService;
	
	@ModelAttribute
	public MdsMissQuery get(@RequestParam(required=false) String id) {
		MdsMissQuery entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdsMissQueryService.get(id);
		}
		if (entity == null){
			entity = new MdsMissQuery();
		}
		return entity;
	}
	/**
	 * 项目缺件信息查询
	 * @return
	 */
	@RequiresPermissions(value="mds:mdsPurchaseSupplier:missQuery")
	@RequestMapping(value="missProjectQuery")
	public String missQuery() {
		return "modules/mds/mdsMissQuery/mdsProjectMissQuery";
	}
	/**
	 * 项目缺件数据
	 * @param mdsMissQuery
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsPurchaseSupplier:missQuery")
	@RequestMapping(value = "missProjectQueryData")
	public Map<String, Object> data(MdsMissQuery mdsMissQuery, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MdsMissQuery> page = mdsMissQueryService.findPage(new Page<MdsMissQuery>(request, response), mdsMissQuery); 
		return getBootstrapData(page);
	}
	@ResponseBody
	@RequestMapping(value="findByFormId")
	public List<MdsMissQuery> findByFormId(String fromId){
		return mdsMissQueryService.findByFromId(fromId);
	}
	/**
	 * 批量缺件列表
	 * @param mdsMissQuery
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("mds:mdsPurchaseSupplier:missQuery:missQueryList")
	@RequestMapping(value="missData")
	public Map<String,Object> missData(MdsMissQuery mdsMissQuery, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<MdsMissQuery> page = mdsMissQueryService.findMissPage(new Page<MdsMissQuery>(request, response), mdsMissQuery);
		return getBootstrapData(page);
	}
	/**
	 * 批量缺件页面
	 * @return
	 */
	@RequiresPermissions(value="mds:mdsPurchaseSupplier:missQuery:missQueryList")
	@RequestMapping(value="missListQuery")
	public String missListQuery() {
		return "modules/mds/mdsMissQuery/mdsPurchaseMissQuery";
	}
}
