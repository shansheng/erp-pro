<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>任务管理管理</title>
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
					jp.post("${ctx}/mrpwofind/mrpWoFeeding/save",$('#inputForm').serialize(),function(data){
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
		<form:form id="inputForm" modelAttribute="mrpWoFeeding" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>工作订单号：</label></td>
					<td class="width-35">
						<form:input path="woNo" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">年月信息：</label></td>
					<td class="width-35">
						<form:input path="woDateStr" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">订单状态：</label></td>
					<td class="width-35">
						<form:input path="woStatus" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">需求数量：</label></td>
					<td class="width-35">
						<form:input path="reqQty" htmlEscape="false"    class="form-control "/>
					</td>
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
				<tr>
					<td class="width-15 active"><label class="pull-right">任务编号：</label></td>
					<td class="width-35">
						<form:input path="taskNo" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>