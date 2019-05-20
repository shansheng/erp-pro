<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>库房事务管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="wmsItemTransactionList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">库房事务列表</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="wmsItemTransaction" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="事务处理流水号 ：">事务处理流水号 ：</label>
				<form:input path="transactionCode" htmlEscape="false" maxlength="30"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="物料：">物料：</label>
										<sys:gridselect url="${ctx}/mdsitem/mdsItem/data?itemType=M" id="mdsItemId" name="mdsItemId" value="${mdsRoute.mdsItemId}" labelName="mdsItem.itemCode" labelValue="${mdsRoute.mdsItemCode}"
							 title="选择物料" cssClass="form-control required" fieldLabels="物料号|物料名|物料版本" fieldKeys="itemCode|itemName|itemVersion" searchLabels="物料号|物料名" searchKeys="itemCode|itemName" ></sys:gridselect>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="库房：">库房 ：</label>
                <sys:gridselect url="${ctx}/wmsinventory/wmsInventory/data" id="wmsInventoryId" name="wmsInventoryId" value="${wmsLocator.wmsInventoryId}" labelName="wmsInventory.inventoryCode" labelValue="${wmsLocator.wmsInventoryCode}"
							 title="选择库房代码" cssClass="form-control required" fieldLabels="库房代码|库房名称" fieldKeys="inventoryCode|inventoryName" searchLabels="库房代码|库房名称" searchKeys="inventoryCode|inventoryName" ></sys:gridselect>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="库位：">库位：</label>
				<sys:gridselect url="${ctx}/wmslocator/wmsLocator/data" id="wmsLocatorId" name="wmsLocatorId" value="${wmsLocator.wmsLocatorId}" labelName="wmsLocator.locatorCode" labelValue="${wmsLocator.locatorCode}"
							 title="选择库位" cssClass="form-control required" fieldLabels="库位号" fieldKeys="locatorCode" searchLabels="库位号" searchKeys="locatorCode" ></sys:gridselect>
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
			<shiro:hasPermission name="wmsitemtransaction:wmsItemTransaction:add">
				<a id="add" class="btn btn-primary" onclick="add()"><i class="glyphicon glyphicon-plus"></i> 新建</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="wmsitemtransaction:wmsItemTransaction:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="wmsitemtransaction:wmsItemTransaction:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="wmsitemtransaction:wmsItemTransaction:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
				<div id="importBox" class="hide">
						<form id="importForm" action="${ctx}/wmsitemtransaction/wmsItemTransaction/import" method="post" enctype="multipart/form-data"
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
	<table id="wmsItemTransactionTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="wmsitemtransaction:wmsItemTransaction:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="wmsitemtransaction:wmsItemTransaction:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>