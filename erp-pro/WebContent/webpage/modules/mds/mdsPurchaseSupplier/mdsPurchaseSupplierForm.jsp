<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>材料定额详情添加页</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
		var validateForm;
		var $table; // 父页面table表格id
		var $topIndex;//弹出窗口的 index
		function doSubmit(table, index){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $table = table;
			  $topIndex = index;
			  jp.loading();
			  $("#inputForm").submit();
			  return true;
		  }

		  return false;
		}

		$(document).ready(function() {
			 
			console.log($('#inputForm').serialize());
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					jp.post("${ctx}/mds/mdsPurchaseSupplier/updateSupplier",$('#inputForm').serialize(),function(data){
						if(data.success){
	                    	$table.bootstrapTable('refresh');
	                    	jp.success(data.msg);
	                    	jp.close($topIndex);//关闭dialog
	                    }else{
            	  			jp.error(data.msg);
	                    }
					})
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		
			
		});
		
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="mdsProcurementPlan" class="form-horizontal">
		<form:hidden path="id"/>
		<input id="mdsQuotaId" name="mdsQuotaId"  type="hidden" value="${mdsQuotaDetail.mdsQuotaId}"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">物料编码：</label></td>
					<td class="width-35">
					<input id="itemCode" name="itemCode" type="text" value="${mdsProcurementPlan.itemCode}"  class="form-control required" readonly="readonly"/>
					</td>
					<td class="width-15 active"><label class="pull-right">物料名称：</label></td>
					<td class="width-35">
						<input id="itemName" name="itemName" type="text" value="${mdsProcurementPlan.itemName}"    class="form-control required" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">标准号：</label></td>
					<td class="width-35">
						<input id="normalField" name="normalField" type="text" value="${mdsProcurementPlan.normalField}"    class="form-control" readonly="readonly"/>
					</td>
					<td class="width-15 active"><label class="pull-right">材质：</label></td>
					<td class="width-35">
						<input id="modelnm" name="modelnm" type="text" value="${mdsProcurementPlan.modelnm}"    class="form-control" readonly="readonly"/>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active"><label class="pull-right">规格/型号：</label></td>
					<td class="width-35">
						<input id="specs" name="specs" type="text" value="${mdsProcurementPlan.specs}"    class="form-control" readonly="readonly"/>
					</td>
					<td class="width-15 active"><label class="pull-right">采购数量：</label></td>
					<td class="width-35">
						<input id="purchaseAmount" name="purchaseAmount" type="text" value="${mdsProcurementPlan.purchaseAmount}"    class="form-control" readonly="readonly"/>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active"><label class="pull-right">需求时间：</label></td>
					<td class="width-35">
						<input id="requiredDate" name="requiredDate" type="text"   class="form-control required" readonly="readonly" value="<fmt:formatDate value="${mdsProcurementPlan.requiredDate}" pattern="yyyy-MM-dd"/>"/>
					</td>
					<td class="width-15 active"><label class="pull-right">采购方式：</label></td>
					<td class="width-35">
						<input id="procurementWay" name="procurementWay" type="text" value="${mdsProcurementPlan.procurementWay}"  class="form-control required" readonly="readonly"/>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active"><label class="pull-right">来源类型：</label></td>
					<td class="width-35">
						<input id="sourceType" name="sourceType" type="text" value="${mdsProcurementPlan.sourceType}"  class="form-control required" readonly="readonly"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>供应商选择：</label></td>
					<td class="width-35">
					   	<sys:supplierSelectForm url="${ctx}/supplier/supplier/data"
										id="supplier" name="supplierId" value="${mdsProcurementPlan.supplierId}"
										labelName="supplierName" labelValue="${mdsProcurementPlan.supplierName}"
										title="选择供应商" cssClass="form-control required"
										fieldLabels="供应商编码|供应商名称|简称|行业|联系人|电话"
										fieldKeys="supplierNo|suName|shortName|industry|linkman|phone"
										searchLabels="供应商编码|供应商名称" searchKeys="supplierNo|suName"></sys:supplierSelectForm>
					</td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>