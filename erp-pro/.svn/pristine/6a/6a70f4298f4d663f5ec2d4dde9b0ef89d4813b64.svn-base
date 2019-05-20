<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>台账管理</title>
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
					jp.post("${ctx}/wms/maininventory/wmsMainInventory/save",$('#inputForm').serialize(),function(data){
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
		<form:form id="inputForm" modelAttribute="wmsMainInventory" action="${ctx}/wms/maininventory/wmsMainInventory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered">
		   <tbody>
		 	</tbody>
		</table>
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">库房记录表：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th>物料编码</th>
						<th>物料名称</th>
						<th>操作数</th>
						<th>操作人</th>
						<th>操作时间</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody id="wmsMainRecordList">
				</tbody>
			</table>
			<script type="text/template" id="wmsMainRecordTpl">//<!--
				<tr id="wmsMainRecordList{{idx}}">
					<td class="hide">
						<input id="wmsMainRecordList{{idx}}_id" name="wmsMainRecordList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="wmsMainRecordList{{idx}}_delFlag" name="wmsMainRecordList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						{{row.itemCode}}
					</td>

					<td>
						{{row.itemName}}
					</td>

					<td>
						{{row.qty}}
					</td>
					
					<td>
						{{row.createBy.name}}
					</td>
					
					<td>
						{{row.createDate}}
					</td>
					
					<td>
						{{row.state}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var wmsMainRecordRowIdx = 0, wmsMainRecordTpl = $("#wmsMainRecordTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(wmsMainInventory.wmsMainRecordList)};
					for (var i=0; i<data.length; i++){
						addRow('#wmsMainRecordList', wmsMainRecordRowIdx, wmsMainRecordTpl, data[i]);
						wmsMainRecordRowIdx = wmsMainRecordRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>
		</form:form>
</body>
</html>