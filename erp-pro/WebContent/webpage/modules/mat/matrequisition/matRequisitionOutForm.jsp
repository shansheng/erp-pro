<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>领料单出库管理</title>
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
					jp.post("${ctx}/matrequisition/matRequisition/save",$('#inputForm').serialize(),function(data){
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
		//出库
		function deliver() {
			var flag = false;
			var len = $("input[name=boxs]:checked").length;
			if(len == 0){
				alert("请选择一条记录!");
			}else{
				 var check_val = [];
				   var check = $("input[name=boxs]:checked");
				   check.each(function(){
					   var row = $(this).parent("td").parent("tr");
					   var id = row.find("[name='matListId']").val();
					   var remarks = row.find("[name='matRemarks']").val();
					   var shipmentsStatus = row.find("[name='matOutputStatus']").val();
					   var matRequisitionId = row.find("[name='matRequisitionId']").val();
					   if(shipmentsStatus == "已出库"){
						   flag = true;
					   }
					   
					   check_val.push( {id:id,remarks:remarks,matRequisitionId:matRequisitionId});
				   }) 
			   
			if(flag){
			      alert("该物料已出库，不能进行重复操作！");
			} else {
				$.ajax({
					url : "${ctx}/matoutrequisition/matOutRequisition/deliver",
					data : JSON.stringify(check_val),
					type:"POST",
					contentType:'application/json;charset=utf-8',
					success : function(data){
						if(data.success){
							window.location.reload();
							jp.success(data.msg);
						}else{
							jp.error(data.msg);
						}
						
					}
				}) 
			}	
			}
			 
		}
		
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
		
			$(list+idx).find(".form_datetime").each(function(){
				 $(this).datetimepicker({
					 format: "YYYY-MM-DD"
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
	
		function checkCode(){
			
			jp.post("${ctx}/matrequisition/matRequisition/checkCode",$('#inputForm').serialize(),function(data){
				if(data.success){
                	$table.bootstrapTable('refresh');
                	jp.close($topIndex);//关闭dialog

                }else{
                	$("#code").val("");
    	  			jp.error(data.msg);
                }
			})
		}
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="matRequisition" action="${ctx}/matrequisition/matRequisition/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		    <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>申请单号：</label></td>
					<td class="width-35">
						<form:input path="code" htmlEscape="false"   class="form-control required" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">申请原因：</label></td>
					<td class="width-35">
						<form:textarea path="remarks" htmlEscape="false" rows="4"    class="form-control " readonly="true"/>
					</td>
				</tr>
		 	</tbody>
		</table>
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">领料单申请表：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<shiro:hasPermission name="matoutrequisition:matOutRequisition:out:deliver">
			<a class="btn btn-white btn-sm" onclick="deliver()" title="出库"><i class="fa fa-plus"></i> 出库</a>
			</shiro:hasPermission>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th></th>
						<th><font color="red">*</font>物料编号</th>
						<th><font color="red">*</font>物料名称</th>
						<th><font color="red">*</font>申请数量</th>
						<th><font color="red">*</font>需求日期</th>
						<th><font color="red">*</font>出库状态</th>
						<th>备注</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="matRequisitionListList">
				</tbody>
			</table>
			<script type="text/template" id="matRequisitionListTpl">//<!--
				<tr id="matRequisitionListList{{idx}}">
					<td class="hide">
						<input id="matRequisitionListList{{idx}}_id" name="matListId" type="hidden" value="{{row.id}}"/>
						<input id="matRequisitionListList{{idx}}_delFlag" name="matRequisitionListList[{{idx}}].delFlag" type="hidden" value="0"/>
                        <input id="matRequisitionListList{{idx}}_matRequisitionId" name="matRequisitionId" type="hidden" value="{{row.matRequisitionId}}"/>
					</td>
					<td>
                       <input type="checkbox" name="boxs"/>
                    </td>
					<td>
					<input id="matRequisitionListList{{idx}}_itemCode" name="matRequisitionListList[{{idx}}].itemCode" type="text" value="{{row.itemCode}}"    class="form-control required" readonly="readonly"/>
					</td>
					<td>
						<input id="matRequisitionListList{{idx}}_itemName" name="mdsItem.itemName" type="text" value="{{row.itemName}}"    class="form-control required" readonly="readonly"/>
					</td>
					
					<td>
						<input id="matRequisitionListList{{idx}}_applyNumber" name="matRequisitionListList[{{idx}}].applyNumber" type="text" value="{{row.applyNumber}}"    class="form-control required" readonly="readonly"/>
					</td>
					
					
				
					
					
				<td>
						<div class='input-group form_datetime' id="matRequisitionListList{{idx}}_demandDate">
		                    <input type='text'  name="matRequisitionListList[{{idx}}].demandDate" class="form-control required"  value="{{row.demandDate}}" readonly="readonly"/>
		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>						            
					</td>
					<td>
                        <input id="matRequisitionListList{{idx}}_outputStatus" name="matOutputStatus" type="text" value="{{row.outputStatus}}"    class="form-control required" readonly="readonly"/> 
                    </td>
					<td>
						<textarea id="matRequisitionListList{{idx}}_remarks" name="matRemarks" rows="2"    class="form-control ">{{row.remarks}}</textarea>
					</td>
					
					
					
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#matRequisitionListList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var matRequisitionListRowIdx = 0, matRequisitionListTpl = $("#matRequisitionListTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(matRequisition.matRequisitionListList)};
					for (var i=0; i<data.length; i++){
						addRow('#matRequisitionListList', matRequisitionListRowIdx, matRequisitionListTpl, data[i]);
						matRequisitionListRowIdx = matRequisitionListRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>