/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsquota.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.common.json.AjaxJson;
import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsitem.entity.MdsItem;
import com.pt.modules.mds.mdsitem.service.MdsItemService;
import com.pt.modules.mds.mdsquota.entity.DictOfTree;
import com.pt.modules.mds.mdsquota.entity.MdsQuota;
import com.pt.modules.mds.mdsquota.entity.MdsQuotaDetail;
import com.pt.modules.mds.mdsquota.mapper.MdsQuotaDetailMapper;
import com.pt.modules.mds.mdsquota.mapper.MdsQuotaMapper;
import com.pt.modules.sys.entity.DictValue;

/**
 * 材料定额管理Service
 * @author cjx
 * @version 2018-09-11
 */
@Service
@Transactional(readOnly = true)
public class MdsQuotaDetailService extends CrudService<MdsQuotaDetailMapper, MdsQuotaDetail> {
	
	@Autowired
	private MdsQuotaMapper mdsQuotaMapper;
	
	@Autowired
	private MdsItemService mdsItemService;
	
	public Page<MdsQuotaDetail> findPage(Page<MdsQuotaDetail> page, MdsQuotaDetail mdsQuotaDetail) {
		return super.findPage(page, mdsQuotaDetail);
	}
	
	public List<MdsQuotaDetail> findList(MdsQuotaDetail mdsQuotaDetail){
		return mapper.findList(mdsQuotaDetail);
	}
	
	/**
	 * 查询所有未定版的物料
	 * @param mdsQuotaDetail
	 * @return
	 */
	public List<MdsQuotaDetail> findNotVersionList(MdsQuotaDetail mdsQuotaDetail){
		return mapper.findNotVersionList(mdsQuotaDetail);
	}
	
	/**
	 * 查询指定版本下的物料
	 * @param version
	 * @return
	 */
	public List<MdsQuotaDetail> findVersionList(MdsQuotaDetail mdsQuotaDetail){
		return mapper.findVersionList(mdsQuotaDetail);
	}
	
	public List<DictOfTree> getChildren(MdsQuota mdsQuota){
		List<DictOfTree> lists = mapper.getChildren(mdsQuota);
		/*List<DictOfTree> listCopy = new ArrayList<DictOfTree>();
		for (DictOfTree dictOfTree : lists) {
			if(StringUtils.isNotBlank(dictOfTree.getNum())){
				listCopy.add(dictOfTree);
			}
		}
		}*/
		return lists;
	}
	
	@Transactional(readOnly = false)
	public void save(MdsQuotaDetail mdsQuotaDetail) {
		/*super.save(mdsQuotaDetail);*/
		 if (mdsQuotaDetail.getIsNewRecord()) {
			 mdsQuotaDetail.preInsert();
			MdsQuota mdsQuota = mdsQuotaMapper.get(new MdsQuota(mdsQuotaDetail.getMdsQuotaId()));
			MdsItem mdsItem = mdsItemService.get(new MdsItem(mdsQuotaDetail.getItemId()));
			if("1".equals(mdsItem.getSourceType())){
				if("1".equals(mdsItem.getOutSourceType())){
					mdsQuotaDetail.setAddProcurement("0");
					mdsQuotaDetail.setIsAdd("1");
				}else{
					mdsQuotaDetail.setAddProcurement("1");
					mdsQuotaDetail.setIsAdd("0");
				}
			}
			mapper.insert(mdsQuotaDetail);
			//判断当前产品主表中的状态是否为已定版,如果是的话就修改为编制中
			if("40".equals(mdsQuota.getState())){
				mdsQuota.setState("20");
				mdsQuotaMapper.changeConfirm(mdsQuota);
			}
	     } else {
	    	 mdsQuotaDetail.preUpdate();
	    	 mapper.update(mdsQuotaDetail);
	     }
	}
	
	/**
	 * 修改定版状态
	 * @param mdsQuotaDetail
	 */
	@Transactional(readOnly = false)
	public void changVersion(List<MdsQuotaDetail> list){
		mapper.changVersion(list);
	}
	
