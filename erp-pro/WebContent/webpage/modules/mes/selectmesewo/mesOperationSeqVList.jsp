<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>生产计划查询管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<style type="text/css">
		.wpg{background-color: #999 !important; color: white;}
		.seq{width: 100px;height:50px;}
	</style>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="mesOperationSeqVList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">生产计划查询列表</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<div class="row" style="height: 40px;">
		<div class="col-sm-12" >
			<span class="btn wpg"></span>未派工
			<span class="btn btn-success"></span>未开工
			<span class="btn btn-warning"></span>已开工
			<span class="btn btn-primary"></span>已完工
			<input type="hidden" id="mesEwoId" name="mesEwoId" value="${mesEwoId }">
		</div>
	</div>
	<div class="row">
		<div style="height: 40px;">
			<div class="col-sm-12" style="overflow-x:auto;white-space:nowrap;">
			<c:forEach items="${mesList }" var="mesOperationSeqV">
			    <c:if test="${mesOperationSeqV.seqStatus eq '1'}">
					<span class="btn btn-xs btn-circle wpg">${mesOperationSeqV.operationSeqNo}</span><label>${mesOperationSeqV.operationSeqDesc}</label>
				</c:if>
				 <c:if test="${mesOperationSeqV.seqStatus eq '2'}">
					<span class="btn btn-success btn-xs btn-circle">${mesOperationSeqV.operationSeqNo}</span><label>${mesOperationSeqV.operationSeqDesc}</label>
				</c:if>
				 <c:if test="${mesOperationSeqV.seqStatus eq '3'}">
					<span class="btn btn-warning btn-xs btn-circle">${mesOperationSeqV.operationSeqNo}</span><label>${mesOperationSeqV.operationSeqDesc}</label>
				</c:if>
				 <c:if test="${mesOperationSeqV.seqStatus eq '4'}">
					<span class="btn btn-primary btn-xs btn-circle ">${mesOperationSeqV.operationSeqNo}</span><label>${mesOperationSeqV.operationSeqDesc}</label>
				</c:if>
			</c:forEach>
			</div>
		</div>
	</div>
		
	<!-- 表格 -->
	<table id="mesOperationSeqVTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="mesoperationseqv:mesOperationSeqV:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="mesoperationseqv:mesOperationSeqV:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>