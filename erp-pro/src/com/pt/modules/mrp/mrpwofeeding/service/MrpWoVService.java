/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mrp.mrpwofeeding.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mrp.mrpwofeeding.entity.MrpWoV;
import com.pt.modules.mrp.mrpwofeeding.mapper.MrpWoVMapper;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 投料Service
 * @author 郑利
 * @version 2018-06-06
 */
@Service
@Transactional(readOnly = true)
public class MrpWoVService extends CrudService<MrpWoVMapper, MrpWoV> {
	@Autowired
	private  SqlSessionFactory sqlSessionFactory;
	public MrpWoV get(String id) {
		return super.get(id);
	}
	
	public List<MrpWoV> findList(MrpWoV mrpWoV) {
		return super.findList(mrpWoV);
	}
	
	public Page<MrpWoV> findPage(Page<MrpWoV> page, MrpWoV mrpWoV) {
		return super.findPage(page, mrpWoV);
	}
	
	@Transactional(readOnly = false)
	public void save(MrpWoV mrpWoV) {
		super.save(mrpWoV);
	}
	
	@Transactional(readOnly = false)
	public void delete(MrpWoV mrpWoV) {
		super.delete(mrpWoV);
	}

	/**
	 * @param mrpWoV
	 */
	@Transactional(readOnly = false)
	public void batchMesEwoInsert(String mrpWoId,Double tlQty) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "feedingSubmit";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_wo_id", mrpWoId);
        parameterMap.put("i_qty", tlQty);
        parameterMap.put("i_user_id", UserUtils.getUser().getId());
        parameterMap.put("o_return_int", null);
        parameterMap.put("o_return_string", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
		
	}
	/**
	 * 投料提交
	 * @param mesMrpWoFeeding
	 */
	@Transactional(readOnly = false)
	public  void doSubmit(String mrpWoId,Double tlQty) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "feedingSubmit";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_wo_id", mrpWoId);
        parameterMap.put("i_qty", tlQty);
        parameterMap.put("i_user_id", UserUtils.getUser().getId());
        parameterMap.put("o_return_int", null);
        parameterMap.put("o_return_string", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
	}
}