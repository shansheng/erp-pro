<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>材料定额详情添加页</title>
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
			console.log($('#inputForm').serialize());
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					jp.post("${ctx}/mds/mdsquota/mdsQuotaDetail/save",$('#inputForm').serialize(),function(data){
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
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="mdsQuotaDetail" class="form-horizontal">
		<form:hidden path="id"/>
		<input id="mdsQuotaId" name="mdsQuotaId"  type="hidden" value="${mdsQuotaDetail.mdsQuotaId}"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>序号：</label></td>
					<td class="width-35">
						<form:input path="serial" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">物料编码：</label></td>
					<td class="width-35">
						<sys:itemSelectForDetailForm url="${ctx}/mdsitem/mdsItem/data?itemType=M"
										id="item" name="itemId" value="${mdsQuotaDetail.itemId}"
										labelName="itemCode" labelValue="${mdsQuotaDetail.itemCode}"
										title="选择物料" cssClass="form-control required"
										fieldLabels="物料号|物料名|型号/规格|材质|单位"
										fieldKeys="itemCode|itemName|specs|modelNum|mdsUnitId"
										searchLabels="物料号|物料名" searchKeys="itemCode|itemName"></sys:itemSelectForDetailForm>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">物料名称：</label></td>
					<td class="width-35">
						<input id="itemName" name="itemName" type="text" value="${mdsQuotaDetail.itemName}"    class="form-control required" readonly="readonly"/>
					</td>
					<%-- <td class="width-15 active"><label class="pull-right">标准号：</label></td>
					<td class="width-35">
						<input id="normalField" name="normalField" type="text" value="${mdsQuotaDetail.normalField}"    class="form-control" readonly="readonly"/>
					</td> --%>
					<td class="width-15 active"><label class="pull-right">型号/规格：</label></td>
					<td class="width-35">
						<input id="specs" name="specs" type="text" value="${mdsQuotaDetail.specs}"    class="form-control" readonly="readonly"/>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active"><label class="pull-right">材质：</label></td>
					<td class="width-35">
						<input id="modelNum" name="modelNum" type="text" value="${mdsQuotaDetail.modelNum}"    class="form-control" readonly="readonly"/>
					</td>
					<td class="width-15 active"><label class="pull-right">单位：</label></td>
					<td class="width-35">
						<form:select path="mdsUnitId" disabled="true" class="form-control" >
								<form:option value="" label="---请选择---" />
								<form:options items="${fns:getSqlList('SELECT DISTINCT mesewo.unit AS value ,mesewo.unit_text as label,mesewo.sort from prickle mesewo order by mesewo.sort ')}"
											itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</td>
		  		</tr>
		  		<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>单台定额：</label></td>
					<td class="width-35">
						<input id="quota" name="quota" type="text" value="${mdsQuotaDetail.quota}"  onkeyup="value=value.replace(/[^\d.]/g,'')"  class="form-control required" />
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>分类：</label></td>
					<td class="width-35">
						<%-- <input id="classify" name="classify" type="text" value="${mdsQuotaDetail.classify}"    class="form-control required" /> --%>
						<form:select path="classify" class="form-control required">
							<form:options items="${fns:getDictList('mds_quota_classify')}" itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</td>
		  		</tr>
		  		<%-- <tr>
					
					<td class="width-15 active"><label class="pull-right">校验人：</label></td>
					<td class="width-35">
						<input id="verifyBy" name="verifyBy" type="text" value="${mdsQuotaDetail.verifyBy}"    class="form-control " />
					</td>
		  		</tr> --%>
		  		<tr>
		  			<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3">
						<%-- <input id="remarks" name="remarks" type="text" value="${mdsQuotaDetail.remarks}"    class="form-control" /> --%>
						<form:textarea path="remarks" htmlEscape="false" rows="4" class="form-control "/>
					</td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>