<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>问题转移管理</title>
	<meta name="decorator" content="ani"/>
<!-- 	<script type="text/javascript">
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
					jp.post(",$('#inputForm').serialize(),function(data){
						if(data.success){
	                    	jp.success(data.msg);
	                    	jp.close($topIndex);//关闭dialog  
	                    }else{
            	  			jp.error(data.msg);
	                    }
					})
					$.ajax({
					 		async: true,
			 	       	 	url: "${ctx}/sysreply/sysReply/list?sysQueId="+$("#questionId").val()
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
	</script> -->
</head>
<body class="bg-white">

		<form action="${ctx}/sysquetransfer/sysQueTransfer/save?sysQueId=${sysQueTransfer.sysQueId}">
		<input type="hidden" id="sysQueId" name="sysQueId" value="${sysQueTransfer.sysQueId }">
		<sys:message content="${message}"/>	
			<table class="table table-bordered">
		   <tbody>
				<tr>

				<td class="width-15 active"><label class="pull-right"><font color="red">*</font>被转移人：</label></td>
					<td class="width-35">
						<sys:userselect id="proSysUserId" name="proSysUserId" value="${sysQueTransfer.proSysUserId}" labelName="" labelValue="${sysQueTransfer.proSysUserId}"
							    cssClass="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>转移原因：</label></td>
					<td class="width-35">
						<textarea rows="20" cols="10" name="transferReason" style="margin: 0px; width: 400px; height: 200px;"></textarea>
					</td>
				</tr>
		  		<tr>
		  		<td colspan="4"><button type="submit">确定</button> </td>
		  		</tr>
		 	</tbody>
		</table>
		</form>
	
</body>
</html>