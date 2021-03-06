<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>mat_bom管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="matBomDetailTreeList.js" %>
	<%@include file="matBomList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title"> 
				<a class="panelButton" href="${ctx}/matbom/matBom/list"><i class="ti-angle-left"></i> 返回</a>
			</h3>
	</div>
	<div class="panel-body">
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
					<input type="hidden" id="matBomId" name="matBomId" value="${matBomDetail.matBomId }">
					</div>
				</div>
				<div  class="col-sm-8 col-md-9 animated fadeInRight">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
		<input type="hidden" id="sysMarketListId" name="sysMarketListId" value="${sysMarketListId }">
		<input type="hidden" id="matBomDetailId" name="matBomDetailId" value="${matBomDetailId }">
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
			<shiro:hasPermission name="matbom:matBomTable:add">
				<a id="add" class="btn btn-primary" onclick="add()"><i class="glyphicon glyphicon-plus"></i>从物料选取</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="matbom:matBomTable:add">
				<a id="quota" class="btn btn-primary" onclick="quota()"><i class="glyphicon glyphicon-plus"></i>从材料定额选取</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="matbom:matBomTable:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="matbom:matBomTable:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="matbom:matBomTable:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
				<div id="importBox" class="hide">
						<form id="importForm" action="${ctx}/matbom/matBomTable/import" method="post" enctype="multipart/form-data"
							 style="padding-left:20px;text-align:center;" ><br/>
							<input id="uploadFile" name="file" type="file" style="width:330px"/>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br/>　　
							
							
						</form>
				</div>
			</shiro:hasPermission>
	        	
		    </div>
		
	<!-- 表格 -->
	<table id="matBomTableTable"   data-toolbar="#toolbar"></table>
 <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="matbom:matBomTable:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="matbom:matBomTable:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
   
	</div>
	</div>
	</div>
	</div>
</div>
</body>
</html>