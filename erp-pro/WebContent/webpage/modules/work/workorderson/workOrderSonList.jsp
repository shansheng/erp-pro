<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工作计划管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="workOrderSonList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title"> 
		<input type="hidden" id="workOrderId" name="workOrderId" value="${workOrderId }">
				<a class="panelButton" href="${ctx}/workorder/workOrder/list"><i class="ti-angle-left"></i> 返回</a>
			</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	

	
	<!-- 工具栏 -->
	<div id="toolbar">
			
			<shiro:hasPermission name="workorderson:workOrderSon:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 添加计划
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="workorderson:workOrderSon:view">
				<button id="view" class="btn btn-danger" disabled onclick="view()">
	            	<i class="glyphicon glyphicon-remove"></i> 查看计划
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="workorderson:workOrderSon:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
				<div id="importBox" class="hide">
						<form id="importForm" action="${ctx}/workorderson/workOrderSon/import" method="post" enctype="multipart/form-data"
							 style="padding-left:20px;text-align:center;" ><br/>
							<input id="uploadFile" name="file" type="file" style="width:330px"/>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br/>　　
							
							
						</form>
				</div>
			</shiro:hasPermission>
	        	<a class="accordion-toggle btn btn-default" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					<i class="fa fa-search"></i> 检索
				</a>
		    </div>
		
	<!-- 表格 -->
	<table id="workOrderSonTable"   data-toolbar="#toolbar"></table>

   
	</div>
	</div>
	</div>
</body>
</html>