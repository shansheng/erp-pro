/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmaininstorage.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.pt.core.persistence.DataEntity;

/**
 * 入库单Entity
 * @author 郑利
 * @version 2018-09-18
 */
public class WmsMainInList extends DataEntity<WmsMainInList> {
	
	private static final long serialVersionUID = 1L;
	private List<WmsMainInStorage> wmsMainInStorageList = Lists.newArrayList();		// 子表列表
	public WmsMainInList() {
		super();
	}

	public WmsMainInList(String id){
		super(id);
	}

	public List<WmsMainInStorage> getWmsMainInStorageList() {
		return wmsMainInStorageList;
	}

	public void setWmsMainInStorageList(List<WmsMainInStorage> wmsMainInStorageList) {
		this.wmsMainInStorageList = wmsMainInStorageList;
	}
	
	
}