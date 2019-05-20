<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>

<html>
<head>
<script type="text/javascript">

function editst(id){//问题转移
	  if(id == undefined){
			id = $("#sysQueId").val();
		}
	   <shiro:hasPermission name="sysquetransfer:sysQueTransfer:edit">
	  jp.openDialog('编辑问题转移', "${ctx}/sysquetransfer/sysQueTransfer/form?sysQueId=" + id,'800px', '500px', $('#sysReplyTable'));
	   </shiro:hasPermission>
	  
}
/* function editReply(id){//问题回复
	  if(id == undefined){
			id = $("#sysQueId").val();
		}
	   <shiro:hasPermission name="sysquetransfer:sysQueTransfer:edit">
	  jp.openDialog('编辑问题转移', "${ctx}/sysreply/sysReply/form?questionId=" + id,'800px', '500px', $('#sysReplyTable'));
	   </shiro:hasPermission>
	
	  
} */




</script>
	<title>回复管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
<div class="panel-heading">
		<h3 class="panel-title"> 
		<a class="panelButton" href="${ctx}/sysquestion/sysQuestion/list"><i class="ti-angle-left"></i> 返回</a>
	    </h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>



<div class="panel panel-default">
   <div class="panel-heading">问题</div>
  <table class="table">
      <tr><td style="width:200px; border-right:1px solid #ddd;">标题:</td><td >${sysQuestion.title }</td></tr>
      <tr><td style="width:200px; border-right:1px solid #ddd;">分类:</td><td> ${sysQuestion.classifyName }</td></tr>
      <tr><td style="width:200px; border-right:1px solid #ddd;">子分类:</td><td>${sysQuestion.subclassificationName }</td></tr>
   	  <tr><td style="width:200px; border-right:1px solid #ddd;">内容:</td><td>${sysQuestion.text }</td></tr>
   	  <tr><td style="width:200px; border-right:1px solid #ddd;">附件:</td><td>
   	  <c:forEach items="${uploads}" var="upLoad" varStatus="loop"> 
   	  <a href="${upLoad }" target="_blank">${ups[loop.count-1] }</a><br>
   	  </c:forEach>
   	  </td></tr>
   </table>
</div>

<div align="right" >

<a style="font-size: 20px; text-decoration:none;" href="${ctx}/sysquestion/sysQuestion/updateStatus?sysQueId=${sysQueId }" >问题关闭</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="${ctx}/sysquetransfer/sysQueTransfer/form?sysQueId=${sysQueId }" style="font-size: 20px; text-decoration:none;">问题转移</a>&nbsp;&nbsp;&nbsp;&nbsp;

</div>
	
	<!-- 表格 -->
	<table class="table" id="sysReplyTable" >
	<c:forEach items="${replyList }" var="reply">
	
	<tr>
	<td style="width:200px; border-right:1px solid #ddd;">${reply.replierName }<br>
	<fmt:formatDate value="${reply.replyDate }"  type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
	</td>
	<td align="left">${reply.text }</td>
	</tr>
	</c:forEach>
	</table>
	
<div>
	<form action="${ctx}/sysreply/sysReply/save">
		<input type="hidden" name="sysQueId" id="sysQueId" value="${sysQueId }">
		<table class="table table-bordered">
		   <tbody>
				<tr>
				<td class="width-15 active"><label class="pull-right">回复内容：</label></td>
					<td class="width-35" colspan="3">
						
						<textarea rows="20" cols="20" name="text" style="margin: 0px; width: 907px; height: 394px;"></textarea>
					</td>
				</tr>
				<tr>
				<td colspan="4" align="right"><button type="submit">确定</button> </td>
				</tr>
		 	</tbody>
		</table>
		</form>
</div>
	
<input type="hidden" id="sysQueId" value="${sysQueId }"> 
    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="sysreply:sysReply:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="sysreply:sysReply:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>