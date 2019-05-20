<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>物料管理</title>
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
						var obj=document.getElementById("outSourceType");
						var outSourceType=$("#outSourceType").val();
						var sourceType=$("#sourceType").val();
						if(outSourceType!=''||sourceType==1){
						document.getElementById("outSourceType").disabled=false;
						obj.className +=' required';
						}
						validateForm = $("#inputForm")
								.validate({submitHandler : function(form) {
												jp
														.post(
																"${ctx}/mdsitem/mdsItem/save",
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
	function checkType(){
		var sourceType=$("#sourceType").val();
		var obj=document.getElementById("outSourceType");  
		if(sourceType==0){
			document.getElementById("outSourceType").disabled=true;
			$("#outSourceType").val('');
			document.getElementById("outSourceType").className = "form-control";

		}
		if(sourceType==1){
			document.getElementById("outSourceType").disabled=false;  
			obj.className +=' required';
			
			
		}
	}
	
	
</script>
</head>
<body class="bg-white">
	<form:form id="inputForm" modelAttribute="mdsItem"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<table class="table table-bordered">
			<tbody>
				<tr>

					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>物料代码：</label></td>
					<td class="width-35"><form:input path="itemCode"
							htmlEscape="false" class="form-control required"/></td>
					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>物料名称：</label></td>
					<td class="width-35"><form:input path="itemName"
							htmlEscape="false" class="form-control required"/></td>
				</tr>
				<tr>

					<%-- <td class="width-15 active"><label class="pull-right">物料版本：</label></td>
					<td class="width-35"><form:input path="itemVersion"
							htmlEscape="false" class="form-control" /></td> --%>
					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>物料类型：</label></td>
					<td class="width-35"><form:select path="itemType"
							class="form-control required">
							<form:options items="${fns:getDictList('item_type')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select></td>
						<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>来源类型：</label></td>
					<td class="width-35"><form:select path="sourceType" onchange="checkType()" id="sourceType"
							class="form-control required">
							<form:options items="${fns:getDictList('source_type')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select></td>
				</tr>
				<tr>

					
					<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>外购类型：</label></td>
					<td class="width-35">
					<form:select path="outSourceType" id="outSourceType" disabled="true"
							class="form-control">
							<form:option value="" label="---请选择---"></form:option>
							<form:options items="${fns:getDictList('out_source_type')}"  
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select></td>
							<td class="width-15 active"><label class="pull-right">备注信息：</label></td>
					<td class="width-70"><form:textarea path="remarks" 
							htmlEscape="false" rows="4" class="form-control" /></td>
				</tr>
			<%-- 	<tr>
				 	<td class="width-15 active"><label class="pull-right">设计部门名称：</label></td>
					<td class="width-35"><sys:treeselect id="office"
							name="office.id" value="${mdsItem.office.id}"
							labelName="office.name" labelValue="${mdsItem.office.name}" 
							title="机构" url="/sys/office/treeData" allowClear="true"
							cssClass="form-control " /></td> 

				
				</tr> --%>
			</tbody>
		</table>
		<div class="tabs-container">
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1"
					aria-expanded="true">物料辅助特征</a></li>
				<!-- <li class=""><a data-toggle="tab" href="#tab-2"
					aria-expanded="false">计划策略</a></li>
				<li class=""><a data-toggle="tab" href="#tab-3"
					aria-expanded="false">计划期量</a></li> -->
			</ul>
			<div class="tab-content">
				<div id="tab-1" class="tab-pane active">
					<table id="contentTable"
						class="table table-striped table-bordered table-condensed">
						<tbody>
							<tr>
								<td class="width-15 active"><label class="pull-right">规格/型号</label></td>
								<td class="width-35"><form:input path="specs"
										htmlEscape="false" class="form-control" /></td>
											<td class="width-15 active"><label class="pull-right">材质</label></td>
								<td class="width-35"><form:input path="modelNum" htmlEscape="false"
										class="form-control" /></td>
							
							</tr>
							<tr>
								<td class="width-15 active"><label class="pull-right">技术标准</label></td>
								<td class="width-35"><form:input path="techCondition"
										htmlEscape="false" class="form-control" /></td>
								<td class="width-15 active"><label class="pull-right"><font
										color="red">*</font>计量单位</label></td>
								<td class="width-35"><form:select path="mdsUnitId"
										class="form-control required">
										<form:option value="" label="---请选择---" />
										<form:options items="${fns:getSqlList('SELECT DISTINCT mesewo.unit AS value ,mesewo.unit_text as label,mesewo.sort from prickle mesewo order by mesewo.sort ')}"
											itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select></td>
							</tr>
							<tr>
								<%-- <td class="width-15 active"><label class="pull-right">是否使用小号(无)</label></td>
								<td class="width-35"><form:input path="" htmlEscape="false"
										class="form-control" /></td> --%>
								<td class="width-15 active"><label class="pull-right">标准</label></td>
								<td class="width-35"><form:input path="normalField" htmlEscape="false"
										class="form-control" /></td>
								<td class="width-15 active"><label class="pull-right">牌号</label></td>
								<td class="width-35"><form:input path="marque"
										htmlEscape="false" class="form-control" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<%-- <div id="tab-2" class="tab-pane">
					<table id="contentTable"
						class="table table-striped table-bordered table-condensed">
						<tbody>
							<tr>
								<td class="width-15 active"><label class="pull-right">关键件</label></td>
								<td class="width-35">
								<form:select path="keyFlag" class="form-control ">
									<form:options items="${fns:getDictList('key_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select>
								</td>
								<td class="width-15 active"><label class="pull-right">批次管理方式</label></td>
								 <td class="width-35"><form:input path="batchMode"
										htmlEscape="false" class="form-control" />
								<form:select path="batchMode" class="form-control ">
									<form:options items="${fns:getDictList('batch_Mode_state')}" itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select>
								</td>
							</tr>
						</tbody>
						<tbody id="testDataChild2List">
						</tbody>
					</table>
				</div>
				<div id="tab-3" class="tab-pane">
					<table id="contentTable"
						class="table table-striped table-bordered table-condensed">
						<tbody>
							<tr>
								<td class="width-15 active"><label class="pull-right">计划不变提前期</label></td>
								<td class="width-35"><form:input path="planCLt"
										htmlEscape="false" class="form-control" /></td>
								<td class="width-15 active"><label class="pull-right">计划可变提前期</label></td>
								<td class="width-35"><form:input path="planVLt"
										htmlEscape="false" class="form-control" /></td>
							</tr>
							<tr>
								<td class="width-15 active"><label class="pull-right">安全库存</label></td>
								<td class="width-35"><form:input path="safetyStock"
										htmlEscape="false" class="form-control" /></td>
								<td class="width-15 active"><label class="pull-right">平均批量（无）</label></td>
								<td class="width-35"><form:input path="" htmlEscape="false"
										class="form-control" /></td>
							</tr>
							<tr>
								<td class="width-15 active"><label class="pull-right">投入产出比（无）</label></td>
								<td class="width-35"><form:input path="" htmlEscape="false"
										class="form-control" /></td>
							</tr>
						</tbody>
					</table>
				</div> --%>
			</div>
		</div>
	</form:form>
</body>
</html>