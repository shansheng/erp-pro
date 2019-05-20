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
	
	

	$(document).ready(function() {
		validateForm = $("#inputForm").validate({
			submitHandler : function(form) {
						$table.bootstrapTable('refresh');
						jp.close($topIndex);//关闭dialog
				},
				errorContainer : "#messageBox",
				errorPlacement : function(error,element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")|| element.is(":radio")|| element.parent().is(".input-append")) {
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}

				$('#deliveryDate').datetimepicker({
					format : "YYYY-MM-DD"
				});
				$('#contractDate').datetimepicker({
					format : "YYYY-MM-DD"
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
					<td class="width-15 active"><label class="pull-right">合同编号：</label></td>
					<td class="width-35">
						<form:input path="contractNo" htmlEscape="false" class="form-control required" disabled="true"/>
					</td>
					<td class="width-15 active"><label class="pull-right">是否发货：</label></td>
					<td class="width-35" >
						<form:select path="shipments" class="form-control required" disabled="true">
							<form:options items="${fns:getDictList('shipments')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">合同签订时间：</label></td>
					<td class="width-35">
						<p class="input-group">
						<div class='input-group form_datetime' id='contractDate'>
							<input type='text' name="contractDate"
								class="form-control required" disabled="true"
								value="<fmt:formatDate value="${sysMarket.contractDate}" pattern="yyyy-MM-dd"/>" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
						</p>

					</td>
					<td class="width-15 active"><label class="pull-right">交货日期：</label></td>

					<td class="width-35">
						<p class="input-group">
						<div class='input-group form_datetime' id='deliveryDate'>
							<input type='text' name="deliveryDate" class="form-control required" disabled="true"
								value="<fmt:formatDate value="${sysMarket.deliveryDate}" pattern="yyyy-MM-dd"/>" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
						</p>

					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">责任人：</label></td>
					<td class="width-35"><form:input path="principalName"
							htmlEscape="false" disabled="true" class="form-control required" />
					</td>
					<td class="width-15 active"></td>
					<td class="width-35"></td>
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
						<th style="width:20%;">产品</th>
						<th>规格/型号</th>
						<th>材质</th>
						<th style="width:10%;">数量</th>
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
					</td>
					<td >
						<input id="sysMarketListList{{idx}}_mdsItemspecs" name="mdsItem.specs" type="text" value="{{row.itemName}}"    class="form-control " readonly="true"/>
					
					<td>
						<input id="sysMarketListList{{idx}}_mdsItemspecs" name="mdsItem.specs" type="text" value="{{row.itemSpecs}}"    class="form-control " readonly="true"/>
					</td>
					<td>
						<input id="sysMarketListList{{idx}}_mdsItemmodelNum" name="mdsItem.modelNum" type="text" value="{{row.modelNum}}"    class="form-control " readonly="true"/>
					</td>
					<td>
						<input id="sysMarketListList{{idx}}_num" name="sysMarketListList[{{idx}}].num" type="text" value="{{row.num}}"    class="form-control" readonly="true"/>
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
			
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