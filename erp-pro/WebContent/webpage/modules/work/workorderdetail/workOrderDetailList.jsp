<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工作计划管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="workOrderDetailList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">
				<a class="panelButton" href="${ctx}/workorderson/workOrderSon/list?workOrderId=${workOrderId }"><i class="ti-angle-left"></i> 返回</a>
			</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
	<input type="hidden"  id="workOrderSonId" name="workOrderSonId" value="${workOrderSonId }">
		<input type="hidden"  id="workOrderId" name="workOrderId" value="${workOrderId }">
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div id="toolbar">
		
			<shiro:hasPermission name="workorderdetail:workOrderDetail:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 下达
	        	</button>
			</shiro:hasPermission>
			
	        	
		    </div>
		
	<!-- 表格 -->
	<table id="workOrderDetailTable"   data-toolbar="#toolbar"></table>

   
	</div>
	</div>
	</div>
</body>
</html>