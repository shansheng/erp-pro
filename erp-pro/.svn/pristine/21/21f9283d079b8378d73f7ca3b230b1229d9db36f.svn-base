<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#sysMarketTable').bootstrapTable({
		 
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
               url: "${ctx}/SysMarketShip/SysMarketShip/data",
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
                   	edit(row.id);
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该销售记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/sysmarket/sysMarket/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#sysMarketTable').bootstrapTable('refresh');
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
		       
		    }     ,{
  		      
  		        title: '操作',
  		        formatter:function(value, row , index){
  		        	if(row.shipments!="2"){
  		        		return "<a href='javascript:view(\""+row.id+"\")'>"+"发货"+"</a>";
  		        	}else{
  		        		return "已发货完毕";
  		        	}
  		        }
  		       
  		    } ,{
   		        field: 'clientName',
   		        title: '客户名称'
   		    }
   			,{
   		        field: 'deliveryDate',
   		        title: '约定交货日期',
   		       
                formatter : crtTimeFtt
   		       
   		    },{
   		        field: 'shipmentsDate',
   		        title: '实际交货日期',
   		       
                formatter : crtTimeFtt
   		       
   		    }
   			,{
   		        field: 'contractClass',
   		        title: '订单类型',
   		      
   		        formatter:function(value, row , index){
   		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('contract_class'))}, value, "-");
   		        }
   		       
   		    }
   			,{
   		        field: 'money',
   		        title: '金额(万)'
   		       
   		    },{
   		        field: 'shipments',
   		        title: '是否发货',
   		        formatter:function(value, row , index){
   		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('shipments'))}, value, "-");
   		        }
   		       
   		    }
   		 ,{
		        field: 'isKp',
		        title: '是否开票',
		      
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('is_kp'))}, value, "-");
		        }
		       
		    }
   		,{
		        field: 'kpDate',
		        title: '开票日期',
		       
            formatter : crtTimeFtt
		       
		    }
   			
   			
   			
   			
   		
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#sysMarketTable').bootstrapTable("toggleView");
		}
	  
	  $('#sysMarketTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#edit').prop('disabled',  $('#sysMarketTable').bootstrapTable('getSelections').length!=1 || $('#sysMarketTable').bootstrapTable('getSelections')[0].isKp!='0');

        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/sysmarket/sysMarket/import/template';
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
		  $('#sysMarketTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#sysMarketTable').bootstrapTable('refresh');
		});
		
		$('#beginContractDate').datetimepicker({
			 format: "YYYY-MM-DD"
		});
		$('#endContractDate').datetimepicker({
			 format: "YYYY-MM-DD"
		});
		
	});
		
  function getIdSelections() {
        return $.map($("#sysMarketTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  function view(id){
	  if(id == undefined){
			id = getIdSelections();
		}
	  jp.openDialog('查看销售', "${ctx}/SysMarketShip/SysMarketShip/view?id=" + id,'800px', '500px', $('#sysMarketTable'));
  }

   function edit(){
	   jp.confirm('确认要开票吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/SysMarketShip/SysMarketShip/kp?id=" + getIdSelections(), function(data){
  	  		if(data.success){
  	  			$('#sysMarketTable').bootstrapTable('refresh');
  	  			jp.success(data.msg);
  	  		}else{
  	  			jp.error(data.msg);
  	  		}
  	  	})
   	   
		})
  }
 

  function crtTimeFtt(val, row) {
	    if (val != null) {
	            var date = new Date(val);
	            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
	        }
	}
 

</script>


