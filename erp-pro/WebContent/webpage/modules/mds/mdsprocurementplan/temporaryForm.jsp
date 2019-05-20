<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>采购发货管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
		var validateForm;
		var $table; // 父页面table表格id
		var $topIndex;//弹出窗口的 index
		function doSubmit(table, index){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $table = table;
			  $topIndex = index;
			  $("#inputForm").submit();
			  return true;
		  }
		  return false;
		}

		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
	                  $table.bootstrapTable('refresh');
	                  jp.close($topIndex);//关闭dialog
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
		<form:form id="inputForm" modelAttribute="templanProcurement" action="${ctx}/templan/templanprocurement/templanProcurement/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
		   <tr>
		   		<td class="width-15 active"><label class="pull-right"><font color="red">*</font>申请人：</label></td>
		   		<td class="width-35" colspan="2">
					<form:input path="createBy.name" htmlEscape="false" readonly="true"    class="form-control"/>
				</td>
				<td class="width-15 active"><label class="pull-right"><font color="red">*</font>申请时间：</label></td>
		   		<td colspan="3">
					<div class='input-group form_datetime' style="width: 251px" id='createDate'>
						<input type='text' name="createDate" readonly="true"  class="form-control" value="<fmt:formatDate value="${templanProcurement.createDate}" pattern="yyyy-MM-dd"/>" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</td>
		   </tr>
		   <tr>
				<td class="width-15 active"><label class="pull-right"><font color="red">*</font>申请单号：</label></td>
				<td class="width-35" colspan="2">
					<form:input path="code" htmlEscape="false" readonly="true"    class="form-control"/>
				</td>
				<td class="width-15 active"><label class="pull-right">状态：</label></td>
				<td colspan="3">
					<form:select path="state" disabled="true"   class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('temporary_plan_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">申请原因：</label></td>
				<td colspan="6">
					<form:textarea path="remarks" htmlEscape="false" rows="2" readonly="true"    class="form-control "/>
				</td>
		  	</tr>
		  	<tr>
				<td class="width-15 active"><label class="pull-right"><font color="red">*</font>部门审核人：</label></td>
				<td class="width-25">
					<form:input path="departmentAssessingName" htmlEscape="false" readonly="true"    class="form-control"/>
				</td>
				<td class="width-14 active"><label class="pull-right">状态：</label></td>
				<td  colspan="2">
					<form:select path="departmentAssessingState" disabled="true"   class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('templan_department_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				</td>
				<td class="width-10 active"><label class="pull-right">审批时间：</label></td>
				<td class="width-40">
					<div class='input-group form_datetime' id='departmentAssessingDate'>
						<input type='text' name="departmentAssessingDate" readonly="true" class="form-control" value="<fmt:formatDate value="${templanProcurement.departmentAssessingDate}" pattern="yyyy-MM-dd"/>" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right"><font color="red">*</font>副总审核人：</label></td>
				<td class="width-25">
					<form:input path="deputyAssessingName" htmlEscape="false" readonly="true"    class="form-control"/>
				</td>
				<td class="width-14 active"><label class="pull-right">状态：</label></td>
				<td colspan="2">
					<form:select path="deputyAssessingState" disabled="true"   class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('templan_deputy_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				</td>
				<td class="width-10 active"><label class="pull-right">审批时间：</label></td>
				<td class="width-40">
					<div class='input-group form_datetime' id='deputyAssessingDate'>
						<input type='text' name="deputyAssessingDate" readonly="true" class="form-control" value="<fmt:formatDate value="${templanProcurement.deputyAssessingDate}" pattern="yyyy-MM-dd"/>" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
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
				<shiro:hasPermission name="templan:templanprocurement:templanProcurement:deliver">
			</shiro:hasPermission>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th><font color="red">*</font>物料编号</th>
						<th><font color="red">*</font>物料名称</th>
						<th><font color="red">*</font>申请数量</th>
						<th><font color="red">*</font>审批数量</th>
						<th><font color="red">*</font>需求日期</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody id="templanProcurementListList">
				</tbody>
			</table>
			<script type="text/template" id="templanProcurementListTpl">//<!--
				<tr id="templanProcurementListList{{idx}}">
					<td class="hide">
						<input id="templanProcurementListList{{idx}}_id" name="templanProcurementListList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="templanProcurementListList{{idx}}_delFlag" name="templanProcurementListList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						<input id="templanProcurementListList{{idx}}_itemCode" name="templanProcurementListList[{{idx}}].itemCode" type="text" value="{{row.itemCode}}" readonly="true"     class="form-control required"/>
					</td>

					<td>
						<input id="templanProcurementListList{{idx}}_itemName" name="templanProcurementListList[{{idx}}].itemName" type="text" value="{{row.itemName}}" readonly="true"     class="form-control required"/>
					</td>
					
					
					<td>
						<input id="templanProcurementListList{{idx}}_applyNumber" name="templanProcurementListList[{{idx}}].applyNumber" type="text" value="{{row.applyNumber}}" readonly="true"     class="form-control required"/>
					</td>
					
					
					<td>
						<input id="templanProcurementListList{{idx}}_applyovalNumber" name="templanProcurementListList[{{idx}}].applyovalNumber" type="text" value="{{row.applyovalNumber}}" readonly="true"     class="form-control required"/>
					</td>
					
					
					<td>
						<div class='input-group form_datetime' id="templanProcurementListList{{idx}}_demandDate">
		                    <input type='text'  name="templanProcurementListList[{{idx}}].demandDate" class="form-control required" readonly="true"   value="{{row.demandDate}}"/>
		                </div>						            
					</td>
					
					
					<td>
						<textarea id="templanProcurementListList{{idx}}_remarks" name="templanProcurementListList[{{idx}}].remarks" rows="2" readonly="true"     class="form-control ">{{row.remarks}}</textarea>
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var templanProcurementListRowIdx = 0, templanProcurementListTpl = $("#templanProcurementListTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(templanProcurement.templanProcurementListList)};
					for (var i=0; i<data.length; i++){
						addRow('#templanProcurementListList', templanProcurementListRowIdx, templanProcurementListTpl, data[i]);
						templanProcurementListRowIdx = templanProcurementListRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>