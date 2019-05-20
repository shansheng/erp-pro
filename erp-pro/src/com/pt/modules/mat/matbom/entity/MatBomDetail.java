/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matbom.entity;


import java.util.List;
import com.google.common.collect.Lists;

import com.pt.core.persistence.TreeEntity;

/**
 * Bom管理Entity
 * @author 郑利
 * @version 2018-09-27
 */
public class MatBomDetail extends TreeEntity<MatBomDetail> {
	
	private static final long serialVersionUID = 1L;
	private String mdsItemId;		// mds_item_id
	private String matBomId;
	private String modelNum;		// model_num
	private String column2;		// column2
	private String column3;		// column3
	private String column4;		// column4
	private String column5;		// column5
	private String classify;		// classify
	private String mdsItemCode;		//产品编号
	private String mdsItemName;	//产品名字
	private String mdsItemspecs;	//规格
	private String mdsItemmodelNum;	//型号
	private String num;		//数量

	private List<MatBomTable> matBomTableList = Lists.newArrayList();		// 子表列表
	
	public MatBomDetail() {
		super();
	}

	public MatBomDetail(String id){
		super(id);
	}

	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}
	
	
	


	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	
	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	
	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	public  MatBomDetail getParent() {
			return parent;
	}
	
	@Override
	public void setParent(MatBomDetail parent) {
		this.parent = parent;
		
	}
	

	
	
	
	public List<MatBomTable> getMatBomTableList() {
		return matBomTableList;
	}

	public void setMatBomTableList(List<MatBomTable> matBomTableList) {
		this.matBomTableList = matBomTableList;
	}

	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";

	}

	public String getMdsItemCode() {
		return mdsItemCode;
	}

	public void setMdsItemCode(String mdsItemCode) {
		this.mdsItemCode = mdsItemCode;
	}

	public String getMdsItemName() {
		return mdsItemName;
	}

	public void setMdsItemName(String mdsItemName) {
		this.mdsItemName = mdsItemName;
	}

	public String getMdsItemspecs() {
		return mdsItemspecs;
	}

	public void setMdsItemspecs(String mdsItemspecs) {
		this.mdsItemspecs = mdsItemspecs;
	}

	public String getMdsItemmodelNum() {
		return mdsItemmodelNum;
	}

	public void setMdsItemmodelNum(String mdsItemmodelNum) {
		this.mdsItemmodelNum = mdsItemmodelNum;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getMatBomId() {
		return matBomId;
	}

	public void setMatBomId(String matBomId) {
		this.matBomId = matBomId;
	}





	
	
}