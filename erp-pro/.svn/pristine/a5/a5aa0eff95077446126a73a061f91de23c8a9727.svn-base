<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工艺管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="mdsRouteList.js" %>
</head>

<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">工艺列表</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="mdsRoute" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="物料号：">物料号：</label>
				<sys:gridselect url="${ctx}/mdsitem/mdsItem/data?itemType=M" id="mdsItemId" name="mdsItemId" value="${mdsRoute.mdsItemId}" labelName="mdsItem.itemCode" labelValue="${mdsRoute.mdsItemCode}"
							 title="选择物料" cssClass="form-control required" fieldLabels="物料号|物料名|物料版本" fieldKeys="itemCode|itemName|itemVersion" searchLabels="物料号|物料名" searchKeys="itemCode|itemName" ></sys:gridselect>
							 
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="产品：">产品：</label>
			<sys:gridselect url="${ctx}/mdsitem/mdsItem/data?itemType=A" id="proMdsItemId" name="proMdsItemId" value="${mdsRoute.proMdsItemId}" labelName="mdsItem.itemCode" labelValue="${mdsRoute.proMdsItemCode}"
							 title="选择产品" cssClass="form-control required" fieldLabels="产品号|产品名|产品版本" fieldKeys="itemCode|itemName|itemVersion" searchLabels="产品号|产品名" searchKeys="itemCode|itemName" ></sys:gridselect>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="部门：">部门：</label>
				<sys:treeselect id="office" name="office.id" value="${mdsRoute.office.id}" labelName="office.name" labelValue="${mdsRoute.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="form-control" allowClear="true" notAllowSelectParent="true"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="工艺编号：">工艺编号：</label>
				<form:input path="routeNo" htmlEscape="false" maxlength="32"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="工艺版本：">工艺版本：</label>
				<form:input path="routeEd" htmlEscape="false" maxlength="200"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="工艺名称：">工艺名称：</label>
				<form:input path="routeDesc" htmlEscape="false" maxlength="100"  class=" form-control"/>
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
			<shiro:hasPermission name="mdsroute:mdsRoute:add">
				<a id="add" class="btn btn-primary" onclick="add()"><i class="glyphicon glyphicon-plus"></i> 新建</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="mdsroute:mdsRoute:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="mdsroute:mdsRoute:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="mdsroute:mdsRoute:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
				<div id="importBox" class="hide">
						<form id="importForm" action="${ctx}/mdsroute/mdsRoute/import" method="post" enctype="multipart/form-data"
							 style="padding-left:20px;text-align:center;" ><br/>
							<input id="uploadFile" name="file" type="file" style="width:330px"/>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br/>　　
							
							
						</form>
				</div>
			</shiro:hasPermission> --%>
	        	<a class="accordion-toggle btn btn-default" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					<i class="fa fa-search"></i> 检索
				</a>
		    </div>
		
	<!-- 表格 -->
	<table id="mdsRouteTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="mdsroute:mdsRoute:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="mdsroute:mdsRoute:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>