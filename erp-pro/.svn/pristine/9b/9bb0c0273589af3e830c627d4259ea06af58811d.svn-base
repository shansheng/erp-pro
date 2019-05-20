<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>客户管理管理</title>
<meta name="decorator" content="ani" />
<script type="text/javascript">
	var validateForm;
	var $table; // 父页面table表格id
	var $topIndex;//弹出窗口的 index
	function doSubmit(table, index) {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		if (validateForm.form()) {
			$table = table;
			$topIndex = index;
			jp.loading();
			$("#inputForm").submit();
			return true;
		}

		return false;
	}

	$(document)
			.ready(
					function() {
						validateForm = $("#inputForm")
								.validate(
										{
											submitHandler : function(form) {
												jp
														.post(
																"${ctx}/client/client/save",
																$('#inputForm')
																		.serialize(),
																function(data) {
																	if (data.success) {
																		$table
																				.bootstrapTable('refresh');
																		jp
																				.success(data.msg);
																		jp
																				.close($topIndex);//关闭dialog

																	} else {
																		jp
																				.error(data.msg);
																	}
																})
											},
											errorContainer : "#messageBox",
											errorPlacement : function(error,
													element) {
												$("#messageBox").text(
														"输入有误，请先更正。");
												if (element.is(":checkbox")
														|| element.is(":radio")
														|| element
																.parent()
																.is(
																		".input-append")) {
													error.appendTo(element
															.parent().parent());
												} else {
													error.insertAfter(element);
												}
											}
										});

					});
	
	$("#valid").val('1');
</script>
</head>
<body class="bg-white">
	<form:form id="inputForm" modelAttribute="client"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<table class="table table-bordered">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>客户编码：</label></td>
					<td class="width-35"><form:input path="clientNo"
							htmlEscape="false" class="form-control required" /></td>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>客户名称：</label></td>
					<td class="width-35"><form:input path="clientName"
							htmlEscape="false" class="form-control required" /></td>
		
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><!-- <font
							color="red">*</font> -->简称：</label></td>
					<td class="width-35"><form:input path="shortTitle"
							htmlEscape="false" class="form-control" /></td>
					<td class="width-15 active"><label class="pull-right"><!-- <font
							color="red">*</font> -->联系人：</label></td>
					<td class="width-35"><form:input path="clientManager"
							htmlEscape="false" class="form-control" /></td>

				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><!-- <font
							color="red">*</font> -->电话：</label></td>
					<td class="width-35"><form:input path="phone"
							htmlEscape="false" class="form-control" maxlength="11"  minlength="0"	onkeyup="value=value.replace(/[^\d.]/g,'')"/></td>
				<td class="width-15 active"><label class="pull-right"><!-- <font
							color="red">*</font> -->客户类型：</label></td>
					<td class="width-35"><form:select path="clientClass"
							class="form-control ">
							<form:option value="" label="---请选择---" />
							<form:options items="${fns:getDictList('client_class')}" 
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select></td>
				</tr>
				<tr><td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>是否有效：</label></td>
					<td class="width-35"><form:select path="valid"
							class="form-control required" >
							
							<form:options items="${fns:getDictList('valid')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select></td>

					<td class="width-15 active"><label class="pull-right">邮编：</label></td>
					<td class="width-35"><form:input path="postcode" maxlength="6"  minlength="0"	onkeyup="value=value.replace(/[^\d.]/g,'')"
							htmlEscape="false" class="form-control " /></td>
				</tr>
		
				<tr>
					
					<td class="width-15 active"><label class="pull-right"><!-- <font
							color="red">*</font> -->地区：</label></td>
					<td class="width-35">
						<div class=" input-group" style="width: 100%;">
							<form:input path="region" htmlEscape="false"
								data-toggle="city-picker" style="height: 34px;" />
						</div>
					</td>
<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>地址：</label></td>
					<td class="width-35"><form:input path="address"
							htmlEscape="false" class="form-control required" /></td>

				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">描述：</label></td>
					<td class="width-35" colspan="3">
					<textarea  name="remake" style="resize:none; margin: 0px; width: 646px; height: 97px;" >${client.remake }</textarea>
					</td>
					

				</tr>

			</tbody>
		</table>
	</form:form>
</body>
</html>