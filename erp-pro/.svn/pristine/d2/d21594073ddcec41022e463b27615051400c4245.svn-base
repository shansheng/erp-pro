<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>问题管理管理</title>
	<meta name="decorator" content="ani"/>
	
	<script type="text/javascript">
		var validateForm;
		var $table; // 父页面table表格id
		var $topIndex;//弹出窗口的 index
		function doSubmit(table, index){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $table = table;
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
					jp.post("${ctx}/sysquestion/sysQuestion/save",$('#inputForm').serialize(),function(data){
						if(data.success){
	                    	$table.bootstrapTable('refresh');
	                    	jp.success(data.msg);
	                    	jp.close($topIndex);//关闭dialog

	                    }else{
            	  			jp.error(data.msg);
	                    }
					})
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
	<script type="text/javascript">
	function selectSub(){
		var classify=$("#classify").val();
		$.ajax({
 	        async: true,
 	        url: "${ctx}/sysquestion/sysQuestion/selectSub",
 	        data:{classify:classify},
 	        dataType: "json",
 	       success:function(data){
 	    	  var secondCategoryObj = document.getElementById("subclassification");
 	    	    secondCategoryObj.innerHTML = "<option value=''>----请--选--择----</option>";
 	    	       var dataList = data.tasks;
 	    	       if (dataList!= null) { //如果没有这一步，js会报length null之类的
 	    	       for(var i=0;i<dataList.length;i++)//遍历
 	    	       {
 	    	       var categoryObj = dataList[i]; 
 	    	  var cid = categoryObj.id;
 	    	  var cname = categoryObj.name;
 	    	            //进行添加到标签里
 	    	  secondCategoryObj.innerHTML =  secondCategoryObj.innerHTML + "<option value='"+cid+"'>"+cname+"</option>";
 	    	           
 	    	       }
 	    	       }

 	    	  }
 		});
	}
	
	
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="sysQuestion" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>标题：</label></td>
					<td class="width-35">
						<form:input path="title" htmlEscape="false"    class="form-control required"/>
					</td>
						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>分类：</label></td>
					<td class="width-35">
						<form:select id="classify" path="classify" class="form-control required"  onchange="selectSub()">
							<form:option value="" label="--请选择--"/>
							<form:options items="${fns:getDictList('classify')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>

						<td class="width-15 active"><label class="pull-right"><font color="red">*</font>子分类：</label></td>
					<td class="width-35">
					<select id="subclassification" name="subclassification" class="form-control required ">
					<option value="">----请--选--择----</option>
					</select>
						
					</td> 
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>问题解决人：</label></td>
				<td class="width-35">
						<sys:userselect id="sysUserId" name="sysUserId" value="${sysUserId}" labelName="" labelValue="${sysUserId}"
							    cssClass="form-control required"/>
					</td>
				</tr>
				
				<tr>
									<td class="width-15 active"><label class="pull-right"><font color="red">*</font>问题描述：</label></td>
					<td class="width-35">
						<textarea rows="20" cols="10" name="transferReason" style="margin: 0px; width: 300px; height: 200px;"></textarea>
					</td>
					
					
					<td class="width-15 active"><label class="pull-right">附件：</label></td>
					<td class="width-35">
						<form:hidden id="upl" path="upl" htmlEscape="false" maxlength="255" class="form-control"/>
						<sys:ckfinder input="upload" type="files" uploadPath="/sysquestion/sysQuestion" selectMultiple="true"/>
					</td>
				</tr>
				
				
				
		
		 	</tbody>
		</table>
	</form:form>
</body>
</html>