<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	//zTree初始化
	$.getJSON("${ctx}/mds/mdsquota/mdsQuota/bootstrapTreeData?id="+$('#mdsQuotaId')[0].value,function(data){
		$('#mdsQuotaDetailTree').treeview({
			data: data,
			levels: 5,
            onNodeSelected: function(event, treeNode) {
            	var id = treeNode.id == '0' ? '' :treeNode.id;
				if(treeNode.level == 1){//level=0 代表公司
					$("#classify").val("");
				}else{
					$("#classify").val(treeNode.id);
				}
				
				$('#mdsQuotaDetailTable').bootstrapTable('refresh');
            },
         });
	});
	
	var mdsQuotaId = $('#mdsQuotaId')[0].value;
	var view = $('#view')[0].value;
	$('#mdsQuotaDetailTable').bootstrapTable({
		 
		  	   //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //数据
               /*data: {mdsQuotaId:}*/
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
               url: "${ctx}/WorkOrderSynthesize/WorkOrderSynthesize/QuotaData?mdsQuotaId="+mdsQuotaId,
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
                  
               },
              
               onClickRow: function(row, $el){
               },
               columns: [{
		        checkbox: true
		       
		    }
             ,{
   		        field: 'mdsQuota.id',
   		        title: '材料定额表ID',
   		        visible: false
   		    }
			,{
		        field: 'serial',
		        title: '序号',
		        sortable: true
		       
		    }
			,{
		        field: 'mdsItemId',
		        title: '物料id',
   		        visible: false
		       
		    }
			,{
		        field: 'itemCode',
		        title: '物料编码',
		        sortable: true
		       
		    }
			,{
		        field: 'itemName',
		        title: '物料名称',
		        sortable: true
		       
		    }
			,{
		        field: 'normalField',
		        title: '标准号',
		        sortable: true
		       
		    }
			,{
		        field: 'modelNum',
		        title: '型号',
		        sortable: true
		       
		    }
			,{
		        field: 'specs',
		        title: '规格',
		        sortable: true
		       
		    }
			,{
		        field: 'quota',
		        title: '单台定额',
		        sortable: true
		       
		    }
			,{
		        field: 'mdsUnitId',
		        title: '单位',
		        sortable: true
		       
		    }
			,{
		        field: 'remarks',
		        title: '备注',
		        sortable: true
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#mdsQuotaDetailTable').bootstrapTable("toggleView");
		}
		$('#add').prop('disabled',$('#mdsQuotaState')[0].value == '30');
		$('#btnImport').prop('disabled',$('#mdsQuotaState')[0].value == '30');
	  
	  $('#mdsQuotaDetailTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled',!($('#mdsQuotaDetailTable').bootstrapTable('getSelections').length ==1 && $('#mdsQuotaState')[0].value != '30'));
            $('#edit').prop('disabled', !($('#mdsQuotaDetailTable').bootstrapTable('getSelections').length ==1 && $('#mdsQuotaState')[0].value != '30'));
        });
		  
	 
		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#mdsQuotaDetailTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#mdsQuotaDetailTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#mdsQuotaDetailTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
</script>