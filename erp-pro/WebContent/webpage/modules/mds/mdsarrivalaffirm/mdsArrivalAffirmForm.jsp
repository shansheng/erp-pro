<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>到货详情</title>
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
					jp.post("${ctx}/mds/mdsarrivalaffirm/mdsArrivalAffirm/save",$('#inputForm').serialize(),function(data){
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
		/* function delRow(obj, prefix){
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
		} */
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="mdsArrivalAffirm" action="${ctx}/mds/mdsarrivalaffirm/mdsArrivalAffirm/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">物料编码：</label></td>
					<td class="width-35">
						<form:input path="itemCode" htmlEscape="false"    class="form-control " readonly="true"/>
					</td>
					<td class="width-15 active"><label class="pull-right">物料名称：</label></td>
					<td class="width-35">
						<form:input path="itemName" htmlEscape="false"    class="form-control " readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">标准号：</label></td>
					<td class="width-35">
						<form:input path="normalField" htmlEscape="false"    class="form-control " readonly="true"/>
					</td>
					<td class="width-15 active"><label class="pull-right">材质：</label></td>
					<td class="width-35">
						<form:input path="modelnm" htmlEscape="false"    class="form-control " readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">规格/型号：</label></td>
					<td class="width-35">
						<form:input path="specs" htmlEscape="false"    class="form-control " readonly="true"/>
					</td>
					<td class="width-15 active"><label class="pull-right">供应商名字：</label></td>
					<td class="width-35">
						<form:input path="supplierName" htmlEscape="false"    class="form-control " readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">采购数量：</label></td>
					<td class="width-35">
						<form:input path="purchaseAmount" htmlEscape="false"    class="form-control " readonly="true"/>
					</td>
					<td class="width-15 active"><label class="pull-right">已到货数量：</label></td>
					<td class="width-35">
						<form:input path="arrivalnum" htmlEscape="false"    class="form-control " readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">需求时间：</label></td>
					<td class="width-35">
						<div class='input-group form_datetime' id='requiredDate'>
							<input type='text' name="requiredDate" class="form-control" value="<fmt:formatDate value="${mdsArrivalAffirm.requiredDate}" pattern="yyyy-MM-dd"/>" readonly="true"/>
							<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</td>
					<td class="width-15 active"><label class="pull-right">采购方式：</label></td>
					<td class="width-35">
						<form:input path="procurementWay" htmlEscape="false"    class="form-control " readonly="true"/>
					</td>
				</tr>
		 	</tbody>
		</table>
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">到货记录：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th>到货数量</th>
						<th>到货确认者</th>
						<th>到货确认时间</th>
					</tr>
				</thead>
				<tbody id="mdsArrivalCheckoutList">
				</tbody>
			</table>
			<script type="text/template" id="mdsArrivalCheckoutTpl">//<!--
				<tr id="mdsArrivalCheckoutList{{idx}}">
					<td class="hide">
						<input id="mdsArrivalCheckoutList{{idx}}_id" name="mdsArrivalCheckoutList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="mdsArrivalCheckoutList{{idx}}_delFlag" name="mdsArrivalCheckoutList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						<input id="mdsArrivalCheckoutList{{idx}}_arrivalNum" name="mdsArrivalCheckoutList[{{idx}}].arrivalNum" type="text" value="{{row.arrivalNum}}"    class="form-control " readonly="readonly"/>
					</td>
					<td>
						<input id="mdsArrivalCheckoutList{{idx}}_arrivalNum" name="mdsArrivalCheckoutList[{{idx}}].createBy.name" type="text" value="{{row.createBy.name}}"    class="form-control " readonly="readonly"/>
					</td>
					<td>
						<input id="mdsArrivalCheckoutList{{idx}}_arrivalNum" name="mdsArrivalCheckoutList[{{idx}}].createDate" type="text" value="{{row.createDate}}"    class="form-control " readonly="readonly"/>
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var mdsArrivalCheckoutRowIdx = 0, mdsArrivalCheckoutTpl = $("#mdsArrivalCheckoutTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(mdsArrivalAffirm.mdsArrivalCheckoutList)};
					for (var i=0; i<data.length; i++){
						addRow('#mdsArrivalCheckoutList', mdsArrivalCheckoutRowIdx, mdsArrivalCheckoutTpl, data[i]);
						mdsArrivalCheckoutRowIdx = mdsArrivalCheckoutRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>