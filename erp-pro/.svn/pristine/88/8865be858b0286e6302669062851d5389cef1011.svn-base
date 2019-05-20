<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工作计划管理</title>
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
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					jp.post("${ctx}/workorderson/workOrderSon/save",$('#inputForm').serialize(),function(data){
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
		<form:form id="inputForm" modelAttribute="arrival" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
		   	<tr>

					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>物料代码：</label></td>
					<td class="width-35"><form:input path="itemCode"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>物料名称：</label></td>
					<td class="width-35"><form:input path="itemName"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
				</tr>
					<tr>

					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>标准号：</label></td>
					<td class="width-35"><form:input path="normalField"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>型号：</label></td>
					<td class="width-35"><form:input path="modelnm"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
				</tr>
					
					<tr>

					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>规格：</label></td>
					<td class="width-35"><form:input path="specs"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>供应商名称：</label></td>
					<td class="width-35"><form:input path="supplierName"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
				</tr>
				<tr>

					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>供应商名称：</label></td>
					<td class="width-35"><form:input path="supplierName"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>到货数量：</label></td>
					<td class="width-35"><form:input path="arrivalNum"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
				</tr>
				<tr>

					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>到货确认日期：</label></td>
					<td class="width-35"><form:input path="createDate"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>到货确认者：</label></td>
					<td class="width-35"><form:input path="createBy.name"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
				</tr>
				<tr>
<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>合格数：</label></td>
					<td class="width-35"><form:input path="qualified"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>不合格数：</label></td>
					<td class="width-35"><form:input path="disqualification"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
					
				</tr>
				<tr>
<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>检验人：</label></td>
					<td class="width-35"><form:input path="updateBy.name"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>检验时间：</label></td>
					<td class="width-35"><form:input path="updateDate"
							htmlEscape="false" class="form-control required" disabled="true"/></td>
					
				</tr>
				
		 	</tbody>
		</table>
	</form:form>
</body>
</html>