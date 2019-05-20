<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>采购查看页</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="mdspurchaseconfirmationList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">采购查看列表</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="mdsProcurementPlan" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="物料编码：">物料编码：</label>
				<form:input path="itemCode" htmlEscape="false" maxlength="100"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="物料名称：">物料名称：</label>
				<form:input path="itemName" htmlEscape="false" maxlength="100"  class=" form-control"/>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="采购方式：">采购方式：</label>
				<select id="procurementWay" name="procurementWay" class="form-control m-b">
					<option value="" selected="selected">---请选择---</option>
					<option value="项目采购">项目采购</option>
					<option value="批量采购">批量采购</option>
				</select>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="需求时间：">需求时间：</label>
				<div class="col-xs-12">
			            <div class="col-xs-12 col-sm-5">
						   
					        	  <div class='input-group date' id='beginRequiredDate' style="left: -10px;" >
					                   <input type='text'  name="beginRequiredDate" class="form-control"  />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					             </div>	
					        </div>
					        <div class="col-xs-12 col-sm-1">
					        		~
					       	</div>
					        <div class="col-xs-12 col-sm-5">
					          	<div class='input-group date' id='endRequiredDate' style="left: -10px;" >
					                   <input type='text'  name="endRequiredDate" class="form-control" />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					           	</div>	
					        </div>	
					</div>
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
			<shiro:hasPermission name="mds:mdspurchaseconfirmation:reject ">
				<button id="reject" class="btn btn-danger" disabled onclick="reject()">
	            	<i class="glyphicon glyphicon-remove"></i> 驳回
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="mds:mdspurchaseconfirmation:addIndent">
				<button id="addIndent" class="btn btn-primary" disabled onclick="addIndent()">
	            	<i class="glyphicon glyphicon-plus"></i> 确定供应商
	        	</button>
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="mds:mdsPcmPlanProcurement:collect">
				<button id="collect" class="btn btn-info" onclick="collectAll()">
	            	<i class="glyphicon glyphicon-zoom-in"></i> 汇总显示
	        	</button>
			</shiro:hasPermission> --%>
	        	<a class="accordion-toggle btn btn-default" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					<i class="fa fa-search"></i> 检索
				</a>
		    </div>
		
	<!-- 表格 -->
	<table id="mdsProcurementPlanTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
        <li data-item="action1"><a>取消</a></li>
    </ul> 

     </div>
	</div>
	</div>
</body>
</html>