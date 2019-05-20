<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>销售管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="sysMarketList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">销售列表</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="sysMarket" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-4">
			 <label class="label-item single-overflow pull-left" title="客户：">客户：</label>
				<form:select path="clientId" class="form-control m-b">
									<form:option value="" label="---请选择---" />
									<form:options items="${fns:getSqlList('SELECT DISTINCT c.id AS value ,c.client_name as label from client c')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
			</div>
			
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="合同编号：">合同编号：</label>
				<form:input path="contractNo" htmlEscape="false" maxlength="255"  class=" form-control"/>
			</div>

			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="责任人：">责任人：</label>
				<form:input path="principalName" htmlEscape="false" maxlength="255"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="合同状态：">合同状态：</label>
				<form:select path="contractStatus"  class="form-control m-b">
					<form:option value="" label="---请选择---"/>
					<form:options items="${fns:getDictList('contract_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="是否发货：">是否发货：</label>
				<form:select path="shipments"  class="form-control m-b">
					<form:option value="" label="---请选择---"/>
					<form:options items="${fns:getDictList('shipments')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
						 <div class="col-xs-12 col-sm-6 col-md-4">
				 <div class="form-group">
					<label class="label-item single-overflow pull-left" title="合同签订时间：">&nbsp;合同签订时间：</label>
					<div class="col-xs-12">
						   <div class="col-xs-12 col-sm-5">
						   
					        	  <div class='input-group date' id='beginContractDate' style="left: -10px;" >
					                   <input type='text'  name="beginContractDate" class="form-control"  />
					                   <span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
					             </div>	
					        </div>
					        <div class="col-xs-12 col-sm-1">
					        		~
					       	</div>
					        <div class="col-xs-12 col-sm-5">
					          	<div class='input-group date' id='endContractDate' style="left: -10px;" >
					                   <input type='text'  name="endContractDate" class="form-control" />
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
	<div id="toolbar">

				
			
	        	<a class="accordion-toggle btn btn-default" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					<i class="fa fa-search"></i> 检索
				</a>
		    </div>

		
	<!-- 表格 -->
	<table id="sysMarketTable"   data-toolbar="#toolbar"></table>

   
	</div>
	</div>
	</div>
</body>
</html>