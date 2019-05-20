/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsoperationseq.service;

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
import com.pt.modules.mds.mdsoperationseq.entity.MdsOperationSeq;
import com.pt.modules.mds.mdsoperationseq.mapper.MdsOperationSeqMapper;
import com.pt.modules.sys.utils.UserUtils;

/**
 * 工序管理Service
 * @author 郑利
 * @version 2018-05-19
 */
@Service
@Transactional(readOnly = true)
public class MdsOperationSeqService extends CrudService<MdsOperationSeqMapper, MdsOperationSeq> {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public MdsOperationSeq get(String id) {
		return super.get(id);
	}
	
	public List<MdsOperationSeq> findList(MdsOperationSeq mdsOperationSeq) {
		return super.findList(mdsOperationSeq);
	}
	
	public Page<MdsOperationSeq> findPage(Page<MdsOperationSeq> page, MdsOperationSeq mdsOperationSeq) {
		return super.findPage(page, mdsOperationSeq);
	}
	
	@Transactional(readOnly = false)
	public void save(MdsOperationSeq mdsOperationSeq) {
		super.save(mdsOperationSeq);
	}
	
	@Transactional(readOnly = false)
	public void delete(MdsOperationSeq mdsOperationSeq) {
		super.delete(mdsOperationSeq);
	}
	/**
	 * 工序上移
	 * @param mdsOperationSeq
	 */
	@Transactional(readOnly = false)
	public Map<String, Object> doBeforeSeq(MdsOperationSeq mdsOperationSeq) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();  
		String statement = "com.pt.modules.mds.mdsoperationseq.mapper.MdsOperationSeqMapper.doBeforeSeq";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_Mds_Operation_Seq_Id", mdsOperationSeq.getId());
        parameterMap.put("i_Sys_User_Id", UserUtils.getUser().getId());
        parameterMap.put("o_Return_Int", null);
        parameterMap.put("o_Return_String", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
        return parameterMap;
	}
	
	/**
	 * 工序下移
	 * @param mdsOperationSeq
	 */
	@Transactional(readOnly = false)
	public Map<String, Object> doAfterSeq(MdsOperationSeq mdsOperationSeq) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "com.pt.modules.mds.mdsoperationseq.mapper.MdsOperationSeqMapper.doAfterSeq";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_Mds_Operation_Seq_Id", mdsOperationSeq.getId());
        parameterMap.put("i_Sys_User_Id", UserUtils.getUser().getId());
        parameterMap.put("o_Return_Int", null);
        parameterMap.put("o_Return_String", "");
        sqlSession.selectOne(statement, parameterMap);
        sqlSession.close();
        return parameterMap;
	}
	
	
	/**
	 * 刷新工序排序
	 * @param mdsOperationSeq
	 */
	@Transactional(readOnly = false)
	public Map<String, Object> refrushSeqInnerOrder(MdsOperationSeq mdsOperationSeq) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();  
		String statement = "com.pt.modules.mds.mdsOperationSeq.mapper.MdsOperationSeqMapper.refrushSeqInnerOrder";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("i_Mds_Route_Id", mdsOperationSeq.getMdsRouteId());
        parameterMap.put("o_Return_Int", null);
        parameterMap.put("o_Return_String", "");
        sqlSession.selectOne(statement, parameterMap);
        //System.out.println("=====================================================" + parameterMap.toString());
        sqlSession.close();
        return parameterMap;
	}
}