	/**
	 * 撤销定版
	 * @param list
	 */
	@Transactional(readOnly = false)
	public AjaxJson delconfirm(List<MdsQuotaDetail> list){
		AjaxJson j = new AjaxJson();
		for (MdsQuotaDetail mdsQuotaDetail : list) {
			if("1".equals(mdsQuotaDetail.getAddProcurement())&&"1".equals(mdsQuotaDetail.getIsAdd())){
				j.setSuccess(false);
				j.setMsg("撤销定版失败,已经添加到采购计划中!");
				break;
			}else{
				mdsQuotaDetail.setIsVersion("0");
			}
		}
		if(j.isSuccess() == true){
			mapper.changVersion(list);
			j.setMsg("撤销定版成功!");
		}
		return j;
	}
	
	//通过sys_market_list_id查询当前产品中的物料
	public Page<MdsItem> findItemByMarkList(Page<MdsItem> page,MdsItem mdsItem){
		dataRuleFilter(mdsItem);
		mdsItem.setPage(page);
		page.setList(mapper.findItemByMarkList(mdsItem.getId()));
		return page;
	}
	
	public int updateList(List<MdsQuotaDetail> list) {
		return mapper.updateList(list);
	}
	public int saveList(List<MdsQuotaDetail> list) {
		return mapper.saveList(list);
	}
	/**
	 * 分来验证
	 * @param mdsQuotaDetail
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean classifyValidate(MdsQuotaDetail mdsQuotaDetail) {
		DictValue dict =mapper.findLabel(mdsQuotaDetail.getClassify());
		   if(dict!=null)return true;
		return false;
	}
	/**
	 * 整数校验
	 * @param mdsQuotaDetail
	 * @return
	 */
	public boolean serialValidate(MdsQuotaDetail mdsQuotaDetail) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(mdsQuotaDetail.getSerial()).matches();
		
	}
	/**
	 * 编码验证
	 * @param mdsQuotaDetail
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean  itemCodeValidate(MdsQuotaDetail mdsQuotaDetail) {
		MdsItem mdsItem = mapper.getMdsItem(mdsQuotaDetail.getItemCode());
		if(mdsItem == null)return false;
		return true;
	}
	/**
	 * 单台定额正整数验证
	 * @param mdsQuotaDetail
	 * @return
	 */
	public boolean quotaValidate(MdsQuotaDetail mdsQuotaDetail) {
		Pattern pattern = Pattern.compile("^\\d+(\\.{0,1}\\d+){0,1}$");
		return pattern.matcher(String.valueOf(mdsQuotaDetail.getQuota())).matches();
	}
	
	/*
	@Transactional(readOnly = false)
	public void confirm(MdsQuota mdsQuota) {
		mdsQuota.setState("30");
		mdsQuota.preUpdate();
		mapper.changeConfirm(mdsQuota);
		mdsQuotaDetailMapper.delete(new MdsQuotaDetail(mdsQuota));
	}
	
	@Transactional(readOnly = false)
	public void delconfirm(MdsQuota mdsQuota) {
		mdsQuota.setState("20");
		mdsQuota.preUpdate();
		mapper.changeConfirm(mdsQuota);
		mdsQuotaDetailMapper.delete(new MdsQuotaDetail(mdsQuota));
	}
	*/
	
	@Transactional(readOnly = false)
	public void delete(MdsQuotaDetail mdsQuotaDetail) {
		super.delete(mdsQuotaDetail);
	}
	@Transactional(readOnly=false)
	public MdsItem getMdsItem(String itemCode) {
		return mapper.getMdsItem(itemCode);
	}
	@Transactional(readOnly=false)
	public DictValue getValue(String classify) {
		return mapper.getValue(classify);
	}

	/**
	 * @param mdsQuotaId
	 * @return
	 */
	public List<MdsQuotaDetail> getByQuotaId(String mdsQuotaId) {
		
		return mapper.getByQuotaId(mdsQuotaId);
	}

	/**
	 * @param mdsQuotaDetail
	 */
	public void updateOmiNum(MdsQuotaDetail mdsQuotaDetail) {
		mapper.updateOmiNum(mdsQuotaDetail);
		
	}

	/**
	 * @param mdsQuotaDetail
	 * @return
	 */
	public MdsQuotaDetail getByQuotaItemId(MdsQuotaDetail mdsQuotaDetail) {
		
		return mapper.getByQuotaItemId(mdsQuotaDetail);
	}
	public ArrayList<String> getMdsClassify(){
		return mapper.getMdsClassify();
	}
}