<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<jsp:useBean id="WorkOrderSynthesize" class="com.pt.modules.work.workordersynthesize.entity.WorkOrderSynthesize" scope="request" ></jsp:useBean>
<html>
<head>
	<title>工作计划管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="workOrderSynthesizeList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title"> 
		订单综合查询列表
			</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	<input type="hidden" id="mdsItemId" name="mdsItemId" value="${mdsItemId }">
	<input type="hidden" id="sysMarketId" name="sysMarketId" value="${sysMarketId }">
	<input type="hidden" id="clientId" name="clientId" value="${clientId }">
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="WorkOrderSynthesize" class="form form-horizontal well clearfix">
			  <div class="col-xs-12 col-sm-6 col-md-4">
			 	<label class="label-item single-overflow pull-left" title="客户：">客户：</label>
				<form:select path="clientId" class="form-control m-b">
					<form:option value="" label="---请选择---" />
					<form:options items="${fns:getSqlList('SELECT DISTINCT c.id AS value ,c.client_name as label from client c')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
			
			 
		 <div class="col-xs-12 col-sm-6 col-md-4">
			<div style="margin-top:26px">
			  <a  id="search" class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i class="fa fa-search"></i> 查询</a>
			  <a  id="reset" class="btn btn-primary btn-rounded  btn-bordered btn-sm" ><i class="fa fa-refresh"></i> 重置</a>
			 </div>
	    </div>	
	</form:form>
	</div>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div id="toolbar">
			
		
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