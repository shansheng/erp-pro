<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理</title>
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
					jp.post("${ctx}/supplier/supplier/save",$('#inputForm').serialize(),function(data){
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
		
		$("#valid").val('1');
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="supplier" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>供应商编码：</label></td>
					<td class="width-35">
						<form:input path="supplierNo" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>名称：</label></td>
					<td class="width-35">
						<form:input path="suName" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>简称：</label></td>
					<td class="width-35">
						<form:input path="shortName" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>联系人：</label></td>
					<td class="width-35">
						<form:input path="linkman" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>电话：</label></td>
					<td class="width-35">
						<form:input path="phone" 	onkeyup="value=value.replace(/[^\d.]/g,'')" htmlEscape="false" maxlength="11"  minlength="7"   class="form-control required isTel"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>类型：</label></td>
					<td class="width-35">
						<form:select path="suClass" class="form-control required">
							<form:option value="" label="---请选择---"/>
							<form:options items="${fns:getDictList('su_class')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>是否有效：</label></td>
					<td class="width-35">
						<form:select path="valid" class="form-control required">
							
							<form:options items="${fns:getDictList('valid')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>行业：</label></td>
					<td class="width-35">
						<form:input path="industry" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>采购负责人：</label></td>
					<td class="width-35">
						<sys:userselect id="agent" name="agent" value="${supplier.agent}" labelName="" labelValue="${supplier.agentName}"
							    cssClass="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>付款条件：</label></td>
					<td class="width-35">
						<form:select path="payment" class="form-control required">
							<form:option value="" label="---请选择---"/>
							<form:options items="${fns:getDictList('payment')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>银行名称：</label></td>
					<td class="width-35">
						<form:input path="bankName" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>银行账户：</label></td>
					<td class="width-35">
						<form:input path="bankLog" htmlEscape="false" 	onkeyup="value=value.replace(/[^\d.]/g,'')" maxlength="19"  minlength="0"   class="form-control required number"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>地区：</label></td>
					<td class="width-35">
					<div class=" input-group" style=" width: 100%;">
						  <form:input path="region" htmlEscape="false"  class="required" data-toggle="city-picker" style="height: 34px;"/>
					</div>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>邮编：</label></td>
					<td class="width-35">
						<form:input path="postcode" 	onkeyup="value=value.replace(/[^\d.]/g,'')" htmlEscape="false" maxlength="6"  minlength="0"   class="form-control required isZipCode"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">地址：</label></td>
					<td class="width-35" colspan="3">
						
						<textarea style="resize:none; margin: 0px; width: 629px; height: 50px;" name="address">${supplier.address }</textarea>
					</td>
					
				</tr>
				<tr>
				<td class="width-15 active"><label class="pull-right">描述：</label></td>
					<td class="width-35" colspan="3">
					<textarea style="resize:none; margin: 0px; width: 629px; height: 104px;" name="remake">${supplier.remake }</textarea>
						
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>