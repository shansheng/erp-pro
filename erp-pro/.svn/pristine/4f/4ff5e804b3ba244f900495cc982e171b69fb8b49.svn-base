<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>批量采购计划管理</title>
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
		
		jQuery.validator.addMethod("positiveinteger", function(value, element) {
			var aint=parseInt(value);
			return aint>0&& (aint+"")==value;
			},"请输入正整数");
		
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				rules:{
					demandNumber:{positiveinteger:true},
					purchaseNumber:{positiveinteger:true}
				},
				/* message:{
					demandNumber:{positiveinteger:"请输入正整数"}
				}, */
				submitHandler: function(form){
					
					jp.post("${ctx}/mds/purchase/mdsPurchaseList/save",$('#inputForm').serialize(),function(data){
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
	        $('#demandDate').datetimepicker({
				 format: "YYYY-MM-DD"
		    });
		});
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="mdsPurchaseList" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
		       <%-- 	<tr>
					<td class="width-15 active"><label class="pull-right">临时计划申请单号：</label></td>
					<td class="width-35">
						<form:input path="temNumber" htmlEscape="false"    class="form-control " readOnly="readOnly"/>
					</td>
					<td class="width-15 active"><label class="pull-right">项目编号：</label></td>
					<td class="width-35">
						<form:input path="projectNumber" htmlEscape="false"    class="form-control " readOnly="readOnly"/>
					</td>
					
		  		</tr>
		        --%>
				<tr>
					<td class="width-15 active"><label class="pull-right">物料编码：</label></td>
						<td class="width-35">
						<sys:itemSelectForDetailForm url="${ctx}/mdsitem/mdsItem/data?itemType=M"
										id="mdsItem" name="mdsItemId" value="${mdsPurchaseList.mdsItemId}"
										labelName="itemCode" labelValue="${mdsPurchaseList.itemCode}"
										title="选择物料" cssClass="form-control required"
										fieldLabels="物料号|物料名|标准号|型号|规格|单位"
										fieldKeys="itemCode|itemName|normalField|modelNum|specs|mdsUnitId"
										searchLabels="物料号|物料名" searchKeys="itemCode|itemName" ></sys:itemSelectForDetailForm>
					</td>
					<td class="width-15 active"><label class="pull-right">物料名称：</label></td>
					<td class="width-35">
						<form:input path="itemName" htmlEscape="false"    class="form-control " readOnly="readOnly"/>
					</td>
				</tr>
				<tr>
				  <td class="width-15 active"><label class="pull-right">标准号：</label></td>
					<td class="width-35">
						<form:input path="normalField" htmlEscape="false"    class="form-control " readOnly="readOnly"/>
					</td>
					<td class="width-15 active"><label class="pull-right">材质：</label></td>
					<td class="width-35">
						<form:input path="modelNum" htmlEscape="false"    class="form-control " readOnly="readOnly"/>
					</td>
				
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">规格/型号：</label></td>
					<td class="width-35">
						<form:input path="specs" htmlEscape="false"    class="form-control " readOnly="readOnly"/>
					</td>
				  	<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>需求数量：</label></td>
					<td class="width-35">
						<form:input path="demandNumber" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
			    <tr>
			    <td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>采购数量：</label></td>
					<td class="width-35">
						<form:input path="purchaseNumber" htmlEscape="false"    class="form-control required"/>
					</td>
			       <td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>需求时间：</label></td>
			     <%-- <td class="width-35">
					<input type="date" name="demandDate" id="demandDate"  class="form-control required"  value="<fmt:formatDate value="${mdsPurchaseList.demandDate}" pattern="yyyy-MM-dd"/>"> 
				 </td> --%>
				    <td class="width-35"> 
						<p class="input-group">
							 <div class='input-group form_datetime' id='demandDate'> 
			                    <input type='text'  name="demandDate" class="form-control required"  value="<fmt:formatDate value="${mdsPurchaseList.demandDate}" pattern="yyyy-MM-dd"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			               </div> 
			            </p>
					 </td>  
			    </tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>