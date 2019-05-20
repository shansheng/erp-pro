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
																"${ctx}/sysmarketmilestone/sysMarket/save",
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
						
						
				        
					/* 	//客户选择方法
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
																		url : "${ctx}/sysmarketmilestone/sysMarket/getClient",
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
										}); */

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

function dateCheck(id){
	
	var SkillStartDate=$("#SkillStartDate").val();
	var SkillEndDate=$("#SkillEndDate").val();
	if(SkillStartDate!='' &SkillEndDate!=''){
		if(SkillStartDate>SkillEndDate){
			jp.error("开始时间不能大于结束时间!");
			$("#"+id).val('');
			return;
		}
	}
	
	
	
}
function purdateCheck(id){
	
	var purchaseStartDate=$("#purchaseStartDate").val();
	var purchaseEndDate=$("#purchaseEndDate").val();
	if(purchaseStartDate!='' &purchaseEndDate!=''){
	if(purchaseStartDate>purchaseEndDate){
		jp.error("开始时间不能大于结束时间!");
		$("#"+id).val('');
		return;
	}
	}
}
function prodateCheck(id){
	
	var productionStartDate=$("#productionStartDate").val();
	var productionEndDate=$("#productionEndDate").val();
	if(productionStartDate!='' &productionEndDate!=''){
	if(productionStartDate>productionEndDate){
		jp.error("开始时间不能大于结束时间!");
		$("#"+id).val('');
		return;
	}
	}
}
</script>
</head>
<body class="bg-white">
	<form:form id="inputForm" modelAttribute="sysmarketmilestone"
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
						name="clientId" type="hidden" value="${sysmarketmilestone.clientId }" />
						<div class="input-group" style="width: 100%">
							<input id="clientIdName" 
								readonly="readonly" type="text" value="${sysmarketmilestone.clientNo }"
								data-msg-required="" class="form-control required" style="" />
							

						</div> <label id="clientIdName-error" class="error" for="clientIdName"
						style="display: none"></label>
					</td>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>客户名称：</label></td>
					<td class="width-35"><form:input id="clientName"
							path="clientName" htmlEscape="false"
							class="form-control required" disabled="true" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>电话：</label></td>
					<td class="width-35"><form:input id="phone" path="phone"
							htmlEscape="false" class="form-control required" disabled="true" /></td>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>负责人：</label></td>
					<td class="width-35"><form:input id="clientManager"
							path="clientManager" htmlEscape="false"
							class="form-control required" disabled="true" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>地区：</label></td>
					<td class="width-35">
						<div class=" input-group" style="width: 100%;">
							<form:input id="region" path="region" htmlEscape="false"
								class="form-control required" disabled="true" />

						</div>
					</td>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>地址：</label></td>
					<td class="width-35"><form:input id="address" path="address"
							htmlEscape="false" class="form-control required" disabled="true" /></td>
				</tr>

			
				<tr>
					
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>合同编号：</label></td>
					<td class="width-35"><form:input path="contractNo" disabled="true"
							htmlEscape="false" class="form-control required" /></td>
							<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>订单类型：</label></td>
					<td class="width-35"><form:select path="contractClass" disabled="true"
							class="form-control required">
							<form:option value="" label="---请选择---" />
							<form:options items="${fns:getDictList('contract_class')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>合同签订时间：</label></td>
					<td class="width-35">
						<p class="input-group">
						<div class='input-group form_datetime' id='contractDate'>
							<input type='text' name="contractDate" disabled="disabled"
								class="form-control required"
								value="<fmt:formatDate value="${sysmarketmilestone.contractDate}" pattern="yyyy-MM-dd"/>" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
						</p>

					</td>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>交货日期：</label></td>

					<td class="width-35">
						<p class="input-group">
						<div class='input-group form_datetime' id='deliveryDate'>
							<input type='text' name="deliveryDate" disabled="disabled"
								class="form-control required"
								value="<fmt:formatDate value="${sysmarketmilestone.deliveryDate}" pattern="yyyy-MM-dd"/>" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
						</p>

					</td>
				</tr>
				

				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>技术开始时间：</label></td>
					<td class="width-35">
					<input type="date" name="skillStartDate" id="SkillStartDate" readonly="readonly" onchange="dateCheck('SkillStartDate')" class="form-control required"  value="<fmt:formatDate value="${sysmarketmilestone.skillStartDate}" pattern="yyyy-MM-dd"/>"> 
					
						
					</td>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>技术结束时间：</label></td>
					<td class="width-35">
					<input type="date" name="skillEndDate" id="SkillEndDate" readonly="readonly" onchange="dateCheck('SkillEndDate')" class="form-control required"  value="<fmt:formatDate value="${sysmarketmilestone.skillEndDate}" pattern="yyyy-MM-dd"/>"> 
					
						
						
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>采购开始时间：</label></td>
					<td class="width-35">
					<input type="date" name="purchaseStartDate" readonly="readonly" id="purchaseStartDate" onchange="purdateCheck('purchaseStartDate')" class="form-control required"  value="<fmt:formatDate value="${sysmarketmilestone.purchaseStartDate}" pattern="yyyy-MM-dd"/>"> 
						
					</td>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>采购完成时间：</label></td>
					<td class="width-35">
										<input type="date" readonly="readonly" name="purchaseEndDate" onchange="purdateCheck('purchaseEndDate')" id="purchaseEndDate" class="form-control required"  value="<fmt:formatDate value="${sysmarketmilestone.purchaseEndDate}" pattern="yyyy-MM-dd"/>"> 
						
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>生产开始时间：</label></td>
					<td class="width-35">
										<input type="date" readonly="readonly" name="productionStartDate" onchange="prodateCheck('productionStartDate')" id="productionStartDate" class="form-control required"  value="<fmt:formatDate value="${sysmarketmilestone.productionStartDate}" pattern="yyyy-MM-dd"/>"> 
						
					</td>
					<td class="width-15 active"><label class="pull-right"><font
							color="red">*</font>生产结束时间：</label></td>
					<td class="width-35">
										<input type="date" readonly="readonly" name="productionEndDate" onchange="prodateCheck('productionEndDate')" id="productionEndDate" class="form-control required"  value="<fmt:formatDate value="${sysmarketmilestone.productionEndDate}" pattern="yyyy-MM-dd"/>"> 
						
					</td>
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

			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th style="width:15%;"><font color="red">*</font>产品</th>
						<th>规格/型号</th>
						<th>材质</th>
						<th style="width:10%;"><font color="red">*</font>数量</th>
						
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
					
					
					
				
					<td ><input id="sysMarketListList{{idx}}_mdsItem" name="mdsItem.name" type="text" value="{{row.itemName}}"    class="form-control " readonly="true"/>
					<td>
						<input id="sysMarketListList{{idx}}_mdsItemspecs" name="mdsItem.specs" type="text" value="{{row.itemSpecs}}"    class="form-control " readonly="true"/>
					</td>
					<td>
						<input id="sysMarketListList{{idx}}_mdsItemmodelNum" name="mdsItem.modelNum" type="text" value="{{row.modelNum}}"    class="form-control " readonly="true"/>
					</td>
					<td>
						<input id="sysMarketListList{{idx}}_num" name="sysMarketListList[{{idx}}].num" type="text" value="{{row.num}}"    class="form-control required number" readonly="true" />
					</td>
					
					
				</tr>//-->
			</script>
			<script type="text/javascript">
				var sysMarketListRowIdx = 0, sysMarketListTpl = $("#sysMarketListTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(sysmarketmilestone.sysMarketListList)};
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