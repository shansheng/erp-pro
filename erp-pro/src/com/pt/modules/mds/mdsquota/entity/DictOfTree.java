package com.pt.modules.mds.mdsquota.entity;

import com.pt.core.persistence.DataEntity;

public class DictOfTree extends DataEntity<DictOfTree>{

	private static final long serialVersionUID = 1L;
	
	private String dictName;
	private String value;
	private String num;
	
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
}
