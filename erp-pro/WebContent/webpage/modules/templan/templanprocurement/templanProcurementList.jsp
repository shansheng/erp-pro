<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>采购发货管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="templanProcurementList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">采购发货列表</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="templanProcurement" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="申请单号：">申请单号：</label>
				<form:input path="code" htmlEscape="false" maxlength="100"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="部门审批人：">部门审批人：</label>
				<form:input path="departmentAssessingName" htmlEscape="false" maxlength="64"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="部门审批时间：">部门审批时间：</label>
				<div class="col-xs-12">
			            <div class="col-xs-12 col-sm-5">
						   
					        	  <div class='input-group date' id='departmentAssessingDate' style="left: -10px;" >
					                   <input type='text'  name="departmentAssessingDate" class="form-control"  />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					             </div>	
					        </div>
					        <div class="col-xs-12 col-sm-1">
					        		~
					       	</div>
					        <div class="col-xs-12 col-sm-5">
					          	<div class='input-group date' id='departmentAssessingDateEnd' style="left: -10px;" >
					                   <input type='text'  name="departmentAssessingDateEnd" class="form-control" />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					           	</div>	
					        </div>	
					</div>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="副总审批人：">副总审批人：</label>
				<form:input path="deputyAssessingName" htmlEscape="false" maxlength="64"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="副总审批时间：">副总审批时间：</label>
				<div class="col-xs-12">
			            <div class="col-xs-12 col-sm-5">
						   
					        	  <div class='input-group date' id='deputyAssessingDate' style="left: -10px;" >
					                   <input type='text'  name="deputyAssessingDate" class="form-control"  />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					             </div>	
					        </div>
					        <div class="col-xs-12 col-sm-1">
					        		~
					       	</div>
					        <div class="col-xs-12 col-sm-5">
					          	<div class='input-group date' id='deputyAssessingDateEnd' style="left: -10px;" >
					                   <input type='text'  name="deputyAssessingDateEnd" class="form-control" />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					           	</div>	
					        </div>	
					</div>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="申请人：">申请人：</label>
				<form:input path="createBy.name" htmlEscape="false" maxlength="64"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<div class="form-group">
					<label class="label-item single-overflow pull-left" title="申请日期：">&nbsp;申请日期：</label>
					<div class="col-xs-12">
			            <div class="col-xs-12 col-sm-5">
						   
					        	  <div class='input-group date' id='createDate' style="left: -10px;" >
					                   <input type='text'  name="createDate" class="form-control"  />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					             </div>	
					        </div>
					        <div class="col-xs-12 col-sm-1">
					        		~
					       	</div>
					        <div class="col-xs-12 col-sm-5">
					          	<div class='input-group date' id='createDateEnd' style="left: -10px;" >
					                   <input type='text'  name="createDateEnd" class="form-control" />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					           	</div>	
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
			<shiro:hasPermission name="templan:templanprocurement:templanProcurement:deliver">
				<button id="deliver" class="btn btn-primary" disabled onclick="deliverAll()">
	            	<i class="glyphicon glyphicon-plus"></i> 发货
	        	</button>
			</shiro:hasPermission>
	        	<a class="accordion-toggle btn btn-default" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					<i class="fa fa-search"></i> 检索
				</a>
		    </div>
		
	<!-- 表格 -->
	<table id="templanProcurementTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <%-- <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="templan:templanprocurement:templanProcurement:deliver">
        <li data-item="deliver"><a>发货</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="templan:templanprocurement:templanProcurement:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>   --%>
	</div>
	</div>
	</div>
</body>
</html>