<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>部门领导审核管理</title>
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
					jp.post("${ctx}/templan/templandepartment/templanDepartment/saveTable",$('#inputForm').serialize(),function(data){
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
		
		/* function doAssessing() {
			var data = $('#inputForm').serialize();
			console.log(data);
			$.ajax({
				url : "${ctx}/templan/templandepartment/templanDepartment/save",
				data : data,
				success : function(data){
					if(data.success){
						jp.success(data.msg);
					}else{
						jp.error(data.msg);
					}
					
				}
			})
		};
		
		function doReject() {
			var data = $('#inputForm').serialize();
			$.ajax({
				url : "${ctx}/templan/templandepartment/templanDepartment/doreject",
				data : data,
				success : function(data){
					if(data.success){
						jp.success(data.msg);
					}else{
						jp.error(data.msg);
					}
					
				}
			})
		}; */
		
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
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="templanDepartment" action="${ctx}/templan/templandepartment/templanDepartment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>申请单号：</label></td>
					<td class="width-35">
						<form:input path="code" htmlEscape="false" readonly="true"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">状态：</label></td>
					<td class="width-35">
						<form:select path="state" disabled="true"   class="form-control m-b">
							<form:option value="" label="请选择"/>
							<form:options items="${fns:getDictList('temporary_plan_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">申请原因：</label></td>
					<td class="width-35" colspan="3">
						<form:textarea path="remarks" htmlEscape="false" rows="2" readonly="true"    class="form-control "/>
					</td>
		  		</tr>
		 	</tbody>
		</table>
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">临时订单物料详情表：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<%-- <shiro:hasPermission name="templan:templandepartment:templanDepartment:add">
			<a class="btn btn-white btn-sm" onclick="doAssessing()" title="同意并保存"><i class="fa fa-plus"></i> 同意并保存</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="templan:templandepartment:templanDepartment:reject">
			<a class="btn btn-white btn-sm" onclick="doReject()" title="驳回"><i class="fa fa-remove"></i> 驳回</a>
			 </shiro:hasPermission> --%>
			 <div class="width-50">
			 
				
			</div>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th colspan="1" align="center">
							<label style="height: 34px;padding: 8px 15px"><font color="red">*</font>审批意见：</label>
						</th>
						<th colspan="2">
							<form:select path="statying" class="form-control m-b required">
								<form:option value="" label="请选择"/>
								<form:options items="${fns:getDictList('templan_department_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</th>
						<th colspan="3">
						</th>
					</tr>
					<tr>
						<th class="hide"></th>
						<th><font color="red">*</font>物料编号</th>
						<th><font color="red">*</font>物料名称</th>
						<th><font color="red">*</font>申请数量</th>
						<th><font color="red">*</font>审批数量</th>
						<th><font color="red">*</font>需求日期</th>
						<th>备注信息</th>
					</tr>
				</thead>
				<tbody id="templanDepartmentListList">
				</tbody>
			</table>
			<script type="text/template" id="templanDepartmentListTpl">//<!--
				<tr id="templanDepartmentListList{{idx}}">
					<td class="hide">
						<input id="templanDepartmentListList{{idx}}_id" name="templanDepartmentListList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="templanDepartmentListList{{idx}}_delFlag" name="templanDepartmentListList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						<input id="templanDepartmentListList{{idx}}_itemCode" name="templanDepartmentListList[{{idx}}].itemCode" type="text" value="{{row.itemCode}}" readonly="readonly"    class="form-control required"/>
					</td>

					<td>
						<input id="templanDepartmentListList{{idx}}_itemName" name="templanDepartmentListList[{{idx}}].itemName" type="text" value="{{row.itemName}}" readonly="readonly"    class="form-control required"/>
					</td>
					
					
					<td>
						<input id="templanDepartmentListList{{idx}}_applyNumber" name="templanDepartmentListList[{{idx}}].applyNumber" type="text" value="{{row.applyNumber}}" readonly="readonly"    class="form-control required"/>
					</td>
					
					
					<td>
						<input id="templanDepartmentListList{{idx}}_applyovalNumber" name="templanDepartmentListList[{{idx}}].applyovalNumber" onkeyup="value=value.replace(/[^\d.]/g,'')" class="form-control required digits" type="text" value="{{row.applyovalNumber}}" aria-required="true">
					</td>
					
					
					<td>
						<input id="templanDepartmentListList{{idx}}_demandDate" type="text"  name="templanDepartmentListList[{{idx}}].demandDate" readonly="readonly" class="form-control required"  value="{{row.demandDate}}"/>						            
					</td>
					
					
					<td>
						<textarea id="templanDepartmentListList{{idx}}_remarks" name="templanDepartmentListList[{{idx}}].remarks" rows="2"  readonly="readonly"   class="form-control ">{{row.remarks}}</textarea>
					</td>
					
				</tr>//-->
			</script>
			<script type="text/javascript">
				var templanDepartmentListRowIdx = 0, templanDepartmentListTpl = $("#templanDepartmentListTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(templanDepartment.templanDepartmentListList)};
					for (var i=0; i<data.length; i++){
						addRow('#templanDepartmentListList', templanDepartmentListRowIdx, templanDepartmentListTpl, data[i]);
						templanDepartmentListRowIdx = templanDepartmentListRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>