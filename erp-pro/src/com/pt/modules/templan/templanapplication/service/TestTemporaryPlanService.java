/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.templan.templanapplication.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.common.utils.StringUtils;
import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.templan.templanapplication.dto.TemporaryStateDTO;
import com.pt.modules.templan.templanapplication.entity.TestTemporaryPlan;
import com.pt.modules.templan.templanapplication.entity.TestTemporaryPlanList;
import com.pt.modules.templan.templanapplication.mapper.TestTemporaryPlanListMapper;
import com.pt.modules.templan.templanapplication.mapper.TestTemporaryPlanMapper;

/**
 * 临时计划申请Service
 * @author cjx
 * @version 2018-09-04
 */
@Service
@Transactional(readOnly = true)
public class TestTemporaryPlanService extends CrudService<TestTemporaryPlanMapper, TestTemporaryPlan> {

	@Autowired
	private TestTemporaryPlanListMapper testTemporaryPlanListMapper;
	@Autowired
	private TestTemporaryPlanMapper testTemporaryPlanMapper;
	public TestTemporaryPlan get(String id) {
		TestTemporaryPlan testTemporaryPlan = super.get(id);
		testTemporaryPlan.setTestTemporaryPlanListList(testTemporaryPlanListMapper.findList(new TestTemporaryPlanList(testTemporaryPlan)));
		return testTemporaryPlan;
	}
	
	
	public List<TestTemporaryPlan> findList(TestTemporaryPlan testTemporaryPlan) {
		return super.findList(testTemporaryPlan);
	}
	
	public Page<TestTemporaryPlan> findPage(Page<TestTemporaryPlan> page, TestTemporaryPlan testTemporaryPlan) {
		return super.findPage(page, testTemporaryPlan);
	}
	
	/**
	 * @param code
	 * @return
	 */
	public TestTemporaryPlan getByCode(String code) {
		
		return mapper.getByCode(code);
	}
	
	@Transactional(readOnly = false)
	public void save(TestTemporaryPlan testTemporaryPlan) {
		System.out.println("testTemporaryPlan:" + testTemporaryPlan.toString());
		testTemporaryPlan.setCreateDate(new Date());
		if (testTemporaryPlan.getIsNewRecord()){
			testTemporaryPlan.setState("10");
			testTemporaryPlan.preInsert();
			mapper.insert(testTemporaryPlan);
		}else{
			testTemporaryPlan.preUpdate();
			 mapper.update(testTemporaryPlan);
		}
		
		for (TestTemporaryPlanList testTemporaryPlanList : testTemporaryPlan.getTestTemporaryPlanListList()){
		/*	if (testTemporaryPlanList.getId() == null){
				continue;
			}*/
//			if (TestTemporaryPlanList.DEL_FLAG_NORMAL.equals(testTemporaryPlanList.getDelFlag())){
				if (StringUtils.isBlank(testTemporaryPlanList.getId())){
					testTemporaryPlanList.setTemporaryPlanId(testTemporaryPlan.getId());
					testTemporaryPlanList.preInsert();
					testTemporaryPlanListMapper.insert(testTemporaryPlanList);
				}else{
					testTemporaryPlanList.preUpdate();
					testTemporaryPlanListMapper.update(testTemporaryPlanList);
				}
//			}else{
//				testTemporaryPlanListMapper.delete(testTemporaryPlanList);
//			}
		}
	}
	
	@Transactional(readOnly = false)
	public void doSubmit(TestTemporaryPlan testTemporaryPlan){
		testTemporaryPlan.preUpdate();
		testTemporaryPlan.setState("20");
		mapper.doSubmit(testTemporaryPlan);
		}
	
	@Transactional(readOnly = false)
	public void delete(TestTemporaryPlan testTemporaryPlan) {
		super.delete(testTemporaryPlan);
		testTemporaryPlanListMapper.delete(new TestTemporaryPlanList(testTemporaryPlan));
	}
	public LinkedHashMap<String,Object> getTemporaryState(){
		int temApply = testTemporaryPlanMapper.getApply();
		int temOnPurchase = testTemporaryPlanMapper.getOnPurchase();
		int temEndPurchase = testTemporaryPlanMapper.getEndPurchase();
		LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
		TemporaryStateDTO tem = new TemporaryStateDTO();
		tem.setStatus("部门待审批数,副总待审批数,采购完成数");
		tem.setNum(temApply + "," + temOnPurchase + "," + temEndPurchase);
		map.put("data",tem);
		return map;
		
	}
	
}