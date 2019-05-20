<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>出库单管理</title>
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
					jp.post("${ctx}/wmsinvoutbill/wmsInvOutBill/save",$('#inputForm').serialize(),function(data){
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
					});
				}
			});
			
			$('#wmsInventory_id').change(function(){
				//$('#wmsInvOutBillListList').;
			});
		});
		
		//刷新库位下拉菜单
		function refreshWmsLocator(wmsInventoryId){
			$.get("${ctx}/wmslocator/wmsLocator/getDataByWmsinventoryId?wmsInventoryId=" + wmsInventoryId, function(data){
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
		//出库单申请单子表 选择库位上的物料
		function selectItem(idx){
			var wmsInventoryId = $("[id='wmsInventory.id']").val();
			var wmsInventoryName = $('#wmsInventory_id').find("option:selected").text();
			if(wmsInventoryId == ''){
				alert('请选择库房');
			}else{
			  openDialog('选择物料', "${ctx}/wmsiteminventory/wmsItemInventory/list?wmsInventoryNameId=" + wmsInventoryId + '&wmsInventoryNameName=' + wmsInventoryName,'800px', '500px', idx);
			}
		}
		//选择物料方法
		function openDialog(title,url,width,height, idx){
			var auto = true;//是否使用响应式，使用百分比时，应设置为false
			if(width.indexOf("%")>=0 || height.indexOf("%")>=0 ){
				auto =false;
			}
		  	top.layer.open({
		  	    type: 2,  
		  	    area: [width, height],
		  	    title: title,
		  	    auto:auto,
		  	    maxmin: true, //开启最大化最小化按钮
		  	    content: url ,
		  	    btn: ['确定', '关闭'],
		  	  	idx: idx,
		  	    yes: function(index, layero){
		  	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
		  	         var row = iframeWin.contentWindow.getSelections();
		  	         
		  	         if(!!row[0]){
		  	        	 //console.log(row[0]);
			  	         $('#wmsInvOutBillListList' + idx + '_mdsItemId').val(row[0].itemId);
			  	         $('#wmsInvOutBillListList' + idx + '_mdsItemCode').val(row[0].itemCode);
			  	      	 $('#wmsInvOutBillListList' + idx + '_wmsLocatorId').val(row[0].locatorId);
			  	         $('#wmsInvOutBillListList' + idx + '_wmsLocatorCode').text(row[0].locatorCode);
			  	         $('#wmsInvOutBillListList' + idx + '_wmsItemInventoryId').val(row[0].id);
			  	 		 $('#wmsInvOutBillListList' + idx + '_canUseQty').text(row[0].canUseQty);
			  	 		 
			  	 		 setwmsInventoryDropdownReadonly();
		  	         }
		  	         top.layer.close(index);//关闭对话框
		  		},
		  	    cancel: function(index){ 
		  	    }
		  	}); 
		  }
		
		  //设置库房下拉只读
		  function setwmsInventoryDropdownReadonly(){
			  $('#wmsInventory_id').attr('onfocus','this.defaultIndex=this.selectedIndex;')
				 .attr('onchange','this.selectedIndex=this.defaultIndex;');
		  }
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="wmsInvOutBill" action="${ctx}/wmsinvoutbill/wmsInvOutBill/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>申请单号：</label></td>
					<td class="width-35">
						<form:input path="billNo" htmlEscape="false" class="form-control required" readonly="true"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>出库类型：</label></td>
					<td class="width-35">
						<form:select path="billType" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('wms_out_bill_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">出库申请单子表：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<a class="btn btn-white btn-sm" onclick="addRow('#wmsInvOutBillListList', wmsInvOutBillListRowIdx, wmsInvOutBillListTpl);wmsInvOutBillListRowIdx = wmsInvOutBillListRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th><font color="red">*</font>物料</th>
						<th style="width:60px;"><font color="red">*</font>库位</th>
						<th>质量编号</th>
						<th>供方合格证号</th>
						<th>可用数量</th>
						<th><font color="red">*</font>申请数量</th>
						<th><font color="red">*</font>领用人</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="wmsInvOutBillListList">
				</tbody>
			</table>
			<script type="text/template" id="wmsInvOutBillListTpl">//<!--
				<tr id="wmsInvOutBillListList{{idx}}">
					<td class="hide">
						<input id="wmsInvOutBillListList{{idx}}_id" name="wmsInvOutBillListList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="wmsInvOutBillListList{{idx}}_delFlag" name="wmsInvOutBillListList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						<input id="wmsInvOutBillListList{{idx}}_mdsItemId" name="wmsInvOutBillListList[{{idx}}].mdsItemId" type="text" readonly="true" style="display:none;" value="{{row.mdsItemId}}"/>
						<input id="wmsInvOutBillListList{{idx}}_mdsItemCode" name="wmsInvOutBillListList{{idx}}_mdsItemCode" type="text" readonly="true" value="{{row.itemCode}}" onclick="selectItem({{idx}})"/>
						
						<input id="wmsInvOutBillListList{{idx}}_wmsItemInventoryId" name="wmsInvOutBillListList[{{idx}}].wmsItemInventoryId" type="text" readonly="true" style="display:none;" value="{{row.wmsItemInventoryId}}"/>
					</td>
					
					<td>
						<input flag="wmsLocatorId" id="wmsInvOutBillListList{{idx}}_wmsLocatorId" name="wmsInvOutBillListList[{{idx}}].wmsLocatorId" type="text" value="{{row.wmsLocatorId}}" style="display:none;" class="form-control required" />
						<span id="wmsInvOutBillListList{{idx}}_wmsLocatorCode">{{row.wmsLocatorCode}}</span>
					</td>
					
					<td>
						<input id="wmsInvOutBillListList{{idx}}_qualityNo" name="wmsInvOutBillListList[{{idx}}].qualityNo" type="text" value="{{row.qualityNo}}"    class="form-control "/>
					</td>
					
					
					<td>
						<input id="wmsInvOutBillListList{{idx}}_supplyRegularNo" name="wmsInvOutBillListList[{{idx}}].supplyRegularNo" type="text" value="{{row.supplyRegularNo}}"    class="form-control "/>
					</td>
					
					<td>
						<span id="wmsInvOutBillListList{{idx}}_canUseQty">{{row.canUseQty}}</span>
					</td>

					<td>
						<input id="wmsInvOutBillListList{{idx}}_applyQty" name="wmsInvOutBillListList[{{idx}}].applyQty" type="text" value="{{row.applyQty}}"    class="form-control required"/>
					</td>
					
					<td>
						<sys:userselect id="wmsInvOutBillListList{{idx}}_leaderUserId" name="wmsInvOutBillListList[{{idx}}].leaderUserId" value="{{row.leaderUserId}}" labelName="wmsInvOutBillListList[{{idx}}].leaderSysUserName" labelValue="{{row.leaderSysUserName}}" cssClass="form-control required" isMultiSelected="false"/>
					</td>
					
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#wmsInvOutBillListList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var wmsInvOutBillListRowIdx = 0, wmsInvOutBillListTpl = $("#wmsInvOutBillListTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(wmsInvOutBill.wmsInvOutBillListList)};
					for (var i=0; i<data.length; i++){
						addRow('#wmsInvOutBillListList', wmsInvOutBillListRowIdx, wmsInvOutBillListTpl, data[i]);
						wmsInvOutBillListRowIdx = wmsInvOutBillListRowIdx + 1;
					}
					if(data.length > 0){
						setwmsInventoryDropdownReadonly();
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>