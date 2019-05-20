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
               url: "${ctx}/sysmarketmilestone/sysMarket/data",
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
                       	jp.get("${ctx}/sysmarketmilestone/sysMarket/delete?id="+row.id, function(data){
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
		       
		    } ,{
  		        
  		        title: '操作',
  		      
  		        formatter:function(value, row , index){
  		        	
  		        		var url="<a href='javascript:view(\""+row.id+"\")'>"+"查看"+"</a>"
  		   	        		return url;
  		        	
  		        	
  		     	
  	        		
  	        	
  		        }
  		       
  		    }
              
               ,{
   		        field: 'clientName',
   		        title: '客户名称'
   		    }
   			
   			,{
   		        field: 'contractNo',
   		        title: '合同编号'
   		       
   		    }
   			,{
   		        field: 'contractDate',
   		        title: '合同签订时间',
   		       
                formatter : crtTimeFtt
   		       
   		    }
   			,{
   		        field: 'deliveryDate',
   		        title: '约定交货日期',
   		        
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
   		        field: 'milestoneStatuss',
   		        title: '状态'
   		       
   		    }
   			,{
   		        field: 'milestoneStatus',
   		        title: '状态',
   		        visible: false
   		    }
   			
   			
   			
   		
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#sysMarketTable').bootstrapTable("toggleView");
		}
	  
	  $('#sysMarketTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#sysMarketTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#sysMarketTable').bootstrapTable('getSelections').length!=1);
            $('#dispatch').prop('disabled',$('#sysMarketTable').bootstrapTable('getSelections').length!=1 || $('#sysMarketTable').bootstrapTable('getSelections')[0].milestoneStatuss!='已编制');
        });
	  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/sysmarketmilestone/sysMarket/import/template';
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
  function getIdSelectionss() {
      return $.map($("#sysMarketTable").bootstrapTable('getSelections'), function (row) {
          return row
      });
  }
  function deleteAll(){

		jp.confirm('确认要删除该销售记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/sysmarket/sysMarket/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#sysMarketTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
   function add(){
	  jp.openDialog('新增销售', "${ctx}/sysmarketmilestone/sysMarket/form",'800px', '500px', $('#sysMarketTable'));
  }
  function edit(id){//没有权限时，不显示确定按钮
	 var sysMar= getIdSelectionss();
	
	 if(id == undefined){
			id = getIdSelections();
		}
	 for(var i=0;i<sysMar.length;i++){
		 if(sysMar[i].milestoneStatuss=="已下达"){
	  		 
			
			 jp.error("该订单已下达不可编辑!");
		  		return;
		  	 }
	 }
  	
  	  
	   <shiro:hasPermission name="sysmarketmilestone:sysMarket:edit">
	  jp.openDialog('编辑计划', "${ctx}/sysmarketmilestone/sysMarket/form?id=" + id,'800px', '500px', $('#sysMarketTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="sysmarketmilestone:sysMarket:edit">
	  jp.openDialogView('查看销售', "${ctx}/sysmarketmilestone/sysMarket/form?id=" + id,'800px', '500px', $('#sysMarketTable'));
	  </shiro:lacksPermission>
  }
  
  function view(id){
	  if(id == undefined){
			id = getIdSelections();
		}
	  jp.openDialogView('查看计划', "${ctx}/sysmarketmilestone/sysMarket/view?id=" + id,'800px', '500px', $('#sysMarketTable'));
  }
  function crtTimeFtt(val, row) {
	    if (val != null) {
	            var date = new Date(val);
	            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
	        }
	}
  function dispatch(id){
	  var sysMar= getIdSelectionss();
		 for(var i=0;i<sysMar.length;i++){
			 if(sysMar[i].milestoneStatuss=="已下达"){
				 jp.error("该订单已下达!");
			  		return;
			  	 }
			 if(sysMar[i].milestoneStatuss=="未编制"){
				 jp.error("该订单未编制不可下达!");
			  		return;
			  	 }
		 }
		jp.confirm('确认要下达吗？', function(){
			
			jp.loading();  	
			jp.get("${ctx}/sysmarketmilestone/sysMarket/dispatch?id=" + getIdSelections(), function(data){
   	  		if(data.success){
   	  			$('#sysMarketTable').bootstrapTable('refresh');
   	  			jp.success(data.msg);
   	  		}else{
   	  			jp.error(data.msg);
   	  		}
   	  	})
    	   
		})
	}

</script>


