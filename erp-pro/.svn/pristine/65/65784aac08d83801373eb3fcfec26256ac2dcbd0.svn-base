<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>

<html>
<head>
	<title>工序管理</title>
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
					jp.post("${ctx}/mdsoperationseq/mdsOperationSeq/save",$('#inputForm').serialize(),function(data){
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
		<form:form id="inputForm" modelAttribute="mdsOperationSeq" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="innerOrder" value="0"/>
		<input type="hidden" name="mdsRouteId" id="mdsRouteId" value="${mdsOperationSeq.mdsRouteId }">
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>工序号：</label></td>
					<td class="width-35">
						<form:input path="operationSeqNo" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>工序描述：</label></td>
					<td class="width-35">
						<form:input path="operationSeqDesc" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>下道工序号：</label></td>
					<td class="width-35">
						<form:input path="operationSeqNoNext" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>加工部门：</label></td>
					<td class="width-35">
						<sys:treeselect id="sysOfficeId" name="sysOfficeId" value="${mdsOperationSeq.sysOfficeId}" labelName="sysOfficeId.name" labelValue="${mdsOperationSeq.sysOfficeName}"
							title="部门" url="/sys/office/treeData" cssClass="form-control required" allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">加工中心：</label></td>
							<td class="width-35">
						<sys:gridselect url="${ctx}/mesworkshift/mesWorkshift/data" id="mdsWorkcenterId" name="mdsWorkcenterId" value="${mdsOperationSeq.mdsWorkcenterId}" labelName="mesWorkshift.workcenterName" labelValue="${mdsOperationSeq.mdsWorkcenterName}"
							 title="选择班组" cssClass="form-control required" fieldLabels="班组代码|班组名称|所属部门" fieldKeys="workcenterCode|workcenterName|sysOfficeName" searchLabels="班组代码|班组名称" searchKeys="workcenterCode|workcenterName" ></sys:gridselect>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>