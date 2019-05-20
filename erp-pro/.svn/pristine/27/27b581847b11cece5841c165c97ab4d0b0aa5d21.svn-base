/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.mapper;

import com.pt.core.persistence.BaseMapper;
import com.pt.core.persistence.annotation.MyBatisMapper;
import com.pt.modules.sys.entity.Role;

/**
 * 角色MAPPER接口
 * @author pt
 * @version 2016-12-05
 */
@MyBatisMapper
public interface RoleMapper extends BaseMapper<Role> {

	public Role getByName(Role role);
	
	public Role getByEnname(Role role);

	/**
	 * 维护角色与菜单权限关系
	 * @param role
	 * @return
	 */
	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);
	
	/**
	 * 维护角色与数据权限关系
	 * @param role
	 * @return
	 */
	public int deleteRoleDataRule(Role role);

	public int insertRoleDataRule(Role role);

}
