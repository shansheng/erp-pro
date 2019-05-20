<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>入库管理</title>
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
					jp.post("${ctx}/wms/wmsmaininstorage/wmsMainInStorage/save",$('#inputForm').serialize(),function(data){
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
			var len = $("#wmsMainInStorageListList > tr").length;
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
			wmsMainInStorageListRowIdx = wmsMainInStorageListRowIdx-1;
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
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="WmsMainInList" class="form-horizontal">
		<%-- <form:hidden path="id"/> --%>
		<sys:message content="${message}"/>	
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">领料单申请表：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<a class="btn btn-white btn-sm" onclick="addRow('#wmsMainInStorageList', wmsMainInStorageListRowIdx+1, wmsMainInStorageListTpl);wmsMainInStorageListRowIdx = wmsMainInStorageListRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th><input type="checkbox"  id="allcheck"  onclick="demo()"/></th>
						<th><font color="red">*</font>物料编号</th>
						<th><font color="red">*</font>物料名称</th>
						<th><font color="red">*</font>入库数量</th>
						<th>备注</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="wmsMainInStorageList">
				</tbody>
			</table>
			<script type="text/template" id="wmsMainInStorageListTpl">//<!--
				<tr id="wmsMainInStorageList_{{idx}}">
					<td class="hide">
						<input id="wmsMainInStorageList_{{idx}}_id" name="wmsMainInStorageList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="wmsMainInStorageList_{{idx}}_delFlag" name="wmsMainInStorageList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					<td>
						<input type="checkbox" name="test"/>
					</td>
					<td>
					<sys:wmsItemSelectForForm2 url="${ctx}/mdsitem/mdsItem/dataOfSelf?itemType=M"
										id="wmsMainInStorageList_{{idx}}_item" name="wmsMainInStorageList[{{idx}}].itemId" value="{{row.mdsItemId}}"
										labelName="wmsMainInStorageList[{{idx}}].itemCode" labelValue="{{row.itemCode}}"
										title="选择物料" cssClass="form-control required"
										fieldLabels="物料号|物料名|规格/型号"
										fieldKeys="itemCode|itemName|specs"
										searchLabels="物料号|物料名" searchKeys="itemCode|itemName"></sys:wmsItemSelectForForm2>
					</td>
					<td>
						<input id="wmsMainInStorageList_{{idx}}_itemName" name="wmsMainInStorageList[{{idx}}].itemName" type="text" value="{{row.itemName}}"    class="form-control required" readonly="readonly"/>
					</td>
					
					<td>
						<input id="wmsMainInStorageList_{{idx}}_applyNumber" name="wmsMainInStorageList[{{idx}}].qualified" type="text" value="{{row.qualified}}"    class="form-control required"/>
					</td>
					<td>
						<textarea id="wmsMainInStorageList_{{idx}}_remarks" name="wmsMainInStorageList[{{idx}}].remarks" rows="2"    class="form-control ">{{row.remarks}}</textarea>
					</td>
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#wmsMainInStorageList_{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var wmsMainInStorageListRowIdx = 0, wmsMainInStorageListTpl = $("#wmsMainInStorageListTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(WmsMainInList.wmsMainInStorageList)};
					if (data.length > 0 ){
						for (var i=0; i<data.length; i++){
							addRow('#wmsMainInStorageList', wmsMainInStorageListRowIdx, wmsMainInStorageListTpl, data[i]);
							wmsMainInStorageListRowIdx = wmsMainInStorageListRowIdx + 1;
						}
					} else {
						addRow('#wmsMainInStorageList', wmsMainInStorageListRowIdx, wmsMainInStorageListTpl);
					}
				});
			</script>
			</div>
		</div>
		</div>
	</form:form>
</body>
</html>