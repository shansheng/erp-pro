<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#wmsInvBalanceBillListVTable').bootstrapTable({
		 
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
               url: "${ctx}/wmsinvbalancebilllist/wmsInvBalanceBillListV/data",
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
                   	window.location = "${ctx}/wmsinvbalancebilllist/wmsInvBalanceBillListV/form?id=" + row.id;
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该结转查询记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/wmsinvbalancebilllist/wmsInvBalanceBillListV/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#wmsInvBalanceBillListVTable').bootstrapTable('refresh');
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
//			,{
//		        field: 'year',
//		        title: '会计年度',
//		        sortable: true
//		    }
//			,{
//		        field: 'period',
//		        title: '期段号',
//		        sortable: true
//		       
//		    }
//			,{
//		        field: 'startDate',
//		        title: '开始日期',
//		        sortable: true
//		       
//		    }
//			,{
//		        field: 'endDate',
//		        title: '结束日期',
//		        sortable: true
//		       
//		    }
			,{
		        field: 'itemCode',
		        title: '物料代码',
		        sortable: true
		       
		    }
			,{
		        field: 'itemName',
		        title: '物料名称',
		        sortable: true
		       
		    }
			,{
		        field: 'currentInitInvQty',
		        title: '期初数',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	return Math.round(value);
		         }
		       
		    }
			,{
		        field: 'currentSumupInQty',
		        title: '转入数',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	return Math.round(value);
		         }
		       
		    }
			,{
		        field: 'currentSumupOutQty',
		        title: '转出数',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	return Math.round(value);
		         }
		    }
			,{
		        field: 'currentSumupAdjustQty',
		        title: '调整数',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	return Math.round(value);
		         }
		       
		    }
			,{
		        field: 'currentOnhandQty',
		        title: '库存数',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	return Math.round(value);
		         }
		       
		    }
			,{
		        field: 'inPrice',
		        title: '单价',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	return Math.round(value*1000)/1000;
		         }
		    }
		    ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#wmsInvBalanceBillListVTable').bootstrapTable("toggleView");
		}
	  
	  $('#wmsInvBalanceBillListVTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#wmsInvBalanceBillListVTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#wmsInvBalanceBillListVTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/wmsinvbalancebilllist/wmsInvBalanceBillListV/import/template';
				  },
			    btn2: function(index, layero){
				        var inputForm =top.$("#importForm");
				        var top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe 
				        inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
				        inputForm.onsubmit = function(){
				        	jp.loading('  正在导入，请稍等...');
				        }
				        inputForm.submit();
					    jp.close(index);
				  },
				 
				  btn3: function(index){ 
					  jp.close(index);
	    	       }
			}); 
		});
		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#wmsInvBalanceBillListVTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#itemCode").val("");
		  $("#itemName").val("");
		  $('#wmsInvBalanceBillListVTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#wmsInvBalanceBillListVTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该结转查询记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/wmsinvbalancebilllist/wmsInvBalanceBillListV/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#wmsInvBalanceBillListVTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  function edit(){
	  window.location = "${ctx}/wmsinvbalancebilllist/wmsInvBalanceBillListV/form?id=" + getIdSelections();
  }
  
</script>