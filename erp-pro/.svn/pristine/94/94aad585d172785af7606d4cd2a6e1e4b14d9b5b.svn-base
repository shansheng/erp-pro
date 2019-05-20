<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>计划管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="mesEwoList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">计划列表</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="mesEwo" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="执行工作订单号：">执行工作订单号：</label>
				<form:input path="ewoNo" htmlEscape="false" maxlength="20"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="物料ID">物料号：</label>
				<form:input path="mdsItemCode" htmlEscape="false" maxlength="32"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="物料名称">物料名称：</label>
				<form:input path="mdsItemName" htmlEscape="false" maxlength="32"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<span>产品</span>
								<form:select path="proMdsItemCode" class="form-control m-b">
									<form:option value="" label="请选择" />
									<form:options items="${fns:getSqlList('SELECT DISTINCT mesewo.pro_mds_item_code AS value ,mesewo.pro_mds_item_code as label from mes_ewo_v mesewo')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="批次号：">批次号：</label>
				<form:input path="batchNo" htmlEscape="false" maxlength="20"  class=" form-control"/>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4">
								<span>状态</span>
								<form:select path="ewoStatus" class="form-control m-b">
									<form:option value="" label="请选择" />
									<form:options items="${fns:getDictList('ewo_status')}"
										itemLabel="label" itemValue="value" htmlEscape="false" />
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
								<button  class="btn btn-primary"  onclick="doFeed()" title="投料">
								<i class="glyphicon glyphicon-plus"></i>投料
							</button>
							<button id="dispatch" class="btn btn-warning"  onclick="dispatch()" title="下达">
								<i class="glyphicon glyphicon-plus"></i>下达
							</button>
							<button id="dispatchDel"  class=" btn btn-danger" onclick="dispatchDel()" title="删除投料">
								<i class="glyphicon glyphicon-plus"></i>删除投料
							</button>

	        	<a class="accordion-toggle btn btn-default" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					<i class="fa fa-search"></i> 检索
				</a>
		    </div>
		
	<!-- 表格 -->
	<table id="mesEwoTable"   data-toolbar="#toolbar">
	<tr>
	<td hidden="true" class="ewoStatus">${mesEwo.ewoStatus}</td>
	</tr>
	</table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="meswo:mesEwo:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="meswo:mesEwo:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>