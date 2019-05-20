<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>班组管理</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="ani" />
<%@ include file="/webpage/include/bootstraptable.jsp"%>
<%@include file="/webpage/include/treeview.jsp"%>
<%@include file="mesWorkshiftList.js"%>
<%@ include file="mesWorkshift-userList.js"%>
</head>
<body>
	<div class="wrapper wrapper-content">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">班组管理列表</h3>
			</div>
			<div class="panel-body">
				<sys:message content="${message}" />
				<div class="row">
					<div id="left" class="col-sm-12">
						<!-- 搜索 -->
						<div class="accordion-group">
							<div id="collapseTwo" class="accordion-body collapse">
								<div class="accordion-inner">
									<form:form id="searchForm" modelAttribute="mesWorkshift"
										class="form form-horizontal well clearfix">
										<div class="col-xs-12 col-sm-6 col-md-4">
											<label class="label-item single-overflow pull-left"
												title="班组代码：">班组代码：</label>
											<form:input path="workcenterCode" htmlEscape="false"
												maxlength="16" class=" form-control" />
										</div>
										<div class="col-xs-12 col-sm-6 col-md-4">
											<label class="label-item single-overflow pull-left"
												title="班组名称：">班组名称：</label>
											<form:input path="workcenterName" htmlEscape="false"
												maxlength="100" class=" form-control" />
										</div>
										<div class="col-xs-12 col-sm-6 col-md-4">
											<label class="label-item single-overflow pull-left"
												title="所属部门 ：">所属部门 ：</label>
											<td class="width-35"><sys:treeselect id="sysOfficeName"
													name="sysOfficeName" value="${mesWorkshift.sysOfficeName}"
													labelName="sysOfficeName"
													labelValue="${mesWorkshift.sysOfficeName}" title="部门"
													url="/sys/office/treeData?type=2"
													cssClass="form-control required" 
													notAllowSelectParent="true" /></td>
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
						<div id="toolbar" >
							<shiro:hasPermission name="mesworkshift:mesWorkshift:add">
								<a id="add" class="btn btn-primary" onclick="add()"><i
									class="glyphicon glyphicon-plus"></i> 新建</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="mesworkshift:mesWorkshift:edit">
								<button id="edit" class="btn btn-success" disabled
									onclick="edit()">
									<i class="glyphicon glyphicon-edit"></i> 修改
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="mesworkshift:mesWorkshift:del">
								<button id="remove" class="btn btn-danger" disabled
									onclick="deleteAll()">
									<i class="glyphicon glyphicon-remove"></i> 删除
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="mesworkshift:mesWorkshift:import">
								<button id="btnImport" class="btn btn-info">
									<i class="fa fa-folder-open-o"></i> 导入
								</button>
								<div id="importBox" class="hide">
									<form id="importForm"
										action="${ctx}/mesworkshift/mesWorkshift/import" method="post"
										enctype="multipart/form-data"
										style="padding-left: 20px; text-align: center;">
										<br /> <input id="uploadFile" name="file" type="file"
											style="width: 330px" />导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br />
									</form>
								</div>
							</shiro:hasPermission>
							<a class="accordion-toggle btn btn-default"
								data-toggle="collapse" data-parent="#accordion2"
								href="#collapseTwo"> <i class="fa fa-search"></i> 检索
							</a>
						</div>

						<!-- 表格 -->
						<table id="mesWorkshiftTable" data-toolbar="#toolbar"
							data-id-field="id">
						</table>

						<!-- context menu -->
						<ul id="context-menu" class="dropdown-menu">
							<shiro:hasPermission name="mesworkshift:mesWorkshift:edit">
								<li data-item="edit"><a>编辑</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission name="mesworkshift:mesWorkshift:del">
								<li data-item="delete"><a>删除</a></li>
							</shiro:hasPermission>
							<li data-item="action1"><a>取消</a></li>
						</ul>
					</div>
					<input type="hidden" id="mesWorkshiftId" name="mesWorkshiftId" value="${mesWorkshiftUser.mesWorkshiftId}"/>
					<div id="right" class="panel panel-default col-sm-6"
						style="display: none">
						<div class="panel-heading">
							<h3 class="panel-title">
								<label>用户列表: </label>
							</h3>
						</div>
						<div class="panel-body">
							<div id="userToolbar">
								<button id="addButton" class="btn btn-outline btn-primary"
									     title="添加人员">
									<i class="fa fa-user-plus"></i> 添加人员
								</button>
							</div>
							<!-- 表格 -->
							<table id="userTable" data-toolbar="#userToolbar"
								data-id-field="id">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>