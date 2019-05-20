<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>保存“期段”成功管理</title>
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
			 $('#startDate').datetimepicker({
				 format: "YYYY-MM-DD"
		    });
	        $('#endDate').datetimepicker({
				 format: "YYYY-MM-DD"
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
				<a class="panelButton" href="${ctx}/wmsfiscalperiod/wmsFiscalPeriod"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="wmsFiscalPeriod" action="${ctx}/wmsfiscalperiod/wmsFiscalPeriod/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
				<div class="form-group">
					<label class="col-sm-2 control-label">会计年度：</label>
					<div class="col-sm-10">
						<form:input path="year" htmlEscape="false" class="form-control required" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">期段号：</label>
					<div class="col-sm-10">
						<form:input path="period" htmlEscape="false" class="form-control required" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">开始日期：</label>
					<div class="col-sm-10">
						<p class="input-group">
							<div class='input-group form_datetime' id='startDate'>
			                    <input type='text'  name="startDate" class="form-control required"  value="<fmt:formatDate value="${wmsFiscalPeriod.startDate}" pattern="yyyy-MM-dd"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">结束日期：</label>
					<div class="col-sm-10">
						<p class="input-group">
							<div class='input-group form_datetime' id='endDate'>
			                    <input type='text'  name="endDate" class="form-control required"  value="<fmt:formatDate value="${wmsFiscalPeriod.endDate}" pattern="yyyy-MM-dd"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</div>
				</div>
		<c:if test="${fns:hasPermission('wmsfiscalperiod:wmsFiscalPeriod:edit') || isAdd}">
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