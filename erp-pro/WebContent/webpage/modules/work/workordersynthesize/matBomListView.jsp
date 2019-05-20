<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>mat_bom管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="matBomDetailTreeListT.js" %>
	<%@include file="matBomListView.js" %>
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
	<input type="hidden" id="matBomId" name="matBomId" value="${matBomId }">
		<div class="row">
				<div class="col-sm-4 col-md-3" >
					<div class="form-group">
						<div class="row">
							<div class="col-sm-10" >
								<div class="input-search">
									<button type="submit" class="input-search-btn">
										<i class="fa fa-search" aria-hidden="true"></i></button>
									<input   id="search_q" type="text" class="form-control input-sm" name="" placeholder="查找...">

								</div>
							</div>
						
						</div>
					</div>
					<div id="matBomDetailjsTree">
					
					</div>
				</div>
				<div  class="col-sm-8 col-md-9 animated fadeInRight">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
		<input type="hidden" id="sysMarketListId" name="sysMarketListId" value="${sysMarketListId }">
		
			<form:form id="searchForm" modelAttribute="matBomDetail" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="大部件：">大部件：</label>
				<sys:treeselect id="matBomDetailId" name="matBomDetailId" value="${matBom.matBomDetailId}" labelName="" labelValue="${matBom.mdsItemName}"
					title="mat_bom_detail_id" url="/matbom/matBomDetail/treeData" extId="${matBom.id}" cssClass="form-control " allowClear="true"/>
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

	        	<a class="accordion-toggle btn btn-default" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					<i class="fa fa-search"></i> 检索
				</a>
		    </div>
		
	<!-- 表格 -->
	<table id="matBomTableTable"   data-toolbar="#toolbar"></table>
 
	</div>
	</div>
	</div>
	</div>
</div>
</body>
</html>