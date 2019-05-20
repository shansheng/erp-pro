/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsbom.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * BOMEntity
 * @author 龚志强
 * @version 2018-07-14
 */
/**
 * @author gzq
 *
 */
public class MdsBom extends DataEntity<MdsBom> {
	
	private static final long serialVersionUID = 1L;
	private String bomType;		// bom类型
	private String mdsItemId;		// 物料id
	private String parentItemId;		// 父物料id
	private String proMdsItemId;		// 产品id
	private String mdsBomInstanceId;		// bom实例id
	private String effectiveType;		// 生效类型：B批次
	private Date effectiveDate;		// 生效时间
	private Date ineffectiveDate;		// 失效时间
	private String effectiveValue;		// 生效值
	private String ineffectiveValue;		// 失效值
	private String optionFlag;		// 是否可选：Y，N
	private String partQuantity;		// 每对数
	private String avgUtlRatio;		// 平均材料下料利用率
	private String rawQty;		// 毛料物料数
	private String mLength;		// 材料长度
	private String mWidth;		// 材料宽度（内径）
	private String mThickness;		// 材料厚度
	private String item01;		// 备用字段01
	private String item02;		// 备用字段02
	private String item03;		// 备用字段03
	private String item04;		// 备用字段04
	private String item05;		// 备用字段05
	
	//关联字段
	private String itemCode;	//子图号code
	private String itemName;	//子图号name
	
	
	public MdsBom() {
		super();
	}

	public MdsBom(String id){
		super(id);
	}

	@ExcelField(title="bom类型", dictType="mds_bom_type", align=2, sort=1)
	public String getBomType() {
		return bomType;
	}

	public void setBomType(String bomType) {
		this.bomType = bomType;
	}
	
	@ExcelField(title="物料id", align=2, sort=2)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="父物料id", align=2, sort=3)
	public String getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(String parentItemId) {
		this.parentItemId = parentItemId;
	}
	
	@ExcelField(title="产品id", align=2, sort=4)
	public String getProMdsItemId() {
		return proMdsItemId;
	}

	public void setProMdsItemId(String proMdsItemId) {
		this.proMdsItemId = proMdsItemId;
	}
	
	@ExcelField(title="bom实例id", align=2, sort=5)
	public String getMdsBomInstanceId() {
		return mdsBomInstanceId;
	}

	public void setMdsBomInstanceId(String mdsBomInstanceId) {
		this.mdsBomInstanceId = mdsBomInstanceId;
	}
	
	@ExcelField(title="生效类型：B批次", align=2, sort=6)
	public String getEffectiveType() {
		return effectiveType;
	}

	public void setEffectiveType(String effectiveType) {
		this.effectiveType = effectiveType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="生效时间", align=2, sort=7)
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="失效时间", align=2, sort=8)
	public Date getIneffectiveDate() {
		return ineffectiveDate;
	}

	public void setIneffectiveDate(Date ineffectiveDate) {
		this.ineffectiveDate = ineffectiveDate;
	}
	
	@ExcelField(title="生效值", align=2, sort=9)
	public String getEffectiveValue() {
		return effectiveValue;
	}

	public void setEffectiveValue(String effectiveValue) {
		this.effectiveValue = effectiveValue;
	}
	
	@ExcelField(title="失效值", align=2, sort=10)
	public String getIneffectiveValue() {
		return ineffectiveValue;
	}

	public void setIneffectiveValue(String ineffectiveValue) {
		this.ineffectiveValue = ineffectiveValue;
	}
	
	@ExcelField(title="是否可选：Y，N", align=2, sort=11)
	public String getOptionFlag() {
		return optionFlag;
	}

	public void setOptionFlag(String optionFlag) {
		this.optionFlag = optionFlag;
	}
	
	@ExcelField(title="每对数", align=2, sort=12)
	public String getPartQuantity() {
		return partQuantity;
	}

	public void setPartQuantity(String partQuantity) {
		this.partQuantity = partQuantity;
	}
	
	@ExcelField(title="平均材料下料利用率", align=2, sort=13)
	public String getAvgUtlRatio() {
		return avgUtlRatio;
	}

	public void setAvgUtlRatio(String avgUtlRatio) {
		this.avgUtlRatio = avgUtlRatio;
	}
	
	@ExcelField(title="毛料物料数", align=2, sort=14)
	public String getRawQty() {
		return rawQty;
	}

	public void setRawQty(String rawQty) {
		this.rawQty = rawQty;
	}
	
	@ExcelField(title="材料长度", align=2, sort=15)
	public String getMLength() {
		return mLength;
	}

	public void setMLength(String mLength) {
		this.mLength = mLength;
	}
	
	@ExcelField(title="材料宽度（内径）", align=2, sort=16)
	public String getMWidth() {
		return mWidth;
	}

	public void setMWidth(String mWidth) {
		this.mWidth = mWidth;
	}
	
	@ExcelField(title="材料厚度", align=2, sort=17)
	public String getMThickness() {
		return mThickness;
	}

	public void setMThickness(String mThickness) {
		this.mThickness = mThickness;
	}
	
	@ExcelField(title="备用字段01", align=2, sort=18)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="备用字段02", align=2, sort=19)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="备用字段03", align=2, sort=20)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="备用字段04", align=2, sort=21)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="备用字段05", align=2, sort=22)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}
	
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}