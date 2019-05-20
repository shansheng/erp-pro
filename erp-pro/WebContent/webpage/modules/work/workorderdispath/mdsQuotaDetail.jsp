<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<link href="${ctxStatic}/plugin/bootstrapTree/bootstrap-treeview.css" rel="stylesheet" type="text/css"/>
	<script src="${ctxStatic}/plugin/bootstrapTree/bootstrap-treeview.js" type="text/javascript"></script>
	<%@include file="mdsQuotaDetail.js" %>
</head>
<body>
<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
			<h3 class="panel-title"> 
				<a class="panelButton" href="${ctx}/workorderdispath/workOrderDispath/list"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
	<div class="panel-body">
		<input id="mdsQuotaId" name="mdsQuotaid" type="hidden" value="${mdsQuotaId}" />
		
		<input id="view" name="view" type="hidden" value="0" />
		<div class="row">

		<div class="col-sm-3 col-md-2" >
			<font color="red">*</font>领料人：
				<sys:userselect id="user" name="createBy" value="${createBy.id}" labelName="" labelValue="${createBy.name}"
							    cssClass="form-control required"/>
			<div id="mdsQuotaDetailTree"></div>
		</div>
		<div  class="col-sm-9 col-md-10 animated fadeInRight">
			<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
		<form:form id="searchForm" modelAttribute="mdsQuotaDetail" class="form form-horizontal well clearfix">
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="label-item single-overflow pull-left" title="物料编码：">物料编码：</label>
					<form:input path="itemCode" htmlEscape="false" maxlength="100" class=" form-control"/>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="label-item single-overflow pull-left" title="物料名称：">物料名称：</label>
					<form:input path="itemName" htmlEscape="false" maxlength="100" class=" form-control" />
				</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="材料定额分类：">材料定额分类：</label>
				<!-- <input id="classify" name="classify" class=" form-control" type="text" value="" maxlength="100"> -->
				<form:select path="classify"  class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('mds_quota_classify')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				
			<shiro:hasPermission name="mds:mdsquota:mdsQuota:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 出库
	        	</button>
			</shiro:hasPermission>
			
	        	<a class="accordion-toggle btn btn-default" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					<i class="fa fa-search"></i> 检索
				</a>
		    </div>
		
	<!-- 表格 -->
	<table id="mdsQuotaDetailTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <%-- <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="mds:mdsquota:mdsQuota:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="mds:mdsquota:mdsQuota:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>   --%>
	</div>
	</div>
	</div>
	</div>		
</div>
</body>
</html>