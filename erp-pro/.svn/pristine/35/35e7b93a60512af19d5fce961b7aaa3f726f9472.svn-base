<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>领料单流程管理</title>
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
		/*
		页面加载完之后，自动生成订单号
		*/
		window.onload = function(){
			
			var timestamp = (new Date()).getTime();
			$("#code").val(timestamp);
		}
		
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+"_"+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+"_"+idx).find("input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
			$(list+"_"+idx).find(".form_datetime").each(function(){
				 $(this).datetimepicker({
					 format: "YYYY-MM-DD"
			    });
			});
		}
		function delRow(obj, prefix){
			var len = $("#matRequisitionListList > tr").length;
			//if (len == 1){
			//	jp.warning("一条数据时不允许删除");
		    //	return;
			//}
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
			matRequisitionListRowIdx = matRequisitionListRowIdx-1;
		}
		/*
		 一键修改时间
		*/
		function changeDate() {
			var check = $("input[name=test]:checked");
			var len = check.length;
			if (len <= 0) {
				alert("请选择一条数据!");
			}
			var demandDate = document.getElementById("temDemandDate").value;
			console.log("demandDate:" + demandDate)
			check.each(function() {
				  var row=$(this).parent("td").parent("tr");
				var id = row.find(".demandDate").attr('id');
				console.log("id:" + id)
				$("#"+id).val(demandDate);
			})
		}
		/*
		  全选
		*/
		function demo(){
			var allcheck=document.getElementById("allcheck");
			var choice=document.getElementsByName("test");
			for(var i=0;i<choice.length;i++){
			choice[i].checked=allcheck.checked;
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
						<form:input path="code" htmlEscape="false" onblur="checkCode()"   class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">申请原因：</label></td>
					<td class="width-35">
						<form:textarea path="remarks" htmlEscape="false" rows="4"    class="form-control "/>
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
			<a class="btn btn-white btn-sm" onclick="addRow('#matRequisitionListList', matRequisitionListRowIdx+1, matRequisitionListTpl);matRequisitionListRowIdx = matRequisitionListRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
				<tr>
						<th colspan="2" align="center">
							<label style="height: 34px;padding: 8px 15px"><font color="red">*</font>一键选择需求时间：</label>
						</th>
						<th colspan="2">
							<input type="date"  id="temDemandDate"  class="form-control" onchange="changeDate('temDemandDate')" value/> 
					
						</th>
						<th colspan="8">
					</th>
					</tr>
					<tr>
						<th class="hide"></th>
						<th><input type="checkbox"  id="allcheck"  onclick="demo()"/></th>
						<th><font color="red">*</font>物料编号</th>
						<th><font color="red">*</font>物料名称</th>
						<th><font color="red">*</font>申请数量</th>
						<th><font color="red">*</font>需求日期</th>
						<th>备注</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="matRequisitionListList">
				</tbody>
			</table>
			<script type="text/template" id="matRequisitionListTpl">//<!--
				<tr id="matRequisitionListList_{{idx}}">
					<td class="hide">
						<input id="matRequisitionListList_{{idx}}_id" name="matRequisitionListList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="matRequisitionListList_{{idx}}_delFlag" name="matRequisitionListList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					<td>
						<input type="checkbox" name="test"/>
					</td>
					<td>
					<sys:proItemSelectForForm url="${ctx}/mdsitem/mdsItem/data?itemType=M"
										id="matRequisitionListList_{{idx}}_item" name="matRequisitionListList[{{idx}}].mdsItemId" value="{{row.mdsItemId}}"
										labelName="matRequisitionListList[{{idx}}].itemCode" labelValue="{{row.itemCode}}"
										title="选择物料" cssClass="form-control required"
										fieldLabels="物料号|物料名|规格/型号"
										fieldKeys="itemCode|itemName|specs"
										searchLabels="物料号|物料名" searchKeys="itemCode|itemName"></sys:proItemSelectForForm>
					</td>
					<td>
						<input id="matRequisitionListList_{{idx}}_itemName" name="matRequisitionListList[{{idx}}].itemName" type="text" value="{{row.itemName}}"    class="form-control required" readonly="readonly"/>
					</td>
					
					<td>
						<input id="matRequisitionListList_{{idx}}_applyNumber" name="matRequisitionListList[{{idx}}].applyNumber" type="text" value="{{row.applyNumber}}"    class="form-control required"/>
					</td>
					
					
				
					
					
				<td>
						<div class='input-group form_datetime' id="matRequisitionListList_{{idx}}_demandDate">
		                    <input type='text'  name="matRequisitionListList[{{idx}}].demandDate" class="form-control demandDate required"  value="{{row.demandDate}}" id="demandDate{{idx}}"/>
		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>						            
					</td>
					<td>
						<textarea id="matRequisitionListList_{{idx}}_remarks" name="matRequisitionListList[{{idx}}].remarks" rows="2"    class="form-control ">{{row.remarks}}</textarea>
					</td>
					
					
					
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#matRequisitionListList_{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var matRequisitionListRowIdx = 0, matRequisitionListTpl = $("#matRequisitionListTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(matRequisition.matRequisitionListList)};
					if (data.length > 0 ){
						for (var i=0; i<data.length; i++){
							addRow('#matRequisitionListList', matRequisitionListRowIdx, matRequisitionListTpl, data[i]);
							matRequisitionListRowIdx = matRequisitionListRowIdx + 1;
						}
					} else {
						addRow('#matRequisitionListList', matRequisitionListRowIdx, matRequisitionListTpl);
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>