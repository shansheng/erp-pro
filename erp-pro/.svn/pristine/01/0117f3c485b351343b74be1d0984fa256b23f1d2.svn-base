<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>到货检验保存管理</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="ani" />
<%@ include file="/webpage/include/bootstraptable.jsp"%>
<%@include file="/webpage/include/treeview.jsp"%>
<%@include file="mdsArrivalCheckoutList.js"%>
</head>
<body>
	<div class="wrapper wrapper-content">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">到货检验保存列表</h3>
			</div>
			<div class="panel-body">
				<sys:message content="${message}" />

				<!-- 搜索 -->
				<div class="accordion-group">
					<div id="collapseTwo" class="accordion-body collapse">
						<div class="accordion-inner">
							<form:form id="searchForm" modelAttribute="mdsArrivalCheckout"
								class="form form-horizontal well clearfix">
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="物料编码：">物料编码：</label>
									<form:input path="itemCode" htmlEscape="false"
										class=" form-control" />
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="物料名称：">物料名称：</label>
									<form:input path="itemName" htmlEscape="false"
										class=" form-control" />
								</div>
								<%-- 
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="供应商名：">供应商名：</label>
				<form:input path="supplierName" htmlEscape="false" maxlength="18"  class=" form-control"/>
			</div>
			 --%>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="供应商名：">供应商名：</label>
									<form:select path="supplierId" class="form-control m-b">
										<form:option value="" label="---请选择---" />
										<form:options
											items="${fns:getSqlList('SELECT DISTINCT c.id AS value ,c.su_name as label from supplier c')}"
											itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</div>

								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="到货检验状态：">到货检验状态：</label>
									<form:select path="state" class="form-control m-b">
										<form:option value="" label="---请选择---" />
										<form:options
											items="${fns:getDictList('mds_arrival_checkout')}"
											itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<div style="margin-top: 26px">
										<a id="search"
											class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i
											class="fa fa-search"></i> 查询</a> <a id="reset"
											class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i
											class="fa fa-refresh"></i> 重置</a>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>

				<!-- 工具栏 -->
				<div id="toolbar">
					<shiro:hasPermission
						name="mds:mdsarrivalcheckout:mdsArrivalCheckout:edit">
						<button id="edit" class="btn btn-success" disabled
							onclick="edit()">
							<i class="glyphicon glyphicon-edit"></i> 保存
						</button>
					</shiro:hasPermission>
					<%-- <shiro:hasPermission name="mds:mdsarrivalcheckout:mdsArrivalCheckout:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
				<div id="importBox" class="hide">
						<form id="importForm" action="${ctx}/mds/mdsarrivalcheckout/mdsArrivalCheckout/import" method="post" enctype="multipart/form-data"
							 style="padding-left:20px;text-align:center;" ><br/>
							<input id="uploadFile" name="file" type="file" style="width:330px"/>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br/>　　
							
							
						</form>
				</div>
			</shiro:hasPermission> --%>
					<a class="accordion-toggle btn btn-default" data-toggle="collapse"
						data-parent="#accordion2" href="#collapseTwo"> <i
						class="fa fa-search"></i> 检索
					</a>
				</div>

				<!-- 表格 -->
				<table id="mdsArrivalCheckoutTable" data-toolbar="#toolbar"></table>

				<!-- context menu -->
				<ul id="context-menu" class="dropdown-menu">
					<shiro:hasPermission
						name="mds:mdsarrivalcheckout:mdsArrivalCheckout:edit">
						<li data-item="edit"><a>保存</a></li>
					</shiro:hasPermission>
					<li data-item="action1"><a>取消</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>