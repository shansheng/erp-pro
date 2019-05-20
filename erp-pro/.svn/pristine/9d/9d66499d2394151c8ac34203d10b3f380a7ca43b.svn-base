/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysqueclass.entity;


import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 问题分类管理Entity
 * @author 郑利
 * @version 2018-10-24
 */
public class SysQueClass extends DataEntity<SysQueClass> {
	
	private static final long serialVersionUID = 1L;
	private String classify;		// classify
	private String subclassification;		// subclassification
	private String subclassificationName;		// subclassification_name
	
	public SysQueClass() {
		super();
	}

	public SysQueClass(String id){
		super(id);
	}

	@ExcelField(title="classify", align=2, sort=1)
	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	@ExcelField(title="subclassification", align=2, sort=2)
	public String getSubclassification() {
		return subclassification;
	}

	public void setSubclassification(String subclassification) {
		this.subclassification = subclassification;
	}
	
	@ExcelField(title="subclassification_name", align=2, sort=3)
	public String getSubclassificationName() {
		return subclassificationName;
	}

	public void setSubclassificationName(String subclassificationName) {
		this.subclassificationName = subclassificationName;
	}
	
}