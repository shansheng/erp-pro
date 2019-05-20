<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>入库单管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
		var wmsInventoryId = '';//库房id
		
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
			//校验
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					jp.post("${ctx}/wmsinvinbill/wmsInvInBill/save",$('#inputForm').serialize(),function(data){
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
			
			//库房下拉带来的一系列
			$.get("${ctx}/wmsinventory/wmsInventory/data", function(data){
				if(data.total != 0){
					for(var i=0; i<data.total; i++){
						if(data.rows[i].id == $("[id='wmsInventory.id']").val()){
							$('#wmsInventory_id').append($('<option selected="selected" value="' + data.rows[i].id + '">' + data.rows[i].inventoryName + '</option>'));
						}else{
							$('#wmsInventory_id').append($('<option value="' + data.rows[i].id + '">' + data.rows[i].inventoryName + '</option>'));
						}
					}
					
					$('#wmsInventory_id').change(function(){
						//更改主表库房id
						$("[id='wmsInventory.id']").val($('#wmsInventory_id').val());
						//子表库位制空
						$('[flag=wmsLocatorId]').each(function(){
							$(this).val('');
						});
						//刷新子表库位菜单
						refreshWmsLocator($('#wmsInventory_id').val());
					});
				}
		     });
			//刷新库位下拉菜单
			refreshWmsLocator($("[id='wmsInventory.id']").val());
		});
		
		//刷新库位下拉菜单
		function refreshWmsLocator(wmsInventoryId){
			$.get("${ctx}/wmslocator/wmsLocator/getDataByWmsinventoryId?wmsInventoryId=" + wmsInventoryId, function(data){
				/*
				console.log('---------');
				console.log(data);
				console.log('---------');
				*/
				//初始化，绑定事件
				$('[flag=wmsLocatorId_select]').each(function(){
					$(this).html('<option value=""></option>')
						   .on('change',function(){
							   	$('#' + $(this).attr('id').replace('_select','')).val($(this).val());
						   });
				});
				if(data.total != 0){
					var optionValue = '';
					for(var i=0; i<data.total; i++){
						optionValue = optionValue + '<option value="' + data.rows[i].id + '">' + data.rows[i].locatorCode + '</option>';
					}
					$('[flag=wmsLocatorId_select]').each(function(){
						$(this).append($(optionValue))
							   .val($('#' + $(this).attr('id').replace('_select','')).val());
					});
				}
			});
		}
		
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
			
			//刷新库位下拉菜单
			refreshWmsLocator($("[id='wmsInventory.id']").val());
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
		<form:form id="inputForm" modelAttribute="wmsInvInBill" action="${ctx}/wmsinvinbill/wmsInvInBill/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>申请单号：</label></td>
					<td class="width-35">
						<form:input path="billNo" htmlEscape="false" class="form-control required" readonly="true"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>入库类型：</label></td>
					<td class="width-35">
						<form:select path="billType"  class="form-control m-b">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('wms_in_bill_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>库房：</label></td>
					<td class="width-35">
						<form:input path="wmsInventory.id" style="display:none;" htmlEscape="false" class="form-control required"/>
						<select id="wmsInventory_id" name="wmsInventory_id" class="form-control required m-b">
							<option value=""></option>
						</select>
					</td>
					<td class="width-15 active"><label class="pull-right">备注信息：</label></td>
					<td class="width-35">
						<form:textarea path="remarks" htmlEscape="false" rows="4"    class="form-control "/>
					</td>
				</tr>
		 	</tbody>
		</table>
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">入库申请单子表：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<a class="btn btn-white btn-sm" onclick="addRow('#wmsInvInBillListList', wmsInvInBillListRowIdx, wmsInvInBillListTpl);wmsInvInBillListRowIdx = wmsInvInBillListRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th style="width:15%;"><font color="red">*</font>库位</th>
						<th><font color="red">*</font>物料</th>
						<th>质量编号</th>
						<th style="width:10%;"><font color="red">*</font>数量</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="wmsInvInBillListList">
				</tbody>
			</table>
			<script type="text/template" id="wmsInvInBillListTpl">//<!--
				<tr id="wmsInvInBillListList{{idx}}">
					<td class="hide">
						<input id="wmsInvInBillListList{{idx}}_id" name="wmsInvInBillListList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="wmsInvInBillListList{{idx}}_delFlag" name="wmsInvInBillListList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						<input flag="wmsLocatorId" id="wmsInvInBillListList{{idx}}_wmsLocatorId" name="wmsInvInBillListList[{{idx}}].wmsLocatorId" type="text" value="{{row.wmsLocatorId}}"  class="form-control required" style="display:none;"/>
						<select flag="wmsLocatorId_select" id="wmsInvInBillListList{{idx}}_wmsLocatorId_select" name="wmsInvInBillListList{{idx}}_wmsLocatorId_select" class="form-control required m-b">
							<option value=""></option>
						</select>
					</td>
					
					<td>
						<sys:gridselect url="${ctx}/mdsitem/mdsItem/data" id="wmsInvInBillListList{{idx}}_mdsItemId" name="wmsInvInBillListList[{{idx}}].mdsItemId" value="{{row.mdsItemId}}" labelName="mdsItem.itemCode" labelValue="{{row.itemCode}}"
							 title="选择物料" cssClass="form-control required" fieldLabels="物料代码|物料名称|物料版本|物料类型" fieldKeys="itemCode|itemName|itemVersion|itemType" searchLabels="物料代码|物料名称|物料类型" searchKeys="itemCode|itemName|itemType" ></sys:gridselect>
					</td>
					
					<td>
						<input id="wmsInvInBillListList{{idx}}_qualityNo" name="wmsInvInBillListList[{{idx}}].qualityNo" type="text" value="{{row.qualityNo}}"    class="form-control "/>
					</td>
					
					<td>
						<input id="wmsInvInBillListList{{idx}}_applyQty" name="wmsInvInBillListList[{{idx}}].applyQty" type="text" value="{{row.applyQty}}"    class="form-control required number"/>
					</td>
					
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#wmsInvInBillListList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var wmsInvInBillListRowIdx = 0, wmsInvInBillListTpl = $("#wmsInvInBillListTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(wmsInvInBill.wmsInvInBillListList)};
					for (var i=0; i<data.length; i++){
						addRow('#wmsInvInBillListList', wmsInvInBillListRowIdx, wmsInvInBillListTpl, data[i]);
						wmsInvInBillListRowIdx = wmsInvInBillListRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>