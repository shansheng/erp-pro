/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsarrivalaffirm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsarrivalaffirm.entity.MdsArrivalAffirm;
import com.pt.modules.mds.mdsarrivalaffirm.mapper.MdsArrivalAffirmMapper;
import com.pt.modules.mds.mdsarrivalcheckout.entity.MdsArrivalCheckout;
import com.pt.modules.mds.mdsarrivalcheckout.mapper.MdsArrivalCheckoutMapper;
import com.pt.modules.sys.entity.User;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 到货确认Service
 * @author cjx
 * @version 2018-10-10
 */
@Service
@Transactional(readOnly = true)
public class MdsArrivalAffirmService extends CrudService<MdsArrivalAffirmMapper, MdsArrivalAffirm> {
	
	@Autowired
	protected MdsArrivalCheckoutMapper mdsArrivalCheckoutMapper;

	public MdsArrivalAffirm get(String id) {
		MdsArrivalAffirm mdsArrivalAffirm = super.get(id);
		mdsArrivalAffirm.setMdsArrivalCheckoutList(mdsArrivalCheckoutMapper.findAllList(new MdsArrivalCheckout(mdsArrivalAffirm)));
		return mdsArrivalAffirm;
	}
	
	public List<MdsArrivalAffirm> findList(MdsArrivalAffirm mdsArrivalAffirm) {
		return super.findList(mdsArrivalAffirm);
	}
	
	public Page<MdsArrivalAffirm> findPage(Page<MdsArrivalAffirm> page, MdsArrivalAffirm mdsArrivalAffirm) {
		return super.findPage(page, mdsArrivalAffirm);
	}
	
	@Transactional(readOnly = false)
	public void save(List<MdsArrivalAffirm> list) {
		MdsArrivalAffirm mds;
		User user = UserUtils.getUser();
		for (MdsArrivalAffirm mdsArrivalAffirm : list) {
			mds = get(mdsArrivalAffirm);
			mds.setArrivalnum(mds.getArrivalnum()+mdsArrivalAffirm.getArrivalnumNew());
			mds.setArrivalBy(user.getId());
			mds.setArrivalDate(new Date());
			if(mds.getPurchaseAmount()>mds.getArrivalnum()){
				mds.setFinishState("5");
			}else{
				mds.setFinishState("6");
			}
			mapper.update(mds);
			//将获取的数据存入到到货检验表中.
			MdsArrivalCheckout entity = new MdsArrivalCheckout(mds,mdsArrivalAffirm.getArrivalnumNew(),user);
			mdsArrivalCheckoutMapper.insert(entity);
		}
		
	}

	
}