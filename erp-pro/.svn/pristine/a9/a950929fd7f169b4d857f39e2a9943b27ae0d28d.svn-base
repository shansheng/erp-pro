<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>销售管理</title>
<meta name="decorator" content="ani" />
<script type="text/javascript">
	var validateForm;
	var $table; // 父页面table表格id
	var $topIndex;//弹出窗口的 index
	function doSubmit(table, index) {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		if (validateForm.form()) {
			$table = table;
			$topIndex = index;
			jp.loading();
			$("#inputForm").submit();
			return true;
		}

		return false;
	}

	$(document)
			.ready(
					function() {
						
						validateForm = $("#inputForm")
								.validate(
										{
											submitHandler : function(form) {
												jp
														.post(
																"${ctx}/sysmarket/sysMarket/save",
																$('#inputForm')
																		.serialize(),
																function(data) {
																	if (data.success) {
																		$table
																				.bootstrapTable('refresh');
																		jp
																				.success(data.msg);
																		jp
																				.close($topIndex);//关闭dialog

																	} else {
																		jp
																				.error(data.msg);
																	}
																})
											},
											errorContainer : "#messageBox",
											errorPlacement : function(error,
													element) {
												$("#messageBox").text(
														"输入有误，请先更正。");
												if (element.is(":checkbox")
														|| element.is(":radio")
														|| element
																.parent()
																.is(
																		".input-append")) {
													error.appendTo(element
															.parent().parent());
												} else {
													error.insertAfter(element);
												}
											}
										});

						$('#deliveryDate').datetimepicker({
							format : "YYYY-MM-DD"
						});
						$('#contractDate').datetimepicker({
							format : "YYYY-MM-DD"
						});
						$('#assignDate').datetimepicker({
							format : "YYYY-MM-DD"
						});

						//客户选择方法
						$("#clientIdButton, #clientIdName")
								.click(
										function() {
											if ($("#clientIdButton").hasClass(
													"disabled")) {
												return true;
											}

											top.layer
													.open({
														type : 2,
														area : [ '800px',
																'500px' ],
														title : "选择客户",
														auto : true,
														name : 'friend',
														content : "${ctx}/tag/gridselect?url="
																+ encodeURIComponent("${ctx}/client/client/data")
																+ "&fieldLabels="
																+ encodeURIComponent("客户编号|客户名")
																+ "&fieldKeys="
																+ encodeURIComponent("clientNo|clientName")
																+ "&searchLabels="
																+ encodeURIComponent("客户编号|客户名")
																+ "&searchKeys="
																+ encodeURIComponent("clientNo|clientName")
																+ "&isMultiSelected=false",
														btn : [ '确定', '关闭' ],
														yes : function(index,
																layero) {
															var iframeWin = layero
																	.find('iframe')[0].contentWindow; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
															var items = iframeWin
																	.getSelections();
															if (items == "") {
																jp
																		.warning("必须选择一条数据!");
																return;
															}
															var ids = [];
															var names = [];
															for (var i = 0; i < items.length; i++) {
																var item = items[i];
																ids
																		.push(item.id);
																names
																		.push(item.clientNo)
															}
															$("#clientIdId")
																	.val(
																			ids
																					.join(","));
															$("#clientIdName")
																	.val(
																			names
																					.join(","));

															//向后台发送请求
															var clientId = $(
																	"#clientIdId")
																	.val();
															$
																	.ajax({
																		url : "${ctx}/sysmarket/sysMarket/getClient",
																		data : {
																			clientId : clientId
																		},
																		success : function(
																				data) {
																			console
																					.log(data);
																			$(
																					"#clientName")
																					.val(
																							data.clientName);
																			$(
																					"#phone")
																					.val(
																							data.phone);
																			$(
																					"#clientManager")
																					.val(
																							data.clientManager);
																			$(
																					"#region")
																					.val(
																							data.region);
																			$(
																					"#address")
																					.val(
																							data.address);
																		}
																	})

															top.layer
																	.close(index);//关闭对话框。
														},
														cancel : function(index) {
														}
													});
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
	<form:form id="inputForm" modelAttribute="sysMarket"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<table class="table table-bordered">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>客户编码：</label></td>

					<td class="width-35">
						<!-- 客户选择----解决不触发change事件 --> <input id="clientIdId"
						name="clientId" type="hidden" value="${sysMarket.clientId }" />
						<div class="input-group" style="width: 100%">
							<input id="clientIdName" 
								readonly="readonly" type="text" value="${sysMarket.clientNo }"
								data-msg-required="" class="form-control required" style="" />
							<span class="input-group-btn">
								<button type="button" id="clientIdButton"
									class="btn   btn-primary  ">
									<i class="fa fa-search"></i>
								</button>
								<button type="button" id="clientIdDelButton" class="close"
									data-dismiss="alert"
									style="position: absolute; top: 5px; right: 53px; z-index: 999; display: block;">×</button>
							</span>

						</div> <label id="clientIdName-error" class="error" for="clientIdName"
						style="display: none"></label>
					</td>
					<td class="width-15 active"><label class="pull-right">客户名称：</label></td>
					<td class="width-35"><form:input id="clientName"
							path="clientName" htmlEscape="false"
							class="form-control " disabled="true" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">电话：</label></td>
					<td class="width-35"><form:input id="phone" path="phone"
							htmlEscape="false" class="form-control " disabled="true" /></td>
					<td class="width-15 active"><label class="pull-right">负责人：</label></td>
					<td class="width-35"><form:input id="clientManager"
							path="clientManager" htmlEscape="false"
							class="form-control " disabled="true" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">地区：</label></td>
					<td class="width-35">
						<div class=" input-group" style="width: 100%;">
							<form:input id="region" path="region" htmlEscape="false"
								class="form-control " disabled="true" />

						</div>
					</td>
					<td class="width-15 active"><label class="pull-right">地址：</label></td>
					<td class="width-35"><form:input id="address" path="address"
							htmlEscape="false" class="form-control " disabled="true" /></td>
				</tr>

			
				<tr>
					
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>合同编号：</label></td>
					<td class="width-35"><form:input path="contractNo"
							htmlEscape="false" class="form-control required" /></td>
							
							
			<%-- 	<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>合同类型：</label></td>
					<td class="width-35"><form:select path="contractType"
							class="form-control required">
							<form:option value="" label="---请选择---" />
							<form:options items="${fns:getDictList('contract_type')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select></td> --%>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>责任人：</label></td>
					<td class="width-35">
						<sys:userselect id="principal" name="principal" value="${sysMarket.principal}" labelName="" labelValue="${sysMarket.principalName}"
							    cssClass="form-control required"/>
					</td>		
				</tr>
				<tr>
				
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>合同签订时间：</label></td>
					<td class="width-35">
						<p class="input-group">
						<div class='input-group form_datetime' id='contractDate'>
							<input type='text' name="contractDate"
								class="form-control required"
								value="<fmt:formatDate value="${sysMarket.contractDate}" pattern="yyyy-MM-dd"/>" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
						</p>

					</td>
						<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>约定交货日期：</label></td>

					<td class="width-35">
						<p class="input-group">
						<div class='input-group form_datetime' id='deliveryDate'>
							<input type='text' name="deliveryDate"
								class="form-control required"
								value="<fmt:formatDate value="${sysMarket.deliveryDate}" pattern="yyyy-MM-dd"/>" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
						</p>

					</td>
					
				</tr>
				<tr>
			
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>合同状态：</label></td>
					<td class="width-35"><form:select path="contractStatus"
							class="form-control required">
							<form:option value="" label="---请选择---" />
							<form:options items="${fns:getDictList('contract_status')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select></td> 
					
						<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>付款方式：</label></td>
					<td class="width-35">
					<form:select path="paymentMethod"
							class="form-control required">
							<form:option value="" label="---请选择---" />
							<form:options items="${fns:getDictList('payment_method')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select></td>
				</tr>
				<tr>
				<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>金额(万)：</label></td>
					<td class="width-35"><form:input path="money"
							htmlEscape="false" onkeyup="value=value.replace(/[^\d.]/g,'')"
							class="form-control required" /></td>
						
				<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3">
					<%-- <textarea   name="remake"  style="resize:none;margin: 0px; height: 50px; width: 635px;">${sysMarket.remake}</textarea> --%>
					<form:input path="remake"
							htmlEscape="false" 
							class="form-control" />
					</td>
						<%-- <td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35"><form:input path="remake"
							htmlEscape="false" class="form-control" /></td> --%>
					
				</tr>
				
				<tr>
					
				</tr>
			</tbody>
		</table>
			<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">产品表：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<a class="btn btn-white btn-sm" onclick="addRow('#sysMarketListList', sysMarketListRowIdx, sysMarketListTpl);sysMarketListRowIdx = sysMarketListRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th style="width:15%;"><font color="red">*</font>产品</th>
						<th>规格/型号</th>
						<th>材质</th>
						<th style="width:10%;"><font color="red">*</font>数量</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="sysMarketListList">
				</tbody>
			</table>
			<script type="text/template" id="sysMarketListTpl">

//<!--
				<tr id="sysMarketListList{{idx}}">
					<td class="hide">
						<input id="sysMarketListList{{idx}}_id" name="sysMarketListList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="sysMarketListList{{idx}}_delFlag" name="sysMarketListList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					
					
				
					<td ><sys:itemSelectForForm url="${ctx}/mdsitem/mdsItem/data?itemType=G,A" id="sysMarketListList{{idx}}_mdsItem" name="sysMarketListList[{{idx}}].mdsItemId" value="{{row.mdsItemId}}" labelName="mdsItem.itemName" labelValue="{{row.itemName}}"
							 title="选择产品" cssClass="form-control required" fieldLabels="产品号|产品名|规格/型号|材质" fieldKeys="itemCode|itemName|specs|modelNum" searchLabels="产品号|产品名" searchKeys="itemCode|itemName" ></sys:itemSelectForForm></td>
					

					<td>
						<input id="sysMarketListList{{idx}}_mdsItemspecs" name="mdsItem.specs" type="text" value="{{row.itemSpecs}}"    class="form-control " readonly="true"/>
					</td>
					<td>
						<input id="sysMarketListList{{idx}}_mdsItemmodelNum" name="mdsItem.modelNum" type="text" value="{{row.modelNum}}"    class="form-control " readonly="true"/>
					</td>
					<td>
						<input id="sysMarketListList{{idx}}_num" name="sysMarketListList[{{idx}}].num" type="text" value="{{row.num}}" onkeyup="value=value.replace(/[^\d.]/g,'')"    class="form-control required number"/>
					</td>
					
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#sysMarketListList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var sysMarketListRowIdx = 0, sysMarketListTpl = $("#sysMarketListTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(sysMarket.sysMarketListList)};
					for (var i=0; i<data.length; i++){
						addRow('#sysMarketListList', sysMarketListRowIdx, sysMarketListTpl, data[i]);
						sysMarketListRowIdx = sysMarketListRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
	</form:form>
</body>
</html>