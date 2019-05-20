<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<script>
$(document).ready(function() {
	$('#table').bootstrapTable({
		 
		  //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //是否显示行间隔色
               striped: true,
               //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
               cache: false,    
               //是否显示分页（*）  
               pagination: false,   
                //排序方式 
               sortOrder: "asc",  
               //初始化加载第一页，默认第一页
               pageNumber:1,   
               //每页的记录行数（*）   
               pageSize: 10,  
               //可供选择的每页的行数（*）    
               pageList: [10, 25, 50, 'ALL'],
               //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
               url: "${ctx}/mds/mdspcmplanprocurement/collectAllDate",
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
                   return searchParam;
               },
               //分页方式：client客户端分页，server服务端分页（*）
               sidePagination: "server",
	            columns: [
				{
			        field: 'itemCode',
			        title: '物料编号'
			       
			    },{
			        field: 'itemName',
			        title: '物料名称'
			       
			    },{
			        field: 'normalField',
			        title: '标准号'
			    },{
			        field: 'modelnm',
			        title: '材质'
			    },{
			        field: 'specs',
			        title: '规格/型号'
			    },{
			        field: 'purchaseAmount',
			        title: '采购数'
			       
			    },{
			        field: 'requiredDate',
			        title: '需求时间'
			       
			    },{
			        field: 'state',
			        title: '状态',
			        formatter:function(value,row,index){
			        	return jp.getDictLabel(${fns:toJson(fns:getDictList('mds_procurement_plan_state'))}, value, "-");
			        }
			    }
			    ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，默认关闭tab

		  $('#table').bootstrapTable("toggleView");
		}
		    
	 	$("#search").click("click", function() {// 绑定查询按扭
		  	$('#table').bootstrapTable('refresh');
		});
	 	$("#reset").click("click", function() {// 绑定查询按扭
			  $("#searchForm  #itemCode").val("");
			  $("#searchForm  #itemName").val("");
			  $("#searchForm  #state").val("");
			  $('#table').bootstrapTable('refresh');
		});
	 	 $('#pcmBeginRequiredDate').datetimepicker({
			 format: "YYYY-MM-DD"
		});
		 $('#pcmEndRequiredDate').datetimepicker({
			 format: "YYYY-MM-DD"
		});
	});
		
  function getSelections() {
        return $.map($("#table").bootstrapTable('getSelections'), function (row) {
            return row
        });
    }
  

</script>
</head>
<body class="bg-white">
	
	<div class="wrapper wrapper-content">
	<!-- 搜索 -->
	<div class="accordion-group">
	<div class="accordion-inner">
	<form:form id="searchForm"  class="form form-horizontal well clearfix" modelAttribute="collect">
		<div class="hide">
			<form:input path="pcmItemCode" htmlEscape="false" maxlength="100"  class=" form-control"/>
			<form:input path="pcmItemName" htmlEscape="false" maxlength="100"  class=" form-control"/>
			<input type="date" name="pcmBeginRequiredDate" id="pcmBeginRequiredDate" class="form-control"  
			value="<fmt:formatDate value="${collect.pcmBeginRequiredDate}" pattern="yyyy-MM-dd"/>"> 
			<input type="date" name="pcmEndRequiredDate" id="pcmEndRequiredDate" class="form-control"  
			value="<fmt:formatDate value="${collect.pcmEndRequiredDate}" pattern="yyyy-MM-dd"/>"> 
			<form:input path="pcmProcurementWay" htmlEscape="false" maxlength="100"  class=" form-control"/>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4">
			<label class="label-item single-overflow pull-left" title="物料编码：">物料编码：</label>
			<form:input path="itemCode" htmlEscape="false" maxlength="100"  class=" form-control"/>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4">
			<label class="label-item single-overflow pull-left" title="物料名称：">物料名称：</label>
			<form:input path="itemName" htmlEscape="false" maxlength="100"  class=" form-control"/>
		</div>
		 <div class="col-xs-12 col-sm-6 col-md-4">
			<div style="margin-top:26px">
			  <a  id="search" class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i class="fa fa-search"></i> 查询</a>
			  <a  id="reset" class="btn btn-primary btn-rounded  btn-bordered btn-sm" ><i class="fa fa-refresh"></i> 重置</a>
			 </div>
	    </div>	
	</form:form>
	</div>
	</div>
	<!-- 表格 -->
	<table id="table"></table>
	</div>
</body>
</html>