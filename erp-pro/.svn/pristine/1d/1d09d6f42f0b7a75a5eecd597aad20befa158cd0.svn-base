package com.pt.modules.mds.mdsMissQuery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.mds.mdsMissQuery.entity.MdsMissQuery;
import com.pt.modules.mds.mdsMissQuery.mapper.MdsMissQueryMapper;

@Service
@Transactional(readOnly = true)
public class MdsMissQueryService extends CrudService<MdsMissQueryMapper, MdsMissQuery> {
	@Autowired
	private MdsMissQueryMapper mdsMissQueryMapper;
	public MdsMissQuery get(String id) {
		return super.get(id);
	}
	public Page<MdsMissQuery> findPage(Page<MdsMissQuery> page, MdsMissQuery mdsMissQuery) {
		return super.findPage(page, mdsMissQuery);
	}
	public List<MdsMissQuery> findByFromId(String fromId){
		return  mdsMissQueryMapper.findByFromId(fromId);
	}
	public Page<MdsMissQuery> findMissPage(Page<MdsMissQuery> page, MdsMissQuery mdsMissQuery){
		mdsMissQuery.setPage(page);
		page.setList(mdsMissQueryMapper.findPurchaseMiss(mdsMissQuery));
		return page;
	}
	
}
