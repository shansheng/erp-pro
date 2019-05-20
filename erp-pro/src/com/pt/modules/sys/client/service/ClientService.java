/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.client.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pt.core.persistence.Page;
import com.pt.core.service.CrudService;
import com.pt.modules.sys.client.entity.Client;
import com.pt.modules.sys.client.mapper.ClientMapper;

/**
 * 客户管理Service
 * @author 郑利
 * @version 2018-08-21
 */
@Service
@Transactional(readOnly = true)
public class ClientService extends CrudService<ClientMapper, Client> {

	public Client get(String id) {
		return super.get(id);
	}
	
	public List<Client> findList(Client client) {
		return super.findList(client);
	}
	
	public Page<Client> findPage(Page<Client> page, Client client) {
		return super.findPage(page, client);
	}
	
	@Transactional(readOnly = false)
	public void save(Client client) {
		super.save(client);
	}
	
	@Transactional(readOnly = false)
	public void delete(Client client) {
		super.delete(client);
	}
	
}