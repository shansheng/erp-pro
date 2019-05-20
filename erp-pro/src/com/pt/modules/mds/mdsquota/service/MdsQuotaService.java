/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsquota.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.common.json.AjaxJson;
import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsprojectpcmplan.entity.MdsProjectpcmplan;
import com.pt.modules.mds.mdsprojectpcmplan.mapper.MdsProjectpcmplanMapper;
import com.pt.modules.mds.mdsquota.entity.MdsQuota;
import com.pt.modules.mds.mdsquota.entity.MdsQuotaDetail;
import com.pt.modules.mds.mdsquota.mapper.MdsQuotaMapper;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.mapper.UserMapper;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 材料定额管理Service
 * @author cjx
 * @version 2018-09-11
 */
@Service
@Transactional(readOnly = true)
public class MdsQuotaService extends CrudService<MdsQuotaMapper, MdsQuota> {

	@Autowired
	private MdsQuotaDetailService mdsQuotaDetailService;
	
	@Autowired
	private MdsProjectpcmplanMapper mdsProjectpcmplanMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	public MdsQuota get(String id) {
		MdsQuota mdsQuota = super.get(id);
		mdsQuota.setMdsQuotaDetailList(mdsQuotaDetailService.findList(new MdsQuotaDetail(mdsQuota)));
		return mdsQuota;
	}
	
	public List<MdsQuota> findList(MdsQuota mdsQuota) {
		return super.findList(mdsQuota);
	}
	
	public Page<MdsQuota> findPage(Page<MdsQuota> page, MdsQuota mdsQuota) {
		User user = UserUtils.getUser();
		user = userMapper.get(user.getId());
		mdsQuota.setCreateBy(user);
		return super.findPage(page, mdsQuota);
	}
	
	@Transactional(readOnly = false)
	public void save(MdsQuota mdsQuota) {
		super.save(mdsQuota);
	}
	
	@Transactional(readOnly = false)
	public void update(MdsQuotaDetail mdsQuotaDetail) {
		mdsQuotaDetail.preUpdate();
		mapper.update(mdsQuotaDetail);
	}
	
	/**
	 * 编制完成
	 * @param mdsQuota
	 */
	@Transactional(readOnly = false)
	public void compile(MdsQuota mdsQuota) {
		mdsQuota.setState("30");
		mdsQuota.preUpdate();
		mapper.changeConfirm(mdsQuota);
	}
	
	/**
	 * 撤销编制
	 * @param mdsQuota
	 * @return
	 */
	@Transactional(readOnly = false)
	public AjaxJson unCompile(MdsQuota mdsQuota) {
		AjaxJson j = new AjaxJson();
		MdsQuota getMdsQuota = get(mdsQuota);
		if("40".equals(getMdsQuota.getState())){
			j.setSuccess(false);
			j.setMsg("撤销编制失败,已经定版!");
			return j;
		}else{
			mdsQuota.setState("20");
			mdsQuota.preUpdate();
			mapper.changeConfirm(mdsQuota);
			j.setMsg("撤销编制成功");
			return j;
		}
	}
	
	/**
	 * 定版完成
	 * @param mdsQuota
	 */
	@Transactional(readOnly = false)
	public void confirm(MdsQuota mdsQuota) {
		mdsQuota.setState("40");
		mdsQuota.preUpdate();
		List<MdsQuotaDetail> childList = mdsQuotaDetailService.findNotVersionList(new MdsQuotaDetail(mdsQuota));
		for (MdsQuotaDetail mdsQuotaDetail : childList) {
			mdsQuotaDetail.setIsVersion("1");
		}
		if(!childList.isEmpty()){
			mdsQuotaDetailService.changVersion(childList);
		}
		mapper.changeConfirm(mdsQuota);
		mapper.updateVerify(UserUtils.getUser().getName(), mdsQuota.getId());
		//修改销售订单产品表中的项目采购状态
		mdsProjectpcmplanMapper.update(new MdsProjectpcmplan(mdsQuota));
		
	}
	
	/**
	 * 撤销定版
	 * @param mdsQuota
	 * @return
	 */
	@Transactional(readOnly = false)
	public void delconfirm(MdsQuota mdsQuota) {
//		AjaxJson j = new AjaxJson();
//		MdsQuota getMdsQuota = get(mdsQuota);
//		if("1".equals(getMdsQuota.getAddProcurement())){
//			j.setSuccess(false);
//			j.setMsg("撤销定版失败,项目采购计划已经添加到采购计划中!");
//			return j;
//		}else if("1".equals(getMdsQuota.getIsAdd())){
//			j.setSuccess(false);
//			j.setMsg("撤销定版失败,数据已经到批量采购计划中!");
//			return j;
//		}else{
//			MdsQuotaDetail mdsQuotaDetail = new MdsQuotaDetail();
//			mdsQuotaDetail.setMdsQuotaId(mdsQuota.getId());
			mdsQuota.setState("20");
			mdsQuota.preUpdate();
			mapper.changeConfirm(mdsQuota);
//			j.setMsg("撤销定版成功");
//			return j;
//		}
	}
	
	
	@Transactional(readOnly = false)
	public void delete(MdsQuota mdsQuota) {
		super.delete(mdsQuota);
		mdsQuotaDetailService.delete(new MdsQuotaDetail(mdsQuota));
	}

	/**
	 * @param sysMarketId
	 * @return
	 */
	public MdsQuota findByMarketListId(String sysMarketListId) {
		return mapper.findByMarketListId(sysMarketListId);
	}

	/**
	 * @param mdsQuota
	 * @return
	 */
	public MdsQuota getByMarketItemId(MdsQuota mdsQuota) {
	
		return mapper.getByMarketItemId(mdsQuota);
	}

	
	
}