<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
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
					jp.post("${ctx}/mdsequipment/mdsEquipment/save",$('#inputForm').serialize(),function(data){
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
			
	        $('#factoryDate').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
	        $('#dateOfReceiving').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
	        $('#dateOfInstall').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
		});
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="mdsEquipment" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>设备代码：</label></td>
					<td class="width-35">
						<form:input path="equipmentCode" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>设备名称：</label></td>
					<td class="width-35">
						<form:input path="equipmentName" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>设备型号/IT型号：</label></td>
					<td class="width-35">
						<form:input path="equipmentModel" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>设备规格：</label></td>
					<td class="width-35">
						<form:input path="equipmentSpec" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>设备大类：</label></td>
					<td class="width-35">
						<form:select path="tpmEquipmentClassId" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('tpm_equipment_class_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">设备重量：</label></td>
					<td class="width-35">
						<form:input path="equipmentWeigth" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">单位：</label></td>
					<td class="width-35">
						<form:input path="mdsUnitId" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">控制系统：</label></td>
					<td class="width-35">
						<form:input path="controlSystem" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>资产类型：</label></td>
					<td class="width-35">
						<form:select path="assetType" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('asset_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">IT设备编号：</label></td>
					<td class="width-35">
						<form:input path="assetNo" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">固定资产编号：</label></td>
					<td class="width-35">
						<form:input path="fixedAssetNo" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">使用部门：</label></td>
					<td class="width-35">
						<sys:treeselect id="useDeptId" name="useDeptId" value="${mdsEquipment.useDeptId}" labelName="" labelValue="${mdsEquipment.useDeptId}"
							title="部门" url="/sys/office/treeData?type=2" cssClass="form-control " allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">所属制造单元 ：</label></td>
					<td class="width-35">
						<form:input path="mdsWorkcenterId" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">使用单位：</label></td>
					<td class="width-35">
						<sys:treeselect id="maintainDeptId" name="maintainDeptId" value="${mdsEquipment.maintainDeptId}" labelName="" labelValue="${mdsEquipment.maintainDeptId}"
							title="使用单位" url="/sys/office/treeData?type=1" cssClass="form-control " allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">供应商：</label></td>
					<td class="width-35">
						<form:input path="supplierSrmVendorId" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-35">
						<form:input path="factoryNo" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">出厂日期：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='factoryDate'>
			                    <input type='text'  name="factoryDate" class="form-control"  value="<fmt:formatDate value="${mdsEquipment.factoryDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</td>
					<td class="width-15 active"><label class="pull-right">到货日期：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='dateOfReceiving'>
			                    <input type='text'  name="dateOfReceiving" class="form-control"  value="<fmt:formatDate value="${mdsEquipment.dateOfReceiving}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">安装日期：</label></td>
					<td class="width-35">
						<p class="input-group">
							<div class='input-group form_datetime' id='dateOfInstall'>
			                    <input type='text'  name="dateOfInstall" class="form-control"  value="<fmt:formatDate value="${mdsEquipment.dateOfInstall}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </p>
					</td>
					<td class="width-15 active"><label class="pull-right">ABC代码：</label></td>
					<td class="width-35">
						<form:input path="abcCode" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>设备状态：</label></td>
					<td class="width-35">
						<form:select path="equipmentStatus" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('equipment_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>