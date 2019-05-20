<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>临时计划申请表管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
		var temIndex;
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
					jp.post("${ctx}/templan/testTemporaryPlan/save",$('#inputForm').serialize(),function(data){
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
	window.onload = function(){
			
			var timestamp = (new Date()).getTime();
			$("#code").val(timestamp);
		}
		
	
		function addRow(list, idx, tpl, row){
			
			 temIndex = idx;
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='radio']").each(function(){
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
			check.each(function() {
				  var row=$(this).parent("td").parent("tr");
				var id = row.find(".demandDate").attr('id');
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
			/* if($("#id").val()!=""){
				console.log(111111); */
				jp.post("${ctx}/templan/testTemporaryPlan/checkCode",$('#inputForm').serialize(),function(data){
					if(data.success){
	                	$table.bootstrapTable('refresh');
	                	jp.close($topIndex);//关闭dialog
	                }else{
	                	$("#code").val("");
	    	  			jp.error(data.msg);
	                }
				})
			/* } */
		}
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="testTemporaryPlan" action="${ctx}/templan/testTemporaryPlan/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>申请单号：</label></td>
					<td class="width-35">
						<form:input path="code" htmlEscape="false"  id="code" onblur="checkCode()"  class="form-control required" readonly="true"/>
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
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">临时订单物料详情表：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<a class="btn btn-white btn-sm" onclick="addRow('#testTemporaryPlanListList', testTemporaryPlanListRowIdx, testTemporaryPlanListTpl);testTemporaryPlanListRowIdx = testTemporaryPlanListRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
		
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
				<tbody id="testTemporaryPlanListList">
				</tbody>
			</table>
			<script type="text/template" id="testTemporaryPlanListTpl">//<!--
				<tr id="testTemporaryPlanListList{{idx}}">
					<td class="hide">
						<input id="testTemporaryPlanListList{{idx}}_id" name="testTemporaryPlanListList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="testTemporaryPlanListList{{idx}}_delFlag" name="testTemporaryPlanListList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					<td>
						<input type="checkbox"  name="test" />
					</td>
					<td>
						<sys:temItemSelectFormForm url="${ctx}/mdsitem/mdsItem/data?itemType=M"
										id="testTemporaryPlanListList_{{idx}}_item" name="testTemporaryPlanListList[{{idx}}].itemId" value="{{row.itemId}}"
										labelName="testTemporaryPlanListList[{{idx}}].itemCode" labelValue="{{row.itemCode}}"
										title="选择物料" cssClass="form-control required"
										fieldLabels="物料号|物料名|规格/型号"
										fieldKeys="itemCode|itemName|specs"
										searchLabels="物料号|物料名" searchKeys="itemCode|itemName"></sys:temItemSelectFormForm>
					</td>
					<td>
						<input id="testTemporaryPlanListList_{{idx}}_itemName" name="testTemporaryPlanListList[{{idx}}].itemName" type="text" value="{{row.itemName}}"    class="form-control required" readonly="readonly"/>
					</td>
					<td>
						<input id="testTemporaryPlanListList_{{idx}}_applyNumber" name="testTemporaryPlanListList[{{idx}}].applyNumber" type="text" value="{{row.applyNumber}}" onkeyup="value=value.replace(/[^\d.]/g,'')"    class="form-control required digits"/>
					</td>
					<td>
						<div class='input-group form_datetime' id="testTemporaryPlanListList{{idx}}_demandDate" >
		                    <input type='text'  name="testTemporaryPlanListList[{{idx}}].demandDate" class="form-control demandDate required"  id="demandDate{{idx}}" value="{{row.demandDate}}"/>
		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>						            
					</td>
					
					
					<td>
						<textarea id="testTemporaryPlanListList{{idx}}_remarks" name="testTemporaryPlanListList[{{idx}}].remarks" rows="2"    class="form-control ">{{row.remarks}}</textarea>
					</td>
					
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#testTemporaryPlanListList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var testTemporaryPlanListRowIdx = 0, 
				testTemporaryPlanListTpl = $("#testTemporaryPlanListTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(testTemporaryPlan.testTemporaryPlanListList)};
					for (var i=0; i<data.length; i++){
				
						addRow('#testTemporaryPlanListList', testTemporaryPlanListRowIdx, testTemporaryPlanListTpl, data[i]);
						testTemporaryPlanListRowIdx = testTemporaryPlanListRowIdx + 1;
					}
					addRow('#testTemporaryPlanListList', testTemporaryPlanListRowIdx,testTemporaryPlanListTpl);
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>