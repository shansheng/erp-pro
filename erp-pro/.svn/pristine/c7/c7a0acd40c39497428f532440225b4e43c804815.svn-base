<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工作计划管理</title>
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
					jp.post("${ctx}/workorderson/workOrderSon/save",$('#inputForm').serialize(),function(data){
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
		
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
			$(list+idx).find(".form_datetime").each(function(){
				 $(this).datetimepicker({
					 format: "YYYY-MM-DD HH:mm:ss"
			    });
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
		
		
	
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="workOrderSon" action="${ctx}/workorderson/workOrderSon/save" method="post" class="form-horizontal">
		<input type="hidden" name="workOrderSonId" id="workOrderSonId" value="${workOrderSonId }">
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
		 	</tbody>
		</table>
		<div class="tabs-container">
            
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th>大部件:</th>
						<th>计划开始时间:</th>
						<th>计划结束时间:</th>
						
					</tr>
				</thead>
				<tbody id="workOrderDetailList">
				</tbody>
			</table>
			<script type="text/template" id="workOrderDetailTpl">//<!--
				<tr id="workOrderDetailList{{idx}}">
					<td class="hide">
						<input id="workOrderDetailList{{idx}}_id" name="workOrderDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="workOrderDetailList{{idx}}_delFlag" name="workOrderDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						<input id="workOrderDetailList{{idx}}_mdsItemName" name="workOrderDetailList[{{idx}}].mdsItemName" type="text" value="{{row.mdsItemName}}"    class="form-control "/>
					</td>
					
					
					<td>
						<input id="workOrderDetailList{{idx}}_beginPlanDate" name="workOrderDetailList[{{idx}}].beginPlanDate" type="text" value="{{row.beginPlanDate}}" onchange="prodateCheck('workOrderDetailList{{idx}}_beginPlanDate')"   class="form-control "/>
					</td>
					
					
					<td>
						<input id="workOrderDetailList{{idx}}_endPlanDate" name="workOrderDetailList[{{idx}}].endPlanDate" type="text" value="{{row.endPlanDate}}"    class="form-control "/>
					</td>
					
				
				</tr>//-->
			</script>
			<script type="text/javascript">
				var workOrderDetailRowIdx = 0, workOrderDetailTpl = $("#workOrderDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(workOrderSon.workOrderDetailList)};
					for (var i=0; i<data.length; i++){
						addRow('#workOrderDetailList', workOrderDetailRowIdx, workOrderDetailTpl, data[i]);
						workOrderDetailRowIdx = workOrderDetailRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>