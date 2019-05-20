<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#mdsProjectpcmplanTable').bootstrapTable({
		 
		  //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //显示刷新按钮
               showRefresh: true,
               //显示切换手机试图按钮
               showToggle: true,
               //显示 内容列下拉框
    	       showColumns: false,
    	       //显示到处按钮
    	       showExport: true,
    	       //显示切换分页按钮
    	       showPaginationSwitch: true,
    	       //显示详情按钮
    	       detailView: false,
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
               url: "${ctx}/mds/mdsprojectpcmplan/data",
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
               contextMenuTrigger:"right",//pc端 按右键弹出菜单
               contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
               contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){
                   if($el.data("item") == "edit"){
                   	edit(row.sysMarketListId);
                   }
               },
              
               onClickRow: function(row, $el){
               },
               columns: [{
		        checkbox: true
		       
		    },
		    {
		        field: 'contractNo',
		        title: '合同编号'
		    },
			{
		        field: 'mdsItemCode',
		        title: '产品编号',
		        formatter:function(value, row , index){
		        	return "<a href='javascript:edit(\""+row.sysMarketListId+"\")'>"+value+"</a>";
		         }
		    },
		    {
		        field: 'mdsItemName',
		        title: '产品名字'
		    },
		    {
		        field: 'amount',
		        title: '数量'
		    },
		    {
		        field: 'deliveryDate',
		        title: '交货日期'
		    },
		    {
		        field: 'principalName',
		        title: '负责人'
		    },
		    {
		        field: 'purchaseStartDate',
		        title: '采购开始时间'
		    },
		    {
		    	field: 'purchaseEndDate',
		        title: '采购结束时间'
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#mdsProjectpcmplanTable').bootstrapTable("toggleView");
		}
	  
	  $('#mdsProjectpcmplanTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#edit').prop('disabled', $('#mdsProjectpcmplanTable').bootstrapTable('getSelections').length!=1);
        });
		  
		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#mdsProjectpcmplanTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定重置按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		   $("#searchForm  .select-item").html("");
		  $('#mdsProjectpcmplanTable').bootstrapTable('refresh');
		});
		
		 $('#purchaseStartDate').datetimepicker({
			 format: "YYYY-MM-DD"
		});
		$('#purchaseStartDateEnd').datetimepicker({
			 format: "YYYY-MM-DD"
		});
		$('#purchaseEndDateStart').datetimepicker({
			 format: "YYYY-MM-DD"
		});
		$('#purchaseEndDate').datetimepicker({
			 format: "YYYY-MM-DD"
		});
	});
		
  function getIdSelections() {
        return $.map($("#mdsProjectpcmplanTable").bootstrapTable('getSelections'), function (row) {
            return row.sysMarketListId
        });
    }
  
  function edit(sysMarketListId){
	  if(sysMarketListId == undefined){
		  jp.openDialog('编辑临时计划申请表', "${ctx}/mds/mdsprojectpcmplan/form?sysMarketListId=" +getIdSelections(),'1200px', '600px', $('#mdsProjectpcmplanTable'));
	  }else{
		  jp.openDialog('编辑临时计划申请表', "${ctx}/mds/mdsprojectpcmplan/form?sysMarketListId=" +sysMarketListId,'1200px', '600px', $('#mdsProjectpcmplanTable'));
	  }
	  
  }
</script>

