<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<link href="${ctxStatic}/plugin/bootstrapTree/bootstrap-treeview.css" rel="stylesheet" type="text/css"/>
	<script src="${ctxStatic}/plugin/bootstrapTree/bootstrap-treeview.js" type="text/javascript"></script>
	<%@include file="mdsQuotaDetailView.js" %>
</head>
<body>
<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
			<h3 class="panel-title"> 
				<a class="panelButton" href="${ctx}/WorkOrderSynthesize/WorkOrderSynthesize/list?clientId=+${clientId}"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
	<div class="panel-body">
	
	<input id="mdsQuotaId" name="mdsQuotaId" type="hidden" value="${mdsQuotaId}" />
	
	
		<input id="view" name="view" type="hidden" value="1" />
		<div class="row">
		<div class="col-sm-3 col-md-2" >
			<div id="mdsQuotaDetailTree"></div>
		</div>
		<div  class="col-sm-9 col-md-10 animated fadeInRight">
			<sys:longMessage content="${message}"/>
	

	

		
	<!-- 表格 -->
	<table id="mdsQuotaDetailTable"   data-toolbar="#toolbar"></table>

  
	</div>
	</div>
	</div>
	</div>		
</div>
</body>
</html>