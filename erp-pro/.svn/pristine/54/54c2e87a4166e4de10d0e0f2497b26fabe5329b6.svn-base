<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>库存结转管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					jp.loading();
					form.submit();
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
<body>
<div class="wrapper wrapper-content">				
<div class="row">
	<div class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title"> 
				<a class="panelButton" href="${ctx}/wmsinvbalancebill/wmsInvBalanceBill"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="wmsInvBalanceBill" action="${ctx}/wmsinvbalancebill/wmsInvBalanceBill/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="form-group">
			<label class="col-sm-2 control-label"><font color="red">*</font>会计年度：</label>
			<div class="col-sm-10">
				<form:select path="year"  class="form-control required">
					<form:options items="${fns:getSqlList('SELECT DISTINCT wfp.year AS value ,wfp.year as label from wms_fiscal_period wfp')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"><font color="red">*</font>期段号：</label>
			<div class="col-sm-10">
				<form:select path="period"  class="form-control required">
					<form:options items="${fns:getSqlList('SELECT DISTINCT wfp.period AS value ,wfp.period as label from wms_fiscal_period wfp ORDER BY wfp.period')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
			<div class="form-group">
			<label class="col-sm-2 control-label"><font color="red">*</font>库房：</label>
			<div class="col-sm-10">
				<form:select path="wmsInventoryId"  class="form-control required">
					<form:options items="${fns:getSqlList('SELECT wi.id AS value ,wi.inventory_name as label from wms_inventory wi')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<c:if test="${fns:hasPermission('wmsinvbalancebill:wmsInvBalanceBill:edit') || isAdd}">
				<div class="col-lg-3"></div>
		        <div class="col-lg-6">
		             <div class="form-group text-center">
		                 <div>
		                     <button class="btn btn-primary btn-block btn-lg btn-parsley" data-loading-text="正在提交...">提 交</button>
		                 </div>
		             </div>
		        </div>
		</c:if>
		</form:form>
		</div>				
	</div>
	</div>
</div>
</div>
</body>
</html>