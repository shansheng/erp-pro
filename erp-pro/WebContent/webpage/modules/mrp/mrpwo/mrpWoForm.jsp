<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>任务管理管理</title>
<meta name="decorator" content="ani" />
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										jp.loading();
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
				$('#planStartDate').datetimepicker({
					format : "YYYY-MM-DD"
				});
				$('#planEndDate').datetimepicker({
					format : "YYYY-MM-DD"
				});

			});
</script>
</head>
<body class="bg-white">
	<form:form id="inputForm" modelAttribute="mrpWo"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<table class="table table-bordered">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>任务编号：</label></td>
					<td class="width-35"><form:input path="taskNo"
							htmlEscape="false" class="form-control required" /></td>
					<td class="width-15 active"><label class="pull-right">批次号：</label></td>
					<td class="width-35"><form:input path="batchNo"
							htmlEscape="false" class="form-control " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>产品编码：</label></td>
					<td class="width-35"><sys:gridselect
							url="${ctx}/mdsitem/mdsItem/data?itemType=G" id="proMdsItemId"
							name="proMdsItemId" value="${mrpWo.proMdsItemId}"
							labelName="mdsItem.itemCode" labelValue="${mrpWo.proMdsItemCode}"
							title="选择产品" cssClass="form-control required"
							fieldLabels="产品号|产品名|产品版本"
							fieldKeys="itemCode|itemName|itemVersion" searchLabels="产品号|产品名"
							searchKeys="itemCode|itemName"></sys:gridselect></td>
					<td class="width-15 active"><label class="pull-right">产品名称：</label></td>
					<td class="width-35"><form:input path="proMdsItemName"
							htmlEscape="false" class="form-control " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>物料编码：</label></td>
					<td class="width-35"><sys:gridselect
							url="${ctx}/mdsitem/mdsItem/data?itemType=M" id="mdsItemId"
							name="mdsItemId" value="${mrpWo.mdsItemId}"
							labelName="mdsItem.itemCode" labelValue="${mrpWo.mdsItemCode}"
							title="选择物料" cssClass="form-control required"
							fieldLabels="物料号|物料名|物料版本"
							fieldKeys="itemCode|itemName|itemVersion" searchLabels="物料号|物料名"
							searchKeys="itemCode|itemName"></sys:gridselect></td>
					<td class="width-15 active"><label class="pull-right">物料名称：</label></td>
					<td class="width-35"><form:input path="MdsItemName"
							htmlEscape="false" class="form-control " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>需求数：</label></td>
					<td class="width-35"><form:input path="reqQty"
							htmlEscape="false" class="form-control " /></td>
					<td class="width-15 active"><label class="pull-right">主制部门：</label></td>
					<td class="width-35"><sys:treeselect id="mainOfficeId"
							name="mainOfficeId" value="${mrpWo.mainOfficeId}"
							labelName="office.name" labelValue="${mrpWo.mainOfficeName}"
							title="部门" url="/sys/office/treeData?type=2"
							cssClass="form-control required" allowClear="true"
							notAllowSelectParent="true" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">计划开始时间：</label></td>
					<td class='input-group form_datetime' id='planStartDate'><input
						type='text' name="planStartDate" class="form-control required"
						value="<fmt:formatDate value="${mrpWo.planStartDate}" pattern="yyyy-MM-dd"/>" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
					</span></td>
					<td class="width-15 active"><label class="pull-right">计划结束时间：</label></td>
					<td class='input-group form_datetime' id='planEndDate'><input
						type='text' name="planEndDate" class="form-control required"
						value="<fmt:formatDate value="${mrpWo.planEndDate}" pattern="yyyy-MM-dd"/>" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
					</span></td>
				</tr>

			</tbody>
		</table>
	</form:form>
</body>
</html>