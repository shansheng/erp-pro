<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>用户选择</title>
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/anihead.jsp"%>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@ include file="/webpage/modules/mes/mesworkshift/mesWorkshift-userList.js" %>
	<script type="text/javascript">
		$(document).ready(function() {
			//bootstrap treeview初始化
			$('#jstree').jstree({
				'core' : {
					"multiple" : false,
					"animation" : 0,
					"themes" : { "variant" : "large", "icons":true , "stripes":true},
					'data' : {
						"url" : "${ctx}/sys/office/treeData",
						"dataType" : "json" // needed only if you do not supply JSON headers
					}
				},
				"conditionalselect" : function (node, event) {
				      return false;
				 },
				'plugins' : ['types', 'wholerow'],
				"types":{ 
					'default' : { 'icon' : 'fa fa-file-text-o' }, 
			        '1' : {'icon' : 'fa fa-home'},
					'2' : {'icon' : 'fa fa-umbrella' },
				    '3' : { 'icon' : 'fa fa-group'},
					'4' : { 'icon' : 'fa fa-file-text-o' }
				} 

			}).bind("activate_node.jstree", function (obj, e) {
			    // 处理代码
			    // 获取当前节点
			    var treeNode = e.node;
			    var id = treeNode.id == '0' ? '' :treeNode.id;
				if(treeNode.level == 0){//level=0 代表公司
					$("#companyId").val(id);
					$("#officeId").val("");
				}else{
					$("#companyId").val("");
					$("#officeId").val(id);
				}
				
				$('#table').bootstrapTable('refresh');
			}).on('loaded.jstree', function() {
				$("#jstree").jstree('open_all');
			});
			
			
			//初始化表格
 			$('#table').bootstrapTable({
	  
				//请求方法
				method: 'get',
				dataType: "json",
				 //是否显示行间隔色
				striped: true,
				//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
				cache: false,    
				//是否显示分页（*）  
				pagination: true, 
				 //排序方式 
				sortOrder: "asc",    
				//初始化加载第一页，默认第一页
				pageNumber:1,   
				//每页的记录行数（*）   
				pageSize: 500,  
				//可供选择的每页的行数（*）    
				pageList: [5, 10,  'ALL'],
				//这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
				url: "${ctx}/sys/user/list",
				//默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
				queryParamsType:'',   
				////查询参数,每次调用是会带上这个参数，可自定义
				/*
				queryParams : function(params) {
					var searchParam = $("#searchForm").serializeJSON();
					searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
					searchParam.pageSize = params.limit === undefined? -1 : params.limit;
					searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
				    return searchParam;
				},
				*/
				//分页方式：client客户端分页，server服务端分页（*）
				sidePagination: "server",
				onClickRow: function(row, $el){
				},
				onDblClickRow: function(row, $el){
					$("#existsTable").bootstrapTable('remove', {field: 'id', values: [row.id]});
					$("#existsTable").bootstrapTable('insertRow', {index:0,row:row});
				},
				columns: [{
				        field: 'loginName',
				        title: '登录名',
				        sortable: true
				       
				    }, {
				        field: 'name',
				        title: '姓名',
				        sortable: true,
				    }, {
				        field: 'office.name',
				        title: '部门'
			    }]
			
			});
			
 			//初始已存在人员表格
 			$('#existsTable').bootstrapTable({
	  
				//请求方法
				method: 'get',
				dataType: "json",
				 //是否显示行间隔色
				striped: true,
				//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
				cache: false,    
				//是否显示分页（*）  
				pagination: true, 
				 //排序方式 
				sortOrder: "asc",    
				//初始化加载第一页，默认第一页
				pageNumber:1,   
				//每页的记录行数（*）   
				pageSize: 500,  
				//可供选择的每页的行数（*）    
				pageList: [5, 10,  'ALL'],
				//这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
				url: "${ctx}/sys/user/findUsersByIds?ids=${param.ids}",
				//默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
				queryParamsType:'',   
				////查询参数,每次调用是会带上这个参数，可自定义
				/*
				queryParams : function(params) {
					var searchParam = $("#searchForm").serializeJSON();
					searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
					searchParam.pageSize = params.limit === undefined? -1 : params.limit;
					searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
				    return searchParam;
				},
				*/
				//分页方式：client客户端分页，server服务端分页（*）
				sidePagination: "server",
				onClickRow: function(row, $el){
					
				},
				onDblClickRow: function(row, $el){
					$("#existsTable").bootstrapTable('remove', {field: 'id', values: row.id});
				},
				columns: [{
				        field: 'loginName',
				        title: '登录名',
				       
				    }, {
				        field: 'name',
				        title: '姓名',
				    }, {
				        field: 'office.name',
				        title: '部门'
			    }]
			
			});
 			
			
			  
			  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，默认关闭tab
				  $('#table').bootstrapTable("toggleView");
				}
		});
		//获取全部数据
		function getAllData(){
			return($("#existsTable").bootstrapTable('getData'));
		}
	</script>
</head>
<body class="bg-white">
	
	<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-3 col-md-2" >
			<div id="jstree"></div>
		</div>
		<div class="col-sm-6 col-md-5 animated">
		    <!-- 表格 -->
		    <table id="table"
		           data-toolbar="#toolbar"
		           data-minimum-count-columns="2"
		           data-id-field="id">
		    </table>
		</div>
		<div class="col-sm-5 col-md-5 animated">
			<!-- 表格 -->
		    <table id="existsTable"
		           data-toolbar="#toolbar"
		           data-minimum-count-columns="2"
		           data-id-field="id">
		    </table>
		</div>
	</div>
	</div>
</body>
</html>