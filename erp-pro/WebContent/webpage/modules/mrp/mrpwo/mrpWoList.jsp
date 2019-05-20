<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>任务管理</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="ani" />
<%@ include file="/webpage/include/bootstraptable.jsp"%>
<%@include file="/webpage/include/treeview.jsp"%>
<%@include file="mrpWoList.js"%>

<script type="text/javascript">
$(document).ready(function(){
});
$('#planStartDate').datetimepicker({
	format : "YYYY-MM-DD"
});
$('#planEndDate').datetimepicker({
	format : "YYYY-MM-DD"
}); 
</script>

</head>
<body>
	<div class="wrapper wrapper-content">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">任务管理列表</h3>
			</div>
			<div class="panel-body">
				<sys:message content="${message}" />

				<!-- 搜索 -->
				<div class="accordion-group">
					<div id="collapseTwo" class="accordion-body collapse">
						<div class="accordion-inner">
							<form:form id="searchForm" modelAttribute="mrpWo"
								class="form form-horizontal well clearfix">
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="工作订单号：">工作订单号：</label>
									<form:input path="woNo" htmlEscape="false" maxlength="32"
										class=" form-control" />
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left" title="年：">年：</label>
									<form:input path="yearStr" htmlEscape="false" maxlength="32"
										class=" form-control" />
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left" title="月：">月：</label>
									<form:input path="monthStr" htmlEscape="false" maxlength="32"
										class=" form-control" />
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="物料号：">物料号：</label>
									<sys:gridselect url="${ctx}/mdsitem/mdsItem/data?itemType=M"
										id="mdsItemId" name="mdsItemId" value="${mrpWo.mdsItemId}"
										labelName="mdsItem.itemCode" labelValue="${mrpWo.mdsItemCode}"
										title="选择物料" cssClass="form-control required"
										fieldLabels="物料号|物料名|物料版本"
										fieldKeys="itemCode|itemName|itemVersion"
										searchLabels="物料号|物料名" searchKeys="itemCode|itemName"></sys:gridselect>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="产品号：">产品号：</label>
									<sys:gridselect url="${ctx}/mdsitem/mdsItem/data?itemType=G"
										id="proMdsItemId" name="proMdsItemId"
										value="${mrpWo.proMdsItemId}" labelName="mdsItem.itemCode"
										labelValue="${mrpWo.proMdsItemCode}" title="选择产品"
										cssClass="form-control required" fieldLabels="产品号|产品名|产品版本"
										fieldKeys="itemCode|itemName|itemVersion"
										searchLabels="产品号|产品名" searchKeys="itemCode|itemName"></sys:gridselect>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="计划开始时间：">计划开始时间：</label>
									<div class="col-sm-12">
										<p class="input-group">
										<div class='input-group form_datetime' id='planStartDate'>
											<input type='text' name="planStartDate"
												class="form-control required"
												value="<fmt:formatDate value="${mrpWo.planStartDate}" pattern="yyyy-MM-dd"/>" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
										</p>
									</div>

								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<label class="label-item single-overflow pull-left"
										title="计划结束时间：">计划结束时间：</label>
									<div class="col-sm-12">
										<p class="input-group">
										<div class='input-group form_datetime' id='planEndDate'>
											<input type='text' name="planEndDate"
												class="form-control required"
												value="<fmt:formatDate value="${mrpWo.planEndDate}" pattern="yyyy-MM-dd"/>" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
										</p>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<div style="margin-top: 26px">
										<a id="search"
											class="btn btn-primary btn-rounded  btn-bordered btn-sm">
											<i class="fa fa-search"></i> 查询
										</a> <a id="reset"
											class="btn btn-primary btn-rounded  btn-bordered btn-sm">
											<i class="fa fa-refresh"></i> 重置
										</a>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>

				<!-- 工具栏 -->
				<div id="toolbar">
					<shiro:hasPermission name="mrpwo:mrpWo:add">
						<a id="add" class="btn btn-primary" onclick="add()"><i
							class="glyphicon glyphicon-plus"></i> 新建</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="mrpwo:mrpWo:edit">
						<button id="edit" class="btn btn-success" disabled
							onclick="edit()">
							<i class="glyphicon glyphicon-edit"></i> 修改
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="mrpwo:mrpWo:del">
						<button id="remove" class="btn btn-danger" disabled
							onclick="deleteAll()">
							<i class="glyphicon glyphicon-remove"></i> 删除
						</button>
					</shiro:hasPermission>
					<button id="dispatch" class="btn btn-warning"  onclick="dispatch()" title="下达">
								<i class="glyphicon glyphicon-plus"></i>下达
							</button>
					<shiro:hasPermission name="mrpwo:mrpWo:import">
						<button id="btnImport" class="btn btn-info">
							<i class="fa fa-folder-open-o"></i> 导入
						</button>
						<div id="importBox" class="hide">
							<form id="importForm" action="${ctx}/mrpwo/mrpWo/import"
								method="post" enctype="multipart/form-data"
								style="padding-left: 20px; text-align: center;">
								<br /> <input id="uploadFile" name="file" type="file"
									style="width: 330px" />导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br />


							</form>
						</div>
					</shiro:hasPermission>
					<a class="accordion-toggle btn btn-default" data-toggle="collapse"
						data-parent="#accordion2" href="#collapseTwo"> <i
						class="fa fa-search"></i> 检索
					</a>
				</div>

				<!-- 表格 -->
				<table id="mrpWoTable" data-toolbar="#toolbar"></table>

				<!-- context menu -->
				<ul id="context-menu" class="dropdown-menu">
					<shiro:hasPermission name="mrpwo:mrpWo:edit">
						<li data-item="edit"><a>编辑</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="mrpwo:mrpWo:del">
						<li data-item="delete"><a>删除</a></li>
					</shiro:hasPermission>
					<li data-item="action1"><a>取消</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>