<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>bom管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
		var validateForm;
		var $matBomDetailTreeTable; // 父页面table表格id
		var $topIndex;//openDialog的 dialog index
		function doSubmit(treeTable, index){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $matBomDetailTreeTable = treeTable;
			  $topIndex = index;
			  jp.loading();
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
						jp.post("${ctx}/matbom/matBomDetail/save",$('#inputForm').serialize(),function(data){
		                    if(data.success){
		                    	$matBomDetailTreeTable.jstree("refresh");
		                    	jp.success(data.msg);
		                    }else {
	            	  			jp.error(data.msg);
		                    }
		                    
		                        jp.close($topIndex);//关闭dialog
		            });;
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
		});
	</script>
</head>

<body class="bg-white">
<form:form id="inputForm" modelAttribute="matBomDetail" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		
		<input type="hidden" id="sysMarketListId" name="sysMarketListId" value="${sysMarketListId }">
		<input type="hidden" id="matBomId" name="matBomId" value="${matBomId }">
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>大部件：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/mdsitem/mdsItem/data?itemType=A" id="mdsItemId" name="mdsItemId" value="${matBomDetail.mdsItemId}" labelName="mdsItem.itemName" labelValue="${matBomDetail.mdsItemName}"
							 title="选择物料" cssClass="form-control required" fieldLabels="部件编号|部件名称|物料版本" fieldKeys="itemCode|itemName|itemVersion" searchLabels="部件编号|部件名称" searchKeys="itemCode|itemName" ></sys:gridselect>
							 
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>上级菜单：</label></td>
					<td class="width-35">
						<sys:treeselect id="parent" name="parent.id" value="${matBomDetail.parent.id}" labelName="parent.name" labelValue="${matBomDetail.parent.mdsItemName}"
						title="上级菜单" url="/matbom/matBomDetail/treeData?matBomId=${matBomId }" extId="${matBomDetail.id}" cssClass="form-control required" allowClear="true"/>
					</td>
				</tr>
				
				
		 	</tbody>
		</table>
		</form:form>











		
</body>

</html>