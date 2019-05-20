<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>临时计划申请表管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
		var validateForm;
		var $table; // 父页面table表格id
		var $topIndex;//弹出窗口的 index
		function doSubmit(table, index){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $table = table;
			  $topIndex = index;
			  /* jp.loading(); */
			  $("#inputForm").submit();
			  return true;
		  }
		  return false;
		}

		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					jp.post("${ctx}/mds/mdsprojectpcmplan/addProcurement",$('#inputForm').serialize(),function(data){
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
			$('#deliveryDate').datetimepicker({
				 
				format : "YYYY-MM-DD"
			});
			$('#purchaseStartDate').datetimepicker({
				 
				format : "YYYY-MM-DD"
			});
			$('#purchaseEndDate').datetimepicker({
				
				format : "YYYY-MM-DD"
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
		 function change(){
			 //修改全部时间
			var AllDate=$("#purchaseEndDateStart").val();
			 //采购完成时间
			var purchaseEndDate=$("#purchaseEndDate").val();
			var converted = Date.parse(purchaseEndDate);  
		    var myDate = new Date(converted); 
				if(purchaseEndDate!='' && AllDate!=''){
					/* if(AllDate>purchaseEndDate){
						jp.error("采购时间不能大于采购结束时间!");
						$("#purchaseEndDateStart").val(purchaseEndDate)
						return;
					}else */ 
					if(new Date()>myDate){
						jp.error("采购时间不能小于当前时间!");
						$("#purchaseEndDateStart").val(purchaseEndDate)
						return;
					}else{
						$(mdsProjectQuotaList).find(".form_datetime").each(function(index,element){
							$(this).find("input")[0].value = AllDate;
						})
					}
				}else if(AllDate!=''){
					$(mdsProjectQuotaList).find(".form_datetime").each(function(index,element){
						$(this).find("input")[0].value = AllDate;
					})
				}
			
		 }
		
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="mdsProjectpcmplan"  class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
		   		<tr class="hide">
		   			<td class="hide">
		   				<form:input path="sysMarketListId" htmlEscape="false"  readonly="true" type="hidden"   class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">合同编号：</label></td>
					<td class="width-35">
						<form:input path="contractNo" htmlEscape="false"  readonly="true"   class="form-control"/>
					</td>
					<td class="width-15 active"><label class="pull-right">交货日期：</label></td>
					<td class="width-35">
						<%-- <form:input path="deliveryDate" htmlEscape="false" readonly="true"    class="form-control required"/> --%>
						<input id="contractDate" name="contractDate" class="form-control" readonly="readonly" type="text" value="<fmt:formatDate value="${mdsProjectpcmplan.deliveryDate}" pattern="yyyy-MM-dd"/>" aria-required="true">
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">采购开始时间：</label></td>
					<td class="width-35">
						<input id="purchaseStartDate" name="purchaseStartDate" class="form-control" readonly="readonly" type="text" value="<fmt:formatDate value="${mdsProjectpcmplan.purchaseStartDate}" pattern="yyyy-MM-dd"/>" aria-required="true">
					</td>
					<td class="width-15 active"><label class="pull-right">采购完成时间：</label></td>
					<td class="width-35">
						<input id="purchaseEndDate" name="purchaseEndDate" class="form-control" readonly="readonly" type="text" value="<fmt:formatDate value="${mdsProjectpcmplan.purchaseEndDate}" pattern="yyyy-MM-dd"/>" aria-required="true">
					</td>
				</tr>
		 	</tbody>
		</table>
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">项目采购物料表：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th colspan="2" align="center">
							<label style="height: 34px;padding: 8px 15px"><font color="red">*</font>修改全部需求时间：</label>
						</th>
						<th colspan="2">
							<input type="date" name="purchaseEndDateStart" id="purchaseEndDateStart" onchange="change('purchaseEndDateStart')" class="form-control"  
							value="<fmt:formatDate value="${mdsProjectpcmplan.purchaseEndDate}" pattern="yyyy-MM-dd"/>"> 
					
						</th>
						<th colspan="8">
					</th>
					</tr>
					
					<tr>
						<th class="hide"></th>
						<th style="width:9%;">分类</th>
						<th style="width:10%;">物料编号</th>
						<th style="width:10%;">物料名称</th>
						<th>标准号</th>
						<th>材质</th>
						<th>规格/型号</th>
						<th>需求数量</th>
						<th>库存数量</th>
						<!-- <th>可使用库存</th> -->
						<!-- 
							<td>
						<input id="mdsProjectQuotaList{{idx}}_usable" name="mdsProjectQuotaList[{{idx}}].usable" type="text" value="{{row.usable}}"    class="form-control" readonly="readonly"/>
					</td>
						 -->
						<th><font color="red">*</font>采购数量</th>
						<th style="width:13%;"><font color="red">*</font>需求时间</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody id="mdsProjectQuotaList">
				</tbody>
			</table>
			<script type="text/template" id="mdsProjectQuotaListTpl">//<!--
				<tr id="mdsProjectQuotaList{{idx}}">
					<td class="hide">
						<input id="mdsProjectQuotaList{{idx}}_sysMarketListId" name="mdsProjectQuotaList[{{idx}}].sysMarketListId" type="hidden" value="{{row.sysMarketListId}}"/>
					</td>
					<td class="hide">
						<input id="mdsProjectQuotaList{{idx}}_itemId" name="mdsProjectQuotaList[{{idx}}].itemId" type="hidden" value="{{row.itemId}}"/>
					</td>
					
					<td>
						<input id="mdsProjectQuotaList{{idx}}_classify" name="mdsProjectQuotaList[{{idx}}].classify"  value="{{row.classify}}"  class="form-control" readonly="readonly"/>
					</td>
					<td>
						<input id="mdsProjectQuotaList{{idx}}_itemCode" name="mdsProjectQuotaList[{{idx}}].itemCode"  value="{{row.itemCode}}"  class="form-control" readonly="readonly"/>
					</td>
					<td>
						<input id="mdsProjectQuotaList{{idx}}_itemName" name="mdsProjectQuotaList[{{idx}}].itemName"  value="{{row.itemName}}"  class="form-control" readonly="readonly"/>
					</td>
					<td>
						<input id="mdsProjectQuotaList{{idx}}_normalField" name="mdsProjectQuotaList[{{idx}}].normalField"  value="{{row.normalField}}"  class="form-control" readonly="readonly"/>
					</td>
					<td>
						<input id="mdsProjectQuotaList{{idx}}_modelNm" name="mdsProjectQuotaList[{{idx}}].modelNm" type="text" value="{{row.modelNm}}"    class="form-control" readonly="readonly"/>
					</td>
					<td>
						<input id="mdsProjectQuotaList{{idx}}_specs" name="mdsProjectQuotaList[{{idx}}].specs" type="text" value="{{row.specs}}"    class="form-control" readonly="readonly"/>
					</td>
					<td>
						<input id="mdsProjectQuotaList{{idx}}_amount" name="mdsProjectQuotaList[{{idx}}].amount" type="text" value="{{row.amount}}"    class="form-control" readonly="readonly"/>
					</td>
					<td>
						<input id="mdsProjectQuotaList{{idx}}_pcs" name="mdsProjectQuotaList[{{idx}}].pcs" type="text" value="{{row.pcs}}"    class="form-control" readonly="readonly"/>
					</td>
					<td>
						<input id="mdsProjectQuotaList{{idx}}_purchaseAmount" name="mdsProjectQuotaList[{{idx}}].purchaseAmount" type="text" value="{{row.purchaseAmount}}"    class="form-control required digits"/>
					</td>
					<td>
						<div class='input-group form_datetime' id="mdsProjectQuotaList{{idx}}_requiredDate">
		                    <input type='text'  name="mdsProjectQuotaList[{{idx}}].requiredDate" class="form-control required"  value="{{row.requiredDate}}"/>
		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
					</td>
					<td>
						<input id="mdsProjectQuotaList{{idx}}_state" name="mdsProjectQuotaList[{{idx}}].state" type="text" value="{{row.state}}"    class="form-control" readonly="readonly"/>
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var mdsProjectQuotaListRowIdx = 0, mdsProjectQuotaListTpl = $("#mdsProjectQuotaListTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(mdsProjectpcmplan.mdsProjectQuotaList)};
					for (var i=0; i<data.length; i++){
						addRow('#mdsProjectQuotaList', mdsProjectQuotaListRowIdx, mdsProjectQuotaListTpl, data[i]);
						mdsProjectQuotaListRowIdx = mdsProjectQuotaListRowIdx + 1;
					}
				});
				
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>