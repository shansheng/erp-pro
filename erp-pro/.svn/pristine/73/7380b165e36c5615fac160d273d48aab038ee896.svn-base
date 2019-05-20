<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#templanProcurementTable').bootstrapTable({
		 
		  //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //显示刷新按钮
               showRefresh: true,
               //显示切换手机试图按钮
               showToggle: true,
               //显示 内容列下拉框
    	       showColumns: true,
    	       //显示到处按钮
    	       showExport: true,
    	       //显示切换分页按钮
    	       showPaginationSwitch: true,
    	       //显示详情按钮
    	       detailView: true,
    	       	//显示详细内容函数
	           detailFormatter: "detailFormatter",
    	       //最低显示2行
    	       minimumCountColumns: 2,
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
               pageSize: 10,  
               //可供选择的每页的行数（*）    
               pageList: [10, 25, 50, 100],
               //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
               url: "${ctx}/templan/templanprocurement/templanProcurement/data",
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	if(params.sort == "departmentAssessingDate"){
               		params.sort = "a.department_assessing_date";
               	}
               	if(params.sort == "deputyAssessingDate"){
               		params.sort = "a.deputy_assessing_date";
               	}
               	if(params.sort == "createDate"){
               		params.sort = "a.create_date";
               	}
               	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
                   return searchParam;
               },
               //分页方式：client客户端分页，server服务端分页（*）
               sidePagination: "server",
               contextMenuTrigger:"right",//pc端 按右键弹出菜单
               contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
               contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){
            	   if($el.data("item") == "deliver"){
                        jp.confirm('确认要发货吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/templan/templanprocurement/templanProcurement/deliver?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#templanProcurementTable').bootstrapTable('refresh');
                   	  			jp.success(data.msg);
                   	  		}else{
                   	  			jp.error(data.msg);
                   	  		}
                   	  	})
                   	   
                   	});
                      
                   } 
               },
              
               onClickRow: function(row, $el){
               },
               columns: [{
		        checkbox: true
		       
		    }
			,{
		        field: 'code',
		        title: '申请单号',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	if(row.state == 40 || row.state == 45){
		        	return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";}
		        	else{
		        		return value
		        	}
		         }
		       
		    }
			,{
		        field: 'departmentAssessingName',
		        title: '部门审批人'
		       
		    }
			,{
		        field: 'departmentAssessingDate',
		        title: '部门审批时间',
		        sortable: true
		       
		    }
			,{
		        field: 'deputyAssessingName',
		        title: '副总审批人'
		       
		    }
			,{
		        field: 'deputyAssessingDate',
		        title: '副总审批时间',
		        sortable: true
		       
		    }
			,{
		        field: 'createBy.name',
		        title: '申请人'
		       
		    }
			,{
		        field: 'createDate',
		        title: '申请日期',
		        sortable: true
		       
		    }
			,{
		        field: 'remarks',
		        title: '申请原因'
		       
		    },{
		        field: 'state',
		        title: '状态',
		        sortable: true,
		        formatter:function(value, row , index){
		    	  return jp.getDictLabel(${fns:toJson(fns:getDictList('temporary_plan_state'))}, value, "-");
		        	
		        }
		       
		    }
		     ]
		
		});
		
	  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#templanProcurementTable').bootstrapTable("toggleView");
		}
	  
	  $('#templanProcurementTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#deliver').prop('disabled',  !($('#templanProcurementTable').bootstrapTable('getSelections').length == 1 && $('#templanProcurementTable').bootstrapTable('getSelections')[0].state=='40'));
        });
		  
		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#templanProcurementTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		   $("#searchForm  .select-item").html("");
		  $('#templanProcurementTable').bootstrapTable('refresh');
		});
				 $('#departmentAssessingDate').datetimepicker({
					 format: "YYYY-MM-DD"
				});
				 $('#deputyAssessingDate').datetimepicker({
					 format: "YYYY-MM-DD"
				});
				$('#createDate').datetimepicker({
					 format: "YYYY-MM-DD"
				});
				$('#departmentAssessingDateEnd').datetimepicker({
					 format: "YYYY-MM-DD"
				});
				 $('#deputyAssessingDateEnd').datetimepicker({
					 format: "YYYY-MM-DD"
				});
				$('#createDateEnd').datetimepicker({
					 format: "YYYY-MM-DD"
				});
		
	});
/*  function isStatus(row){
	  var flag = true;
	  var list = row.templanProcurementListList;
  	for(var i = 0; i < list.length; i++) {
  		if(list[i].shipmentsStatus == "未发货" || list[i].shipmentsStatus == undefined) {
  			flag = false;
  			return flag;
  		}
  	}
  	return flag;
  }*/
  
  function getIdSelections() {
        return $.map($("#templanProcurementTable").bootstrapTable('getSelections'), function (row) {
        	return row.id
        });
    }
  
  function deliverAll(){

		jp.confirm('确认发货吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/templan/templanprocurement/templanProcurement/deliverAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#templanProcurementTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  
  
  function edit(id){//没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	  jp.openDialog('查看采购发货', "${ctx}/templan/templanprocurement/templanProcurement/form?id=" + id,'800px', '500px', $('#templanProcurementTable'));
  }
  
  
  
  
		   
  function detailFormatter(index, row) {
	  var htmltpl =  $("#templanProcurementChildrenTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	  var html = Mustache.render(htmltpl, {
			idx:row.id
		});
	  $.get("${ctx}/templan/templanprocurement/templanProcurement/detail?id="+row.id, function(templanProcurement){
    	var templanProcurementChild1RowIdx = 0, templanProcurementChild1Tpl = $("#templanProcurementChild1Tpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
		var data1 =  templanProcurement.templanProcurementListList;
		for (var i=0; i<data1.length; i++){
			addRow('#templanProcurementChild-'+row.id+'-1-List', templanProcurementChild1RowIdx, templanProcurementChild1Tpl, data1[i]);
			templanProcurementChild1RowIdx = templanProcurementChild1RowIdx + 1;
		}
				
      	  			
      })
     
        return html;
    }
  
	function addRow(list, idx, tpl, row){
		$(list).append(Mustache.render(tpl, {
			idx: idx, delBtn: true, row: row
		}));
	}
			
</script>
<script type="text/template" id="templanProcurementChildrenTpl">//<!--
	<div class="tabs-container">
		<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-{{idx}}-1" aria-expanded="true">临时订单物料详情表</a></li>
		</ul>
		<div class="tab-content">
				 <div id="tab-{{idx}}-1" class="tab-pane fade in active">
						<table class="ani table">
						<thead>
							<tr>
								<th>物料编号</th>
								<th>物料名称</th>
								<th>申请数量</th>
								<th>审批数量</th>
								<th>需求日期</th>
								<th>发货状态</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody id="templanProcurementChild-{{idx}}-1-List">
						</tbody>
					</table>
				</div>
		</div>//-->
	</script>
	<script type="text/template" id="templanProcurementChild1Tpl">//<!--
				<tr>
					<td>
						{{row.itemCode}}
					</td>
					<td>
						{{row.itemName}}
					</td>
					<td>
						{{row.applyNumber}}
					</td>
					<td>
						{{row.applyovalNumber}}
					</td>
					<td>
						{{row.demandDate}}
					</td>
					<td>
					{{row.shipmentsStatus}}
					</td>
					<td>
						{{row.remarks}}
					</td>
				</tr>//-->
	</script>
