<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加库房管理</title>
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
					jp.post("${ctx}/wmsinventory/wmsInventory/save",$('#inputForm').serialize(),function(data){
						if(data.success){
	                    	$table.bootstrapTable('refresh');
	                    	jp.success(data.msg);
	                    	jp.close($topIndex);//关闭dialog

	                    }else{
            	  			jp.error(data.msg);
	                    }
						function checkinventoryCode(){
							var inventoryCodes="${inventoryCodes}";
							var c=inventoryCodes.substring(1,inventoryCodes.length-1);
							var codearr=c.split(',');
							var code=document.getElementById("inventoryCode").value;
							for(var i=0;i<codearr.length;i++){
								var cc=codearr[i].replace(/(^\s*)|(\s*$)/g, "");
								if(code!=""&& code==cc){
									alert("库房代码已存在");
									break;
								}
							}
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
		<form:form id="inputForm" modelAttribute="wmsInventory" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>库房代码：</label></td>
					<td class="width-35">
						<form:input path="inventoryCode" htmlEscape="false"    class="form-control required"  onblur="checkinventoryCode()"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>库房名称：</label></td>
					<td class="width-35">
						<form:input path="inventoryName" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属部门：</label></td>
					<td class="width-35">
						<sys:treeselect id="sysOfficeId"
							name="sysOfficeId" value="${wmsInventory.sysOfficeId}"
							labelName="sysOfficeName"
							labelValue="${wmsInventory.sysOfficeName}" title="所属部门"
							url="/sys/office/treeData?type=2" cssClass="form-control"
							notAllowSelectParent="true" /></td>
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