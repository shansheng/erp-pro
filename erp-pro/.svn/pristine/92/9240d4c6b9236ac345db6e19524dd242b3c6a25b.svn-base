/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mrp.mrpwo.service;

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
import com.pt.modules.mrp.mrpwo.entity.MrpWo;
import com.pt.modules.mrp.mrpwo.mapper.MrpWoMapper;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 生产任务管理Service
 * @author 孙会楠
 * @version 2018-05-04
 */
@Service
@Transactional(readOnly = true)
public class MrpWoService extends CrudService<MrpWoMapper, MrpWo> {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public MrpWo get(String id) {
		return super.get(id);
	}
	
	public List<MrpWo> findList(MrpWo mrpWo) {
		return super.findList(mrpWo);
	}
	
	public Page<MrpWo> findPage(Page<MrpWo> page, MrpWo mrpWo) {
		return super.findPage(page, mrpWo);
	}
	
	@Transactional(readOnly = false)
	public void save(MrpWo mrpWo) {
		super.save(mrpWo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MrpWo mrpWo) {
		super.delete(mrpWo);
	}

	@Transactional(readOnly = false)
	public void mrpWoDispatch(MrpWo mrpWo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();  
		String statement = "mrpWoDispatch";//
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_wo_id", mrpWo.getId());
        parameterMap.put("i_user_id", UserUtils.getUser().getId());
        parameterMap.put("o_return_int", null);
        parameterMap.put("o_return_string","");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
	}
	
	@Transactional(readOnly = false)
	public String getWoNo() {
		String woNo = "";
		SqlSession sqlSession = sqlSessionFactory.openSession();  
		String statement = "getWoNo";//
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_auto_code", "wo_no");
        parameterMap.put("i_prefix", "");
        parameterMap.put("i_postfix", "");
        parameterMap.put("i_user_id", UserUtils.getUser().getId());
        parameterMap.put("o_return_code","");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
        woNo = parameterMap.get("o_return_code").toString();
        return woNo;
	}
	
	@Transactional(readOnly = false)
	public Map<String, Object> getTaskNo(MrpWo mrpWo) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();  
		String statement = "com.pt.modules.mrp.mrpWo.mapper.MrpWoMapper.getTaskNo";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_auto_code", "wms_inv_in_bill_no");
        parameterMap.put("i_prefix", "");
        parameterMap.put("i_postfix", "");
        parameterMap.put("i_Sys_User_Id", UserUtils.getUser().getId());
        parameterMap.put("o_return_code", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
        return parameterMap;
	}
}