/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.prickle.entity;


import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 计量单位管理Entity
 * @author 郑利
 * @version 2018-08-29
 */
public class Prickle extends DataEntity<Prickle> {
	
	private static final long serialVersionUID = 1L;
	private String unit;		// 单位
	private String unitText;		// 单位文本
	private Integer sort;		// 排序
	
	public Prickle() {
		super();
	}

	public Prickle(String id){
		super(id);
	}

	@ExcelField(title="单位", align=2, sort=1)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@ExcelField(title="单位文本", align=2, sort=2)
	public String getUnitText() {
		return unitText;
	}

	public void setUnitText(String unitText) {
		this.unitText = unitText;
	}
	
	@ExcelField(title="排序", align=2, sort=3)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}