<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/modules/mds/mdsroute/mdsRouteList.js" %>
<html>
<head>
	<title>工艺管理</title>
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
					jp.post("${ctx}/mdsroute/mdsRoute/save",$('#inputForm').serialize(),function(data){
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
		<form:form id="inputForm" modelAttribute="mdsRoute" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>物料：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/mdsitem/mdsItem/data?itemType=M" id="mdsItemId" name="mdsItemId" value="${mdsRoute.mdsItemId}" labelName="mdsItem.itemCode" labelValue="${mdsRoute.mdsItemCode}"
							 title="选择物料" cssClass="form-control required" fieldLabels="物料号|物料名|物料版本" fieldKeys="itemCode|itemName|itemVersion" searchLabels="物料号|物料名" searchKeys="itemCode|itemName" ></sys:gridselect>
							 
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>产品：</label></td>
					<td class="width-35">
					<sys:gridselect url="${ctx}/mdsitem/mdsItem/data?itemType=A" id="proMdsItemId" name="proMdsItemId" value="${mdsRoute.proMdsItemId}" labelName="mdsItem.itemCode" labelValue="${mdsRoute.proMdsItemCode}"
							 title="选择产品" cssClass="form-control required" fieldLabels="产品号|产品名|产品版本" fieldKeys="itemCode|itemName|itemVersion" searchLabels="产品号|产品名" searchKeys="itemCode|itemName" ></sys:gridselect>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>工艺编号：</label></td>
					<td class="width-35">
						<form:input path="routeNo" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>工艺版本：</label></td>
					<td class="width-35">
						<form:input path="routeEd" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>工艺名称：</label></td>
					<td class="width-35">
						<form:input path="routeDesc" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">工艺状态：</label></td>
					<td class="width-35">
						<form:select path="routeStatus" class="form-control ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('route_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>部门：</label></td>
					<td class="width-35">
						<sys:treeselect id="office" name="office.id" value="${mdsRoute.office.id}" labelName="office.name" labelValue="${mdsRoute.office.name}"
							title="部门" url="/sys/office/treeData?type=2" cssClass="form-control required" allowClear="true" notAllowSelectParent="true"/>
					</td>
					<td class="width-15 active"><label class="pull-right">备注信息：</label></td>
					<td class="width-35">
						<form:textarea path="remarks" htmlEscape="false" rows="4"    class="form-control "/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>