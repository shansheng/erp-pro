/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.meswo.service;

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
import com.pt.modules.mes.meswo.entity.MesEwo;
import com.pt.modules.mes.meswo.mapper.MesEwoMapper;
import com.pt.modules.sys.utils.UserUtils;


/**
 * 计划管理Service
 * @author 郑利
 * @version 2018-06-05
 */
@Service
@Transactional(readOnly = true)
public class MesEwoService extends CrudService<MesEwoMapper, MesEwo> {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	public MesEwo get(String id) {
		return super.get(id);
	}
	
	public List<MesEwo> findList(MesEwo mesEwo) {
		return super.findList(mesEwo);
	}
	
	public Page<MesEwo> findPage(Page<MesEwo> page, MesEwo mesEwo) {
		return super.findPage(page, mesEwo);
	}
	
	@Transactional(readOnly = false)
	public void save(MesEwo mesEwo) {
		super.save(mesEwo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MesEwo mesEwo) {
		super.delete(mesEwo);
	}
	
	
	/**
	 * 生产计划下达
	 * @param mesEwo
	 */
	@Transactional(readOnly = false)
	public void mesEwoDispatch(String  id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "mesEwoDispatch";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_ewo_id", id);
        parameterMap.put("i_user_id", UserUtils.getUser().getId());
        parameterMap.put("o_return_int", null);
        parameterMap.put("o_return_string", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
	}
	
	/**
	 * 删除投料
	 * @param mesEwo
	 */
	@Transactional(readOnly = false)
	public void mesEwoDispatchDel(String  id) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "mesEwoDispatchDel";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_ewo_id", id);
        parameterMap.put("i_user_id", UserUtils.getUser().getId());
        parameterMap.put("o_Return_Int", null);
        parameterMap.put("o_Return_String", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
	}
}