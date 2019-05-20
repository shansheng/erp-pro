/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.test.entity.treetable;


import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 车辆Entity
 * @author lgf
 * @version 2017-06-12
 */
public class Car extends DataEntity<Car> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 品牌
	private CarKind kind;		// 车系 父类
	
	public Car() {
		super();
	}

	public Car(String id){
		super(id);
	}

	public Car(CarKind kind){
		this.kind = kind;
	}

	@ExcelField(title="品牌", align=2, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public CarKind getKind() {
		return kind;
	}

	public void setKind(CarKind kind) {
		this.kind = kind;
	}
	
}