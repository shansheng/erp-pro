/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmslocator.entity;


import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 库位增删改查Entity
 * @author 孙会楠
 * @version 2018-05-05
 */
public class WmsLocator extends DataEntity<WmsLocator> {
	
	private static final long serialVersionUID = 1L;
	private String wmsInventoryId;		// 库房代码
	private String locatorCode;		// 库位号
	private String item01;		// item_01
	private String item02;		// item_02
	private String item03;		// item_03
	private String item04;		// item_04
	private String item05;		// item_05
	
	//表外字段
	private String wmsInventoryCode;
	private String wmsInventoryName;
	
	public WmsLocator() {
		super();
	}

	public WmsLocator(String id){
		super(id);
	}

	@ExcelField(title="库房代码", align=2, sort=7)
	public String getWmsInventoryId() {
		return wmsInventoryId;
	}

	public void setWmsInventoryId(String wmsInventoryId) {
		this.wmsInventoryId = wmsInventoryId;
	}
	
	@ExcelField(title="库位号", align=2, sort=8)
	public String getLocatorCode() {
		return locatorCode;
	}

	public void setLocatorCode(String locatorCode) {
		this.locatorCode = locatorCode;
	}
	
	@ExcelField(title="item_01", align=2, sort=9)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="item_02", align=2, sort=10)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="item_03", align=2, sort=11)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="item_04", align=2, sort=12)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="item_05", align=2, sort=13)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}
	
	public String getWmsInventoryCode() {
		return wmsInventoryCode;
	}

	public void setWmsInventoryCode(String wmsInventoryCode) {
		this.wmsInventoryCode = wmsInventoryCode;
	}

	public String getWmsInventoryName() {
		return wmsInventoryName;
	}

	public void setWmsInventoryName(String wmsInventoryName) {
		this.wmsInventoryName = wmsInventoryName;
	}

}